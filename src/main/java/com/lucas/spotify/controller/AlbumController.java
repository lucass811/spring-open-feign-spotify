package com.lucas.spotify.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.spotify.client.AuthSpotifyClient;
import com.lucas.spotify.client.LoginRequest;

@RestController
@RequestMapping("/spotify/api")
public class AlbumController {

    private final AuthSpotifyClient authSpotifyClient;

    public AlbumController(AuthSpotifyClient authSpotifyClient) {
        this.authSpotifyClient = authSpotifyClient;
    }

    @GetMapping("/albums")
    public ResponseEntity<String> helloWorld() {
        var request = new LoginRequest(
            "client_credentials",
            "6973b09441f44ae3accc88af03f40a36",
            "7aa17e0d85da483b9308893059e1d832"
        );

        return ResponseEntity.ok(authSpotifyClient.login(request).getAccessToken());
    }
    
}
