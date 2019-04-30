package gui;

import config.Place;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JTextField;

public class PlaceCreator extends javax.swing.JDialog
{
  private static final String windowTitle = "Place Editor";
  private static final java.awt.Color colorFormatIncorrect = java.awt.Color.PINK;
  private static final java.awt.Color colorFormatWarning = java.awt.Color.LIGHT_GRAY;
  private static final java.awt.Color colorFormatCorrect = java.awt.Color.WHITE;
  private GUI parent;
  private Place place;
  private config.Config config;
  private boolean warnIfOverwrite;
  private String[] worldOptions;
  private javax.swing.DefaultComboBoxModel cmbWorldOptions;
  private String placeName;
  private int xPos = 0;
  private int zPos = 0;
  private int yMin = 55;
  private int yMax = 120;
  private int numberX = 80;
  private int numberZ = 80;
  private javax.swing.JButton jButton1;
  private javax.swing.JButton jButtonCancel;
  
  public PlaceCreator(GUI parent, config.Config config, String placeName) {
    super(parent, true);
    place = null;
    
    this.parent = parent;
    warnIfOverwrite = true;
    this.config = config;
    this.placeName = placeName;
    worldOptions = config.getPossibleWorlds();
    cmbWorldOptions = new javax.swing.DefaultComboBoxModel(worldOptions);
    initComponents();
    jButtonDelete.setVisible(false);
  }
  
  public PlaceCreator(GUI parent, config.Config config, Place place)
  {
    super(parent, true);
    this.place = place;
    
    this.parent = parent;
    warnIfOverwrite = true;
    this.config = config;
    worldOptions = config.getPossibleWorlds();
    cmbWorldOptions = new javax.swing.DefaultComboBoxModel(worldOptions);
    xPos = place.getX();
    zPos = place.getZ();
    yMin = place.getYStart();
    yMax = place.getYEnd();
    numberX = place.getNumX();
    numberZ = place.getNumZ();
    placeName = place.getName();
    this.config.deletePlace(place);
    initComponents();
    

    jComboBoxWorld.setSelectedItem(place.getWorld());
  }
  
  private boolean askToOverwrite() {
    Object[] options = { "Yes", "No" };
    int n = javax.swing.JOptionPane.showOptionDialog(null, "A place called \"" + jTextFieldName.getText() + "\" alread exists. Do you want to overwrte?", "Place already exists", 1, -1, null, options, options[1]);
    






    if (n == 0) {
      return true;
    }
    return false;
  }
  
  private void createNewPlace() {
    place = new Place(jTextFieldName.getText());
    place.setX(new Integer(jTextFieldXPos.getText()).intValue());
    place.setZ(new Integer(jTextFieldZPos.getText()).intValue());
    place.setYStart(new Integer(jTextFieldYMin.getText()).intValue());
    place.setYEnd(new Integer(jTextFieldYMax.getText()).intValue());
    place.setNumX(((Integer)jSpinnerNumX.getValue()).intValue());
    place.setNumZ(((Integer)jSpinnerNumZ.getValue()).intValue());
    place.setWorld((String)jComboBoxWorld.getSelectedItem());
    config.addPlace(place);
  }
  
  private void writeOldPlace() {
    if (place != null) {
      config.addPlace(place);
    }
  }
  
  private String makeName(String name) {
    if (name.equals("")) {
      name = "unnamed";
      int index = 2;
      while (config.getPlace(name) != null) {
        name = "unnamed(" + index + ")";
        index++;
      }
    }
    return name;
  }
  
  private Place getConflictPlace(String placeName) {
    Place conflictPlace = config.getPlace(placeName);
    if (place != null) {
      if (place.getName().equals(placeName)) {
        return null;
      }
      
      if (conflictPlace == place) {
        return conflictPlace;
      }
      return null;
    }
    

    return conflictPlace;
  }
  

  private javax.swing.JButton jButtonDelete;
  
