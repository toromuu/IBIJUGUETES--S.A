package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


import modelo.Articulo;

import modelo.MateriaPrima;
import modelo.Produccion;
import modelo.ProductoFinal;
import modelo.RegistroOperacion;

public class AccesoBd {
	private Connection connection;
	private String serverName ="oracle10.inf.um.es";
	private String portNumber = "1521";
	private String sid ="orcl11";
	private String username = "DSI04";
	private String password = "morla";
	private static AccesoBd unicaInstancia = new AccesoBd();
	
	// PATRON SINGLETON
	public static AccesoBd getUnicaInstancia() {
		if (unicaInstancia == null)
			{unicaInstancia = new AccesoBd();}
		unicaInstancia.initDB();//si no funciona aqui hacerlo de forma manual tras el getunicainstancia
		return unicaInstancia;
	}
	
	private Connection initDB()  {
		connection = null;
		try {
			String driverName ="oracle.jdbc.driver.OracleDriver";
			Class.forName(driverName);
			String url = "jdbc:oracle:thin:@"+serverName +":" + portNumber +":"+sid;
			connection=DriverManager.getConnection(url,username,password);
		} catch (ClassNotFoundException e ) {
			System.out.println("No se encuentra driver");
		} catch (SQLException e) {
			System.out.println("No se puede conectar");
		}
		System.out.println(connection.toString());
		
		return connection;
	}
	
	public Connection getConexion() {
		return connection;
	}
	
