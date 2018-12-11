package com.lyle.ms.service;

import com.lyle.ms.dao.GoodsDao;
import com.lyle.ms.model.MiaoshaGoods;
import com.lyle.ms.vo.GoodsVo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsService {

  @Autowired
  GoodsDao goodsDao;

  public List<GoodsVo> listGoodsVo() {
    return goodsDao.listGoodsVo();
  }

  public GoodsVo getGoodsVoByGoodsId(long goodsId) {
    return goodsDao.getGoodsVoByGoodsId(goodsId);
  }

  public void reduceStock(GoodsVo goods) {
    MiaoshaGoods g = new MiaoshaGoods();
    g.setGoodsId(goods.getId());
    goodsDao.reduceStock(g);
  }


}
