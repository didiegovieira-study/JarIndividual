/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import Conexao.ConexaoLocal;
import Conexao.ConexaoNuvem;
import ConexaoDatabase.Componente;
import ConexaoDatabase.ComponenteMaquina;
import ConexaoDatabase.ComponenteMaquinaRowMapper;
import ConexaoDatabase.ComponenteRowMapper;
import ConexaoDatabase.LogUso;
import ConexaoDatabase.LogUsoRowMapper;
import ConexaoDatabase.Usuario;
import ConexaoDatabase.UsuarioRowMapper;
import LoocaApi.ShowCPU;
import LoocaApi.ShowDisco;
import LoocaApi.ShowMemoria;
import LoocaApi.ShowRede;
import LoocaApi.ShowSistema;
import LoocaApi.ShowTemp;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author diego
 */
public class InsertRegistroDatabase {
    ConexaoNuvem conexao = new ConexaoNuvem();
    JdbcTemplate con = conexao.getConnection();
    
    ConexaoLocal conexaoBancoLocal = new ConexaoLocal();
    JdbcTemplate conLocal = conexaoBancoLocal.getConnection();
    
    ShowCPU cpu = new ShowCPU();
    ShowTemp temp = new ShowTemp();
    ShowDisco disco = new ShowDisco();
    ShowRede rede = new ShowRede();
    ShowMemoria ram = new ShowMemoria();
    ShowSistema sis = new ShowSistema();
    
    public void insert(String email, String senha){
        List<Usuario> listaObjetoUsuario;
        listaObjetoUsuario = con.query(
                "select * from usuario where email = ? and senha = ?", 
                new UsuarioRowMapper(), email, senha);
        Usuario id = listaObjetoUsuario.get(0);

        List<LogUso> listaLogUso;
        listaLogUso = con.query(
                "select * from log_uso where id_usuario = ? and id_empresa = ?",
                new LogUsoRowMapper(), id.getId_usuario(), id.getFk_empresa());
        LogUso log = listaLogUso.get(0);
        
        List<ComponenteMaquina> listaCompMaq;
        listaCompMaq = con.query(
                "select * from componente_maquina where id_maquina = ?",
                new ComponenteMaquinaRowMapper(), log.getFk_maquina());
        ComponenteMaquina compmaq = listaCompMaq.get(0);
        
        final int logFkMaquina = log.getFk_maquina();
        

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                JSONObject json = new JSONObject();
                
                //CPU
                List<Componente> listaComp;
                listaComp = con.query("select * from componente where nome = ?",
                    new ComponenteRowMapper(), cpu.nomeCpu());
                Componente lisComp = listaComp.get(0);

                List<ComponenteMaquina> listaCompMaq;
                listaCompMaq = con.query("select * from componente_maquina where id_componente = ? and id_maquina = ?",
                        new ComponenteMaquinaRowMapper(), lisComp.getId_componente(), logFkMaquina);
                ComponenteMaquina lis2 = listaCompMaq.get(0);
                

                con.update("insert into registro (clock_cpu, temp_cpu, uso_cpu, data_hora, id_componente_maquina, id_componente, id_maquina, id_empresa) values (?, ?, ?, current_timestamp, ?, ?, ?, ?)", 
                cpu.clockCpu(), temp.showTemp(), cpu.usoCpu(), lis2.getId_componenteMaquina(), lis2.getFk_componente(), lis2.getFk_maquina(), lis2.getFk_empresa());

//                conLocal.update("insert into registro (clock_cpu, temp_cpu, uso_cpu, data_hora) values (?, ?, ?, current_timestamp)", 
//                cpu.clockCpu(), temp.showTemp(), cpu.usoCpu());
                
                if (temp.showTemp() > 80.0){
                    json.put("text", "A temperatura da máquina está acima de 80.0º");
                }
                
                //Double porcentagem = (cpu.usoCpu() / cpu.clockCpu()) * 100.0;
                
                if (cpu.usoCpu() > 80.0){
                    json.put("text", "O limite de 80% de uso da cpu foi atingido!");
                }

                //REDE
                listaComp = con.query("select * from componente where nome = ?", 
                        new ComponenteRowMapper(),rede.nomeRede());
                lisComp = listaComp.get(0);

                listaCompMaq = con.query("select * from componente_maquina where id_componente = ? and id_maquina = ?",
                        new ComponenteMaquinaRowMapper(), lisComp.getId_componente(), logFkMaquina);
                lis2 = listaCompMaq.get(0);

                con.update("insert into registro (download_rede, upload_rede, data_hora, id_componente_maquina, id_componente, id_maquina, id_empresa) values (?, ?, current_timestamp, ?, ?, ?, ?)", 
                rede.showDownload(), rede.showUpload(), lis2.getId_componenteMaquina(), lis2.getFk_componente(), lis2.getFk_maquina(), lis2.getFk_empresa());


                
                //RAM
                listaComp = con.query("select * from componente where nome = 'Ram' and total = ?", 
                        new ComponenteRowMapper(), ram.totalRam());
                lisComp = listaComp.get(0);

                listaCompMaq = con.query("select * from componente_maquina where id_componente = ? and id_maquina = ?",
                        new ComponenteMaquinaRowMapper(), lisComp.getId_componente(), logFkMaquina);
                lis2 = listaCompMaq.get(0);

                con.update("insert into registro (uso, data_hora, id_componente_maquina, id_componente, id_maquina, id_empresa) values (?, current_timestamp, ?, ?, ?, ?)", 
                ram.usoRam(), lis2.getId_componenteMaquina(), lis2.getFk_componente(), lis2.getFk_maquina(), lis2.getFk_empresa());

//                conLocal.update("insert into registro (uso, data_hora) values (?, current_timestamp)", 
//                ram.usoRam());
                
                Double porcentagem = (ram.usoRam() / ram.totalRam()) * 100.0;

                if(porcentagem > 80.0){
                    json.put("text", "O limite de 80% de uso da Ram foi atingido!");
                }

                //DISCO
                listaComp = con.query("select * from componente where nome = ? and total = ?", 
                        new ComponenteRowMapper(), disco.nomeDisco(), disco.showTotal());
                lisComp = listaComp.get(0);

                listaCompMaq = con.query("select * from componente_maquina where id_componente = ? and id_maquina = ?",
                        new ComponenteMaquinaRowMapper(), lisComp.getId_componente(), logFkMaquina);
                lis2 = listaCompMaq.get(0);

                con.update("insert into registro (uso, data_hora, id_componente_maquina, id_componente, id_maquina, id_empresa) values (?, current_timestamp, ?, ?, ?, ?)", 
                disco.showUsado(), lis2.getId_componenteMaquina(), lis2.getFk_componente(), compmaq.getFk_maquina(), compmaq.getFk_empresa());

//                conLocal.update("insert into registro (uso, data_hora) values (?, current_timestamp)", 
//                disco.showUsado());
                
                porcentagem = (disco.showUsado() / disco.showTotal()) * 100.0;

                if(porcentagem > 80.0){
                    json.put("text", "O limite de 80% de uso de disco foi atingido!");
                }
                
                System.out.println("Finalizado os registro, voltando...");

                 
              

            }
        };

        Timer timer = new Timer();
        timer.schedule(timerTask, 0, 10000); // Executa a cada 10 segundos (10000 milissegundos)
        
    }
}
