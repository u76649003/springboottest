package com.ecomerce.virtualCase.tiendaMonedas.tiendaMon.tiendaMoneda;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
class TiendaMonedaApplicationTests {

	// @Test
	void contextLoads() {
	}

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testMyEndpoint() { //Falta añadirle los test para las peticiones
		// Definir un contrato que describe la interacción con el cliente
		Contract contract = Contract.make(c -> {
			c.request(r -> {
				r.method(HttpMethod.GET);
				r.url("/my-endpoint");
			});
			c.response(r -> {
				r.status(200);
				r.body("Hello, world!");
			});
		});

		// Generar un stub de WireMock a partir del contrato
		WireMock.stubFor(WireMock.from(contract));

		// Realizar la solicitud HTTP utilizando la biblioteca TestRestTemplate
		String url = "/my-endpoint";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		// Validar la respuesta
		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		assertThat(response.getBody()).isEqualTo("Hello, world!");
	}

}
