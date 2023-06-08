/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConexaoDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author diegovieira
 */
public class UsuarioRowMapper implements RowMapper<Usuario> {

    /**
     *
     * @param rs
     * @param rowNum
     * @return
     * @throws SQLException
     */
    @Override
    public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
        Usuario objetoUsuario = new Usuario();

        objetoUsuario.setId_usuario(rs.getInt("id"));
        objetoUsuario.setEmail(rs.getString("email"));
        objetoUsuario.setSenha(rs.getString("senha"));
        objetoUsuario.setFk_empresa(rs.getInt("id_empresa"));

        return objetoUsuario;
    }
}

