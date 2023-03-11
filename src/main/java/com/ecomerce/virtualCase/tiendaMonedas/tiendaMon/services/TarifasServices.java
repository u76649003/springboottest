package com.ecomerce.virtualCase.tiendaMonedas.tiendaMon.services;
import java.util.Date;
import java.util.List;

import com.ecomerce.virtualCase.tiendaMonedas.tiendaMon.model.Tarifa;
	
public interface TarifasServices {
	
	//Crear una tarifa nueva
	public Tarifa crearTarifa(Tarifa tarifa);
	
	//Recuperar tarifa por id
	public Tarifa obtenerTarifa(Integer id);
	
	//Eliminar una tarifa
	public Boolean deleteTarifa(Integer id);
	
	public List<Tarifa> obtenerTarifasPorFechaMarcaProducto(Date start_date, Integer brand_id, Integer product_id);
	
	public List<Tarifa> obtenerListaTarifas();
	
	public Tarifa actualizarTarifa(Tarifa tarifa);
	
}
