package com.itsyw.domain.sys;

import lombok.Data;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/10/11 9:32
 * @Version: 1.0
 * TODO:
 */
@Data
public class Sys {
    /**
     * 服务器名称
     */
    private String computerName;

    /**
     * 服务器Ip
     */
    private String computerIp;

    /**
     * 项目路径
     */
    private String userDir;

    /**
     * 操作系统
     */
    private String osName;

    /**
     * 系统架构
     */
    private String osArch;
}
