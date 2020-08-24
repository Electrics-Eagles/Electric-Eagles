package app.Main_Window;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/*
public class Modules
{

    private String name;
    private String descripton;
    private String verison;
    private String path;
    private String controller;
   // private JFXButton integrate;
    //private JFXButton run;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescripton() {
        return descripton;
    }

    public void setDescripton(String descripton) {
        this.descripton = descripton;
    }

    public String getVerison() {
        return verison;
    }

    public void setVerison(String verison) {
        this.verison = verison;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
    }

    public JFXButton getIntegrate() {
        return integrate;
    }

    public void setIntegrate(JFXButton integrate) {
        this.integrate = integrate;
    }

    public JFXButton getRun() {
        return run;
    }

    public void setRun(JFXButton run) {
        this.run = run;
    }



    public Modules(String name, String descripton, String verison,String path,String controllerJFXButton integrate,JFXButton run)
    {
        this.name =name ;
        this.descripton = descripton;
        this.verison=verison;
        this.path=path;
        this.controller=controller;
        //this.integrate=integrate;
        //this.run=run;

    }
}
*/
class Modules extends RecursiveTreeObject<Modules> {
    StringProperty name;
    StringProperty descripton;
    StringProperty verison;

    public Modules(String name,String descripton,String verison)
    {
        this.name = new SimpleStringProperty(name);
        this.descripton  = new SimpleStringProperty(descripton);
        this.verison = new SimpleStringProperty(verison);

    }


}

