package LoocaApi;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.memoria.Memoria;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class ShowMemoria {
    Looca looca = new Looca();

    Memoria memoria = looca.getMemoria();

    public Memoria showMemoria(){
        return memoria;
    }
    
    public Double usoRam(){
        double valor = memoria.getEmUso().doubleValue() / (1024.0 * 1024.0 * 1024.0);

        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(Locale.getDefault());
        decimalFormatSymbols.setDecimalSeparator('.');

        DecimalFormat decimalFormat = new DecimalFormat("#.##", decimalFormatSymbols);
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);

        String valorFormatado = decimalFormat.format(valor);
        double valorFormatadoComoDouble = Double.parseDouble(valorFormatado);

        return valorFormatadoComoDouble;
    }
    
    public Long disponivelRam(){
        return memoria.getDisponivel();
    }
    

    public Double totalRam() {
        double valor = memoria.getTotal().doubleValue() / (1024.0 * 1024.0 * 1024.0);

        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(Locale.getDefault());
        decimalFormatSymbols.setDecimalSeparator('.');

        DecimalFormat decimalFormat = new DecimalFormat("#.##", decimalFormatSymbols);
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);

        String valorFormatado = decimalFormat.format(valor);
        double valorFormatadoComoDouble = Double.parseDouble(valorFormatado);

        return valorFormatadoComoDouble;
    }



}
