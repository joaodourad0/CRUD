package edu.curso.bibliotecafx;

import java.time.LocalDate;

import javafx.beans.binding.Bindings;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

public class LivroBoundary implements Tela {
    private Label lblId = new Label("");
    private TextField txtTitulo = new TextField("");
    private TextField txtGenero = new TextField("");
    private DatePicker datePublic = new DatePicker();
    private TextField txtAutor = new TextField("");

    private LivroControl control = null;

    private TableView<Livro> tableView = new TableView<>();


    @Override
    public Pane render() {


        try {
            control = new LivroControl();
        } catch (LivrariaException e ) {
            new Alert(AlertType.ERROR, "Erro ao Iniciar", ButtonType.FINISH).showAndWait();
        }

        BorderPane panePrincipal = new BorderPane();
        GridPane paneForm = new GridPane();

        Button btnSalvar = new Button("Salvar");
        btnSalvar.setOnAction( e -> {
            try {
                control.salvar();
            } catch (LivrariaException err) {
                new Alert(AlertType.ERROR, "Erro ao gravar o Livro" + err.getMessage(), ButtonType.CLOSE).showAndWait();
            }
            tableView.refresh();
        });

        Button btnPesquisar = new Button("Pesquisar");
        btnPesquisar.setOnAction( e -> {
            try {
                control.pesquisar();
            } catch (LivrariaException err) {
                new Alert(AlertType.ERROR, "Erro ao pesquisar" + err.getMessage(), ButtonType.CLOSE).showAndWait();
            }
        });
        Button btnExcluir = new Button("Excluir");
        btnExcluir.setOnAction(e -> {
            try {
                Livro livro = tableView.getSelectionModel().getSelectedItem();
                if (livro != null) {
                    control.excluir(livro);
                    tableView.refresh();
                }
            } catch (LivrariaException err) {
                new Alert(AlertType.ERROR, "Erro ao Excluir o Livro", ButtonType.CLOSE).showAndWait();
            }
        });

        Button btnLimpar = new Button("Limpar");
        btnLimpar.setOnAction( e -> control.limparCampos() );

        paneForm.add(new Label("Livros"), 2, 0);
        paneForm.add(new Label("Id"), 0, 1);
        paneForm.add(lblId, 1, 1);
        paneForm.add(new Label("Titulo: "), 0, 2);
        paneForm.add(txtTitulo, 1, 2);
        paneForm.add(btnLimpar, 2, 2);
        paneForm.add(new Label("Genero: "), 0, 3);
        paneForm.add(txtGenero, 1, 3);
        paneForm.add(new Label("Publicacao: "), 0, 4);
        paneForm.add(datePublic, 1, 4);
        paneForm.add(new Label("Autor"), 0, 5);
        paneForm.add(txtAutor, 1, 5);


        paneForm.add(btnSalvar, 0, 6);
        paneForm.add(btnPesquisar, 1, 6);
        paneForm.add(btnExcluir, 2 , 6);

        paneForm.setHgap(10);
        paneForm.setVgap(10);

        ligacoes();
        gerarColunas();

        panePrincipal.setTop( paneForm );
        panePrincipal.setCenter(tableView);

        return panePrincipal;
    }

    public void gerarColunas() {
        TableColumn<Livro, Long> col1 = new TableColumn<>("Id");
        col1.setCellValueFactory( new PropertyValueFactory<Livro, Long>("id") );

        TableColumn<Livro, String> col2 = new TableColumn<>("Titulo");
        col2.setCellValueFactory( new PropertyValueFactory<Livro, String>("titulo"));

        TableColumn<Livro, String> col3 = new TableColumn<>("Genero");
        col3.setCellValueFactory( new PropertyValueFactory<Livro, String>("genero"));

        TableColumn<Livro, String> col4 = new TableColumn<>("Autor");
        col4.setCellValueFactory( new PropertyValueFactory<Livro, String>("autor"));

        TableColumn<Livro, LocalDate> col5 = new TableColumn<>("Publicacao");
        col5.setCellValueFactory( new PropertyValueFactory<Livro, LocalDate>("publicacao"));


        tableView.getSelectionModel().selectedItemProperty()
                .addListener( (obs, antigo, novo) -> {
                    if (novo != null) {
                        System.out.println("Nome: " + novo.getTitulo());
                        control.paraTela(novo);
                    }
                });

        tableView.getColumns().addAll(col1, col2, col3, col4, col5);
        tableView.setItems( control.getLista() );
    }

    public void ligacoes() {
        control.idProperty().addListener( (obs, antigo, novo) -> {
            lblId.setText( String.valueOf(novo) );
        });
        Bindings.bindBidirectional(control.tituloProperty(), txtTitulo.textProperty());
        Bindings.bindBidirectional(control.generoProperty(), txtGenero.textProperty());
        Bindings.bindBidirectional(control.autorProperty(), txtAutor.textProperty());
        Bindings.bindBidirectional(control.publicacaoProperty(), datePublic.valueProperty());

    }

}
