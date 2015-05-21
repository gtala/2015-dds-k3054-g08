package clases;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UsuarioTest {
	
	private Usuario unobjeto; 
	private Usuario unobjeto2;
	
	private final String emailtest = "utnfrba@gmail.com";	
	private final String nombretest = "pepe";
	private final int edadtest = 30;
	private final String dietatest = "vegetariano";
	private final double alturatest = 1.80;
	private final String complexiontest = "media";
	private final char sexotest = 'M';
	private final String precondiciontest = "sano";
	private final String rutinatest = "mediano";

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
	
}
