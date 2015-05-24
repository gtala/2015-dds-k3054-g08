package clases;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class GrupoTest {
	
	private Grupo grupoTest;
	private Usuario usuarioTest;
	
	private final String emailtest = "utnfrba@gmail.com";	
	private final String nombretest = "pepe";
	private final int edadtest = 30;
	private final String dietatest = "vegetariano";
	private final double alturatest = 1.80;
	private final String complexiontest = "media";
	private final char sexotest = 'M';
	private final String precondiciontest = "SANO";
	private final String rutinatest = "mediano";

	private final String ingredienteA  = "CAFE";
	private final String ingredienteB  = "TOSTADAS";
	private final String horarioReceta = "DESAYUNO";
	

	@Before
	public void setUp() throws Exception {
		grupoTest = new Grupo();
		this.setUsuarioTest();
	}
	
	@Test
	public void testAgregarUsuarioNoRepetido() {
		List<Usuario> unListado; 
		unListado = grupoTest.agregarUsuario(this.usuarioTest);
		assertEquals(unListado.get(0), this.usuarioTest);
	}
	
	@Test
	public void testAgregarUsuarioRepetido() {
		grupoTest.agregarUsuario(this.usuarioTest);
		grupoTest.agregarUsuario(this.usuarioTest);
		assertFalse("Se intento agregar un usuario repetido",grupoTest.getUsuarios().size()!=2);
	}
	
	@Test
	public void testRemoverUsuarioExistente() {
		grupoTest.agregarUsuario(this.usuarioTest);
		grupoTest.quitarUsuario(this.usuarioTest);
		assertEquals(0, grupoTest.cantidadUsuarios());
	}
	
	@Test
	public void testRemoverUsuarioInExistente() {
		int sizeBef = grupoTest.cantidadUsuarios();
		grupoTest.quitarUsuario(this.usuarioTest);
		int sizeAft = grupoTest.cantidadUsuarios();
		assertFalse("Se intento eliminar un usuario inexistente", sizeBef==sizeAft);
	}
	
	@Test
	public void testRecetasDelGrupo() {
		grupoTest.agregarUsuario(this.usuarioTest);
		
		//las recetas del grupo deberian ser las compartidas por el unico usuario en el
		assertEquals(grupoTest.listadoRecetas(), this.usuarioTest.getRecetasCompartidas());
	}
	
	public void setUsuarioTest() {
		final List<String> listadoIngredientes  = Arrays.asList(ingredienteA, ingredienteB);
		final Date today = new Date();
		Receta unaReceta;
		final LinkedList<Receta> nuevaListaRecetas = new LinkedList<Receta> ();
		
		this.usuarioTest   = new Usuario().crearUsuario(emailtest,nombretest,edadtest,dietatest,alturatest,complexiontest,sexotest,precondiciontest,rutinatest);
		unaReceta  = new Receta().crearReceta(this.usuarioTest, horarioReceta, today, listadoIngredientes);
		nuevaListaRecetas.add(unaReceta);
		this.usuarioTest.setRecetas(nuevaListaRecetas);
	}
}