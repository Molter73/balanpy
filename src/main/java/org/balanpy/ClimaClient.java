package org.balanpy;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.net.URIBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.util.Pair;

class ClimaClientResponseHandler implements HttpClientResponseHandler<JsonNode> {
	@Override
	public JsonNode handleResponse(ClassicHttpResponse response)
			throws HttpException, IOException {
		HttpEntity httpEntity = response.getEntity();

		ObjectMapper om = new ObjectMapper();
		return om.readTree(httpEntity.getContent());
	}
}

public class ClimaClient {
	private JsonNode clima;

	public ClimaClient() throws UnsupportedOperationException, IOException {
		String url = "https://wttr.in/Madrid";
		HttpGet httpGet = new HttpGet(url);

		try {
			URI uri = new URIBuilder(new URI(url)).addParameter(new BasicNameValuePair("format", "j1")).build();
			httpGet.setUri(uri);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}

		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			HttpClientResponseHandler<JsonNode> responseHandler = new ClimaClientResponseHandler();
			clima = httpClient.execute(httpGet, responseHandler);
		}
	}

	public Double getTemperaturaActual() {
		return clima.get("current_condition").get(0).get("temp_C").asDouble();
	}

	public ArrayList<Pair<String, Double>> getTemperaturasPorHora() {
		ArrayList<Pair<String, Double>> temperaturas = new ArrayList<Pair<String, Double>>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM HH:mm");

		for (JsonNode c : clima.get("weather")) {
			LocalDateTime date = LocalDateTime.parse(c.get("date").asText() + "T00:00:00");

			for (JsonNode hourly : c.get("hourly")) {
				Double temp = hourly.get("tempC").asDouble();
				temperaturas.add(new Pair<String, Double>(date.format(formatter), temp));

				date = date.plusHours(3);
			}
		}

		return temperaturas;
	}
}
