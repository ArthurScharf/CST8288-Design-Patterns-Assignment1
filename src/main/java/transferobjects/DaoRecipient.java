/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transferobjects;



/**
 *
 * @author Arthur Scharf
 * 
 * A generalization for transfer objects. 
 * Each instance of this class is a row in a database
 */
public class DaoRecipient 
{
    private int id;
    
    private String name;
    
    private int year;
    
    private String city;
    
    private String category; // should be an enum
    
    /*
    Col 1 (   AwardID        INT    java.lang.Integer )
    Col 2 (      Name    VARCHAR     java.lang.String )
    Col 3 (      Year        INT    java.lang.Integer )
    Col 4 (      City    VARCHAR     java.lang.String )
    Col 5 (  Category    VARCHAR     java.lang.String )
    */
    
    
    /*
    TODO: Should this maybe take a ResultSet instead? Probably not. The DAO should use this object in a fairly tightly coupled way
    */
    public DaoRecipient(int id, String name, int year, String city, String category)
    {
        this.id = id;
        this.name = name;
        this.year = year;
        this.city = city;
        this.category = category;
    }
    
    
    // -- Getters & Setters -- //
    public int getId() { return this.id; }
    public void setId(int id) { this.id = id;}
    public String getName() { return this.name; };
    public void setName(String name) { this.name = name; }
    public int getYear() { return this.year; }
    public void setYear(int year) { this.year = year; }
    public String getCity() { return this.city; }
    public void setCity(String city) { this.city = city; }
    public String getCategory() { return this.category; }
    public void setCategory(String category) { this.category = category; }
    

    /*
    Returns a stringified version of the object.
    */
    @Override
    public String toString()
    {
        return "";
    }
    
    
}
