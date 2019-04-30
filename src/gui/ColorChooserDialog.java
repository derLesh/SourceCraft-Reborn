package gui;

import buildable.Color;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ColorChooserDialog
        extends JDialog
{
  private Color result;
  private JButton jButtonApply;
  private JButton jButtonCancel;
  private JColorChooser jColorChooser1;
  private JLabel jLabel1;
  private LuminosityChooser luminosityChooser1;

  public ColorChooserDialog(Frame parent, String title, Color color)
  {
    super(parent, true);
    setLocation(parent.getLocation().x, parent.getLocation().y);
    setTitle(title);
    this.result = color;
    initComponents();
    this.luminosityChooser1.setSlider(color.getAlpha());
    this.jColorChooser1.setColor(color.getJavaAwtColor());
  }

  public void enableLuminosity(boolean enabled)
  {
    this.luminosityChooser1.setVisible(enabled);
  }

  private void updateColor()
  {
    this.result = new Color(this.jColorChooser1.getColor());
    this.result.setAlpha(this.luminosityChooser1.getLuminosity());
  }

  public Color getColor()
  {
    return this.result;
  }

  private void initComponents()
  {
    this.jColorChooser1 = new JColorChooser();
    this.jButtonCancel = new JButton();
    this.jButtonApply = new JButton();
    this.luminosityChooser1 = new LuminosityChooser();
    this.jLabel1 = new JLabel();

    setDefaultCloseOperation(2);

    this.jButtonCancel.setText("Cancel");
    this.jButtonCancel.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        ColorChooserDialog.this.jButtonCancelActionPerformed(evt);
      }
    });
    this.jButtonApply.setText("Apply");
    this.jButtonApply.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        ColorChooserDialog.this.jButtonApplyActionPerformed(evt);
      }
    });
    this.jLabel1.setText("Color:");

    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addComponent(this.jButtonApply).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButtonCancel)).addComponent(this.jColorChooser1, -2, 595, -2).addComponent(this.luminosityChooser1, -1, 595, 32767).addComponent(this.jLabel1)).addContainerGap()));

    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap().addComponent(this.luminosityChooser1, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 27, 32767).addComponent(this.jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jColorChooser1, -2, 316, -2).addGap(54, 54, 54).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButtonCancel).addComponent(this.jButtonApply)).addContainerGap()));

    pack();
  }

  private void jButtonApplyActionPerformed(ActionEvent evt)
  {
    updateColor();
    setVisible(false);
  }

  private void jButtonCancelActionPerformed(ActionEvent evt)
  {
    setVisible(false);
  }
}