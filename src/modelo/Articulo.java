package modelo;

import java.util.Date;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;

public abstract class Articulo {
	

	private int idArticulo; 
	private int idTipoArticulo;
	private String articulo;
	private int stock;
	private Date fechaAlta;
	
	public Articulo(int idArticulo, int ideTipoArticulo,int stock, String articulo) {
		
		this.idArticulo = idArticulo;
		this.idTipoArticulo = ideTipoArticulo;
		this.articulo = articulo;
		this.stock = stock;
		this.fechaAlta = new Date();
		
	}
	
	public Articulo(int idArticulo, int ideTipoArticulo, String articulo) {
		this(idArticulo, ideTipoArticulo, 0, articulo);
	}
	

	public int getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(int idArticulo) {
		this.idArticulo = idArticulo;
	}

	public String getArticulo() {
		return articulo;
	}

	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getIdTipoArticulo() {
		return idTipoArticulo;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public abstract void escribirEnPdf(Document documento, String incioFrase) throws DocumentException;
	
}
