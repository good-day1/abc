package com.demo.service.impl;

import com.demo.entity.Record;
import com.demo.mapper.RecordMapper;
import com.demo.service.IRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 123
 * @since 2023-11-20
 */
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements IRecordService {

}
