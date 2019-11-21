package main;
import catalogoXML.CreadorXML;
import model.alimento.*;
import java.util.ArrayList;
public class Main {

	public static void main(String[] args) {
		
		CreadorXML xmlP = new CreadorXML();
		for(Alimento alimento : xmlP.ObtenerCatalogo()) {
			System.out.println(alimento.getCodigo());
		}
	}

}
