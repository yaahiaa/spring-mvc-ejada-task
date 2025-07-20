package ejada.task.service;

import ejada.task.dao.DepartmentDao;
import ejada.task.dao.EmployeeDao;
import ejada.task.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeDao employeeDao;

    @Transactional
    @Override
    public void save(Employee employee) {

        employeeDao.save(employee);
    }

    @Transactional
    @Override
    public List<Employee> get() {

        return employeeDao.get();
    }

    @Transactional
    @Override
    public void delete(Long id) {

        employeeDao.delete(id);
    }

    @Transactional
    @Override
    public List<Employee> filter(Long departmentId) {

        return employeeDao.filter(departmentId);
    }
}
