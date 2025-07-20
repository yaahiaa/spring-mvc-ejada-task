package ejada.task.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ejada.task.model.Employee;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentResponse {
    private Long id;

    private String name;
    private String location;
}
