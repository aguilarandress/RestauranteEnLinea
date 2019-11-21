package main;
import catalogoXML.CreadorXML;
import model.alimento.*;


public class Main {

	public static void main(String[] args) {
		
		CreadorXML xmlP = CreadorXML.getInstance();
		for(Alimento alimento : xmlP.ObtenerCatalogo()) {
			System.out.println(alimento.getCodigo());
		}
	}
}