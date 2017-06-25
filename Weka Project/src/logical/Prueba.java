package logical;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import weka.core.Instances;

public class Prueba {





	public void Leer() throws IOException
	{
		BufferedReader reader = new BufferedReader( new FileReader("/some/where/data.arff"));
		 Instances data = new Instances(reader);
		 reader.close();
		 data.setClassIndex(data.numAttributes() - 1);
	}

}