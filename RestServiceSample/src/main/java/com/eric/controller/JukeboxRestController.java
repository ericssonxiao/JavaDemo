package com.eric.controller;

import com.eric.model.Jukebox;
import com.eric.service.JukeboxService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.Serializable;

/**
 *
 */
@RestController
public class JukeboxRestController implements Serializable {

    private static final long serialVersionUID = -504530226833112392L;

    private Logger log = LoggerFactory.getLogger(JukeboxRestController.class);

    @Autowired
    private JukeboxService jukeboxService;

    @RequestMapping(value = "/jukeboxes", method = RequestMethod.GET)
    public ResponseEntity getJukeboxes(@RequestParam(value = "settingId", required = false) String settingId,
                                       @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset,
                                       @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit) {

        if (StringUtils.isNoneBlank(settingId)) {
            return new ResponseEntity(jukeboxService.search(settingId, offset, limit), HttpStatus.OK);
        }
        return new ResponseEntity(jukeboxService.getAll(offset, limit), HttpStatus.OK);
    }

    @RequestMapping(value = "/jukeboxes/{id}", method = RequestMethod.GET)
    public ResponseEntity getJukebox(@PathVariable("id") String id) {

        Jukebox jukebox = jukeboxService.getById(id);
        if (jukebox == null) {
            return new ResponseEntity("No Jukebox found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(jukebox, HttpStatus.OK);
    }

    @RequestMapping(value = "/jukeboxes", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody Jukebox jukebox, UriComponentsBuilder ucBuilder) {
        if (jukeboxService.exists(jukebox)) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        jukeboxService.create(jukebox);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/jukeboxes/{id}").buildAndExpand(jukebox.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/jukeboxes/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Jukebox> update(@PathVariable String id, @RequestBody Jukebox jukebox) {

        Jukebox currentJukebox = jukeboxService.getById(id);

        if (currentJukebox == null) {
            return new ResponseEntity<Jukebox>(HttpStatus.NOT_FOUND);
        }

        currentJukebox.setId(jukebox.getId());
        currentJukebox.setModel(jukebox.getModel());

        jukeboxService.update(jukebox);
        return new ResponseEntity<Jukebox>(currentJukebox, HttpStatus.OK);
    }

    @RequestMapping(value = "/jukeboxes/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        Jukebox jukebox = jukeboxService.getById(id);

        if (jukebox == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        jukeboxService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
