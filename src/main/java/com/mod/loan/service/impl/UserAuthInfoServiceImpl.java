package com.mod.loan.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mod.loan.common.enums.OrderSourceEnum;
import com.mod.loan.config.Constant;
import com.mod.loan.mapper.OrderMapper;
import com.mod.loan.mapper.UserAuthInfoMapper;
import com.mod.loan.mapper.UserMapper;
import com.mod.loan.model.Order;
import com.mod.loan.model.User;
import com.mod.loan.model.UserAuthInfo;
import com.mod.loan.service.UserAuthInfoService;
import com.mod.loan.util.OSSUtil;
import com.mod.loan.util.ThreadPoolUtils;
import com.mod.loan.util.bengbeng.BengBengRequestUtil;
import com.mod.loan.util.rongze.RongZeRequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * loan-itf 2019/6/5 huijin.shuailijie Init
 */
@Slf4j
@Service
public class UserAuthInfoServiceImpl implements UserAuthInfoService {

    @Autowired
    UserAuthInfoMapper userAuthInfoMapper;

    @Autowired
    private OrderMapper orderMapper;


    @Autowired
    UserMapper userMapper;

    @Override
    public void getNeedCompletingUserAuthInfo() {
        List<UserAuthInfo> list = userAuthInfoMapper.getNeedCompletingList();
        log.info("补全活体认证图片信息={}", list.size());
        if (list.size() > 0) {
            list.stream().forEach(userAuthInfo -> {
                ThreadPoolUtils.executor.execute(() -> {
                    User user = userMapper.selectByPrimaryKey(userAuthInfo.getUid());
                    Order querOrder = new Order();
                    querOrder.setOrderNo(userAuthInfo.getOrderNo());
                    Order order = orderMapper.selectOne(querOrder);
                    if (order == null) {
                        return;
                    }
                    if (StringUtils.isBlank(user.getImgCertFront()) && StringUtils.isNotBlank(userAuthInfo.getIdPositive())) {
                        JSONObject jsonObject1 = new JSONObject();
                        jsonObject1.put("order_no", userAuthInfo.getOrderNo());
                        jsonObject1.put("fileid", userAuthInfo.getIdPositive());
                        String result1 = null;
                        try {
                            if (OrderSourceEnum.isRongZe(order.getSource())) {
                                result1 = RongZeRequestUtil.doPost(Constant.rongZeQueryUrl, "api.resource.findfile", jsonObject1.toJSONString());
                            } else if (OrderSourceEnum.isBengBeng(order.getSource())) {
                                result1 = BengBengRequestUtil.doPost(Constant.bengBengQueryUrl, "api.resource.findfile", jsonObject1.toJSONString());
                            } else {
                                return;
                            }
                        } catch (Exception e) {
                            log.error("推送用户补充信息:身份证正面信息解析失败" + result1);
                        }
                        JSONObject resultJson1 = JSONObject.parseObject(result1);
                        if (!resultJson1.containsKey("code") || !resultJson1.containsKey("data") || resultJson1.getInteger("code") != 200) {
                            log.error("推送用户补充信息:身份证正面信息解析失败" + result1);
                        }
                        JSONObject data = resultJson1.getJSONObject("data");
                        String base64str = data.getString("filestr");
                        String filesuffix = data.getString("filesuffix");
                        String imgCertFront = OSSUtil.uploadImage(base64str, filesuffix);
                        user.setImgCertFront(imgCertFront);
                    }

                    if (StringUtils.isBlank(user.getImgCertBack()) && StringUtils.isNotBlank(userAuthInfo.getIdNegative())) {
                        JSONObject jsonObject2 = new JSONObject();
                        jsonObject2.put("order_no", userAuthInfo.getOrderNo());
                        jsonObject2.put("fileid", userAuthInfo.getIdNegative());
                        String result2 = null;
                        try {
                            if (OrderSourceEnum.isRongZe(order.getSource())) {
                                result2 = RongZeRequestUtil.doPost(Constant.rongZeQueryUrl, "api.resource.findfile", jsonObject2.toJSONString());
                            } else if (OrderSourceEnum.isBengBeng(order.getSource())) {
                                result2 = BengBengRequestUtil.doPost(Constant.bengBengQueryUrl, "api.resource.findfile", jsonObject2.toJSONString());
                            } else {
                                return;
                            }
                        } catch (Exception e) {
                            log.error("推送用户补充信息:身份证背面信息解析失败" + result2);
                        }
                        JSONObject resultJson2 = JSONObject.parseObject(result2);
                        if (!resultJson2.containsKey("code") || !resultJson2.containsKey("data") || resultJson2.getInteger("code") != 200) {
                            log.error("推送用户补充信息:身份证背面信息解析失败" + result2);
                        }
                        JSONObject data2 = resultJson2.getJSONObject("data");
                        String base64str2 = data2.getString("filestr");
                        String filesuffix2 = data2.getString("filesuffix");
                        String imgCertBack = OSSUtil.uploadImage(base64str2, filesuffix2);
                        user.setImgCertBack(imgCertBack);
                    }

                    if (StringUtils.isBlank(user.getImgFace()) && StringUtils.isNotBlank(userAuthInfo.getPhotoAssay())) {
                        JSONObject jsonObject3 = new JSONObject();
                        jsonObject3.put("order_no", userAuthInfo.getOrderNo());
                        jsonObject3.put("fileid", userAuthInfo.getPhotoAssay());
                        String result3 = null;
                        try {
                            if (OrderSourceEnum.isRongZe(order.getSource())) {
                                result3 = RongZeRequestUtil.doPost(Constant.rongZeQueryUrl, "api.resource.findfile", jsonObject3.toJSONString());
                            } else if (OrderSourceEnum.isBengBeng(order.getSource())) {
                                result3 = BengBengRequestUtil.doPost(Constant.bengBengQueryUrl, "api.resource.findfile", jsonObject3.toJSONString());
                            } else {
                                return;
                            }
                        } catch (Exception e) {
                            log.error("推送用户补充信息:身份证活体信息解析失败" + result3);
                        }
                        JSONObject resultJson3 = JSONObject.parseObject(result3);
                        if (!resultJson3.containsKey("code") || !resultJson3.containsKey("data") || resultJson3.getInteger("code") != 200) {
                            log.error("推送用户补充信息:身份证活体信息解析失败" + result3);
                        }
                        JSONObject data3 = resultJson3.getJSONObject("data");
                        String base64str3 = data3.getString("filestr");
                        String filesuffix3 = data3.getString("filesuffix");
                        String imgFace = OSSUtil.uploadImage(base64str3, filesuffix3);
                        user.setImgFace(imgFace);
                    }
                    user.setUpdateTime(new Date());
                    userMapper.updateByPrimaryKeySelective(user);
                });
            });
        }
    }
}
