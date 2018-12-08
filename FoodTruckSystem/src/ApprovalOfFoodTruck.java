package Registration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class ApprovalOfFoodTruck
{
  public static String LoginId;
  public static int TruckId;
  public static String TruckName;
  public static String ApprovedFlag;
  private static String Sql2;
  public static int i = 0;
  public static int j = 0;
  public int []arrayofApproval = new int[999];
  public int[] truck = new int[999];
  String[] login = new String[999];
  boolean CharacterFlag = false;
  String RegularExpression = "[A-Za-z3-9]";
  private String ExitOption;
  
  public int Approval()
  {
    try
    {
      Scanner Sc = new Scanner(System.in);
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/food_truck_system", "root", 
        "Abhi08338@");
      Statement stmt = con.createStatement();
      
      ResultSet res = stmt.executeQuery("SELECT COUNT(*) FROM truck_owner_tab where ApprovedFlag = 0");
      int count = 0;
      while (res.next()) {
        count = res.getInt(1);
      }
      System.out.println("\n\tYou Have " + count + " Rows To Approve");
      if (count >= 1)
      {
        do
        {
          CharacterFlag = false;
          String sql = "SELECT * FROM truck_owner_tab where ApprovedFlag = 0;";
          ResultSet resultSet = stmt.executeQuery(sql);
          while (resultSet.next())
          {
            do
            {
              i += 1;
              LoginId = resultSet.getString("LoginId");
              TruckId = resultSet.getInt("TruckId");
              TruckName = resultSet.getString("TruckName");
              System.out.println("\n\tPress 1 to Approve and 2 to Unapprove");
              System.out.println(
                "\n\t LoginId : " + LoginId + "\tTruckId :" + TruckId + "\tTruckName :" + TruckName);
              
              ApprovedFlag = Sc.nextLine();
              if (ApprovedFlag.equals("1"))
              {
                arrayofApproval[i] = 1;
                CharacterFlag = false;
              }
              else if (ApprovedFlag.equals("2"))
              {
                arrayofApproval[i] = 2;
                CharacterFlag = false;
              }
              else
              {
                System.out.println("Do Not Enter Other Characters...Enter Again");
                CharacterFlag = true;
                i = 0;
              }
            } while (CharacterFlag);
            login[i] = LoginId;
            truck[i] = TruckId;
          }
        } while (CharacterFlag);
        for (j = 0; j <= i; j += 1)
        {
          Sql2 = 
            "UPDATE truck_owner_tab SET ApprovedFlag = '" + arrayofApproval[j] + "' where LoginId ='" + login[j] + "' and TruckId ='" + truck[j] + "' ";
          stmt.executeUpdate(Sql2);
          if (i == j)
          {
            System.out.println("\n\tThere Are Not More Approvals Pending...");
            System.out.println("\n\tPress Any Number to Go Back To Main Menu");
            ExitOption = Sc.next();
          }
        }
      }
      else
      {
        System.out.println("\n\tPress Any Number to Go Back To Main Menu");
        ExitOption = Sc.next();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return 1;
  }
}
