package com.lyle.ms.controller;

import com.lyle.ms.model.MiaoshaUser;
import com.lyle.ms.redis.RedisService;
import com.lyle.ms.result.Result;
import com.lyle.ms.service.MiaoshaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

  @Autowired
  MiaoshaUserService userService;

  @Autowired
  RedisService redisService;

  @RequestMapping("/info")
  @ResponseBody
  public Result<MiaoshaUser> info(Model model, MiaoshaUser user) {
    return Result.success(user);
  }

}
