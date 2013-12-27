package model;

public class Producto {
	private int idProducto;
	private String nombre;
	private Float precioVenta;
	private Float precioCompra;
	private Float ganancia;


	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Float getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(Float precioVenta) {
		this.precioVenta = precioVenta;
	}

	public Float getPrecioCompra() {
		return precioCompra;
	}
	public void setPrecioCompra(Float precioCompra) {
		this.precioCompra = precioCompra;
	}
	public Float getGanancia() {
		return ganancia;
	}
	public void setGanancia(Float ganancia) {
		this.ganancia = ganancia;
	}


}
