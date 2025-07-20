package ejada.task.dao;

import ejada.task.model.Department;
import ejada.task.model.Employee;

import java.util.List;

public interface DepartmentDao {
    void save(Department department);
    List<Department> get();
    List<Employee> getEmployees(Long departmentId);
}
