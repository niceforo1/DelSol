package model;

import java.util.Calendar;
import java.util.Date;

public class Venta {
	private int idVenta;
	private Producto product;
	private Cliente client;
	private int cantidad;
	private Float total;
	private Calendar fecha;


	public int getIdVenta() {
		return idVenta;
	}
	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}


	public Producto getProduct() {
		return product;
	}
	public void setProduct(Producto product) {
		this.product = product;
	}


	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public Float getTotal() {
		return total;
	}
	public void setTotal(Float total) {
		this.total = total;
	}


	public Calendar getFecha() {
		return fecha;
	}
	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}


	public Cliente getClient() {
		return client;
	}
	public void setClient(Cliente client) {
		this.client = client;
	}
}
