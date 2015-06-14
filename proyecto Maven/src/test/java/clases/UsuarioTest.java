package clases;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UsuarioTest {
	
	private Usuario unobjeto; 
	private Usuario unobjeto2;
	private Receta  unaReceta;
	
	private final String emailtest = "utnfrba@gmail.com";	
	private final String nombretest = "pepe";
	private final int edadtest = 30;
	private final String dietatest = "vegetariano";
	private final double alturatest = 1.80;
	private final String complexiontest = "media";
	private final char sexotest = 'M';
	private final String precondiciontest = "SANO";
	private final String rutinatest = "mediano";
	
	private final String ingredienteA  = "HUEVO";
	private final String ingredienteB  = "HARINA";
	private final String ingredienteC  = "CARNE";
	private final String ingredienteD  = "AZUCAR";
	private final String horarioReceta = "DESAYUNO";
	private final String horarioPlan   = "DESAYUNO";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
		
		this.unobjeto = new Usuario();
		
		
	}

	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void testCrearUsuario()
	{
		this.unobjeto = new Usuario().crearUsuario(emailtest,nombretest,edadtest,dietatest,alturatest,complexiontest,sexotest,precondiciontest,rutinatest);
		assertFalse("si el usuario tiene mas de 18 y un mail correcto unobjeto es distinto de null",this.unobjeto==null);
		
	}
	@Test
	public void testDosUsuariosIguales()
	{
		this.unobjeto = new Usuario().crearUsuario(emailtest,nombretest,edadtest,dietatest,alturatest,complexiontest,sexotest,precondiciontest,rutinatest);
		this.unobjeto2 = new Usuario().crearUsuario(emailtest,nombretest,edadtest,dietatest,alturatest,complexiontest,sexotest,precondiciontest,rutinatest);
		
		
		boolean verdad = this.unobjeto.equals(this.unobjeto2);
		assertEquals(verdad, true);
	}
	
	@Test
	public void testListarRecetas () {
		
		final LinkedList <Receta> nuevaListaRecetas;
		final List<String> listadoIngredientes  = Arrays.asList(ingredienteA, ingredienteB, ingredienteC, ingredienteD);
		final LocalDate today = LocalDate.now();

		this.unobjeto   = new Usuario().crearUsuario(emailtest,nombretest,edadtest,dietatest,alturatest,complexiontest,sexotest,precondiciontest,rutinatest);
		this.unaReceta  = new Receta().crearReceta(this.unobjeto, horarioReceta, today, listadoIngredientes, "Desayuno con Mirta");
		
		nuevaListaRecetas = this.unaReceta.planificarReceta(this.unobjeto.getRecetas(), this.unobjeto.getPrecondicion(), today, horarioPlan);
		
		this.unobjeto.setRecetas(nuevaListaRecetas);
			
		assertFalse("No se pudo listar sus recetas",this.unobjeto.listarRecetas() == null);
	}
}