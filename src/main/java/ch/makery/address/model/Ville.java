package ch.makery.address.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a Ville.
 *
 * @author Frederic BAUCHER, inspired by Marco Jakob
 */
public class Ville {

    private final SimpleIntegerProperty postalCode;
    private final StringProperty city;


    /**
     * Default constructor.
     */
    public Ville() {
        this(0, null);
    }

    /**
     * Constructor with some initial data.
     * 
     * @param firstName
     * @param lastName
     */
    public Ville(int postalCode, String cityName) {
        // Some initial dummy data, just for convenient testing.
        this.postalCode = new SimpleIntegerProperty(postalCode);
        this.city = new SimpleStringProperty(cityName);
    }


    public int getPostalCode() {
        return postalCode.get();
    }

    public void setPostalCode(int postalCode) {
        this.postalCode.set(postalCode);
    }

    public SimpleIntegerProperty postalCodeProperty() {
        return postalCode;
    }

    public String getCity() {
        return city.get();
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public StringProperty cityProperty() {
        return city;
    }
}