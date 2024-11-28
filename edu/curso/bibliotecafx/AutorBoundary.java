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

public class AutorBoundary implements Tela {
    private Label lblId = new Label("");
    private TextField txtNome = new TextField();
    private TextField txtNacio = new TextField();
    private DatePicker dateNasc = new DatePicker();

    private AutorControl control = null;

    private TableView<Autor> tableView = new TableView<>();


    @Override
    public Pane render() {


        try {
            control = new AutorControl();
        } catch (LivrariaException e ) {
            new Alert(AlertType.ERROR, "Erro ao Iniciar", ButtonType.FINISH).showAndWait();
        }

        BorderPane panePrincipal = new BorderPane();
        GridPane paneForm = new GridPane();

        Button btnSalvar = new Button("Salvar");
        btnSalvar.setOnAction(e -> {
            try {
                control.salvar();
            } catch (LivrariaException err) {
                new Alert(AlertType.ERROR, "Erro ao gravar o Autor" + err.getMessage(), ButtonType.CLOSE).showAndWait();
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

        Button btnLimpar = new Button("Limpar");
        btnLimpar.setOnAction( e -> control.limparCampos() );


        Button btnExcluir = new Button("Excluir");
        btnExcluir.setOnAction(e -> {
            try {
                Autor autor = tableView.getSelectionModel().getSelectedItem();
                if (autor != null) {
                    control.excluir(autor);
                    tableView.refresh();
                }
            } catch (LivrariaException err) {
                new Alert(AlertType.ERROR, "Erro ao Excluir o Livro", ButtonType.CLOSE).showAndWait();
            }
        });


        paneForm.add(new Label("Autores"), 2, 0);
        paneForm.add(new Label("Id:"), 0, 1);
        paneForm.add(lblId, 1, 1);
        paneForm.add(new Label("Nome: "), 0, 2);
        paneForm.add(txtNome, 1, 2);
        paneForm.add(btnLimpar, 2, 2);
        paneForm.add(new Label("Nacionalidade: "), 0, 3);
        paneForm.add(txtNacio, 1, 3);
        paneForm.add(new Label("Nascimento: "), 0, 4);
        paneForm.add(dateNasc, 1, 4);




        paneForm.add(btnSalvar, 0, 5);
        paneForm.add(btnPesquisar, 1, 5);
        paneForm.add(btnExcluir, 2, 5);

        paneForm.setHgap(10);
        paneForm.setVgap(10);

        ligacoes();
        gerarColunas();

        panePrincipal.setTop( paneForm );
        panePrincipal.setCenter(tableView);

        return panePrincipal;
    }

    public void gerarColunas() {
        TableColumn<Autor, Long> col1 = new TableColumn<>("Id");
        col1.setCellValueFactory( new PropertyValueFactory<Autor, Long>("id") );

        TableColumn<Autor, String> col2 = new TableColumn<>("Nome");
        col2.setCellValueFactory( new PropertyValueFactory<Autor, String>("nome"));

        TableColumn<Autor, String> col3 = new TableColumn<>("Nacionalidade");
        col3.setCellValueFactory( new PropertyValueFactory<Autor, String>("nacionalidade"));

        TableColumn<Autor, LocalDate> col4 = new TableColumn<>("Nascimento");
        col4.setCellValueFactory( new PropertyValueFactory<Autor, LocalDate>("nascimento"));


        tableView.getSelectionModel().selectedItemProperty()
                .addListener( (obs, antigo, novo) -> {
                    if (novo != null) {
                        System.out.println("Nome: " + novo.getNome());
                        control.paraTela(novo);
                    }
                });

        tableView.getColumns().addAll(col1, col2, col3, col4);
        tableView.setItems( control.getLista() );
    }

    public void ligacoes() {
        control.idProperty().addListener( (obs, antigo, novo) -> {
            lblId.setText( String.valueOf(novo) );
        });
        Bindings.bindBidirectional(control.nomeProperty(), txtNome.textProperty());
        Bindings.bindBidirectional(control.nacionalidadeProperty(), txtNacio.textProperty());
        Bindings.bindBidirectional(control.nascimentoProperty(),
                dateNasc.valueProperty());
    }

}
