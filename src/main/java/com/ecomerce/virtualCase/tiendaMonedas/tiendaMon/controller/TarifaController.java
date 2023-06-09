package com.ecomerce.virtualCase.tiendaMonedas.tiendaMon.controller;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomerce.virtualCase.tiendaMonedas.tiendaMon.model.Tarifa;
import com.ecomerce.virtualCase.tiendaMonedas.tiendaMon.services.TarifasServices;

@RestController
@RequestMapping("/tarifas/")
public class TarifaController {

	@Autowired
	TarifasServices tarifaService;
	
	@Autowired
    private WireMockServer wireMockServer;
 
    @Autowired
    private OpenAPI openApi;
	
	private final static String FORMATO_FECHA = "yyyy-MM-dd";
		
	@PostMapping
	public ResponseEntity<Tarifa> crearTarifa(@RequestBody Tarifa tarifa) {
		try{
			Tarifa tarifaSave = tarifaService.crearTarifa(tarifa);
			return ResponseEntity.created(new URI("/tarifas/nuevo/"+tarifaSave.getId())).body(tarifaSave);
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}
	
	@GetMapping(value="/obtenerTarifasPorFechaMarcaProducto/{fecha}/{marcaId}/{productoId}")
	public ResponseEntity<List<Tarifa>> obtenerTarifasPorFechaMarcaProducto(@PathVariable String fecha, @PathVariable Integer marcaId, @PathVariable Integer productoId){
		try{
			Date fechaStart = getDateFormat(fecha);
			List<Tarifa> tarifas = tarifaService.obtenerTarifasPorFechaMarcaProducto(fechaStart, marcaId, productoId);
			return new ResponseEntity(tarifas, HttpStatus.OK);
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@GetMapping("/listaTarifas")
	public ResponseEntity<Tarifa> list() {
        List<Tarifa> tarifas = tarifaService.obtenerListaTarifas();	
        return new ResponseEntity(tarifas, HttpStatus.OK);
    }
	
	@GetMapping("{id}")
	public Tarifa obtenerTarifasById(@PathVariable Integer id){
		return tarifaService.obtenerTarifa(id);	
	}
	
	@PutMapping("/actualizarTarifas")
	public ResponseEntity<Tarifa> actualizarTarifa(@Validated @RequestBody Tarifa tarifa) {
		try{
			Tarifa tarifaSave = tarifaService.actualizarTarifa(tarifa);
			return ResponseEntity.ok(tarifaSave);
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping("/modificarPrecioTarifa/{id}/{price}")
	public ResponseEntity<Tarifa> modificarPrecioTarifa(@PathVariable Integer id,@PathVariable Double price) {
		try{
			Tarifa tarifaSave = tarifaService.obtenerTarifa(id);
			tarifaSave.setPrice(price);
			tarifaService.actualizarTarifa(tarifaSave);
			return ResponseEntity.ok(tarifaSave);
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> borrarTarifa(@PathVariable Integer id) {
		tarifaService.deleteTarifa(id);
		return ResponseEntity.noContent().build();
	}
	

 
    @GetMapping("/v1/currencies")
    public ResponseEntity<List> listaModenas() {
        // Crear una solicitud HTTP simulada utilizando el servidor WireMock
        WireMock.configureFor("localhost", wireMockServer.port());
//        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/v1/currencies"))
//            .willReturn(WireMock.aResponse().withBody("Hello, world!")));
 
        // Validar la solicitud utilizando la especificación OpenAPI
        OpenAPIValidationService validationService = new OpenAPIValidationService(openApi);
        validationService.validateRequest("/v1/currencies", "GET", null);
 
        // Realizar la solicitud HTTP utilizando la biblioteca de su elección (por ejemplo, RestTemplate)
        String url = "http://localhost:" + wireMockServer.port() + "/v1/currencies";
        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
 
        // Validar la respuesta utilizando la especificación OpenAPI
        validationService.validateResponse("/v1/currencies", "GET", response.getStatusCodeValue(), null);
 
        return response;
    }
    
    @GetMapping("/v1/currencies/{currencyCode}")
    public ResponseEntity<String> modenaPorCodigo(@PathVariable String code) {
        // Crear una solicitud HTTP simulada utilizando el servidor WireMock
        WireMock.configureFor("localhost", wireMockServer.port());
//        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/v1/currencies"))
//            .willReturn(WireMock.aResponse().withBody("Hello, world!")));
 
        // Validar la solicitud utilizando la especificación OpenAPI
        OpenAPIValidationService validationService = new OpenAPIValidationService(openApi);
        validationService.validateRequest("/v1/currencies/"+code, "GET", null);
 
        // Realizar la solicitud HTTP utilizando la biblioteca de su elección (por ejemplo, RestTemplate)
        String url = "http://localhost:" + wireMockServer.port() + "/v1/currencies";
        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
 
        // Validar la respuesta utilizando la especificación OpenAPI
        validationService.validateResponse("/v1/currencies", "GET", response.getStatusCodeValue(), null);
 
        return response;
    }
	
	
	private static Date getDateFormat(String date) {
	    try {
		    SimpleDateFormat formatter = new SimpleDateFormat(FORMATO_FECHA);
			return formatter.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
}
