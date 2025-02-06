package com.demo.common;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Data
@Component
public class QueryPageParam {
    private static int PAGE_SIZE=20;
    private static int PAGE_NUM=1;
    private int PageSize=PAGE_SIZE;
    private int PageNum=PAGE_NUM;
    private HashMap parma=new HashMap();


}
