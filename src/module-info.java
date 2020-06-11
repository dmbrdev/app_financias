module agora {
	requires javafx.fxml;
	requires javafx.controls;
	requires javafx.graphics;
	requires java.sql;
	requires javafx.base;
	
    opens aplicacao to javafx.fxml;
    opens gui to javafx.fxml;
//	
	exports aplicacao;
	exports db;
	exports gui;
	exports model.dao;
	exports model.dao.impl;
	exports model.entidade;
	exports model.services;
	
}