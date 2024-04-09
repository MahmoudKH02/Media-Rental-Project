package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MediaMenu extends Stage {
	private BorderPane root = new BorderPane();
	private Button addMedia = new Button("Add New Media");
	private Button delMedia = new Button("Delete Media");
	private Button updateMedia = new Button("Update Information About Media");
	private Button searchMedia = new Button("Search a Media by code");
	private Button printMedia = new Button("Print All Media information");
	private Button mainPage = new Button("Return To Main Page");
	private VBox box = new VBox(15);
	
	public MediaMenu() {
		box.getChildren().addAll(addMedia, delMedia, updateMedia, searchMedia, printMedia, mainPage);
		root.setCenter(box);
		box.setAlignment(Pos.CENTER);
		
		addMedia.setOnAction(e -> {
			this.close();
			new MediaStage("add");
		});
		delMedia.setOnAction(e -> {
			this.close();
			new MediaStage("del");
		});
		updateMedia.setOnAction(e -> {
			this.close();
			new MediaStage("update");
		});
		searchMedia.setOnAction(e -> {
			this.close();
			new MediaStage("find");
		});
		printMedia.setOnAction(e -> {
			this.close();
			//new InfoStage();
		});
		mainPage.setOnAction(e -> {
			this.close();
			new Main().start(new Stage());
		});
		this.setTitle("Media Menu");
		this.setScene(new Scene(root, 400, 400));
		this.show();
	}
	
}
