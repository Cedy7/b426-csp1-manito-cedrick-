package com.joysistvi.recordingapp.repository;

import com.joysistvi.recordingapp.model.Artist;

import java.util.List;

// Repository(Data Tier) -> Data Access Logic and Data Persistence
public interface ArtistRepository {

    // Read Artists(Not inside archive)
    public List<Artist> findAllActive();

    // !!! this will be used for promptRestoreArtist() !!!
    // !!! still not tested !!!
    // Read Artists(Inside archive)
    public List<Artist> findAllArchived();

    // Create Artist
    public boolean save(Artist artists);

    // Update Artist
    public boolean update(int id, Artist artists);

    // Hard Delete of Artist
    public boolean deleteById(int id);

    // Soft Delete of Artist
    public boolean archiveById(int id);

    // Restore After Soft Delete
    public boolean restoreById(int id);

    // Search Artist by Keyword
    public List<Artist> searchByName(String keyword);
}
