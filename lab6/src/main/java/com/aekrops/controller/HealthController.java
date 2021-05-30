package com.aekrops.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RestController
public class HealthController {
    @RequestMapping(value = "/healthcheck", method = RequestMethod.GET)
    public ResponseEntity returnHealthCheckResponse() {
        return new ResponseEntity<>("Alive :)", HttpStatus.OK);
    }
}
