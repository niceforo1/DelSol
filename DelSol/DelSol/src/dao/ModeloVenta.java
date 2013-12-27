package dao;

import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

import model.Cliente;
import model.Venta;

public class ModeloVenta extends AbstractTableModel{
	private LinkedList<Venta> datos;
	private String [] columnas;

	public ModeloVenta(){
		columnas = new String[3];
		columnas[0]= "Producto";
		columnas[1]= "Cantidad";
		columnas[2]= "Precio";
		datos = new LinkedList<Venta>();
	}

	public int getColumnCount() {
		return columnas.length;
	}

	public int getRowCount() {
		return datos.size();
	}

	public String getColumnName(int column) {
		return columnas[column];
	}

	public void addVenta(Venta v){
		datos.add(v);
		this.fireTableDataChanged();
	}

	public void vaciarModeloVenta(){
		datos.clear();
		this.fireTableDataChanged();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Venta auxv = datos.get(rowIndex);
		switch(columnIndex){
		case 0: return auxv.getProduct().getNombre();
		case 1: return auxv.getCantidad();
		case 2: return auxv.getProduct().getPrecioVenta();
		}
		return null;
	}

	public Venta retornarVenta(int index){
		return datos.get(index);
	}

}
