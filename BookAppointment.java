package FoodTruckSystem.Project;

import java.util.Date;
import java.text.SimpleDateFormat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.mysql.cj.jdbc.result.ResultSetImpl;

public class BookAppointment {

	public String Input;
	boolean InputMismatch;
	boolean BulkOrder;
	boolean ReplyMismatch;
	boolean Mismatch;
	public String sql;
	public String FromDate;
	public java.util.Date FromDateD;
	public String ToDate;
	public String Reply;

	public String CustomerId = "1";
	public int BookId;
	public String TimeSlot;
	public int TruckId = 1;
	public String Location;
	public String DateValue;
	public String Date;
	public int BulkOrderId;
	public String NoOfPerson;
	public String Dish;

	public void CustomerInput() throws ClassNotFoundException, SQLException {

		do {
			InputMismatch = false;
			System.out.println("Would you like to book an appointment for your order: Y or N ?");
			Scanner Sc = new Scanner(System.in);
			Input = Sc.nextLine().toUpperCase();

			if ((Input.equals("Y"))) {
				CustomerAppointment();
			} else if ((Input.equals("N"))) {
				System.exit(0);
			} else {
				System.out.println("Invalid Input, please enter either Y or N");
				InputMismatch = true;
			}
		} while (InputMismatch);

	}

	public void CustomerAppointment() throws ClassNotFoundException, SQLException {

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdata", "root", "Shams*97");
		java.sql.Statement stmt = con.createStatement();
		PreparedStatement preparedStmt;
		Scanner Sc = new Scanner(System.in);

		System.out.println("Please enter the below details to book an appointment:");

		do {
			InputMismatch = false;

			int i = 0;
			String sql = "SELECT TimeSlot1,TimeSlot2,TimeSlot3, Location1, Location2, Location3 FROM projectdata.food_truck_tab where TruckId='"
					+ TruckId + "'";
			ResultSet RS = stmt.executeQuery(sql);
			while (RS.next()) {

				if ((RS.getString(1) != null) && (RS.getString(4) != null)) {
					i++;
					System.out.println(
							"Select " + i + " for TimeSlot " + RS.getString(1) + " Location " + RS.getString(4));
				}
				if ((RS.getString(2) != null) && (RS.getString(5) != null)) {
					i++;
					System.out.println(
							"Select " + i + " for TimeSlot " + RS.getString(2) + " Location " + RS.getString(5));
				}
				if ((RS.getString(3) != null) && (RS.getString(6) != null)) {
					i++;
					System.out.println(
							"Select " + i + " for TimeSlot " + RS.getString(3) + " Location " + RS.getString(6));
				}
			}
			TimeSlot = Sc.nextLine();

			switch (TimeSlot) {

			case "1":
				break;

			case "2":
				break;

			case "3":
				break;

			default:
				InputMismatch = true;
				System.out.println("Invalid input, please try again");
				break;
			}
		} while (InputMismatch);

		boolean IsNotValid = false;
		String sql = "SELECT FromDate, ToDate FROM projectdata.food_truck_tab where TruckId='" + TruckId + "'";
		ResultSet RS = stmt.executeQuery(sql);
		while (((ResultSetImpl) RS).next()) {
			FromDate = RS.getString(1);
			ToDate = RS.getString(2);
		}

		do {
			System.out.println("Enter the Date (in the format YYYY-MM-DD) from the following date range:" + FromDate
					+ " to " + ToDate + " ");
			DateValue = Sc.nextLine();
			IsNotValid = false;
			if (DateValue.contains(" ")) {
				IsNotValid = true;
				System.out.println("No spaces allowed. Please try again!!!");
				continue;
			}
			DateFormat FromDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			FromDateFormat.setLenient(false);
			try {
				FromDateFormat.parse(DateValue);
				Date Dt = FromDateFormat.parse(DateValue);
				Date DateFr = FromDateFormat.parse(FromDate);
				Date DateTo = FromDateFormat.parse(ToDate);
				if (!((Dt.after(DateFr)) && (Dt.before(DateTo)))) {
					System.out.println("Date " + DateValue + " is not in Range");
					IsNotValid = true;
				}
				/*
				 * if (((Dt.equals(DateFr)) || (Dt.equals(DateTo)))) { IsNotValid = false; }
				 */
			} catch (ParseException ex) {
				System.out.println("Date " + DateValue + " is not valid");
				IsNotValid = true;
			}
		} while (IsNotValid);

		do {
			InputMismatch = false;
			System.out.println("Do you wish to book a bulk order: Y or N ?");
			Reply = Sc.nextLine().toUpperCase();

			if ((Reply.equals("Y"))) {
				CustomerBulkOrder();
			} else if ((Reply.equals("N"))) {
				System.out.println("Thank you for booking an appointment for your order");
				System.exit(0);
			} else {
				System.out.println("Invalid Input, please enter either Y or N");
				InputMismatch = true;
			}
		} while (InputMismatch);

		String Query = "INSERT INTO customer_appointment_tab (`BookId`,`CustomerId`, `TimeSlot`, `TruckId`, `Location`, `Date`,`BulkOrderId`, `NoOfPerson`,`Dish`) VALUES (?,?,?,?,?,?,?,?,?)";

		preparedStmt = con.prepareStatement(Query);
		preparedStmt.setInt(1, BookId);
		preparedStmt.setString(2, CustomerId);
		preparedStmt.setString(3, TimeSlot);
		preparedStmt.setInt(4, TruckId);
		preparedStmt.setString(5, Location);
		preparedStmt.setString(6, Date);
		preparedStmt.setInt(7, BulkOrderId);
		preparedStmt.setString(8, NoOfPerson);
		preparedStmt.setString(9, Dish);

		preparedStmt.execute();
		con.close();
		
		System.exit(0);
	}

	public void CustomerBulkOrder() throws ClassNotFoundException, SQLException {

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdata", "root", "Shams*97");
		java.sql.Statement stmt = con.createStatement();
		PreparedStatement preparedStmt;
		Scanner Sc = new Scanner(System.in);

		do {
			ReplyMismatch = false;

			System.out.println("Please enter the number of persons for bulk order");
			NoOfPerson = Sc.nextLine();
			int result = Integer.parseInt(NoOfPerson);

			if (result <= 50) {
				ReplyMismatch = true;
				System.out.println("Number of Persons should be more than 50");
			}

		} while (ReplyMismatch);

		do {

			InputMismatch = false;
			int i = 0;
			String sql = "SELECT Dish FROM projectdata.truckmenu_tab where TruckId='" + TruckId + "'";
			ResultSet RS = stmt.executeQuery(sql);
			while (RS.next()) {
				if (RS.getString(1) != null) {
					i++;
					System.out.println("Select " + i + " for Dish " + RS.getString(1));
				}

				Dish = Sc.nextLine();

			}

		} while (InputMismatch);

		System.out.println("Thank you for booking an appointment for your bulk order");
	}

}
