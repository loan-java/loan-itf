//package com.mod.loan.itf.user;
//
//import com.mod.loan.model.UserAddressList;
//import com.mod.loan.service.UserAddressListService;
//import com.mod.loan.util.MoxieOssUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping(value = "user")
//@CrossOrigin("*")
//public class AddressController {
//
//    public static final Logger logger = LoggerFactory.getLogger(AddressController.class);
//
//    @Autowired
//    UserAddressListService userAddressListService;
//
//    @RequestMapping(value = "address_list")
//    public String address_list(Long uid) {
//        String result = "";
//        if (null == uid) {
//            return result;
//        }
//        try {
//            UserAddressList userAddressList = userAddressListService.selectByPrimaryKey(uid);
//            if (userAddressList == null) {
//                return result;
//            }
//            if (0 == userAddressList.getStatus()) {
//                return result;
//            }
//            result = MoxieOssUtil.addressList(userAddressList.getTaskId());
//        } catch (Exception e) {
//            logger.error("uid={}", uid);
//            logger.error("通讯录数据异常", e);
//        }
//        return result;
//    }
//
//
//}
