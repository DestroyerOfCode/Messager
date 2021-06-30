package com.marius.controller.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/health")
public class HealthController {

    @GetMapping
    public ResponseEntity<String> getHealth() {
        return new ResponseEntity<>("Som OK! Bezim!", HttpStatus.OK);
    }
}
