package LoocaApi;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.temperatura.Temperatura;

public class ShowTemp {
    Looca looca = new Looca();

    Temperatura temperatura = looca.getTemperatura();

    public Double showTemp(){
        return temperatura.getTemperatura();
    }
}
