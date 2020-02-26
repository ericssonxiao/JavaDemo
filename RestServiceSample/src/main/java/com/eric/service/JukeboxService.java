package com.eric.service;

import com.eric.model.Jukebox;

import java.util.List;

/**
 *
 */
public interface JukeboxService {

    // search Setting with page
    public List<Jukebox> search(String settingId, int offset, int limit);

    public List<Jukebox> getAll(int offset, int limit);

    public Jukebox getById(String id);

    public Jukebox getByModel(String model);

    public Jukebox create(Jukebox jukebox);

    public Jukebox update(Jukebox jukebox);

    public void delete(String id);

    public boolean exists(Jukebox jukebox);
}
