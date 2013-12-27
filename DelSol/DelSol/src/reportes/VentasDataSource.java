package reportes;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Venta;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;


public class VentasDataSource implements JRDataSource{
	private List<Venta> listaVentas = new ArrayList<Venta>();
	private int indiceVenta = -1;
	String DATE_FORMAT = "dd-MM-yyyy";
    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
    Float total = 0F;

	public Object getFieldValue(JRField jrField) throws JRException {
		Object valor = null;
		if("cantidad".equals(jrField.getName())){
			valor = listaVentas.get(indiceVenta).getCantidad();
		}else if("nombProd".equals(jrField.getName())){
			valor = listaVentas.get(indiceVenta).getProduct().getNombre();
		}else if("fecha".equals(jrField.getName())){
			valor = sdf.format(listaVentas.get(indiceVenta).getFecha().getTime());
		}else if("precioProd".equals(jrField.getName())){
			valor = listaVentas.get(indiceVenta).getProduct().getPrecioVenta();
		}else if("total".equals(jrField.getName())){
			total = total + listaVentas.get(indiceVenta).getProduct().getPrecioVenta();
			valor = total;
		}
		return valor;
	}

	public boolean next() throws JRException {
		return ++indiceVenta < listaVentas.size();
	}

	public void addVenta(Venta venta){
	    this.listaVentas.add(venta);
	}
}
