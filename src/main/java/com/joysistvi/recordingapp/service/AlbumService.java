package com.joysistvi.recordingapp.service;

import com.joysistvi.recordingapp.model.Album;

import java.util.List;

// Application Tier: Business Rule / Validation
public interface AlbumService {

    // Read Albums(Not inside archive)
    public List<Album> getAllActiveAlbums();


}
