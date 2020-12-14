package com.dziamyan.department.controllers;

import com.dziamyan.department.models.Department;
import com.dziamyan.department.repository.DepartmentRepository;
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
public class DepartmentController {

    @Autowired
    DepartmentRepository departmentRepository;

    @GetMapping("/departments")
    public String departmentMain(Model model){
        Iterable<Department> departments = departmentRepository.findAll();
        model.addAttribute("departments", departments);
        return "/departments-main";
    }

    @GetMapping("/departments/add")
    public String addDepartment(Model model){
        return "/department-add";
    }

    @PostMapping("/departments/add")
    public String addDepartmentPost(@RequestParam String name, Model model){
        Department department = new Department(name);
        departmentRepository.save(department);
        return "redirect:/departments";
    }

    @GetMapping("/departments/{id}/edit")
    public String editDepartment(@PathVariable(value = "id") Long id, Model model){
        Optional<Department> department = departmentRepository.findById(id);
        ArrayList<Department> list = new ArrayList<>();
        department.ifPresent(list::add);
        model.addAttribute("department", list);
        return "/department-edit";
    }

    @PostMapping("/departments/{id}/edit")
    public String editDepartmentPost(@PathVariable(value = "id") Long id ,@RequestParam String name, Model model){
        Department department = departmentRepository.findById(id).orElseThrow();
        department.setDepartmentName(name);
        departmentRepository.save(department);
        return "redirect:/departments";
    }

    @PostMapping("/departments/{id}/delete")
    public String deleteDepartment(@PathVariable(value = "id") Long id, Model model){
        Department department = departmentRepository.findById(id).orElseThrow();
        departmentRepository.delete(department);
        return "redirect:/departments";
    }

}
