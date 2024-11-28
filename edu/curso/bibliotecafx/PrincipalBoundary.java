package edu.curso.bibliotecafx;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;



public class PrincipalBoundary extends Application {
    private Map<String, Tela> telas = new HashMap<>();
    @Override
    public void start(Stage stage) throws Exception {
        telas.put("autor", new AutorBoundary() );
        telas.put("livro", new LivroBoundary() );
        telas.put("credito", new CreditosBoundary());

        BorderPane panePrincipal = new BorderPane();
        MenuBar menuBar = new MenuBar();

        Menu mnuAutores = new Menu("Autores");
        Menu mnuLivros = new Menu("Livros");
        Menu mnuCreditos = new Menu("Creditos");

        MenuItem menuItemAutor = new MenuItem("Autores");
        menuItemAutor.setOnAction(e ->
                panePrincipal.setCenter( telas.get("autor").render()) );

        MenuItem menuItemLivro = new MenuItem("Livro");
        menuItemLivro.setOnAction(e ->
                panePrincipal.setCenter( telas.get("livro").render()) );

        MenuItem menuItemCreditos = new MenuItem("Creditos");
        menuItemCreditos.setOnAction(e ->
                panePrincipal.setCenter( telas.get("credito").render()) );

        mnuAutores.getItems().addAll( menuItemAutor );
        mnuLivros.getItems().addAll( menuItemLivro );
        mnuCreditos.getItems().addAll( menuItemCreditos);

        menuBar.getMenus().addAll( mnuAutores, mnuLivros, mnuCreditos);

        panePrincipal.setTop( menuBar );

        Scene scn = new Scene(panePrincipal, 400, 600);

        stage.setScene(scn);
        stage.setTitle("Cadastro de Livros");
        stage.show();
    }
    public static void main(String[] args) {
        Application.launch(PrincipalBoundary.class, args);
    }


}

