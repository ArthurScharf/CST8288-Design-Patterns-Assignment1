/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transferobjects;

/**
 * @Description A data transfer object for a recipient entity from the database
 * @author Arthur Scharf
 */
public class RecipientTransferObject 
{
    private int awardID;
    private String name;
    private int year;
    private String city;
    private String category;

    // awardID is created by the database
    public RecipientTransferObject(String name, int year, String city, String category)
    {
       setName(name);
       setYear(year);
       setCity(city);
       setCategory(category);
    }

    // Creating DTO's from database entities that already exist
    public RecipientTransferObject(int awardID, String name, int year, String city, String category)
    {
       setAwardID(awardID);
       setName(name);
       setYear(year);
       setCity(city);
       setCategory(category);
    }
    
    public final void setAwardID(int awardID) {
        this.awardID = awardID;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final void setYear(int year) {
        this.year = year;
    }

    public final void setCity(String city) {
        this.city = city;
    }

    public final void setCategory(String category) {
        this.category = category;
    }

    public int getAwardID() {
        return awardID;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public String getCity() {
        return city;
    }

    public String getCategory() {
        return category;
    }
    
    @Override
    public String toString()
    {
        return String.format("%-4d  %-25s  %-4d  %-15s  %-15s", 
                getAwardID(), 
                getName(), 
                getYear(),
                getCity(),
                getCategory() );
    }
}
