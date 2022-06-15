package com.modasby.URLShortener.controller;

import com.modasby.URLShortener.DTO.URLDto;
import com.modasby.URLShortener.service.ShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class ShortenerController {
    @Autowired
    ShortenerService shortenerService;

    @PostMapping("/shortener")
    public ResponseEntity<?> generateShortURL(@RequestBody URLDto url) {
        return ResponseEntity.ok(shortenerService.generateShortURL(url.getLongURL()));
    }

    @GetMapping("/{key}")
    public ResponseEntity<?> redirectToLongURL(@PathVariable String key, HttpServletResponse response) {

        URLDto url = shortenerService.getLongURL(key);

        return ResponseEntity.ok(url);
    }
}
