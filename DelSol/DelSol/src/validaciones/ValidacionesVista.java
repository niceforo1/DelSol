
package validaciones;

import javax.swing.JOptionPane;

public class ValidacionesVista {
	public boolean validarProducto(String producto, String costo, String ganancia, String venta){
		if(producto.equals("")){
			JOptionPane.showMessageDialog(null,"Por Favor Ingrese el Producto",null,JOptionPane.ERROR_MESSAGE);
			return false;
		}else if(costo.equals("")){
			JOptionPane.showMessageDialog(null,"Por Favor Ingrese el precio de costo",null,JOptionPane.ERROR_MESSAGE);
			return false;
		}else if(ganancia.equals("")){
			JOptionPane.showMessageDialog(null,"Por Favor Ingrese la precio de ganancia",null,JOptionPane.ERROR_MESSAGE);
			return false;
		}else if(venta.equals("")){
			JOptionPane.showMessageDialog(null,"Por Favor Ingrese el Precio de venta",null,JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	public boolean validarVenta(String producto, String cantidad, String precio){
		if(producto.equals("")){
			JOptionPane.showMessageDialog(null,"Por Favor Ingrese el Producto",null,JOptionPane.ERROR_MESSAGE);
			return false;
		}else if(cantidad.equals("")){
			JOptionPane.showMessageDialog(null,"Por Favor Ingrese la Cantidad",null,JOptionPane.ERROR_MESSAGE);
			return false;
		}else if(precio.equals("")){
			JOptionPane.showMessageDialog(null,"Por Favor Ingrese el Precio",null,JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
}
