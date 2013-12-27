package dao;

public class MysqlDAOFactory extends AbstractDAOFactory {

	public ProductoDAO getProductoDAO() {
		return new MysqlProductoDAO();
	}

	public ClienteDAO getClienteDAO() {
		return new MysqlClienteDAO();
	}

	public VentaDAO getVentaDAO() {
		return new MysqlVentaDAO();
	}
}
