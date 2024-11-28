package edu.curso.bibliotecafx;

import java.util.List;

public interface AutorDAO {

    void inserir (Autor a ) throws LivrariaException;
    void atualizar (Autor a ) throws LivrariaException;
    void excluir (Autor a ) throws LivrariaException;
    List<Autor> pesquisarPorNome(String nome) throws LivrariaException;


}
