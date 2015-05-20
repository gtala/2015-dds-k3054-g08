package clases;

public class Usuario {
	String email="";
	String nombre="";
	int edad=0;
	String dieta="";
	int altura=0;
	String complexion="";
	char sexo = 'M';
	String precondicion="";
	String rutina="";
	
	public Usuario()
	{
		
	}
	
	public Usuario crearUsuario(String email,String nombre,int edad,String dieta,int altura,String complexion, char sexo,String precond,String rutina)
	{
		Usuario usr = new Usuario();
		usr.email=email;
		usr.nombre=nombre;
		usr.edad=edad;
		usr.dieta=dieta;
		usr.altura=altura;
		usr.complexion=complexion;
		usr.sexo=sexo;
		usr.precondicion=precond;
		usr.rutina=rutina;
		return usr;
	}

}
