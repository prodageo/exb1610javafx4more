package ch.makery.address;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import ch.makery.address.model.Ville;
import ch.makery.address.view.VilleEditDialogController;
import ch.makery.address.view.VilleOverviewController;



public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    
 // ... AFTER THE OTHER VARIABLES ...

    /**
     * The data as an observable list of Persons.
     */
    private ObservableList<Ville> villeData = FXCollections.observableArrayList();

    /**
     * Constructor
     */
    public MainApp() {
        // Add some sample data to the constructs of the GUI
	// This has nothing to do with database
        villeData.add(new Ville(30100, "Marrakech"));
        villeData.add(new Ville(75000, "Paris"));
	   
	// TO DO
	    /*
	VilleIterator myVilleIt = myTxnScript.getVilleIterator()
	int myCurrentCodePostal ;
	String myCurrentNom ;    
	while ( myVilleIt.hasNext() )
	{
		myCurrentNom = myVilleIt.nextNomVille ( ) ;
		myCurrentCodePostal = myVilleIt.nextCodePostalVille ( ) ;		
	        villeData.add(new Ville( myCurrentCodePostal, myCurrentNom));
	}
	*/
		
    }

	
	
	
	
    // ... VILLE MANAGEMENT  ... /////////////////////////////////////////////////////////////////////////////////////////////////
	

    public ObservableList<Ville> getVilleData() {
        return villeData;
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showVilleOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/VilleOverview.fxml"));
            AnchorPane villeOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(villeOverview);

            // Give the controller access to the main app.
            VilleOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


	
    /**
     * Opens a dialog to edit details for the specified person. If the user
     * clicks OK, the changes are saved into the provided person object and true
     * is returned.
     * 
     * @param person the person object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showVilleEditDialog(Ville aVille) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/VilleEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit aVille");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the aVille into the controller.
            VilleEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setVille(aVille);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
	
	
	
	
    // ... THE REST OF THE CLASS ...

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");

        initRootLayout();

        showVilleOverview();
    }

    // Initializes the root layout.
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Returns the main stage.
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
