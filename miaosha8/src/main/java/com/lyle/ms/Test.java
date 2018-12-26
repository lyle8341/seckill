package com.lyle.ms;

import com.lyle.ms.serializers.jdk.JdkSerializerService;
import com.lyle.ms.serializers.protostuff.ProtostuffSerializerService;

/**
 * @author Lyle
 * @version v1.0
 * @desc
 * @date 2018-12-26 下午10:09
 * @since 1.8
 */
public class Test {

  public static void main(String[] args) throws Exception {

    User1 u1 = new User1();
    u1.setAge(11);
    u1.setName("user1");
    u1.setSex("男");
    //length=101
    byte[] bytes1 = JdkSerializerService.serializeObj(u1);
    User1 obj = JdkSerializerService.getObj(bytes1);

    User2 u2 = new User2();
    u2.setAge(22);
    u2.setName("user2");
    u2.setSex("男");
    //length=14
    byte[] bytes2 = ProtostuffSerializerService.serializeObj(u2);
    User2 obj1 = ProtostuffSerializerService.getObj(bytes2);
    System.out.println("============");
  }
}