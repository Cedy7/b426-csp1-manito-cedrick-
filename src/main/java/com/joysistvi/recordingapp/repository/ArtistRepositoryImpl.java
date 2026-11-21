package com.joysistvi.recordingapp.repository;

import com.joysistvi.recordingapp.config.DbConnection;
import com.joysistvi.recordingapp.model.Artist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


// Repository(Data Tier) -> Data Access Logic and Data Persistence
public class ArtistRepositoryImpl implements ArtistRepository{

    // Constructor Injection
    // Composition -> has a relationship
    // Using Composition Artist Repository is loosely coupled to DbConnection
    private final DbConnection dbConnection;

    public ArtistRepositoryImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    // Read Artists(Not inside archive)
    @Override
    public List<Artist> findAllActive() {
        List<Artist> artists = new ArrayList<>();
        String query = "SELECT * FROM artists WHERE is_archived = 0";

        try (Connection connection = dbConnection.connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

//            System.out.println("\n----- Artists -----");
//            System.out.printf("%-4s %-20s%n", "ID", "Artist Name");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                artists.add(new Artist(id, name));

                //System.out.printf("%-4d %-20s%n", id, name);
            }

        } catch (SQLException e) {
            System.out.println("Read Artists Error: " + e.getMessage());
        }
        return artists;
    }

    // !!! this will be used for promptRestoreArtist() !!!
    // !!! still not tested !!!
    // Read Artists(Inside archive)
    @ Override
    public List<Artist> findAllArchived() {
        List<Artist> artists = new ArrayList<>();
        String query = "SELECT * FROM artists WHERE is_archived = 1";

        try (Connection connection = dbConnection.connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                artists.add(new Artist(id, name));
            }
        } catch (SQLException e) {
            System.out.println("Read Artists Error: " + e.getMessage());
        }
        return artists;
    }


    // Create Artist
    @Override
    public boolean save(Artist artists) {

        String query = "INSERT INTO artists (name) VALUES (?)";

        try (Connection connection = dbConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, artists.getName().trim());

            return preparedStatement.executeUpdate() > 0;

           // System.out.println("\nArtist: " + artist.getName().trim() + " created successfully!");
        } catch (SQLException e) {
            System.out.println("\nCreate Artist Error: " + e.getMessage());
            return false;
        }
    }

    // Update Artist
    @Override
    public boolean update(int id, Artist artists) {

        String query = "UPDATE artists SET name = ? WHERE id = ?";

        try (Connection connection = dbConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, artists.getName().trim());
            preparedStatement.setInt(2, id);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
//            if (rowsAffected > 0) {
//                System.out.println("\nArtist ID " + id + " updated successfully!");
//                viewAllArtists();
//            } else {
//                System.out.println("\nNo artist found with ID " + id + ".");
//            }
        } catch (SQLException e) {
            System.out.println("\nUpdate Artist Error: " + e.getMessage());
            return false;
        }

    }

    // Hard Delete of Artist
    @Override
    public boolean deleteById(int id) {
        String query = "DELETE FROM artists WHERE id = ?";

        try (Connection connection = dbConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

//            if (rowsAffected > 0) {
//                System.out.println("\nArtist ID " + id + " deleted successfully!");
////                viewAllArtists();
//            } else {
//                System.out.println("\nNo artist found with ID " + id + ".");
//            }
        } catch (SQLException e) {
            System.out.println("\nDelete Artist Error: " + e.getMessage());
            return false;
        }
    }


    // Soft Delete of Artist
    @Override
    public boolean archiveById(int id) {
        String query = "UPDATE artists SET is_archived = 1 WHERE id = ?";

        try (Connection connection = dbConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
//            if (rowsAffected > 0) {
//                System.out.println("\nArtist ID " + id + " archived successfully!");
////                viewAllArtists();
//            } else {
//                System.out.println("\nNo artist found with ID " + id + ".");
//            }
        } catch (SQLException e) {
            System.out.println("\nArchive Artist Error: " + e.getMessage());
            return false;
        }
    }


    // Restore After Soft Delete
    @Override
    public boolean restoreById(int id) {
        String query = "UPDATE artists SET is_archived = 0 WHERE id = ?";

        try (Connection connection = dbConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

//            if (rowsAffected > 0) {
//                System.out.println("\nArtist ID " + id + " restored successfully!");
////                viewAllArtists();
//            } else {
//                System.out.println("\nNo artist found with ID " + id + ".");
//            }
        } catch (SQLException e) {
            System.out.println("\nRestore Artist Error: " + e.getMessage());
            return false;
        }
    }

    // Search Artist by Keyword
    @Override
    public List<Artist> searchByName(String keyword) {
        List<Artist> artists = new ArrayList<>();
        String query = "SELECT id, name FROM artists " +
                "WHERE is_archived = 0 AND name LIKE ? " +
                "ORDER BY id ASC";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, "%" + keyword + "%");

            try (ResultSet res = prep.executeQuery()) {
                while (res.next()) {
                    int id = res.getInt("id");
                    String name = res.getString("name");
                    artists.add(new Artist(id, name));
                }
            }
        } catch (SQLException e) {
            System.out.println("\nSearch Artist Error: " + e.getMessage());
        }

        return artists;
    }


}
