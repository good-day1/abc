package com.demo.controller;


import com.demo.common.Result;
import com.demo.entity.Goods;
import com.demo.entity.Record;
import com.demo.entity.RecordRes;
import com.demo.mapper.RecordMapper;
import com.demo.service.IGoodsService;
import com.demo.service.IRecordService;
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
 * @since 2023-11-20
 */
@RestController
@RequestMapping("/record")
public class RecordController {

    @Autowired
    IRecordService recordService;
    @Autowired
    RecordMapper recordMapper;
    @Autowired
    IGoodsService goodsService;
    @GetMapping("/listp")
    public Map<String, Object> search(@RequestParam Integer pageNum,
                                      @RequestParam Integer pageSize,
                                      @RequestParam String name, @RequestParam String goodstype, @RequestParam String storage1,
                                      @RequestParam String roleId,@RequestParam String userId
    ){
        pageNum = (pageNum - 1) * pageSize;
        name = "%" + name + "%";
        List<RecordRes> data=null;
        Integer total=null;
        if("2".equals(roleId)){
             data = recordMapper.selectPage1(pageNum, pageSize, name,goodstype,storage1,userId,roleId);
             total = recordMapper.selectTotal1(name,goodstype,storage1,userId);
        }
        else{
             data = recordMapper.selectPage(pageNum, pageSize, name,goodstype,storage1);
             total = recordMapper.selectTotal(name,goodstype,storage1);
        }
        Map<String, Object> res = new HashMap<>();
        System.out.println(storage1);
        res.put("data", data);
        res.put("total", total);
        return res;

    }
    @PostMapping("/save")
    public Result save(@RequestBody Record record){
        Goods goods=goodsService.getById(record.getGoods());
        int n=record.getCount();
        if("2".equals(record.getAction())){
            n=-n;
            record.setCount(n);
        }
        int num=goods.getCount()+n;
        goods.setCount(num);
        goodsService.updateById(goods);
        return recordService.save(record)?Result.suc():Result.fail();
    }

}
