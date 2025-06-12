/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transferobjects;


import java.util.ArrayList;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import java.sql.SQLException;
        
        
/**
 * @description A simple general class for retrieving and storing column metadata information 
 *              for a table in a database
 * @author Arthur Scharf
 */
public class TableMetaDataTransferObject 
{
    /**
     * @description Labels for each column in the table used to construct an instance
     */
    private final ArrayList<String> colLabels = new ArrayList<>();
    /**
     * @description SQL data types for each column in the table used to construct an instance
     */
    private final ArrayList<String> colSQLTypes = new ArrayList<>();
    /**
     * @description Java data types for each column in the table used to construct an instance
     */
    private final ArrayList<String> colJavaTypes = new ArrayList<>();
    

    /**
     * @param metaData meta data for a database
     * @param tableName Name of table we'll use to construct this object
     */
    public TableMetaDataTransferObject(DatabaseMetaData metaData, String tableName) throws SQLException
    {
        // A set of columns that belong to the table with name tableName
        ResultSet columns = metaData.getColumns(null, null, tableName, null);
        while (columns.next())
        {
           colLabels.add(columns.getString("COLUMN_NAME"));
           String sqlType = columns.getString("TYPE_NAME");
           colSQLTypes.add(sqlType);
           colJavaTypes.add(sqlType.equals("VARCHAR") ? "String" : "int"); // Workaround mapping because our program is built for a specific table
        }
    }
    
    @Override
    public String toString()
    {
        String output = "";
        for (int i = 0; i < colLabels.size(); i++)
        {
            output += String.format("%-10s | %-10s | %-10s\n", colLabels.get(i), colSQLTypes.get(i), colJavaTypes.get(i));
        }
        return output;
    }
}
