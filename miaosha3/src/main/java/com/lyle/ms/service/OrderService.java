package com.lyle.ms.service;

import com.lyle.ms.dao.OrderDao;
import com.lyle.ms.model.MiaoshaOrder;
import com.lyle.ms.model.MiaoshaUser;
import com.lyle.ms.model.OrderInfo;
import com.lyle.ms.vo.GoodsVo;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

  @Autowired
  OrderDao orderDao;

  public MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(long userId, long goodsId) {
    return orderDao.getMiaoshaOrderByUserIdGoodsId(userId, goodsId);
  }

  @Transactional
  public OrderInfo createOrder(MiaoshaUser user, GoodsVo goods) {
    OrderInfo orderInfo = new OrderInfo();
    orderInfo.setCreateDate(new Date());
    orderInfo.setDeliveryAddrId(0L);
    orderInfo.setGoodsCount(1);
    orderInfo.setGoodsId(goods.getId());
    orderInfo.setGoodsName(goods.getGoodsName());
    orderInfo.setGoodsPrice(goods.getMiaoshaPrice());
    orderInfo.setOrderChannel(1);
    orderInfo.setStatus(0);
    orderInfo.setUserId(user.getId());
    long orderId = orderDao.insert(orderInfo);
    MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
    miaoshaOrder.setGoodsId(goods.getId());
    miaoshaOrder.setOrderId(orderId);
    miaoshaOrder.setUserId(user.getId());
    orderDao.insertMiaoshaOrder(miaoshaOrder);
    return orderInfo;
  }

}
