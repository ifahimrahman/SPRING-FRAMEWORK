package com.fahim.springboot.cruddataJpa.dao;

import com.fahim.springboot.cruddataJpa.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // That's it . . . no need to write any code LOL!!
}
