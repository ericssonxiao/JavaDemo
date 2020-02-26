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
public class SettingServiceImpl implements SettingService, Serializable {

    private static final long serialVersionUID = -604530226840992392L;

    private Logger log = LoggerFactory.getLogger(SettingServiceImpl.class);

    @Autowired
    private SettingDao settingDao;

    @Autowired
    private JukeboxDao jukeboxDao;

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
    public List<Setting> getAll(int offset, int limit) {
        return settingDao.getAll(offset, limit);
    }

    public Setting getById(String id) {
        return settingDao.getById(id);
    }

    @Override
    public Setting create(Setting setting) {
        settingDao.create(setting);
        return setting;
    }

    @Override
    public Setting update(Setting setting) {
        settingDao.update(setting);
        return setting;
    }

    @Override
    public void delete(String id) {
        settingDao.delete(id);
    }

    @Override
    public boolean exists(Setting setting) {
        return settingDao.exists(setting);
    }
}
