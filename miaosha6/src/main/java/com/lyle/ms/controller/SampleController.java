package com.lyle.ms.controller;

import com.lyle.ms.model.User;
import com.lyle.ms.rabbitmq.MQSender;
import com.lyle.ms.redis.RedisService;
import com.lyle.ms.redis.UserKey;
import com.lyle.ms.result.CodeMsg;
import com.lyle.ms.result.Result;
import com.lyle.ms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
public class SampleController {

  @Autowired
  UserService userService;

  @Autowired
  RedisService redisService;

  @Autowired
  MQSender sender;

//	@RequestMapping("/mq/header")
//    @ResponseBody
//    public Result<String> header() {
//		sender.sendHeader("hello,imooc");
//        return Result.success("Hello，world");
//    }
//	
//	@RequestMapping("/mq/fanout")
//    @ResponseBody
//    public Result<String> fanout() {
//		sender.sendFanout("hello,imooc");
//        return Result.success("Hello，world");
//    }
//	
//	@RequestMapping("/mq/topic")
//    @ResponseBody
//    public Result<String> topic() {
//		sender.sendTopic("hello,imooc");
//        return Result.success("Hello，world");
//    }
//	
//	@RequestMapping("/mq")
//    @ResponseBody
//    public Result<String> mq() {
//		sender.send("hello,imooc");
//        return Result.success("Hello，world");
//    }

  @RequestMapping("/hello")
  @ResponseBody
  public Result<String> home() {
    return Result.success("Hello，world");
  }

  @RequestMapping("/error")
  @ResponseBody
  public Result<String> error() {
    return Result.error(CodeMsg.SESSION_ERROR);
  }

  @RequestMapping("/hello/themaleaf")
  public String themaleaf(Model model) {
    model.addAttribute("name", "thymeleaf");
    return "hello";
  }

  @RequestMapping("/db/get")
  @ResponseBody
  public Result<User> dbGet() {
    User user = userService.getById(1);
    return Result.success(user);
  }


  @RequestMapping("/db/tx")
  @ResponseBody
  public Result<Boolean> dbTx() {
    userService.tx();
    return Result.success(true);
  }

  @RequestMapping("/redis/get")
  @ResponseBody
  public Result<User> redisGet() {
    User user = redisService.get(UserKey.getById, "" + 1, User.class);
    return Result.success(user);
  }

  @RequestMapping("/redis/set")
  @ResponseBody
  public Result<Boolean> redisSet() {
    User user = new User();
    user.setId(1);
    user.setName("1111");
    redisService.set(UserKey.getById, "" + 1, user);//UserKey:id1
    return Result.success(true);
  }


}
