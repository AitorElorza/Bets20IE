package table;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import domain.Mugimendua;
import domain.Registered;
import domain.User;

public class UserAdapter extends AbstractTableModel {
	
	
	private String[] colNames = {"Deskribapena","Data","Zenbatekoa","Gertaera","Galdera","Kuota","Egoera"};
	private final Vector<Mugimendua> mugi;
	int rows;
	
	public UserAdapter(Registered u) {
		mugi=u.getMugimenduak();
		rows=mugi.size();
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return rows;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return colNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Mugimendua m= mugi.get(rowIndex);
		
		return m;
			
	}
	/*public String getColumnName(int col) {
	      return colNames[col];
	   }
	public Class getColumnClass(int col) {
	      if (col == 2) {
	         return Double.class;
	      }
	      else {
	         return String.class;
	      }
	   }*/

}
