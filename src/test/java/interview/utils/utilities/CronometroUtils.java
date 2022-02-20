package interview.utils.utilities;

public class CronometroUtils {

    public Long milis;
    public Long segundos;
    public Long minutos;
    public Long horas;
    public Long milisNaAtivacaoDoCronometro;
    public Long totalEmMilis;
    public String nomeCronometro;

    public CronometroUtils() {
        milisNaAtivacaoDoCronometro  = System.currentTimeMillis();
    }

    public CronometroUtils(String nomeCronometro) {
        milisNaAtivacaoDoCronometro  = System.currentTimeMillis();
        this.nomeCronometro = nomeCronometro;
    }

    public String finish(){

        totalEmMilis = System.currentTimeMillis() - milisNaAtivacaoDoCronometro;

        //Extraímos os milisegundos do total
        milis = totalEmMilis % 1000;
        //Convertemos o total para segundos
        totalEmMilis = totalEmMilis / 1000;
        //Extraímos os segundos do total
        segundos = totalEmMilis % 60;
        //Convertemos o total para minutos
        totalEmMilis /= 60;
        //Extraímos os minutos em 1 hora
        minutos = totalEmMilis % 60;
        //Convertemos o total para horas
        totalEmMilis /= 60;
        horas = totalEmMilis;

        String tempo = "Tempo de execução de " + nomeCronometro + ": Horas " + horas + " , Minutos " + minutos + " , Segundos " + segundos + " , Millisegundos " + milis;
        System.out.println(tempo);
        return tempo;
    }
}
