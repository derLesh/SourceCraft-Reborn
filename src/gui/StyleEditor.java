package gui;

import config.ConvertOption;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.LayoutStyle.ComponentPlacement;

public class StyleEditor
        extends JDialog
{
  private static final String windowTitle = "Style Editor";
  private static final String ChooseAColorForSunBrightness = "Choose a color for the brightness-value of the sun";
  private static final String ChooseAColorForSunAmbient = "Choose a color for the ambient-value of the sun";
  private static final String ChooseAColorForSunShadow = "Choose a color for the shadow";
  private static final java.awt.Color colorFormatIncorrect = java.awt.Color.PINK;
  private GUI parent;
  private ConvertOption convertOptions;
  private int scale;
  private String skyTexture;
  private buildable.Color colorSunBrightness = new buildable.Color(java.awt.Color.BLACK);
  private buildable.Color colorSunAmbient = new buildable.Color(java.awt.Color.BLACK);
  private buildable.Color colorSunShadow = new buildable.Color(java.awt.Color.BLACK);
  private JButton jButtonApply;
  private JButton jButtonCancel;
  private JButton jButtonSunAmbient;
  private JButton jButtonSunBrightness;
  private JButton jButtonSunShadow;
  private JColorChooser jColorChooser1;
  private JLabel jLabel1;
  private JLabel jLabel2;
  private JLabel jLabel3;
  private JTextField jTextFieldScale;
  private JTextField jTextFieldSkyTexture;

  public StyleEditor(GUI parent, ConvertOption convertOptions)
  {
    super(parent, true);
    this.parent = parent;
    this.convertOptions = convertOptions;
    this.scale = convertOptions.getScale();
    this.skyTexture = convertOptions.getSkyTexture();

    this.colorSunBrightness = convertOptions.getSunLight();
    this.colorSunAmbient = convertOptions.getSunAmbient();
    this.colorSunShadow = convertOptions.getSunShadow();
    initComponents();
    updateSunPropertyButtons();
  }

  private void udateColorSunBrightness(ColorChooserDialog ccd)
  {
    this.colorSunBrightness = ccd.getColor();
    updateColoredButton(this.jButtonSunBrightness, this.colorSunBrightness);
  }

  private void udateColorSunAmbient(ColorChooserDialog ccd)
  {
    this.colorSunAmbient = ccd.getColor();
    updateColoredButton(this.jButtonSunAmbient, this.colorSunAmbient);
  }

  private void udateColorSunShadow(ColorChooserDialog ccd)
  {
    this.colorSunShadow = ccd.getColor();
    updateColoredButton(this.jButtonSunShadow, this.colorSunShadow);
  }

  private void updateSunPropertyButtons()
  {
    updateColoredButton(this.jButtonSunBrightness, this.colorSunBrightness);
    updateColoredButton(this.jButtonSunAmbient, this.colorSunAmbient);
    updateColoredButton(this.jButtonSunShadow, this.colorSunShadow);
  }

  private void updateColoredButton(JButton button, buildable.Color color)
  {
    if (color != null)
    {
      button.setForeground(color.getJavaAwtColorNegative());
      button.setBackground(color.getJavaAwtColor());
    }
  }

  private void initComponents()
  {
    this.jColorChooser1 = new JColorChooser();
    this.jLabel1 = new JLabel();
    this.jTextFieldScale = new JTextField();
    this.jButtonApply = new JButton();
    this.jButtonCancel = new JButton();
    this.jLabel2 = new JLabel();
    this.jTextFieldSkyTexture = new JTextField();
    this.jLabel3 = new JLabel();
    this.jButtonSunBrightness = new JButton();
    this.jButtonSunAmbient = new JButton();
    this.jButtonSunShadow = new JButton();

    setDefaultCloseOperation(2);
    setTitle("Style Editor");

    this.jLabel1.setText("Scale:");

    this.jTextFieldScale.setHorizontalAlignment(4);
    this.jTextFieldScale.setText(new Integer(this.scale).toString());
    this.jTextFieldScale.setCursor(new Cursor(2));
    this.jTextFieldScale.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        StyleEditor.this.jTextFieldScaleActionPerformed(evt);
      }
    });
    this.jButtonApply.setText("Apply");
    this.jButtonApply.addMouseListener(new MouseAdapter()
    {
      public void mousePressed(MouseEvent evt)
      {
        StyleEditor.this.jButtonApplyMousePressed(evt);
      }
    });
    this.jButtonCancel.setText("Cancel");
    this.jButtonCancel.addMouseListener(new MouseAdapter()
    {
      public void mousePressed(MouseEvent evt)
      {
        StyleEditor.this.jButtonCancelMousePressed(evt);
      }
    });
    this.jLabel2.setText("Sky-Texture:");

    this.jTextFieldSkyTexture.setText(this.skyTexture);

    this.jLabel3.setText("Sun:");

    this.jButtonSunBrightness.setForeground(this.colorSunBrightness.getJavaAwtColor());
    this.jButtonSunBrightness.setText("brightness");
    this.jButtonSunBrightness.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        StyleEditor.this.jButtonSunBrightnessActionPerformed(evt);
      }
    });
    this.jButtonSunAmbient.setText("ambient");
    this.jButtonSunAmbient.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        StyleEditor.this.jButtonSunAmbientActionPerformed(evt);
      }
    });
    this.jButtonSunShadow.setText("shadow");
    this.jButtonSunShadow.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        StyleEditor.this.jButtonSunShadowActionPerformed(evt);
      }
    });
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jTextFieldScale, -2, 62, -2)).addGroup(layout.createSequentialGroup().addComponent(this.jLabel2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jTextFieldSkyTexture, -1, 222, 32767)).addGroup(layout.createSequentialGroup().addComponent(this.jLabel3).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButtonSunBrightness, -2, 104, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButtonSunAmbient).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButtonSunShadow)).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addComponent(this.jButtonApply).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButtonCancel))).addContainerGap()));

    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel1).addComponent(this.jTextFieldScale, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel2).addComponent(this.jTextFieldSkyTexture, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel3).addComponent(this.jButtonSunBrightness, -2, 18, -2).addComponent(this.jButtonSunAmbient, -2, 18, -2).addComponent(this.jButtonSunShadow, -2, 18, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 58, 32767).addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addComponent(this.jButtonCancel, -1, -1, 32767).addComponent(this.jButtonApply, -1, 23, 32767)).addContainerGap()));

    pack();
  }

  private void jButtonApplyMousePressed(MouseEvent evt)
  {
    boolean continu = true;
    try
    {
      this.convertOptions.setScale(new Integer(this.jTextFieldScale.getText()).intValue());
      this.convertOptions.setSkyTexture(this.jTextFieldSkyTexture.getText());
      this.convertOptions.setSunLight(this.colorSunBrightness);
      this.convertOptions.setSunAmbient(this.colorSunAmbient);
      this.convertOptions.setSunShadow(this.colorSunShadow);
    }
    catch (NumberFormatException e)
    {
      continu = false;
      this.jTextFieldScale.setBackground(colorFormatIncorrect);
    }
    if (continu) {
      setVisible(false);
    }
  }

  private void jTextFieldScaleActionPerformed(ActionEvent evt) {}

  private void jButtonSunBrightnessActionPerformed(ActionEvent evt)
  {
    ColorChooserDialog ccd = new ColorChooserDialog(this.parent, "Choose a color for the brightness-value of the sun", this.colorSunBrightness);
    ccd.setVisible(true);
    udateColorSunBrightness(ccd);
  }

  private void jButtonCancelMousePressed(MouseEvent evt)
  {
    setVisible(false);
  }

  private void jButtonSunAmbientActionPerformed(ActionEvent evt)
  {
    ColorChooserDialog ccd = new ColorChooserDialog(this.parent, "Choose a color for the ambient-value of the sun", this.colorSunAmbient);
    ccd.setVisible(true);
    udateColorSunAmbient(ccd);
  }

  private void jButtonSunShadowActionPerformed(ActionEvent evt)
  {
    ColorChooserDialog ccd = new ColorChooserDialog(this.parent, "Choose a color for the shadow", this.colorSunShadow);
    ccd.enableLuminosity(false);
    ccd.setVisible(true);
    udateColorSunShadow(ccd);
  }
}
