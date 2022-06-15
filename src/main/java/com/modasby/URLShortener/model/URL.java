package com.modasby.URLShortener.model;

import com.modasby.URLShortener.generator.URLIdGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity @Table(name = "url") @NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class URL {
        private static final long EXPIRATION_TIME = 86400000L;
        @Id
        @GeneratedValue(generator = URLIdGenerator.URL_ID_GENERATOR)
        @GenericGenerator(name = URLIdGenerator.URL_ID_GENERATOR,
                strategy = "com.modasby.URLShortener.generator.URLIdGenerator")
        @Column(name = "id", columnDefinition = "VARCHAR(255)")
        private String key;
        private String URL;
        private Date createdAt;

        public boolean isExpired() {
                Date currentTime = new Date();

                return currentTime.getTime() - createdAt.getTime() > EXPIRATION_TIME;
        }

        public String toString() {
            return "URL{" +
                    "id='" + key + '\'' +
                    ", URL='" + URL + '\'' +
                    '}';
        }
}
