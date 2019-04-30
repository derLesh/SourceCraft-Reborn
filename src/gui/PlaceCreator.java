package gui;

import config.Config;
import config.Place;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.LayoutStyle.ComponentPlacement;

public class PlaceCreator
        extends JDialog
{
  private static final String windowTitle = "Place Editor";
  private static final Color colorFormatIncorrect = Color.PINK;
  private static final Color colorFormatWarning = Color.LIGHT_GRAY;
  private static final Color colorFormatCorrect = Color.WHITE;
  private GUI parent;
  private Place place;
  private Config config;
  private boolean warnIfOverwrite;
  private String[] worldOptions;
  private DefaultComboBoxModel cmbWorldOptions;
  private String placeName;
  private int xPos = 0;
  private int zPos = 0;
  private int yMin = 55;
  private int yMax = 120;
  private int numberX = 80;
  private int numberZ = 80;
  private JButton jButton1;
  private JButton jButtonCancel;
  private JButton jButtonDelete;
  private JButton jButtonSave;
  private JComboBox jComboBoxWorld;
  private JLabel jLabel1;
  private JLabel jLabel2;
  private JLabel jLabel3;
  private JSpinner jSpinnerNumX;
  private JSpinner jSpinnerNumZ;
  private JTextField jTextField3;
  private JTextField jTextFieldName;
  private JTextField jTextFieldXPos;
  private JTextField jTextFieldYMax;
  private JTextField jTextFieldYMin;
  private JTextField jTextFieldZPos;

  public PlaceCreator(GUI parent, Config config, String placeName)
  {
    super(parent, true);
    this.place = null;

    this.parent = parent;
    this.warnIfOverwrite = true;
    this.config = config;
    this.placeName = placeName;
    this.worldOptions = config.getPossibleWorlds();
    this.cmbWorldOptions = new DefaultComboBoxModel(this.worldOptions);
    initComponents();
    this.jButtonDelete.setVisible(false);
  }

  public PlaceCreator(GUI parent, Config config, Place place)
  {
    super(parent, true);
    this.place = place;

    this.parent = parent;
    this.warnIfOverwrite = true;
    this.config = config;
    this.worldOptions = config.getPossibleWorlds();
    this.cmbWorldOptions = new DefaultComboBoxModel(this.worldOptions);
    this.xPos = place.getX();
    this.zPos = place.getZ();
    this.yMin = place.getYStart();
    this.yMax = place.getYEnd();
    this.numberX = place.getNumX();
    this.numberZ = place.getNumZ();
    this.placeName = place.getName();
    this.config.deletePlace(place);
    initComponents();

    this.jComboBoxWorld.setSelectedItem(place.getWorld());
  }

  private boolean askToOverwrite()
  {
    Object[] options = { "Yes", "No" };
    int n = JOptionPane.showOptionDialog(null, "A place called \"" + this.jTextFieldName.getText() + "\" alread exists. Do you want to overwrte?", "Place already exists", 1, -1, null, options, options[1]);
    if (n == 0) {
      return true;
    }
    return false;
  }

  private void createNewPlace()
  {
    this.place = new Place(this.jTextFieldName.getText());
    this.place.setX(new Integer(this.jTextFieldXPos.getText()).intValue());
    this.place.setZ(new Integer(this.jTextFieldZPos.getText()).intValue());
    this.place.setYStart(new Integer(this.jTextFieldYMin.getText()).intValue());
    this.place.setYEnd(new Integer(this.jTextFieldYMax.getText()).intValue());
    this.place.setNumX(((Integer)this.jSpinnerNumX.getValue()).intValue());
    this.place.setNumZ(((Integer)this.jSpinnerNumZ.getValue()).intValue());
    this.place.setWorld((String)this.jComboBoxWorld.getSelectedItem());
    this.config.addPlace(this.place);
  }

  private void writeOldPlace()
  {
    if (this.place != null) {
      this.config.addPlace(this.place);
    }
  }

  private String makeName(String name)
  {
    if (name.equals(""))
    {
      name = "unnamed";
      int index = 2;
      while (this.config.getPlace(name) != null)
      {
        name = "unnamed(" + index + ")";
        index++;
      }
    }
    return name;
  }

  private Place getConflictPlace(String placeName)
  {
    Place conflictPlace = this.config.getPlace(placeName);
    if (this.place != null)
    {
      if (this.place.getName().equals(placeName)) {
        return null;
      }
      if (conflictPlace == this.place) {
        return conflictPlace;
      }
      return null;
    }
    return conflictPlace;
  }

  private void initComponents()
  {
    this.jTextField3 = new JTextField();
    this.jTextFieldName = new JTextField();
    this.jLabel1 = new JLabel();
    this.jTextFieldZPos = new JTextField();
    this.jTextFieldXPos = new JTextField();
    this.jLabel2 = new JLabel();
    this.jTextFieldYMax = new JTextField();
    this.jTextFieldYMin = new JTextField();
    this.jLabel3 = new JLabel();
    this.jSpinnerNumZ = new JSpinner();
    this.jSpinnerNumX = new JSpinner();
    this.jButtonSave = new JButton();
    this.jButtonCancel = new JButton();
    this.jComboBoxWorld = new JComboBox();
    this.jButton1 = new JButton();
    this.jButtonDelete = new JButton();

    this.jTextField3.setText("jTextField3");

    setDefaultCloseOperation(2);
    setTitle("Place Editor");

    this.jTextFieldName.setText(this.placeName);

    this.jLabel1.setText("(X,Z)-Position");

    this.jTextFieldZPos.setText(new Integer(this.zPos).toString());
    this.jTextFieldZPos.setMinimumSize(new Dimension(72, 20));

    this.jTextFieldXPos.setText(new Integer(this.xPos).toString());
    this.jTextFieldXPos.setCursor(new Cursor(2));
    this.jTextFieldXPos.setMinimumSize(new Dimension(72, 20));

    this.jLabel2.setText("(Ymin,Ymax)-Height");

    this.jTextFieldYMax.setText(new Integer(this.yMax).toString());

    this.jTextFieldYMin.setText(new Integer(this.yMin).toString());

    this.jLabel3.setText("(X,Z)-Size");

    this.jSpinnerNumZ.setValue(Integer.valueOf(this.numberZ));

    this.jSpinnerNumX.setValue(Integer.valueOf(this.numberX));

    this.jButtonSave.setText("Save");
    this.jButtonSave.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        PlaceCreator.this.jButtonSaveActionPerformed(evt);
      }
    });
    this.jButtonCancel.setText("Cancel");
    this.jButtonCancel.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        PlaceCreator.this.jButtonCancelActionPerformed(evt);
      }
    });
    this.jComboBoxWorld.setMaximumRowCount(16);
    this.jComboBoxWorld.setModel(this.cmbWorldOptions);
    this.jComboBoxWorld.setSelectedItem(this.worldOptions[0]);
    this.jComboBoxWorld.setAutoscrolls(true);
    this.jComboBoxWorld.setMaximumSize(new Dimension(28, 20));

    this.jButton1.setText("...");
    this.jButton1.setEnabled(false);

    this.jButtonDelete.setText("Delete");
    this.jButtonDelete.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        PlaceCreator.this.jButtonDeleteActionPerformed(evt);
      }
    });
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.jTextFieldName, GroupLayout.Alignment.LEADING, -1, 338, 32767).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel1).addComponent(this.jLabel3).addComponent(this.jLabel2, GroupLayout.Alignment.TRAILING, -1, 116, 32767)).addGap(71, 71, 71).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.jSpinnerNumX).addComponent(this.jTextFieldYMin).addComponent(this.jTextFieldXPos, -2, 65, 32767)).addGap(7, 7, 7).addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addComponent(this.jTextFieldYMax, GroupLayout.Alignment.LEADING).addComponent(this.jTextFieldZPos, GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(this.jSpinnerNumZ, -1, 72, 32767))).addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup().addComponent(this.jComboBoxWorld, 0, 305, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton1, -2, 27, -2)).addGroup(layout.createSequentialGroup().addComponent(this.jButtonDelete).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButtonSave).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButtonCancel))).addContainerGap()));

    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jTextFieldName, -2, -1, -2).addGap(9, 9, 9).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jComboBoxWorld, -2, -1, -2).addComponent(this.jButton1)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jTextFieldZPos, -2, -1, -2).addComponent(this.jTextFieldXPos, -2, -1, -2).addComponent(this.jLabel1)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel2).addComponent(this.jTextFieldYMax, -2, -1, -2).addComponent(this.jTextFieldYMin, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE, false).addComponent(this.jSpinnerNumZ, -2, -1, -2).addComponent(this.jSpinnerNumX, -2, -1, -2).addComponent(this.jLabel3)).addGap(13, 13, 13).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButtonCancel).addComponent(this.jButtonSave).addComponent(this.jButtonDelete)).addContainerGap()));

    pack();
  }

  private void jButtonSaveActionPerformed(ActionEvent evt)
  {
    this.placeName = this.jTextFieldName.getText();
    if (fieldsOk())
    {
      createNewPlace();
      this.parent.setDefaultPlaceNew(this.place.getName());
      setVisible(false);
    }
  }

  private void jButtonCancelActionPerformed(ActionEvent evt)
  {
    if (this.place != null)
    {
      writeOldPlace();
      this.parent.setDefaultPlaceNew(this.place.getName());
    }
    setVisible(false);
  }

  private void jButtonDeleteActionPerformed(ActionEvent evt)
  {
    this.config.deletePlace(this.place);
    this.parent.setDefaultPlaceNew(null);
    setVisible(false);
  }

  private boolean fieldsOk()
  {
    FieldCheck fc = new FieldCheck();
    fc.checkName(this);
    fc.checkIntegerField(this.jTextFieldXPos);
    fc.checkIntegerField(this.jTextFieldZPos);
    fc.checkIntegerField(this.jTextFieldYMin);
    fc.checkIntegerField(this.jTextFieldYMax);
    fc.checkIntegerSpinner(this.jSpinnerNumX);
    fc.checkIntegerSpinner(this.jSpinnerNumZ);
    return fc.fieldsOk();
  }

  private class FieldCheck
  {
    private boolean result;

    public FieldCheck()
    {
      this.result = true;
    }

    public boolean fieldsOk()
    {
      return this.result;
    }

    public void checkName(PlaceCreator pc)
    {
      JTextField field = PlaceCreator.this.jTextFieldName;
      String text = field.getText();
      if (PlaceCreator.this.config.getPlace(text) != null)
      {
        this.result = false;
        field.setBackground(PlaceCreator.colorFormatIncorrect);
      }
      else if (text.equals(""))
      {
        this.result = false;
        field.setText(pc.makeName(text));
        field.setBackground(PlaceCreator.colorFormatWarning);
      }
      else
      {
        field.setBackground(PlaceCreator.colorFormatCorrect);
      }
    }

    public void checkIntegerField(JTextField field)
    {
      boolean continu = true;
      try
      {
        new Integer(field.getText());
      }
      catch (NumberFormatException e)
      {
        this.result = false;
        continu = false;
        field.setBackground(PlaceCreator.colorFormatIncorrect);
      }
      if (continu) {
        field.setBackground(PlaceCreator.colorFormatCorrect);
      }
    }

    public void checkIntegerSpinner(JSpinner spinner)
    {
      boolean continu = true;
      try
      {
        new Integer(((Integer)spinner.getValue()).intValue());
      }
      catch (ClassCastException e)
      {
        this.result = false;
        continu = false;
        spinner.setBackground(PlaceCreator.colorFormatIncorrect);
      }
      if (continu) {
        spinner.setBackground(PlaceCreator.colorFormatCorrect);
      }
    }
  }
}
