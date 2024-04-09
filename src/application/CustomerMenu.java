package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomerMenu extends Stage {
	BorderPane root = new BorderPane();
	Button add = new Button("Add new Customer");
	Button delete = new Button("Delete Customer");
	Button update = new Button("Update Info About Customer");
	Button search = new Button("Search a Customer By ID");
	Button mainPage = new Button("Return To Main Page");
	VBox buttons = new VBox(15);
	
	public CustomerMenu() {
		this.setTitle("Customer Menu");
		buttons.getChildren().addAll(add, delete, update, search, mainPage);
		root.setCenter(buttons);
		buttons.setAlignment(Pos.CENTER);

		add.setOnAction(e -> {
			this.close();
			new AddCustomer();
		});
		delete.setOnAction(e -> {
			this.close();
			new DeleteCustomer();
		});
		update.setOnAction(e -> {
			this.close();
			new UpdateCustomer();
		});
		search.setOnAction(e -> {
			this.close();
			new SearchCustomer();
		});
		mainPage.setOnAction(e -> {
			this.close();
			new Main().start(new Stage());
		});
		this.setScene(new Scene(root, 400, 400));
		this.show();
	}
}

