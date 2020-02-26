package com.eric.support;

import org.apache.commons.httpclient.NameValuePair;

public class HttpEntity {
    private static int timeout = 5000;
    private static int thisTimeout = 5000;
    private String url;
    private String cookies;
    private NameValuePair[] post;
    private String param;
    private String responseText;
    private String encoding = "UTF-8";

    public static HttpEntity getInstance(String url, String param, String encoding, int timeout) {
        HttpEntity entity = new HttpEntity();
        entity.setUrl(url);
        entity.setParam(param);
        entity.setEncoding(encoding);
        if (timeout != 0) {
            entity.setTimeout(timeout);
        } else {
            entity.setTimeout(thisTimeout);
        }
        return entity;
    }

    public static HttpEntity getInstance(String url, String param, String encoding) {
        return getInstance(url, param, encoding, 0);
    }

    public String getParam() {
        return param;
    }


    public void setParam(String param) {
        this.param = param;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCookies() {
        return cookies;
    }

    public void setCookies(String cookies) {
        this.cookies = cookies;
    }

    public NameValuePair[] getPost() {
        return post;
    }

    public void setPost(NameValuePair[] post) {
        this.post = post;
    }


    public String getResponseText() {
        return responseText;
    }


    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }


    public String getEncoding() {
        return encoding;
    }


    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }


    public int getTimeout() {
        return timeout;
    }


    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

}
