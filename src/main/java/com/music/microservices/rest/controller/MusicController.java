package com.music.microservices.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.music.microservices.rest.response.Music;
import com.music.microservices.rest.service.MusicService;


@RestController
@RequestMapping("/listMusics")
public class MusicController {
	
	@Autowired
	private MusicService musicService;

	@RequestMapping(value = { "/getAllMusics" }, method = RequestMethod.GET)
	public Object getAllProducts() {
		return musicService.getAllMusics();
	}
	@RequestMapping(value = { "/addMusic" }, method = RequestMethod.POST)
	public Object addProduct(@RequestBody Music music) {
		return musicService.addMusic(music);
	}

	@RequestMapping(value = { "/removeMusic" }, method = RequestMethod.POST)
	public Object removeProduct(@RequestBody Music music) {
		return musicService.removeMusic(music);
	}
}
