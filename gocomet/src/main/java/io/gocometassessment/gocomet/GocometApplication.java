package io.gocometassessment.gocomet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@SpringBootApplication
public class GocometApplication {
    public static void main(String[] args) {
        SpringApplication.run(GocometApplication.class, args);
    }
}
