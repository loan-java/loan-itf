package com.mod.loan.mapper;

import com.mod.loan.model.UserAuthInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserAuthInfoMapper extends Mapper<UserAuthInfo> {

    List<UserAuthInfo> getNeedCompletingList();
}