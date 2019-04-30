package gui;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;

public class LuminosityChooser extends javax.swing.JPanel
{
  private int luminosity = 0;
  private javax.swing.JLabel jLabel1;
  
  public LuminosityChooser() {
    initComponents();
  }
  
  public void setSlider(int luminosity) {
    this.luminosity = luminosity;
    double logarg = this.luminosity * 171.0D / 200000.0D + 1.0D;
    
    int value = (int)(Math.log(logarg) * 100.0D);
    jSlider.setValue(value);
    jTextFieldLuminosity.setText(Integer.toString(luminosity));
  }
  

  private void setLuminosity(int silderValue)
  {
    luminosity = (10 * (int)((Math.exp(silderValue / 100.0D) - 1.0D) * 100.0D * 200.0D / 171.0D));
    jTextFieldLuminosity.setText(Integer.toString(luminosity));
  }
  
  public int getLuminosity() {
    return luminosity;
  }
  







  private void initComponents()
  {
    jSlider = new JSlider();
    jTextFieldLuminosity = new JTextField();
    jLabel1 = new javax.swing.JLabel();
    
    jSlider.addChangeListener(new javax.swing.event.ChangeListener() {
      public void stateChanged(ChangeEvent evt) {
        LuminosityChooser.this.jSliderStateChanged(evt);
      }
    });
    jSlider.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
      public void mouseDragged(MouseEvent evt) {
        LuminosityChooser.this.jSliderMouseDragged(evt);
      }
      
    });
    jTextFieldLuminosity.setText(Integer.toString(luminosity));
    jTextFieldLuminosity.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyReleased(KeyEvent evt) {
        LuminosityChooser.this.jTextFieldLuminosityKeyReleased(evt);
      }
      
    });
    jLabel1.setText("Luminosity:");
    
    GroupLayout layout = new GroupLayout(this);
    setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(jLabel1).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jTextFieldLuminosity, -2, 60, -2).addContainerGap(257, 32767)).addComponent(jSlider, -1, 375, 32767));
    







    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(jLabel1).addComponent(jTextFieldLuminosity, -2, -1, -2)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jSlider, -2, -1, -2).addContainerGap()));
  }
  


  private JSlider jSlider;
  

  private JTextField jTextFieldLuminosity;
  

  private void jSliderMouseDragged(MouseEvent evt)
  {
    setLuminosity(jSlider.getValue());
  }
  
  private void jTextFieldLuminosityKeyReleased(KeyEvent evt) {
    update();
  }
  
  private void jSliderStateChanged(ChangeEvent evt) {
    setLuminosity(jSlider.getValue());
  }
  
  private void update() {
    int value = 0;
    try {
      value = new Integer(jTextFieldLuminosity.getText()).intValue();
      setSlider(value);
    }
    catch (NumberFormatException e) {}
  }
}