  private javax.swing.JButton jButtonSave;
  private javax.swing.JComboBox jComboBoxWorld;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private void initComponents()
  {
    jTextField3 = new JTextField();
    jTextFieldName = new JTextField();
    jLabel1 = new javax.swing.JLabel();
    jTextFieldZPos = new JTextField();
    jTextFieldXPos = new JTextField();
    jLabel2 = new javax.swing.JLabel();
    jTextFieldYMax = new JTextField();
    jTextFieldYMin = new JTextField();
    jLabel3 = new javax.swing.JLabel();
    jSpinnerNumZ = new javax.swing.JSpinner();
    jSpinnerNumX = new javax.swing.JSpinner();
    jButtonSave = new javax.swing.JButton();
    jButtonCancel = new javax.swing.JButton();
    jComboBoxWorld = new javax.swing.JComboBox();
    jButton1 = new javax.swing.JButton();
    jButtonDelete = new javax.swing.JButton();
    
    jTextField3.setText("jTextField3");
    
    setDefaultCloseOperation(2);
    setTitle("Place Editor");
    
    jTextFieldName.setText(placeName);
    
    jLabel1.setText("(X,Z)-Position");
    
    jTextFieldZPos.setText(new Integer(zPos).toString());
    jTextFieldZPos.setMinimumSize(new java.awt.Dimension(72, 20));
    
    jTextFieldXPos.setText(new Integer(xPos).toString());
    jTextFieldXPos.setCursor(new java.awt.Cursor(2));
    jTextFieldXPos.setMinimumSize(new java.awt.Dimension(72, 20));
    
    jLabel2.setText("(Ymin,Ymax)-Height");
    
    jTextFieldYMax.setText(new Integer(yMax).toString());
    
    jTextFieldYMin.setText(new Integer(yMin).toString());
    
    jLabel3.setText("(X,Z)-Size");
    
    jSpinnerNumZ.setValue(Integer.valueOf(numberZ));
    
    jSpinnerNumX.setValue(Integer.valueOf(numberX));
    
    jButtonSave.setText("Save");
    jButtonSave.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        PlaceCreator.this.jButtonSaveActionPerformed(evt);
      }
      
    });
    jButtonCancel.setText("Cancel");
    jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        PlaceCreator.this.jButtonCancelActionPerformed(evt);
      }
      
    });
    jComboBoxWorld.setMaximumRowCount(16);
    jComboBoxWorld.setModel(cmbWorldOptions);
    jComboBoxWorld.setSelectedItem(worldOptions[0]);
    jComboBoxWorld.setAutoscrolls(true);
    jComboBoxWorld.setMaximumSize(new java.awt.Dimension(28, 20));
    
    jButton1.setText("...");
    jButton1.setEnabled(false);
    
    jButtonDelete.setText("Delete");
    jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        PlaceCreator.this.jButtonDeleteActionPerformed(evt);
      }
      
    });
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addComponent(jTextFieldName, javax.swing.GroupLayout.Alignment.LEADING, -1, 338, 32767).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel1).addComponent(jLabel3).addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, -1, 116, 32767)).addGap(71, 71, 71).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(jSpinnerNumX).addComponent(jTextFieldYMin).addComponent(jTextFieldXPos, -2, 65, 32767)).addGap(7, 7, 7).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false).addComponent(jTextFieldYMax, javax.swing.GroupLayout.Alignment.LEADING).addComponent(jTextFieldZPos, javax.swing.GroupLayout.Alignment.LEADING, -1, -1, 32767).addComponent(jSpinnerNumZ, -1, 72, 32767))).addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup().addComponent(jComboBoxWorld, 0, 305, 32767).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jButton1, -2, 27, -2)).addGroup(layout.createSequentialGroup().addComponent(jButtonDelete).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jButtonSave).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jButtonCancel))).addContainerGap()));
    































    layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jTextFieldName, -2, -1, -2).addGap(9, 9, 9).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jComboBoxWorld, -2, -1, -2).addComponent(jButton1)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jTextFieldZPos, -2, -1, -2).addComponent(jTextFieldXPos, -2, -1, -2).addComponent(jLabel1)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel2).addComponent(jTextFieldYMax, -2, -1, -2).addComponent(jTextFieldYMin, -2, -1, -2)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false).addComponent(jSpinnerNumZ, -2, -1, -2).addComponent(jSpinnerNumX, -2, -1, -2).addComponent(jLabel3)).addGap(13, 13, 13).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jButtonCancel).addComponent(jButtonSave).addComponent(jButtonDelete)).addContainerGap()));
    






























    pack();
  }
  
  private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {
    placeName = jTextFieldName.getText();
    if (fieldsOk()) {
      createNewPlace();
      parent.setDefaultPlaceNew(place.getName());
      setVisible(false);
    }
  }
  
  private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {
    if (place != null) {
      writeOldPlace();
      parent.setDefaultPlaceNew(place.getName());
    }
    setVisible(false);
  }
  
  private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {
    config.deletePlace(place);
    parent.setDefaultPlaceNew(null);
    setVisible(false);
  }
  


  private javax.swing.JSpinner jSpinnerNumX;
  
  private javax.swing.JSpinner jSpinnerNumZ;
  
  private JTextField jTextField3;
  
  private JTextField jTextFieldName;
  
  private JTextField jTextFieldXPos;
  
  private JTextField jTextFieldYMax;
  
  private JTextField jTextFieldYMin;
  
  private JTextField jTextFieldZPos;
  
  private boolean fieldsOk()
  {
    FieldCheck fc = new FieldCheck();
    fc.checkName(this);
    fc.checkIntegerField(jTextFieldXPos);
    fc.checkIntegerField(jTextFieldZPos);
    fc.checkIntegerField(jTextFieldYMin);
    fc.checkIntegerField(jTextFieldYMax);
    fc.checkIntegerSpinner(jSpinnerNumX);
    fc.checkIntegerSpinner(jSpinnerNumZ);
    return fc.fieldsOk();
  }
  
  private class FieldCheck
  {
    private boolean result;
    
    public FieldCheck() {
      result = true;
    }
    
    public boolean fieldsOk() {
      return result;
    }
    
    public void checkName(PlaceCreator pc) {
      JTextField field = jTextFieldName;
      String text = field.getText();
      if (config.getPlace(text) != null) {
        result = false;
        field.setBackground(PlaceCreator.colorFormatIncorrect);

      }
      else if (text.equals("")) {
        result = false;
        field.setText(pc.makeName(text));
        field.setBackground(PlaceCreator.colorFormatWarning);
      }
      else {
        field.setBackground(PlaceCreator.colorFormatCorrect);
      }
    }
    

    public void checkIntegerField(JTextField field)
    {
      boolean continu = true;
      try {
        new Integer(field.getText());
      }
      catch (NumberFormatException e) {
        result = false;
        continu = false;
        field.setBackground(PlaceCreator.colorFormatIncorrect);
      }
      if (continu) {
        field.setBackground(PlaceCreator.colorFormatCorrect);
      }
    }
    
    public void checkIntegerSpinner(javax.swing.JSpinner spinner)
    {
      boolean continu = true;
      try {
        new Integer(((Integer)spinner.getValue()).intValue());
      }
      catch (ClassCastException e) {
        result = false;
        continu = false;
        spinner.setBackground(PlaceCreator.colorFormatIncorrect);
      }
      if (continu) {
        spinner.setBackground(PlaceCreator.colorFormatCorrect);
      }
    }
  }
}
