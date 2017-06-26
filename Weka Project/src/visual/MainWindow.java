package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.sun.prism.Image;
import com.sun.swing.internal.plaf.basic.resources.basic_ja;

import logical.Prueba;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.SMO;
import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.gui.beans.Classifier;

public class MainWindow extends JFrame {

	private JPanel mainPanel;
	private File defaultlocation = new File(".");
	private JButton btnEditarDataset = new JButton("Editar Dataset");
	private JTextField ruta;
	private JTextPane resumen;
	private JPanel informacionArchivoPanel;
	private JPanel resultadosPanel;
	private Instances data;
	private JButton btnNaive;
	private JTextPane txtFldResultados;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/icons/weka-icon.png")));
		setTitle("Weka-Java Implementation");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 880, 592);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		JMenuItem mntmAbrirArchivo = new JMenuItem("Abrir archivo");
		mntmAbrirArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fc = new JFileChooser();
				//fc.showOpenDialog(null);
				fc.setCurrentDirectory(defaultlocation);
				fc.setDialogTitle("Elegir archivo");
				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				FileNameExtensionFilter filtro = new FileNameExtensionFilter("Solo archivos con la extension .arff", "arff");
				fc.setFileFilter(filtro);
				
				if(fc.showOpenDialog(btnEditarDataset) == JFileChooser.APPROVE_OPTION){
					informacionArchivoPanel.setVisible(true);
					//textField.setText(fc.getSelectedFile().getAbsolutePath());
					try {
						Prueba p = new Prueba(fc.getSelectedFile().getAbsolutePath());
						ruta.setText(fc.getSelectedFile().getAbsolutePath());
						resumen.setText(p.Resumen());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				
			}
		});
		mnArchivo.add(mntmAbrirArchivo);
		
		JMenuItem mntmEditarArchivo = new JMenuItem("Editar Archivo");
		mntmEditarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!ruta.getText().equalsIgnoreCase(""))
				{
					EditarArchivo ea = new EditarArchivo();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "No hay ningun archivo cargado");
				}
				
				
			}
		});
		mnArchivo.add(mntmEditarArchivo);
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPanel);
		mainPanel.setLayout(null);
		
		informacionArchivoPanel = new JPanel();
		informacionArchivoPanel.setVisible(false);
		
		resultadosPanel = new JPanel();
		resultadosPanel.setBorder(new TitledBorder(null, "Resultados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		resultadosPanel.setBounds(10, 385, 1124, 554);
		mainPanel.add(resultadosPanel);
		resultadosPanel.setVisible(false);
		
		JScrollPane scrllPResultados = new JScrollPane();
		GroupLayout gl_resultadosPanel = new GroupLayout(resultadosPanel);
		gl_resultadosPanel.setHorizontalGroup(
			gl_resultadosPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_resultadosPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrllPResultados, GroupLayout.PREFERRED_SIZE, 946, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(156, Short.MAX_VALUE))
		);
		gl_resultadosPanel.setVerticalGroup(
			gl_resultadosPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_resultadosPanel.createSequentialGroup()
					.addContainerGap(58, Short.MAX_VALUE)
					.addComponent(scrllPResultados, GroupLayout.PREFERRED_SIZE, 462, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		txtFldResultados = new JTextPane();
		scrllPResultados.setViewportView(txtFldResultados);
		resultadosPanel.setLayout(gl_resultadosPanel);
		informacionArchivoPanel.setBorder(new TitledBorder(null, "Informacion del archivo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		informacionArchivoPanel.setBounds(10, 22, 709, 335);
		mainPanel.add(informacionArchivoPanel);
		informacionArchivoPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 55, 689, 226);
		informacionArchivoPanel.add(scrollPane);
		
		resumen = new JTextPane();
		resumen.setEditable(false);
		scrollPane.setViewportView(resumen);
		
		ruta = new JTextField();
		ruta.setEditable(false);
		ruta.setBounds(66, 24, 633, 20);
		informacionArchivoPanel.add(ruta);
		ruta.setColumns(10);
		
		JLabel lblRuta = new JLabel("Ruta: ");
		lblRuta.setBounds(10, 27, 46, 14);
		informacionArchivoPanel.add(lblRuta);
		
		btnNaive = new JButton("Naive Bayes");
		btnNaive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resultadosPanel.setVisible(true);
				
				try {
					Prueba p = new Prueba(ruta.getText());
					data = p.getDataset();
					
					
					data.setClassIndex(data.numAttributes()-1);
					NaiveBayes nb = new NaiveBayes();
					nb.buildClassifier(data);
					txtFldResultados.setText("Clase Actual\tNB Prediccion"+"\n");
					for(int i=0;i<data.numInstances();i++)
					{
						double actualClass = data.instance(i).classValue();
						String actual = data.classAttribute().value((int)actualClass);
						
						Instance newInt = data.instance(i);
						
						double pred = nb.classifyInstance(newInt);
						
						String predString = data.classAttribute().value((int)pred);
						System.out.println(actual+", "+predString);
						
						txtFldResultados.setText(txtFldResultados.getText() + (actual +"\t" + predString) + "\n");
					}
					
					
					
					
					System.out.println(nb.displayModelInOldFormatTipText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNaive.setBounds(10, 301, 131, 23);
		informacionArchivoPanel.add(btnNaive);
		
		JLabel lblJavaIcon = new JLabel("");
		lblJavaIcon.setIcon(new ImageIcon(MainWindow.class.getResource("/icons/java icon.png")));
		lblJavaIcon.setBounds(0, 0, 1902, 955);
		mainPanel.add(lblJavaIcon);
		lblJavaIcon.setHorizontalAlignment(JLabel.CENTER);
		lblJavaIcon.setVerticalAlignment(JLabel.CENTER);
		
		JLabel lblWekaIcon = new JLabel("");
		lblWekaIcon.setIcon(new ImageIcon(MainWindow.class.getResource("/icons/weka-icon-background.png")));
		lblWekaIcon.setBounds(0, 0, 1902, 955);
		mainPanel.add(lblWekaIcon);
		lblWekaIcon.setHorizontalAlignment(JLabel.CENTER);
		lblWekaIcon.setVerticalAlignment(JLabel.CENTER);
		
	}
}
