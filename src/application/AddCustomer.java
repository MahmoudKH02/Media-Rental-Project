package application;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class AddCustomer extends CustomerStage{
	private Button add = new Button("Add");
	private RadioButton limited = new RadioButton("LIMITED");
	private RadioButton unlimited = new RadioButton("UNLIMITED");
	
	private String sPlan;//the plan as a string which is affected by the radio buttons
	
	public AddCustomer() {
		this.setTitle("Adding Customers");
		limited.setDisable(true);
		unlimited.setDisable(true);
		add.setDisable(true);
		
		TextHandler handler = new TextHandler();
		tName.setOnKeyTyped(handler);
		tAddress.setOnKeyTyped(handler);
		tMobile.setOnKeyTyped(handler);
		
		limited.setOnAction(e -> {
			sPlan = "LIMITED";
			add.setDisable(false);
		});
		unlimited.setOnAction(e -> {
			sPlan = "UNLIMITED";
			add.setDisable(false);
		});
		add.setOnAction(e -> {
			Main.addCustomer(tId.getText(), tName.getText(), tAddress.getText(), tMobile.getText(), sPlan, "", "");
			tId.setText("");
			tName.setText("");
			tAddress.setText("");
			tMobile.setText("");
			limited.setSelected(false);
			unlimited.setSelected(false);
		});
		this.setScene(new Scene(getPane(), 750, 550));
		this.show();
	}
	
	protected GridPane getPane() {
		GridPane root = super.getPane();
		ToggleGroup group = new ToggleGroup();
		ImageView addImage = new ImageView(new Image("C:\\images\\add-user.png"));
		
		limited.setToggleGroup(group);
		unlimited.setToggleGroup(group);
		textBox.getChildren().addAll(limited, unlimited);
		
		add.setPrefSize(120, 60);
		add.setGraphic(addImage);
		add.setGraphicTextGap(10);
		add.setFont(new Font(15));
		root.getChildren().add(add);
		GridPane.setRowIndex(add, 1);
		GridPane.setColumnIndex(add, 0);
		
		return root;
	}
	
	class TextHandler implements EventHandler<KeyEvent> {
		@Override
		public void handle(KeyEvent arg0) {
			if(!tId.getText().isEmpty())
				tName.setEditable(true);
			
			if(!tName.getText().isEmpty())
				tAddress.setEditable(true);
			
			if(!tAddress.getText().isEmpty())
				tMobile.setEditable(true);
			
			if(!tMobile.getText().isEmpty()) {
				limited.setDisable(false);
				unlimited.setDisable(false);
			}
		}
	}//end handler

}//end class
