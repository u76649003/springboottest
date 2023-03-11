package com.ecomerce.virtualCase.tiendaMonedas.tiendaMon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TiendaMonedaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TiendaMonedaApplication.class, args);
	}
	
	@Bean
    public WireMockServer wireMockServer() {
        WireMockServer wireMockServer = new WireMockServer(options().port(8081));
        wireMockServer.start();
        return wireMockServer;
    }
 
    @Bean
    public OpenAPI openApi() throws IOException {
        String apiYaml = FileUtils.readFileToString(new File("src/main/resources/openApi.yaml"), "UTF-8");
        return new OpenAPIV3Parser().read(apiYaml);
    }

}
