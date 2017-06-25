package logical;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class Prueba {

	public Prueba(String absolutePath) throws Exception {
		Leer(absolutePath);
	}

	public void Leer(String lugar) throws Exception
	{
		
		DataSource source = new DataSource(lugar);
		Instances dataset = source.getDataSet();
		
		dataset.setClassIndex(dataset.numAttributes() - 1);
		System.out.println(dataset.toSummaryString());
	}

}