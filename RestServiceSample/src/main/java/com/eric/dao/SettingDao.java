package com.eric.dao;

import com.eric.model.Setting;

import java.util.List;

/**
 *
 */
public interface SettingDao {

    public List<Setting> getAll(int offset, int limit);

    public Setting getById(String id);

    public Setting create(Setting setting);

    public Setting update(Setting setting);

    public void delete(String id);

    public boolean exists(Setting setting);
}
