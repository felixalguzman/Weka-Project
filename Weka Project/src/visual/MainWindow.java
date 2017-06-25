package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

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
		btnAbrirDataset.setBounds(15, 52, 160, 37);
		panel.add(btnAbrirDataset);
		
		JButton btnEditarDataset = new JButton("Editar Dataset");
		btnEditarDataset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditarDataset newEditDataset= new EditarDataset();
				newEditDataset.show();
				
			}
		});
		btnEditarDataset.setBounds(15, 134, 160, 37);
		panel.add(btnEditarDataset);
		
		textField = new JTextField();
		textField.setBounds(201, 54, 524, 32);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnMostrarGraficas = new JButton("Mostrar Graficos");
		btnMostrarGraficas.setBounds(15, 211, 160, 37);
		panel.add(btnMostrarGraficas);
	}
}
