package com.atguigu.mybatis.dao;

import com.atguigu.mybatis.bean.Employee;

/**
 * @author chenxin
 * @create 2021-09-09 15:08
 */
public interface EmployeeMapper {
    public Employee getEmpById(Integer id);
}
