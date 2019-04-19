package com.mod.loan.mapper;

import org.apache.ibatis.annotations.Param;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.MoxieZfbFail;

public interface MoxieZfbFailMapper extends MyBaseMapper<MoxieZfbFail> {

	MoxieZfbFail selectByTaskId(@Param("task_id") String task_id);
}