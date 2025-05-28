package MainPkg;

import dataaccesslayer.DataAccessImpl;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData; 
import java.sql.DriverManager; // Managers the unique driver for each implementation of the jdbc


import java.util.Random;
import java.security.SecureRandom;



/**
 * @author Arthur Scharf
 * @purpose A lab assignment exploring user input for DB interaction
 * @date 5/19/2025
 */
public class Main 
{
    public static void main(String[] args) 
    {
        // ---- ---- ---- ---- --- ---- ---- ---- ---- //
        // ---- ---- 1. Establish Connection ---- ---- //
        // ---- ---- ---- ---- --- ---- ---- ---- ---- //
        
        // ---- 1.1. Creating Properties Object ---- //
        Properties properties = new Properties();

        try (InputStream in = Files.newInputStream(Paths.get("src/main/java/database.properties")))
        {
            properties.load(in); // Initializes the properties data object with information stored in database.properties
        } catch (IOException e) {
            e.printStackTrace();
        }//~ catch
        
        String url = properties.getProperty("jdbc.url");
        String username = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");
    
        
        // ---- 1.2. Connect to Database ---- //
        
        String stmt = "SELECT * FROM recipients WHERE Year = ?";
        
        Random rand = new SecureRandom();
        
        
        
        /**/
        try(
                Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement preparedStatement = connection.prepareStatement(stmt); )
        {
            int randomYear = rand.nextInt(34) + 1987;
            
            preparedStatement.setInt(1, randomYear);
            
            // QUESTION: Will this code still user the outer-most catch block while still closing the resources?
            try (ResultSet resultSet = preparedStatement.executeQuery())
            {
                ResultSetMetaData metaData = resultSet.getMetaData();
                int numColumns = metaData.getColumnCount();
                
                
                // -- Handle Empty Result -- //
                if (!resultSet.isBeforeFirst())
                {
                    System.out.println("No results for year " + randomYear);
                    return;
                }
                
                
                // -- Printing Meta Data -- //
                System.out.print("---- Meta data ----\n");
                for (int i = 1; i <= numColumns; i++)
                {
                    System.out.printf("Col %d (%10s %10s %20s )\n", i, metaData.getColumnLabel(i), metaData.getColumnTypeName(i), metaData.getColumnClassName(i));
                }
                
                
                // -- Printing the columns -- //
                System.out.printf("\n---- Recipients for: %4s ----\n", randomYear);
                for (int i = 1; i <= numColumns; i++)
                {
                    System.out.printf("%-20s" , metaData.getColumnLabel(i));
                }
                System.out.println();
                while (resultSet.next()) // Remember that index 0 is ignored. This is why we can do .next() instead of .hasNext()
                {
                    for (int i = 1; i <= numColumns; i++) // Remember we start at 1 and ends AT right bound
                    {
                        System.out.printf("%-20s", resultSet.getObject(i)); // `\t` is tabular. Makes sure space exists
                    }
                    System.out.println();
                }
                
            }
            
        } catch (SQLException e)
        {
            e.printStackTrace(); // Why the warning?
        }

    }
}
