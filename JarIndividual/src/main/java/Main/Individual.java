/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import Conexao.ConexaoLocal;
import Conexao.ConexaoNuvem;
import ConexaoDatabase.Usuario;
import ConexaoDatabase.UsuarioRowMapper;
import Database.InsertLogDatabase;
import Database.InsertRegistroDatabase;
import Database.SelectDatabase;
import LoocaApi.ShowCPU;
import LoocaApi.ShowDisco;
import LoocaApi.ShowMemoria;
import LoocaApi.ShowRede;
import LoocaApi.ShowSistema;
import LoocaApi.ShowTemp;
import java.util.List;
import java.util.Scanner;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author diego
 */
public class Individual {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        Scanner leitor2 = new Scanner(System.in);
        
        ConexaoLocal conexaoLocal = new ConexaoLocal();
        ConexaoNuvem conexaoNuvem = new ConexaoNuvem();
        JdbcTemplate conNuvem = conexaoNuvem.getConnection();
        JdbcTemplate conLocal = conexaoLocal.getConnection();
        
        Integer escolha;
        
        ShowCPU cpu = new ShowCPU();
        ShowTemp temp = new ShowTemp();
        ShowRede rede = new ShowRede();
        ShowMemoria mem = new ShowMemoria();
        ShowDisco disco = new ShowDisco();
        ShowSistema sis = new ShowSistema();
        
        SelectDatabase select = new SelectDatabase();
        InsertLogDatabase loguso = new InsertLogDatabase();
        InsertRegistroDatabase inreg = new InsertRegistroDatabase();
        
        System.out.println("Diga seu email:");
        String email = leitor.nextLine();
        System.out.println("Diga sua senha:");
        String senha = leitor.nextLine();
        
        List<Usuario> listaObjetoUsuario = conNuvem.query(
                "select * from usuario where email = ? and senha = ?", 
                new UsuarioRowMapper(), email, senha);
        
        select.selectAndInsert(email, senha);

        if (select.isLoginValido()) {
            System.out.println("Login deu certo!");

            loguso.validacao(email, senha);
            inreg.insert(email, senha);

        } else {
            System.out.println("Login deu errado");

        }
        
    }
}
