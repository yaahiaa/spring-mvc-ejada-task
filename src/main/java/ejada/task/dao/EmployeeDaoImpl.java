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
public class EmployeeDaoImpl implements EmployeeDao{
    @Autowired
    private EntityManager entityManager;
    @Override
    public void save(Employee employee) {
        Session currentSession= entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(employee);
    }

    @Override
    public List<Employee> get() {
        Session currentSession= entityManager.unwrap(Session.class);
        Query<Employee> query= currentSession.createQuery("from Employee",Employee.class);
        List<Employee> list=query.getResultList();
        return list;
    }

    @Override
    public void delete(Long id) {
        Session currentSession= entityManager.unwrap(Session.class);
        Employee employee = currentSession.get(Employee.class, id);
        if (employee != null) {
            currentSession.delete(employee);
        }
    }

    @Override
    public List<Employee> filter(Long departmentId) {
        Session currentSession= entityManager.unwrap(Session.class);
        Query<Employee> query = currentSession.createQuery(
                "SELECT e FROM Employee e WHERE e.department.id = :deptId", Employee.class);
        query.setParameter("deptId", departmentId);
        List<Employee> employees = query.getResultList();
        return employees;
    }
}
