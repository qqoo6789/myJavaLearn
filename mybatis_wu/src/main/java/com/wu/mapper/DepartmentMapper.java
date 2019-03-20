package com.wu.mapper;

import com.wu.bean.Department;

public interface DepartmentMapper {
    public Department getDepartmentById(Integer did);
    public Department getDepartmentAndEmployeeById(Integer did);
}
