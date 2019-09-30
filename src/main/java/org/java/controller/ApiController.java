package org.java.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ApiController {


    @RequestMapping("pay")
    public void pay(HttpServletResponse httpResponse) throws IOException {
        //获得初始化的AlipayClient
        AlipayClient client = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do", "2016101300679370", "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCPhkZd6y+nm/nPFJ7SlMs/gMq1oglFppj5Nbd5F7yO5l2rYqkf7mZFXPMDgnCwPP5QtWqbDMJs8vt8p67VNAmykRsj2cJGENngcrXpuOmfgxEiALUG8xfO4Ilur2bhC81PJfg1dkLsCN8cNoZW1YMnEVIq5h8MeC3c/JBQALb9gnp9cwhFxv1lzZx4bCKtTj+mb3DC/GC2vVBcW2gIBgJaR0/2zhjKJSQU7Flh5lRxh0o74ePpN6zCVw4GFffyQk0WCH2G4qFfmgFDAxSlGViLkA8FY1E3xyQm4n/eOureDK5/XZzQP0YMujQAo1BOlmAivZIlHLyE5rMKk2ryldNTAgMBAAECggEBAIb6Hr9A6aZVmA/aMquUZ7knJHzBELtCPGdSz2PAoQI7QChoNuniyKe/DIRmbhgwpHO+ya1byJWxxjWrdU7pC521vbJvnrdKLb1Z5B8UWhI7ryctRxJsGB3lKRrBNp26/bDL4qxUtgdJKJpd5OVRE3DwZzgVB2ows+E6XXX/1hh+5Zuoj/adQRtG5zxX6bJRgL+U3ImTORNvA3vAANkDWbBE3BkLlbym9bUNCrLqRQ3fW9GIRWp6PRCu+cj3zOwnzqcoSzKUu9cTRUzCT+T6xi+C64UmRd5Ysu/fbRo5ozX9foIROeGz+5ff4TmdSo19xq//6iO2h7cbNXDXAxEMOKECgYEA78RTQaUIfGzCwENaOFO6TbXWDNPGflHxJeh3MLl9aDCJ6xCH4OUXElezwHvnjYBy5bsosZmMKdsDLJVkG/eqZAyDr8pXVxE1syuf7an18v/5yS21jM3XuFtYuSTqryDfeLVnwdPcgr6e83zTWmiTZe0F9w3IThvR+07xOfG2HUkCgYEAmT3c1Jy37PzlmcdNr1O1RuzhFIZmmDEkwdz0GuRq98ThiW0d03G7nIrgfj/ZYPAuR1/aEnVrO53VpNkzKOlA4bkRn8tqAqABJl/+Nb7kcusjlsc/iTs/ONqBjnYY+hKnXS7Z6zjv1CkqvX0yn5FcYtBVtt8jRbDItJe4Vqrl97sCgYBk7UxqjpCy54lXK0LYAQ6kVY2j2qVsWQvcfC1n+jy61evIYi4AUg7TLs3AkaeX3eD/qTfpAoyIteNxKfV4/FjiQo5QOilnwhvLrupkPa4FOxEnSTFp8uACf5kqXkrXq0vGYP4rDgZ9lsYMEphZLzoUpqBjLY8KqhVK5yEN1hxl2QKBgHoGEsSSjrr9nm7Xj3qfM2ho08oeQ6/I4jYlFZQcczkc42MlKmzm0bgTtjOR8C0K1C8tml68dUkho7AC7IQVSP70+Z2+womYk5HVOn3dpdgEeXBk9Kj4Lp29ERE55SWsmOzQk+KEkiTlKw18lkUN4P0mXDqkcFfcL9XuQnFw4HJlAoGAH16MeuTBZomeTAKiwjiCpvSshXumJDCcwhyqTGFXmHFoYm/BdGGsUQmh++AiYICM2LoiFkgfn2UESa5JsiLG8VFk8kR8sPre/1X7Al+KgS4/lBZXJI+ZOxzJjv6eXnKWhTghXbsZIhILTJ8X5BuVjsMNUg9kfpvGLF0L6PfDbFA=");
        //创建API对应的request
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl("http://www.baidu.com");
        alipayRequest.setNotifyUrl("http://www.baidu.com");//在公共参数中设置回跳和通知地址
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\"20150320010101003\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":9999," +
                "    \"subject\":\"Iphone6 16G\"," +
                "    \"body\":\"Iphone6 16G\"," +
                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\"2088511833207846\"" +
                "    }"+
                "  }");//填充业务参数
        String form="";
        try {
            form = client.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=UTF-8");
        httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }


}
