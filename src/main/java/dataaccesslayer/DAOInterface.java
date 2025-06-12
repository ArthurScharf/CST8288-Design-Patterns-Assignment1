/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dataaccesslayer;
import java.sql.SQLException;
import transferobjects.RecipientTransferObject;

import java.util.ArrayList;

/**
 * @author Arthur Scharf
 */
public interface DAOInterface 
{
    /* Returns all instances of whichever entity this DAO is managing */
    public ArrayList<RecipientTransferObject> getAll() throws SQLException;
    
    /* Returns all instance of whichever entity this DAO is manager after inserting the new element */
    public ArrayList<RecipientTransferObject> insertNew(RecipientTransferObject dto) throws SQLException;
    
    /* rollsback the mostly recently made insert */
    public ArrayList<RecipientTransferObject> rollbackInsert() throws SQLException;
}
