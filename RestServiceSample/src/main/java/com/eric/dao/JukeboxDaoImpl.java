package com.eric.dao;

import com.eric.model.Jukebox;
import com.eric.support.HttpClientUtil;
import com.eric.support.HttpEntity;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
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
 * JukeboxDaoImpl
 */
@Repository
//@Transactional(readOnly = true)
public class JukeboxDaoImpl implements JukeboxDao, Serializable {

    private static final long serialVersionUID = -704530226840992392L;

    private static final AtomicLong generateID = new AtomicLong();
    private static List<Jukebox> jukeboxes;

    private Logger log = LoggerFactory.getLogger(JukeboxDaoImpl.class);

    {
        jukeboxes = this.callThirdPartAPI();
    }

    @Override
    public List<Jukebox> getAll(int offset, int limit) {
        return jukeboxes;
    }

    @Override
    public Jukebox getById(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }
        for (Jukebox jukebox : jukeboxes) {
            if (jukebox.getId().equalsIgnoreCase(id)) {
                return jukebox;
            }
        }
        return null;
    }

    @Override
    public Jukebox getByModel(String model) {
        if (StringUtils.isBlank(model)) {
            return null;
        }
        for (Jukebox jukebox : jukeboxes) {
            if (jukebox.getModel().equalsIgnoreCase(model)) {
                return jukebox;
            }
        }
        return null;
    }

    @Override
    public Jukebox create(Jukebox jukebox) {
        jukebox.setId(String.valueOf(generateID.incrementAndGet()));
        jukeboxes.add(jukebox);
        return jukebox;
    }

    @Override
    public Jukebox update(Jukebox jukebox) {
        for (Jukebox jb : jukeboxes) {
            if (jukebox.getId().equalsIgnoreCase(jb.getId())) {
                int index = jukeboxes.indexOf(jb);
                jukeboxes.set(index, jukebox);
            }
        }
        return jukebox;
    }

    @Override
    public void delete(String id) {
        Jukebox jukebox = getById(id);
        jukeboxes.remove(jukebox);
    }

    @Override
    public boolean exists(Jukebox jukebox) {
        return getById(jukebox.getId()) != null;
    }

    private List<Jukebox> callThirdPartAPI() {
        log.debug("jukeboxes callThirdPartAPI start...");
        String url = null;
        HttpEntity httpEntity = null;
        HttpClientUtil httpClientUtil = null;
        ObjectMapper mapper = null;

        try {
            httpEntity = new HttpEntity();
            url = "http://my-json-server.typicode.com/touchtunes/tech-assignment/jukes";
            httpEntity.setUrl(url);
            httpClientUtil = new HttpClientUtil();
            String httpResult = httpClientUtil.sendHttpRequest(httpEntity);
            log.debug("JukeboxDaoImpl callThirdPartAPI http result = " + httpResult);

            mapper = new ObjectMapper();
            jukeboxes = Arrays.asList(mapper.readValue(httpResult, Jukebox[].class));
            log.debug("jukeboxes size: " + jukeboxes.size());
            log.debug("jukeboxes[0] id={}, model={}, components={} : ", jukeboxes.get(0).getId(), jukeboxes.get(0).getModel(), jukeboxes.get(0).getComponents().toString());
        } catch (JsonGenerationException e) {
            log.error("JukeboxDaoImpl callThirdPartAPI JsonGenerationException : " + e);
        } catch (JsonMappingException e) {
            log.error("JukeboxDaoImpl callThirdPartAPI JsonMappingException : " + e);
        } catch (IOException e) {
            log.error("JukeboxDaoImpl callThirdPartAPI IOException : " + e);
        } catch (Exception e) {
            log.debug("JukeboxDaoImpl callThirdPartAPI Exception : " + e);
        }
        log.debug("jukeboxes callThirdPartAPI end...");
        return jukeboxes;
    }
}
