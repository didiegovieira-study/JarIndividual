package LoocaApi;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.rede.Rede;
import com.github.britooo.looca.api.group.rede.RedeInterface;
import com.github.britooo.looca.api.util.Conversor;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ShowRede {
    Looca looca = new Looca();
    Rede rede = looca.getRede();
    
    private static Double byteConverterGiga(long bytes){
        return (double) bytes /(1024 * 1024 * 1024);
    }
    
    private static Double byteConverterMega(long bytes){
        return (double) bytes / (1024 * 1024);
    }

    // Lista de dados da rede
    public List showRede(){
        List<RedeInterface> lista = rede.getGrupoDeInterfaces().getInterfaces();

        return lista;
    }
    
    //Nome da Rede
    public String nomeRede(){
        List<RedeInterface> lista = rede.getGrupoDeInterfaces().getInterfaces();
        
        String nome = null;
        
        for (RedeInterface redeInterface : lista) {
            nome = redeInterface.getNome();
        }

        return nome;
    }
    
    //IPV4 da rede
    public String ipRede(){
        List<RedeInterface> lista = rede.getGrupoDeInterfaces().getInterfaces();
        
        String nome = null;
        
        for (RedeInterface redeInterface : lista) {
            nome = redeInterface.getEnderecoIpv4().get(0);
        }

        return nome;
    }
    
    //Driver da rede
    public String driverRede(){
        List<RedeInterface> lista = rede.getGrupoDeInterfaces().getInterfaces();
        
        String nome = null;
        
        for (RedeInterface redeInterface : lista) {
            nome = redeInterface.getNomeExibicao();
        }

        return nome;
    }
        
    // Download de rede acumulativo
    public Double showDownload(){
        List<RedeInterface> lista = rede.getGrupoDeInterfaces().getInterfaces();
        
        Long download = null;
        
        for (RedeInterface redeInterface : lista) {
            download = redeInterface.getBytesRecebidos();
            long bytesRecebidosA = redeInterface.getBytesRecebidos();
            download = bytesRecebidosA;
        }
        
        return byteConverterMega(download);
    }
    
    // Upload da rede acumulativo
    public Double showUpload() {
        List<RedeInterface> lista = rede.getGrupoDeInterfaces().getInterfaces();
        
        Long upload = null;
        
        for (RedeInterface redeInterface : lista) {
            long bytesEnviadosA = redeInterface.getBytesEnviados();
            upload = bytesEnviadosA;
        }

        return byteConverterMega(upload);
    }
    
    
}
