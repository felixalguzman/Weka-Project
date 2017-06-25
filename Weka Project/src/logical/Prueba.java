package logical;



import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class Prueba {
	
	private Instances dataset ;
	private DataSource source;

	public Prueba(String absolutePath) throws Exception {
		//Leer();
		source = new DataSource(absolutePath);
		dataset = source.getDataSet();
	}

	
	
	public String Resumen()
	{
		return dataset.toSummaryString();
	}

}