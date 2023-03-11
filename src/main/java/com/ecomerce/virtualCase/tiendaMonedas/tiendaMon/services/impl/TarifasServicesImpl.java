package com.ecomerce.virtualCase.tiendaMonedas.tiendaMon.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomerce.virtualCase.tiendaMonedas.tiendaMon.mock.ServerMock;
import com.ecomerce.virtualCase.tiendaMonedas.tiendaMon.model.Tarifa;
import com.ecomerce.virtualCase.tiendaMonedas.tiendaMon.repositories.ITarifasRepository;
import com.ecomerce.virtualCase.tiendaMonedas.tiendaMon.services.TarifasServices;

@Service
public class TarifasServicesImpl  implements TarifasServices{
	@Autowired
	private ITarifasRepository tarifasRepository;
	@Autowired
	ServerMock mock;
	
	//Crear una tarifa nueva
	public Tarifa crearTarifa(Tarifa tarifa) {
		return tarifasRepository.save(tarifa);
	}
	
	//Recuperar tarifa por id
	public Tarifa obtenerTarifa(Integer id) {
		return tarifasRepository.findById(id).get();
	}
	
	//Eliminar una tarifa
	public Boolean deleteTarifa(Integer id) {
		try {
			tarifasRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public List<Tarifa> obtenerListaTarifas(){
		return tarifasRepository.findAll();
	}
	
	public List<Tarifa> obtenerTarifasPorFechaMarcaProducto(Date start_date, Integer brand_id, Integer product_id) {
		return tarifasRepository.findByStartDateAndMarcaAndProducto(start_date, brand_id, product_id);
	}
	
	public Tarifa actualizarTarifa(Tarifa tarifa) {
		if(tarifa.getId() != null && tarifasRepository.existsById(tarifa.getId())){
			return tarifasRepository.save(tarifa);
		}
		return null;
	}
}
