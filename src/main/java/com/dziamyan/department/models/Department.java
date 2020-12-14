package com.dziamyan.department.models;

import javax.persistence.*;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long department_id;

    @Column(name = "department_name",unique = true)
    private String departmentName;

    public Department(){}

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    public Long getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Long department_id) {
        this.department_id = department_id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