	public double consultaSaldo(){
		double saldo=0.0;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT saldo FROM saldo");
			while(rs.next()){
				saldo=rs.getDouble("SALDO");
			}	
			rs.close();
			stmt.close();

		} catch (Exception e) {
			System.out.println("Consulta Saldo Erronea");
		}
		return saldo;
	}
	
	
	public List<Articulo> consultaArticulos(){
		String consultaArticulos = "SELECT IdArticulo,idtipoarticulo, Articulo, Stock, Precio,Margen FROM articulos";
		LinkedList<Articulo> articulos= new LinkedList<Articulo>();
		
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(consultaArticulos);
			
			if (rs != null) {
				
				while (rs.next()) {
					
					int idTipoArticulo = rs.getInt("IDTIPOARTICULO");
					int idArticulo =0;
					String articulo = rs.getString("ARTICULO");
					int stock = rs.getInt("STOCK");
					int desglose =  0;
					if (idTipoArticulo == 1) {
					    double precio = rs.getDouble("PRECIO");
						articulos.add( new MateriaPrima(idArticulo,articulo,precio,stock));

					} else {
						int margen = rs.getInt("MARGEN");
						HashMap<MateriaPrima,Integer> listaMateriasPrimas = new HashMap<MateriaPrima,Integer>();
						articulos.add( new ProductoFinal(idArticulo,desglose,articulo,margen,listaMateriasPrimas,stock));
					}
				}
			}
			stmt.close();
			return articulos;
			
		}catch (Exception e) {
			System.out.println("Consulta Lista Articulos Erronea");
		}
		return null;
	}
	
	
	// Obtiene todas las materias primas
	public List<MateriaPrima> consultaMatPrimas(){
		String consultaMateriasPrimas = "SELECT IdArticulo, Articulo, Stock, Precio FROM articulos WHERE idtipoarticulo=1";
		LinkedList<MateriaPrima> materias = new LinkedList<MateriaPrima>();
		
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(consultaMateriasPrimas);
				while (rs.next()) {
					int idArticulo = rs.getInt("IDARTICULO");
					String articulo = rs.getString("ARTICULO");
				    double precio = rs.getDouble("PRECIO");
					materias.add(new MateriaPrima(idArticulo, articulo, precio));
				}
			stmt.close();
			return materias;
			
		}catch (Exception e) {
			System.out.println("Consulta Lista Materias Primas Erronea");
		}
		return null;
	}
	
	
	public List<ProductoFinal> consultaProdFinales(){
		String consultaProductoFinal = "SELECT IdArticulo, Articulo, Stock,IDDesglose, Margen FROM articulos WHERE idtipoarticulo=2";
		LinkedList<ProductoFinal> productosfinales= new LinkedList<ProductoFinal>();
		
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(consultaProductoFinal);
			
			if (rs != null) {

				while (rs.next()) {
					
					String articulo = rs.getString("ARTICULO");
					int stock = rs.getInt("STOCK");
					int margen = rs.getInt("MARGEN");
					int desglose = rs.getInt("IDDESGLOSE");
					int idArticulo = rs.getInt("IDARTICULO");

					HashMap<MateriaPrima,Integer> listaMateriasPrimas = new HashMap<MateriaPrima,Integer>();
					productosfinales.add( new ProductoFinal(idArticulo,desglose,articulo,margen,listaMateriasPrimas,stock));
					}
				}
			stmt.close();
			return productosfinales;
				
		}
				
		catch (Exception e) {
			System.out.println("Consulta Lista Productos finales Erronea");
		}
		return null;
	}
	
	
	public int consultaMaxId(String consultaID){
		int idMax=0;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(consultaID);
			while(rs.next()) idMax=rs.getInt(1);
		} catch (SQLException e1) {
			System.out.println("Consulta Max Id Erronea");
		}
		return idMax;
	}
	
	public int consultaIdArticulo(Articulo articulo){
		int idArticulo=0;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT idarticulo from articulos where articulo='"+articulo.getArticulo()+"'");
			while(rs.next()) idArticulo=rs.getInt(1);
		} catch (SQLException e1) {
			System.out.println("Consulta IdArticulo Erronea");
		}
		return idArticulo;
	}
	
	
	public Articulo consultaObtenerArticulo(int idArticulo) {
		Articulo articulo=null;
		HashMap<MateriaPrima, Integer> listaMateriasPrimas= new HashMap<>();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * from articulos where idarticulo="+idArticulo);
			while(rs.next()) {
				int idDesglose=rs.getInt("iddesglose");
				String nombre=rs.getString("articulo");
				int margen=rs.getInt("margen");
				double precio= rs.getDouble("precio");
				if(rs.getInt("idtipoarticulo")==1){
					MateriaPrima mp = new MateriaPrima(idArticulo, nombre, precio);
					return mp;
				}else{
					listaMateriasPrimas=this.consultaObtenerMateriasArticulo(nombre);
					ProductoFinal pf = new ProductoFinal(idArticulo, idDesglose, nombre, margen, listaMateriasPrimas);
					pf.setPrecio(precio);
					return pf;
				}
			}
		} catch (SQLException e1) {
			System.out.println("Consulta ObtenerArticulo Erronea");

		}
		return articulo;
	}
	
	
	public HashMap<MateriaPrima, Integer> consultaObtenerMateriasArticulo(String nombreArticulo){
		HashMap<MateriaPrima, Integer> materiasPrimas=new HashMap<>();
		int idarticulo;
		String nombre;
		double precio;
		int stock;
		String query =	"SELECT aa2.idarticulo, aa2.articulo, aa2.precio, ad.cantidad,aa2.stock "+
				"FROM Articulos aa "+
				"inner join articulosDesglose ad on aa.iddesglose=ad.iddesglose "+
				"inner join articulos aa2 on aa2.idarticulo=ad.idarticulo "+
				"WHERE aa.articulo= '"+nombreArticulo+"'"
				;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				idarticulo=rs.getInt("idarticulo");
				nombre=rs.getString("articulo");
				precio= rs.getDouble("precio");
				stock= rs.getInt("stock");
				MateriaPrima materiaPrima=new MateriaPrima(idarticulo, nombre, precio,stock);
				materiasPrimas.put(materiaPrima, rs.getInt("cantidad"));
			}
		} catch (SQLException e1) {
			System.out.println("Consulta Materias primas del articulo: "+nombreArticulo+" Erronea");

		}
		return materiasPrimas;
	}
	
	
	public boolean consultaMatPriSuficientes(Articulo articulo,int nArticulos){
		int stock=0;
		int cantNecesaria=0;
		String query =	"SELECT aa2.stock , ad.cantidad "+
						"FROM Articulos aa "+
						"inner join articulosDesglose ad on aa.iddesglose=ad.iddesglose "+
						"inner join articulos aa2 on aa2.idarticulo=ad.idarticulo "+
						"WHERE aa.articulo= '"+articulo.getArticulo()+"'"
						;
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				stock=rs.getInt(1);
				cantNecesaria=rs.getInt(2);
				if (stock < cantNecesaria*nArticulos) return false;
			}
		} catch (SQLException e1) {
			System.out.println("Consulta Materias primas suficientes Erronea");

		}
		return true;
	}
	
	public Produccion consultaUltimaProduccion(){
		Produccion ultima = null ;
		String query ="SELECT * FROM produccion WHERE idproduccion= (SELECT max(idproduccion)FROM produccion)";
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				int idProduccion=rs.getInt("idProduccion");
				int idArticulo =rs.getInt("idArticulo"); 
				int idConjunto=rs.getInt("idconjunto"); 
				int cantidad=rs.getInt("cantidad"); 
				double coste=rs.getDouble("coste"); 
				ultima = new Produccion(idProduccion, idArticulo, idConjunto, cantidad, coste);
			}
		} catch (SQLException e1) {
			System.out.println("Consulta Ultima Produccion Erronea");
		}
		return ultima;
	}
	
	public double consultarPrecioCompras(){
		double precio=0.0;
		String query ="SELECT sum(cantidad * precio ) as total FROM compras";
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			precio=rs.getDouble("total");
			
		} catch (SQLException e1) {
			System.out.println("Consulta Saldo Compras Erronea");

		}
		return precio;
	}
	
	public double consultarCosteProduccionTotal(){
		double coste=0.0;
		String query ="select sum(stock*precio) as Activos from articulos where idtipoarticulo=1";
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			coste=rs.getDouble("Activos");
			
		} catch (SQLException e1) {
			System.out.println("Consulta coste Produccion total erronea");

		}
		return coste;
	}
	
	public double consultarPrecioVentas(){
		double precio=0.0;
		String query ="select sum(cantidad * precio ) as total FROM ventas";
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			precio=rs.getDouble("total");
			
		} catch (SQLException e1) {
			System.out.println("Consulta Saldo Compras Erronea");

		}
		return precio;
	}
	
	
	public double consultarBeneficioEsperado(){
		double precio=0.0;
		String query =
				"select" + 
				"        sum (((stock * precio) * 100) /  (100 - margen)) margen ," + 
				"        sum (stock * precio) costeTotal,"+
				"        sum(((stock * precio) * 100) /  (100 - margen)) - sum(stock * precio) BeneficioEsperado" + 
				" from articulos where idtipoarticulo=2";
		
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			precio=rs.getDouble("BeneficioEsperado");
			
		} catch (SQLException e1) {
			System.out.println("Consulta Beneficio Erronea");
		}
		return precio;
	}
	
	
	public List<RegistroOperacion> consultarRegistroOperaciones()  {
		
		String operacion;
		String articulo;
		int idArticulo;
		String proveedorCliente;
		int cantidad;
		double coste;
		Date fecha;
		
		List<RegistroOperacion> listaOperaciones= new LinkedList<RegistroOperacion>();

		String query = "select a.*,b.articulo from\r\n" + 
				"(select 'Compra' as Operacion, IdArticulo, Proveedor as ProveedorCliente,cantidad,precio, fechacompra as fecha from compras\r\n" + 
				"union all\r\n" + 
				"select 'Producción', IdArticulo,null,cantidad,coste,fechaproduccion from produccion\r\n" + 
				"union all\r\n" + 
				"select 'Venta',IdArticulo,Cliente,cantidad,precio,fechaventa from ventas) a\r\n" + 
				"inner join articulos b on a.idarticulo=b.idarticulo";
		
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
	
			while(rs.next()){
				operacion = rs.getString("Operacion");
				articulo = rs.getString("Articulo");
				idArticulo = rs.getInt("idArticulo");
				proveedorCliente =rs.getString("ProveedorCliente");
				cantidad = rs.getInt("cantidad");
				coste = rs.getDouble("precio");
				fecha= rs.getDate("fecha");
				
				RegistroOperacion registro = new RegistroOperacion(operacion, articulo, idArticulo, proveedorCliente, cantidad, coste, fecha);
				listaOperaciones.add(registro);

			}
		
		} catch (SQLException e1) {
			System.out.println("Consulta  Erronea");
		}
		
		return listaOperaciones;
		
	}


	public int insertar(String insertSQL) {
		int comprobacion=0;
		try {
			Statement stmt = connection.createStatement();
			comprobacion = stmt.executeUpdate(insertSQL);
			stmt.close();
		}catch (Exception e) {
			System.out.println("Insert Erroneo");
		}
		
		return comprobacion;
	}


}
