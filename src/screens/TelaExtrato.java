package screens;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import persistence.ConexaoMySql;
import persistence.IConexao;
import templates.ClientePF;
import templates.ICliente;
import templates.IConta;
import javax.swing.JButton;
import java.awt.Font;

public class TelaExtrato extends JFrame{

	private static final long serialVersionUID = 1L;
	IConexao conexao;
	private JTable table;
	private String[] columnNames = {"Valor", "Data", "Tipo"};
	private Object[][] dataTable = {};
	private DefaultTableModel tableModel = new DefaultTableModel(dataTable, columnNames);
	private JScrollPane scrollPane;
	
	public TelaExtrato(ICliente cliente, IConta conta)
	{
		setSize(571, 327);
		getContentPane().setLayout(null);
		JPanel panel = new JPanel();
		setVisible(true);
		panel.setBounds(29, 30, 474, 137);
		getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		scrollPane = new JScrollPane(table = new JTable(tableModel));
		table.setEnabled(false);
		scrollPane.setViewportBorder((Border) new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		panel.add(scrollPane);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnVoltar.setBounds(29, 189, 89, 23);
		btnVoltar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new TelaClienteLogado(cliente, conta);
			}
		});
		getContentPane().add(btnVoltar);
		loadData(cliente);
	}
	public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {

		ResultSetMetaData metaData = rs.getMetaData();

		// names of columns
		Vector<String> columnNames = new Vector<String>();
		int columnCount = metaData.getColumnCount();
		for (int column = 1; column <= columnCount; column++) {
			columnNames.add(metaData.getColumnName(column));
		}

		// data of the table
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (rs.next()) {
			Vector<Object> vector = new Vector<Object>();
			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				vector.add(rs.getObject(columnIndex));
			}
			data.add(vector);
		}
		return new DefaultTableModel(data, columnNames);
	}
	private void loadData(ICliente cliente) {
		conexao=new ConexaoMySql();
		Connection conn = conexao.getConexao();
		PreparedStatement ps;
		try {
			if(cliente instanceof ClientePF)
			{
				ps = conn.prepareStatement("SELECT * FROM transacaoclientepf WHERE cpf_cliente = ?");
			}
			else
			{
				ps = conn.prepareStatement("SELECT * FROM transacaoclientepj WHERE cnpj_cliente = ?");
			}
			ps.setString(1, cliente.pegalogin());
	        ps.execute();
	        ResultSet rs =ps.getResultSet();
	        ResultSetMetaData metaData;
			try {
				metaData = rs.getMetaData();
				// Names of columns
	            Vector<String> columnNames = new Vector<String>();
	            int columnCount = metaData.getColumnCount();
	            for (int i = 1; i <= columnCount; i++) {
	                columnNames.add(metaData.getColumnName(i));
	            }
	            // Data of the table
	            Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	            while (rs.next()) {
	                Vector<Object> vector = new Vector<Object>();
	                for (int i = 1; i <= columnCount; i++) {
	                    vector.add(rs.getObject(i));
	                }
	                data.add(vector);
	            }
	            tableModel.setDataVector(data, columnNames);
	            conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}