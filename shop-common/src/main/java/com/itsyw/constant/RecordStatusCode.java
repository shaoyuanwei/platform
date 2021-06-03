package com.itsyw.constant;

import lombok.Getter;
import lombok.Setter;

/**
 * @author: YuanWei Shao
 * @date: 2021/6/3 15:30
 * @version: 1.0
 * TODO: 记录基础状态Code
 */
@Getter
public enum  RecordStatusCode {

    /**
     * 基本状态
     */
    RECORD_NOT_PROCEEDED(0, "未进行"),
    RECORD_SUCCESS(1, "已完成"),
    RECORD_ERROR(-1, "失败"),
    /**
     * 审批
     */
    RECORD_APPROVED_SUCCESS(1, "审批通过"),
    RECORD_APPROVED_ERROR(-1, "审批驳回"),
    /**
     * 是否删除
     */
    RECORD_IS_DEL_FALSE(0, "未删除"),
    RECORD_IS_DEL_TRUE(1, "已删除"),
    /**
     * 是否启用
     */
    DATA_IS_ENABLE_FALSE(0, "否"),
    DATA_IS_ENABLE_TRUE(1, "是"),
    /**
     * 使用情况
     */
    DATA_IS_USED_FALSE(0, "未使用"),
    DATA_IS_USED_TRUE(1, "正常"),
    DATA_IS_USED_BAD(2, "报废");

    @Setter
    private int code;

    @Setter
    private String describe;

    RecordStatusCode(int code, String describe) {
        this.code = code;
        this.describe = describe;
    }

}
