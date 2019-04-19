package com.mod.loan.mapper;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.UserAddressList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserAddressListMapper extends MyBaseMapper<UserAddressList> {

    List<UserAddressList> findByStatus(Integer status);

    int updateTaskIdByUid(@Param("uid")Long uid,@Param("taskId")String taskId);

}