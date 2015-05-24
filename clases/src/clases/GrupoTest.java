package clases;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class Grupo Test {
	
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
	
	@Test
	public void testAgregarUsuarioNoRepetido() {
		grupoTest = new Grupo();
		this.setUsuarioTest();
		grupoTest.agregarUsuario(this.usuarioTest);
		assertEquals(grupoTest.get(0), this.usuarioTest);
	}
	
	@Test(expected = UnaExceptionMuyClaraYPuntual)
	public void testAgregarUsuarioRepetido() {
		grupoTest = new Grupo();
		this.setUsuarioTest();
		grupoTest.agregarUsuario(this.usuarioTest);
		grupoTest.agregarUsuario(this.usuarioTest);
	}
	
	@Test
	public void testRemoverUsuarioExistente() {
		grupoTest = new Grupo();
		this.setUsuarioTest();
		grupoTest.agregarUsuario(this.usuarioTest);
		
		grupoTest.removerUsuario(this.usuarioTest);
		assertEquals(0, grupoTest.cantidadUsuarios());
	}
	
	@Test(expected = UnaExceptionMuyClaraYPuntual)
	public void testRemoverUsuarioInExistente() {
		grupoTest = new Grupo();
		
		grupoTest.removerUsuario(this.usuarioTest);
	}
	
	@Test
	public void testRecetasDelGrupo() {
		grupoTest = new Grupo();
		this.setUsuarioTest();
		grupoTest.agregarUsuario(this.usuarioTest);
		
		//las recetas del grupo deberian ser las compartidas por el unico usuario en el
		assertEquals(grupoTest.listadoRecetas(), this.usuarioTest.getRecetasCompartidas());
	}
	
	public void setUsuarioTest() {
		final List<String> listadoIngredientes  = Arrays.asList("CAFE", "TOSTADAS");
		final Date today = new Date();
		Receta unaReceta;
		
		this.usuarioTest   = new Usuario().crearUsuario(emailtest,nombretest,edadtest,dietatest,alturatest,complexiontest,sexotest,precondiciontest,rutinatest);
		unaReceta  = new Receta().crearReceta(this.usuarioTest, "DESAYUNO", today, listadoIngredientes);
		this.usuarioTest.setRecetas(nuevaListaRecetas);
	}
}