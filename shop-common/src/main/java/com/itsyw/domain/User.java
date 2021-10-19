package com.itsyw.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: YuanWei Shao
 * @Date: 2020/11/2 10:41
 * @Version: 1.0
 * TODO:
 */
@Data// 不再写set和get方法
@Entity(name = "shop_user")// 实体类与数据表的对应
public class User {

    @Id // 声明主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 数据库自增
    private Integer uid;

    private String username;

    private String password;

    private String telephone;

    private String role;

    private Integer state;

    private Integer sex;

    private Date createTime;

    private Date birth;

    private String sign;

    private String email;

    private String avachImg;

    private String qq;
}
