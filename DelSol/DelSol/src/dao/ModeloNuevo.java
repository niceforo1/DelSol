package dao;

import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;
import model.Cliente;

public class ModeloNuevo extends AbstractTableModel{
	private LinkedList<Cliente> datos;
	private String [] columnas;

	public ModeloNuevo(){
		columnas = new String[3];
		columnas[0]= "Numero";
		columnas[1]= "Nombre";
		columnas[2]= "Pago";
		datos = new LinkedList<Cliente>();
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

	public void addCliente(Cliente c){
		datos.add(c);
		this.fireTableDataChanged();
	}

	public void vaciarModeloNuevo(){
		datos.clear();
		this.fireTableDataChanged();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Cliente auxc = datos.get(rowIndex);
			switch(columnIndex){
			case 0: return auxc.getNumeroCliente();
			case 1: return auxc.getNombre();
			case 2: if(auxc.isPago()){
						return "Y";
					}else{
						return "N";
					}
		}
		return null;
	}
	public Cliente retornarCliente(int index){
		return datos.get(index);
	}
}
