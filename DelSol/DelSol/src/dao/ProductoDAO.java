package dao;

import java.util.List;

import model.Producto;

public interface ProductoDAO {
	public void agregarProducto(Producto p);
	public void actualizarProducto(Producto p);
	public void eliminarProducto(Producto p);
	public Producto buscar(Producto p);

	public List<Producto> listar();
	public List<String> retProductos();
}
