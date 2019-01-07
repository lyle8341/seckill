package com.lyle.ms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.lyle.ms.dao"})
public class Miaosha8Application {

  public static void main(String[] args) {
    SpringApplication.run(Miaosha8Application.class, args);
  }

}

