module br.damiao.financias {
    requires javafx.controls;
    requires javafx.fxml;

    opens br.damiao.financias to javafx.fxml;
    exports br.damiao.financias;
}