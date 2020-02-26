package com.eric.controller;

import com.eric.model.Setting;
import com.eric.service.SettingService;
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
public class SettingRestController implements Serializable {

    private static final long serialVersionUID = -501110226833112392L;

    private Logger log = LoggerFactory.getLogger(SettingRestController.class);

    @Autowired
    private SettingService settingService;

    @RequestMapping(value = "/settings", method = RequestMethod.GET)
    public ResponseEntity getSettings(@RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset,
                                      @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit) {
        return new ResponseEntity(settingService.getAll(offset, limit), HttpStatus.OK);
    }

    @RequestMapping(value = "/settings/{id}", method = RequestMethod.GET)
    public ResponseEntity getSetting(@PathVariable("id") String id) {

        Setting setting = settingService.getById(id);
        if (setting == null) {
            return new ResponseEntity("No Setting found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(setting, HttpStatus.OK);
    }

    @RequestMapping(value = "/settings/{id}/jukeboxes", method = RequestMethod.GET)
    public ResponseEntity getJukeboxBySettingId(@PathVariable("id") String id,
                                                @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset,
                                                @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit) {

        if (StringUtils.isNoneBlank(id)) {
            return new ResponseEntity(settingService.search(id, offset, limit), HttpStatus.OK);
        }
        return new ResponseEntity("No Setting found for ID " + id, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/settings", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody Setting setting, UriComponentsBuilder ucBuilder) {
        if (settingService.exists(setting)) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        settingService.create(setting);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/settings/{id}").buildAndExpand(setting.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/settings/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Setting> update(@PathVariable String id, @RequestBody Setting setting) {

        Setting currentSetting = settingService.getById(id);

        if (currentSetting == null) {
            return new ResponseEntity<Setting>(HttpStatus.NOT_FOUND);
        }

        currentSetting.setId(setting.getId());
        currentSetting.setRequires(setting.getRequires());

        settingService.update(setting);
        return new ResponseEntity<Setting>(currentSetting, HttpStatus.OK);
    }

    @RequestMapping(value = "/settings/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        Setting setting = settingService.getById(id);

        if (setting == null) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        settingService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
