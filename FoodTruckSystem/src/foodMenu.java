import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.*;

public class foodMenu {

	int TruckId;
	String FoodType;
	String Dish;
	String Price;

	public void foodMenu() throws ClassNotFoundException, SQLException {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/food_truck_schema?useSSL=false",
				"root", "lenovopc.3");
		Statement stmt = con.createStatement();
		PreparedStatement preparedStmt;
		int choice, count;
		int Flag;
		Scanner Sc = new Scanner(System.in);
		do {
			do {
			System.out.println("Enter the Food Type");
			System.out.println("Press 1 for German ");
			System.out.println("Press 2 for Indian");
			System.out.println("Press 3 for Chineese");
			System.out.println("Press 4 for Continental");
			choice = Sc.nextInt();
			Flag = 0;
			//if(choice.matches("\\d+")) {
			if((choice == 1) || (choice == 2) || (choice == 3) || (choice == 4)) {
						
				switch (choice) {

				case 1:
					FoodType = "German";
					break;

				case 2:
					FoodType = "Indian";
					break;

				case 3:
					FoodType = "Chineese";
					break;

				case 4:
					FoodType = "Continental";
					break;
//				default:
//					System.out.println("Wrong Entry");
//					System.out.println("Please try again");
//					Flag = 1;
//					break;
				}		}
				else {System.out.println("Wrong Entry \n Please input correct Values"); Flag=1;}
			} while (Flag > 0);
			System.out.println("Enter the dish to be entered");
			Dish = Sc.next();
			System.out.println("Enter the price for the dish");
			Price = Sc.next();
			// stmt = con.createStatement();
			String Query = "INSERT INTO truckmenu_tab(`TruckId`, `FoodType`,`Dish`,`Price`) VALUES (?,?,?,?)";
			preparedStmt = con.prepareStatement(Query);
			preparedStmt.setString(1, "4");
			preparedStmt.setString(2, FoodType);
			preparedStmt.setString(3, Dish);
			preparedStmt.setString(4, Price);
			preparedStmt.execute();
			System.out.println("Do you want to enter more details \nPress 1 to Add More \nPress 0 to exit");
			count = Sc.nextInt();
		} while (count == 1);
		con.close();
	}
}
