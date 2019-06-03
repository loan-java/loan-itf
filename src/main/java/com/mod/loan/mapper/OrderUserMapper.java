package com.mod.loan.mapper;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.OrderUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderUserMapper extends MyBaseMapper<OrderUser> {
    @Select(" select id,order_no as orderNo,source,uid,create_time as createTime  from tb_user_order where order_no not in (select order_no from tb_decision_res_detail) and source=#{source}")
    List<OrderUser> getQjldQueryOrder(@Param("source")Integer source);
}