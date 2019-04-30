package gui;

import javax.swing.GroupLayout.SequentialGroup;

public class StyleEditor extends javax.swing.JDialog
{
  private static final String windowTitle = "Style Editor";
  private static final String ChooseAColorForSunBrightness = "Choose a color for the brightness-value of the sun";
  private static final String ChooseAColorForSunAmbient = "Choose a color for the ambient-value of the sun";
  private static final String ChooseAColorForSunShadow = "Choose a color for the shadow";
  private static final java.awt.Color colorFormatIncorrect = java.awt.Color.PINK;
  
  private GUI parent;
  
  private config.ConvertOption convertOptions;
  private int scale;
  private String skyTexture;
  private buildable.Color colorSunBrightness = new buildable.Color(java.awt.Color.BLACK);
  private buildable.Color colorSunAmbient = new buildable.Color(java.awt.Color.BLACK);
  private buildable.Color colorSunShadow = new buildable.Color(java.awt.Color.BLACK);
  private javax.swing.JButton jButtonApply;
  private javax.swing.JButton jButtonCancel;
  
  public StyleEditor(GUI parent, config.ConvertOption convertOptions) { super(parent, true);
    this.parent = parent;
    this.convertOptions = convertOptions;
    scale = convertOptions.getScale();
    skyTexture = convertOptions.getSkyTexture();
    
    colorSunBrightness = convertOptions.getSunLight();
    colorSunAmbient = convertOptions.getSunAmbient();
    colorSunShadow = convertOptions.getSunShadow();
    initComponents();
    updateSunPropertyButtons();
  }
  
  private void udateColorSunBrightness(ColorChooserDialog ccd) {
    colorSunBrightness = ccd.getColor();
    updateColoredButton(jButtonSunBrightness, colorSunBrightness);
  }
  
  private void udateColorSunAmbient(ColorChooserDialog ccd) {
    colorSunAmbient = ccd.getColor();
    updateColoredButton(jButtonSunAmbient, colorSunAmbient);
  }
  
  private void udateColorSunShadow(ColorChooserDialog ccd) {
    colorSunShadow = ccd.getColor();
    updateColoredButton(jButtonSunShadow, colorSunShadow);
  }
  
  private void updateSunPropertyButtons() {
    updateColoredButton(jButtonSunBrightness, colorSunBrightness);
    updateColoredButton(jButtonSunAmbient, colorSunAmbient);
    updateColoredButton(jButtonSunShadow, colorSunShadow);
  }
  
  private void updateColoredButton(javax.swing.JButton button, buildable.Color color) {
    if (color != null) {
      button.setForeground(color.getJavaAwtColorNegative());
      button.setBackground(color.getJavaAwtColor());
    }
  }
  
