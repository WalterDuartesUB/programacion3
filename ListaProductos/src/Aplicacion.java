import ar.edu.ub.p3.producto.modelo.ListaProductos;
import ar.edu.ub.p3.producto.modelo.Producto;

public class Aplicacion {

	public static void main(String[] args) {
		ListaProductos lista = new ListaProductos();
		
		lista.add( new Producto( "asd", "un Producto", 123.45, 30 ) );
		lista.add( new Producto( "ad", "otro Producto", 343.45, 40 ) );
		lista.add( new Producto( "d3", "un Producto 1", 13.45, 90 ) );
		lista.add( new Producto( "fyys", "un Producto 2", 16.45, 10 ) );
		lista.add( new Producto( "ff", "un Pr 2", 16.45, 50 ) );
		
		//Muestro la lista de productos
		System.out.println("Lista de productos");
		System.out.println( lista );
		
		//Muestro una posicion
		System.out.println("Muestro el producto en la 3era posicion");
		System.out.println( lista.at( 3 ) );
		
		//HAgo un aumento del 10%
		System.out.println("Incrementar 10%");
		System.out.println( lista.at( 3 ) );
		lista.at(3).incrementarValor( 10.0 );
		System.out.println( lista.at( 3 ) );
		
		//Busco un producto que exista
		System.out.println("Busco un producto que exista");
		System.out.println( lista.obtenerProducto("fyys") );
		
		System.out.println("Busco un producto que NO exista");
		//Busco un producto que no exista
		System.out.println( lista.obtenerProducto("avion") );
		
		
		//Pido una lista inferior o igual
		ListaProductos listaInferior = lista.obtenerProductosConStockInferiorOIgualA( 50 );
		
		//Pido una lista superior o igual
		ListaProductos listaSuperior = lista.obtenerProductosConStockSuperiorOIgualA( 50 );
		
		System.out.println("Muestro la lista sub 50");
		System.out.println( listaInferior );
		
		System.out.println("Muestro la lista sobre 50");
		System.out.println( listaSuperior );
	}

}
