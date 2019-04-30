package gui;

import config.Config;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JTextField;

public class PathAsker extends javax.swing.JDialog
{
  private static final String windowTitle = "Enter Paths";
  private static final java.awt.Color colorDirectoryFound = java.awt.Color.GREEN;
  private static final java.awt.Color colorDirectoryMissing = java.awt.Color.PINK;
  
  private GUI parent;
  
  private Config config;
  
  private String minecraftPath;
  private String steamPath;
  private javax.swing.ComboBoxModel cbmSteamUser;
  private boolean minecraftCorrect = false;
  private boolean steamCorrect = false;
  private JButton jButtonApply;
  private JButton jButtonMinecraftPath;
  
  public PathAsker(GUI parent, Config config) { super(parent, true);
    this.parent = parent;
    this.config = config;
    
    minecraftPath = config.getMinecraftPathString();
    steamPath = config.getSteamPathString();
    if ((minecraftPath == null) || (!new java.io.File(minecraftPath).exists())) {
      minecraftPath = ("C:\\Users\\" + System.getProperty("user.name") + "\\AppData\\Roaming\\.minecraft\\");
      config.setMinecraftPath(minecraftPath);
    }
    String[] dummy = { " " };
    cbmSteamUser = new javax.swing.DefaultComboBoxModel();
    initComponents();
    java.awt.Dimension d = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    int x = (width - getSizewidth) / 2;
    int y = (height - getSizeheight) / 2;
    setLocation(x, y);
    
    update();
  }
  
  private void update() {
    updateMinecraftPath();
    updateSteamPath();
  }
  
  private void updateButton() {
    jButtonApply.setEnabled((steamCorrect) && (minecraftCorrect));
  }
  
  public void updateMinecraftPath() {
    minecraftPath = jTextFieldMinecraftPath.getText();
    config.setMinecraftPath(minecraftPath);
    if (config.verifyMinecraftDirectory()) {
      jTextFieldMinecraftPath.setBackground(colorDirectoryFound);
      minecraftCorrect = true;
    }
    else {
      jTextFieldMinecraftPath.setBackground(colorDirectoryMissing);
      minecraftCorrect = false;
    }
    updateButton();
  }
  
  public void updateSteamPath() {
    steamPath = jTextFieldSteamPath.getText();
    config.setSteamPath(steamPath);
    if (config.verifySteamDirectory()) {
      jTextFieldSteamPath.setBackground(colorDirectoryFound);
      jLabelSteamUserName.setEnabled(true);
      jComboBoxSteamUser.setEnabled(true);
      jComboBoxSteamUser.setModel(new javax.swing.DefaultComboBoxModel(config.getSteamUsers()));
      jComboBoxSteamUser.setSelectedItem(config.getMostLikelySteamUser());
      steamCorrect = true;
    }
    else {
      jTextFieldSteamPath.setBackground(colorDirectoryMissing);
      jLabelSteamUserName.setEnabled(false);
      jComboBoxSteamUser.setEnabled(false);
      steamCorrect = false;
    }
    updateButton();
  }
  
  public void changePath(JTextField field) {
    String current = field.getText();
    java.io.File fileFolder = new java.io.File(current);
    javax.swing.JFileChooser fileChooser = new javax.swing.JFileChooser();
    fileChooser.setFileSelectionMode(1);
    
    if (fileFolder.exists()) {
      fileChooser.setCurrentDirectory(fileFolder);
    }
    int state = fileChooser.showOpenDialog(null);
    if ((state == 0) && 
      (fileChooser.getSelectedFile() != null)) {
      current = fileChooser.getSelectedFile().toString();
    }
    
    field.setText(current);
  }
  
