package visual;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


import java.awt.Toolkit;


public class EditarArchivo implements ActionListener {
	
	private static final int NUM_ROWS = 25;
	private static final int NUM_COLS = 50;
	private static String lugar;
	private JButton saveButton;
	private static JTextArea text;

	// Creates the GUI
	public EditarArchivo(String lugar) {
		this.lugar = lugar;
		JFrame frmEditarArchivo = new JFrame();
		frmEditarArchivo.setTitle("Editar Archivo");
		frmEditarArchivo.setIconImage(Toolkit.getDefaultToolkit().getImage(EditarArchivo.class.getResource("/icons/weka-icon.png")));
		JPanel buttonPanel = new JPanel();
		saveButton = new JButton("Guardar Archivo");
		buttonPanel.add(saveButton);

		text = new JTextArea(NUM_ROWS, NUM_COLS);
		text.setFont(new Font("System", Font.PLAIN, 24));
		JScrollPane textScroller = new JScrollPane(text);
		Container contentPane = frmEditarArchivo.getContentPane();
		contentPane.add(textScroller, BorderLayout.CENTER);
		contentPane.add(buttonPanel, BorderLayout.NORTH);

		saveButton.addActionListener(this);

		frmEditarArchivo.pack();
		frmEditarArchivo.setVisible(true);
		loadFile(lugar);
	
	}

	
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == saveButton) {
			saveFile(lugar);
			MainWindow.actualizarInformacion(lugar);
		} else {
			loadFile(lugar);
			
		}
	}

	
	private void saveFile(String lugar) {

			try {
				
				PrintWriter output = new PrintWriter(new FileWriter(lugar));
				output.println(text.getText());
				output.close();
				JOptionPane.showMessageDialog(null, "Archivo guardado correctamente");
			} catch (IOException e) {
				JOptionPane.showMessageDialog(text, "No se puede salvar el archivo"
						+ e.getMessage());
			}
		
	}

	
	private  void loadFile(String lugar) {
		String line;
	
			try {
				
				BufferedReader input = new BufferedReader(new FileReader(lugar));
				text.setText("");

				
				line = input.readLine();
				while (line != null) {
					text.append(line + "\n");
					line = input.readLine();
				}

				input.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(text, "Can't load file "
						+ e.getMessage());
			}
		
	}
	
	

	
}
