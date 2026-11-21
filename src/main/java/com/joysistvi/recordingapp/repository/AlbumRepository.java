package com.joysistvi.recordingapp.repository;

import com.joysistvi.recordingapp.model.Album;

import java.util.List;

public interface AlbumRepository {


    // Read Artists(Not inside archive)
    public List<Album> findAllActive();

}
