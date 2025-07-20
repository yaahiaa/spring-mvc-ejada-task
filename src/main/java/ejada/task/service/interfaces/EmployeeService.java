package ejada.task.service;

import ejada.task.model.Employee;

import java.util.List;

public interface EmployeeService {
    void save(Employee employee);
    List<Employee> get();
    void delete(Long id);
    List<Employee> filter(Long departmentId);
}
