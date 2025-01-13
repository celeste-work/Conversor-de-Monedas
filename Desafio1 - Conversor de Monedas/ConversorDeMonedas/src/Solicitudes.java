import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Solicitudes {

    public double convertirMoneda (Double monto, String monedaBase, String monedaAConvertir){

        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/5dbcb213f5f951a6b8464e52/pair/" + monedaBase + "/" + monedaAConvertir);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Respuesta de la API: " + response.body());

            Monedas resultado = new Gson().fromJson(response.body(), Monedas.class);

            if (!"success".equalsIgnoreCase(resultado.result())) {
                throw new RuntimeException("Error: La API no devolvió un resultado exitoso.");
            }

            if (resultado.conversion_rate() == null) {
                throw new RuntimeException("Error: La API no devolvió una tasa de conversión válida.");
            }

            return monto * resultado.conversion_rate();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error al comunicarse con la API.", e);
        }

    }
}
