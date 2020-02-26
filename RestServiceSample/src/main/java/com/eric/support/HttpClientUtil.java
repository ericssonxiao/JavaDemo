package com.eric.support;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

/**
 *
 */
public class HttpClientUtil {
    private final static Log log = LogFactory.getLog(HttpClientUtil.class);
    private static String key;

    public static String sendHttpRequest(HttpEntity entity) {
        String url = entity.getUrl();

        HttpClient httpClient = new HttpClient();
        log.info(url);
        GetMethod getMethod = new GetMethod(entity.getUrl());


        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());

        if (entity.getCookies() != null) {
            getMethod.addRequestHeader("Cookie", entity.getCookies());
        }

        getMethod.setRequestHeader("Connection", "close");
        getMethod.setRequestHeader("Accept-Encoding", "gzip,deflate,sdch");
        if (StringUtils.isBlank(entity.getEncoding())) {
            entity.setEncoding("UTF-8");
        }
        getMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, entity.getEncoding());

        // setting 5 seconds timeout
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(entity.getTimeout());
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(entity.getTimeout());

        InputStream ins = null;
        BufferedReader br = null;
        try {

            //执行getMethod
            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode != HttpStatus.SC_OK) {
                log.error("method failure: " + getMethod.getStatusLine());
            }

            String result;
            Header header = getMethod.getResponseHeader("Content-Encoding");
            if (header != null && "gzip".equalsIgnoreCase(header.getValue())) {
                InputStream is = getMethod.getResponseBodyAsStream();
                GZIPInputStream gzin = new GZIPInputStream(is);

                InputStreamReader isr = new InputStreamReader(gzin, getMethod.getResponseCharSet());
                br = new BufferedReader(isr);
                StringBuffer sb = new StringBuffer();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                    sb.append("\r\n");
                }
                isr.close();
                gzin.close();
                result = sb.toString();
            } else {
                result = getMethod.getResponseBodyAsString();
            }
            log.error("result=" + result);

            entity.setResponseText(result);
            String cookies = updateCookie(getMethod);
            entity.setCookies(cookies);

            return result;
        } catch (HttpException e) {
            log.error("please check your http url address！", e);
        } catch (IOException e) {
            log.error("network exception is happening" + url, e);
        } catch (Exception e) {
            log.error("Exception  is happening", e);
        } finally {
            //关闭流，释放连接
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                log.error("br stream connection close failure", e);
            }

            if (ins != null) {
                try {
                    ins.close();
                } catch (IOException e) {
                    log.error("ins stream connection close failure", e);
                }
            }

            if (getMethod != null) {
                try {
                    getMethod.releaseConnection();
                    httpClient.getHttpConnectionManager().closeIdleConnections(0);
                } catch (Exception e) {
                    log.error("close http connetion failure", e);
                }
            }

        }
        return null;
    }

    private static String updateCookie(GetMethod getMethod) {
        String cookies = null;
        NameValuePair cookieHeader = getMethod.getResponseHeader("Set-Cookie");
        if (cookieHeader != null) {
            cookies = cookieHeader.getValue();
            if (cookies != null) {
                cookies = cookies.substring(0, cookies.indexOf(";"));
            }
        }
        return cookies;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

