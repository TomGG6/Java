package com.database;

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

    public static void addPerson(String firstName, String lastName) throws SQLException {
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

    public static void deletePerson(int index) throws SQLException {
        try{
            PreparedStatement stmt = connection.getConnection().prepareStatement("DELETE FROM Persons WHERE Person_ID = ?;");
            stmt.setInt(1, index);
            stmt.execute();
            System.out.println("Usunięto osobę");
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updatePerson(String firstName, String lastName, int index)
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

    public static void showPersons() throws SQLException {
        try (Statement stmt = connection.getConnection().createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM Persons;");
            while (rs.next()) {
                int index = rs.getInt("Person_ID");
                String firstName = rs.getString("First_name");
                String lastName = rs.getString("Last_name");
                System.out.println(index + ", " + firstName + ", " + lastName);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addEvent(String name, String place, String date)
    {
        try {
            PreparedStatement stmt  = connection.getConnection().prepareStatement("INSERT INTO Events (Name, Place, Deadline) VALUES (?,?,?);");
            stmt.setString(1, name);
            stmt.setString(2, place);
            stmt.setString(3, date);
            stmt.execute();
            System.out.println("Dodano wydarzenie");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteEvent(int index) throws SQLException {
        try{
            PreparedStatement stmt = connection.getConnection().prepareStatement("DELETE FROM Events WHERE Event_ID = ?");
            stmt.setInt(1, index);
            stmt.execute();
            System.out.println("Usunięto wydarzenie");
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateEvent(String name, String place, String date, int index)
    {
        try {
            PreparedStatement stmt  = connection.getConnection().prepareStatement("UPDATE Events SET Name = ?, Place = ?, Deadline = ? WHERE Event_ID = ?;");
            stmt.setString(1, name);
            stmt.setString(2, place);
            stmt.setString(3, date);
            stmt.setInt(4, index);
            stmt.execute();
            System.out.println("Zaktualizowano wydarzenie");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void showEvents() throws SQLException {
        try (Statement stmt = connection.getConnection().createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM Events;");
            while (rs.next()) {
                int index = rs.getInt("Event_ID");
                String name = rs.getString("Name");
                String place = rs.getString("Place");
                String deadline = rs.getString("Deadline");
                System.out.println(index + ", " + name + ", " + place + "," + deadline);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addDonation(String paymentDate, int amount, int personID, int eventID, int installmentID)
    {
        try {
            PreparedStatement stmt  = connection.getConnection().prepareStatement("INSERT INTO Donation (Payment_date, Amount, Person_ID, Event_ID, Installment_ID) VALUES (?,?,?, ?, ?);");
            stmt.setString(1, paymentDate);
            stmt.setInt(2, amount);
            stmt.setInt(3, personID);
            stmt.setInt(4, eventID);
            stmt.setInt(5, installmentID);
            stmt.execute();
            System.out.println("Dodano wpłatę");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteDonation(int index) throws SQLException {
        try{
            PreparedStatement stmt = connection.getConnection().prepareStatement("DELETE FROM Donation WHERE Donation_ID = ?");
            stmt.setInt(1, index);
            stmt.execute();
            System.out.println("Usunięto wpłatę");
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateDonation(String paymentDate, int amount, int personID, int eventID, int installmentID, int index)
    {
        try {
            PreparedStatement stmt  = connection.getConnection().prepareStatement("UPDATE Donation SET Payment_date = ?, Amount = ?, Person_ID = ?, Event_ID = ?, Installment_ID = ? WHERE Donation_ID = ?;");
            stmt.setString(1, paymentDate);
            stmt.setInt(2, amount);
            stmt.setInt(3, personID);
            stmt.setInt(4, eventID);
            stmt.setInt(5, installmentID);
            stmt.setInt(6, index);
            stmt.execute();
            System.out.println("Zaktualizowano wpłatę");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void showDonations() throws SQLException {
        try (Statement stmt = connection.getConnection().createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM Donation;");
            while (rs.next()) {
                int index = rs.getInt("Donation_ID");
                String paymentDate = rs.getString("Payment_date");
                int amount = rs.getInt("Amount");
                int personID = rs.getInt("Person_ID");
                int eventID = rs.getInt("Event_ID");
                int installmentID = rs.getInt("Installment_ID");
                System.out.println(index + ", " + paymentDate + ", " + amount + ", " + personID + ", " + eventID + ", " + installmentID);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addInstallment(int eventID, int number, String paymentDeadline)
    {
        try {
            PreparedStatement stmt  = connection.getConnection().prepareStatement("INSERT INTO Installment (Event_ID, Number, Payment_deadline) VALUES (?,?,?);");
            stmt.setInt(1, eventID);
            stmt.setInt(2, number);
            stmt.setString(3, paymentDeadline);
            stmt.execute();
            System.out.println("Dodano ratę");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteInstallment(int index) throws SQLException {
        try{
            PreparedStatement stmt = connection.getConnection().prepareStatement("DELETE FROM Installment WHERE Installment_ID = ?");
            stmt.setInt(1, index);
            stmt.execute();
            System.out.println("Usunięto ratę");
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateInstallment(int eventID, int number, String paymentDeadline, int index)
    {
        try {
            PreparedStatement stmt  = connection.getConnection().prepareStatement("UPDATE Installment SET Event_ID = ?, Number = ?, Payment_deadline = ? WHERE Installment_ID = ?;");
            stmt.setInt(1, eventID);
            stmt.setInt(2, number);
            stmt.setString(3, paymentDeadline);
            stmt.setInt(4, index);
            stmt.execute();
            System.out.println("Zaktualizowano ratę");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void showInstallments() throws SQLException {
        try (Statement stmt = connection.getConnection().createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM Installment;");
            while (rs.next()) {
                int index = rs.getInt("Installment_ID");
                int eventID = rs.getInt("Event_ID");
                int number = rs.getInt("Number");
                String paymentDeadline = rs.getString("Payment_deadline");
                System.out.println(index + ", " + eventID + ", " + number + "," + paymentDeadline);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void showUnpaidInstallments(){
        try (Statement stmt = connection.getConnection().createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT Installment.Installment_ID, Installment.Event_ID, Installment.Number, Person_ID FROM Installment " +
                                                 "LEFT JOIN Donation ON Installment.Installment_ID = Donation.Installment_ID;");


            while (rs.next()) {
                int installmentID = rs.getInt("Installment_ID");
                int eventID = rs.getInt("Event_ID");
                int number = rs.getInt("Number");
                int personID = rs.getInt("Person_ID");
                System.out.println(personID);
                System.out.println(installmentID + ", " + eventID + ", " + number + ", " + personID);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
