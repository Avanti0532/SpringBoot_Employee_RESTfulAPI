package academy.ennate.service;

import academy.ennate.entity.Employee;
import academy.ennate.exception.BadRequestException;
import academy.ennate.exception.EmployeeNotFound;
import academy.ennate.exception.ResourceNotFoundException;
import academy.ennate.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository empRepository;
    @Override
    public List<Employee> findAll() {

        return (List<Employee>) empRepository.findAll();
    }

    @Override
    public Employee findOne(String id) {
      Optional<Employee> emp = empRepository.findById(id);
        if(!emp.isPresent()){
            throw new EmployeeNotFound("Employee not found "+id);
        }else{
            return emp.get();
        }
    }

    @Override
    public Employee findByEmail(String email) {
        Optional<Employee> existing = empRepository.findByEmail(email);
        if(!existing.isPresent()){
            throw new EmployeeNotFound("Employee with email: "+email+" does not exist");
        }
        return existing.get();
    }

    @Override
    @Transactional
    public Employee create(Employee emp) {
        Optional<Employee> existing = empRepository.findByEmail(emp.getEmail());
        if(existing.isPresent()){
            throw new BadRequestException("Employee with email: "+emp.getEmail()+" already exist");
        }
        return empRepository.save(emp);
    }

    @Override
    @Transactional
    public Employee update(String id, Employee emp) {
        Optional<Employee> existing = empRepository.findById(id);
        if(!existing.isPresent()){
            throw new ResourceNotFoundException("Employee with id "+id+" doesn't exist");
        }
        existing.get().setFirstName(emp.getFirstName());
        existing.get().setLastName(emp.getLastName());
        return empRepository.save(existing.get());
    }

    @Override
    @Transactional
    public void delete(String id) {
        Optional<Employee> existing = empRepository.findById(id);
        if(!existing.isPresent()){
            throw new ResourceNotFoundException("Employee with id "+id+" doesn't exist");
        }
        empRepository.delete(existing.get());
    }
}
