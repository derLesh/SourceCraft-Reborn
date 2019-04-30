package gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class LuminosityChooser
        extends JPanel
{
  private int luminosity = 0;
  private JLabel jLabel1;
  private JSlider jSlider;
  private JTextField jTextFieldLuminosity;

  public LuminosityChooser()
  {
    initComponents();
  }

  public void setSlider(int luminosity)
  {
    this.luminosity = luminosity;
    double logarg = this.luminosity * 171.0D / 200000.0D + 1.0D;

    int value = (int)(Math.log(logarg) * 100.0D);
    this.jSlider.setValue(value);
    this.jTextFieldLuminosity.setText(Integer.toString(luminosity));
  }

  private void setLuminosity(int silderValue)
  {
    this.luminosity = (10 * (int)((Math.exp(silderValue / 100.0D) - 1.0D) * 100.0D * 200.0D / 171.0D));
    this.jTextFieldLuminosity.setText(Integer.toString(this.luminosity));
  }

  public int getLuminosity()
  {
    return this.luminosity;
  }

  private void initComponents()
  {
    this.jSlider = new JSlider();
    this.jTextFieldLuminosity = new JTextField();
    this.jLabel1 = new JLabel();

    this.jSlider.addChangeListener(new ChangeListener()
    {
      public void stateChanged(ChangeEvent evt)
      {
        LuminosityChooser.this.jSliderStateChanged(evt);
      }
    });
    this.jSlider.addMouseMotionListener(new MouseMotionAdapter()
    {
      public void mouseDragged(MouseEvent evt)
      {
        LuminosityChooser.this.jSliderMouseDragged(evt);
      }
    });
    this.jTextFieldLuminosity.setText(Integer.toString(this.luminosity));
    this.jTextFieldLuminosity.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent evt)
      {
        LuminosityChooser.this.jTextFieldLuminosityKeyReleased(evt);
      }
    });
    this.jLabel1.setText("Luminosity:");

    GroupLayout layout = new GroupLayout(this);
    setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jTextFieldLuminosity, -2, 60, -2).addContainerGap(257, 32767)).addComponent(this.jSlider, -1, 375, 32767));

    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel1).addComponent(this.jTextFieldLuminosity, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jSlider, -2, -1, -2).addContainerGap()));
  }

  private void jSliderMouseDragged(MouseEvent evt)
  {
    setLuminosity(this.jSlider.getValue());
  }

  private void jTextFieldLuminosityKeyReleased(KeyEvent evt)
  {
    update();
  }

  private void jSliderStateChanged(ChangeEvent evt)
  {
    setLuminosity(this.jSlider.getValue());
  }

  private void update()
  {
    int value = 0;
    try
    {
      value = new Integer(this.jTextFieldLuminosity.getText()).intValue();
      setSlider(value);
    }
    catch (NumberFormatException e) {}
  }
}
