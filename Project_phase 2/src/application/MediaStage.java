package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MediaStage extends Stage {
	ComboBox<String> typeBox = new ComboBox<>();
	TextField tCode = new TextField();
	TextField tTitle = new TextField();
	TextField tCopiesAvailable = new TextField();
	TextField tWeight = new TextField();
	TextField tArtist = new TextField();
	TextField tSongs = new TextField();
	RadioButton ac = new RadioButton("AC");
	RadioButton hr = new RadioButton("HR");
	RadioButton dr = new RadioButton("DR");
	private Button add = new Button("Add");
	private Button del = new Button("Del");
	private Button update = new Button("Update");
	private Button find = new Button("Find");
	private Button back = new Button("Back");
	
	private String sType;//media type as a string
	private String sRating;//rating as a string
	
	public MediaStage(String operation) {
		BorderPane pane = getPane();
		this.setTitle("Media Stage");
		ac.setDisable(true);
		hr.setDisable(true);
		dr.setDisable(true);
		tTitle.setEditable(false);
		tCopiesAvailable.setEditable(false);
		tWeight.setDisable(true);
		tArtist.setDisable(true);
		tSongs.setDisable(true);
		add.setVisible(false);
		del.setVisible(false);
		update.setVisible(false);
		find.setOnAction(new FindHandler());
		typeBox.setDisable(true);
		TypeHandler handler = new TypeHandler();
		
		if(operation.equals("add")) {
			add.setDisable(true);
			typeBox.setDisable(false);
			typeBox.setOnAction(handler);
			tTitle.setEditable(true);
			tCopiesAvailable.setEditable(true);
			add.setVisible(true);
			find.setVisible(false);
		}
		else if(operation.equals("del")) {
			del.setVisible(true);
			tTitle.setEditable(false);
			tCopiesAvailable.setEditable(false);
			tWeight.setEditable(false);
		}
		else if(operation.equals("update")) {
			typeBox.setOnAction(handler);
			update.setVisible(true);
			typeBox.setDisable(false);
			tTitle.setEditable(true);
			tCopiesAvailable.setEditable(true);
		}
		ac.setOnAction(e -> {
			sRating = "AC";
		});
		hr.setOnAction( e-> {
			sRating = "HR";
		});
		dr.setOnAction(e -> {
			sRating = "DR";
		});
		add.setOnAction(e -> {
			if(sType.equals("Movie"))
				Main.addMedia(sType, tCode.getText(), tTitle.getText(), tCopiesAvailable.getText(), sRating, "");
			else if(sType.equals("Album"))
				Main.addMedia(sType, tCode.getText(), tTitle.getText(), tCopiesAvailable.getText(), tArtist.getText(), tSongs.getText());
			else if(sType.equals("Game"))
				Main.addMedia(sType, tCode.getText(), tTitle.getText(), tCopiesAvailable.getText(), tWeight.getText(), "");
			
			tCode.setText("");
			tTitle.setText("");
			tCopiesAvailable.setText("");
			tArtist.setText("");
			tSongs.setText("");
		});
		del.setOnAction(e -> {
			Main.deleteMedia(tCode.getText());
			tCode.setText("");
		});
		update.setOnAction(e -> {
			if(sType.equals("Movie"))
				Main.updateMedia(sType, tCode.getText(), tTitle.getText(), tCopiesAvailable.getText(), sRating, "");
			else if(sType.equals("Album"))
				Main.updateMedia(sType, tCode.getText(), tTitle.getText(), tCopiesAvailable.getText(), tArtist.getText(), tSongs.getText());
			else if(sType.equals("Game"))
				Main.updateMedia(sType, tCode.getText(), tTitle.getText(), tCopiesAvailable.getText(), tWeight.getText(), "");
		});
		back.setOnAction(e -> {
			this.close();
			new MediaMenu();
		});
		this.setScene(new Scene(pane, 750, 600));
		this.show();
	}
	
	private BorderPane getPane() {
		BorderPane root = new BorderPane();
		GridPane pane = new GridPane();
		Label type = new Label("Media Type:");
		Label code = new Label("Media Code:");
		Label title = new Label("Media Title:");
		Label copiesAvailable = new Label("Copies Available:");
		Label rating = new Label("Movie Rating:");
		Label weight = new Label("Game Weight:");
		Label artist = new Label("Album Artist:");
		Label songs = new Label("Album Songs:");
		ImageView addImage = new ImageView(new Image("C:\\images\\plus.png"));
		ImageView delImage = new ImageView(new Image("C:\\images\\delete.png"));
		ImageView findImage = new ImageView(new Image("C:\\images\\loupe.png"));
		ImageView updateImage = new ImageView(new Image("C:\\images\\loop.png"));
		ImageView backImage = new ImageView(new Image("C:\\images\\turn-back.png"));
		VBox textBox = new VBox(30);
		VBox labelBox = new VBox(35);
		HBox buttonBox = new HBox(10);
		HBox radioBox = new HBox(10);
		ToggleGroup group = new ToggleGroup();
		
		ac.setToggleGroup(group);
		hr.setToggleGroup(group);
		dr.setToggleGroup(group);
		
		typeBox.getItems().addAll("Movie", "Album", "Game");
		type.setFont(new Font(17));
		code.setFont(new Font(17));
		title.setFont(new Font(17));
		copiesAvailable.setFont(new Font(17));
		rating.setFont(new Font(17));
		weight.setFont(new Font(17));
		artist.setFont(new Font(17));
		songs.setFont(new Font(17));
		
		tCode.setPrefSize(250, 35);
		tTitle.setPrefSize(250, 35);
		tCopiesAvailable.setPrefSize(250, 35);
		tWeight.setPrefSize(250, 35);
		tArtist.setPrefSize(250, 35);
		tSongs.setPrefSize(250, 35);
		
		add.setPrefSize(120, 60);
		add.setGraphic(addImage);
		add.setGraphicTextGap(10);
		add.setFont(new Font(15));
		
		del.setPrefSize(120, 60);
		del.setGraphic(delImage);
		del.setGraphicTextGap(10);
		del.setFont(new Font(15));
		
		find.setPrefSize(120, 60);
		find.setGraphic(findImage);
		find.setGraphicTextGap(10);
		find.setFont(new Font(15));
		
		update.setPrefSize(120, 60);
		update.setGraphic(updateImage);
		update.setGraphicTextGap(10);
		update.setFont(new Font(15));
		
		back.setPrefSize(120, 60);
		back.setGraphic(backImage);
		back.setGraphicTextGap(10);
		back.setFont(new Font(15));
		
		radioBox.getChildren().addAll(ac, hr, dr);
		labelBox.getChildren().addAll(type, code, title, copiesAvailable, rating, weight, artist, songs);
		textBox.getChildren().addAll(typeBox, tCode, tTitle, tCopiesAvailable, radioBox, tWeight, tArtist, tSongs);
		buttonBox.getChildren().addAll(add, del, update, find, back);
		
		pane.setHgap(25);
		GridPane.setRowIndex(labelBox, 0);
		GridPane.setColumnIndex(labelBox, 0);
		GridPane.setRowIndex(textBox, 0);
		GridPane.setColumnIndex(textBox, 1);
		
		pane.getChildren().addAll(labelBox, textBox);
		pane.setAlignment(Pos.CENTER);
		buttonBox.setAlignment(Pos.CENTER);
		root.setCenter(pane);
		root.setBottom(buttonBox);
		return root;
	}
	
	class TypeHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent arg0) {
			if(typeBox.getSelectionModel().isSelected(0)) {
				ac.setDisable(false);
				hr.setDisable(false);
				dr.setDisable(false);
				tWeight.setDisable(true);
				tArtist.setDisable(true);
				tSongs.setDisable(true);
				sType = "Movie";
			}
			else if(typeBox.getSelectionModel().isSelected(1)) {
				ac.setDisable(true);
				hr.setDisable(true);
				dr.setDisable(true);
				tArtist.setDisable(false);
				tSongs.setDisable(false);
				sType = "Album";
			}
			else if(typeBox.getSelectionModel().isSelected(2)) {
				tWeight.setDisable(false);
				ac.setDisable(true);
				hr.setDisable(true);
				dr.setDisable(true);
				tArtist.setDisable(true);
				tSongs.setDisable(true);
				sType = "Game";
			}
			add.setDisable(false);
		}
	}//end of Type handler
	
	class FindHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent arg0) {
			String[] s = Main.searchMedia(tCode.getText()).split("-");
			if(s.length > 1) {
				if(s[0].equals("Movie")) {
					typeBox.getSelectionModel().select(0);
					if(s[4].equals("AC"))
						ac.setSelected(true);
					else if(s[4].equals("HR"))
						hr.setSelected(true);
					else
						dr.setSelected(true);
				}
				else if(s[0].equals("Album")) {
					typeBox.getSelectionModel().select(1);
					tArtist.setText(s[4]);
					tSongs.setText(s[5]);
				}
				else if(s[0].equals("Game")) {
					typeBox.getSelectionModel().select(3);
					tWeight.setText(s[4]);
				}
				tCode.setText(s[1]);
				tTitle.setText(s[2]);
				tCopiesAvailable.setText(s[3]);
			}
		}
	}//end of find handler
	
}//end class
