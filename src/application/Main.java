package application;
	
import java.io.*;
import java.util.*;

import dataBase.*;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class Main extends Application {
	
	private static MediaRental manager = new MediaRental();
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setMaximized(true);
			primaryStage.setTitle("Main Menu");
			Pane root = new Pane();
			ImageView view = new ImageView(new Image("C:\\images\\Key.png"));
			ImageView b1View = new ImageView(new Image("C:\\images\\people.png"));
			ImageView b2View = new ImageView(new Image("C:\\images\\television.png"));
			ImageView b3View = new ImageView(new Image("C:\\images\\rent.png"));
			Label l1 = new Label("Media Rental System");
			Button b1 = new Button("Customer");
			Button b2 = new Button("Media");
			Button b3 = new Button("Rent");
			
			b1.setPrefSize(350, 150);
			b2.setPrefSize(350, 150);
			b3.setPrefSize(350, 150);
			b1.setFont(new Font(30));//change the font size
			b2.setFont(new Font(30));
			b3.setFont(new Font(30));
			l1.setFont(new Font(45));
			
			b1.setGraphic(b1View);
			b1.setGraphicTextGap(50);
			b2.setGraphic(b2View);
			b2.setGraphicTextGap(50);
			b3.setGraphic(b3View);
			b3.setGraphicTextGap(50);
			
			VBox buttons = new VBox(35);
			VBox visuals = new VBox(30);
			buttons.getChildren().addAll(b1, b2, b3);
			visuals.getChildren().addAll(view, l1);
			
			buttons.setLayoutX(300);
			buttons.setLayoutY(250);
			visuals.setLayoutX(1200);
			visuals.setLayoutY(145);
			root.getChildren().addAll(buttons, visuals);
			
			b1.setOnAction(e -> {
				new CustomerMenu();
				primaryStage.close();
			});
			b2.setOnAction(e -> {
				new MediaMenu();
				primaryStage.close();
			});
			Scene scene = new Scene(root,800,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}//end start
	
	public static void main(String[] args) throws Exception {
		File file = new File("data.txt");
		String[] s;
		if(!file.exists()) {
			System.out.println("File: " + file + " does not exist");
			System.exit(0);
		}
		Scanner scan = new Scanner(file);
		while(scan.hasNext()) {
			s = scan.nextLine().split("-"); //data in file is separated by '-'
			switch(s[0]) {
			case "Movie": 
				addMedia(s[0], s[1], s[2], s[3], s[4], null);
				break;
			case "Album":
				addMedia(s[0], s[1], s[2], s[3], s[4], s[5]);
				break;
			case "Game": 
				addMedia(s[0], s[1], s[2], s[3], s[4], null);
				break;
			case "Customer": 
				//this is how customer is displayed in the file --> Customer-customerID-customerName-address-customerMobile-plan-Cart[Interested Media]-Rented[Rented Media]
				s[6] = s[6].substring(s[6].indexOf('[')+1, s[6].length()-1);
				s[7] = s[7].substring(s[7].indexOf('[')+1, s[7].length()-1);
				addCustomer(s[1], s[2], s[3], s[4], s[5], s[6], s[7]);
				break;
			default: break;
			}
		}
		scan.close();
		
		launch(args);
		
		PrintWriter filePrint = new PrintWriter(file);
		filePrint.println(manager.getAllMediaInfo());
		filePrint.print(manager.getAllCustomersInfo());
		filePrint.close();
	}//end main
	
	public static void addCustomer(String id, String name, String address, String phone, String plan, String cart, String rented) {
		if(!rented.isEmpty()) {
			String[] tmp = rented.split(", ");
			for(String s : tmp)
				manager.tmpRentedList.add(s);
		}
		if(!cart.isEmpty()) {
			String[] tmp = cart.split(", ");
			for(String s : tmp)
				manager.tmpCartList.add(s);
		}
		manager.addCustomer(id, name, address, phone, plan);
	}
	
	//str can be rating or artist or weight
	public static void addMedia(String type, String code, String title, String copiesAvailable, String str, String songs) {
		try {
			if(type.equalsIgnoreCase("movie"))
				manager.addMovie(code, title, Integer.parseInt(copiesAvailable), str);
			
			else if(type.equalsIgnoreCase("album"))
				manager.addAlbum(code, title, Integer.parseInt(copiesAvailable), str, songs);
			
			else
				manager.addGame(code, title, Integer.parseInt(copiesAvailable), Double.parseDouble(str));
		}
		catch(NumberFormatException ex) {
			new ErrorMessage(ex.toString());
		}
	}
	
	public static boolean deleteMedia(String code) {
		try {
			manager.deleteMedia(code);
			return true;
		}
		catch(ItemNotFoundException ex) {
			new ErrorMessage("Media: " + code + " Was Not Found!!");
		}
		return false;
	}
	
	public static void updateMedia(String type, String code, String title, String copies, String str, String songs) {
		if(deleteMedia(code))
			addMedia(type, title, code, copies, str, songs);
	}
	
	//serach media will return a string of the media info
	public static String searchMedia(String code) {
		try {
			return manager.searchMediaCode(code).toString();
		}catch (ItemNotFoundException ex) {
			new ErrorMessage("Media: " + code + " Was not Found!!");
		}
		return "";
	}
	
	public static void addToCart(String id, String code) throws ItemNotFoundException {
		manager.addToCart(id, code);
	}
	
	public static void removeFromCart() {
		
	}
	
	public static void returnMedia() {
		
	}
	
	public static boolean deleteCustomer(String id) {
		try {
			manager.deleteCustomer(id);
			return true;
		}
		catch(ItemNotFoundException ex) {
			new ErrorMessage("Customer: " + id + " Was Not Found!!");
		}
		return false;
	}
	
	//searchCustomer will return a String of the customer info
	public static String searchCustomer(String id) {
		String info = "";
		try {
			info = manager.searchCustomer(id).toString();
		}
		catch(ItemNotFoundException ex) {
			new ErrorMessage("Customer: " + id + " Was Not Found!!");
		}
		return info;
	}
	
	public static void updateCustomer(String id, String name, String address, String phone, String plan) {
		if(deleteCustomer(id))
			addCustomer(id, name, address, phone, plan, "", "");
	}
	
}//end class
