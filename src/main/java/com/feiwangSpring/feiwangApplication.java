package com.feiwangSpring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author wsq
 * @version 1.0
 * @date 2020/4/17 13:28
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@MapperScan("com.feiwangSpring.dao")
public class feiwangApplication {
    public static void main(String[] args) {
        SpringApplication.run(feiwangApplication.class, args);
    }
}
