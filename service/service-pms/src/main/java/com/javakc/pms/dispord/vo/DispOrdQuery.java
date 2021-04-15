package com.javakc.pms.dispord.vo;

import lombok.Data;

/*
* 条件查询封装类
* */
@Data
public class DispOrdQuery {

    private String name;

    private String beginDate;

    private String endDate;
}
