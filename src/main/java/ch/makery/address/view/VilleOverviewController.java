package ch.makery.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ch.makery.address.MainApp;
import ch.makery.address.model.Ville;
import ch.makery.address.util.DateUtil;

public class VilleOverviewController {
    @FXML
    private TableView<Ville> VilleTable;
    @FXML
    private TableColumn<Ville, Integer> postalCodeColumn;
    @FXML
    private TableColumn<Ville, String> cityColumn;
	
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public VilleOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the Ville table with the two columns.
		
		// https://stackoverflow.com/questions/24889638/javafx-properties-in-tableview
        postalCodeColumn.setCellValueFactory(
                cellData -> cellData.getValue().postalCodeProperty().asObject());
        cityColumn.setCellValueFactory(
                cellData -> cellData.getValue().cityProperty());

        // Clear Ville details.
        showVilleDetails(null);

        // Listen for selection changes and show the Ville details when changed.
        VilleTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showVilleDetails(newValue));
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        VilleTable.setItems(mainApp.getVilleData());
    }
    
    /**
     * Fills all text fields to show details about the Ville.
     * If the specified Ville is null, all text fields are cleared.
     * 
     * @param Ville the Ville or null
     */
    private void showVilleDetails(Ville Ville) {
        if (Ville != null) {
            // Fill the labels with info from the Ville object.
            postalCodeLabel.setText(Integer.toString(Ville.getPostalCode()));
            cityLabel.setText(Ville.getCity());
        } else {
            // Ville is null, remove all the text.
            postalCodeLabel.setText("");
            cityLabel.setText("");
        }
    }
    
    /**
     * Called when the user clicks on the delete button.
     */
    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteVille() {
        int selectedIndex = VilleTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            VilleTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Ville Selected");
            alert.setContentText("Please select a Ville in the table.");

            alert.showAndWait();
        }
    }
    
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new Ville.
     */
    @FXML
    private void handleNewVille() {
        Ville tempVille = new Ville();
        boolean okClicked = mainApp.showVilleEditDialog(tempVille);
        if (okClicked) {
            mainApp.getVilleData().add(tempVille);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected Ville.
     */
    @FXML
    private void handleEditVille() {
        Ville selectedVille = VilleTable.getSelectionModel().getSelectedItem();
        if (selectedVille != null) {
            boolean okClicked = mainApp.showVilleEditDialog(selectedVille);
            if (okClicked) {
                showVilleDetails(selectedVille);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Ville Selected");
            alert.setContentText("Please select a Ville in the table.");

            alert.showAndWait();
        }
    }
}