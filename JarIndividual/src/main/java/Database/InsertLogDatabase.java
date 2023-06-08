/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import Conexao.ConexaoLocal;
import Conexao.ConexaoNuvem;
import ConexaoDatabase.LogUso;
import ConexaoDatabase.LogUsoRowMapper;
import ConexaoDatabase.Usuario;
import ConexaoDatabase.UsuarioRowMapper;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author diego
 */
public class InsertLogDatabase {
    ConexaoNuvem conexao = new ConexaoNuvem();
    JdbcTemplate con = conexao.getConnection();
    
    ConexaoLocal conexaoLocal = new ConexaoLocal();
    JdbcTemplate conLocal = conexaoLocal.getConnection();

    InsertMaquinaDatabase inMaq = new InsertMaquinaDatabase();
    InsertComponenteDatabase comp = new InsertComponenteDatabase();

    List<LogUso> listaLogUso;
    List<Usuario> listaObjetoUsuario;

    public void validacao(String email, String senha) {
        listaObjetoUsuario = con.query(
                "select * from usuario where email = ? and senha = ?", 
                new UsuarioRowMapper(), email, senha);
        
        Usuario id = listaObjetoUsuario.get(0);
        Integer idUser = id.getId_usuario();
        Integer idEmpresa = id.getFk_empresa();
        
        listaLogUso = con.query(
                "select * from log_uso where id_usuario = ? and id_empresa = ?",
                new LogUsoRowMapper(), idUser, idEmpresa);

        if (listaLogUso.isEmpty()) {
            System.out.println("Indo criar a maquina");
            
            inMaq.criarMaquinaELog(idEmpresa, idUser);
            
        } else {
            con.update("insert into log_uso (id_maquina, id_empresa, id_usuario) values (?, ?, ?)",
                    listaLogUso.get(0).getFk_maquina(), idEmpresa, idUser);
            
//            conLocal.update(
//                "insert into log_uso (id_maquina, id_empresa, id_usuario) values (?, ?, ?)",
//                listaLogUso.get(0).getFk_maquina(), idEmpresa, idUser);
            
            InsertComponenteDatabase comp = new InsertComponenteDatabase();
            comp.inserirComponenteMaquina(idEmpresa, listaLogUso.get(0).getFk_maquina());
        }
    }
}
