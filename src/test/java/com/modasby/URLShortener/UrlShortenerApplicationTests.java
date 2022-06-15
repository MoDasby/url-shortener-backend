package com.modasby.URLShortener;

import com.modasby.URLShortener.DTO.URLDto;
import com.modasby.URLShortener.exception.ExpiredURLException;
import com.modasby.URLShortener.model.URL;
import com.modasby.URLShortener.repository.ShortenerRepository;
import com.modasby.URLShortener.service.ShortenerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

@SpringBootTest
class UrlShortenerApplicationTests {

	@Autowired
	ShortenerRepository shortenerRepository;

	@Autowired
	ShortenerService shortenerService;

	@Test
	void checkExpiredURL() {

		URL entity = new URL();

		entity.setURL("https://www.google.com");
		entity.setCreatedAt(new Date(1000 * 60 * 60 * 25));

		shortenerRepository.save(entity);

		assertThrows(ExpiredURLException.class, () -> shortenerService.getLongURL(entity.getKey()));
	}

	@Test
	void checkNotExpiredURL() {

		URL entity = new URL();

		entity.setURL("https://www.google.com");
		Date notExpiredDate = new Date(new Date().getTime() + (1000 * 60 * 60 * 24));
		entity.setCreatedAt(notExpiredDate);

		shortenerRepository.save(entity);

		assertEquals(entity.getURL(), shortenerService.getLongURL(entity.getKey()).getLongURL());
	}
}
