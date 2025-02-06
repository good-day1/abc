package com.demo.controller;


import com.demo.common.Result;
import com.demo.entity.BuyRes;
import com.demo.entity.Goods;
import com.demo.entity.RecordRes;
import com.demo.entity.User;
import com.demo.mapper.BuyMapper;
import com.demo.service.IBuyService;
import com.demo.service.IGoodsService;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 123
 * @since 2023-11-28
 */
@RestController
@RequestMapping("/buy")
public class BuyController {
    @Autowired
    IBuyService buyService;
    @Autowired
    BuyMapper buyMapper;
    @Autowired
    IGoodsService goodsService;
    @Autowired
    UserService userService;
    @GetMapping("/list")
    public Map<String, Object> search(@RequestParam Integer pageNum,
                                      @RequestParam Integer pageSize,
                                      @RequestParam String name, @RequestParam String goodstype, @RequestParam String storage1,
                                       @RequestParam String userId1, @RequestParam String roleId
    ){
        pageNum = (pageNum - 1) * pageSize;
        name = "%" + name + "%";
        List<RecordRes> data=null;
        Integer total=null;
        System.out.println(userId1);
        System.out.println("roleid="+roleId);
        if("2".equals(roleId)){
            data = buyMapper.selectPage1(pageNum, pageSize, name,goodstype,storage1,userId1);
            total = buyMapper.selectTotal1(name,goodstype,storage1,userId1);
        }
        else{
            data = buyMapper.selectPage(pageNum, pageSize, name,goodstype,storage1,userId1);
            total = buyMapper.selectTotal(name,goodstype,storage1);
        }
        Map<String, Object> res = new HashMap<>();

        res.put("data", data);
        res.put("total", total);
        return res;

    }
    @PostMapping("/save")
    public Result save(@RequestBody BuyRes buyRes){
        Goods goods=goodsService.getById(buyRes.getGoods());
        int n=buyRes.getCount();
        int num=goods.getCount()-n;
        goods.setCount(num);
        int p=n*goods.getPrice();//购买物品的总价钱
        System.out.println("yonhuid="+buyRes.getUserid());
        User user=userService.getById(buyRes.getUserid());
        int p1=user.getMoney();
        System.out.println("yonhudeqian="+p1);
        if(p1>=p){
            p1=p1-p;
        }
        user.setMoney(p1);
        userService.updateById(user);
        goodsService.updateById(goods);
        return buyService.save(buyRes)?Result.suc():Result.fail();
    }

}
