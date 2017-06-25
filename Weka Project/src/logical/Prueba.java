package logical;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import weka.core.Instances;

public class Prueba {

	public void Leer() throws IOException
	{
		BufferedReader reader = new BufferedReader( new FileReader("C:/Users/eric/Desktop/Universidad/3er Agno/3er Semestre/Mineria de Datos/Teoria/Datasets-20170515/Grupo 2 datasets/titanic.arff"));
		 Instances data = new Instances(reader);
		 reader.close();
		 data.setClassIndex(data.numAttributes() - 1);
	}

}