package com.wu.bean;

import java.util.List;

public class Department {
    private Integer id;
    private String name;

    private List<Employee> emps;
    public Department() {
    }

    public List<Employee> getEmps() {
        return emps;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", emps=" + emps +
                '}';
    }

    public void setEmps(List<Employee> emps) {
        this.emps = emps;
    }

    public Department(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
