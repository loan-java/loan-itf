package com.mod.loan.service;


import com.mod.loan.common.mapper.BaseService;
import com.mod.loan.model.UserAddressList;

import java.util.List;

public interface UserAddressListService extends BaseService<UserAddressList,Long> {

    List<UserAddressList> findByStatus(Integer status);

    int updateTaskIdByUid(Long uid,String taskId);
}