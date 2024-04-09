package application;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class DeleteCustomer extends CustomerStage {
	private Button delete = new Button("Del");
	
	public DeleteCustomer() {	
		
		find.setVisible(true);
		delete.setOnAction( e-> {
			Main.deleteCustomer(tId.getText()); 
			tId.setText("");
			tName.setText("");
			tAddress.setText("");
			tMobile.setText("");
			tPlan.setText("");
		});
		this.setTitle("Delete Customers");
		this.setScene(new Scene(getPane(), 750, 550));
		this.show();
	}
	
	protected GridPane getPane() {
		GridPane root = super.getPane();
		ImageView delImage = new ImageView(new Image("C:\\images\\delete.png"));
		
		find.setPrefSize(120, 60);
		find.setGraphic(findImage);
		find.setGraphicTextGap(10);
		find.setFont(new Font(15));
		
		delete.setPrefSize(120, 60);
		delete.setGraphic(delImage);
		delete.setGraphicTextGap(10);
		delete.setFont(new Font(15));
		textBox.getChildren().add(tPlan);
		
		GridPane.setRowIndex(delete, 1);
		GridPane.setColumnIndex(delete, 0);
		root.getChildren().add(delete);
		
		return root;
	}
	
}
