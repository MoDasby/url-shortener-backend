package com.modasby.URLShortener.service;

import com.modasby.URLShortener.DTO.URLDto;
import com.modasby.URLShortener.exception.ExpiredURLException;
import com.modasby.URLShortener.exception.InvalidURLException;
import com.modasby.URLShortener.model.URL;
import com.modasby.URLShortener.repository.ShortenerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Date;

@Service
public class ShortenerService implements IShortenerService {
    @Value("${com.modasby.URLShortener.baseURL}")
    String baseShortURL;
    @Autowired
    private ShortenerRepository shortenerRepository;

    public URLDto generateShortURL(String longURL) {

        URL entity = new URL();

        entity.setURL(longURL);
        entity.setCreatedAt(new Date());

        shortenerRepository.save(entity);

        return new URLDto(
                ServletUriComponentsBuilder
                        .fromHttpUrl(baseShortURL)
                        .path("/" + entity.getKey())
                        .toUriString(),
                entity.getURL()
        );
    }

    public URLDto getLongURL (String key) {

        if (shortenerRepository.findById(key).isPresent()) {
            URL entity = shortenerRepository.findById(key).get();

            if (entity.isExpired()) {
                shortenerRepository.delete(entity);

                throw new ExpiredURLException("URL has expired");
            }

            URLDto url = new URLDto();
            url.setLongURL(entity.getURL());
            url.setShortURL(
                    ServletUriComponentsBuilder
                            .fromHttpUrl(baseShortURL)
                            .path("/" + entity.getKey())
                            .toUriString()
            );

            return url;
        } else {
            throw new InvalidURLException("URL not found");
        }
    }
}
