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
public class MaquinaRowMapper implements RowMapper<Maquina>{
    public Maquina mapRow(ResultSet rs, int rowNum) throws SQLException {
        Maquina objetoMaquina = new Maquina();
        
        objetoMaquina.setId_maquina(rs.getInt("id"));
        objetoMaquina.setSistemaOperacional(rs.getString("sistema_operacional"));
        objetoMaquina.setArquitetura(rs.getString("arquitetura"));
        objetoMaquina.setFabricante(rs.getString("fabricante"));
        objetoMaquina.setFk_empresa(rs.getInt("id_empresa"));

        return objetoMaquina;
    }
}
