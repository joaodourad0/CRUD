package edu.curso.bibliotecafx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDAOImpl implements LivroDAO {

    private final static String DB_CLASS = "org.mariadb.jdbc.Driver";
    //trocar 3306 para 3307 para teste em aula
    private final static String DB_URL = "jdbc:mariadb://localhost:3306/livraria?allowPublicKeyRetrieval=true";
    private final static String DB_USER = "root";
    private final static String DB_PASS = "alunofatec";

    private Connection conexao = null;

    public LivroDAOImpl() throws LivrariaException {
        try {
            Class.forName(DB_CLASS);
            conexao = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (ClassNotFoundException | SQLException e ) {
            throw new LivrariaException( e );
        }
    }

    @Override
    public void inserir(Livro l ) throws LivrariaException {
        try {
            String SQL = """
                    INSERT INTO Livros (id, titulo, genero, autor, publicacao)
                    VALUES (?, ?, ?, ?, ?)
                    """;

            PreparedStatement stm = conexao.prepareStatement(SQL);
            stm.setLong(1, l.getId());
            stm.setString(2, l.getTitulo());
            stm.setString(3, l.getGenero());
            stm.setString(4, l.getAutor());
            stm.setDate(5, java.sql.Date.valueOf(l.getPublicacao()));

            stm.executeUpdate();
        } catch (SQLException e){
            throw new LivrariaException( e );
        }
    }

    @Override
    public void atualizar(Livro l ) throws LivrariaException {
        try {
            String SQL = """
                    UPDATE Livros SET titulo = ?, genero = ?, publicacao = ?, autor =?
                    WHERE id = ?
                    """;
            PreparedStatement stm = conexao.prepareStatement(SQL);
            stm.setString(1, l.getTitulo());
            stm.setString(2, l.getGenero());
            stm.setString(4, l.getAutor());
            stm.setDate(3, java.sql.Date.valueOf(l.getPublicacao()));


            stm.executeUpdate();
        } catch (SQLException e) {
            throw new LivrariaException( e );
        }
    }

    @Override
    public void excluir(Livro l ) throws LivrariaException {
        try {
            String SQL = """
                    DELETE FROM Livros WHERE id = ?
                    """;
            PreparedStatement stm = conexao.prepareStatement(SQL);
            stm.setLong( 1, l.getId() );
            int i = stm.executeUpdate();
        } catch (SQLException e) {
            throw new LivrariaException( e );
        }
    }

    @Override
    public List<Livro> pesquisarPorTitulo(String titulo) throws LivrariaException {
        List<Livro> lista = new ArrayList<>();
        try {
            String SQL = """
                    SELECT * FROM Livros WHERE titulo LIKE ?
                    """;
            PreparedStatement stm = conexao.prepareStatement(SQL);
            stm.setString(1, "%" + titulo + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Livro l = new Livro();
                l.setId( rs.getLong("id") );
                l.setTitulo( rs.getString("titulo") );
                l.setGenero( rs.getString("genero") );
                l.setAutor(rs.getString("autor"));
                l.setPublicacao( rs.getDate("publicacao").toLocalDate() );

                lista.add( l );
            }
        } catch (SQLException e) {
            throw new LivrariaException( e );
        }
        return lista;
    }
}
