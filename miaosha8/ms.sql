# 秒杀执行存储过程
DELIMITER $$    #console ; 转换为

# row_count() 返回上一条修改类型sql(insert,delete,updaet)的影响行数
#row_count 0:未修改数据  >0：修改的行数  <0:sql错误/未执行修改sql(insert,delete,updaet)语句
create procedure `seckill`.`execute_seckil`(in v_seckill_id bigint, in v_phone bigint,
                                            in v_kill_time  timestamp, out r_result int)
  begin
    declare insert_count int default 0;
    start transaction;
    insert ignore into success_killed (seckill_id, user_phone, create_time)
    values (v_seckill_id, v_phone, v_kill_time);
    select row_count() into insert_count;
    if (insert_count = 0)
    then
      rollback;
      set r_result = -1;
    elseif (insert_count < 0)
      then
        rollback;
        set r_result = -2;
    else
      update seckill
      set num = num - 1
      where seckill_id = v_seckill_id
        and end_time > v_kill_time
        and start_time < v_kill_time
        and num > 0;
      select row_count() into insert_count;
      if (insert_count = 0)
      then
        rollback;
        set r_result = 0;
      elseif (insert_count < 0)
        then
          rollback;
          set r_result = -2;
      else
        commit;
        set r_result = 1;
      end if;
    end if;
  end;
$$