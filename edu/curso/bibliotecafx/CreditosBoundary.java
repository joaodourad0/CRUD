package edu.curso.bibliotecafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class CreditosBoundary implements Tela {

    private Label lblCredito = new Label("João Victor Lopes Dourado");


    @Override
    public Pane render() {

        BorderPane panePrincipal = new BorderPane();
        StackPane centerPane = new StackPane();

        lblCredito.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        centerPane.getChildren().add(lblCredito);

        panePrincipal.setCenter( centerPane );
        return panePrincipal;
    }
}