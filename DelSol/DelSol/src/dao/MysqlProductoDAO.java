package dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import controller.JdbcConnection;

import model.Producto;

public class MysqlProductoDAO implements ProductoDAO{

	public void agregarProducto(Producto p) {
		String sql = "insert into producto(nombre, precio_venta, precio_compra, ganancia) values (?,?,?,?)";
		Object [][] params = new Object[1][5];
		params[0][0] = "producto";
		params[0][1] = p.getNombre();
		params[0][2] = (Float)p.getPrecioVenta();
		params[0][3] = (Float)p.getPrecioCompra();
		params[0][4] = (Float)p.getGanancia();
		JdbcConnection con = new JdbcConnection();
		con.actualizar(sql, params);
	}

	public void actualizarProducto(Producto p) {
		String sql = "update producto set precio_venta = ?,precio_compra = ?, ganancia = ? where nombre = '" + p.getNombre()+"'";
		Object[][] params = new Object[1][4];
		params[0][0] = "producto";
		params[0][1] = p.getPrecioVenta();
		params[0][2] = p.getPrecioCompra();
		params[0][3] = p.getGanancia();
		JdbcConnection con = new JdbcConnection();
		con.actualizar(sql, params);
	}

	public Producto buscar(Producto p) {
		String sql = "select nombre, precio_venta, precio_compra, ganancia from producto where nombre = '"+p.getNombre()+"'";
		JdbcConnection con = new JdbcConnection();
		List<List<String>> res = con.consulta(sql);
		if(res != null){
			p.setNombre((String)res.get(1).get(0));
			p.setPrecioVenta(Float.parseFloat((String)res.get(1).get(1)));
			p.setPrecioCompra(Float.parseFloat((String)res.get(1).get(2)));
			p.setGanancia(Float.parseFloat((String)res.get(1).get(3)));
			return p;
		}
		return null;
	}

	public void eliminarProducto(Producto p) {


	}

	public List<Producto> listar() {

		return null;
	}

	public List<String> retProductos(){
		List<String> resultado = new ArrayList<String>();
		String sql = "SELECT nombre FROM producto;";
		String nombre = null;
		JdbcConnection con = new JdbcConnection();
		List<List<String>> res = con.consulta(sql);
		Iterator iterEXT = res.iterator();
		Integer cont = 0;
		if (res != null) {
			while(iterEXT.hasNext()){
				List<String> fila = (List<String>) iterEXT.next();
				Iterator itIN = fila.iterator();
				while(itIN.hasNext()){
					if (cont == 0){
						cont++;
						itIN.next();
					}else{
						nombre = itIN.next().toString();
						resultado.add(nombre);
						cont++;
					}

				}
			}
		}
		return resultado;
	}

}
