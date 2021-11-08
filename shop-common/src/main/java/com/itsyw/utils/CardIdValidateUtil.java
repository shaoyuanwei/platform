package com.itsyw.utils;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/6/7 10:33
 * @Version: 1.0
 * TODO:
 */
public class CardIdValidateUtil {

    /**
     * 区号
     */
    final static Map<Integer, String> zoneNum = new HashMap<>(16);

    static {
        zoneNum.put(11, "北京");
        zoneNum.put(12, "天津");
        zoneNum.put(13, "河北");
        zoneNum.put(14, "山西");
        zoneNum.put(15, "内蒙古");
        zoneNum.put(21, "辽宁");
        zoneNum.put(22, "吉林");
        zoneNum.put(23, "黑龙江");
        zoneNum.put(31, "上海");
        zoneNum.put(32, "江苏");
        zoneNum.put(33, "浙江");
        zoneNum.put(34, "安徽");
        zoneNum.put(35, "福建");
        zoneNum.put(36, "江西");
        zoneNum.put(37, "山东");
        zoneNum.put(41, "河南");
        zoneNum.put(42, "湖北");
        zoneNum.put(43, "湖南");
        zoneNum.put(44, "广东");
        zoneNum.put(45, "广西");
        zoneNum.put(46, "海南");
        zoneNum.put(50, "重庆");
        zoneNum.put(51, "四川");
        zoneNum.put(52, "贵州");
        zoneNum.put(53, "云南");
        zoneNum.put(54, "西藏");
        zoneNum.put(61, "陕西");
        zoneNum.put(62, "甘肃");
        zoneNum.put(63, "青海");
        zoneNum.put(64, "宁夏");
        zoneNum.put(65, "新疆");
        zoneNum.put(71, "台湾");
        zoneNum.put(81, "香港");
        zoneNum.put(82, "澳门");
        zoneNum.put(91, "外国");
    }

    final static int[] PARITY = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
    final static int[] POWER_LIST = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10,
            5, 8, 4, 2};

    public static boolean isIdCard(String cardId) {

        final char[] ids = cardId.toUpperCase().toCharArray();

        int power = 0;
        // TODO: 校验位数
        for (int i = 0; i < ids.length; i++) {
            if (i == ids.length - 1 && ids[i] == 'X') {
                break;
            }
            if (ids[i] < '0' || ids[i] > '9') {
                return false;
            }
            if (i < ids.length - 1) {
                power += (ids[i] - '0') * POWER_LIST[i];
            }
        }

        // TODO: 校验区位码
        if (!zoneNum.containsKey(Integer.valueOf(cardId.substring(0, 2)))) {
            return false;
        }

        // TODO: 校验年份
        boolean isLeapYear = false;
        final int year = Integer.parseInt(cardId.substring(6, 10));
        if (year < 1900 || year > Calendar.getInstance().get(Calendar.YEAR)) {
            return false;
        }

        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            isLeapYear = true;
        }

        // TODO: 校验月份
        final int month = Integer.parseInt(cardId.substring(10, 12));
        if (month < 1 || month > 12) {
            return false;
        }

        // TODO: 检验天数
        final int day = Integer.parseInt(cardId.substring(12, 14));
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                if (day < 1 || day > 31) {
                    return false;
                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                if (day < 1 || day > 30){
                    return false;
                }
                break;
            case 2:
                if (isLeapYear) {
                    if (day < 1 || day > 29) {
                        return false;
                    }
                } else {
                    if (day < 1 || day > 28) {
                        return false;
                    }
                }
                break;
            default:
                break;
        }

        //  TODO: 校验 校验码(最后一位)
        return ids[ids.length - 1] == PARITY[power % 11];

    }

    public static void main(String[] args) {
        boolean i = isIdCard("110203201511301421");
        System.out.println(i);
    }

}
