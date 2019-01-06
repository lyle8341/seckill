package com.lyle.ms.dao;

import java.util.Map;

/**
 * @author Lyle
 * @version v1.0
 * @desc
 * @date 2018-12-27 下午11:02
 * @since 1.8
 */
public interface SeckillDao {

  /**
   * 使用存储过程秒杀
   *
   * @param paramsMap
   */
  void killByProcedure(Map<String, Object> paramsMap);


}