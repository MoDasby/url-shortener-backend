package com.modasby.URLShortener.generator;

import com.modasby.URLShortener.repository.ShortenerRepository;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class URLIdGenerator implements IdentifierGenerator {
    public static final String URL_ID_GENERATOR = "URL_ID_GENERATOR";

    @Autowired
    ShortenerRepository shortenerRepository;

    List<String> characters = Arrays.asList(
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
            "u", "m", "v", "w", "x", "y", "z", "A", "B", "C",
            "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
            "N", "O" , "P", "Q", "R", "S", "T", "U", "V", "W",
            "X", "Y", "Z");

    Supplier<String> generateKey = () -> {
        StringBuilder shortURLKey = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            shortURLKey.append(characters.get((int) (Math.random() * characters.size())));
        }

        return shortURLKey.toString();
    };

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {

        return generateKey.get();
    }
}
