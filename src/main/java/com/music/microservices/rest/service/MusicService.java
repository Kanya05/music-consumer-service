package com.music.microservices.rest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;
import com.music.microservices.rest.response.Music;

@Service
public class MusicService {

	private Gson gson = new Gson();
	private Logger logger = LoggerFactory.getLogger(MusicService.class);
	
	@Autowired
	private RestTemplate restTemplate;

	public Object getAllMusics() {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("Accept", "application/json");

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost").port(8080)
				.path("/musics");

		HttpEntity<?> httpEntity = new HttpEntity<Object>(requestHeaders);

		Object productsObject = null;

		try {

			logger.info("Making a Rest GET Call Towards URL={}", builder.toUriString());

			productsObject = (Object) restTemplate
					.exchange(builder.toUriString(), HttpMethod.GET, httpEntity, String.class).getBody();

		} catch (Exception e) {
			logger.error("Encountered an error when trying to get a Products ", e.getMessage());
		}
		return productsObject;
	}

	public Object addMusic(Music music) {
	
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost").port(8080)
					.path("/musics");
			
			HttpEntity<String> entity = new HttpEntity<String>(gson.toJson(music), headers);
			
			ResponseEntity<String> answer = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, String.class);
			
			logger.info(String.valueOf(answer.getStatusCodeValue()));
		} catch (RestClientException e) {
			logger.error("Encountered an error when trying to Add a Product ", e.getMessage());
		}
		
		return this.getAllMusics();
	}
	
	public Object removeMusic(Music music) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost").port(8080)
					.path("/musics/"+music.getMusicId());
			
			HttpEntity<String> entity = new HttpEntity<String>(gson.toJson(music), headers);
			
			ResponseEntity<String> answer = restTemplate.exchange(builder.toUriString(), HttpMethod.DELETE, entity, String.class);
			
			logger.info(String.valueOf(answer.getStatusCodeValue()));
		} catch (RestClientException e) {
			logger.error("Encountered an error when trying to Add a Product ", e.getMessage());
		}
		
		return this.getAllMusics();
	}
}
