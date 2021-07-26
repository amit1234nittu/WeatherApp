package com.example.weather.weather;

import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.weather.weather.controller.WeatherController;
import com.example.weather.weather.model.Weather;
import com.example.weather.weather.model.WeatherEntry;
import com.example.weather.weather.repository.WeatherService;

@RunWith(SpringRunner.class)
@WebMvcTest(WeatherController.class)
class WeatherApplicationTests {

	@MockBean
	private WeatherService weatherService;

	@Autowired
	private MockMvc mvc;

	@Test
	public void weather() throws Exception {
		Weather weather = new Weather();
		weather.setName("London");
		setWeatherEntry(weather, 286.72, 800, "01d", Instant.ofEpochSecond(1234));
		given(this.weatherService.getWeather("uk", "london")).willReturn(weather);
		this.mvc.perform(get("/api/weather/now/uk/london"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is("London")))
				.andExpect(jsonPath("$.temperature", is(286.72)))
				.andExpect(jsonPath("$.weatherId", is(800)))
				.andExpect(jsonPath("$.weatherIcon", is("01d")))
				.andExpect(jsonPath("$.timestamp", is("1970-01-01T00:20:34Z")));
		verify(this.weatherService).getWeather("uk", "london");
	}
	
	private static void setWeatherEntry(WeatherEntry entry, double temperature, int id, String icon,
			Instant timestamp) {
		entry.setTemperature(temperature);
		entry.setWeatherId(id);
		entry.setWeatherIcon(icon);
		entry.setTimestamp(timestamp.getEpochSecond());
	}

}
