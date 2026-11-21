package com.joysistvi.recordingapp.repository;


import com.joysistvi.recordingapp.config.DbConnection;
import com.joysistvi.recordingapp.model.Album;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumRepositoryImpl implements AlbumRepository{

    private final DbConnection dbConnection;

    public AlbumRepositoryImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    // Read Albums(Not inside archive)
    @Override
    public List<Album> findAllActive(){
        List <Album> albums = new ArrayList<>();
        String query = "SELECT albums.id, albums.name, albums.year, artists.name AS artist_name " +
                "FROM albums " +
                "JOIN artists " +
                "ON albums.artist_id = artists.id " +
                "WHERE albums.is_archived = 0";

        try (Connection connection = dbConnection.connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
//
//            System.out.println("\n----- Albums -----");
//            System.out.printf("%-4s %-20s %-8s %-20s%n", "ID", "Name", "Year", "Artist");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int year = resultSet.getInt("year");
                String artistName = resultSet.getString("artist_name");
                albums.add(new Album(id, name, year, artistName));

//                System.out.printf("%-4d %-20s %-8d %-20s%n", id, name, year, artistName);
            }
        } catch (SQLException e) {
            System.out.println("Read Albums Error: " + e.getMessage());
        }
        return albums;
    }

}