  private JButton jButtonSteamPath;
  private javax.swing.JComboBox jComboBoxSteamUser;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabelSteamUserName;
  private JTextField jTextFieldMinecraftPath;
  private JTextField jTextFieldSteamPath;
  private void initComponents()
  {
    jLabel1 = new javax.swing.JLabel();
    jTextFieldMinecraftPath = new JTextField();
    jButtonMinecraftPath = new JButton();
    jLabel2 = new javax.swing.JLabel();
    jTextFieldSteamPath = new JTextField();
    jButtonSteamPath = new JButton();
    jButtonApply = new JButton();
    jComboBoxSteamUser = new javax.swing.JComboBox();
    jLabelSteamUserName = new javax.swing.JLabel();
    
    setDefaultCloseOperation(2);
    setTitle("Enter Paths");
    
    jLabel1.setText("Select your Minecraft-path");
    
    jTextFieldMinecraftPath.setText(minecraftPath);
    jTextFieldMinecraftPath.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyReleased(java.awt.event.KeyEvent evt) {
        PathAsker.this.jTextFieldMinecraftPathKeyReleased(evt);
      }
      
    });
    jButtonMinecraftPath.setText("...");
    jButtonMinecraftPath.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        PathAsker.this.jButtonMinecraftPathActionPerformed(evt);
      }
      
    });
    jLabel2.setText("Select your Steam-path");
    
    jTextFieldSteamPath.setText(steamPath);
    jTextFieldSteamPath.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyReleased(java.awt.event.KeyEvent evt) {
        PathAsker.this.jTextFieldSteamPathKeyReleased(evt);
      }
      
    });
    jButtonSteamPath.setText("...");
    jButtonSteamPath.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        PathAsker.this.jButtonSteamPathActionPerformed(evt);
      }
      
    });
    jButtonApply.setText("Apply");
    jButtonApply.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        PathAsker.this.jButtonApplyActionPerformed(evt);
      }
      
    });
    jComboBoxSteamUser.setModel(cbmSteamUser);
    
    jLabelSteamUserName.setText("Steam user-name");
    
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addComponent(jTextFieldMinecraftPath, -1, 329, 32767).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jButtonMinecraftPath)).addComponent(jLabel1).addComponent(jLabel2).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING).addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup().addGap(10, 10, 10).addComponent(jLabelSteamUserName).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jComboBoxSteamUser, 0, 231, 32767)).addComponent(jTextFieldSteamPath, -1, 329, 32767)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jButtonSteamPath)))).addGroup(layout.createSequentialGroup().addGap(162, 162, 162).addComponent(jButtonApply))).addContainerGap()));
    


























    layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jLabel1).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jTextFieldMinecraftPath, -2, -1, -2).addComponent(jButtonMinecraftPath)).addGap(18, 18, 18).addComponent(jLabel2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jTextFieldSteamPath, -2, -1, -2).addComponent(jButtonSteamPath)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jComboBoxSteamUser, -2, -1, -2).addComponent(jLabelSteamUserName)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, 32767).addComponent(jButtonApply).addContainerGap()));
    






















    pack();
  }
  
  private void jButtonMinecraftPathActionPerformed(ActionEvent evt) {
    changePath(jTextFieldMinecraftPath);
    updateMinecraftPath();
  }
  
  private void jButtonSteamPathActionPerformed(ActionEvent evt) {
    changePath(jTextFieldSteamPath);
    updateSteamPath();
  }
  
  private void jButtonApplyActionPerformed(ActionEvent evt) {
    minecraftPath = jTextFieldMinecraftPath.getText();
    config.setMinecraftPath(minecraftPath);
    steamPath = jTextFieldSteamPath.getText();
    config.setSteamPath(steamPath);
    config.setSteamUserName((String)jComboBoxSteamUser.getSelectedItem());
    parent.writeConfig();
    setVisible(false);
  }
  
  private void jTextFieldMinecraftPathKeyReleased(java.awt.event.KeyEvent evt) {
    updateMinecraftPath();
  }
  
  private void jTextFieldSteamPathKeyReleased(java.awt.event.KeyEvent evt) {
    updateSteamPath();
  }
  






  public static void main(String[] args)
  {
    try
    {
      for (javax.swing.UIManager.LookAndFeelInfo info : ) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger.getLogger(PathAsker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(PathAsker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(PathAsker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(PathAsker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    


    java.awt.EventQueue.invokeLater(new Runnable()
    {
      public void run() {
        new PathAsker(null, null).setVisible(true);
      }
    });
  }
}
