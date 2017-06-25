package logical;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		Prueba p = new Prueba();
		try {
			p.Leer();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
