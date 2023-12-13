package com.lucas.spotify.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.spotify.client.Album;
import com.lucas.spotify.client.AlbumSpotifyClient;
import com.lucas.spotify.client.AuthSpotifyClient;
import com.lucas.spotify.client.LoginRequest;

@RestController
@RequestMapping("/spotify/api")
public class AlbumController {

    private final AuthSpotifyClient authSpotifyClient;

    private final AlbumSpotifyClient albumSpotifyClient;

    public AlbumController(AuthSpotifyClient authSpotifyClient, AlbumSpotifyClient albumSpotifyClient) {
        this.authSpotifyClient = authSpotifyClient;
        this.albumSpotifyClient = albumSpotifyClient;
    }


    @GetMapping("/albums")
    public ResponseEntity<List<Album>> helloWorld() {
        var request = new LoginRequest(
            "client_credentials",
            "6973b09441f44ae3accc88af03f40a36",
            "7aa17e0d85da483b9308893059e1d832"
        );
        var token = authSpotifyClient.login(request).getAccessToken();

        var response = albumSpotifyClient.getReleases("Bearer " + token);
        return ResponseEntity.ok(response.getAlbums().getItems());
    }
    
}
