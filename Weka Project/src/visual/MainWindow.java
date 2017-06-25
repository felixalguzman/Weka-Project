package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

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

public class MainWindow extends JFrame {

	private JPanel mainPanel;
	private File defaultlocation = new File(".");
	private JButton btnEditarDataset = new JButton("Editar Dataset");
	private JTextField ruta;
	private JTextPane resumen;
	private JPanel informacionArchivoPanel;
	
	

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
				FileNameExtensionFilter filtro = new FileNameExtensionFilter("Solo archivos con .arff", "arff");
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
		mainPanel = new JPanel();
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPanel);
		mainPanel.setLayout(null);
		
		informacionArchivoPanel = new JPanel();
		informacionArchivoPanel.setVisible(false);
		informacionArchivoPanel.setBorder(new TitledBorder(null, "Informacion del archivo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		informacionArchivoPanel.setBounds(10, 22, 709, 292);
		mainPanel.add(informacionArchivoPanel);
		informacionArchivoPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 55, 689, 226);
		informacionArchivoPanel.add(scrollPane);
		
		resumen = new JTextPane();
		scrollPane.setViewportView(resumen);
		
		ruta = new JTextField();
		ruta.setEditable(false);
		ruta.setBounds(66, 24, 633, 20);
		informacionArchivoPanel.add(ruta);
		ruta.setColumns(10);
		
		JLabel lblRuta = new JLabel("Ruta: ");
		lblRuta.setBounds(10, 27, 46, 14);
		informacionArchivoPanel.add(lblRuta);
		
		JLabel lblJavaIcon = new JLabel("");
		lblJavaIcon.setIcon(new ImageIcon(MainWindow.class.getResource("/icons/java icon.png")));
		lblJavaIcon.setBounds(0, 0, 1902, 955);
		lblJavaIcon.setHorizontalAlignment(JLabel.CENTER);
		lblJavaIcon.setVerticalAlignment(JLabel.CENTER);
		mainPanel.add(lblJavaIcon);
		
		JLabel lblWekaIcon = new JLabel("");
		lblWekaIcon.setIcon(new ImageIcon(MainWindow.class.getResource("/icons/weka-icon-background.png")));
		lblWekaIcon.setVerticalAlignment(SwingConstants.CENTER);
		lblWekaIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblWekaIcon.setBounds(0, 0, 1902, 955);
		mainPanel.add(lblWekaIcon);
		
	}
}
