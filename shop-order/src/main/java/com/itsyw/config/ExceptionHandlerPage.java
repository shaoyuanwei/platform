package com.itsyw.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: YuanWei Shao
 * @Date: 2020/12/31 16:23
 * @Version: 1.0
 * TODO:
 */
@Component
public class ExceptionHandlerPage implements UrlBlockHandler {

    @Override
    public void blocked(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws IOException {

        /**
         * BlockException: 异常接口，包含Sentinel的五个异常
         * FlowException 限流异常
         * DegradeException 降级异常
         * ParamFlowException 参数限流异常
         * AuthorityException 授权异常
         * SystemBlockException 系统负载异常
         */
        httpServletResponse.setContentType("application/json;charset=utf-8");
        ResponseData data =  null;
        if (e instanceof FlowException) {
            data = new ResponseData(-1, "接口被限流了");
        }
        if (e instanceof DegradeException) {
            data = new ResponseData(-2, "接口被降级了");
        }
        if (e instanceof ParamFlowException) {
            data = new ResponseData(-3, "接口被参数限流了");
        }
        if (e instanceof AuthorityException) {
            data = new ResponseData(-4, "接口被授权限流了");
        }
        if (e instanceof SystemBlockException) {
            data = new ResponseData(-5, "接口被系统负载限流了");
        }

        httpServletResponse.getWriter().write(JSON.toJSONString(data));

    }

}

@Data
@AllArgsConstructor // 全参构造
@NoArgsConstructor
        // 无参构造
class ResponseData {
    private Integer code;
    private String message;
}