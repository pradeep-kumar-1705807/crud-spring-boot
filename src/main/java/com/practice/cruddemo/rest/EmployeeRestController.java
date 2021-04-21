package com.practice.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.cruddemo.dao.EmployeeDAO;
import com.practice.cruddemo.entity.Employee;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	@Autowired
	private EmployeeDAO employeeService;
	
	@GetMapping("/employees")
	public List<Employee> findAll() {
		
		return employeeService.findAll();
	}
	
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId) {
		
		Employee employee =  employeeService.findById(employeeId);
		if(employee == null ) {
			throw new RuntimeException("Employee id not found " +  employeeId);
		}
		
		return employee;
	}
	
	
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee theEmployee) {
		theEmployee.setId(0);
		
		employeeService.save(theEmployee);
		
		return theEmployee;
		
		
	}
	
	@DeleteMapping("/employees/{employeeId}")
	public Employee deleteById(@PathVariable int employeeId) {
		
		Employee employee =  employeeService.findById(employeeId);
		if(employee == null ) {
			throw new RuntimeException("Employee id not found " +  employeeId);
		}
		employeeService.deleteById(employeeId);
		
		return employee;
		
		
	}

}
