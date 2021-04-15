package com.javakc.pms.dispord.service;

import com.javakc.commonutils.jpa.base.service.BaseService;
import com.javakc.commonutils.jpa.dynamic.SimpleSpecification;
import com.javakc.commonutils.jpa.dynamic.SimpleSpecificationBuilder;
import com.javakc.pms.dispord.dao.DispOrdDao;
import com.javakc.pms.dispord.entity.DispOrd;
import com.javakc.pms.dispord.vo.DispOrdQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class DispOrdService extends BaseService<DispOrdDao,DispOrd> {

//    @Autowired
//    private DispOrdDao dispOrdDao;

    /**
     * 查询所有调度指令库
     * @return
     */
    public List<DispOrd> findAll() {
        return dao.findAll();
    }

    /*
    * 带条件的分页查询
    * */
    public Page<DispOrd> findPage(DispOrdQuery dispOrdQuery, int pageNo, int pageSize){

        // 设置查询条件
        SimpleSpecificationBuilder simpleSpecificationBuilder = new SimpleSpecificationBuilder();
        // 验证dispOrdQuery。name是否有值
        if (!StringUtils.isEmpty(dispOrdQuery.getName())){
            simpleSpecificationBuilder.and("name","=",dispOrdQuery.getName());
        }
        if (!StringUtils.isEmpty(dispOrdQuery.getBeginDate())){
            simpleSpecificationBuilder.and("gmtCreate","gt",dispOrdQuery.getBeginDate());
        }
        if (!StringUtils.isEmpty(dispOrdQuery.getEndDate())){
            simpleSpecificationBuilder.and("gmtCreate","lt",dispOrdQuery.getEndDate());
        }

        // 调用dao查询方法
        return dao.findAll(simpleSpecificationBuilder.getSpecification(), PageRequest.of(pageNo-1,pageSize));
    }

}