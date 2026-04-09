/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection.ConnectionFactory;

import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Pedro Henrique
 */
public class ConnectionFactory {
    
    private static final String URL = "jdbc:mysql://localhost:3306/crud_cliente";
    private static final String USER = "root";
    private static final String PASS = "";
    
    public static java.sql.Connection connection(){
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException("Erro na conexao com o Banco de Dados" + e.getMessage());
        }
    }
}
