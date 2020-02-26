package com.eric.dao;

import com.eric.model.Setting;
import com.eric.support.HttpClientUtil;
import com.eric.support.HttpEntity;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * SettingDaoImpl
 */
@Repository
//@Transactional(readOnly = true)
public class SettingDaoImpl implements SettingDao, Serializable {

    private static final long serialVersionUID = -704530289840992392L;
    private static final AtomicLong generateID = new AtomicLong();
    private static List<Setting> settings;
    private Logger log = LoggerFactory.getLogger(SettingDaoImpl.class);

    {
        settings = this.callThirdPartAPI();
    }

    @Override
    public List<Setting> getAll(int offset, int limit) {
        return settings;
    }

    @Override
    public Setting getById(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }
        for (Setting setting : settings) {
            if (setting.getId().equalsIgnoreCase(id)) {
                return setting;
            }
        }
        return null;
    }

    @Override
    public Setting create(Setting setting) {
        setting.setId(String.valueOf(generateID.incrementAndGet()));
        settings.add(setting);
        return setting;
    }

    @Override
    public Setting update(Setting setting) {
        for (Setting s : settings) {
            if (setting.getId().equalsIgnoreCase(s.getId())) {
                int index = settings.indexOf(s);
                settings.set(index, setting);
            }
        }
        return setting;
    }

    @Override
    public void delete(String id) {
        Setting setting = getById(id);
        settings.remove(setting);
    }

    @Override
    public boolean exists(Setting setting) {
        return getById(setting.getId()) != null;
    }

    private List<Setting> callThirdPartAPI() {
        log.debug("settings callThirdPartAPI start...");
        String url = null;
        HttpEntity httpEntity = null;
        HttpClientUtil httpClientUtil = null;
        ObjectMapper mapper = null;

        try {
            httpEntity = new HttpEntity();
            url = "http://my-json-server.typicode.com/touchtunes/tech-assignment/settings";
            httpEntity.setUrl(url);
            httpClientUtil = new HttpClientUtil();
            String httpResult = httpClientUtil.sendHttpRequest(httpEntity);
            log.debug("SettingDaoImpl callThirdPartAPI http result = " + httpResult);

            mapper = new ObjectMapper();
            JsonNode json = mapper.readValue(httpResult, JsonNode.class);
            log.debug("108 result : " + json.get("settings").toString());
            settings = Arrays.asList(mapper.readValue(json.get("settings").toString(), Setting[].class));
            log.debug("Settings size: " + settings.size());
            log.debug("Settings[0] id={}, model={}: ", settings.get(0).getId(), settings.get(0).getRequires().toString());

        } catch (JsonGenerationException e) {
            log.error("SettingDaoImpl callThirdPartAPI JsonGenerationException : " + e);
        } catch (JsonMappingException e) {
            log.error("SettingDaoImpl callThirdPartAPI JsonMappingException : " + e);
        } catch (IOException e) {
            log.error("SettingDaoImpl callThirdPartAPI IOException : " + e);
        } catch (Exception e) {
            log.debug("SettingDaoImpl callThirdPartAPI Exception : " + e);
        }

        log.debug("settings callThirdPartAPI end...");
        return settings;
    }
}
