package LoocaApi;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.processador.Processador;

public class ShowCPU {
    Looca looca = new Looca();

    Processador processador = looca.getProcessador();

    public Processador showCpu(){
        return processador;
    }
    
    public String nomeCpu(){
        return processador.getNome();
    }
    
    public String fabricanteCpu(){
        return processador.getFabricante();
    }
    
    public Double frequenciaCpu(){
        return Double.valueOf(processador.getFrequencia());
    }
    
    public String modeloCpu(){
        return processador.getIdentificador();
    }
    
    public Double clockCpu(){
        Integer clock = processador.getNumeroCpusFisicas() + processador.getNumeroCpusLogicas();
        Double d = clock.doubleValue();
        return d;
    }
    
    public Double usoCpu(){
        return processador.getUso();
    }
}
