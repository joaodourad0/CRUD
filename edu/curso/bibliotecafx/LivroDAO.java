package edu.curso.bibliotecafx;

import java.util.List;

public interface LivroDAO {

    void inserir (Livro l ) throws LivrariaException;
    void atualizar (Livro l ) throws LivrariaException;
    void excluir (Livro l ) throws LivrariaException;
    List<Livro> pesquisarPorTitulo(String titulo) throws LivrariaException;


}
