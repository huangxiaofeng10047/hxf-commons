package com.example.springbatchdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * All rights Reserved, Designed By xfhuang
 *
 * @ProjectName: spring-batch-demo
 * @Package: com.example.springbatchdemo
 * @ClassName: User
 * @Description: []
 * @Author: [xf huang]
 * @Date: 9/27/2021 11:32 AM
 * @Version: V1.0
 * @TODO: 注意, 本文件xf huang所作,如果转载或使用请标明具体出处!
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String name;

}
