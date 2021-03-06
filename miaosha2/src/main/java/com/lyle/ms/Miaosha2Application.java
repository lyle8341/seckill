package com.lyle.ms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lyle.ms.dao")
public class Miaosha2Application {

  public static void main(String[] args) {
    SpringApplication.run(Miaosha2Application.class, args);
  }
}
