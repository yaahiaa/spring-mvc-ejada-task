package ejada.task.dao;

import ejada.task.model.Employee;

import java.util.List;

public interface EmployeeDao {
    void save(Employee employee);
    List<Employee> get();
    void delete(Long id);
    List<Employee> filter(Long departmentId);
}
