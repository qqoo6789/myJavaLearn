package com.wu.mapper;

import com.wu.bean.Department;
import com.wu.bean.Employee;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {
    public Employee getEmpById(Integer id);
    public List<Employee> getEmpsByName(String name);
    public Employee getEmpByName(String name);
    public List<Employee> getEmpsByDeptId(Integer did);
    public Map<String,Object> getEmpMapById(Integer id);
    @MapKey("id")
    public Map<Integer,Employee> getEmpsMapByName(String name);

    @MapKey("lastName")//这里填的是po里面的属性，而不是jdbc的属性名
    public Map<String,Employee> getEmpsMapByName1(String name);

    public Employee getEmployeeByIdWhitLeftJoinDepartment(Integer eid);
    public Employee getEmployeeByIdWhitLeftJoinDepartmentAssociation(Integer eid);
    public Employee getEmployeeByIdWhitLeftJoinDepartmentAssocaitionSelect(Integer eid);



}
