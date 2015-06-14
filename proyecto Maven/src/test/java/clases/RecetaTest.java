package clases;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class RecetaTest {

	private Usuario unobjeto; 
	private Receta unaReceta;
	private Receta unaReceta2;
	
	private Grupo grupo1;

	private final String emailtest        = "utnfrba@gmail.com";	
	private final String nombretest       = "pepe";
	private final int edadtest            = 30;
	private final String dietatest        = "vegetariano";
	private final double alturatest       = 1.80;
	private final String complexiontest   = "media";
	private final char sexotest           = 'M';
	private final String precondiciontest = "SANO";
	private final String rutinatest       = "mediano";
	
	private final String ingredienteA     = "HUEVO";
	private final String ingredienteB     = "HARINA";
	private final String ingredienteC     = "CARNE";
	private final String ingredienteD     = "AZUCAR";
	private final String ingredienteE     = "CAFE";
	private final String ingredienteF     = "TOSTADAS";
	private final String horarioReceta    = "DESAYUNO";
	private final String horarioPlan      = "DESAYUNO";
	private final String horarioPlanError = "MERIENDA";
	
	private final String nombreReceta1	  = "Desayuno copado";
	private final String nombreReceta2    = "Merienda tuneada";
	
	@Before
	public void setUp() {
		final List<String> listadoIngredientes  = Arrays.asList(ingredienteA, ingredienteB, ingredienteC, ingredienteD);
		final List<String> listadoIngredientes2 = Arrays.asList(ingredienteE, ingredienteF);
		final LocalDate today = LocalDate.now();
		
		this.unobjeto   = new Usuario().crearUsuario(emailtest,nombretest,edadtest,dietatest,alturatest,complexiontest,sexotest,precondiciontest,rutinatest);
		this.unaReceta  = new Receta().crearReceta(this.unobjeto, horarioReceta, today, listadoIngredientes, nombreReceta1);
		this.unaReceta2 = new Receta().crearReceta(this.unobjeto, horarioReceta, today, listadoIngredientes2, nombreReceta2);
		
		this.grupo1 = new Grupo();
		this.grupo1.agregarUsuario(unobjeto);
	}
	
	@Test
	public void testPlanificarNuevaReceta()
	{
		final LinkedList <Receta> nuevaListaRecetas;
		final List<String> listadoIngredientes  = Arrays.asList(ingredienteA, ingredienteB, ingredienteC, ingredienteD);
		final LocalDate today = LocalDate.now();
		
		this.unobjeto   = new Usuario().crearUsuario(emailtest,nombretest,edadtest,dietatest,alturatest,complexiontest,sexotest,precondiciontest,rutinatest);
		this.unaReceta  = new Receta().crearReceta(this.unobjeto, horarioReceta, today, listadoIngredientes, nombreReceta1);
		
		nuevaListaRecetas = this.unaReceta.planificarReceta(this.unobjeto.getRecetas(), this.unobjeto.getPrecondicion(), today, horarioPlan);

		this.unobjeto.setRecetas(nuevaListaRecetas);
		assertFalse("No se pudo crear la receta",this.unobjeto.getRecetas()==null);
	}
	
	@Test
	public void testRecetaHorarioInc()
	{
		final LinkedList <Receta> nuevaListaRecetas;
		final LocalDate today = LocalDate.now();
		
		nuevaListaRecetas = this.unaReceta.planificarReceta(this.unobjeto.getRecetas(), this.unobjeto.getPrecondicion(), today, horarioPlanError);
		
		this.unobjeto.setRecetas(nuevaListaRecetas);
		assertTrue("No se pudo crear la receta porque el horario elegido no es valido",this.unobjeto.getRecetas()==null);
	}
	
	@Test
	public void testReemplazarReceta()
	{
		final LinkedList <Receta> nuevaListaRecetas;
		final LinkedList <Receta> nuevaListaRecetas2;
		final LocalDate today = LocalDate.now();
		
		nuevaListaRecetas = this.unaReceta.planificarReceta(this.unobjeto.getRecetas(), this.unobjeto.getPrecondicion(), today, horarioPlan);
		
		int cantidadInicial = nuevaListaRecetas.size(); //cant desp de planificar receta1
	
		nuevaListaRecetas2 = this.unaReceta2.planificarReceta(this.unobjeto.getRecetas(), this.unobjeto.getPrecondicion(), today, horarioPlan);

		int cantidadFinal = nuevaListaRecetas2.size(); //cant desp de planificar receta2 estando planificada receta1
		
		assertEquals(cantidadInicial, cantidadFinal);
	}
	
	@Test(expected= UnaExceptionMuyClaraYPuntual.class)
	public void testDosRecetasIguales() throws UnaExceptionMuyClaraYPuntual {
		RepositorioRecetas repo = new RepositorioRecetas();
		repo.nuevaReceta(unaReceta);
		repo.nuevaReceta(unaReceta);
	}
	
	@Test
	public void testCalificarReceta2Veces() {
		Calificacion c = new Calificacion(this.unobjeto, this.grupo1, 5);
		
		unaReceta.nuevaCalificacion(c);
		
		assertFalse("Si intenta calificar de nuevo, falla la validacion", unaReceta.validarCalificacion(c));
	}
	
	@Test
	public void testRecetaEnHistorialNoCambiaConModificacionAOriginal() throws UnaExceptionMuyClaraYPuntual {
		final LinkedList <Receta> nuevaListaRecetas; //recetas planificadas
		final LocalDate today = LocalDate.now();
		final List<String> listadoIngredientes  = Arrays.asList(ingredienteA, ingredienteB, ingredienteC, ingredienteD);
		final List<String> listadoIngredientes2 = Arrays.asList(ingredienteE, ingredienteF);
		
		RepositorioRecetas repo = new RepositorioRecetas();
		repo.nuevaReceta(unaReceta);
		
		nuevaListaRecetas = this.unaReceta.planificarReceta(this.unobjeto.getRecetas(), this.unobjeto.getPrecondicion(), today, horarioPlan);
		
		//copia de la receta original para comparar
		Receta tempReceta = new Receta().crearReceta(this.unobjeto, horarioReceta, today, listadoIngredientes, nombreReceta1);
		
		unaReceta.setIngredientes(listadoIngredientes2);
		repo.modificarReceta(unaReceta);
		
		//la receta en el repo y unaReceta deberian ser distintas, pero la planificada deberia ser igual
		assertFalse("Deberian ser distintas porque cambio la original", tempReceta.getIngrediente() == repo.buscarReceta(this.unobjeto, nombreReceta1));
		assertEquals(tempReceta.getIngrediente(), nuevaListaRecetas.get(0).getIngrediente());
	}
}