package com.joysistvi.recordingapp.view;

import com.joysistvi.recordingapp.model.Artist;
import com.joysistvi.recordingapp.service.ArtistService;

import java.util.List;
import java.util.Scanner;

public class ArtistView {

    private final ArtistService artistService;
    private final Scanner scanner;

    public ArtistView(ArtistService artistService, Scanner scanner) {
        this.artistService = artistService;
        this.scanner = scanner;
    }

    // Read Artists
    public void displayAllArtist() {
        List<Artist> artists = artistService.getAllActiveArtist();


        System.out.println("\n----- Artists -----");
        System.out.printf("%-4s %-20s%n", "ID", "Artist Name");


        if (artists == null || artists.isEmpty()) {
            System.out.println("No active artists found.");
            return;
        }

        for (Artist artist : artists) {
            System.out.printf("%-4d %-20s%n",
                    artist.getId(),
                    artist.getName());
        }

    }

    // Read Artists(Inside archive)
    public void displayAllArchivedArtist() {
        List<Artist> artists = artistService.getAllArchivedArtists();


        System.out.println("\n----- Archived Artists -----");
        System.out.printf("%-4s %-20s%n", "ID", "Artist Name");


        if (artists == null || artists.isEmpty()) {
            System.out.println("No active artists found.");
            return;
        }

        for (Artist artist : artists) {
            System.out.printf("%-4d %-20s%n",
                    artist.getId(),
                    artist.getName());
        }

    }


    // Create Artist
    public void promptCreateArtist() {
        System.out.println("\n----- Create New Artist -----");
        System.out.println("Enter artist name: ");
        String name = scanner.nextLine();

        boolean created = artistService.createArtist(name);

        if (created) {
            System.out.println("Artist " + name.trim() + " created successfully");
        } else {
            System.out.println("Failed to create artist.");
        }
    }

    // Update Artist
    public void promptUpdateArtist() {
        System.out.print("\n----- Update Artist -----");
        System.out.print("\nEnter ID of artist you want to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // clears the leftover '\n' from pressing Enter

        System.out.println("\nEnter the Name that you want instead for that ID: ");
        String name = scanner.nextLine();

        boolean updated = artistService.updateArtist(id, name);

        if (updated) {
            System.out.println("\nArtist ID " + id + " updated successfully!");
//             viewAllArtists();
        } else {
            System.out.println("\nNo artist found with ID " + id + ".");
        }

    }

    // Hard Delete of Artist
    public void promptDeleteArtist() {
        System.out.println("\n----- Delete Artist -----");
        System.out.print("\nEnter ID of artist you want to delete permanently: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean deleted = artistService.deleteArtist(id);

        if (deleted) {
            System.out.println("\nArtist ID " + id + " deleted successfully!");
//            viewAllArtists();
        } else {
            System.out.println("\nNo artist found with ID " + id + ".");
        }
    }

    // Soft Delete of Artist
    public void promptArchiveArtist() {
        System.out.println("\n----- Archive Artist -----");
        System.out.print("\nEnter ID of artist you want to archive or delete temporarily: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean archived = artistService.archiveArtist(id);

        if (archived) {
            System.out.println("\nArtist ID " + id + " archived successfully!");
//           viewAllArtists();
        } else {
            System.out.println("\nNo artist found with ID " + id + ".");
        }

    }

    // Restore After Soft Delete
    public void promptRestoreArtist() {
        List<Artist> archivedArtists = artistService.getAllArchivedArtists();

        if (archivedArtists == null || archivedArtists.isEmpty()) {
            System.out.println("There is no artist in archive!\nProgram Terminated!");
            return;
        } else {
            displayAllArchivedArtist();
        }

        System.out.print("\n----- Restore Archived Artist -----");
        System.out.print("\nEnter ID of artist you want to restore from archive:");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean restored = artistService.restoreArtist(id);

        if (restored) {
            System.out.println("\nArtist ID " + id + " restored successfully!");
//            viewAllArtists();
        } else {
            System.out.println("\nNo artist found with ID " + id + ".");
        }
    }

    // Search Artist by Keyword
    public void promptSearchArtists() {
        System.out.print("\n----- Search Artist with Keyword -----");
        System.out.print("\nGive the keyword name of artist you wanna search: ");
        String keyword = scanner.nextLine();

        List<Artist> artists = artistService.searchArtists(keyword);

        String cleanKeyword = (keyword == null) ? "" : keyword.trim();
        System.out.println("\n----- Search Artists (Keyword: '" + cleanKeyword + "') -----");
        System.out.printf("%-4s %-25s%n", "ID", "Artist Name");

        if (artists == null || artists.isEmpty()) {
            System.out.println("No matching active artists found.");
            return;
        }

        for (Artist artist : artists) {
            System.out.printf("%-4d %-25s%n",
                    artist.getId(),
                    artist.getName());
        }
    }

}
 /*
 System.out.println("=== Initial Artists (READ) ===");
System.out.println("\n=== Create New Artist (CREATE) ===");




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
Main.main()
  */