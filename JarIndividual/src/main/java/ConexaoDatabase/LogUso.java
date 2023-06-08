/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConexaoDatabase;

/**
 *
 * @author diego
 */
public class LogUso {
    private Integer id_LogUso;
    private Integer fk_maquina;
    private Integer fk_empresa;
    private Integer fk_usuario;

    public LogUso(Integer id_LogUso, Integer fk_maquina, Integer fk_empresa, Integer fk_usuario) {
        this.id_LogUso = id_LogUso;
        this.fk_maquina = fk_maquina;
        this.fk_empresa = fk_empresa;
        this.fk_usuario = fk_usuario;
    }


    public LogUso(){
        
    }

    @Override
    public String toString() {
        return "ObjetoLog{" +
                "id_LogUso ='" + id_LogUso + '\'' +
                "fk_maquina ='" + fk_maquina + '\'' +
                "fk_empresa ='" + fk_empresa + '\'' +
                "fk_usuario ='" + fk_usuario + '\'' +
                '}';
    }

    public Integer getId_LogUso() {
        return id_LogUso;
    }

    public void setId_LogUso(Integer id_LogUso) {
        this.id_LogUso = id_LogUso;
    }

    public Integer getFk_maquina() {
        return fk_maquina;
    }

    public void setFk_maquina(Integer fk_maquina) {
        this.fk_maquina = fk_maquina;
    }

    public Integer getFk_empresa() {
        return fk_empresa;
    }

    public void setFk_empresa(Integer fk_empresa) {
        this.fk_empresa = fk_empresa;
    }

    public Integer getFk_usuario() {
        return fk_usuario;
    }

    public void setFk_usuario(Integer fk_usuario) {
        this.fk_usuario = fk_usuario;
    }


    
}
