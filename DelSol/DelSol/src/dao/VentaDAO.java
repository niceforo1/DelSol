package dao;

import java.sql.ResultSet;
import java.util.List;

import controller.ParametrosAccion;

import model.Venta;

public interface VentaDAO {
	public void agregarVenta(Venta v);
	public void actualizarVenta(Venta v);
	public void eliminarVenta(Venta v);
	public Venta buscar(Venta v);

	public List<Venta> listar(String fechaInicio, String fechaFin);
	public List<String> retVenta();
	public ResultSet llenarJtable(ParametrosAccion pars);
	public List<String> retTotVenta();
	public void generarReporte(String fechaInicio, String fechaFin);

}
