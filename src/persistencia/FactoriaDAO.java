package persistencia;

//Define una factoria abstracta que devuelve todos los DAO de la aplicacion

public abstract class FactoriaDAO {
	private static FactoriaDAO unicaInstancia;
	
	public static final String DAO = "persistencia.IBIFactoriaDAO";
		
	/** 
	 * Crea un tipo de factoria DAO.
	 */
	public static FactoriaDAO getInstancia(String tipo) throws DAOException{
		if (unicaInstancia == null)
			try { unicaInstancia=(FactoriaDAO) Class.forName(tipo).newInstance();
			} catch (Exception e) {	
				throw new DAOException(e.getMessage());
			} 
		return unicaInstancia;
	}


	public static FactoriaDAO getInstancia() throws DAOException{
			if (unicaInstancia == null) return getInstancia (FactoriaDAO.DAO);
					else return unicaInstancia;
			
	}

	/* Constructor */
	protected FactoriaDAO (){}
		
	// Metodos factoria que devuelven adaptadores que implementen estos interfaces
	public abstract IAdaptadorArticuloDAO getArticuloDAO();
	public abstract IAdaptadorComprasDAO getCompraDAO();
	public abstract IAdaptadorVentaDAO getVentaDAO();
	public abstract IAdaptadorProduccionDAO getProduccionDAO();
	

}
