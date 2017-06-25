package logical;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import weka.core.Instances;

public class Prueba {

	public Prueba(String absolutePath) throws IOException {
		Leer(absolutePath);
	}

	public void Leer(String lugar) throws IOException
	{
		BufferedReader reader = new BufferedReader( new FileReader(lugar));
		 Instances data = new Instances(reader);
		 reader.close();
		 data.setClassIndex(data.numAttributes() - 1);
		 System.out.println(data.toSummaryString());
	}

}