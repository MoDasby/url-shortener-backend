package com.modasby.URLShortener.service;

import com.modasby.URLShortener.DTO.URLDto;

public interface IShortenerService {
    URLDto generateShortURL(String longURL);
    URLDto getLongURL(String key);
}
