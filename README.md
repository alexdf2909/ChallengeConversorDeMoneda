# Challenge: Conversor de Moneda
Sobre el Desafío de programación Conversor de Monedas me ha ayudado a reforzar los conocimientos aprendidos al realizar solicitudes a una API de tasas de cambio y a manipular datos JSON.
## Api
Para el desafío se utilizó la API Exchange Rate API.
## HttpClient y HttpRequest
```java
HttpClient client = HttpClient.newHttpClient();
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(direccion))
                            .build();
                    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
```
## Clases
### MonedaOmdb
```java
public record MonedaOmdb(
        @SerializedName("base_code") String baseCode,
        @SerializedName("target_code") String targetCode,
        @SerializedName("conversion_rate") String conversionRate,
        @SerializedName("conversion_result") String conversionResult
) {}
```
### Moneda
```java
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
    public String toString() {
        return "(" +
                "codigoBase=" + codigoBase  +
                ", codigoObjetivo=" + codigoObjetivo +
                ", tasaConversion=" + tasaConversion +
                ", resultadoConversion=" + resultadoConversion +
                ')';
    }
}
```
## Funcionamiento
```java
String json = response.body();

                    MonedaOmdb miMonedaOmdb = gson.fromJson(json, MonedaOmdb.class);

                    Moneda miMoneda = new Moneda(miMonedaOmdb);
                    System.out.println("El valor " + cantidad + " [" + miMoneda.getCodigoBase()+
                            "] corresponde al valor final de =>>> " + miMoneda.getResultadoConversion() +" [" + miMoneda.getCodigoObjetivo() + "]\n");
```
## Resultado
![image](https://github.com/user-attachments/assets/464756e6-691b-449b-8ea2-b7a92217c9c0)

