package application;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class SearchCustomer extends CustomerStage {
	
	public SearchCustomer() {
		find.setVisible(true);
		this.setTitle("Searching Customers");
		this.setScene(new Scene(getPane(), 750, 550));
		this.show();
	}
	
	protected GridPane getPane() {
		GridPane root = super.getPane();
		textBox.getChildren().add(tPlan);
		return root;
	}
}
