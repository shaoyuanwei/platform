package com.itsyw.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.RequestOriginParser;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: YuanWei Shao
 * @Date: 2020/12/31 14:03
 * @Version: 1.0
 * TODO:
 */
@Component
public class RequestOriginParserDefinition implements RequestOriginParser {

    /**
     * 然后交给流控应用 进行匹配
     * app pc
     *
     * @param httpServletRequest request
     * @return String
     * TODO: 定义区分来源：本质作用是通过request域获取到来源标识
     */
    @Override
    public String parseOrigin(HttpServletRequest httpServletRequest) {
        String serviceName = httpServletRequest.getParameter("serviceName");
        return serviceName;
    }

}
