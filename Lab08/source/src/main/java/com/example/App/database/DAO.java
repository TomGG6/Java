package com.example.App.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO {
    private static DatabaseConnection connection;

    static {
        try {
            connection = new DatabaseConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addPerson(String firstName, String lastName) throws SQLException {
        try {
            PreparedStatement stmt  = connection.getConnection().prepareStatement("INSERT INTO Persons(First_name, Last_name) VALUES (?,?);");
            stmt.setString(1,   firstName);
            stmt.setString(2, lastName);
            stmt.execute();
            System.out.println("Dodano osobę");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deletePerson(int index) throws SQLException {
        try{
            PreparedStatement stmt = connection.getConnection().prepareStatement("DELETE FROM Persons WHERE Person_ID = ?;");
            stmt.setInt(1, index);
            stmt.execute();
            System.out.println("Usunięto osobę");
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updatePerson(String firstName, String lastName, int index)
    {
        try {
            PreparedStatement stmt  = connection.getConnection().prepareStatement("UPDATE Persons SET First_name = ?, Last_name = ? WHERE Person_ID = ?;");
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setInt(3, index);
            stmt.execute();
            System.out.println("Zaktualizowano osobę");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String showPersons() throws SQLException {
        String result = "";
        try (Statement stmt = connection.getConnection().createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM Persons;");
            while (rs.next()) {
                int index = rs.getInt("Person_ID");
                String firstName = rs.getString("First_name");
                String lastName = rs.getString("Last_name");
                result += String.valueOf(index) + ", " + firstName + ", " + lastName + "\n";
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
