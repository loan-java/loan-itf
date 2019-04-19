package com.mod.loan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.MoxieZfb;

public interface MoxieZfbMapper extends MyBaseMapper<MoxieZfb> {
	
	List<MoxieZfb> findMoxieZfbList(@Param("tag") int tag,@Param("limit")int limit);
}