package controller;

import java.sql.ResultSet;
import java.util.List;

import javax.swing.JDesktopPane;

import dao.AbstractDAOFactory;
import dao.VentaDAO;

import view.Principal;
import view.ViewProducto;
import view.ViewReportes;
import view.ViewVenta;

public class EjecPant extends Controlador {

	public void execute(String name, ParametrosAccion pars) {
		if(name.equals("loginForm")){
			executeAbrirAplicacion(pars);
		}else if(name.equals("producto")){
			executeAbrirProducto(pars);
		}else if(name.equals("venta")){
			executeAbrirVenta(pars);
		}else if(name.equals("reportes")){
			executeAbrirRep(pars);
		}else if (name.equals("genReporteVentas")){
			executeGenReport(pars);
		}

	}

	public Object[] executeCons(String name, ParametrosAccion pars) {
		return null;
	}

	protected void executeAbrirAplicacion(ParametrosAccion pars){
		Principal owner = (Principal)pars.get("principal");
		owner.setVisible(true);
	}

	private void executeAbrirProducto(ParametrosAccion pars) {
		JDesktopPane panel = (JDesktopPane)pars.get("desk");
		ViewProducto viewProd = new ViewProducto();
		viewProd.setVisible(true);
		panel.add(viewProd);
	}

	private void executeAbrirVenta(ParametrosAccion pars) {
		JDesktopPane panel = (JDesktopPane)pars.get("desk");
		ViewVenta viewVenta = new ViewVenta();
		viewVenta.setVisible(true);
		panel.add(viewVenta);
	}
	private void executeAbrirRep(ParametrosAccion pars){
		JDesktopPane panel = (JDesktopPane)pars.get("desk");
		ViewReportes viewRep = new ViewReportes();
		viewRep.setVisible(true);
		panel.add(viewRep);
	}
	private void executeGenReport(ParametrosAccion pars){
		String fechaInicio, fechaFin;
		fechaInicio = (String)pars.get("fechaInicio");
		fechaFin = (String)pars.get("fechaFin");
		/*System.out.println(fechaInicio);
		System.out.println(fechaFin);*/
		VentaDAO vdao = AbstractDAOFactory.getInstance(AbstractDAOFactory.MYSQL).getVentaDAO();
		vdao.generarReporte(fechaInicio, fechaFin);
	}

	public List<String> executeConsPart(String name, ParametrosAccion pars) {
		// TODO Auto-generated method stub
		return null;
	}

	public ResultSet executellenarJtable() {

		return null;
	}

	@Override
	public ResultSet executellenarJtableVenta(ParametrosAccion pars) {
		// TODO Auto-generated method stub
		return null;
	}


}
