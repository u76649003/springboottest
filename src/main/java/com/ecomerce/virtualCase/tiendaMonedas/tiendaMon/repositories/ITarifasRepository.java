package com.ecomerce.virtualCase.tiendaMonedas.tiendaMon.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomerce.virtualCase.tiendaMonedas.tiendaMon.model.Tarifa;

@Repository
public interface ITarifasRepository extends JpaRepository<Tarifa, Integer> {
	
	List<Tarifa> findByStartDateAndMarcaAndProducto(Date start_date, Integer brand_id, Integer product_id);

}
