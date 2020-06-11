module br.damiao.financias {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;

    opens br.damiao.financias to javafx.fxml;
    exports br.damiao.financias;
    exports br.damiao.financias.gui;
}