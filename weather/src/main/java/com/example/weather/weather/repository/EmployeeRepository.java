package com.example.weather.weather.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.weather.weather.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>{

	
	
}
