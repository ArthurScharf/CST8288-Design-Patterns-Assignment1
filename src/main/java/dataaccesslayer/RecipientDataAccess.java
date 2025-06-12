/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;

import java.sql.SQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;

import transferobjects.RecipientTransferObject;
import transferobjects.TableMetaDataTransferObject;



/**
 * @author Arthur Scharf
 * 
 * An Singleton implemented using the Java Enum approach. This approach
 * guarantees thread safety because enums are static and implicitly created at runtime
 * 
 * The available values that an enum can take are created statically.
 */
public enum RecipientDataAccess implements DAOInterface
{  
    /* Unlike in C++, this is an instance of the class. It's also a static instance.
       By having only a single instance, we force all variables of this classes type to 
       point to this instance, which is public static final and created at the beginning of run time by the jvm
    */
    INSTANCE;
           
    /**
     * @return TransferObjectContainer with all records
     * @throws SQLException 
     */
    @Override
    public ArrayList<RecipientTransferObject> getAll() throws SQLException
    {
        String query = "SELECT * FROM recipients";
        // This will always exist because of the enum implementation strategy for the connection
        Connection conn = RecipientDataSource.INSTANCE.connection;
        
        ArrayList<RecipientTransferObject> dtos = new ArrayList<>();
                
        try ( Statement stmt = conn.createStatement(); ResultSet results = stmt.executeQuery(query); )
        {
            // -- Constructing Transfer Objects -- //
            while (results.next()) // Remember that index 0 is ignored. This is why we can do .next() instead of .hasNext()
            {
                dtos.add(new RecipientTransferObject(
                    results.getInt("awardID"), 
                    results.getString("name"), 
                    results.getInt("year"), 
                    results.getString("city"), 
                    results.getString("category")) 
                );
            }//~ while(results.next())
        } catch (SQLException e) {
            throw new SQLException("Exception when retrieving all recipients", e);
        }   
        return dtos;
    }
    
    
    /**
     * @param dto Recipient DTO to be inserted
     * @return TransferObjectContainer with freshly inserted records
     * @throws SQLException 
     */
    @Override
    public ArrayList<RecipientTransferObject> insertNew(RecipientTransferObject dto) throws SQLException
    {
        String query = "INSERT INTO recipients (name, year, city, category)"
                + "VALUES (?, ?, ?, ?)";
        
        Connection conn = RecipientDataSource.INSTANCE.connection;
        
        try ( PreparedStatement stmt = conn.prepareStatement(query); ) 
        {            
            // -- Set Statement Parameters -- //
            stmt.setString(1, dto.getName());
            stmt.setInt(2, dto.getYear());
            stmt.setString(3, dto.getCity());
            stmt.setString(4, dto.getCategory()); 
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Exception inserting new record", e);
        }
        
        return getAll();
    }
    
    /**
     * @return TransferObjectContainer with freshly rolled back records
     * @throws SQLException
     */
    @Override
    public ArrayList<RecipientTransferObject> rollbackInsert() throws SQLException
    {
        String query = "DELETE FROM recipients ORDER BY awardID DESC LIMIT 1;";
        
        Connection conn = RecipientDataSource.INSTANCE.connection;
        
        try ( PreparedStatement stmt = conn.prepareStatement(query); ) 
        {    
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Exception removing most recent insert", e);
        }
        return getAll();
    }
    
    public TableMetaDataTransferObject getRecipientMetaData() throws SQLException
    {
        return new TableMetaDataTransferObject(RecipientDataSource.INSTANCE.connection.getMetaData(), "recipients");
    }

}
