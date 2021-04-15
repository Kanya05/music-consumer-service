package com.music.microservices.rest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.google.gson.Gson;
import com.music.microservices.rest.response.Music;
import com.music.microservices.rest.service.MusicService;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.ConsoleAppender;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

@WebAppConfiguration
public class MusicServiceApplicationTests extends BaseTest{

	@Autowired
	private MusicService musicService;
	
	private Gson gson = new Gson();


	private Appender mockAppender;

	@Before
	public void setupClient() {

//		mockAppender = Mockito.mock(ConsoleAppender.class);

		final Logger logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
//		logger.addAppender(mockAppender);
		logger.setLevel(Level.ERROR);
	}

	@After
	public void teardown() {
		final Logger logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
//		logger.detachAppender(mockAppender);
	}

	@Test
	public void shouldGetAllProducts() {

		Object response = musicService.getAllMusics();
		Music[] returnedProduct = gson.fromJson((String) response, Music[].class);
		Assert.assertEquals("Woozy", returnedProduct[0].getName());
	}
	
	@Test
	public void shouldPOSTNewProduct() {

		Object response = musicService.addMusic(Expectations.createMusicObjectToPut());
		Music[] returnedProduct = gson.fromJson((String) response, Music[].class);
		Assert.assertEquals("Sweater Weather", returnedProduct[1].getName());
	}

}
