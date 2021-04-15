package com.music.microservices.rest.response;

import org.springframework.hateoas.ResourceSupport;

public class Music extends ResourceSupport{
	private String musicId;
    private String name;
    private String artist;
	
    public String getMusicId() {
		return musicId;
	}
	public void setMusicId(String musicId) {
		this.musicId = musicId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	

}