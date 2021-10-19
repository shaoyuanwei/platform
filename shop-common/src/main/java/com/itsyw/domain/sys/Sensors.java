package com.itsyw.domain.sys;

import lombok.Data;

import java.util.Arrays;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/10/11 13:12
 * @Version: 1.0
 * TODO:
 */
@Data
public class Sensors {

//     System.out.format(" CPU Temperature: %.1f°C%n", sensors.getCpuTemperature());
//        System.out.println(" Fan Speeds: " + Arrays.toString(sensors.getFanSpeeds()));
//        System.out.format(" CPU Voltage: %.1fV%n", sensors.getCpuVoltage());

    private Double temperature;

    private String fanSpeeds;

    private Double voltage;

}
