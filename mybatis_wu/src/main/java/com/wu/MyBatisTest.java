package com.wu;

import com.wu.bean.Department;
import com.wu.bean.Employee;
import com.wu.mapper.DepartmentMapper;
import com.wu.mapper.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class MyBatisTest {
    @Test
    public void test() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            Employee employee = (Employee) session.selectOne("com.wu.mapper.EmployeeMapper.selectEmp", 1);
            System.out.println(employee);
        } finally {
            session.close();
        }
    }

    @Test
    public void test1() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        try {
            Employee emp = employeeMapper.getEmpById(2);
            System.out.println(emp);
        }finally {
            sqlSession.close();
        }
    }
    public static SqlSessionFactory getSqlsessionFactory() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }

    @Test
    public void test3_testList() throws IOException {
        SqlSessionFactory sqlsessionFactory = MyBatisTest.getSqlsessionFactory();
        SqlSession sqlSession = sqlsessionFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        List<Employee> emps = mapper.getEmpsByName("%wu%");
        System.out.println(emps);
        //[Employee{id=1, lastName='null', email='dddd@123.com', gender='1'},
        // Employee{id=3, lastName='null', email='xxx@xxx.com', gender='1'},
        // Employee{id=4, lastName='null', email='yyy@xxx.com', gender='1'}]
    }
    @Test
    public void test4_testObj() throws IOException {
        SqlSessionFactory sqlsessionFactory = MyBatisTest.getSqlsessionFactory();
        SqlSession sqlSession = sqlsessionFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        /*
        Employee emp = mapper.getEmpByName("wuyiming");
        System.out.println(emp);
        */
        //Employee{id=1, lastName='null', email='dddd@123.com', gender='1'}

        /*
        Map<String, Object> map = mapper.getEmpMapById(1);
        System.out.println(map);
        */
        //{gender=1, last_name=wuyiming, id=1, email=dddd@123.com}
        //输出一个Map,key位列名，value为值

       /* Map<Integer, Employee> mapByName = mapper.getEmpsMapByName("%wu%");
        System.out.println(mapByName);*/
        //{1=Employee{id=1, lastName='null', email='dddd@123.com', gender='1'},
        // 3=Employee{id=3, lastName='null', email='xxx@xxx.com', gender='1'},
        // 4=Employee{id=4, lastName='null', email='yyy@xxx.com', gender='1'}}
        // 以id为key，以employee对象为值

        /*
        Map<String, Employee> mapByName = mapper.getEmpsMapByName1("%wu%");
        System.out.println(mapByName);
        */
        //{wuyiming2=Employee{id=4, lastName='wuyiming2', email='yyy@xxx.com', gender='1'},
        // wuyiming1=Employee{id=3, lastName='wuyiming1', email='xxx@xxx.com', gender='1'},
        // wuyiming=Employee{id=1, lastName='wuyiming', email='dddd@123.com', gender='1'}}


        //Employee employee = mapper.getEmployeeByIdWhitLeftJoinDepartment(1);
        //System.out.println(employee);
        //Employee{id=1, lastName='wuyiming', email='dddd@123.com', gender='1', department=Department{id=1, name='开发部'}}

        //Employee employee = mapper.getEmployeeByIdWhitLeftJoinDepartmentAssociation(1);
        //System.out.println(employee);
        //Employee{id=1, lastName='wuyiming', email='dddd@123.com', gender='1', department=Department{id=1, name='开发部'}}

        DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);

        //Department department = departmentMapper.getDepartmentById(1);
        //System.out.println(department);
        //Department{id=1, name='开发部'}

//        Employee employee = mapper.getEmployeeByIdWhitLeftJoinDepartmentAssocaitionSelect(1);
//        System.out.println(employee.getLastName());
//        System.out.println(employee.getDepartment());
        //Employee{id=1, lastName='wuyiming', email='dddd@123.com', gender='1', department=Department{id=1, name='开发部'}}

        //test git

//        Department department = departmentMapper.getDepartmentAndEmployeeById(1);
//        System.out.println(department);
//        Department{id=1, name='开发部',
//        emps=[
//              Employee{id=1, lastName='wuyiming', email='dddd@123.com', gender='null', department=null},
//              Employee{id=2, lastName='gaozaoshun', email='gao@11.com', gender='null', department=null},
//              Employee{id=5, lastName='wuyiming3', email='test@dxx.com', gender='null', department=null}
//        ]}


        Department department = departmentMapper.getDepartmentAndEmployeeByIdLazy(1);
        System.out.println(department);
        //Department{id=1, name='开发部',
        // emps=[
        //  Employee{id=1, lastName='wuyiming', email='dddd@123.com', gender='1', department=null},
        //  Employee{id=2, lastName='gaozaoshun', email='gao@11.com', gender='0', department=null},
        //  Employee{id=5, lastName='wuyiming3', email='test@dxx.com', gender='1', department=null}
        //  ]}


    }
}
