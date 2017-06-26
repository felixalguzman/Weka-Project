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
	
	public int CantidadInstancias(){
		return dataset.numInstances();
	}
	
	public int CantidadAtributos(){
		return dataset.numAttributes();
	}
	
	public String[] Atributos()
	{
		String[] nombres = new String[dataset.numAttributes()];
		
		for(int i=0; i < dataset.numAttributes();i++)
		{
			nombres[i] = dataset.attribute(i).toString();
		}
		return nombres;
	}
	
	/*public String[] Instancias()
	{
		String[] inst = new String[dataset.numInstances()];
		String[] aux = new String[dataset.numAttributes()*dataset.numInstances()];
		
		for(int i =0; i < dataset.numInstances();i++)
		{
			inst[i] = dataset.instance(i).toString();
			
			
		}
		
		for(int i=0; i < dataset.numInstances();i++)
		{
			aux = inst[i].split("\\,");
		}
		
		inst = aux; 
		return inst;
	}*/
	public void ObtenerInstancias(){
		String[][] instanciasAux = new String[dataset.numInstances()][dataset.numInstances()];
		for (int i = 0; i < dataset.numInstances(); i++) {
			for (int j = 0; j < dataset.numAttributes(); j++) {
				instanciasAux[i][j] = dataset.instance(i).stringValue(j);
				System.out.println("Instancia "+i+": Atributo"+j+" "+instanciasAux[i][j]);
			}
		}
	}
	
	

}