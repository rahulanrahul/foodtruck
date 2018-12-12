package opppp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
 public class ChangeTruckInformation {
    public static String NewDetails;
	public static String TruckID;
    public static String FoodType;
    public static String Dish;
	public static String Price;
	public static void main(String[] args) throws SQLException
	{
     Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/food_truck_schema","root","desertstrom47007");
     
     Scanner scan = new Scanner(System.in);
     System.out.println("Enter the Truck ID");
     TruckID = scan.next();
     System.out.println("Enter the type of details you want to change");
     NewDetails = scan.next();
     {
    
     if (NewDetails.equals("FoodType"))
        {
     	 System.out.println("You have selected");
     	 System.out.println(NewDetails);
     	 System.out.println("Enter the new Food Type");
     	 FoodType = scan.next();
     	 System.out.println("Your Details have been successfully updated");
        }
     else if (NewDetails.equals("Dish"))
        {
    	  System.out.println("You have selected");
     	  System.out.println(NewDetails);
     	  System.out.println("Enter the new Dish");
     	  Dish = scan.next();
     	  System.out.println("Your Details have been successfully updated");
        }
     else if (NewDetails.equals("Price"))
        {
    	 System.out.println("You have selected");
    	 System.out.println(NewDetails);
    	 System.out.println("Enter the new Price");
    	 Price = scan.next();
    	 System.out.println("Your Details have been successfully updated");
        }
      }
	}
	
 }
