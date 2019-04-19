package com.mod.loan.mapper;

import com.mod.loan.common.mapper.MyBaseMapper;
import com.mod.loan.model.MessageVoice;

public interface MessageVoiceMapper extends MyBaseMapper<MessageVoice> {

    MessageVoice findLatestByCallId(String callId);
}