package LoocaApi;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.discos.Volume;
import java.io.File;
import java.text.DecimalFormat;

import java.util.List;

public class ShowDisco {
    Looca looca = new Looca();

    public List showDisco() {
        Long qtdArmazenamentoBytes = null;
        //DiscoGrupo grupoDeDiscos = looca.getGrupoDeDiscos();
        List<Disco> discos = looca.getGrupoDeDiscos().getDiscos();

//        for (Disco disco : discos) {
//           qtdArmazenamentoBytes = disco.getLeituras();
//        }
        
        return discos;
        
    }
    
    public String nomeDisco() {
        String nome = null;
        List<Disco> discos = looca.getGrupoDeDiscos().getDiscos();

        for (Disco disco : discos) {
           nome = disco.getNome();
        }
        
        return nome;
        
    }
    
    public Double showTotal() {
        File disk = new File("/"); // diretório raiz do disco
        long totalSpace = disk.getTotalSpace(); // tamanho total do disco em bytes

        // converter bytes para GB
        Double totalGB = (double) totalSpace / (1024 * 1024 * 1024);
        
        Double value = totalGB;
        DecimalFormat df = new DecimalFormat("#,##0.00"); // define o padrão de formatação
        String formattedValue = df.format(value); // formata o valor double
        Double numero = Double.parseDouble(formattedValue.replace(",", "."));
        
        return numero;
    }
    
    public Double showDisponivel() {
        File disk = new File("/"); // diretório raiz do disco
        long freeSpace = disk.getFreeSpace(); // espaço livre em bytes

        // converter bytes para GB
        Double freeGB = (double) freeSpace / (1024 * 1024 * 1024);
        
        Double value = freeGB;
        DecimalFormat df = new DecimalFormat("#,##0.00"); // define o padrão de formatação
        String formattedValue = df.format(value); // formata o valor double
        Double numero = Double.parseDouble(formattedValue.replace(",", "."));
        
        return numero;
        
    }
    
    public Double showUsado() {
        File disk = new File("/"); // diretório raiz do disco
        long totalSpace = disk.getTotalSpace(); // tamanho total do disco em bytes
        long usedSpace = totalSpace - disk.getFreeSpace(); // espaço usado em bytes

        Double usedGB = (double) usedSpace / (1024 * 1024 * 1024);
        
        Double value = usedGB;
        DecimalFormat df = new DecimalFormat("#,##0.00"); // define o padrão de formatação
        String formattedValue = df.format(value); // formata o valor double
        Double numero = Double.parseDouble(formattedValue.replace(",", "."));
        
        return numero;
    }
    
    
}
