package org.balanpy;

import java.io.IOException;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ClimaClient {
	private JsonNode clima;

	public ClimaClient() throws UnsupportedOperationException, IOException {
		try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
			HttpGet httpGet = new HttpGet("https://wttr.in/Madrid?format=j1");

			try (CloseableHttpResponse httpResponse = httpclient.execute(httpGet)) {
				System.out.println(httpResponse.getCode() + " " + httpResponse.getReasonPhrase());

				HttpEntity httpEntity = httpResponse.getEntity();

				ObjectMapper om = new ObjectMapper();
				clima = om.readTree(httpEntity.getContent());

				EntityUtils.consume(httpEntity);
			}
		}
	}

	public Double getTemperaturaActual() {
		return clima.get("current_condition").get(0).get("temp_C").asDouble();
	}
}
