package com.lyle.ms.dao;

import com.lyle.ms.model.MiaoshaUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface MiaoshaUserDao {

  @Select("select * from miaosha_user where id = #{id}")
  public MiaoshaUser getById(@Param("id") long id);
}
