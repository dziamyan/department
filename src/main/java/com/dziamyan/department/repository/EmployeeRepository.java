package com.dziamyan.department.repository;

import com.dziamyan.department.models.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
