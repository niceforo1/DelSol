package controller;

import java.sql.ResultSet;
import java.util.Calendar;
import java.util.List;

import model.Cliente;
import model.Producto;

import dao.AbstractDAOFactory;
import dao.ClienteDAO;
import dao.ProductoDAO;
import dao.VentaDAO;

public class ConsAction extends Controlador{

	public void execute(String name, ParametrosAccion pars) {
		if(name.equals("consProd")) {
			executeConsProd(pars);
		}else if(name.equals("consRetProd")){
			executeConsPart("consRetProd",pars);
		}
	}

	public Object[] executeCons(String name, ParametrosAccion pars) {
		Object[] obj = new Object[4];
		if(name.equals("consProd")) {
			obj = executeConsProd(pars);
		}else if(name.equals("consVentTot")){
			obj = executeConsVenTot(pars);
		}
		return obj;
	}

	protected Object[] executeConsProd(ParametrosAccion pars){
		Object[] obj = new Object[4];
		Producto prod = new Producto();
		prod.setNombre((String)pars.get("producto"));
		ProductoDAO pdao = AbstractDAOFactory.getInstance(AbstractDAOFactory.MYSQL).getProductoDAO();
		prod = pdao.buscar(prod);
		obj[0]= prod.getNombre();
		obj[1] = prod.getPrecioCompra();
		obj[2] = prod.getGanancia();
		obj[3] = prod.getPrecioVenta();
		return obj;

	}

	public List<String> executeConsPart(String name, ParametrosAccion pars) {
		if(name.equals("consRetProd")){
			ProductoDAO pdao = AbstractDAOFactory.getInstance(AbstractDAOFactory.MYSQL).getProductoDAO();
			return pdao.retProductos();
		}else if(name.equals("consTotCli")){
			ClienteDAO cdao = AbstractDAOFactory.getInstance(AbstractDAOFactory.MYSQL).getClienteDAO();
			return cdao.retTotClientes();
		}
		return null;
	}

	public ResultSet executellenarJtable() {
		ClienteDAO cdao = AbstractDAOFactory.getInstance(AbstractDAOFactory.MYSQL).getClienteDAO();
		return cdao.llenarJtable();
	}

	public ResultSet executellenarJtableVenta(ParametrosAccion pars) {
		Cliente cli = new Cliente();
		cli = (Cliente)pars.get("cliente");
		VentaDAO vdao = AbstractDAOFactory.getInstance(AbstractDAOFactory.MYSQL).getVentaDAO();
		return vdao.llenarJtable(pars);
		//return null;
	}

	private Object[] executeConsVenTot(ParametrosAccion pars) {
		Object[] obj = new Object[1];
		Float total= null;
		ClienteDAO cdao = AbstractDAOFactory.getInstance(AbstractDAOFactory.MYSQL).getClienteDAO();
		total = cdao.totalCliVentas((Cliente)pars.get("cliente"));
		obj[0]= total;
		return obj;
	}
}
