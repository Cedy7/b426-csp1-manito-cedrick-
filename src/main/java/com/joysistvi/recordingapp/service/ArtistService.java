package com.joysistvi.recordingapp.service;

import com.joysistvi.recordingapp.model.Artist;
import java.util.List;

// Application Tier: Business Rule / Validation
public interface ArtistService {

    // Read Artists(Not inside archive)
    List<Artist> getAllActiveArtists();

    // Read Artists(Inside archive)
    List<Artist> getAllArchivedArtists();

    // Create Artist
    boolean createArtist(String name);

    // Update Artist
    boolean updateArtist(int id, String name);

    // Hard Delete of Artist
    boolean deleteArtist(int id);

    // Soft Delete of Artist
    boolean archiveArtist(int id);

    // Restore After Soft Delete
    boolean restoreArtist(int id);

    // Search Artist by Keyword
    List<Artist> searchArtists(String keyword);

    Artist getArtistById(int id);
}
