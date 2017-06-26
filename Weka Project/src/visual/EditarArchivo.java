package visual;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import logical.Prueba;

import java.awt.Toolkit;

/*
 * A simple Text Editor.  This demonstrates the use of a 
 * JFileChooser for the user to select a file to read from or write to.
 * It also demonstrates reading and writing text files.
 */
public class EditarArchivo implements ActionListener {
	// Size of editing text area.
	private static final int NUM_ROWS = 25;
	private static final int NUM_COLS = 50;
	private static String lugar;

	// Buttons to save and load files.
	private JButton saveButton;

	// Area where the user does the editing
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

	// Listener for button clicks that loads the
	// specified files and puts it in the
	// editor.
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == saveButton) {
			saveFile(lugar);
			
			
			
		} else {
			loadFile(lugar);
			
		}
	}

	// Display a file chooser so the user can select a file
	// to save to. Then write the contents of the text area
	// to that file. Does nothing if the user cancels out
	// of the file chooser.
	private void saveFile(String lugar) {
		File file;

		// create and display dialog box to get file name
		
			try {
				// Now write to the file
				PrintWriter output = new PrintWriter(new FileWriter(lugar));
				output.println(text.getText());
				output.close();
				JOptionPane.showMessageDialog(null, "Archivo guardado correctamente");
			} catch (IOException e) {
				JOptionPane.showMessageDialog(text, "No se puede salvar el archivo"
						+ e.getMessage());
			}
		
	}

	// Display a file chooser so the user can select a file to load.
	// Then load the file into the editing area. Does nothing if
	// the user cancels the file chooser.
	private static void loadFile(String lugar) {
		String line;
		File file;

		

			try {
				// Open the file.
				BufferedReader input = new BufferedReader(new FileReader(lugar));

				// Clear the editing area
				text.setText("");

				// Fill up the ediitng area with the contents of the file being
				// read.
				line = input.readLine();
				while (line != null) {
					text.append(line + "\n");
					line = input.readLine();
				}

				// Close the file
				input.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(text, "Can't load file "
						+ e.getMessage());
			}
		
	}
	
	

	
}
