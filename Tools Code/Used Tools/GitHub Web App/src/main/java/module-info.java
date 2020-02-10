module GitHub.Web.main {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.logging;
    requires org.jfxtras.styles.jmetro;
    opens app to javafx.fxml;
    exports  app ;

}