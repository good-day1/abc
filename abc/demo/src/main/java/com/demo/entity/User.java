package com.demo.entity;

import lombok.Data;

@Data
public class User {
    private int id;
    private String no;
    private String name;
    private String password;
    private int age;
    private int sex;
    private String phone;
    private int roleId;
    private int isvalid;
    private int money;
}
