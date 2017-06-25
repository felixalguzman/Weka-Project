package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import logical.Prueba;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private File defaultlocation = new File(".");
	private JButton btnEditarDataset = new JButton("Editar Dataset");

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
		setTitle("Weka ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 880, 592);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Opciones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(15, 158, 828, 360);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		JButton btnAbrirDataset = new JButton("Abrir Dataset");
		
		
		btnAbrirDataset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fc = new JFileChooser();
				//fc.showOpenDialog(null);
				fc.setCurrentDirectory(defaultlocation);
				fc.setDialogTitle("Elegir archivo");
				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				FileNameExtensionFilter filtro = new FileNameExtensionFilter("Solo archivos con .arff", "arff");
				fc.setFileFilter(filtro);
				
				if(fc.showOpenDialog(btnEditarDataset) == JFileChooser.APPROVE_OPTION)
				{
					textField.setText(fc.getSelectedFile().getAbsolutePath());
					try {
						Prueba p = new Prueba(fc.getSelectedFile().getAbsolutePath());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				//System.out.println("Ubicacion: " + fc.getSelectedFile().getAbsolutePath());
				
			}
		});
		btnAbrirDataset.setBounds(15, 52, 160, 37);
		panel.add(btnAbrirDataset);
		
		
		
		btnEditarDataset = new JButton("Editar Dataset");
		btnEditarDataset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditarDataset newEditDataset= new EditarDataset();
				newEditDataset.show();
				
				
			}
		});
		btnEditarDataset.setBounds(15, 134, 160, 37);
		panel.add(btnEditarDataset);
		
		
		
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(201, 54, 524, 32);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnMostrarGraficas = new JButton("Mostrar Graficos");
		btnMostrarGraficas.setBounds(15, 211, 160, 37);
		panel.add(btnMostrarGraficas);
	}
	
	
}
