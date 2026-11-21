package com.joysistvi.recordingapp;

import com.joysistvi.recordingapp.config.DbConnection;
import com.joysistvi.recordingapp.repository.AlbumRepository;
import com.joysistvi.recordingapp.repository.AlbumRepositoryImpl;
import com.joysistvi.recordingapp.repository.ArtistRepository;
import com.joysistvi.recordingapp.repository.ArtistRepositoryImpl;
import com.joysistvi.recordingapp.service.AlbumService;
import com.joysistvi.recordingapp.service.AlbumServiceImpl;
import com.joysistvi.recordingapp.service.ArtistService;
import com.joysistvi.recordingapp.service.ArtistServiceImpl;
import com.joysistvi.recordingapp.view.AlbumView;
import com.joysistvi.recordingapp.view.ArtistView;

import java.util.Scanner;

// Entry Point
public class App {

    public static void main(String[] args) {
        DbConnection dbConnection = new DbConnection();
        Scanner scanner = new Scanner(System.in);

        // Wiring (Artist)
        ArtistRepository artistRepository = new ArtistRepositoryImpl(dbConnection);
        ArtistService artistService = new ArtistServiceImpl(artistRepository);
        ArtistView artistView = new ArtistView(artistService, scanner);

        // Wiring (Album)
        AlbumRepository albumRepository = new AlbumRepositoryImpl((dbConnection));
        AlbumService albumService = new AlbumServiceImpl(albumRepository);
        AlbumView albumView = new AlbumView(albumService, scanner);



//        artistView.promptCreateArtist();
//        artistView.promptUpdateArtist();
//        artistView.promptDeleteArtist();
//        artistView.promptArchiveArtist();


        artistView.displayAllArtist();
//        artistView.promptArchiveArtist();
//        artistView.promptRestoreArtist();
//        artistView.displayAllArchivedArtist();
        artistView.promptSearchArtists();
        artistView.displayAllArtist();


    }
}

// APPLICATION FLOW

/*
// APPLICATION FLOW
    Repository
     │
     ▼
    Service
     │
     ▼
    View
     │
     ▼
    Main Application

Dependency Wiring:
Repository --> Service --> View --> main()

Example:
ArtistRepositoryImpl
        │
        ▼
ArtistServiceImpl
        │
        ▼
ArtistView
        │
        ▼
App.main()
 */