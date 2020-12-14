package com.dziamyan.department.repository;

import com.dziamyan.department.models.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
    Department findByDepartmentName(String name);
}
