package gui;

import config.Config;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager.LookAndFeelInfo;

public class PathAsker
        extends JDialog
{
  private static final String windowTitle = "Enter Paths";
  private static final Color colorDirectoryFound = Color.GREEN;
  private static final Color colorDirectoryMissing = Color.PINK;
  private GUI parent;
  private Config config;
  private String minecraftPath;
  private String steamPath;
  private ComboBoxModel cbmSteamUser;
  private boolean minecraftCorrect = false;
  private boolean steamCorrect = false;
  private JButton jButtonApply;
  private JButton jButtonMinecraftPath;
  private JButton jButtonSteamPath;
  private JComboBox jComboBoxSteamUser;
  private JLabel jLabel1;
  private JLabel jLabel2;
  private JLabel jLabelSteamUserName;
  private JTextField jTextFieldMinecraftPath;
  private JTextField jTextFieldSteamPath;

  public PathAsker(GUI parent, Config config)
  {
    super(parent, true);
    this.parent = parent;
    this.config = config;

    this.minecraftPath = config.getMinecraftPathString();
    this.steamPath = config.getSteamPathString();
    if ((this.minecraftPath == null) || (!new File(this.minecraftPath).exists()))
    {
      this.minecraftPath = ("C:\\Users\\" + System.getProperty("user.name") + "\\AppData\\Roaming\\.minecraft\\");
      config.setMinecraftPath(this.minecraftPath);
    }
    String[] dummy = { " " };
    this.cbmSteamUser = new DefaultComboBoxModel();
    initComponents();
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);

    update();
  }

  private void update()
  {
    updateMinecraftPath();
    updateSteamPath();
  }

  private void updateButton()
  {
    this.jButtonApply.setEnabled((this.steamCorrect) && (this.minecraftCorrect));
  }

  public void updateMinecraftPath()
  {
    this.minecraftPath = this.jTextFieldMinecraftPath.getText();
    this.config.setMinecraftPath(this.minecraftPath);
    if (this.config.verifyMinecraftDirectory())
    {
      this.jTextFieldMinecraftPath.setBackground(colorDirectoryFound);
      this.minecraftCorrect = true;
    }
    else
    {
      this.jTextFieldMinecraftPath.setBackground(colorDirectoryMissing);
      this.minecraftCorrect = false;
    }
    updateButton();
  }

  public void updateSteamPath()
  {
    this.steamPath = this.jTextFieldSteamPath.getText();
    this.config.setSteamPath(this.steamPath);
    if (this.config.verifySteamDirectory())
    {
      this.jTextFieldSteamPath.setBackground(colorDirectoryFound);
      this.jLabelSteamUserName.setEnabled(true);
      this.jComboBoxSteamUser.setEnabled(true);
      this.jComboBoxSteamUser.setModel(new DefaultComboBoxModel(this.config.getSteamUsers()));
      this.jComboBoxSteamUser.setSelectedItem(this.config.getMostLikelySteamUser());
      this.steamCorrect = true;
    }
    else
    {
      this.jTextFieldSteamPath.setBackground(colorDirectoryMissing);
      this.jLabelSteamUserName.setEnabled(false);
      this.jComboBoxSteamUser.setEnabled(false);
      this.steamCorrect = false;
    }
    updateButton();
  }

  public void changePath(JTextField field)
  {
    String current = field.getText();
    File fileFolder = new File(current);
    JFileChooser fileChooser = new JFileChooser();
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

  private void initComponents()
  {
    this.jLabel1 = new JLabel();
    this.jTextFieldMinecraftPath = new JTextField();
    this.jButtonMinecraftPath = new JButton();
    this.jLabel2 = new JLabel();
    this.jTextFieldSteamPath = new JTextField();
    this.jButtonSteamPath = new JButton();
    this.jButtonApply = new JButton();
    this.jComboBoxSteamUser = new JComboBox();
    this.jLabelSteamUserName = new JLabel();

    setDefaultCloseOperation(2);
    setTitle("Enter Paths");

    this.jLabel1.setText("Select your Minecraft-path");

    this.jTextFieldMinecraftPath.setText(this.minecraftPath);
    this.jTextFieldMinecraftPath.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent evt)
      {
        PathAsker.this.jTextFieldMinecraftPathKeyReleased(evt);
      }
    });
    this.jButtonMinecraftPath.setText("...");
    this.jButtonMinecraftPath.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        PathAsker.this.jButtonMinecraftPathActionPerformed(evt);
      }
    });
    this.jLabel2.setText("Select your Steam-path");

    this.jTextFieldSteamPath.setText(this.steamPath);
    this.jTextFieldSteamPath.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent evt)
      {
        PathAsker.this.jTextFieldSteamPathKeyReleased(evt);
      }
    });
    this.jButtonSteamPath.setText("...");
    this.jButtonSteamPath.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        PathAsker.this.jButtonSteamPathActionPerformed(evt);
      }
    });
    this.jButtonApply.setText("Apply");
    this.jButtonApply.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        PathAsker.this.jButtonApplyActionPerformed(evt);
      }
    });
    this.jComboBoxSteamUser.setModel(this.cbmSteamUser);

    this.jLabelSteamUserName.setText("Steam user-name");

    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addComponent(this.jTextFieldMinecraftPath, -1, 329, 32767).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButtonMinecraftPath)).addComponent(this.jLabel1).addComponent(this.jLabel2).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup().addGap(10, 10, 10).addComponent(this.jLabelSteamUserName).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jComboBoxSteamUser, 0, 231, 32767)).addComponent(this.jTextFieldSteamPath, -1, 329, 32767)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButtonSteamPath)))).addGroup(layout.createSequentialGroup().addGap(162, 162, 162).addComponent(this.jButtonApply))).addContainerGap()));

    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jTextFieldMinecraftPath, -2, -1, -2).addComponent(this.jButtonMinecraftPath)).addGap(18, 18, 18).addComponent(this.jLabel2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jTextFieldSteamPath, -2, -1, -2).addComponent(this.jButtonSteamPath)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jComboBoxSteamUser, -2, -1, -2).addComponent(this.jLabelSteamUserName)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 24, 32767).addComponent(this.jButtonApply).addContainerGap()));

    pack();
  }

  private void jButtonMinecraftPathActionPerformed(ActionEvent evt)
  {
    changePath(this.jTextFieldMinecraftPath);
    updateMinecraftPath();
  }

  private void jButtonSteamPathActionPerformed(ActionEvent evt)
  {
    changePath(this.jTextFieldSteamPath);
    updateSteamPath();
  }

  private void jButtonApplyActionPerformed(ActionEvent evt)
  {
    this.minecraftPath = this.jTextFieldMinecraftPath.getText();
    this.config.setMinecraftPath(this.minecraftPath);
    this.steamPath = this.jTextFieldSteamPath.getText();
    this.config.setSteamPath(this.steamPath);
    this.config.setSteamUserName((String)this.jComboBoxSteamUser.getSelectedItem());
    this.parent.writeConfig();
    setVisible(false);
  }

  private void jTextFieldMinecraftPathKeyReleased(KeyEvent evt)
  {
    updateMinecraftPath();
  }

  private void jTextFieldSteamPathKeyReleased(KeyEvent evt)
  {
    updateSteamPath();
  }

  public static void main(String[] args)
  {
    try
    {
      for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
        if ("Nimbus".equals(info.getName())) {
          UIManager.setLookAndFeel(info.getClassName());
          break;
        }
    }
    catch (ClassNotFoundException ex)
    {
      Logger.getLogger(PathAsker.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (InstantiationException ex)
    {
      Logger.getLogger(PathAsker.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (IllegalAccessException ex)
    {
      Logger.getLogger(PathAsker.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (UnsupportedLookAndFeelException ex)
    {
      Logger.getLogger(PathAsker.class.getName()).log(Level.SEVERE, null, ex);
    }
    EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
        new PathAsker(null, null).setVisible(true);
      }
    });
  }
}
