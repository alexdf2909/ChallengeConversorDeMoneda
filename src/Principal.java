import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner lectura = new Scanner(System.in);
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        while (true){

            System.out.println("***********************************************");
            System.out.println("Sea bienvenido/a al Conversor de Moneda =]");
            System.out.println("\n");
            System.out.println("1) Dólar =>> Peso argentino");
            System.out.println("2) Peso argentino =>> Dólar");
            System.out.println("3) Dólar =>> Real brasileño");
            System.out.println("4) Real brasileño =>> Dólar");
            System.out.println("5) Dólar =>> Peso colombiano");
            System.out.println("6) Peso colombiano =>> Dólar");
            System.out.println("7) Salir");
            System.out.println("Elija una opción válida:");
            System.out.println("***********************************************");

            int opcion = lectura.nextInt();

            if (opcion==7){
                break;
            }

            if(opcion<8 && opcion>0){
                String codigoConvertir = "";
                String codigoConvertido = "";
                switch (opcion){
                    case 1:
                        codigoConvertir = "USD";
                        codigoConvertido = "ARS";
                        break;
                    case 2:
                        codigoConvertido = "USD";
                        codigoConvertir = "ARS";
                        break;
                    case 3:
                        codigoConvertir = "USD";
                        codigoConvertido = "BRL";
                        break;
                    case 4:
                        codigoConvertido = "USD";
                        codigoConvertir = "BRL";
                        break;
                    case 5:
                        codigoConvertir = "USD";
                        codigoConvertido = "COP";
                        break;
                    case 6:
                        codigoConvertido = "USD";
                        codigoConvertir = "COP";
                        break;
                }

                System.out.println("Ingrese el valor que deseas convertir: ");
                double cantidad = lectura.nextDouble();

                String direccion = "https://v6.exchangerate-api.com/v6/aeb82242f1cbf27da8c64432/pair/"+codigoConvertir+"/"+codigoConvertido+"/"+String.valueOf(cantidad);

                try{

                    HttpClient client = HttpClient.newHttpClient();
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(direccion))
                            .build();
                    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                    String json = response.body();

                    MonedaOmdb miMonedaOmdb = gson.fromJson(json, MonedaOmdb.class);

                    Moneda miMoneda = new Moneda(miMonedaOmdb);
                    System.out.println("El valor " + cantidad + " [" + miMoneda.getCodigoBase()+
                            "] corresponde al valor final de =>>> " + miMoneda.getResultadoConversion() +" [" + miMoneda.getCodigoObjetivo() + "]\n");


                }catch (NumberFormatException e){
                    System.out.println("Ocurrió un error: ");
                    System.out.println(e.getMessage());
                }catch(IllegalArgumentException e){
                    System.out.println("Error en la URI, verifique la dirección.");
                }
            }else{
                System.out.println("Seleccione una opción válida.");
            }

        }
    }
}
