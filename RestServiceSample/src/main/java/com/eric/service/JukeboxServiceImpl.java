package com.eric.service;

import com.eric.dao.JukeboxDao;
import com.eric.dao.SettingDao;
import com.eric.model.Jukebox;
import com.eric.model.Setting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 *
 */
@Service
public class JukeboxServiceImpl implements JukeboxService, Serializable {

    private static final long serialVersionUID = -604530226840992392L;

    private Logger log = LoggerFactory.getLogger(JukeboxServiceImpl.class);

    @Autowired
    private JukeboxDao jukeboxDao;

    @Autowired
    private SettingDao settingDao;

    public List<Jukebox> search(String settingId, int offset, int limit) {
        List<Jukebox> jukeboxes = jukeboxDao.getAll(offset, limit);

        List<Jukebox> result = new ArrayList<Jukebox>();

        Setting setting = settingDao.getById(settingId);
        Collection<String> requires = setting.getRequires();

        Collection<String> comRequires = new ArrayList<>();

        for (Jukebox jb : jukeboxes) {
            Collection<HashMap<String, String>> components = jb.getComponents();
            for (HashMap<String, String> h : components) {
                comRequires.add(h.get("name"));
            }
            if (comRequires.containsAll(requires)) {
                result.add(jb);
            }
            comRequires.clear();
        }

        return result;
    }

    @Override
    public List<Jukebox> getAll(int offset, int limit) {
        return jukeboxDao.getAll(offset, limit);
    }

    public Jukebox getById(String id) {
        return jukeboxDao.getById(id);
    }

    public Jukebox getByModel(String model) {
        return jukeboxDao.getByModel(model);
    }

    @Override
    public Jukebox create(Jukebox jukebox) {
        jukeboxDao.create(jukebox);
        return jukebox;
    }

    @Override
    public Jukebox update(Jukebox jukebox) {
        jukeboxDao.update(jukebox);
        return jukebox;
    }

    @Override
    public void delete(String id) {
        jukeboxDao.delete(id);
    }

    @Override
    public boolean exists(Jukebox jukebox) {
        return jukeboxDao.exists(jukebox);
    }
}
