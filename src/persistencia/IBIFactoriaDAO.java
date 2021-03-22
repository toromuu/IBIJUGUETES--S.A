package persistencia;

public class IBIFactoriaDAO extends FactoriaDAO {
	
	public IBIFactoriaDAO () {
	}
	
	@Override
	public IAdaptadorVentaDAO getVentaDAO() {
		return AdaptadorVenta.getUnicaInstancia();
	}

	
	public IAdaptadorComprasDAO getCompraDAO(){
		return  AdaptadorCompras.getUnicaInstancia();
	}
	
	
	public IAdaptadorArticuloDAO getArticuloDAO() {
		return AdaptadorArticulo.getUnicaInstancia();
	}
	

	public IAdaptadorProduccionDAO getProduccionDAO() {
		return AdaptadorProduccion.getUnicaInstancia();
	}

}


