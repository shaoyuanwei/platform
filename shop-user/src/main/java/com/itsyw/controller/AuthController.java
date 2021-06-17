package com.itsyw.controller;

import com.itsyw.constant.HttpStatusCode;
import com.itsyw.utils.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/6/11 16:36
 * @Version: 1.0
 * TODO: 验证auth接口
 */
@RestController
public class AuthController {

    @GetMapping("/get")
    public R get() {
        return R.ok().put("code", HttpStatusCode.HTTP_OK.getCode()).put("msg", "获取接口");
    }

    @PostMapping("/update")
    public R update() {
        return R.ok().put("code", HttpStatusCode.HTTP_OK.getCode()).put("msg", "更新接口");
    }

    @GetMapping("/info")
    public R info() {
        return R.ok().put("code", HttpStatusCode.HTTP_OK.getCode()).put("msg", "详情 自由查看 不受限制");
    }

}
