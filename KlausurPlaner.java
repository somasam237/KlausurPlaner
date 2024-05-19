package application; 
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.RadioButton;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
public class KlausurPlaner extends Application {

	 RadioButton schwierigRadioButton;
	   private List<Faecher> elemente = new ArrayList<>();
           Label nameLabel;

//	    public static void main(String[] args) {
//	        launch(args);
//	    }

	    @Override
	    public void start(Stage primaryStage) {
	        primaryStage.setTitle("DatumSpeicher");

	        GridPane gridPane = new GridPane();
	        gridPane.setPadding(new Insets(10));
	        gridPane.setHgap(10);
	        gridPane.setVgap(10);

	        nameLabel = new Label("Fach:");
	        TextField nameTextField = new TextField();
	        Label LevelLabel = new Label("Level:");
	        TextField levelTextField = new TextField();
	        Label dateLabel = new Label("Klausurdatum (dd.mm.yyyy):");
	        TextField dateTextField = new TextField();
                
                Label uhrzeit = new Label("Uhrzeit (hh:mm) : ");
                TextField uhrTextField = new TextField();
	        Label schwierigLabel = new Label("Schwierig?:");
	        ToggleGroup schwierigToggleGroup = new ToggleGroup();
	        RadioButton nichtSchwierigButton = new RadioButton("nein");
	        nichtSchwierigButton.setToggleGroup(schwierigToggleGroup);
	        nichtSchwierigButton.setSelected(true);
	        
	        schwierigRadioButton = new RadioButton("ja");
	        schwierigRadioButton.setToggleGroup(schwierigToggleGroup);
	        
	       
	        
	       // maximalerBetragTextField.setDisable(true);
	      //  Label euroLabel = new Label("Euro");
	        Label euroLabel = new Label("Maximal Summe");
	        
	        //schwierigRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
	          //  maximalerBetragTextField.setDisable(!newValue);
	        //});
	        Button speichernButton = new Button("Speichern");
	        Button button = new Button("Datei Schreiben");
	        
	        speichernButton.setOnAction(e -> {
	            if (validateFields(nameTextField.getText(), levelTextField.getText(), dateTextField.getText(),uhrTextField.getText() )) {
	                try {
	                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
	                    Date klausurdate = dateFormat.parse(dateTextField.getText());
	                    boolean schwer = schwierigRadioButton.isSelected(); 

	                    Faecher faecher = new Faecher(nameTextField.getText(), levelTextField.getText(), klausurdate, schwer,uhrTextField.getText());
	                    elemente.add(faecher);

	                    clearFields(nameTextField, levelTextField, dateTextField,uhrTextField);
	                    nichtSchwierigButton.setSelected(true);
	                    
	                    stop();
	                } catch (ParseException ex) {
	                    showErrorDialog("Ungültiges Datumsformat. Verwenden Sie das Format dd.mm.yyyy.");
	                }
	            } else {
	                showErrorDialog("Bitte füllen Sie alle Felder korrekt aus.");
	            }
	        });

	        gridPane.add(nameLabel, 0, 0);
	        gridPane.add(nameTextField, 1, 0);
	        gridPane.add(LevelLabel, 0, 1);
	        gridPane.add(levelTextField, 1, 1);
	        gridPane.add(dateLabel, 0, 2);
                gridPane.add(uhrzeit,0,3);
                gridPane.add(uhrTextField,1,3);
	        gridPane.add(dateTextField, 1, 2);
	        gridPane.add(schwierigLabel, 0, 4);
	        gridPane.add(nichtSchwierigButton, 1, 5);
	        gridPane.add(schwierigRadioButton, 1, 4); 
	        gridPane.add(speichernButton, 0, 7);
	       // gridPane.add(button, 1, 6);
	        

	        Scene scene = new Scene(gridPane, 400, 250);
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    }

	    private boolean validateFields(String fach, String level, String klausurdatum ,String uhrzeit) {
	        return !fach.isEmpty() && !level.isEmpty() && !klausurdatum.isEmpty() && !uhrzeit.isEmpty(); // && (schwierigRadioButton.isSelected()  );
	    }

	    private void clearFields(TextField nameTextField, TextField vornameTextField, TextField geburtstagTextField ,TextField uhrTextField ) {
	        nameTextField.clear();
	        vornameTextField.clear();
	        geburtstagTextField.clear();
                uhrTextField.clear();
	    }

	    private void showErrorDialog(String message) {
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Fehler");
	        alert.setHeaderText(null);
	        alert.setContentText(message);
	        alert.showAndWait();
	    }

	    @Override
	    public void stop() {
	        saveToFile();
	        showTotalGiftSum();
	    }

	    private void saveToFile() {
	        Collections.sort(elemente);
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Faecher.txt"))) {
	            for (Faecher faecher : elemente) {
	                writer.write(faecher.toString());
	                writer.newLine();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	   private void showTotalGiftSum() {
	        int totalGiftSum = 0;
	         
	        Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setTitle("Cool! Ich habe das fuer dich gespeihert!!");
	        alert.setHeaderText(null);
	        alert.setContentText(" Der fach wurde gespeichert" );
	        alert.showAndWait();
	    }

	 
	


}
