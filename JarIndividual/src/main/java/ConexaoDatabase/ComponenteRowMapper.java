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
public class ComponenteRowMapper implements RowMapper<Componente>{
    public Componente mapRow(ResultSet rs, int rowNum) throws SQLException {
        Componente objetoComponente = new Componente();
        
        objetoComponente.setId_componente(rs.getInt("id"));
        objetoComponente.setNomeComponente(rs.getString("nome"));
        objetoComponente.setFabricante(rs.getString("fabricante"));
        objetoComponente.setTipo(rs.getString("tipo"));
        objetoComponente.setModelo(rs.getString("modelo"));
        objetoComponente.setTotal(rs.getDouble("total"));

        return objetoComponente;
    }
}
