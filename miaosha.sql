drop database if exists miaosha;
create database miaosha character set utf8mb4;
use miaosha;

drop table if exists user;
create table user(
  id bigint not null auto_increment primary key comment '用户id',
  name varchar(16) default null comment '名字'
)ENGINE = InnoDB   default charset = utf8mb4;

drop table if exists miaosha_user;
create table miaosha_user(
  id bigint not null primary key comment '手机号',
  nickname varchar(32) not null,
  password varchar(32) default null comment 'md5(md5(pass明文+固定salt) + salt)',
  salt varchar(10) default null,
  head varchar(128) default null comment '头像',
  register_date datetime default null comment '注册时间',
  last_login_date datetime default null comment '上次登录时间',
  login_count int default '0' comment '登录次数'
)ENGINE = InnoDB   default charset = utf8mb4;

drop table if exists goods;
create table goods (
  id bigint not null auto_increment primary key comment '商品id',
  goods_name varchar(16) default null comment '商品名称',
  goods_title varchar(64) default null comment '商品标题',
  goods_img varchar(64) default null comment '商品图片',
  goods_detail longtext comment '商品详情介绍',
  goods_price decimal(10,2) default '0.00' comment '商品单价',
  goods_stock int default '0' comment '库存，-1表示没有限制'
)ENGINE = InnoDB   default charset = utf8mb4;

insert into goods values
  (1,'iphonex','apple iphone x(A1865) 64G 银色 全网通4G手机','/img/iphonex.png','aple iphone x(A1865) 64G 银色 全网通4G手机',8765.00,100000),
  (2,'华为meta9','华为meta9 4G +32G 月光银 全网通 双卡双待','/img/meta10.png','华为meta9 4G +32G 月光银 全网通 双卡双待',3212.00,-1);

drop table if exists miaosha_goods;
create table miaosha_goods(
  id bigint not null auto_increment primary key comment '秒杀商品表',
  goods_id bigint default null comment '商品id',
  miaosha_price decimal(10,2) default '0.00' comment '秒杀价',
  stock_count int default null comment '库存数量',
  start_date datetime default null comment '秒杀开始时间',
  end_date datetime default null comment '秒杀结束时间'
)engine = InnoDB default charset = utf8mb4;

insert into miaosha_goods values(1,1,0.01,10,'2018-12-18 12:22:34','2018-12-18 12:22:34'),(2,2,0.01,10,'2018-12-18 12:22:34','2018-12-18 12:22:34');

drop table if exists order_info;
create table order_info(
  id bigint not null auto_increment primary key ,
  user_id bigint default null comment '用户id',
  goods_id bigint default null comment '商品id',
  delivery_addr bigint default null comment '收货地址id',
  goods_name varchar(16) default null comment '冗余商品名称',
  goods_count int default '0' comment '商品数量',
  goods_price decimal(10,2) default '0.00' comment '商品单价',
  order_channel tinyint(4) default '0' comment '1pc,2andriod,3ios',
  status tinyint(4) default '0' comment '订单状态，0未支付，1已支付，2已发，3已收,4已退，5完成',
  create_date datetime default null comment '订单创建时间',
  pay_date datetime default null comment '支付时间'
)engine = InnoDB default charset =utf8mb4;

drop table if exists miaosha_order;
create table miaosha_order(
  id bigint not null auto_increment primary key ,
  user_id bigint default null comment '用户id',
  order_id bigint default null comment '订单id',
  goods_id bigint default null comment '商品id'
)engine =InnoDB default charset = utf8mb4;
