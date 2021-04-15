package com.music.microservices.rest;

import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.JsonBody;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

import com.google.gson.Gson;

import com.music.microservices.rest.response.Music;

public class Expectations {

	private static Gson gson = new Gson();

	public static void createDefaultExpectations(ClientAndServer mockServer) {

	 	addMusic(mockServer);
		getMusics(mockServer);
		removeMusic(mockServer);

	}

	private static void removeMusic(ClientAndServer mockServer) {
		Music music = createMusicObjectToPut();
		JsonBody body = new JsonBody(gson.toJson(music));
		
		mockServer.when(request().withMethod("DELETE")
				.withHeader("Content-Type", "application/json").withPath("/musics/"+music.getMusicId())
				.withBody(body))
				.respond(response().withStatusCode(200).withBody(body));

	}

	private static void getMusics(ClientAndServer mockServer) {
		Music   musics [] = new Music[1];
		musics[0]=createMusicObjectToPut();
	
		mockServer.when(request().withMethod("GET")
				.withHeader("Accept", "application/json").withPath("/musics"))
				.respond(response().withStatusCode(200).withBody(gson.toJson(musics)));

	}

	private static void addMusic(ClientAndServer mockServer) {
		Music   musics [] = new Music[1];
		musics[0]=createMusicObjectToPut();
		
		mockServer.when(request().withMethod("POST")
				.withHeader("Content-Type", "application/json").withPath("/musics")
				.withBody(gson.toJson(createMusicObjectToPut())))
				.respond(response().withStatusCode(200).withBody(gson.toJson(musics)));
	}

	public static Music createMusicObjectToPut() {
		Music music = new Music();
		music.setName("Sweater Weather");
		music.setArtist("The Neighborhood");
		return music;
	}

}
