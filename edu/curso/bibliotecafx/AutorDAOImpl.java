package edu.curso.bibliotecafx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutorDAOImpl implements AutorDAO {

    private final static String DB_CLASS = "org.mariadb.jdbc.Driver";
    //trocar 3306 para 3307 para teste em aula
    private final static String DB_URL = "jdbc:mariadb://localhost:3306/livraria?allowPublicKeyRetrieval=true";
    private final static String DB_USER = "root";
    private final static String DB_PASS = "alunofatec";

    private Connection conexao = null;

    public AutorDAOImpl() throws LivrariaException {
        try {
            Class.forName(DB_CLASS);
            conexao = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (ClassNotFoundException | SQLException e ) {
            throw new LivrariaException( e );
        }
    }

    @Override
    public void inserir(Autor a) throws LivrariaException {
        try {
            String SQL = """
                    INSERT INTO Autores ( id, nome, nacionalidade, nascimento)
                    VALUES (?, ?, ?, ?)
                    """;

            PreparedStatement stm = conexao.prepareStatement(SQL);
            stm.setLong(1, a.getId());
            stm.setString(2, a.getNome());
            stm.setString(3, a.getNacionalidade());
            stm.setDate(4, java.sql.Date.valueOf(a.getNascimento()));
            stm.executeUpdate();
        } catch (SQLException e){
            throw new LivrariaException( e );
        }
    }

    @Override
    public void atualizar(Autor a) throws LivrariaException {
        try {
            String SQL = """
                    UPDATE Autores SET nome = ?, nacionalidade = ?, nascimento = ?
                    WHERE id = ?
                    """;
            PreparedStatement stm = conexao.prepareStatement(SQL);
            stm.setString(1, a.getNome());
            stm.setString(2, a.getNacionalidade());
            stm.setDate(3, java.sql.Date.valueOf(a.getNascimento()));
            stm.setLong(4, a.getId());

            stm.executeUpdate();
        } catch (SQLException e) {
            throw new LivrariaException( e );
        }
    }

    @Override
    public void excluir(Autor a) throws LivrariaException {
        try {
            String SQL = """
                    DELETE FROM Autores WHERE id = ?
                    """;
            PreparedStatement stm = conexao.prepareStatement(SQL);
            stm.setLong( 1, a.getId() );
            int i = stm.executeUpdate();
        } catch (SQLException e) {
            throw new LivrariaException( e );
        }
    }

    @Override
    public List<Autor> pesquisarPorNome(String nome) throws LivrariaException {
        List<Autor> lista = new ArrayList<>();
        try {
            String SQL = """
                    SELECT * FROM Autores WHERE nome LIKE ?
                    """;
            PreparedStatement stm = conexao.prepareStatement(SQL);
            stm.setString(1, "%" + nome + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Autor a = new Autor();
                a.setId( rs.getLong("id") );
                a.setNome( rs.getString("nome") );
                a.setNacionalidade( rs.getString("nacionalidade") );
                a.setNascimento( rs.getDate("nascimento").toLocalDate() );

                lista.add( a );
            }
        } catch (SQLException e) {
            throw new LivrariaException( e );
        }
        return lista;
    }



}
