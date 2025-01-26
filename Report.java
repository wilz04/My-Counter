import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class Report extends JInternalFrame {
  
  public static final int LINEAL = 0;
  public static final int DSD = 1;
  public static final int SDA = 2;
  
  public Report(String title, Double value, double mod, int ages, int method) {
    super(title, false, true, false, true);
    
    JTable table;
    switch (method) {
      case LINEAL:
        table = new_LinealTable(value, mod, ages);
        break;
      case DSD:
        table = new_DSDTable(value, mod, ages);
        break;
      case SDA:
        table = new_SDATable(value, mod, ages);
        break;
      default:
        table = null;
    }
    table.setFont(new Font("Verdana", 0, 10));
    table.setEnabled(false);
    table.getTableHeader().setFont(new Font("Verdana", 1, 10));
    
    add(table.getTableHeader(), BorderLayout.NORTH);
    add(table, BorderLayout.CENTER);
    
    JScrollPane pane = new JScrollPane(getContentPane(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    setContentPane(pane);
    
    pack();
    setVisible(true);
  }
  
  private JTable new_LinealTable(Double value, double mod, int ages) {
    Double dev = 1/new Double(ages);
    Double dis = value - mod;
    Double les = .0;
    Object head[] = {"Año", "Costo", "Taza Dep", "CD", "Dep Ac", "VL"};
    Object body[][] = new Object[ages+1][6];
    for (Integer age=0; age<=ages; age++) {
      if (age == 0) {
        body[0][0] = 0;
        body[0][1] = value;
      } else {
        les += dis*dev;
        body[age][0] = age;
        body[age][2] = dev;
        body[age][3] = dis;
        body[age][4] = les;
        body[age][5] = value - les;
      }
    }
    JTable table = new JTable(body, head);
    return table;
  }
  
  private JTable new_DSDTable(Double value, double mod, int ages) {
    Double dis = value;
    Double dev = 2/new Double(ages);
    Double car;
    Double les = .0;
    Object head[] = {"Año", "Costo", "CD", "Taza Dep", "Importe", "Dep Ac", "VL"};
    Object body[][] = new Object[ages+1][7];
    for (Integer age=0; age<=ages; age++) {
      if (age == 0) {
        body[0][0] = 0;
        body[0][1] = value;
      } else if (age < ages) {
        car = dis*dev;
        les += car;
        body[age][0] = age;
        body[age][2] = dis;
        body[age][3] = dev;
        body[age][4] = car;
        body[age][5] = les;
        dis = value - les;
        body[age][6] = dis;
      } else {
        body[age][0] = age;
        body[age][4] = dis - mod;
        body[age][5] = value - mod;
        body[age][6] = mod;
      }
    }
    JTable table = new JTable(body, head);
    return table;
  }
  
  private JTable new_SDATable(Double value, double mod, int ages) {
    double tmp = new Double(ages);
    tmp = tmp*(tmp + 1)/2;
    Double dev;
    Double dis = value - mod;
    Double car;
    Double les = .0;
    Object head[] = {"Año", "Costo", "Taza Dep", "CD", "Importe", "Dep Ac", "VL"};
    Object body[][] = new Object[ages+1][7];
    for (Integer age=0; age<=ages; age++) {
      if (age == 0) {
        body[0][0] = 0;
        body[0][1] = value;
      } else {
        dev = (ages - age + 1)/tmp;
        car = dis*dev;
        les += car;
        body[age][0] = age;
        body[age][2] = dev;
        body[age][3] = dis;
        body[age][4] = car;
        body[age][5] = les;
        body[age][6] = value - les;
      }
    }
    JTable table = new JTable(body, head);
    return table;
  }
  
}
