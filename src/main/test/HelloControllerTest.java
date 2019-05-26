

import com.mod.loan.Application;
import com.mod.loan.itf.sms.moxintong.MoXinTongSms;
import com.mod.loan.mapper.UserSmsMapper;
import com.mod.loan.model.UserSms;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author: whw
 * @Date: 2019/5/14/014 11:05
 */
@RunWith(SpringRunner.class)
@ActiveProfiles
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerTest {


    @Resource
    UserSmsMapper smsMapper;

    @Resource
    MoXinTongSms moXinTongSms;

    /**
     * 向"/test"地址发送请求，并打印返回结果
     *
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        //记录发送成功的次数
        UserSms sms = new UserSms();
        sms.setMobile("1111111");
        sms.setCreateTime(new Date());
        smsMapper.insert(sms);
    }

    /**
     * 向"/test"地址发送请求，并打印返回结果
     *
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        moXinTongSms.send(null);
    }




}
