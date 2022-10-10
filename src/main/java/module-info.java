module br.com.charmapeditor {
    requires javafx.controls;
    requires javafx.fxml;


    opens br.com.charmapeditor to javafx.fxml;
    exports br.com.charmapeditor;
}