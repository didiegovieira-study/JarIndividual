/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexao;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author diego
 */
public class ConexaoLocal {
     private JdbcTemplate connection;
    
    public ConexaoLocal() {
       BasicDataSource dataSource = new BasicDataSource();

       dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
       dataSource.setUrl("jdbc:mysql://localhost:3306/banco1?useSSL=false&serverTimezone=UTC");
       dataSource.setUsername("root");
       dataSource.setPassword("#Gf47643238855");

       this.connection = new JdbcTemplate(dataSource);

    }

    public JdbcTemplate getConnection() {
        return connection;

    }
}
