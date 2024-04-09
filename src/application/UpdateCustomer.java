package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class UpdateCustomer extends CustomerStage{
	private Button update = new Button("Update");
	private RadioButton limited = new RadioButton("LIMITED");
	private RadioButton unlimited = new RadioButton("UNLIMITED");
	
	public UpdateCustomer() {
		find.setVisible(true);
		tName.setEditable(true);
		tAddress.setEditable(true);
		tMobile.setEditable(true);
		
		update.setOnAction(e -> {
			String [] s = Main.searchCustomer(tId.getText()).split("-");
			if (s.length > 1) {
				tId.setText("");
				tName.setText("");
				tAddress.setText("");
				tMobile.setText("");
			}
		});
		find.setOnAction(e -> {
			String[] s = Main.searchCustomer(tId.getText()).split("-");
			if(s.length > 1) {
				tName.setText(s[2]);
				tAddress.setText(s[3]);
				tMobile.setText(s[4]);
				if(s[5].equals("LIMITED")) {
					limited.setSelected(true);
					unlimited.setSelected(false);
				}
				else{
					limited.setSelected(false);
					unlimited.setSelected(true);
				}
			}
		});
		this.setTitle("Update Customers");
		this.setScene(new Scene(getPane(), 750, 550));
		this.show();
	}
	
	protected GridPane getPane() {
		GridPane root = super.getPane();
		ImageView updateImage = new ImageView(new Image("C:\\images\\loop.png"));
		ToggleGroup group = new ToggleGroup();
		limited.setToggleGroup(group);
		unlimited.setToggleGroup(group);
		textBox.getChildren().addAll(limited, unlimited);
		
		update.setPrefSize(120, 60);
		update.setGraphic(updateImage);
		update.setGraphicTextGap(10);
		update.setFont(new Font(15));
		
		GridPane.setRowIndex(update, 1);
		GridPane.setColumnIndex(update, 0);
		root.getChildren().add(update);
		
		return root;
	}
	
}
