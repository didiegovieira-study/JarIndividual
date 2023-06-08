/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import Conexao.ConexaoNuvem;
import ConexaoDatabase.Usuario;
import ConexaoDatabase.UsuarioRowMapper;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author diego
 */
public class SelectDatabase {
    private Boolean loginValido = false;
    
    ConexaoNuvem conexaoBanco = new ConexaoNuvem();
    JdbcTemplate conexao = conexaoBanco.getConnection();
    List<Usuario> listaObjetoUsuario;
    
    InsertLogDatabase inLogUso = new InsertLogDatabase();
    
    public void selectAndInsert(String email, String senha){
        
        listaObjetoUsuario = conexao.query(
                "select * from usuario where email = ? and senha = ?", 
                new UsuarioRowMapper(), email, senha);
        
        if (listaObjetoUsuario.stream().anyMatch(usuario ->
                usuario.getEmailUsuario().equalsIgnoreCase(email)
                        && usuario.getSenhaUsuario().equals(senha))) {
            
            loginValido = true;

        } else {
            loginValido = false;

        }
        
    }
    
    public Boolean isLoginValido() {
        return loginValido;
    }
}
