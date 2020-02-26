package com.eric.service;

import com.eric.model.Jukebox;
import com.eric.model.Setting;

import java.util.List;


/**
 *
 */
public interface SettingService {

    // search Setting with page
    public List<Jukebox> search(String id, int offset, int limit);

    public List<Setting> getAll(int offset, int limit);

    public Setting getById(String id);

    public Setting create(Setting setting);

    public Setting update(Setting setting);

    public void delete(String id);

    public boolean exists(Setting setting);
}
