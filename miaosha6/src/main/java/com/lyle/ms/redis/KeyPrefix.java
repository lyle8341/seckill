package com.lyle.ms.redis;

public interface KeyPrefix {

  public int expireSeconds();

  public String getPrefix();

}
