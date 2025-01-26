import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JFrame implements ActionListener {
  
  private Font normal;
  private Font blacky;
  private JButton blin;
  private JButton bdsd;
  private JButton bsda;
  private JButton bhel;
  private JDesktopPane desktop;
  private JPanel panel;
  private JTextField tfvalue;
  private JTextField tfmod;
  private JTextField tfages;
  
  public Main() {
    super("MyCounter");
    setBounds((1024-550)/2, (768-400)/2, 550, 400);
    
    normal = new Font("Verdana", 0, 10);
    blacky = new Font("Verdana", 1, 10);
    
    blin = new JButton("Líneal");
    blin.setFont(blacky);
    blin.addActionListener(this);
    
    bdsd = new JButton("DSD");
    bdsd.setFont(blacky);
    bdsd.addActionListener(this);
    
    bsda = new JButton("SDA");
    bsda.setFont(blacky);
    bsda.addActionListener(this);
    
    bhel = new JButton("Ayuda");
    bhel.setFont(blacky);
    bhel.addActionListener(this);
    
    JToolBar toolbar = new JToolBar();
    toolbar.add(blin);
    toolbar.add(bdsd);
    toolbar.add(bsda);
    toolbar.addSeparator();
    toolbar.add(bhel);
    
    add(toolbar, BorderLayout.NORTH);
    addWindowListener(
      new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
          dispose();
          System.exit(0);
        }
      }
    );
    
    desktop = new JDesktopPane();
    desktop.setBackground(new Color(0x7A, 0x8A, 0x99));
    add(desktop, BorderLayout.CENTER);
    
    JLabel lvalue = new JLabel("Valor Original: ");
    lvalue.setFont(normal);
    lvalue.setHorizontalAlignment(JTextField.RIGHT);
    tfvalue = new JTextField();
    tfvalue.setFont(normal);
    
    JLabel lmod = new JLabel("Valor Residual: ");
    lmod.setFont(normal);
    lmod.setHorizontalAlignment(JTextField.RIGHT);
    tfmod = new JTextField();
    tfmod.setFont(normal);
    
    JLabel lages = new JLabel("Años: ");
    lages.setFont(normal);
    lages.setHorizontalAlignment(JTextField.RIGHT);
    tfages = new JTextField();
    tfages.setFont(normal);
    
    panel = new JPanel(new GridLayout(3, 2));
    panel.add(lvalue);
    panel.add(tfvalue);
    panel.add(lmod);
    panel.add(tfmod);
    panel.add(lages);
    panel.add(tfages);
    
    setVisible(true);
  }
  
  private void new_Report(String title, int method) {
    if (JOptionPane.showConfirmDialog(this, panel, "Nuevo Cálculo", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
      try {
        Double value = Double.parseDouble(tfvalue.getText());
        double mod = Double.parseDouble(tfmod.getText());
        int ages = Integer.parseInt(tfages.getText());
        Report rpt = new Report(title, value, mod, ages, method);
        desktop.add(rpt);
        
        int spacer = ((desktop.getComponentCount() - 1)%((getHeight() - 60)/10))*10;
        int height = rpt.getHeight();
        if (height > 300) {
          height = 300;
        }
        rpt.setBounds(rpt.getX()+spacer, rpt.getY()+spacer, rpt.getWidth(), height);
        
        rpt.setSelected(true);
      } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Datos no válidos!", "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
  }
  
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == blin) {
      new_Report("Línea Recta [" + (desktop.getComponentCount() + 1) + "]", Report.LINEAL);
    }
    if (e.getSource() == bdsd) {
      new_Report("Doble Saldo Decresiente [" + (desktop.getComponentCount() + 1) + "]", Report.DSD);
    }
    if (e.getSource() == bsda) {
      new_Report("Suma de Digitos de los Años [" + (desktop.getComponentCount() + 1) + "]", Report.SDA);
    }
    if (e.getSource() == bhel) {
      JOptionPane.showMessageDialog(this, "Elige alguno de los métodos de depreciación para iniciar!\n\nDesarrollado por Wil C", "MyCounter®", JOptionPane.INFORMATION_MESSAGE);
    }
  }
    
  public static void main(String args[]) {
    new Main();
  } 
  
}
