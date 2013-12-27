package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import reportes.VentasDataSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import controller.JdbcConnection;
import controller.ParametrosAccion;

import model.Cliente;
import model.Producto;
import model.Venta;

public class MysqlVentaDAO implements VentaDAO {

	public void actualizarVenta(Venta v) {

	}


	public void agregarVenta(Venta v) {
		String DATE_FORMAT = "yyyy/MM/dd";
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		String sql = "INSERT INTO ventas(cantidad, fecha, nombre_producto, precio_producto, numero_cliente, nombre_cliente, pago)VALUES(?,?,?,?,?,?,?)";
		Object [][] params = new Object[1][8];
		params[0][0] = "venta";
		params[0][1] = Integer.toString(v.getCantidad());
		params[0][2] = sdf.format(v.getFecha().getTime()).toString();
		params[0][3] = v.getProduct().getNombre();
		params[0][4] = v.getProduct().getPrecioVenta();
		params[0][5] = Integer.toString(v.getClient().getNumeroCliente());
		params[0][6] = v.getClient().getNombre();
		params[0][7] = "N";
		JdbcConnection con = new JdbcConnection();
		con.actualizar(sql, params);
	}


	public Venta buscar(Venta v) {
		return null;
	}


	public void eliminarVenta(Venta v) {

	}


	public List<Venta> listar(String fechaInicio, String fechaFin) {
		List<Venta> result = new ArrayList<Venta>();
		List<List<String>> ret = null;
		int cont;
		int contIn;
		String DATE_FORMAT = "yyyy-MM-dd";
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
	    String sql = "select cantidad, fecha, nombre_producto, precio_producto, precio_producto*cantidad as total from ventas where fecha between '"+ fechaInicio+ "' and '" +fechaFin +"'";
		JdbcConnection con = new JdbcConnection();
		ret =con.consulta(sql);
		if (ret!= null){
			Iterator iterEXT = ret.iterator();
			cont = 0;
			while(iterEXT.hasNext()){
				List<String> fila = (List<String>) iterEXT.next();
				Iterator IN = fila.iterator();
				if (cont != 0){
					contIn=0;
					Venta venta = new Venta();
					Producto producto = new Producto();
					Calendar fecha = Calendar.getInstance();
					while(IN.hasNext()){
						contIn++;
						String cadena = IN.next().toString();
						if (contIn == 1){
							venta.setCantidad(Integer.parseInt(cadena));
						}else if(contIn==2){
							try {
								fecha.setTime(sdf.parse(cadena));
							} catch (ParseException e) {
								e.printStackTrace();
							}
							venta.setFecha(fecha);
						}else if(contIn==3){
							producto.setNombre(cadena);
							venta.setProduct(producto);
						}else if(contIn==4){
							producto.setPrecioVenta(Float.parseFloat(cadena));
							venta.setProduct(producto);
						}else if(contIn == 5){
							venta.setTotal(Float.parseFloat(cadena));
						}
					}
					result.add(venta);
				}
				cont++;
			}
		}
		return result;
	}


	public ResultSet llenarJtable(ParametrosAccion pars) {
		Cliente cli = new Cliente();
		cli = (Cliente)pars.get("cliente");
		Calendar c = Calendar.getInstance();
		String DATE_FORMAT = "yyyy/MM/dd";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		JdbcConnection jb = new JdbcConnection();
		return jb.llenarJTable("select nombre_producto Producto, cantidad Cantidad, precio_producto Precio from ventas where fecha = '" + sdf.format(c.getTime()).toString() +"' and numero_cliente = "+ cli.getNumeroCliente());
	}


	public List<String> retTotVenta() {
		return null;
	}

	public List<String> retVenta() {
		return null;
	}


	public void generarReporte(String fechaInicio, String fechaFin) {
		JdbcConnection jb = new JdbcConnection();
		List<Venta> ventas= null;
		VentasDataSource dataSource = new VentasDataSource();
		try {
			ventas = listar(fechaInicio,fechaFin);
			Iterator iterVenta = ventas.iterator();
			while(iterVenta.hasNext()){
				Venta venta = new Venta();
				venta = (Venta)iterVenta.next();
				dataSource.addVenta(venta);
			}
			//JasperReport reporte = (JasperReport) JRLoader.loadObject("reportWood.jasper");
			JasperReport reporte = (JasperReport) JRLoader.loadObject("reporte22.jasper");
			JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, dataSource);
			JasperViewer jviewer = new JasperViewer(jasperPrint,false);
			jviewer.setVisible(true);

			//GUARDAR EL REPORTE EN ALGUNA PARTE DE LA PC
			/*JRExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT,jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE,new java.io.File("C:\\reportePDF.pdf"));
			exporter.exportReport();*/
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
}
