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
		
		return usr;
	}
}
