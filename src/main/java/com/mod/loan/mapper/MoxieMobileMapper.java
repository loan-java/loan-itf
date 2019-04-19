package com.mod.loan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.MoxieMobile;

public interface MoxieMobileMapper extends MyBaseMapper<MoxieMobile> {
	
	/**
	 * 查找运营商记录是否已获取过魔杖数据
	 * @param taskId
	 */
	MoxieMobile selectOneByTaskId(String taskId);
	/**
	 * 将成功获取过魔杖数据的运营商记录magicTag改为1
	 * @param taskId
	 */
	void updateMagicTag(String taskId);
	
	List<MoxieMobile> findMoxieMobileList(@Param("tag") int tag,@Param("limit")int limit);
}