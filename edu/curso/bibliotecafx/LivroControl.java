package edu.curso.bibliotecafx;

import java.time.LocalDate;

import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LivroControl {
    private ObservableList<Livro> lista = FXCollections.observableArrayList();
    private long contador = 0;

    private LongProperty id = new SimpleLongProperty(0);
    private StringProperty titulo = new SimpleStringProperty("");
    private StringProperty genero = new SimpleStringProperty("");
    private StringProperty autor = new SimpleStringProperty("");
    private ObjectProperty<LocalDate> publicacao =
            new SimpleObjectProperty<>(LocalDate.now());


    private LivroDAO livroDAO = null;

    public LivroControl() throws LivrariaException {
        livroDAO = new LivroDAOImpl();
        pesquisarTodos();
        inicializarContador();
    }

    private void inicializarContador() {
        long maiorId = lista.stream()
                            .mapToLong(Livro::getId)
                            .max()
                            .orElse(0);
        this.contador = maiorId;
    }

    public Livro paraEntidade(){
        Livro l = new Livro();
        l.setId( id.get());
        l.setTitulo(titulo.get());
        l.setGenero( genero.get());
        l.setAutor(autor.get());
        l.setPublicacao( publicacao.get());

        return l;
    }

    public void excluir(Livro l ) throws LivrariaException {
        livroDAO.excluir (l);
        pesquisarTodos();
    }

    public void limparCampos() {
        id.set(0);
        titulo.set("");
        genero.set("");
        autor.set("");
        publicacao.set(LocalDate.of(2000,12, 31));


    }

    public void paraTela(Livro l) {
        if (l != null) {
            id.set( l.getId() );
            titulo.set( l.getTitulo() );
            genero.set( l.getGenero() );
            autor.set(l.getAutor());
            publicacao.set( l.getPublicacao() );

        }
    }

    public void salvar() throws LivrariaException {
        Livro l = paraEntidade();
        if (l.getId() == 0 ) {
            this.contador += 1;
            l.setId( this.contador );
            livroDAO.inserir( l );
        } else {
            livroDAO.atualizar( l );
        }

        pesquisarTodos();
        limparCampos();
    }



    public void pesquisar() throws LivrariaException {
        lista.clear();
        lista.addAll( livroDAO.pesquisarPorTitulo( titulo.get() ) );
    }

    public void pesquisarTodos() throws LivrariaException {
        lista.clear();
        lista.addAll( livroDAO.pesquisarPorTitulo(""));

    }


    public LongProperty idProperty() {
        return this.id;
    }
    public StringProperty tituloProperty() {
        return this.titulo;
    }
    public StringProperty generoProperty(){
        return this.genero;
    }

    public StringProperty autorProperty(){
        return this.autor;
    }

    public ObjectProperty<LocalDate> publicacaoProperty() {
        return this.publicacao;
    }


    public ObservableList<Livro> getLista() {
        return this.lista;
    }
}
