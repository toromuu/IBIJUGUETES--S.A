package persistencia;


import modelo.Venta;

public interface IAdaptadorVentaDAO {

	public boolean registrarVenta(AccesoBd conexion,Venta venta);
	public double recuperarPrecioVentas(AccesoBd conexion);

}
