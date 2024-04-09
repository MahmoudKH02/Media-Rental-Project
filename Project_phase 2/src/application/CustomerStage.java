package application;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CustomerStage extends Stage{
	
	protected TextField tId = new TextField();
	protected TextField tName = new TextField();
	protected TextField tAddress = new TextField();
	protected TextField tMobile = new TextField();
	protected TextField tPlan = new TextField();
	protected VBox textBox = new VBox(25);
	
	protected ImageView findImage = new ImageView(new Image("C:\\images\\find.png"));
	protected Button find = new Button("Find");	
	protected Button back = new Button("Back");
	
	public CustomerStage() {
		find.setVisible(false);
		tName.setEditable(false);
		tAddress.setEditable(false);
		tMobile.setEditable(false);
		tPlan.setEditable(false);
		find.setOnAction(e -> {
			String[] s = Main.searchCustomer(tId.getText()).split("-");
			if(s.length > 1) {
				tName.setText(s[2]);
				tAddress.setText(s[3]);
				tMobile.setText(s[4]);
				tPlan.setText(s[5]);
			}
		});
		back.setOnAction(e ->{
			this.close();
			new CustomerMenu();
		});
	}
	
	protected GridPane getPane() {
		GridPane root = new GridPane();
		Label id = new Label("Customer ID:");
		Label name = new Label("Customer Name:");
		Label address = new Label("Customer Address:");
		Label mobile = new Label("Customer Mobile:");
		Label plan = new Label("Customer Plan:");
		ImageView backImage = new ImageView(new Image("C:\\images\\turn-back.png"));
		
		VBox labelBox = new VBox(35);
		
		id.setFont(new Font(17));
		name.setFont(new Font(17));
		address.setFont(new Font(17));
		mobile.setFont(new Font(17));
		plan.setFont(new Font(17));

		tId.setPrefSize(250, 35);
		tName.setPrefSize(250, 35);
		tAddress.setPrefSize(250, 35);
		tMobile.setPrefSize(250, 35);
		tPlan.setPrefSize(250, 35);
		
		find.setPrefSize(120, 60);
		find.setGraphic(findImage);
		find.setGraphicTextGap(10);
		find.setFont(new Font(15));
		
		back.setPrefSize(120, 60);
		back.setGraphic(backImage);
		back.setGraphicTextGap(10);
		back.setFont(new Font(15));
		
		labelBox.getChildren().addAll(id, name, address, mobile, plan);
		textBox.getChildren().addAll(tId, tName, tAddress, tMobile);
		root.setHgap(50);
		root.setVgap(50);

		root.setAlignment(Pos.CENTER);
		GridPane.setRowIndex(labelBox, 0);
		GridPane.setColumnIndex(labelBox, 0);
		GridPane.setRowIndex(textBox, 0);
		GridPane.setColumnIndex(textBox, 1);
		GridPane.setRowIndex(find, 1);
		GridPane.setColumnIndex(find, 1);
		GridPane.setRowIndex(back, 1);
		GridPane.setColumnIndex(back, 2);
		
		root.getChildren().addAll(labelBox, textBox, back, find);
		return root;
	}
	
}//end class
