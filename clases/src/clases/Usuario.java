package clases;

public class Usuario {
	String email="";
	String nombre="";
	int edad=0;
	String Perfil="";
	int altura=0;
	String complexion="";
	char sexo = 'M';
	String precondicion="";
	String rutina="";
	
	public Usuario()
	{
		
	}
	
	public Usuario crearUsuario(String email,String nombre,int edad,String perfil,int altura,String complexion, char sexo,String precond,String rutina)
	{
		Usuario usr = new Usuario();
		usr.email=email;
		usr.nombre=nombre;
		usr.edad=edad;
		usr.Perfil=perfil;
		usr.altura=altura;
		usr.complexion=complexion;
		usr.sexo=sexo;
		usr.precondicion=precond;
		usr.rutina=rutina;
		return usr;
	}

}
