module javafx.carnet {
    exports ch.makery.address;
    exports ch.makery.address.model;
    exports ch.makery.address.util;
    exports ch.makery.address.view;
	// https://stackoverflow.com/questions/51082947/whats-the-correct-to-make-fxml-members-in-java-9-or-10
    opens ch.makery.address;
    opens ch.makery.address.model;
    opens ch.makery.address.util;
    opens ch.makery.address.view;


    requires javafx.controls;
    requires javafx.fxml;
}