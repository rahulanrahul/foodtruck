package FoodTruckSystem.Project;

import java.sql.SQLException;

public class Main {
	
	public static void main(String[] args) {
		GiveFeedback Feedback = new GiveFeedback();
		try {
			try {
				Feedback.CustomerFeedback();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		
		}
		
		GetFeedback Feedbackdata = new GetFeedback();
		try {
			try {
				Feedbackdata.FeedbackData();
			}catch (SQLException e) {
				
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
}

}
