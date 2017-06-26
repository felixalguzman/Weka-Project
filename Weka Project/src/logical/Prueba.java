package logical;



import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class Prueba {
	
	private Instances dataset ;
	private DataSource source;

	public Prueba(String absolutePath) throws Exception {
		
		source = new DataSource(absolutePath);
		dataset = source.getDataSet();
	}

	public Instances getDataset() {
		return dataset;
	}

	public void setDataset(Instances dataset) {
		this.dataset = dataset;
	}

	public DataSource getSource() {
		return source;
	}

	public void setSource(DataSource source) {
		this.source = source;
	}

	public String Resumen(){
		return dataset.toSummaryString();
	}
	
	
}