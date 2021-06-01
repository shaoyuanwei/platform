package com.itsyw.service.impl;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.itsyw.dao.OrderDao;
import com.itsyw.domain.Order;
import com.itsyw.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: YuanWei Shao
 * @Date: 2020/11/2 13:45
 * @Version: 1.0
 * TODO:
 */
@Service
@Slf4j
public class OrderServiceImpl3 {

    private int i = 0;

    // 定义资源，value指定资源名称
    // 定义当资源内部发生异常的时候的处理逻辑
    // blockHandler 定义当资源内部发生BlockException异常时，应该进入的方法，五个规则触发的异常[捕获的是Sentinel定义的异常]
    // fallback 定义当资源内部发生了Throwable应该进入的方法
    @SentinelResource(
            value = "message",
            blockHandlerClass = OrderServiceImpl3BlockHandler.class,
            fallbackClass = OrderServiceImpl3Fallback.class,
            blockHandler = "blockHandler",
            fallback = "fallback"
    )
    public String message(String name) {
        i++;
        if (i % 3 == 0) {
            throw new RuntimeException();
        }
        return "message";
    }

}
