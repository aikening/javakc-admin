package com.javakc.servicebase.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HctfException extends RuntimeException{


        /*
        * 状态码
        * */
        private Integer code;

        /*
        * 信息
        * */
        private String msg;

}
