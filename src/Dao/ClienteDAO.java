/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.sql.ResultSet;
import connection.ConnectionFactory.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import view.ClienteCadastro;
import model.ClienteModel;

/**
 *
 * @author Pedro Henrique
 */
public class ClienteDAO {

    private Connection connection;

    public ClienteDAO() {
        connection = new ConnectionFactory().connection();
    }

    public void cadastrar(String nome, String email, String telefone) throws SQLException {
        String cadastro = "INSERT INTO cliente(nome, email, telefone) VALUES (?,?,?);";

        try (PreparedStatement ps = connection.prepareStatement(cadastro)) {
            ps.setString(1, nome);
            ps.setString(2, email);
            ps.setString(3, telefone);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
        } catch (SQLNonTransientConnectionException err) {
            System.out.println("Erro na conexão com o Banco de Dados" + err.getMessage());
        } catch (SQLException err) {
            System.out.println("Erro ao executar o cadastro no Banco de Dados" + err.getMessage());
        } catch (NullPointerException err) {
            System.out.println("Erro ao enviar dados ao Banco de Dados" + err.getMessage());
        }
    }

    public List<ClienteModel> buscar(String nome) throws SQLException {

        String sql = "SELECT * FROM cliente WHERE nome LIKE ?;";
        ClienteModel cliente = null;
        List<ClienteModel> lista = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, "%" + nome + "%");

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    cliente = new ClienteModel();

                    cliente.setIdCliente(rs.getInt("id_cliente"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setEmail(rs.getString("email"));
                    cliente.setTelefone(rs.getString("telefone"));

                    lista.add(cliente);
                }
            } catch (SQLNonTransientConnectionException err) {
                System.out.println("Erro de busca no Banco de Dados" + err.getMessage());
            }
        }
        return lista;
    }

    public void alterar(int id_cliente, String nome, String email, String telefone) throws SQLException {

        String sql = "UPDATE cliente SET nome = ?, email = ?, telefone = ? WHERE id_cliente = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, nome);
            ps.setString(2, email);
            ps.setString(3, telefone);
            ps.setInt(4, id_cliente);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso!");

        } catch (SQLNonTransientConnectionException err) {
            System.out.println("Erro de conexão: " + err.getMessage());
        } catch (SQLException err) {
            System.out.println("Erro SQL: " + err.getMessage());
        }
    }

    public void excluir(int id_cliente) throws SQLException {

        String sql = "DELETE FROM cliente WHERE id_cliente = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id_cliente);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso!");

        } catch (SQLNonTransientConnectionException err) {
            System.out.println("Erro de conexão: " + err.getMessage());
        } catch (SQLException err) {
            System.out.println("Erro SQL: " + err.getMessage());
        }
    }
}
