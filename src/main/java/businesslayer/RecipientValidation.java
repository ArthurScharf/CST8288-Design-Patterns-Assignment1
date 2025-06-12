/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslayer;

import transferobjects.RecipientTransferObject;


/**
 *
 * @author User
 */
public class RecipientValidation 
{
    private static final int NAME_MAX_LENGTH     = 40;
    private static final int CITY_MAX_LENGTH     = 30;
    private static final int CATEGORY_MAX_LENGTH = 40;

    /**
     * @Description Formats the received name correctly.
     * @param obj 
     */
    protected static void cleanRecipient(RecipientTransferObject obj)
    {
        if (obj.getName() != null)
        {
            String first;     // Must always have at least one name
            String last = ""; // Might have no last name
            // Finding space between first name and last name(s)
            int idx = obj.getName().indexOf(" ");
            if (idx != -1)
            {
                // Multi-part name
                first = "; " + obj.getName().substring(0, idx);
                last  = obj.getName().substring(idx + 1);
            } else {
                first = obj.getName();
            }
            obj.setName(last + first);
        }
    }
    
    protected static void validateRecipient(RecipientTransferObject obj) throws ValidationException
    {
        validateString(obj.getName(), "Name", NAME_MAX_LENGTH, false);
        validateInt(obj.getYear(), "Year", 0, 0, true, false);
        validateString(obj.getCity(), "City", CITY_MAX_LENGTH, false);
        validateString(obj.getCategory(), "Category", CATEGORY_MAX_LENGTH, false);
    }
    
    /**
     *  Uses parameters to check for string validity
     * 
     *  Borrowed from Gustaves example code on Brightspace 
     */
    private static void validateString(String value, String valueName, int maxLength, boolean bNullAllowed) throws ValidationException
    {
        if (value == null && !bNullAllowed)
        {
            throw new ValidationException(valueName + " cannot be null");
        }
        else if (value != null && value.length() > maxLength)
        {
            throw new ValidationException(valueName + " cannot be greater than " + maxLength);
        }
        else if (value != null && value.length() == 0)
        {
            throw new ValidationException(valueName + " cannot be whitespace");
        }
    }
    
    private static void validateInt(int value, String valueName, int min, int max, boolean bUseMin, boolean bUseMax) throws ValidationException
    {
        if (bUseMin == false && bUseMax == false)
        {
            throw new ValidationException(valueName + ": at least one of bUseMin & bUseMax must be true");
        } else if (bUseMin && value < min)
        {
            throw new ValidationException(valueName + " is below minimum value");
        } else if (bUseMax && value > max)
        {
            throw new ValidationException(valueName + " is above maximum");
        }
    }
}
