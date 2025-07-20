package ejada.task.service.implementations;

import ejada.task.dao.DepartmentRepository;
import ejada.task.dao.EmployeeRepository;
import ejada.task.dto.DepartmentRequest;
import ejada.task.dto.DepartmentResponse;
import ejada.task.dto.EmployeeResponse;
import ejada.task.exceptions.ResourceNotFoundException;
import ejada.task.model.Department;
import ejada.task.model.Employee;
import ejada.task.service.interfaces.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public DepartmentResponse createDepartment(DepartmentRequest departmentRequest) {
        Department dept = new Department();
        dept.setName(departmentRequest.getName());
        dept.setLocation(departmentRequest.getLocation());

        Department saved = departmentRepository.save(dept);

        return new DepartmentResponse(saved.getId(), saved.getName(), saved.getLocation());
    }

    @Override
    public List<DepartmentResponse> viewDepartments() {
        List<Department> depts = departmentRepository.findAll();
        List<DepartmentResponse> list=new ArrayList<>();
        for(Department dept:depts){
            DepartmentResponse departmentResponse=new DepartmentResponse(dept.getId(),dept.getName(),dept.getLocation());
            list.add(departmentResponse);
        }
        return list;
    }

    @Override
    public List<EmployeeResponse> getDepartmentEmployees(Long departmentId) {
        Department dept = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        List<Employee> emps = dept.getEmployees();
        List<EmployeeResponse> list=new ArrayList<>();
        for(Employee e:emps){
            EmployeeResponse employeeResponse=new EmployeeResponse(e.getId(),e.getName(),e.getEmail(),e.getJobTitle(),e.getSalary(),e.getDepartment());
            list.add(employeeResponse);
        }
        return list;
    }
}