  private javax.swing.JButton jButtonSunAmbient;
  private javax.swing.JButton jButtonSunBrightness;
  private javax.swing.JButton jButtonSunShadow;
  private javax.swing.JColorChooser jColorChooser1;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JTextField jTextFieldScale;
  private javax.swing.JTextField jTextFieldSkyTexture;
  private void initComponents() { jColorChooser1 = new javax.swing.JColorChooser();
    jLabel1 = new javax.swing.JLabel();
    jTextFieldScale = new javax.swing.JTextField();
    jButtonApply = new javax.swing.JButton();
    jButtonCancel = new javax.swing.JButton();
    jLabel2 = new javax.swing.JLabel();
    jTextFieldSkyTexture = new javax.swing.JTextField();
    jLabel3 = new javax.swing.JLabel();
    jButtonSunBrightness = new javax.swing.JButton();
    jButtonSunAmbient = new javax.swing.JButton();
    jButtonSunShadow = new javax.swing.JButton();
    
    setDefaultCloseOperation(2);
    setTitle("Style Editor");
    
    jLabel1.setText("Scale:");
    
    jTextFieldScale.setHorizontalAlignment(4);
    jTextFieldScale.setText(new Integer(scale).toString());
    jTextFieldScale.setCursor(new java.awt.Cursor(2));
    jTextFieldScale.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        StyleEditor.this.jTextFieldScaleActionPerformed(evt);
      }
      
    });
    jButtonApply.setText("Apply");
    jButtonApply.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mousePressed(java.awt.event.MouseEvent evt) {
        StyleEditor.this.jButtonApplyMousePressed(evt);
      }
      
    });
    jButtonCancel.setText("Cancel");
    jButtonCancel.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mousePressed(java.awt.event.MouseEvent evt) {
        StyleEditor.this.jButtonCancelMousePressed(evt);
      }
      
    });
    jLabel2.setText("Sky-Texture:");
    
    jTextFieldSkyTexture.setText(skyTexture);
    
    jLabel3.setText("Sun:");
    
    jButtonSunBrightness.setForeground(colorSunBrightness.getJavaAwtColor());
    jButtonSunBrightness.setText("brightness");
    jButtonSunBrightness.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        StyleEditor.this.jButtonSunBrightnessActionPerformed(evt);
      }
      
    });
    jButtonSunAmbient.setText("ambient");
    jButtonSunAmbient.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        StyleEditor.this.jButtonSunAmbientActionPerformed(evt);
      }
      
    });
    jButtonSunShadow.setText("shadow");
    jButtonSunShadow.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        StyleEditor.this.jButtonSunShadowActionPerformed(evt);
      }
      
    });
    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(jLabel1).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jTextFieldScale, -2, 62, -2)).addGroup(layout.createSequentialGroup().addComponent(jLabel2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jTextFieldSkyTexture, -1, 222, 32767)).addGroup(layout.createSequentialGroup().addComponent(jLabel3).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jButtonSunBrightness, -2, 104, -2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jButtonSunAmbient).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jButtonSunShadow)).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addComponent(jButtonApply).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jButtonCancel))).addContainerGap()));
    

























    layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel1).addComponent(jTextFieldScale, -2, -1, -2)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel2).addComponent(jTextFieldSkyTexture, -2, -1, -2)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel3).addComponent(jButtonSunBrightness, -2, 18, -2).addComponent(jButtonSunAmbient, -2, 18, -2).addComponent(jButtonSunShadow, -2, 18, -2)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, 32767).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false).addComponent(jButtonCancel, -1, -1, 32767).addComponent(jButtonApply, -1, 23, 32767)).addContainerGap()));
    






















    pack();
  }
  
  private void jButtonApplyMousePressed(java.awt.event.MouseEvent evt) {
    boolean continu = true;
    try {
      convertOptions.setScale(new Integer(jTextFieldScale.getText()).intValue());
      convertOptions.setSkyTexture(jTextFieldSkyTexture.getText());
      convertOptions.setSunLight(colorSunBrightness);
      convertOptions.setSunAmbient(colorSunAmbient);
      convertOptions.setSunShadow(colorSunShadow);
    }
    catch (NumberFormatException e) {
      continu = false;
      jTextFieldScale.setBackground(colorFormatIncorrect);
    }
    if (continu) {
      setVisible(false);
    }
  }
  

  private void jTextFieldScaleActionPerformed(java.awt.event.ActionEvent evt) {}
  
  private void jButtonSunBrightnessActionPerformed(java.awt.event.ActionEvent evt)
  {
    ColorChooserDialog ccd = new ColorChooserDialog(parent, "Choose a color for the brightness-value of the sun", colorSunBrightness);
    ccd.setVisible(true);
    udateColorSunBrightness(ccd);
  }
  
  private void jButtonCancelMousePressed(java.awt.event.MouseEvent evt) {
    setVisible(false);
  }
  
  private void jButtonSunAmbientActionPerformed(java.awt.event.ActionEvent evt) {
    ColorChooserDialog ccd = new ColorChooserDialog(parent, "Choose a color for the ambient-value of the sun", colorSunAmbient);
    ccd.setVisible(true);
    udateColorSunAmbient(ccd);
  }
  
  private void jButtonSunShadowActionPerformed(java.awt.event.ActionEvent evt) {
    ColorChooserDialog ccd = new ColorChooserDialog(parent, "Choose a color for the shadow", colorSunShadow);
    ccd.enableLuminosity(false);
    ccd.setVisible(true);
    udateColorSunShadow(ccd);
  }
}
