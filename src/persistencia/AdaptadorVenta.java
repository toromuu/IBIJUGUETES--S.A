package persistencia;



import modelo.Venta;



public class AdaptadorVenta implements IAdaptadorVentaDAO {
	// Usa un pool para evitar problemas doble referencia con cliente


	private static AdaptadorVenta unicaInstancia;

	public static AdaptadorVenta getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null)
			return new AdaptadorVenta();
		else
			return unicaInstancia;
	}

	
	private AdaptadorVenta() { 
		
	}

	
	public boolean registrarVenta(AccesoBd conexion,Venta venta) {
		String registroVenta= "INSERT INTO Ventas " + 
				"(IdArticulo, Cantidad, Precio,Cliente,FechaVenta) VALUES " + 
				"("+venta.getIdArticulo()+","+venta.getCantidad()+","+
				+venta.getPrecio()+",'"+venta.getCliente()+"',SYSDATE)";
		int registro=conexion.insertar(registroVenta);
		return (registro != 0);
		
		
	}

	public double recuperarPrecioVentas(AccesoBd conexion){
		return conexion.consultarPrecioVentas();
	}


}
