/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;

import java.sql.ResultSet;


/**
 *
 * @author Arthur Scharf
 * 
 * An singleton class for accessing the recipients in the database. Encapsulates the access layer.
 * Exposes an API to control data access
 */
public class DataAccessObject
{
    static DataAccessObject INSTANCE;
    
    private String mostRecentInsertion; // TODO: this is a placeholder until I decide if I'll use a DTO to work with record rows
    
    private DataAccessObject()
    {
        // TODO
    }
    
    public static DataAccessObject getInstance()
    {
        if (INSTANCE == null)
        {
            return new DataAccessObject();
        } else {
            return INSTANCE;
        }
    }
    
    /*
        Returns all records in the recipient table
    */
    public ResultSet getAllRecipients()
    {
        // todo
        return null;
    }
    
    /*
    inserts passed argument as new record for recipient table. Returns updated table
    */
    public ResultSet insertNewRecord()
    {
        // todo
        return null;
    }
    
    /*
    removes most recently inserted row and returns recipient table
    */
    public ResultSet rollbackInsert()
    {
        // todo
        return null;
    }
}
