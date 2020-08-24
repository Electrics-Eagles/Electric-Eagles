package app.Main_Window;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Text {

    // Define a variable to store the property
    private StringProperty text = new SimpleStringProperty();

    // Define a getter for the property's value
    public final String getValue(){
        return text.get();
    }

    // Define a setter for the property's value
    public final void setValue(String value){
        text.set(value);
        }

    // Define a getter for the property itself
    public StringProperty getTextProperty() {
        return text;
    }

}