package com.joysistvi.recordingapp.repository;

import com.joysistvi.recordingapp.model.Artist;
import java.util.List;

// Repository(Data Tier) -> Data Access Logic and Data Persistence
public interface ArtistRepository {

    // Read Artists(Not inside archive)
    List<Artist> findAllActive();

    // Read Artists(Inside archive)
    List<Artist> findAllArchived();

    // Create Artist
    boolean save(Artist artists);

    // Update Artist
    boolean update(int id, Artist artists);

    // Hard Delete of Artist
    boolean deleteById(int id);

    // Soft Delete of Artist
    boolean archiveById(int id);

    // Restore After Soft Delete
    boolean restoreById(int id);

    // Search Artist by Keyword
    List<Artist> searchByName(String keyword);

    Artist findById(int id); // additional method
}
