package de.uhd.ifi.se.quizapp.model.init;

import java.sql.SQLException;
import java.util.ArrayList;

import de.uhd.ifi.se.quizapp.model.DataManager;
import de.uhd.ifi.se.quizapp.model.Information;

public class TestDatabase {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		DataManager dataManager = new DataManager();
		
		Information information = dataManager.getInformation(1);
		System.out.println(information.getName());

		ArrayList<Information> informationList = dataManager.getInformation();
		System.out.println(informationList.size());		
	}
}