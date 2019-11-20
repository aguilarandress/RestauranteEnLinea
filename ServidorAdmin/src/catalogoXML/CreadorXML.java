package catalogoXML;
import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import model.alimento.Alimento;
import model.alimento.TipoAlimento;

public class CreadorXML {
	private static final String path = "Catalogo/Catalogo.xml";
	
	/**
	 * Crea el catalogo en el xml.
	 * @param alimentos ArrayList de alimentos que pertenecen al catalogo.
	 */
	public void RecrearCatalogo(ArrayList<Alimento> alimentos) {
		try
	    {
	        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	        Document documento = docBuilder.newDocument();
	        
	        Element elementoCatalogo = documento.createElement("Catalogo");
	        documento.appendChild(elementoCatalogo);
	        // Crear los tipos de alimentos.
	        Element elementoEntradas = documento.createElement("Entradas");
	        Element elementoPlatosF = documento.createElement("Platos_Fuertes");
	        Element elementoPostres = documento.createElement("Postres");
	        Element elementoBebidas = documento.createElement("Bebidas");
	        
	        // Añadir los tipos de elementos al xml.
	        elementoCatalogo.appendChild(elementoEntradas);
	        elementoCatalogo.appendChild(elementoPlatosF);
	        elementoCatalogo.appendChild(elementoPostres);
	        elementoCatalogo.appendChild(elementoBebidas);
	        // Recorrer cada alimento y añadirlo a su sección correspondiente.
	        if(alimentos != null) {
		        for(Alimento alimento : alimentos) {
		        	
		        	// Crear el tag del alimento
		        	Element nombreAlimento = documento.createElement(alimento.getNombre());
		        	
		        	// Añadir la descripcion al alimento.
		        	Attr atributoDescrip = documento.createAttribute("Descripcion");
		        	atributoDescrip.setNodeValue(alimento.getDescripcion());
		        	nombreAlimento.setAttributeNode(atributoDescrip);
		        	
		        	// Crear tag de codigo y añadirlo.
		        	Element codElemento = documento.createElement("Codigo");
		        	Element codigo = documento.createElement(alimento.getCodigo());
		        	codElemento.appendChild(codigo);
		        	nombreAlimento.appendChild(codElemento);
		        	
		        	// Crear tag de precio y añadirlo
		        	Element precElement = documento.createElement("Precio");
		        	Element precio = documento.createElement(Float.toString(alimento.getPrecio()));
		        	precElement.appendChild(precio);
		        	nombreAlimento.appendChild(precElement);
		        	
		        	// Agregar calorias al alimento.
		        	Element caloriasElemento = documento.createElement("Calorias");
		        	Element calorias = documento.createElement(Float.toString(alimento.getCalorias()));
		          	caloriasElemento.appendChild(calorias);
		          	nombreAlimento.appendChild(caloriasElemento);
		          	
		          	// Agregar racion al alimento.
		          	Element racionElement = documento.createElement("Racion");
		          	Element racion = documento.createElement(Integer.toString(alimento.getRacion()));
		          	if(alimento.getTipo() == TipoAlimento.PLATO_FUERTE) {
		          		elementoPlatosF.appendChild(nombreAlimento);
		          	}
		          	else if(alimento.getTipo() == TipoAlimento.ENTRADA) {
		          		elementoEntradas.appendChild(nombreAlimento);
		          	}
		          	else if(alimento.getTipo() == TipoAlimento.BEBIDA) {
		          		elementoBebidas.appendChild(nombreAlimento);
		          	}
		          	else {
		          		elementoPostres.appendChild(nombreAlimento);
		          	}
		          	
		        }
	        }
	        // Escribir el contenido en el XML
	        DOMSource source = new DOMSource(documento);
	        StreamResult result = new StreamResult(new File(CreadorXML.path));
	        
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        // Ordenar el xml
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
	        transformer.transform(source, result);
	    }
	    catch(Exception ex)
	    {
	        ex.printStackTrace();
	    }
	}
}
