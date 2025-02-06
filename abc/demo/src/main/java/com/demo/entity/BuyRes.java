package com.demo.entity;

import lombok.Data;

@Data
public class BuyRes extends Buy{
    private String goodsname;
    private String storagename;
    private String goodstypename;
    private String username;

}
