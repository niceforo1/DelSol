package controller;

import java.sql.ResultSet;
import java.util.Calendar;
import java.util.List;

import model.Cliente;
import model.Producto;
import model.Venta;
import dao.*;

public class GuarAction extends Controlador{
	public void execute (String name, ParametrosAccion pars){
		if (name.equals("cargaProd")){
			executeCargaProd(pars);
		}else if (name.equals("actuProd")){
			excuteActualizarProd(pars);
		}else if(name.equals("cargaCli")){
			excuteCargaCli(pars);
		}else if(name.equals("guarVenta")){
			executeGuarVenta(pars);
		}else if(name.equals("cerrarCli")){
			executeCerrarCliente(pars);
		}
	}

	public Object[] executeCons(String name, ParametrosAccion pars){
		return null;
	}

	protected void executeCargaProd(ParametrosAccion pars){
		Producto prod = new Producto();
		prod.setNombre((String)pars.get("producto"));
		prod.setPrecioCompra(Float.parseFloat((String)pars.get("costo")));
		prod.setPrecioVenta(Float.parseFloat((String)pars.get("venta")));
		prod.setGanancia(Float.parseFloat((String)pars.get("ganancia")));
		ProductoDAO pdao = AbstractDAOFactory.getInstance(AbstractDAOFactory.MYSQL).getProductoDAO();
		pdao.agregarProducto(prod);
	}

	private void executeGuarVenta(ParametrosAccion pars) {
		Integer cantidad;
		Cliente cli = new Cliente();
		Producto prod = new Producto();
		Venta venta = new Venta();
		cli = (Cliente)pars.get("cliente");
		prod.setNombre((String)pars.get("producto"));
		prod.setPrecioVenta(Float.parseFloat((String)pars.get("precio")));
		venta.setCantidad(Integer.parseInt((String)pars.get("cantidad")));
		venta.setProduct(prod);
		venta.setClient(cli);
		venta.setFecha(Calendar.getInstance());
		VentaDAO vdao = AbstractDAOFactory.getInstance(AbstractDAOFactory.MYSQL).getVentaDAO();
		vdao.agregarVenta(venta);
	}

	protected void excuteActualizarProd(ParametrosAccion pars){
		Producto prod = new Producto();
		prod.setNombre((String)pars.get("producto"));
		prod.setPrecioCompra(Float.parseFloat((String)pars.get("costo")));
		prod.setPrecioVenta(Float.parseFloat((String)pars.get("venta")));
		prod.setGanancia(Float.parseFloat((String)pars.get("ganancia")));
		ProductoDAO pdao = AbstractDAOFactory.getInstance(AbstractDAOFactory.MYSQL).getProductoDAO();
		pdao.actualizarProducto(prod);
	}

	public List<String> executeConsPart(String name, ParametrosAccion pars) {

		return null;
	}

	private void excuteCargaCli(ParametrosAccion pars) {
		Cliente cli = new Cliente();
		cli.setNombre((String)pars.get("cliente"));
		cli.setPago(false);
		cli.setNumeroCliente(Integer.parseInt((String)pars.get("numeroCli")));
		cli.setFecha(Calendar.getInstance());
		ClienteDAO cdao = AbstractDAOFactory.getInstance(AbstractDAOFactory.MYSQL).getClienteDAO();
		cdao.agregarCliente(cli);
	}


	public ResultSet executellenarJtable() {
		return null;
	}

	public ResultSet executellenarJtableVenta(ParametrosAccion pars) {
		return null;
	}

	private void executeCerrarCliente(ParametrosAccion pars) {
		//CERRAR CLIENTE
		Cliente cli = new Cliente();
		cli = (Cliente)pars.get("cliente");
		ClienteDAO cdao = AbstractDAOFactory.getInstance(AbstractDAOFactory.MYSQL).getClienteDAO();
		cdao.actualizarCliente(cli);
	}

}
