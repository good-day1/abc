package com.demo.controller;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.common.QueryPageParam;
import com.demo.common.Result;
import com.demo.entity.Menu;
import com.demo.entity.User;
import com.demo.mapper.UserMapper;
import com.demo.service.MenuService;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/user")

public class UserController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuService menuService;
    @Autowired
    private UserService userService;

    @GetMapping("/tmp")
    public List<User>tmp(){
        return userService.list();
    }
    //增
    @PostMapping("/save")
    public Result save(@RequestBody User user){

        return userService.save(user)?Result.suc():Result.fail();
    }
    @GetMapping("/findByNo")
    public Result findByNo(@RequestParam String no){
        List list=  userService.lambdaQuery().eq(User::getNo,no).list();
        System.out.println(list);
        System.out.println(no);
        return list.size()>0?Result.suc(list):Result.fail();
    }
    //改
    @PostMapping("/mod")
    public boolean mod(@RequestBody User user){
        return userService.updateById(user);
    }
    @PostMapping("/update")
    public Result update(@RequestBody User user){
        return userService.updateById(user)?Result.suc():Result.fail();
    }
    //增或者改
    @PostMapping("/saveormod")
    public boolean saveormod(@RequestBody User user){
        return userService.saveOrUpdate(user);
    }
    //删
    @GetMapping("/deletebyid")
    public Result delete(int id){
        return userService.removeById(id)?Result.suc():Result.fail();
    }
    @PostMapping("/login")
    public Result login(@RequestParam String no,@RequestParam String password){
        LambdaQueryWrapper<User>lambdaQueryWrapper=new LambdaQueryWrapper<User>();
        lambdaQueryWrapper.eq(User::getNo,no).eq(User::getPassword,password);
        List<User> list=userService.list(lambdaQueryWrapper);
        System.out.println(list);

        if(list.size()>0){
            User user1=(User) list.get(0);
            List menuList=menuService.lambdaQuery().like(Menu::getMenuright,user1.getRoleId()).list();
            HashMap res=new HashMap();
            res.put("user",user1);
            res.put("menu",menuList);
            return Result.suc(res);

        }
        return Result.fail();
    }
    //查
    @GetMapping("/listp")
    public Map<String, Object>  search(@RequestParam Integer pageNum,
                         @RequestParam Integer pageSize,
                         @RequestParam String name,
                                      @RequestParam Integer roleId
                           ){
        LambdaQueryWrapper<User>lambdaQueryWrapper=new LambdaQueryWrapper();
        pageNum = (pageNum - 1) * pageSize;
        name = "%" + name + "%";

        List<User> data = userMapper.selectPage(pageNum, pageSize, name,roleId);
        Integer total = userMapper.selectTotal(name,roleId);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
//        if(user.getName()!=null){
//            lambdaQueryWrapper.like(User::getName,user.getName());
//        }
//        System.out.println(Result.suc(userService.list(lambdaQueryWrapper)));
//        return Result.suc(userService.list(lambdaQueryWrapper));

    }
    @PostMapping ("/listp1")
    public Result listpage(@RequestBody QueryPageParam query){
        HashMap param=query.getParma();
        String name=(String) param.get("name");
        String sex=(String) param.get("sex");
        String roleId= (String) param.get("roleID");
        Page<User> page1=new Page();
        page1.setCurrent(query.getPageNum());
        page1.setSize(query.getPageSize());
        System.out.println(query.getPageSize());
        System.out.println(query.getParma());
        System.out.println(page1.getSize());
        System.out.println(name);
        LambdaQueryWrapper<User>lambdaQueryWrapper=new LambdaQueryWrapper<User>();
        if(!"null".equals(name)&& StringUtils.isNotBlank(name)){
            lambdaQueryWrapper.like(User::getName,name);
        }
        if(!"null".equals(sex)&& StringUtils.isNotBlank(sex)){
            lambdaQueryWrapper.eq(User::getSex,sex);
        }
        if( StringUtils.isNotBlank(roleId)){
            lambdaQueryWrapper.eq(User::getRoleId,roleId);
        }
        IPage data=  userService.page(page1,lambdaQueryWrapper);
        System.out.println(data.getTotal());

        return Result.suc(data.getRecords(), (long) data.getRecords().size());

    }

}
