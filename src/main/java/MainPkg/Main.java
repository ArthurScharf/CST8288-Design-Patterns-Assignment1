package MainPkg;

import businesslayer.RecipientsBusinessLogic;
import businesslayer.ValidationException;
import transferobjects.RecipientTransferObject;
import transferobjects.TableMetaDataTransferObject;

/**
 * @author Arthur Scharf
 * @purpose An assignment testing our understanding of SOLID principles, design patterns, and the JDBC
 * @date 6/10/2025
 */
public class Main 
{    
    /*
    public static void main(String[] args)
    {
        TableMetaDataTransferObject dto  = RecipientsBusinessLogic.getRecipientsMetaData();
        System.out.println(dto);
    }
    */
    
    
    public static void main(String[] args) 
    {
        System.out.println("Returning all records");
        System.out.println("ID    Name                       Year  City             Category        ");
        System.out.println("========================================================================");        
        System.out.println( RecipientsBusinessLogic.resultsToString(RecipientsBusinessLogic.getAll()) );
        
        
        System.out.println("Inserting one new recipient");
        System.out.println("ID    Name                       Year  City             Category        ");
        System.out.println("========================================================================");   
        try {
            String resultStr = RecipientsBusinessLogic.resultsToString(
                    RecipientsBusinessLogic.insertNew(
                            new RecipientTransferObject(
                                "TestNameADD", 
                                9999, 
                                "Ottawa", 
                                "Test Category"
                            )
                    )
            );
            System.out.println(resultStr);
        } catch (ValidationException e)
        {
            e.printStackTrace();
        }
        
        System.out.println("Deleting last recipient");
        System.out.println("ID    Name                       Year  City             Category        ");
        System.out.println("========================================================================");   
        System.out.println(RecipientsBusinessLogic.resultsToString(RecipientsBusinessLogic.rollbackInsert()));
        
        
        System.out.println("\n" + RecipientsBusinessLogic.getRecipientsMetaData());
    }
}


