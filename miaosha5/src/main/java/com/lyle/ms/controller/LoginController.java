package com.lyle.ms.controller;

import com.lyle.ms.redis.RedisService;
import com.lyle.ms.result.Result;
import com.lyle.ms.service.MiaoshaUserService;
import com.lyle.ms.vo.LoginVo;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class LoginController {

  private static Logger log = LoggerFactory.getLogger(LoginController.class);

  @Autowired
  MiaoshaUserService userService;

  @Autowired
  RedisService redisService;

  @RequestMapping("/to_login")
  public String toLogin() {
    return "login";
  }

  @RequestMapping("/do_login")
  @ResponseBody
  public Result<String> doLogin(HttpServletResponse response, @Valid LoginVo loginVo) {
    log.info(loginVo.toString());
    //登录
    String token = userService.login(response, loginVo);
    return Result.success(token);
  }
}
