package com.eric.controller;

import com.eric.model.Greeting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 */
@RestController
public class HelloWorldController implements Serializable {

    private static final long serialVersionUID = -504530226840112392L;
    private static final String template = "Hello,%s!";
    private final AtomicLong counter = new AtomicLong();
    private Logger log = LoggerFactory.getLogger(HelloWorldController.class);

    @GetMapping("/hello")
    @ResponseBody
    public ResponseEntity<Greeting> sayHello(@RequestParam(name = "name", required = false, defaultValue = "eric") String name) {
        return new ResponseEntity<>(new Greeting(counter.incrementAndGet(), String.format(template, name)), HttpStatus.OK);
    }
}
