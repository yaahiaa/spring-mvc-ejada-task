package ejada.task.service;

import ejada.task.dao.DepartmentDao;
import ejada.task.model.Department;
import ejada.task.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService{
    @Autowired
    private DepartmentDao departmentDao;
    @Override
    @Transactional
    public void save(Department department) {

        departmentDao.save(department);
    }

    @Override
    @Transactional
    public List<Department> get() {

        return departmentDao.get();
    }

    @Override
    @Transactional
    public List<Employee> getEmployees(Long departmentId) {

        return departmentDao.getEmployees(departmentId);
    }
}
