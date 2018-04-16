import ar.edu.ub.programacion3.modelo.Teletipo;

public class Aplicacion
{
	public static void main(String[] args)
	{
		Teletipo teletipo = new Teletipo();
		
		System.out.println( teletipo.interpretar("a*bc/d//e*"));
		System.out.println( teletipo.interpretar("a&bc/d//e/7*"));
		System.out.println( teletipo.interpretar("a&bc/d//e/8&*"));
	}
}
