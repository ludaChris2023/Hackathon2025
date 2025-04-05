package com.example.numberdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DatabaseController {
    @Autowired
    private DatabaseAPIService databaseAPIService;

    @GetMapping("/fetch-Games-NHL")
    public String fetchGamesDataNHL() {
        return databaseAPIService.fetchGamesByLeague("NHL");
    }

    @GetMapping("/fetch-Games-NFL")
    public String fetchGamesDataNFL() {
        return databaseAPIService.fetchGamesByLeague("NFL");
    }

    @GetMapping("/fetch-Games-NBA")
    public String fetchGamesDataNBA() {
        return databaseAPIService.fetchGamesByLeague("NBA");
    }    
}
