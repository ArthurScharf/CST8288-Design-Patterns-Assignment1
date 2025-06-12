/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;


import java.io.InputStream;
import java.io.IOException;

import java.sql.Connection;
import java.util.Properties;
import java.sql.DriverManager; // Managers the unique driver for each implementation of the jdbc


import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;

/**
 * @description Singleton data source for recipients. Protects against creation of multiple connections
 * @author Arthur Scharf
 */
public enum RecipientDataSource
{    
    INSTANCE;
    
    /*
      While these aren't declared as static & final, they belong to each
      enum value declared as part of the class.
      These values are instances of the enum, which means they're each constructed 
      with instances of these attributes.
      Their private visibility, combined with the enforced existance within the
      constructor, makes them effectively static & final
    */
    
    /**
     * @Description Connection to JDBC database. Effectively static final
     * 
     * @see RecipientDataSource()
     */
    public Connection connection;
    
     /**
     * @Description property attributes. [0] == url, [1] == username, [2] == password
     * 
     * @see RecipientDataSource(), openPropertyFile()
     */
    private String[] info;
    
    
    /*
        Doing this in the constructor is necassary to make this thread-safe.
        Instantiating it anywhere else runs the risk of race conditions.
    
        Throws exceptions because failure to instantiate a connection necessitate
        a method that creates a connection which can be called elsewhere,
        which isn't threadsafe.
        
        Throws unchecked exception to safely crash the program when db connection fails.
        DB and properties are critical for the program to work
    */
    private RecipientDataSource() throws ExceptionInInitializerError
    {        
        info = new String[3];
        try {
            openPropertyFile(info);
            connection = DriverManager.getConnection(info[0], info[1], info[2]);
        } catch (SQLException e) {
            throw new ExceptionInInitializerError("Critical failure establishing database connection: " + e.getMessage());
        } catch (IOException e) {
            throw new ExceptionInInitializerError("Critical failure opening `property` file: " + e.getMessage());
        }
    }
   
    
    /* This code was heavily inspired by the code from the lectures */
    private static void openPropertyFile(String[] info) throws IOException
    {
        Properties properties = new Properties();
        
        try (InputStream in = Files.newInputStream(Paths.get("src/main/java/database.properties")))
        {
            properties.load(in);
        } catch (IOException e)
        {
            throw e;
        }
        
        String url = properties.getProperty("jdbc.url");
        String username = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");
        
        info[0] = url;
        info[1] = username;
        info[2] = password;
    }
}
