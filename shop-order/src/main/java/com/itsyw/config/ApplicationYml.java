package com.itsyw.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/1/4 16:01
 * @Version: 1.0
 * TODO:
 */
@Component("applicationYml")
@PropertySource({"application.yml"})
@Data
public class ApplicationYml {

    @Value("${spring.application.name}")
    public String applicationName;

}
