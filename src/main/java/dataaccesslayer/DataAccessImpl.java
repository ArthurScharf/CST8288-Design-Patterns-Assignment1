/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;

import java.util.ArrayList;

import java.sql.ResultSet;

import transferobjects.DaoRecipient;


/**
 *
 * @author Arthur Scharf
 * 
 * An Singleton implemented using the Java Enum approach. This approach
 * guarantees thread safety because enums are static and implicitly created at runtime
 * 
 * The available values that an enum can take are created statically.
 */
public enum DataAccessImpl implements IDataAccess /*implements DAOInterface TODO */
{  
    /* Unlike in C++, this is an instance of the class. It's also a static instance.
       By having only a single instance, we force all variables of this classes type to 
       point to this instance, which is public static final and created at the beginning of run time by the jvm
    */
    INSTANCE;
    
    
    
    public String mostRecentInsertion = ""; // TODO: this is a placeholder until I decide if I'll use a DTO to work with record rows
    //ArrayList<TransferObject> results = ;
    
    
    /*
        Returns all records in the recipient table
    */
    @Override
    public ArrayList<String> getAll()
    {
        // todo
        return null;
    }
    
    
    /*
    inserts passed argument as new record for recipient table. Returns updated table
    */
    @Override
    public ArrayList<String> insertNew(String newRecord)
    {
        // todo
        return null;
    }
    
    
    /*
    removes most recently inserted row and returns recipient table
    */
    @Override
    public ArrayList<String> rollbackInsert()
    {
        // todo
        return null;
    }
}
