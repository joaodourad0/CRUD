package edu.curso.bibliotecafx;

import java.time.LocalDate;

public class Autor {

    private long id;
    private String nome;
    private String nacionalidade;
    private LocalDate nascimento;

    public Autor() {

    }

    public Autor(long id, String nome, String nacionalidade, LocalDate nascimento) {

        this.id = id;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.nascimento = nascimento;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }




}
