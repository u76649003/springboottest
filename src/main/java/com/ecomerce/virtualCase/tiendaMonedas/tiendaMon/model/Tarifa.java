package com.ecomerce.virtualCase.tiendaMonedas.tiendaMon.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="T_RATES", schema="public")
public class Tarifa {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //para que se autoincremente
	private Integer id;
	
	@JsonProperty("brand_id")
	@Column(name="brand_id")
	private Integer marca;
	
	@JsonProperty("product_id")
	@Column(name="product_id")
	private Integer producto;
	
	@JsonProperty("start_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Column(name="start_date")
	private Date startDate;
	
	@JsonProperty("end_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Column(name="end_date")
	private Date endDate;
	
	@JsonProperty("price")
	private Double price;
	
	@JsonProperty("currency_code")
	@Column(name="currency_code")
	private String moneda;	

}

//static class DecimalJsonSerializer extends JsonSerializer<Double> {
//    @Override
//    public void serialize(Double value, JsonGenerator jgen, SerializerProvider provider)
//            throws IOException, JsonProcessingException {
//        jgen.writeString( String.format("%.2f", value));
//    }
//}
