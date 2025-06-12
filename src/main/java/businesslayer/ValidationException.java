/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businesslayer;

/**
 * @File   Validation.java
 * @author Stanley Pieda
 * @Date 2015
 * @Description Borrowed from the course notes. I, Arthur Scharf, didn't write this
 */
public class ValidationException extends Exception 
{
    public ValidationException() {
        super("Data not in valid form");
    }
    public ValidationException(String message)
    {
        super(message);
    }
    public ValidationException(String message, Throwable throwable)
    {
        super(message, throwable);
    }
    public ValidationException(Throwable throwable)
    {
        super(throwable);
    }
}
