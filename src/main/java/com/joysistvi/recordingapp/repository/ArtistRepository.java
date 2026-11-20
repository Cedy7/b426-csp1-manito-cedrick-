package com.joysistvi.recordingapp.repository;

import com.joysistvi.recordingapp.model.Artist;

import java.util.List;

// Repository(Data Tier) -> Data Access Logic and Data Persistencepublic interface ArtistRepository {
public interface ArtistRepository {

    public List<Artist> findAllActive();


    }
