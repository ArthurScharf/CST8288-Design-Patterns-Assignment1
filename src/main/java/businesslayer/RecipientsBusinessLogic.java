/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslayer;


import dataaccesslayer.RecipientDataAccess;
import transferobjects.RecipientTransferObject;
import java.sql.SQLException;
import transferobjects.TableMetaDataTransferObject;
import java.util.ArrayList;

/**
 * @Description A business class for returning recipients. This class has no state so it can be a collection of static methods
 * @author Arthur Scharf
 */
public class RecipientsBusinessLogic 
{       
    /**
     * @return List of all recipients
     */
    public static ArrayList<RecipientTransferObject> getAll()
    {
        try {
            return RecipientDataAccess.INSTANCE.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    
    /**
     * @param obj Object to be inserted
     * @return All recipients, including the one that was just inserted
     */
    public static ArrayList<RecipientTransferObject> insertNew(RecipientTransferObject obj)
    {
        
        RecipientValidation.cleanRecipient(obj);
        
        try {
            RecipientValidation.validateRecipient(obj);
        } catch (ValidationException e)
        {
            e.printStackTrace();
        }
        try {
            return RecipientDataAccess.INSTANCE.insertNew(obj);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    
    /**
     * @Description Removes the most recently inserted recipient
     * @return list of all recipients after the removal
     */
    public static ArrayList<RecipientTransferObject> rollbackInsert()
    {
        try {
            return RecipientDataAccess.INSTANCE.rollbackInsert();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
   
    
    public static TableMetaDataTransferObject getRecipientsMetaData()
    {
        TableMetaDataTransferObject dto = null;
        try {
            dto = RecipientDataAccess.INSTANCE.getRecipientMetaData();        
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return dto;
    }
    
    // Returns the last result as a string // , 
    public static String resultsToString(ArrayList<RecipientTransferObject> dtos)
    {
        String output = "";
        for (RecipientTransferObject dto : dtos)
        {
            output += dto + "\n";
        }
        return output;
    }
}
