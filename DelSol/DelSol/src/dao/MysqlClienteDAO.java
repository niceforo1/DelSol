package dao;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import controller.JdbcConnection;

import model.Cliente;

public class MysqlClienteDAO implements ClienteDAO{

	public void agregarCliente(Cliente c) {
		String DATE_FORMAT = "yyyy/MM/dd";
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		String sql = "insert into clientes(id_cliente,nombre, pago, fecha) values (?,?,?,?)";
		Object [][] params = new Object[1][5];
		params[0][0] = "cliente";
		params[0][1] = Integer.toString(c.getNumeroCliente());
		params[0][2] = c.getNombre();
		if(c.isPago()){
			params[0][3] = "Y";
		}else{
			params[0][3] = "N";
		}
		params[0][4] = sdf.format(c.getFecha().getTime()).toString();
		JdbcConnection con = new JdbcConnection();
		con.actualizar(sql, params);
	}

	public void actualizarCliente(Cliente c) {
		String DATE_FORMAT = "yyyy/MM/dd";
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
	    Calendar cal = Calendar.getInstance();
		//String sql = "update cliente set precio_venta = ?,precio_compra = ?, ganancia = ? where nombre = '" + p.getNombre()+"'";
		String sql  = "update clientes set pago = ? where nombre = '" + c.getNombre()+"'" + "and fecha = '" + sdf.format(cal.getTime()).toString()+"'"+"and id_cliente = "+ c.getNumeroCliente();
		Object[][] params = new Object[1][2];
		params[0][0] = "producto";
		params[0][1] = "Y";
		JdbcConnection con = new JdbcConnection();
		con.actualizar(sql, params);
	}

	public Cliente buscar(Cliente c) {
		String sql = "select nombre, precio_venta, precio_compra, ganancia from producto where nombre = '"+c.getNombre()+"'";
		JdbcConnection con = new JdbcConnection();
		List<List<String>> res = con.consulta(sql);
		if(res != null){
			c.setNombre((String)res.get(1).get(0));
		//	p.setPrecioVenta(Integer.parseInt((String)res.get(1).get(1)));
		//	p.setPrecioCompra(Integer.parseInt((String)res.get(1).get(2)));
		//	p.setGanancia(Integer.parseInt((String)res.get(1).get(3)));
			return c;
		}
		return null;
	}

	public void eliminarCliente(Cliente c) {

	}

	public Float totalCliVentas(Cliente c){
		Calendar cal = Calendar.getInstance();
		Float retorno = null;
		String DATE_FORMAT = "yyyy/MM/dd";
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		String sql = "select IFNULL(sum(precio_producto),0) total from ventas where fecha = '" + sdf.format(cal.getTime()).toString() + "' and numero_cliente = "+c.getNumeroCliente();
		JdbcConnection con = new JdbcConnection();
		List<List<String>> result = con.consulta(sql);
		Iterator iterExt =result.iterator();
		Integer cont = 0;
		if (result != null){
			while(iterExt.hasNext()){
				List<String> fila = (List<String>)iterExt.next();
				Iterator itIn = fila.iterator();
				while(itIn.hasNext()){
					if (cont == 0){
						cont++;
						itIn.next();
					}else{
						//if(itIn.next().equals(null)){
							retorno = Float.parseFloat((String)itIn.next());
						//}else{
						//	retorno = Float.parseFloat("0");
						//}

					}
				}
			}
		}
		return retorno;

	}

	public List<Cliente> listar() {
		List<Cliente> result = new ArrayList<Cliente>();
		List<List<String>> ret = null;
		Calendar c = Calendar.getInstance();
		String DATE_FORMAT = "yyyy/MM/dd";
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		String sql = "select nombre as Cliente, pago Pago from clientes where fecha = '" + sdf.format(c.getTime()).toString() +"' and pago = 'N'";
		JdbcConnection con = new JdbcConnection();
		ret =con.consulta(sql);
		System.out.print(ret);
		return null;
	}

	public List<String> retClientes(){
		/*List<String> resultado = new ArrayList<String>();
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
		return resultado;*/
		return null;
	}

	public List<String> retTotClientes(){
		List<String> resultado = new ArrayList<String>();
		Calendar c = Calendar.getInstance();
		String DATE_FORMAT = "yyyy/MM/dd";
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		String sql = "select count(*) Cantidad from clientes where fecha = '"+ sdf.format(c.getTime()).toString()+"';";
		String nombre = null;
		JdbcConnection con = new JdbcConnection();
		List<List<String>> res = con.consulta(sql);
		Iterator iterEXT = res.iterator();
		Integer cont = 0;
		//System.out.println(res.get(2).toString());
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



	public ResultSet llenarJtable() {
		Calendar c = Calendar.getInstance();
		String DATE_FORMAT = "yyyy/MM/dd";
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		JdbcConnection jb = new JdbcConnection();
		return jb.llenarJTable("select id_cliente as Numero, nombre as Cliente, pago as Pago from clientes where fecha = '" + sdf.format(c.getTime()).toString() +"' and pago = 'N'");
	}


}
