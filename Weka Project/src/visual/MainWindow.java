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
	private static JTextField ruta;
	private static JTextPane resumen;
	private JPanel informacionArchivoPanel;
	private JPanel resultadosPanel;
	private Instances data;
	private JButton btnNaive;


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
				llenarInformacion();

			}
		});
		mnArchivo.add(mntmAbrirArchivo);

		JMenuItem mntmEditarArchivo = new JMenuItem("Editar Archivo");
		mntmEditarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				EditarArchivo ea = new EditarArchivo(ruta.getText());
				actualizarInformacion(ruta.getText());
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
		resultadosPanel.setBounds(10, 393, 709, 391);
		mainPanel.add(resultadosPanel);
		resultadosPanel.setVisible(false);
		resultadosPanel.setLayout(null);

		JScrollPane scrllPResultados = new JScrollPane();
		scrllPResultados.setBounds(16, 34, 646, 299);
		resultadosPanel.add(scrllPResultados);

		JTextPane txtFldResultados = new JTextPane();
		txtFldResultados.setEditable(false);
		scrllPResultados.setViewportView(txtFldResultados);
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
					txtFldResultados.setText("Clase Actual \t NB Prediccion"+"\n");

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


				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNaive.setBounds(10, 301, 125, 23);
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

	public void llenarInformacion()
	{
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(defaultlocation);
		fc.setDialogTitle("Elegir archivo");
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("Solo archivos con la extension .arff", "arff");
		fc.setFileFilter(filtro);

		if(fc.showOpenDialog(btnEditarDataset) == JFileChooser.APPROVE_OPTION){
			informacionArchivoPanel.setVisible(true);
			try {
				Prueba p = new Prueba(fc.getSelectedFile().getAbsolutePath());
				ruta.setText(fc.getSelectedFile().getAbsolutePath());
				resumen.setText(p.Resumen());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	public static void actualizarInformacion(String lugar){

		try {
			Prueba p = new Prueba(lugar);
			ruta.setText(lugar);
			resumen.setText(p.Resumen());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}

