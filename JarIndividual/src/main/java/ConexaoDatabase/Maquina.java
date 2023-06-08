/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConexaoDatabase;

/**
 *
 * @author diego
 */
public class Maquina {
    private Integer id_maquina;
    private String sistemaOperacional;
    private String arquitetura;
    private String fabricante;
    private Integer fk_empresa;

    public Maquina(Integer id_maquina, String sistemaOperacional, String arquitetura, String fabricante, Integer fk_empresa) {
        this.id_maquina = id_maquina;
        this.sistemaOperacional = sistemaOperacional;
        this.arquitetura = arquitetura;
        this.fabricante = fabricante;
        this.fk_empresa = fk_empresa;
    }

    public Maquina(){
        
    }
    
     @Override
    public String toString() {
        return "ObjetoLog{" +
                "id_maquina ='" + id_maquina + '\'' +
                "sistemaOperacional ='" + sistemaOperacional + '\'' +
                "arquitetura ='" + arquitetura + '\'' +
                "fabricante ='" + fabricante + '\'' +
                "fk_empresa ='" + fk_empresa + '\'' +
                '}';
    }

    public Integer getId_maquina() {
        return id_maquina;
    }

    public void setId_maquina(Integer id_maquina) {
        this.id_maquina = id_maquina;
    }

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public void setSistemaOperacional(String sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
    }

    public String getArquitetura() {
        return arquitetura;
    }

    public void setArquitetura(String arquitetura) {
        this.arquitetura = arquitetura;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public Integer getFk_empresa() {
        return fk_empresa;
    }

    public void setFk_empresa(Integer fk_empresa) {
        this.fk_empresa = fk_empresa;
    }
}
