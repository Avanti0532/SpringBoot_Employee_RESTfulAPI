package academy.ennate.repository;
import academy.ennate.entity.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface EmployeeRespository extends CrudRepository<Employee, String> {
    Optional<Employee> findByEmail(String email);
}
