package club.example.oauth2.server;

import club.example.wechatpayment.core.param.OrderQueryParam;
import club.example.wechatpayment.core.result.OrderQueryResult;
import club.example.wechatpayment.service.xmlversion.WeChatPayCoreService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WeChatPaymentStarterTest {

    @Autowired
    private WeChatPayCoreService weChatPayCoreService;

    @Test
    public void testQuery() {
        OrderQueryParam orderQueryParam = new OrderQueryParam();
        orderQueryParam.setOutTradeNo("923845928592489634906806");
        OrderQueryResult orderQueryResult = weChatPayCoreService.queryOrder(orderQueryParam);
        Assert.assertNotNull(orderQueryResult);
    }
}
