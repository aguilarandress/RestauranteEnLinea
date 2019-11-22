package catalogoXML;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import models.alimento.Alimento;
import models.alimento.TipoAlimento;
import models.cola.Cola;

public class CreadorXML {
	private static CreadorXML creadorXML;
	private static final String path = "Catalogo/Catalogo.xml";
	
	private CreadorXML() {
		
	}
	
	public static CreadorXML getInstance() {
		if(creadorXML == null) {
			creadorXML = new CreadorXML();
		}
		return creadorXML;
	}
	

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
   
	        // Recorrer cada alimento y añadirlo a su sección correspondiente.
	        if(alimentos != null) {
		        for(Alimento alimento : alimentos) {
		          	if(alimento.getTipo() == TipoAlimento.PLATO_FUERTE) {
		          		 Element elementoPlatosF = documento.createElement("Plato_Fuerte");
		          		 elementoCatalogo.appendChild(elementoPlatosF);
		          		 
		          		 // Atributo nombre
		          		 Attr nombreAttr = documento.createAttribute("Nombre");
		          		 nombreAttr.setNodeValue(alimento.getNombre());
		          		 elementoPlatosF.setAttributeNode(nombreAttr);
		          		 
		          		 // Elemento Descripcion
		          		 Element descripcionEle = documento.createElement("Descripcion");
		          		 descripcionEle.appendChild(documento.createTextNode(alimento.getDescripcion()));
		          		 elementoPlatosF.appendChild(descripcionEle);
		          		 
		          		 // Elemento Codigo
		          		 Element codigoEle = documento.createElement("Codigo");
		          		 codigoEle.appendChild(documento.createTextNode(alimento.getCodigo()));
		          		 elementoPlatosF.appendChild(codigoEle);
		          		 
		          		 // Elemento precio
		          		 Element precioEle = documento.createElement("Precio");
		          		 precioEle.appendChild(documento.createTextNode(Float.toString(alimento.getPrecio())));
		          		 elementoPlatosF.appendChild(precioEle);
		          		 
		          		 // Elemento racion
		          		 Element racionEle = documento.createElement("Racion");
		          		 racionEle.appendChild(documento.createTextNode(Integer.toString(alimento.getRacion())));
		          		 elementoPlatosF.appendChild(racionEle);
		          		 
		          		 // Elemento calorias
		          		 Element caloriasEle = documento.createElement("Calorias");
		          		 caloriasEle.appendChild(documento.createTextNode(Float.toString(alimento.getCalorias())));
		          		 elementoPlatosF.appendChild(caloriasEle);
		          		 
		          	}
		          	else if(alimento.getTipo() == TipoAlimento.ENTRADA) {
		          		Element elementoEntradas = documento.createElement("Entrada");
		          		elementoCatalogo.appendChild(elementoEntradas);
		          		
		          		 // Atributo nombre
		          		 Attr nombreAttr = documento.createAttribute("Nombre");
		          		 nombreAttr.setNodeValue(alimento.getNombre());
		          		elementoEntradas.setAttributeNode(nombreAttr);
		          		 
		          		 // Elemento Descripcion
		          		 Element descripcionEle = documento.createElement("Descripcion");
		          		 descripcionEle.appendChild(documento.createTextNode(alimento.getDescripcion()));
		          		elementoEntradas.appendChild(descripcionEle);
		          		 
		          		 // Elemento Codigo
		          		 Element codigoEle = documento.createElement("Codigo");
		          		 codigoEle.appendChild(documento.createTextNode(alimento.getCodigo()));
		          		elementoEntradas.appendChild(codigoEle);
		          		 
		          		 // Elemento precio
		          		 Element precioEle = documento.createElement("Precio");
		          		 precioEle.appendChild(documento.createTextNode(Float.toString(alimento.getPrecio())));
		          		elementoEntradas.appendChild(precioEle);
		          		 
		          		 // Elemento racion
		          		 Element racionEle = documento.createElement("Racion");
		          		 racionEle.appendChild(documento.createTextNode(Integer.toString(alimento.getRacion())));
		          		elementoEntradas.appendChild(racionEle);
		          		 
		          		 // Elemento calorias
		          		 Element caloriasEle = documento.createElement("Calorias");
		          		 caloriasEle.appendChild(documento.createTextNode(Float.toString(alimento.getCalorias())));
		          		elementoEntradas.appendChild(caloriasEle);

		          	}
		          	else if(alimento.getTipo() == TipoAlimento.BEBIDA) {
		          		Element elementoBebidas = documento.createElement("Bebida");
		          		elementoCatalogo.appendChild(elementoBebidas);
		          		
		          		 // Atributo nombre
		          		 Attr nombreAttr = documento.createAttribute("Nombre");
		          		 nombreAttr.setNodeValue(alimento.getNombre());
		          		elementoBebidas.setAttributeNode(nombreAttr);
		          		 
		          		 // Elemento Descripcion
		          		 Element descripcionEle = documento.createElement("Descripcion");
		          		 descripcionEle.appendChild(documento.createTextNode(alimento.getDescripcion()));
		          		elementoBebidas.appendChild(descripcionEle);
		          		 
		          		 // Elemento Codigo
		          		 Element codigoEle = documento.createElement("Codigo");
		          		 codigoEle.appendChild(documento.createTextNode(alimento.getCodigo()));
		          		elementoBebidas.appendChild(codigoEle);
		          		 
		          		 // Elemento precio
		          		 Element precioEle = documento.createElement("Precio");
		          		 precioEle.appendChild(documento.createTextNode(Float.toString(alimento.getPrecio())));
		          		elementoBebidas.appendChild(precioEle);
		          		 
		          		 // Elemento racion
		          		 Element racionEle = documento.createElement("Racion");
		          		 racionEle.appendChild(documento.createTextNode(Integer.toString(alimento.getRacion())));
		          		elementoBebidas.appendChild(racionEle);
		          		 
		          		 // Elemento calorias
		          		 Element caloriasEle = documento.createElement("Calorias");
		          		 caloriasEle.appendChild(documento.createTextNode(Float.toString(alimento.getCalorias())));
		          		elementoBebidas.appendChild(caloriasEle);
		          	}
		          	else {
		          		Element elementoPostres = documento.createElement("Postre");
		          		elementoCatalogo.appendChild(elementoPostres);
		          		
		          		 // Atributo nombre
		          		Attr nombreAttr = documento.createAttribute("Nombre");
		          		nombreAttr.setNodeValue(alimento.getNombre());
		          		elementoPostres.setAttributeNode(nombreAttr);
		          		 
		          		 // Elemento Descripcion
		          		Element descripcionEle = documento.createElement("Descripcion");
		          		descripcionEle.appendChild(documento.createTextNode(alimento.getDescripcion()));
		          		elementoPostres.appendChild(descripcionEle);
		          		 
		          		 // Elemento Codigo
		          		Element codigoEle = documento.createElement("Codigo");
		          		codigoEle.appendChild(documento.createTextNode(alimento.getCodigo()));
		          		elementoPostres.appendChild(codigoEle);
		          		 
		          		 // Elemento precio
		          		Element precioEle = documento.createElement("Precio");
		          		precioEle.appendChild(documento.createTextNode(Float.toString(alimento.getPrecio())));
		          		elementoPostres.appendChild(precioEle);
		          		 
		          		 // Elemento racion
		          		Element racionEle = documento.createElement("Racion");
		          		racionEle.appendChild(documento.createTextNode(Integer.toString(alimento.getRacion())));
		          		elementoPostres.appendChild(racionEle);
		          		 
		          		 // Elemento calorias
		          		Element caloriasEle = documento.createElement("Calorias");
		          		caloriasEle.appendChild(documento.createTextNode(Float.toString(alimento.getCalorias())));
		          		elementoPostres.appendChild(caloriasEle);
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
	
	/**
	 * Lee el catalogo de alimentos y los añade a un ArrayList de Alimento
	 * @return Un ArrayList de alimento
	 */
	public Cola<Alimento> ObtenerCatalogo(){
		Cola<Alimento> alimentos = new Cola<Alimento>();
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document documento = null;
			
			documento = docBuilder.parse(CreadorXML.path);
			documento.getDocumentElement().normalize();
			
			// Obtiene las bebidas
			NodeList lista = documento.getElementsByTagName("Bebida");
			
			for(int i =0; i<lista.getLength(); i++) {
				Element nodo = (Element) lista.item(i);
				// Obtener el nombre
				String nombre = nodo.getAttribute("Nombre").toString();
				//Obtener el codigo
				String codigo = nodo.getElementsByTagName("Codigo").item(0).getTextContent();
				//Obtener descripcion
				String descripcion = nodo.getElementsByTagName("Descripcion").item(0).getTextContent();
				//Obtener precio
				Float precio = Float.parseFloat(nodo.getElementsByTagName("Precio").item(0).getTextContent());
				//Obtener calorias
				Float calorias = Float.parseFloat(nodo.getElementsByTagName("Calorias").item(0).getTextContent());
				//Obtener racion
				int racion = Integer.parseInt(nodo.getElementsByTagName("Racion").item(0).getTextContent());
				
				// Setear cada atributo
				Alimento alimento = new Alimento(0);
				alimento.setCalorias(calorias);
				alimento.setCodigo(codigo);
				alimento.setHabilitado(true);
				alimento.setNombre(nombre);
				alimento.setPrecio(precio);
				alimento.setDescripcion(descripcion);
				alimento.setRacion(racion);
				alimentos.enqueue(alimento);				
			}
			
			lista = documento.getElementsByTagName("Entrada");
			for(int i =0; i<lista.getLength(); i++) {
				Element nodo = (Element) lista.item(i);
				// Obtener el nombre
				String nombre = nodo.getAttribute("Nombre").toString();
				//Obtener el codigo
				String codigo = nodo.getElementsByTagName("Codigo").item(0).getTextContent();
				//Obtener descripcion
				String descripcion = nodo.getElementsByTagName("Descripcion").item(0).getTextContent();
				//Obtener precio
				Float precio = Float.parseFloat(nodo.getElementsByTagName("Precio").item(0).getTextContent());
				//Obtener calorias
				Float calorias = Float.parseFloat(nodo.getElementsByTagName("Calorias").item(0).getTextContent());
				//Obtener racion
				int racion = Integer.parseInt(nodo.getElementsByTagName("Racion").item(0).getTextContent());
				
				// Setear cada atributo
				Alimento alimento = new Alimento(1);
				alimento.setCalorias(calorias);
				alimento.setCodigo(codigo);
				alimento.setHabilitado(true);
				alimento.setNombre(nombre);
				alimento.setPrecio(precio);
				alimento.setDescripcion(descripcion);
				alimento.setRacion(racion);
				alimentos.enqueue(alimento);				
			}
			
			lista = documento.getElementsByTagName("Plato_Fuerte");
			for(int i =0; i<lista.getLength(); i++) {
				Element nodo = (Element) lista.item(i);
				// Obtener el nombre
				String nombre = nodo.getAttribute("Nombre").toString();
				//Obtener el codigo
				String codigo = nodo.getElementsByTagName("Codigo").item(0).getTextContent();
				//Obtener descripcion
				String descripcion = nodo.getElementsByTagName("Descripcion").item(0).getTextContent();
				//Obtener precio
				Float precio = Float.parseFloat(nodo.getElementsByTagName("Precio").item(0).getTextContent());
				//Obtener calorias
				Float calorias = Float.parseFloat(nodo.getElementsByTagName("Calorias").item(0).getTextContent());
				//Obtener racion
				int racion = Integer.parseInt(nodo.getElementsByTagName("Racion").item(0).getTextContent());
				
				// Setear cada atributo
				Alimento alimento = new Alimento(2);
				alimento.setCalorias(calorias);
				alimento.setCodigo(codigo);
				alimento.setHabilitado(true);
				alimento.setNombre(nombre);
				alimento.setPrecio(precio);
				alimento.setDescripcion(descripcion);
				alimento.setRacion(racion);
				alimentos.enqueue(alimento);				
			}
			
			lista = documento.getElementsByTagName("Postre");
			for(int i =0; i<lista.getLength(); i++) {
				Element nodo = (Element) lista.item(i);
				// Obtener el nombre
				String nombre = nodo.getAttribute("Nombre").toString();
				//Obtener el codigo
				String codigo = nodo.getElementsByTagName("Codigo").item(0).getTextContent();
				//Obtener descripcion
				String descripcion = nodo.getElementsByTagName("Descripcion").item(0).getTextContent();
				//Obtener precio
				Float precio = Float.parseFloat(nodo.getElementsByTagName("Precio").item(0).getTextContent());
				//Obtener calorias
				Float calorias = Float.parseFloat(nodo.getElementsByTagName("Calorias").item(0).getTextContent());
				//Obtener racion
				int racion = Integer.parseInt(nodo.getElementsByTagName("Racion").item(0).getTextContent());
				
				// Setear cada atributo
				Alimento alimento = new Alimento(3);
				alimento.setCalorias(calorias);
				alimento.setCodigo(codigo);
				alimento.setHabilitado(true);
				alimento.setNombre(nombre);
				alimento.setPrecio(precio);
				alimento.setDescripcion(descripcion);
				alimento.setRacion(racion);
				alimentos.enqueue(alimento);				
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return alimentos;
		
	}
	
	/**
	 * Agrega un elemento nuevo al catalogo
	 * @param alimento Alimento que se quiere agregar
	 */
	public void AgregarElementoNuevo(Alimento alimento) {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document documento = null;
			
			documento = docBuilder.parse(CreadorXML.path);
			
			Node elementoCatalogo = documento.getFirstChild();
		
			if(alimento.getTipo() == TipoAlimento.PLATO_FUERTE) {
         		 Element elementoPlatosF = documento.createElement("Plato_Fuerte");
         		 elementoCatalogo.appendChild(elementoPlatosF);
         		 
         		 // Atributo nombre
         		 Attr nombreAttr = documento.createAttribute("Nombre");
         		 nombreAttr.setNodeValue(alimento.getNombre());
         		 elementoPlatosF.setAttributeNode(nombreAttr);
         		 
         		 // Elemento Descripcion
         		 Element descripcionEle = documento.createElement("Descripcion");
         		 descripcionEle.appendChild(documento.createTextNode(alimento.getDescripcion()));
         		 elementoPlatosF.appendChild(descripcionEle);
         		 
         		 // Elemento Codigo
         		 Element codigoEle = documento.createElement("Codigo");
         		 codigoEle.appendChild(documento.createTextNode(alimento.getCodigo()));
         		 elementoPlatosF.appendChild(codigoEle);
         		 
         		 // Elemento precio
         		 Element precioEle = documento.createElement("Precio");
         		 precioEle.appendChild(documento.createTextNode(Float.toString(alimento.getPrecio())));
         		 elementoPlatosF.appendChild(precioEle);
         		 
         		 // Elemento racion
         		 Element racionEle = documento.createElement("Racion");
         		 racionEle.appendChild(documento.createTextNode(Integer.toString(alimento.getRacion())));
         		 elementoPlatosF.appendChild(racionEle);
         		 
         		 // Elemento calorias
         		 Element caloriasEle = documento.createElement("Calorias");
         		 caloriasEle.appendChild(documento.createTextNode(Float.toString(alimento.getCalorias())));
         		 elementoPlatosF.appendChild(caloriasEle);
         		 
         	}
         	else if(alimento.getTipo() == TipoAlimento.ENTRADA) {
         		Element elementoEntradas = documento.createElement("Entrada");
         		elementoCatalogo.appendChild(elementoEntradas);
         		
         		 // Atributo nombre
         		 Attr nombreAttr = documento.createAttribute("Nombre");
         		 nombreAttr.setNodeValue(alimento.getNombre());
         		elementoEntradas.setAttributeNode(nombreAttr);
         		 
         		 // Elemento Descripcion
         		 Element descripcionEle = documento.createElement("Descripcion");
         		 descripcionEle.appendChild(documento.createTextNode(alimento.getDescripcion()));
         		elementoEntradas.appendChild(descripcionEle);
         		 
         		 // Elemento Codigo
         		 Element codigoEle = documento.createElement("Codigo");
         		 codigoEle.appendChild(documento.createTextNode(alimento.getCodigo()));
         		elementoEntradas.appendChild(codigoEle);
         		 
         		 // Elemento precio
         		 Element precioEle = documento.createElement("Precio");
         		 precioEle.appendChild(documento.createTextNode(Float.toString(alimento.getPrecio())));
         		elementoEntradas.appendChild(precioEle);
         		 
         		 // Elemento racion
         		 Element racionEle = documento.createElement("Racion");
         		 racionEle.appendChild(documento.createTextNode(Integer.toString(alimento.getRacion())));
         		elementoEntradas.appendChild(racionEle);
         		 
         		 // Elemento calorias
         		 Element caloriasEle = documento.createElement("Calorias");
         		 caloriasEle.appendChild(documento.createTextNode(Float.toString(alimento.getCalorias())));
         		elementoEntradas.appendChild(caloriasEle);

         	}
         	else if(alimento.getTipo() == TipoAlimento.BEBIDA) {
         		Element elementoBebidas = documento.createElement("Bebida");
         		elementoCatalogo.appendChild(elementoBebidas);
         		
         		 // Atributo nombre
         		 Attr nombreAttr = documento.createAttribute("Nombre");
         		 nombreAttr.setNodeValue(alimento.getNombre());
         		elementoBebidas.setAttributeNode(nombreAttr);
         		 
         		 // Elemento Descripcion
         		 Element descripcionEle = documento.createElement("Descripcion");
         		 descripcionEle.appendChild(documento.createTextNode(alimento.getDescripcion()));
         		elementoBebidas.appendChild(descripcionEle);
         		 
         		 // Elemento Codigo
         		 Element codigoEle = documento.createElement("Codigo");
         		 codigoEle.appendChild(documento.createTextNode(alimento.getCodigo()));
         		elementoBebidas.appendChild(codigoEle);
         		 
         		 // Elemento precio
         		 Element precioEle = documento.createElement("Precio");
         		 precioEle.appendChild(documento.createTextNode(Float.toString(alimento.getPrecio())));
         		elementoBebidas.appendChild(precioEle);
         		 
         		 // Elemento racion
         		 Element racionEle = documento.createElement("Racion");
         		 racionEle.appendChild(documento.createTextNode(Integer.toString(alimento.getRacion())));
         		elementoBebidas.appendChild(racionEle);
         		 
         		 // Elemento calorias
         		 Element caloriasEle = documento.createElement("Calorias");
         		 caloriasEle.appendChild(documento.createTextNode(Float.toString(alimento.getCalorias())));
         		elementoBebidas.appendChild(caloriasEle);
         	}
         	else {
         		Element elementoPostres = documento.createElement("Postre");
         		elementoCatalogo.appendChild(elementoPostres);
         		
         		 // Atributo nombre
         		Attr nombreAttr = documento.createAttribute("Nombre");
         		nombreAttr.setNodeValue(alimento.getNombre());
         		elementoPostres.setAttributeNode(nombreAttr);
         		 
         		 // Elemento Descripcion
         		Element descripcionEle = documento.createElement("Descripcion");
         		descripcionEle.appendChild(documento.createTextNode(alimento.getDescripcion()));
         		elementoPostres.appendChild(descripcionEle);
         		 
         		 // Elemento Codigo
         		Element codigoEle = documento.createElement("Codigo");
         		codigoEle.appendChild(documento.createTextNode(alimento.getCodigo()));
         		elementoPostres.appendChild(codigoEle);
         		 
         		 // Elemento precio
         		Element precioEle = documento.createElement("Precio");
         		precioEle.appendChild(documento.createTextNode(Float.toString(alimento.getPrecio())));
         		elementoPostres.appendChild(precioEle);
         		 
         		 // Elemento racion
         		Element racionEle = documento.createElement("Racion");
         		racionEle.appendChild(documento.createTextNode(Integer.toString(alimento.getRacion())));
         		elementoPostres.appendChild(racionEle);
         		 
         		 // Elemento calorias
         		Element caloriasEle = documento.createElement("Calorias");
         		caloriasEle.appendChild(documento.createTextNode(Float.toString(alimento.getCalorias())));
         		elementoPostres.appendChild(caloriasEle);
          	}
			
	        DOMSource source = new DOMSource(documento);
	        StreamResult result = new StreamResult(new File(CreadorXML.path));
	        
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        // Ordenar el xml
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
	        transformer.transform(source, result);
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}

	}
}
