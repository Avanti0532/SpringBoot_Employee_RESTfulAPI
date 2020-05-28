package academy.ennate.service;

import academy.ennate.entity.Employee;
import academy.ennate.exception.BadRequestException;
import academy.ennate.exception.EmployeeNotFound;
import academy.ennate.exception.ResourceNotFoundException;
import academy.ennate.repository.EmployeeRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRespository empRespository;
    @Override
    public List<Employee> findAll() {

        return (List<Employee>) empRespository.findAll();
    }

    @Override
    public Employee findOne(String id) {
      Optional<Employee> emp = empRespository.findById(id);
        if(!emp.isPresent()){
            throw new EmployeeNotFound("Employee not found "+id);
        }else{
            return emp.get();
        }
    }

    @Override
    public Employee findByEmail(String email) {
        Optional<Employee> existing = empRespository.findByEmail(email);
        if(!existing.isPresent()){
            throw new BadRequestException("Employee with email: "+email+" already exist");
        }
        return existing.get();
    }

    @Override
    @Transactional
    public Employee create(Employee emp) {
        Optional<Employee> existing = empRespository.findByEmail(emp.getEmail());
        if(!existing.isPresent()){
            throw new BadRequestException("Employee with email: "+emp.getEmail()+" already exist");
        }
        return empRespository.save(emp);
    }

    @Override
    @Transactional
    public Employee update(String id, Employee emp) {
        Optional<Employee> existing = empRespository.findById(id);
        if(!existing.isPresent()){
            throw new ResourceNotFoundException("Employee with id "+id+" doesn't exist");
        }
        return empRespository.save(emp);
    }

    @Override
    @Transactional
    public void delete(String id) {
        Optional<Employee> existing = empRespository.findById(id);
        if(!existing.isPresent()){
            throw new ResourceNotFoundException("Employee with id "+id+" doesn't exist");
        }
        empRespository.delete(existing.get());
    }
}
