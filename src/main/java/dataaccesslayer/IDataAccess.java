/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dataaccesslayer;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public interface IDataAccess 
{
    public ArrayList<String> getAll();
    
    public ArrayList<String> insertNew(String newRecord);
    
    public ArrayList<String> rollbackInsert();
}
