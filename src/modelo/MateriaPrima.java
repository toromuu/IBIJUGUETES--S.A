package modelo;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;

public class MateriaPrima extends Articulo {
	
	private double precioUnidadMedia;
	
	
	public MateriaPrima(int idArticulo, String articulo, double precio, int stock) {
		super(idArticulo, 1, stock,articulo);
		this.precioUnidadMedia = precio;
		
	}
	
	public MateriaPrima(int idArticulo, String articulo, double precio) {
		this( idArticulo, articulo,  precio,0);
	
	}
	
	public double getPrecio() {
		return precioUnidadMedia;
	}


	@Override
	public void escribirEnPdf(Document documento, String inicio) throws DocumentException {
		 documento.add(new Paragraph(inicio + "\nMateria Prima: " + this.getArticulo() + "\n\tStock: " + this.getStock()
		 + "\n\tPUM: " + this.getPrecio() + "\n\n" ));
	}
	
	
}
