package gui;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;

public class ColorChooserDialog extends javax.swing.JDialog
{
  private buildable.Color result;
  private JButton jButtonApply;
  private JButton jButtonCancel;
  private javax.swing.JColorChooser jColorChooser1;
  private javax.swing.JLabel jLabel1;
  private LuminosityChooser luminosityChooser1;
  
  public ColorChooserDialog(java.awt.Frame parent, String title, buildable.Color color)
  {
    super(parent, true);
    setLocation(getLocationx, getLocationy);
    setTitle(title);
    result = color;
    initComponents();
    luminosityChooser1.setSlider(color.getAlpha());
    jColorChooser1.setColor(color.getJavaAwtColor());
  }
  
  public void enableLuminosity(boolean enabled) {
    luminosityChooser1.setVisible(enabled);
  }
  
  private void updateColor() {
    result = new buildable.Color(jColorChooser1.getColor());
    result.setAlpha(luminosityChooser1.getLuminosity());
  }
  
  public buildable.Color getColor() {
    return result;
  }
  







  private void initComponents()
  {
    jColorChooser1 = new javax.swing.JColorChooser();
    jButtonCancel = new JButton();
    jButtonApply = new JButton();
    luminosityChooser1 = new LuminosityChooser();
    jLabel1 = new javax.swing.JLabel();
    
    setDefaultCloseOperation(2);
    
    jButtonCancel.setText("Cancel");
    jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        ColorChooserDialog.this.jButtonCancelActionPerformed(evt);
      }
      
    });
    jButtonApply.setText("Apply");
    jButtonApply.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        ColorChooserDialog.this.jButtonApplyActionPerformed(evt);
      }
      
    });
    jLabel1.setText("Color:");
    
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addComponent(jButtonApply).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jButtonCancel)).addComponent(jColorChooser1, -2, 595, -2).addComponent(luminosityChooser1, -1, 595, 32767).addComponent(jLabel1)).addContainerGap()));
    












    layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap().addComponent(luminosityChooser1, -2, -1, -2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, 32767).addComponent(jLabel1).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jColorChooser1, -2, 316, -2).addGap(54, 54, 54).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jButtonCancel).addComponent(jButtonApply)).addContainerGap()));
    














    pack();
  }
  
  private void jButtonApplyActionPerformed(java.awt.event.ActionEvent evt) {
    updateColor();
    setVisible(false);
  }
  
  private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {
    setVisible(false);
  }
}
