package ejada.task.service.interfaces;

import ejada.task.dto.DepartmentRequest;
import ejada.task.dto.DepartmentResponse;
import ejada.task.dto.EmployeeResponse;
import ejada.task.model.Department;
import ejada.task.model.Employee;

import java.util.List;

public interface DepartmentService {
    DepartmentResponse createDepartment(DepartmentRequest departmentRequest);
    List<DepartmentResponse> viewDepartments();
    List<EmployeeResponse> getDepartmentEmployees(Long departmentId);
}

