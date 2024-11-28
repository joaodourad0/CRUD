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

public class AutorControl {
    private ObservableList<Autor> lista = FXCollections.observableArrayList();
    private long contador = 0;

    private LongProperty id = new SimpleLongProperty(0);
    private StringProperty nome = new SimpleStringProperty("");
    private StringProperty nacionalidade = new SimpleStringProperty("");
    private ObjectProperty<LocalDate> nascimento =
            new SimpleObjectProperty<>(LocalDate.now());

    private AutorDAO autorDAO = null;

    public AutorControl() throws LivrariaException {
        autorDAO = new AutorDAOImpl();
        pesquisarTodos();
        inicializarContador();
    }

    private void inicializarContador() {
        long maiorId = lista.stream()
                .mapToLong(Autor::getId)
                .max()
                .orElse(0);
        this.contador = maiorId;
    }

    public Autor paraEntidade(){
        Autor a = new Autor();
        a.setId( id.get());
        a.setNome(nome.get());
        a.setNacionalidade( nacionalidade.get());
        a.setNascimento( nascimento.get());
        return a;
    }

    public void excluir(Autor a ) throws LivrariaException {
        autorDAO.excluir (a);
        pesquisarTodos();
    }

    public void limparCampos() {
        id.set(0);
        nome.set("");
        nacionalidade.set("");
        nascimento.set(LocalDate.of(2000,12, 31));
    }

    public void paraTela(Autor a) {
        if (a != null) {
            id.set( a.getId() );
            nome.set( a.getNome() );
            nacionalidade.set( a.getNacionalidade() );
            nascimento.set( a.getNascimento() );
        }
    }

    public void salvar() throws LivrariaException {
        Autor a = paraEntidade();
        if (a.getId() == 0 ) {
            this.contador += 1;
            a.setId( this.contador );
            autorDAO.inserir( a );
        } else {
            autorDAO.atualizar( a );
        }

        pesquisarTodos();
        limparCampos();
    }

    public void alterar(long id) throws LivrariaException {
        Autor autorExistente = lista.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);
        if (autorExistente != null) {
            paraTela(autorExistente);
        } else {
            throw new LivrariaException("Autor com ID " + id + " não encontrado.");
        }
    }


    public void pesquisar() throws LivrariaException {
        lista.clear();
        lista.addAll( autorDAO.pesquisarPorNome( nome.get() ) );
    }

    public void pesquisarTodos() throws LivrariaException {
        lista.clear();
        lista.addAll( autorDAO.pesquisarPorNome( "" ) );
    }

    public LongProperty idProperty() {
        return this.id;
    }
    public StringProperty nomeProperty() {
        return this.nome;
    }
    public StringProperty nacionalidadeProperty(){
        return this.nacionalidade;
    }

    public ObjectProperty<LocalDate> nascimentoProperty() {
        return this.nascimento;
    }


    public ObservableList<Autor> getLista() {
        return this.lista;
    }
}
