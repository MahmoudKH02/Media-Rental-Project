package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ErrorMessage extends Stage {
	BorderPane pane = new BorderPane();
	Button ok = new Button("OK");
	Label label = new Label();
	
	public ErrorMessage(String error) {
		this.setTitle("Error");
		label.setFont(new Font(20));
		label.setText(error);
		pane.setCenter(label);
		BorderPane.setAlignment(label, Pos.CENTER);
		pane.setBottom(ok);
		BorderPane.setAlignment(ok, Pos.CENTER);
		ok.setPrefSize(60, 40);	
		
		ok.setOnAction(e -> {
			this.close();
		});
		this.setScene(new Scene(pane, 350, 300));
		this.show();
	}
}
