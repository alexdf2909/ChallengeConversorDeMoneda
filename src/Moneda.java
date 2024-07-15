import com.google.gson.annotations.SerializedName;

public class Moneda implements Comparable<Moneda>{
    @SerializedName("base_code")
    private String codigoBase;
    @SerializedName("target_code")
    private String codigoObjetivo;
    @SerializedName("conversion_rate")
    private double tasaConversion;
    @SerializedName("conversion_result")
    private double resultadoConversion;


    @Override
    public int compareTo(Moneda o) {
        return 0;
    }

    public Moneda(MonedaOmdb miMonedaOmdb){
        this.codigoBase = miMonedaOmdb.baseCode();
        this.codigoObjetivo = miMonedaOmdb.targetCode();
        this.tasaConversion = Double.valueOf(miMonedaOmdb.conversionRate());
        this.resultadoConversion = Double.valueOf(miMonedaOmdb.conversionResult());
    }

    public String getCodigoBase() {
        return codigoBase;
    }

    public String getCodigoObjetivo() {
        return codigoObjetivo;
    }

    public double getTasaConversion() {
        return tasaConversion;
    }

    public double getResultadoConversion() {
        return resultadoConversion;
    }

    @Override
    public String toString() {
        return "(" +
                "codigoBase=" + codigoBase  +
                ", codigoObjetivo=" + codigoObjetivo +
                ", tasaConversion=" + tasaConversion +
                ", resultadoConversion=" + resultadoConversion +
                ')';
    }
}