package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;
import org.apache.ibatis.annotations.Select;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    //新增員工
    public void save(EmployeeDTO employeeDTO);

    //員工分頁查詢
    public PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    //啟用禁用員工帳號
    public void startOrStop(Integer status, Long id);

    //根據id查詢員工信息
    public Employee getById(Long id);

    //編輯員工信息
    public void update(EmployeeDTO employeeDTO);
}
