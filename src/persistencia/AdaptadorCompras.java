package persistencia;




import modelo.Compras;



public class AdaptadorCompras implements IAdaptadorComprasDAO {

	private static AdaptadorCompras unicaInstancia = null;

	public static AdaptadorCompras getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null) {
			return new AdaptadorCompras();
		} else
			return unicaInstancia;
	}

	private AdaptadorCompras() { 
		
	}

	@Override
	public boolean registrarCompra(AccesoBd conexion,Compras compra) {
		String registroCompra= "INSERT INTO Compras " + 
				"(IdArticulo,Proveedor,Cantidad,FechaCompra,Precio) \n" + 
				"VALUES " + 
				"("+compra.getIdArticulo()+",'"+compra.getProveedor()+"',"+
				+compra.getCantidad()+",SYSDATE,"+compra.getPrecio()+")";
		
		int registro=conexion.insertar(registroCompra);

		return (registro != 0);
	}

	public double recuperarPrecioCompras(AccesoBd conexion){
		return conexion.consultarPrecioCompras();
	}


}
