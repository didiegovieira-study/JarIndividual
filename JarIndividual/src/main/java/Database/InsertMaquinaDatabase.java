/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import Conexao.ConexaoLocal;
import Conexao.ConexaoNuvem;
import ConexaoDatabase.Maquina;
import ConexaoDatabase.MaquinaRowMapper;
import LoocaApi.ShowSistema;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author diego
 */
public class InsertMaquinaDatabase {
    private ConexaoNuvem conexao = new ConexaoNuvem();
    private JdbcTemplate con = conexao.getConnection();
    
    private ConexaoLocal conexaoLocal = new ConexaoLocal();
    private JdbcTemplate conLocal = conexaoLocal.getConnection();
    
    public void criarMaquinaELog(Integer idEmpresa, Integer idUser) {
        ShowSistema sis = new ShowSistema();
        String so = sis.showSistema().getSistemaOperacional();
        Integer arquitetura = sis.showSistema().getArquitetura();
        String fabricante = sis.showSistema().getFabricante();

        List<Maquina> maquinas = con.query("select * from maquina where id_empresa = ?", 
                new MaquinaRowMapper(), idEmpresa);

        if (maquinas.isEmpty()) {
            System.out.println("Registrando Maquina");

            con.update("insert into maquina values (?, ?, ?, ?)", 
                    so, arquitetura, fabricante, idEmpresa);
            
            Integer idMaquina = buscarUltimaMaquinaId();
            if (idMaquina != null) {
                inserirLogUso(idMaquina, idEmpresa, idUser);
            }
        } else {
            Integer idMaquina = maquinas.get(0).getId_maquina();
            inserirLogUso(idMaquina, idEmpresa, idUser);
        }
    }

    private Integer buscarUltimaMaquinaId() {
        List<Maquina> maquinas = con.query("select * from maquina", new MaquinaRowMapper());
        if (!maquinas.isEmpty()) {
            return maquinas.get(maquinas.size() - 1).getId_maquina();
        }
        return null;
    }

    private void inserirLogUso(Integer idMaquina, Integer idEmpresa, Integer idUser) {
        con.update("insert into log_uso (id_maquina, id_empresa, id_usuario) values (?, ?, ?)", 
                idMaquina, idEmpresa, idUser);
        
        conLocal.update("insert into log_uso (id_maquina, id_empresa, id_usuario) values (?, ?, ?)",
                idMaquina, idEmpresa, idUser);

        InsertComponenteDatabase comp = new InsertComponenteDatabase();
        comp.inserirComponenteMaquina(idEmpresa, idMaquina);
    }
}
