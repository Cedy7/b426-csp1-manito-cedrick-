package com.joysistvi.recordingapp.service;

import com.joysistvi.recordingapp.model.Artist;

import java.util.List;

// Application Tier: Business Rule / Validation
public interface ArtistService {

    // Read Artists(Not inside archive)
    public List<Artist> getAllActiveArtist();

    // !!! this will be used for promptRestoreArtist() !!!
    // !!! still not tested !!!
    // Read Artists(Inside archive)
    public List<Artist> getAllArchivedArtists();


    // Create Artist
    public boolean createArtist(String name);


    // Update Artist
    public boolean updateArtist(int id, String name);

    // Hard Delete of Artist
    public boolean deleteArtist(int id);

    // Soft Delete of Artist
    public boolean archiveArtist(int id);

    // Restore After Soft Delete
    public boolean restoreArtist(int id);


    // Search Artist by Keyword
    public List<Artist> searchArtists(String keyword);

    Artist getArtistById(int id);
}
