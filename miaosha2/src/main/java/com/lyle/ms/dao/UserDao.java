package com.lyle.ms.dao;

import com.lyle.ms.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserDao {

  @Select("select * from user where id = #{id}")
  User getById(@Param("id") int id);

  @Insert("insert into user(id, name)values(#{id}, #{name})")
  int insert(User user);

}
