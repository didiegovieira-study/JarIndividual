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
 * @author diego
 */
public class LogUsoRowMapper implements RowMapper<LogUso>{
    public LogUso mapRow(ResultSet rs, int rowNum) throws SQLException {
        LogUso objetoLogUso = new LogUso();
        
        objetoLogUso.setId_LogUso(rs.getInt("id"));
        objetoLogUso.setFk_maquina(rs.getInt("id_maquina"));
        objetoLogUso.setFk_empresa(rs.getInt("id_empresa"));
        objetoLogUso.setFk_usuario(rs.getInt("id_usuario"));

        return objetoLogUso;
    }
}
