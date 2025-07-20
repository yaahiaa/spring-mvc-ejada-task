package ejada.task.controller;

import ejada.task.dto.EmployeeRequest;
import ejada.task.dto.EmployeeResponse;
import ejada.task.exceptions.ResourceNotFoundException;
import ejada.task.model.Employee;
import ejada.task.service.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeResponse> addEmployee(@RequestBody EmployeeRequest employeeRequest) {
        EmployeeResponse employeeResponse=employeeService.addEmployee(employeeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeResponse);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> viewEmployees() {
        List<EmployeeResponse> list=employeeService.viewEmployees();
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editEmployee(@PathVariable Long id, @RequestBody EmployeeRequest employeeRequest) {
        try{
            EmployeeResponse employeeResponse=employeeService.editEmployee(id,employeeRequest);
            return ResponseEntity.ok(employeeResponse);
        }catch(ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("status",404,"error","Not Found","message",e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        try{
            employeeService.deleteEmployee(id);
            return ResponseEntity.ok(Map.of("message","Employee deleted successfully"));
        }
        catch(ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("status",404,"error","Not Found","message",e.getMessage()));
        }
    }

    @GetMapping("/filter/{departmentId}")
    public ResponseEntity<?> filterByDepartment(@PathVariable Long departmentId) {
        try{
            List<EmployeeResponse> list=employeeService.filterByDepartment(departmentId);
            return ResponseEntity.ok(list);
        }
        catch(ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("status",404,"error","Not Found","message",e.getMessage()));
        }
    }
}

