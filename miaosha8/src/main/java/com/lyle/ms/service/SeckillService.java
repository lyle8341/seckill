package com.lyle.ms.service;

import com.lyle.ms.dao.SeckillDao;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;

/**
 * @author Lyle
 * @version v1.0
 * @desc
 * @date 2018-12-27 下午11:17
 * @since 1.8
 */
@Service
public class SeckillService {

  @Resource
  private SeckillDao seckillDao;

  public void seckill(long seckillId, long phone, Date killTime) {
    Map<String, Object> map = new HashMap<>();
    map.put("seckillId", seckillId);
    map.put("phone", phone);
    map.put("killTime", killTime);
    map.put("result", null);
    //result被赋值
    try {
      seckillDao.killByProcedure(map);
      Integer result = MapUtils.getInteger(map, "result", -2);
      //判断...
    } catch (Exception e) {
      //log.error
      throw e;
    }
  }

}