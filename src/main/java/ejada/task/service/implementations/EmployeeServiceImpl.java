package ejada.task.service.implementations;

import ejada.task.dao.DepartmentRepository;
import ejada.task.dao.EmployeeRepository;
import ejada.task.dto.EmployeeRequest;
import ejada.task.dto.EmployeeResponse;
import ejada.task.exceptions.ResourceNotFoundException;
import ejada.task.model.Employee;
import ejada.task.service.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public EmployeeResponse addEmployee(EmployeeRequest employeeRequest) {
        Employee employee=new Employee();
        employee.setName(employeeRequest.getName());
        employee.setSalary(employeeRequest.getSalary());
        employee.setEmail(employeeRequest.getEmail());
        employee.setJobTitle(employeeRequest.getJobTitle());
        employee.setDepartment(employeeRequest.getDepartment());
        Employee saved=employeeRepository.save(employee);
        return new EmployeeResponse(saved.getId(), saved.getName(), saved.getEmail(), saved.getJobTitle(),saved.getSalary(),saved.getDepartment());
    }

    @Override
    public List<EmployeeResponse> viewEmployees() {
        List<Employee> employees=employeeRepository.findAll();
        List<EmployeeResponse> employeeResponses=new ArrayList<>();
        for(Employee e:employees){
            EmployeeResponse er=new EmployeeResponse(e.getId(),e.getName(),e.getEmail(),e.getJobTitle(),e.getSalary(),e.getDepartment());
            employeeResponses.add(er);
        }
        return employeeResponses;
    }

    @Override
    public EmployeeResponse editEmployee(Long id, EmployeeRequest updatedEmployee) {
        Employee employee=employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not found"));
        employee.setName(updatedEmployee.getName());
        employee.setEmail(updatedEmployee.getEmail());
        employee.setSalary(updatedEmployee.getSalary());
        employee.setJobTitle(updatedEmployee.getJobTitle());
        employee.setDepartment(updatedEmployee.getDepartment());
        Employee saved=employeeRepository.save(employee);
        return new EmployeeResponse(saved.getId(), saved.getName(), saved.getEmail(), saved.getJobTitle(), saved.getSalary(),saved.getDepartment());
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee=employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not found"));
        employeeRepository.delete(employee);
    }

    @Override
    public List<EmployeeResponse> filterByDepartment(Long departmentId) {
        if (!departmentRepository.existsById(departmentId)) {
            throw new ResourceNotFoundException("Department not found");
        }
        List<Employee> employees=employeeRepository.findByDepartmentId(departmentId);
        List<EmployeeResponse> list=new ArrayList<>();
        for(Employee e:employees){
            EmployeeResponse er=new EmployeeResponse(e.getId(),e.getName(),e.getEmail(),e.getJobTitle(),e.getSalary(),e.getDepartment());
            list.add(er);
        }
        return list;
    }
}
