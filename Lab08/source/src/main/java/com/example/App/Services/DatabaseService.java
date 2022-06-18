package com.example.App.Services;

import com.example.App.database.DAO;
import com.techprimers.spring_boot_soap_example.Database;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
public class DatabaseService {
    public static DAO databaseDAO;
    public static Database database;
    private static final Map<String, String> records = new HashMap<>();
    @PostConstruct
    public void initialize() throws SQLException {
        databaseDAO = new DAO();
        database = new Database();
        database.setData(databaseDAO.showPersons());

    }


    public Database getPersons() throws SQLException {
        return database;
    }
}
