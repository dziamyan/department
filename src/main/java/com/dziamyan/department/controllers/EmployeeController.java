package com.dziamyan.department.controllers;

import com.dziamyan.department.models.Department;
import com.dziamyan.department.models.Employee;
import com.dziamyan.department.repository.DepartmentRepository;
import com.dziamyan.department.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @GetMapping("/employee")
    public String employeeMain(Model model){
        Iterable<Employee> employees = employeeRepository.findAll();
        model.addAttribute("title", "Блог");
        model.addAttribute("employees", employees);
        return "employee-main";
    }

    @GetMapping("/employee/add")
    public String employeeAdd(Model model){
        Iterable<Department> departments = departmentRepository.findAll();
        model.addAttribute("departments", departments);
        return  "employee-add";
    }

    @PostMapping("/employee/add")
    public String employeeAddPost(@RequestParam String name, @RequestParam String departmentName, @RequestParam String dateOfBirth,
                              @RequestParam String salary, Model model){
        Department department = departmentRepository.findByDepartmentName(departmentName);
        Employee employee = new Employee(name, dateOfBirth, salary, department);
        employeeRepository.save(employee);
        return "redirect:/employee";
    }

    @GetMapping("/employee/{id}/edit")
    public String editEmployee(@PathVariable(value = "id") long id, Model model){

        Iterable<Department> departments = departmentRepository.findAll();
        Optional<Employee> employee = employeeRepository.findById(id);
        ArrayList<Employee> list = new ArrayList<>();
        employee.ifPresent(list::add);
        model.addAttribute("employee", list);
        model.addAttribute("departments", departments);
        return "/employee-edit";
    }

    @PostMapping("/employee/{id}/edit")
    public String updateEmployeePost(@PathVariable(value = "id") long id, @RequestParam String name, @RequestParam String departmentName, @RequestParam String dateOfBirth,
                               @RequestParam String salary, Model model){

        Employee employee = employeeRepository.findById(id).orElseThrow();
        Department department = departmentRepository.findByDepartmentName(departmentName);
        employee.setName(name);
        employee.setDateOfBirth(dateOfBirth);
        employee.setSalary(salary);
        employee.setDepartment(department);
        employeeRepository.save(employee);
        return "redirect:/employee";
    }

    @PostMapping("/employee/{id}/delete")
    public String deleteEmployee(@PathVariable(value="id") long id, Model model){
        Employee employee = employeeRepository.findById(id).orElseThrow();
        employeeRepository.delete(employee);
        return "redirect:/employee";
    }
}
