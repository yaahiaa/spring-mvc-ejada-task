package ejada.task.dao;

import ejada.task.model.Department;
import ejada.task.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department,Long> {

}
