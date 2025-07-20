package ejada.task.service.interfaces;

import ejada.task.dto.EmployeeRequest;
import ejada.task.dto.EmployeeResponse;
import ejada.task.model.Employee;

import java.util.List;

public interface EmployeeService {
    EmployeeResponse addEmployee(EmployeeRequest employeeRequest);
    List<EmployeeResponse> viewEmployees();
    EmployeeResponse editEmployee(Long id, EmployeeRequest updatedEmployee);
    void deleteEmployee(Long id);
    List<EmployeeResponse> filterByDepartment(Long departmentId);
}
