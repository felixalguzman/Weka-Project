package visual;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class ListaInstancias extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel buttonPane;
	private JTable tablaInstancias;
	private DefaultTableModel modeloTabla;
	private Object[] fila;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListaInstancias dialog = new ListaInstancias();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListaInstancias() {
		setTitle("Lista de Instancias");
		setBounds(100, 100, 1036, 651);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(null);
			{
				JButton btnAgregar = new JButton("Agregar");
				btnAgregar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						fila = new Object[modeloTabla.getColumnCount()];
						modeloTabla.addRow(fila);
						tablaInstancias.setModel(modeloTabla);
						tablaInstancias.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
						tablaInstancias.getTableHeader().setReorderingAllowed(false);
						
						
					}
				});
				btnAgregar.setHorizontalAlignment(JButton.CENTER);
				btnAgregar.setVerticalAlignment(JButton.CENTER);
				btnAgregar.setBounds(368, 5, 150, 40);
				btnAgregar.setActionCommand("OK");
				buttonPane.add(btnAgregar);
				getRootPane().setDefaultButton(btnAgregar);
			}
			{
				JButton btnSalir = new JButton("Salir");
				btnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnSalir.setBounds(785, 5, 150, 40);
				btnSalir.setActionCommand("Cancel");
				buttonPane.add(btnSalir);
			}
		}
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 974, Short.MAX_VALUE)
						.addComponent(buttonPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 974, Short.MAX_VALUE))
					.addGap(15))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 490, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addComponent(buttonPane, GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE))
		);
		
		tablaInstancias = new JTable();
		scrollPane.setViewportView(tablaInstancias);
		modeloTabla = new DefaultTableModel();
		String[] nombresColumnas ={"Column_1","Column_2","Column_3","Column_4"};
		modeloTabla.setColumnIdentifiers(nombresColumnas);
		cargarInstancias();
		contentPanel.setLayout(gl_contentPanel);
	}

	public void cargarInstancias() {
		modeloTabla.setRowCount(0);
		fila = new Object[modeloTabla.getColumnCount()];
		//Realizar for que recorra cada una de las filas a cargar
		tablaInstancias.setModel(modeloTabla);
		tablaInstancias.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tablaInstancias.getTableHeader().setReorderingAllowed(false);
	}
}
