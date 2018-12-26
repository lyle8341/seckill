package com.lyle.ms;

import java.io.Serializable;

/**
 * @author Lyle
 * @version v1.0
 * @desc
 * @date 2018-12-25 下午11:31
 * @jdk 1.8
 */
public class User1 implements Serializable {

  private static final long serialVersionUID = 4574144982365094030L;

  private String name;

  private int age;

  private String sex;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  @Override
  public String toString() {
    return "User1{" +
        "name='" + name + '\'' +
        ", age=" + age +
        ", sex='" + sex + '\'' +
        '}';
  }
}