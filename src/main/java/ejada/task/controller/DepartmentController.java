package ejada.task.controller;

import ejada.task.model.Department;
import ejada.task.model.Employee;
import ejada.task.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;
    @PostMapping
    public void addDepartment(@RequestBody Department department) {

        departmentService.save(department);
    }
    @GetMapping
    public List<Department> getAllDepartments() {

        return departmentService.get();
    }
    @GetMapping("/{id}/employees")
    public List<Employee> getEmployeesByDepartment(@PathVariable Long id) {

        return departmentService.getEmployees(id);
    }
}
