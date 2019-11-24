package views;

import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;

/**
 * Vista de pedidos
 * @author Kenneth Sanchez
 *
 */
public class PedidosGraficoView {
	
	/**
	 * Metodo Constructor
	 * @param pTitulo
	 * @param pChart
	 */
	public PedidosGraficoView(String pTitulo, JFreeChart pChart) {
		
		ChartFrame frame = new ChartFrame(pTitulo, pChart);
		frame.setResizable(false);
		frame.setSize(400, 400);
		
		frame.setVisible(true);
		
	}	
}
