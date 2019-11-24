package controllers;
import views.AbrirPlatilloView;
import models.alimento.Alimento;

public class AbrirPlatilloController {
	AbrirPlatilloView view;
	MainController controller;
	Alimento alimentoSelected;
	
	public AbrirPlatilloController(MainController controller) {
		view = new AbrirPlatilloView();
		this.controller = controller;
		alimentoSelected = controller.getAlimentoSelected();
		System.out.println(alimentoSelected.getNombre());
		
		view.getNombrePlatillo().setText(alimentoSelected.getNombre());
		view.getPrecioPlatillo().setText(Float.toString(alimentoSelected.getPrecio()));
		view.getCaloriasPlatillo().setText(Float.toString(alimentoSelected.getCalorias()));
		view.getRacionPlatillo().setText(Integer.toString(alimentoSelected.getRacion()));
	}
	
}