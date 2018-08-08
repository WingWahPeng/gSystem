package bean;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class FixedTable
  extends JTable
{
  Color bankgrandColor = new Color(228, 254, 254);
  boolean isCellEditable = true;
  
  public boolean isCellEditable()
  {
    return this.isCellEditable;
  }
  
  public void setCellEditable(boolean isCellEditable)
  {
    this.isCellEditable = isCellEditable;
  }
  
  public FixedTable(TableModel dm)
  {
    super(dm);
    init();
  }
  
  public FixedTable()
  {
    init();
  }
  
  private void init()
  {
    setSelectionForeground(Color.BLUE);
    setSelectionMode(0);
    setDefaultRenderer(Object.class, new MyRender());
    setRowHeight(30);
    overridCopyAction();
  }
  
  private void overridCopyAction()
  {
    KeyStroke keyStroke = KeyStroke.getKeyStroke(67, 128, true);
    InputMap inputMap = getInputMap();
    inputMap.put(keyStroke, "copy");
    ActionMap actionMap = getActionMap();
    actionMap.put("copy", new AbstractAction()
    {
      public void actionPerformed(ActionEvent e)
      {
        int row = FixedTable.this.getSelectedRow();
        int column = FixedTable.this.getSelectedColumn();
        String value = (String)FixedTable.this.getValueAt(row, column);
        Toolkit tool = Toolkit.getDefaultToolkit();
        Clipboard clipboard = tool.getSystemClipboard();
        Transferable trans = new StringSelection(value);
        clipboard.setContents(trans, null);
      }
    });
  }
  
  public JTableHeader getTableHeader()
  {
    JTableHeader tableHeader = super.getTableHeader();
    tableHeader.setReorderingAllowed(false);
    DefaultTableCellRenderer hr = (DefaultTableCellRenderer)tableHeader.getDefaultRenderer();
    
    hr.setHorizontalAlignment(0);
    hr.setFont(new Font("黑体", 1, 20));
    hr.setPreferredSize(new Dimension(hr.getWidth(), 30));
    return tableHeader;
  }
  
  public boolean isCellEditable(int row, int column)
  {
    return this.isCellEditable;
  }
  
  public void hiddenFirstColumn()
  {
    TableColumnModel firstColumnModel = getColumnModel();
    int count = firstColumnModel.getColumnCount();
    if (count > 0)
    {
      TableColumn firstColumn = firstColumnModel.getColumn(0);
      firstColumn.setMaxWidth(0);
      firstColumn.setMinWidth(0);
      firstColumn.setPreferredWidth(0);
      firstColumn.setResizable(false);
    }
  }
  
  class MyRender
    implements TableCellRenderer
  {
    MyRender() {}
    
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
      DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
      cr.setHorizontalAlignment(0);
      
      JLabel renderer = (JLabel)cr.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, 
        column);
      if (row % 2 != 0) {
        renderer.setBackground(FixedTable.this.bankgrandColor);
      } else {
        renderer.setBackground(Color.WHITE);
      }
      renderer.setFont(new Font("微软雅黑", 0, 14));
      return renderer;
    }
  }
}
