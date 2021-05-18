package com.fahim.springboot.cruddataJpa.rest;

import com.fahim.springboot.cruddataJpa.entity.Employee;
import com.fahim.springboot.cruddataJpa.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService){
        employeeService = theEmployeeService;

    }
    // expose "/employees" and return list of employees
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    // add mapping for GET/employees/{employeeID}
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee (@PathVariable int employeeId){
        Employee theEmployee = employeeService.findById(employeeId);
        if(theEmployee == null){
            throw new RuntimeException("Employee id not found -" + employeeId);
        }

        return theEmployee;
    }

    // add mapping for POST/employees - add new employee

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){
        // also jst in case  they pass an id in JSON .. set id to 0
        // this is to force a save of new item . . . instead of update

        theEmployee.setId(0);
        employeeService.save(theEmployee);
        return theEmployee;
    }

    // add mapping for put/emploee - update existing employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee){
        employeeService.save(theEmployee);
        return theEmployee;
    }

    // add mapping for delete mapping.."employee/{employyeId}" -delete employee
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
        Employee tempEmployee = employeeService.findById(employeeId);
        //throw exception if null
        if(tempEmployee == null){
            throw new RuntimeException("Employee id not found- " + employeeId);
        }
        employeeService.deleteById(employeeId);

        return "Deleted employee id-" + employeeId;
    }


//    private EmployeeDAO employeeDAO;
//
//    // quick and dirty: inject employee dao(use constructor injection)
//    @Autowired
//    public EmployeeRestController(EmployeeDAO theEmployeeDAO){
//        employeeDAO = theEmployeeDAO;
//    }
//
//    // expose "/employees" and return list of employees
//    @GetMapping("/employees")
//    public List<Employee> findAll(){
//        return employeeDAO.findAll();
//    }

}