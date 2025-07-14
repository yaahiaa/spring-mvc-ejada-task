package ejada.task.dao;

import ejada.task.model.Department;
import ejada.task.model.Employee;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentDaoImpl implements DepartmentDao{
    @Autowired
    private EntityManager entityManager;
    @Override
    public void save(Department department) {
        Session currentSession= entityManager.unwrap(Session.class);
        currentSession.save(department);
    }

    @Override
    public List<Department> get() {
        Session currentSession= entityManager.unwrap(Session.class);
        Query<Department> query= currentSession.createQuery("from Department",Department.class);
        List<Department> list=query.getResultList();
        return list;
    }

    @Override
    public List<Employee> getEmployees(Long departmentId) {
        Session currentSession= entityManager.unwrap(Session.class);
        Query<Employee> query = currentSession.createQuery(
                "SELECT e FROM Employee e WHERE e.department.id = :deptId", Employee.class);
        query.setParameter("deptId", departmentId);
        List<Employee> employees = query.getResultList();
        return employees;
    }
}
