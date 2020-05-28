package academy.ennate.controller;

import academy.ennate.entity.Employee;
import academy.ennate.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @RequestMapping(method = RequestMethod.GET, value="getById")
    public Employee findOne(@RequestParam(name="id") String empId){
        System.out.println("idddddddd");
        return service.findOne(empId);
    }

    @RequestMapping(method = RequestMethod.GET, value="getByEmail")
    public Employee findByEmail(@RequestParam(name="email") String email){
        System.out.println("emaillllllllll");
        return service.findByEmail(email);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Employee> findAll(){
        System.out.println("findallllllll");
        return service.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Employee create(@RequestBody Employee employee){
        System.out.println("posttttttttt");
        return service.create(employee);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}")
    public Employee update(@PathVariable("id") String empId, @RequestBody Employee employee){
        return service.update(empId,employee);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void delete(@PathVariable("id") String empId){
        service.delete(empId);

    }
}
