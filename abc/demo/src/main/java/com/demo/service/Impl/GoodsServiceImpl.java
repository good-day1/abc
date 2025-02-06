package com.demo.service.impl;

import com.demo.entity.Goods;
import com.demo.mapper.GoodsMapper;
import com.demo.service.IGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 123
 * @since 2023-11-17
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

}
