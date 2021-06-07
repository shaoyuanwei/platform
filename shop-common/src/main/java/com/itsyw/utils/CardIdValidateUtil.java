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
    final static Map<Integer, String> zoneNum = new HashMap<Integer, String>();

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

    final static int[] PARITYBIT = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
    final static int[] POWER_LIST = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10,
            5, 8, 4, 2};

    public static boolean isIDCard(String cardId) {

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
        final int iyear = Integer.parseInt(cardId.substring(6, 10));
        if (iyear < 1900 || iyear > Calendar.getInstance().get(Calendar.YEAR)) {
            return false;
        }

        if (iyear % 4 == 0 && iyear % 100 != 0 || iyear % 400 == 0) {
            isLeapYear = true;
        }

        // TODO: 校验月份
        final int imonth = Integer.parseInt(cardId.substring(10, 12));
        if (imonth < 1 || imonth > 12) {
            return false;
        }

        // TODO: 检验天数
        final int iday = Integer.parseInt(cardId.substring(12, 14));
        switch (imonth) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                if (iday < 1 || iday > 31) {
                    return false;
                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                if (iday < 1 || iday > 31){
                    return false;
                }
                break;
            case 2:
                if (isLeapYear) {
                    if (iday < 1 || iday > 29) {
                        return false;
                    }
                    if (iday < 1 || iday > 28) {
                        return false;
                    }
                }
                break;
            default:
                break;
        }

        //  TODO: 校验 校验码(最后一位)
        return ids[ids.length - 1] == PARITYBIT[power % 11];

    }

    public static void main(String[] args) {
        boolean i = isIDCard("110203201511301421");
        System.out.println(i);
    }

}
