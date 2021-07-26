package com.example.weather.weather.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.example.weather.weather.exception.ResourceNotFoundException;
import com.example.weather.weather.model.Employee;
import com.example.weather.weather.model.Weather;
import com.example.weather.weather.repository.EmployeeRepository;
import com.example.weather.weather.repository.WeatherService;

@RestController
@RequestMapping("/api/v2")
public class WeatherController {

	@Autowired
	EmployeeRepository employeeRepo;
	
	@Autowired
	WeatherService weatherservice;
	
	
	
	/**
	 * Method to get weather details based on country and city
	 * @param country
	 * @param city
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/now/{country}/{city}")
	public ResponseEntity getWeather(@PathVariable String country,
			@PathVariable String city) {

		Weather weather = null;
		try {
			weather = this.weatherservice.getWeather(country, city);
			return new ResponseEntity(this.weatherservice.getWeather(country,
					city), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
}
