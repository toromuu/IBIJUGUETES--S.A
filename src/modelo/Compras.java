package modelo;


/*
 * Reponer
 */
public class Compras {
	private int idCompra;
	private int idArticulo;
	private final String proveedor;
	private final int cantidad;
	private final double precio;
	
	public Compras(	int idCompra , int idArticulo ,String proveedor , int cantidad , double precio) {
		this.idCompra=idCompra;
		this.idArticulo=idArticulo;
		this.proveedor=proveedor;
		this.cantidad=cantidad;
		this.precio=precio;
	}

	public Compras(int idCompra2, int idArticulo2, int cantidad) {
		this.idCompra=idCompra2;
		this.idArticulo=idArticulo2;
		this.proveedor="";
		this.cantidad=cantidad;
		this.precio=0;
	}

	public int getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}

	public int getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(int idArticulo) {
		this.idArticulo = idArticulo;
	}

	public String getProveedor() {
		return proveedor;
	}

	public int getCantidad() {
		return cantidad;
	}

	public double getPrecio() {
		return precio;
	}

}
