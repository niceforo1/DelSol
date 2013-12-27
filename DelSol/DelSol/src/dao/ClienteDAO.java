package dao;

import java.sql.ResultSet;
import java.util.List;

import model.Cliente;


public interface ClienteDAO {
	public void agregarCliente(Cliente p);
	public void actualizarCliente(Cliente p);
	public void eliminarCliente(Cliente p);
	public Cliente buscar(Cliente p);

	public List<Cliente> listar();
	public List<String> retClientes();
	public ResultSet llenarJtable();
	public Float totalCliVentas(Cliente c);
	public List<String> retTotClientes();
}
