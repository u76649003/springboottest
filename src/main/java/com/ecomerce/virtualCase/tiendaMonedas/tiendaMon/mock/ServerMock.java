package com.ecomerce.virtualCase.tiendaMonedas.tiendaMon.mock;

import org.mockserver.client.MockServerClient;
import org.mockserver.mock.OpenAPIExpectation;

import lombok.Data;

@Data
public class ServerMock {
	MockServerClient mockClient;
	
	public ServerMock() {
		OpenAPIExpectation openAPIExpectations = OpenAPIExpectation.openAPIExpectation("https://github.com/VirtualCave/spring-boot-exercice-1/blob/main/currency-service-api-rest.yml");
		mockClient = new MockServerClient("localhost", 1080);
		mockClient.upsert(openAPIExpectations);

	}
}
