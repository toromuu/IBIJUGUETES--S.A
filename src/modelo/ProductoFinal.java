package modelo;


import java.util.HashMap;

import java.util.Map;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;

import controlador.ControladorIbiJuguetes;

public class ProductoFinal extends Articulo {
	private double precio;
	private int margen;
	private int idDesglose;
	private Map<MateriaPrima,Integer> listaMateriasPrimas;
	
	
	public ProductoFinal(int idArticulo, int idDesglose,String articulo, int margen, HashMap<MateriaPrima,Integer> listaMateriasPrimas,int stock) {
		super(idArticulo, 2, stock, articulo);
		this.setIdDesglose(idDesglose);
		this.margen = margen;
		this.listaMateriasPrimas = new HashMap<MateriaPrima,Integer>(listaMateriasPrimas);
	}
	
	public ProductoFinal(int idArticulo, int idDesglose, String articulo, int margen, HashMap<MateriaPrima,Integer> listaMateriasPrimas) {
		this(idArticulo, idDesglose, articulo, margen,listaMateriasPrimas,0);
	}

	public int getMargen() {
		return margen;
	}

	public void setMargen(int margen) {
		this.margen = margen;
	}

	public Map<MateriaPrima,Integer> getListaMateriasPrimas() {
		return listaMateriasPrimas;
	}

	public void setListaMateriasPrimas(Map<MateriaPrima,Integer> listaMateriasPrimas) {
		this.listaMateriasPrimas = listaMateriasPrimas;
	}

	public int getIdDesglose() {
		return idDesglose;
	}

	public void setIdDesglose(int idDesglose) {
		this.idDesglose = idDesglose;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	@Override
	public void escribirEnPdf(Document documento, String inicio) throws DocumentException {
		 documento.add(new Paragraph(inicio + "\nProducto  Final: " + this.getArticulo() + "\n\tMargen de beneficio: " + this.getMargen() + "%\n"
				 +"Lista de materiales: \n"));
		
		 setListaMateriasPrimas(ControladorIbiJuguetes.getUnicaInstancia().getMateriasPrimasNecesarias(this));
		 
		 listaMateriasPrimas.forEach((k,v) -> {
			try {
				documento.add(new Paragraph(inicio +"\t   "+ v + "\t   " + k.getArticulo()  ));
			} catch (DocumentException e) {
			    System.out.println("No se ha podido imprimir correctamente");
			}
		});
			 
		}
		  
	}
	
	

