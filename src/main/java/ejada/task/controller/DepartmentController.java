package ejada.task.controller;

import ejada.task.dto.DepartmentRequest;
import ejada.task.dto.DepartmentResponse;
import ejada.task.dto.EmployeeResponse;
import ejada.task.exceptions.ResourceNotFoundException;
import ejada.task.model.Department;
import ejada.task.model.Employee;
import ejada.task.service.interfaces.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;
    @PostMapping
    public ResponseEntity<DepartmentResponse> createDepartment(@RequestBody DepartmentRequest departmentRequest) {
        DepartmentResponse departmentResponse=departmentService.createDepartment(departmentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentResponse);
    }
    @GetMapping
    public ResponseEntity<List<DepartmentResponse>> viewDepartments() {

        List<DepartmentResponse> list=departmentService.viewDepartments();
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}/employees")
    public ResponseEntity<?> getDepartmentEmployees(@PathVariable Long id) {
        try{
        List<EmployeeResponse> list=departmentService.getDepartmentEmployees(id);
        return ResponseEntity.ok(list);
        }
        catch(ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("status",404,"error","Not Found","message",e.getMessage()));
        }
    }
}
