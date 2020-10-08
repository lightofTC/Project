package com.ada.marketbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ada.marketbackend.*.mapper")
public class MarketbackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarketbackendApplication.class, args);
    }

}
