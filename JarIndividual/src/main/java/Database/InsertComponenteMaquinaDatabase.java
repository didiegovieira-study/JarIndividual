/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import Conexao.ConexaoLocal;
import Conexao.ConexaoNuvem;
import ConexaoDatabase.ComponenteMaquina;
import ConexaoDatabase.ComponenteMaquinaRowMapper;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author diego
 */
public class InsertComponenteMaquinaDatabase {
    private ConexaoNuvem conexao = new ConexaoNuvem();
    private JdbcTemplate con = conexao.getConnection();
    
    private ConexaoLocal conexaoBancoLocal = new ConexaoLocal();
    private JdbcTemplate conLocal = conexaoBancoLocal.getConnection();
    
    private InsertRegistroDatabase inReg = new InsertRegistroDatabase();
    
    public void cadastroComponenteExistente(Integer idComponente, Integer idMaquina, Integer idEmpresa){
        List<ComponenteMaquina> compo = con.query(
                "select * from componente_maquina where id_componente = ? and id_maquina = ?", 
                new ComponenteMaquinaRowMapper(), idComponente, idMaquina);
        
        if(compo.isEmpty()){
            con.update(
                    "insert into componente_maquina (id_componente, id_maquina, id_empresa) VALUES (?, ?, ?);", 
                    idComponente, idMaquina, idEmpresa);
            
            compo = con.query(
                "select * from componente_maquina where id_componente = ? and id_maquina = ?", 
                new ComponenteMaquinaRowMapper(), idComponente, idMaquina);
            
            

        } else {
            
        }
    }
    
    public void cadastroComponenteNovo(Integer idComponente, Integer idMaquina, Integer idEmpresa){
        List<ComponenteMaquina> compo = con.query(
                "select * from componente_maquina where id_componente = ? and id_maquina = ?", 
                new ComponenteMaquinaRowMapper(), idComponente, idMaquina);
        
        if(compo.isEmpty()){
            con.update(
                "insert into componente_maquina (id_componente, id_maquina, id_empresa) VALUES (?, ?, ?);", 
                idComponente, idMaquina, idEmpresa);
            
            compo = con.query(
                "select * from componente_maquina where id_componente = ? and id_maquina = ?", 
                new ComponenteMaquinaRowMapper(), idComponente, idMaquina);
            

        } else {

        }
    }
}
