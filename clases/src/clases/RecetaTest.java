package clases;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class RecetaTest {

	private Usuario unobjeto; 
	private Receta unaReceta;
	private Receta unaReceta2;

	private final String emailtest = "utnfrba@gmail.com";	
	private final String nombretest = "pepe";
	private final int edadtest = 30;
	private final String dietatest = "vegetariano";
	private final double alturatest = 1.80;
	private final String complexiontest = "media";
	private final char sexotest = 'M';
	private final String precondiciontest = "SANO";
	private final String rutinatest = "mediano";
	
	@Test
	public void testPlanificarNuevaReceta()
	{
		final LinkedList <Receta> nuevaListaRecetas;
		final List<String> listadoIngredientes  = Arrays.asList("HUEVO", "HARINA", "CARNE", "AZUCAR");
		final Date today = new Date();
		
		this.unobjeto   = new Usuario().crearUsuario(emailtest,nombretest,edadtest,dietatest,alturatest,complexiontest,sexotest,precondiciontest,rutinatest);
		this.unaReceta  = new Receta().crearReceta(this.unobjeto, "DESAYUNO", today, listadoIngredientes);
		
		nuevaListaRecetas = this.unaReceta.planificarReceta(this.unobjeto.getRecetas(), this.unobjeto.getPrecondicion(), today, "DESAYUNO");

		this.unobjeto.setRecetas(nuevaListaRecetas);
		assertFalse("No se pudo crear la receta",this.unobjeto.getRecetas()==null);
	}
	
	@Test
	public void testRecetaHorarioInc()
	{
		final LinkedList <Receta> nuevaListaRecetas;
		final List<String> listadoIngredientes  = Arrays.asList("HUEVO", "HARINA", "CARNE", "AZUCAR");
		final Date today = new Date();

		this.unobjeto   = new Usuario().crearUsuario(emailtest,nombretest,edadtest,dietatest,alturatest,complexiontest,sexotest,precondiciontest,rutinatest);
		this.unaReceta  = new Receta().crearReceta(this.unobjeto, "DESAYUNO", today, listadoIngredientes);
		
		nuevaListaRecetas = this.unaReceta.planificarReceta(this.unobjeto.getRecetas(), this.unobjeto.getPrecondicion(), today, "DESAYUNO");
		
		this.unobjeto.setRecetas(nuevaListaRecetas);
		assertFalse("No se pudo crear la receta porque el horario elegido no es valido",this.unobjeto.getRecetas()==null);
	}
	
	@Test
	public void testReemplazarReceta()
	{
		final LinkedList <Receta> nuevaListaRecetas;
		final LinkedList <Receta> nuevaListaRecetas2;
		final List<String> listadoIngredientes  = Arrays.asList("HUEVO", "HARINA", "CARNE", "AZUCAR");
		final List<String> listadoIngredientes2 = Arrays.asList("CAFE", "TOSTADAS");
		final Date today = new Date();
			
		this.unobjeto   = new Usuario().crearUsuario(emailtest,nombretest,edadtest,dietatest,alturatest,complexiontest,sexotest,precondiciontest,rutinatest);
		this.unaReceta  = new Receta().crearReceta(this.unobjeto, "DESAYUNO", today, listadoIngredientes);
		this.unaReceta2 = new Receta().crearReceta(this.unobjeto, "DESAYUNO", today, listadoIngredientes2);
		
		nuevaListaRecetas = this.unaReceta.planificarReceta(this.unobjeto.getRecetas(), this.unobjeto.getPrecondicion(), today, "DESAYUNO");
		
		int cantidadInicial = nuevaListaRecetas.size();
		
		this.unobjeto.setRecetas(nuevaListaRecetas);
	
		nuevaListaRecetas2 = this.unaReceta2.planificarReceta(this.unobjeto.getRecetas(), this.unobjeto.getPrecondicion(), today, "DESAYUNO");

		int cantidadFinal = nuevaListaRecetas2.size();
		
		this.unobjeto.setRecetas(nuevaListaRecetas2);
		
		assertEquals(cantidadInicial, cantidadFinal);
	}
}
