package com.modasby.URLShortener.repository;

import com.modasby.URLShortener.model.URL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortenerRepository extends JpaRepository<URL, String> {
}
