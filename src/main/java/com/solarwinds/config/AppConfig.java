package com.solarwinds.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.solarwinds.error.DpaErrorHandler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan("com.solarwinds")
public class AppConfig {

	@Bean
	RestTemplate restTemplate() {

		// Rest Template
		// The default implementation of RestTemplate only allows you to read the response
		// stream once, this will allow multiple usages needed for when logging
		RestTemplate restTemplate = new RestTemplate(
				new BufferingClientHttpRequestFactory(
						new SimpleClientHttpRequestFactory()
				)
		);

		// Interceptors
		List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
		if (CollectionUtils.isEmpty(interceptors)) {
			interceptors = new ArrayList<>();
		}
		interceptors.add(requestInterceptor());
		restTemplate.setInterceptors(interceptors);

		// JSON Mapper
		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		ObjectMapper jsonObjectMapper = objectMapper();

		// Add JavaTimeModule to out ObjectMapper which will automatically serialize Dates
		// to String and de-serialize String to Dates.
		// Instance variables should use the following annotation for conversions
		//
		//   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
		//   private OffsetDateTime time;
		//    --> 2018-11-06T09:15:45-07:00
		//
		//   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
		//   private OffsetDateTime time;
		//   --> 2018-11-06T09:15:45.678-07:00
		JavaTimeModule module = new JavaTimeModule();
		jsonObjectMapper.registerModule(module);

		jsonConverter.setObjectMapper(jsonObjectMapper);
		restTemplate.getMessageConverters().add(0,jsonConverter);

		// Error Handler
		DpaErrorHandler dpaErrorHandler = new DpaErrorHandler();
		dpaErrorHandler.setObjectMapper(jsonObjectMapper);
		restTemplate.setErrorHandler(dpaErrorHandler);

		return restTemplate;
	}

	@Bean
	DpaClientRequestInterceptor requestInterceptor() {
		return new DpaClientRequestInterceptor();
	}

	@Bean
	ObjectMapper objectMapper() {
		ObjectMapper jsonObjectMapper = new ObjectMapper();
		jsonObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		jsonObjectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return jsonObjectMapper;
	}

}
