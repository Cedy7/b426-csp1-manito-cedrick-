package com.joysistvi.recordingapp.service;

import com.joysistvi.recordingapp.model.Artist;
import com.joysistvi.recordingapp.repository.ArtistRepository;

import java.util.List;


// Application Tier: Business Rule / Validation
public class ArtistServiceImpl implements ArtistService{

    private final ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    // Read Artists(Not inside archive)
    @Override
    public List<Artist> getAllActiveArtist() {
        return artistRepository.findAllActive();
    }

    // !!! this will be used for promptRestoreArtist() !!!
    // !!! still not tested !!!
    // Read Artists(Inside archive)
    @Override
    public List<Artist> getAllArchivedArtists() { return artistRepository.findAllArchived(); }


    // Create Artist
    @Override
    public boolean createArtist(String name) {
        // Guard Clause
        if (name == null || name.trim().isEmpty()) {
            System.out.println("\nValidation Error: Artist name is required.");
            return false;
        }

        Artist artist = new Artist(name);
        return artistRepository.save(artist);
    }


    // Update Artist
    @Override
    public boolean updateArtist(int id, String name) {
        // Guard Clauses
        if (id <= 0) {
            System.out.println("\nValidation Error: Artist ID must be greater than 0.");
            return false;
        }
        if (name == null || name.trim().isEmpty()) {
            System.out.println("\nValidation Error: Artist name is required.");
            return false;
        }

        Artist artistName = new Artist(name);
        Artist artistId = new Artist(id);
        // I used getter so that I get the integer insie model/Artist intead of passing the object...
        // alone that will cause error since artistRepository.update() expect int as first argument
        return artistRepository.update(artistId.getId(), artistName);

    }



    // Hard Delete of Artist
    @Override
    public boolean deleteArtist(int id) {
        // Guard Clause
        if (id <= 0) {
            System.out.println("\nValidation Error: Artist ID must be greater than 0.");
            return false;
        }

        Artist artistId = new Artist(id);
        return artistRepository.deleteById(artistId.getId());
    }


    // Soft Delete of Artist
    public boolean archiveArtist(int id) {
        // Guard Clause
        if (id <= 0) {
            System.out.println("\nValidation Error: Artist ID must be greater than 0.");
            return false;
        }

        Artist artistId = new Artist(id);
        return artistRepository.archiveById(artistId.getId());
    }



    // Restore After Soft Delete
    public boolean restoreArtist(int id) {
        // Guard Clause
        if (id <= 0) {
            System.out.println("\nValidation Error: Artist ID must be greater than 0.");
            return false;
        }

        Artist artisId = new Artist(id);
        return artistRepository.restoreById(artisId.getId());
    }

    // Search Artist by Keyword
    @Override
    public List<Artist> searchArtists(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return List.of();
        }
        return artistRepository.searchByName(keyword.trim());
    }

}
