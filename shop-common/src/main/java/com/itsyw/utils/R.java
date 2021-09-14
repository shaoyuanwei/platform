package com.itsyw.utils;

import com.alibaba.fastjson.JSONObject;
import com.itsyw.constant.HttpStatusCode;

import java.util.HashMap;
import java.util.Map;

public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public R() {
        put("code", HttpStatusCode.HTTP_OK.getCode());
        put("msg", HttpStatusCode.HTTP_OK.getDescribe());
    }

    public static R error() {
        return error(HttpStatusCode.HTTP_FAIL.getCode(), HttpStatusCode.HTTP_FAIL.getDescribe());
    }

    public static R error(String msg) {
        return error(HttpStatusCode.HTTP_INTERNAL_SERVER_ERROR.getCode(),
                HttpStatusCode.HTTP_INTERNAL_SERVER_ERROR.getDescribe());
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok(Object t) {
        R r = new R();
        r.put("data", JSONObject.toJSONString(t));
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok() {
        return new R();
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

}
