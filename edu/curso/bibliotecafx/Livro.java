package edu.curso.bibliotecafx;

import java.time.LocalDate;

public class Livro {

    private long id;
    private String titulo;
    private String genero;
    private String autor;
    private LocalDate publicacao;


    public Livro() {

    }

    public Livro(long id, String titulo, String genero, String autor, LocalDate publicacao) {

        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.autor = autor;
        this.publicacao = publicacao;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public LocalDate getPublicacao() {
        return publicacao;
    }

    public void setPublicacao(LocalDate publicacao) {
        this.publicacao = publicacao;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }


}