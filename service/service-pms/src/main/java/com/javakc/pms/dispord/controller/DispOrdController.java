package com.javakc.pms.dispord.controller;


import com.javakc.commonutils.api.APICODE;
import com.javakc.pms.dispord.entity.DispOrd;
import com.javakc.pms.dispord.service.DispOrdService;
import com.javakc.pms.dispord.vo.DispOrdQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "调度指令库管理")
@RestController
@RequestMapping("/pms/dispord")
@CrossOrigin
public class DispOrdController {

    @Autowired
    private DispOrdService dispOrdService;


    @ApiOperation(value = "查询所有指令库")
    @GetMapping
    public APICODE findAll() {
        List<DispOrd> dispOrdList = dispOrdService.findAll();
        return APICODE.OK().data("items", dispOrdList);
    }

    @ApiOperation("带条件的分页查询")
    @PostMapping("{pageNo}/{pageSize}")
    public APICODE findPage(@RequestBody(required = false) DispOrdQuery dispOrdQuery ,
                            @PathVariable("pageNo") @ApiParam(name = "pageNo",value = "页码",required = true) int pageNo,
                            @ApiParam(name = "pageSize",value = "每页最大条数",required = true) @PathVariable("pageSize") int pageSize ){
        Page<DispOrd> page = dispOrdService.findPage(dispOrdQuery,pageNo,pageSize);
        // 总条数
        long totalElements = page.getTotalElements();
        // 数据
        List<DispOrd> list = page.getContent();
        return APICODE.OK().data("total",totalElements).data("items",list);
    }

    @ApiOperation(value = "新增调度指令库")
    @PostMapping("saveDispOrd")
    public APICODE saveDispOrd(@RequestBody DispOrd dispOrd) {
        dispOrdService.saveOrUpdate(dispOrd);
        return APICODE.OK();
    }

    @ApiOperation("调度指令-修改")
    @PutMapping
    public APICODE update(@RequestBody DispOrd dispOrd){
            /*DispOrd dispOrd1 = dispOrdService.getById(dispOrd.getId());
            dispOrd1.setName(dispOrd.getName());
            dispOrd1.setDescribeC(dispOrd.getDescribeC());*/
            dispOrdService.saveOrUpdate(dispOrd);
            return APICODE.OK();
    }

    @ApiOperation("调度指令库-根据id单条数据获取")
    @GetMapping("{dispOrdId}")
    public APICODE view(@PathVariable("dispOrdId") String dispOrdId){
        DispOrd dispOrd = dispOrdService.getById(dispOrdId);
        return APICODE.OK().data("dispOrd",dispOrd);
    }

    @ApiOperation("调度指令库-根据id删除")
    @DeleteMapping("{dispOrdId}")
    public APICODE delete(@PathVariable("dispOrdId") String dispOrdId){
            dispOrdService.removeById(dispOrdId);
            return APICODE.OK();
    }
}