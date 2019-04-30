package gui;

import config.Config;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class GUI extends javax.swing.JFrame
{
  private static final String frameTitle = "Sourcecraft - MCR to VMF Converter";
  private minecraft.MCRtoVMFconverter converter;
  private Config config;
  private String[] places;
  private javax.swing.ComboBoxModel cbmPlaceOptions;
  private String defaultPlace;
  private String[] games;
  private javax.swing.ComboBoxModel cbmGameOptions;
  private String defaultGame;
  private String[] style;
  private javax.swing.ComboBoxModel cbmStyleOptions;
  private String defaultStyle;
  private String[] texturePacks;
  private javax.swing.ComboBoxModel cbmTexturePackOptions;
  private JButton jButton3;
  
  public static void main(String[] args)
  {
    GUI g = new GUI(new minecraft.MCRtoVMFconverter());
    g.setVisible(true);
  }
  
  private void askPath() {
    if ((!config.getSteamPath().exists()) || (!config.getMinecraftPath().exists()) || (!config.steamUserNameExists())) {
      setVisible(false);
      PathAsker pa = new PathAsker(this, config);
      pa.setVisible(true);
    }
  }
  

  private JButton jButtonGame;
  
  private JButton jButtonPlace;
  
  private JButton jButtonStyle;
  
  private JButton jButtonTexturePack;
  
  private JComboBox jComboBoxGame;
  private JComboBox jComboBoxPlace;
  private JComboBox jComboBoxStyle;
  private JComboBox jComboBoxTexturePack;
  private JLabel jLabel1;
  private JLabel jLabel2;
  private JLabel jLabel3;
  private JLabel jLabel4;
  private JLabel jLabel5;
  private javax.swing.JMenuBar jMenuBar1;
  private javax.swing.JToggleButton jToggleButtonConvert;
  public GUI(minecraft.MCRtoVMFconverter converter)
  {
    this.converter = converter;
    config = converter.getConfig();
    
    String tempPlace = "<new>";
    places = config.getPlaceNamesWithDefault(tempPlace);
    cbmPlaceOptions = new javax.swing.DefaultComboBoxModel(places);
    defaultPlace = tempPlace;
    
    games = config.getGameNames();
    cbmGameOptions = new javax.swing.DefaultComboBoxModel(games);
    
    style = config.getConvertOptionNames();
    cbmStyleOptions = new javax.swing.DefaultComboBoxModel(style);
    if (games.length > 0) {
      defaultStyle = config.getConvertOption(config.getGame(games[0]).getDefaultConvertOption()).getName();
    }
    
    texturePacks = config.getTexturePacks();
    cbmTexturePackOptions = new javax.swing.DefaultComboBoxModel(texturePacks);
    
    initComponents();
    setDefaultPlace(config.getPlace());
    setDefaultGame(config.getGame());
    setDefaultStyle(config.getConvertOption());
    setDefaultPack(config.getPack());
    
    jComboBoxPlace.setSelectedItem(config.getPlace());
    defaultGame = config.getGame();
    
    java.awt.Dimension d = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    int x = (width - getSizewidth) / 2;
    int y = (height - getSizeheight) / 2;
    setLocation(x, y);
    
    askPath();
  }
  
  private void setDefaultStyle(String style) {
    if (style != null) {
      jComboBoxStyle.setSelectedItem(style);
    }
  }
  
  private void setDefaultGame(String game) {
    if (game != null) {
      jComboBoxGame.setSelectedItem(game);
    }
  }
  
  private void setDefaultPlace(String place) {
    if (place != null) {
      jComboBoxPlace.setSelectedItem(place);
    }
  }
  
  private void setDefaultPack(String pack) {
    if (pack != null) {
      jComboBoxTexturePack.setSelectedItem(pack);
    }
  }
  
  public void setDefaultPlaceNew(String place) {
    places = config.getPlaceNamesWithDefault("<new>");
    cbmPlaceOptions = new javax.swing.DefaultComboBoxModel(places);
    jComboBoxPlace.setModel(cbmPlaceOptions);
    if (place != null) {
      jComboBoxPlace.setSelectedItem(place);
    }
    converter.writeConfig();
  }
  
  public java.io.File saveDialog(config.SourceGame game, config.Place place) {
    java.io.File fileFolder = game.getGamePath(config);
    
    javax.swing.JFileChooser fileChooser = new javax.swing.JFileChooser();
    if (fileFolder.exists()) {
      fileChooser.setCurrentDirectory(fileFolder);
    }
    fileChooser.setSelectedFile(new java.io.File(place.getName() + ".vmf"));
    int state = fileChooser.showSaveDialog(this);
    if (state == 0) {
      java.io.File file = fileChooser.getSelectedFile();
      return file;
    }
    return null;
  }
  
  public void writeConfig() {
    converter.writeConfig();
  }
  
  public void convert(config.Place place, config.SourceGame game, config.ConvertOption option, config.TexturePack pack) {
    assert (place != null);assert (game != null);assert (option != null);assert (pack != null);
    
    java.io.File saveTarget = saveDialog(game, place);
    if (saveTarget != null) {
      minecraft.map.DefaultMinecraftMap map = converter.getMinecraftMap(place, game, option, pack);
      game.setGamePath(saveTarget);
      map.save(saveTarget);
      converter.checkIfTexturesExist(game, pack);
      javax.swing.JOptionPane.showMessageDialog(null, "Use Source SDK to edit " + saveTarget.getName() + ".", "Success", 1);
    }
    config.setPlace(place.getName());
    config.setGame(game.getName());
    config.setConvertOption(option.getName());
    config.setPack(pack.getName());
    converter.writeConfig();
    jToggleButtonConvert.setVisible(true);
  }
  







  private void initComponents()
  {
    jLabel3 = new JLabel();
    jButton3 = new JButton();
    jLabel1 = new JLabel();
    jLabel2 = new JLabel();
    jLabel4 = new JLabel();
    jButtonPlace = new JButton();
    jButtonGame = new JButton();
    jButtonStyle = new JButton();
    jComboBoxGame = new JComboBox();
    jComboBoxStyle = new JComboBox();
    jLabel5 = new JLabel();
    jButtonTexturePack = new JButton();
    jComboBoxTexturePack = new JComboBox();
    jComboBoxPlace = new JComboBox();
    jToggleButtonConvert = new javax.swing.JToggleButton();
    jMenuBar1 = new javax.swing.JMenuBar();
    
    jLabel3.setText("jLabel3");
    
    jButton3.setText("jButton3");
    
    setDefaultCloseOperation(3);
    setTitle("Sourcecraft - MCR to VMF Converter");
    setCursor(new java.awt.Cursor(0));
    setResizable(false);
    
    jLabel1.setText("Minecraft-Place");
    
    jLabel2.setText("Source-Game");
    
    jLabel4.setText("Style");
    
    jButtonPlace.setText("...");
    jButtonPlace.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        GUI.this.jButtonPlaceActionPerformed(evt);
      }
      
    });
    jButtonGame.setText("...");
    jButtonGame.setEnabled(false);
    
    jButtonStyle.setText("...");
    jButtonStyle.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        GUI.this.jButtonStyleActionPerformed(evt);
      }
      
    });
    jComboBoxGame.setModel(cbmGameOptions);
    jComboBoxGame.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        GUI.this.jComboBoxGameActionPerformed(evt);
      }
      
    });
    jComboBoxStyle.setModel(cbmStyleOptions);
    jComboBoxStyle.setSelectedItem(defaultStyle);
    
    jLabel5.setText("Texture-Pack");
    
    jButtonTexturePack.setText("...");
    jButtonTexturePack.setEnabled(false);
    
    jComboBoxTexturePack.setModel(cbmTexturePackOptions);
    
    jComboBoxPlace.setModel(cbmPlaceOptions);
    jComboBoxPlace.setSelectedItem(defaultPlace);
    
    jToggleButtonConvert.setText("Convert");
    jToggleButtonConvert.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        GUI.this.jToggleButtonConvertActionPerformed(evt);
      }
    });
    setJMenuBar(jMenuBar1);
    
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel1).addComponent(jLabel2).addComponent(jLabel4).addComponent(jLabel5)).addGap(26, 26, 26).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false).addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup().addComponent(jComboBoxGame, -2, 138, -2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(jButtonGame)).addGroup(layout.createSequentialGroup().addComponent(jComboBoxPlace, -2, 138, -2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jButtonPlace)).addGroup(layout.createSequentialGroup().addComponent(jComboBoxStyle, -2, 138, -2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jButtonStyle)).addGroup(layout.createSequentialGroup().addComponent(jComboBoxTexturePack, -2, 138, -2).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jButtonTexturePack))).addContainerGap()).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap(228, 32767).addComponent(jToggleButtonConvert).addContainerGap()));
    































    layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel1).addComponent(jButtonPlace).addComponent(jComboBoxPlace, -2, -1, -2)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel2).addComponent(jButtonGame).addComponent(jComboBoxGame, -2, -1, -2)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel4).addComponent(jComboBoxStyle, -2, -1, -2).addComponent(jButtonStyle)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel5).addComponent(jButtonTexturePack).addComponent(jComboBoxTexturePack, -2, -1, -2)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, 32767).addComponent(jToggleButtonConvert).addContainerGap()));
    


























    pack();
  }
  
  private void jButtonStyleActionPerformed(ActionEvent evt) {
    config.ConvertOption optionSelected = config.getConvertOption((String)jComboBoxStyle.getSelectedItem());
    StyleEditor se = new StyleEditor(this, optionSelected);
    se.setLocation(getLocationx, getLocationy);
    se.setVisible(true);
  }
  
  private void jComboBoxGameActionPerformed(ActionEvent evt) {
    int index = jComboBoxGame.getSelectedIndex();
    config.SourceGame game = config.getGame((String)cbmGameOptions.getElementAt(index));
    String gameStyle = game.getDefaultConvertOption();
    jComboBoxStyle.setSelectedItem(gameStyle);
  }
  
  private void jButtonPlaceActionPerformed(ActionEvent evt) {
    config.Place selected = config.getPlace((String)cbmPlaceOptions.getSelectedItem());
    PlaceCreator p;
    PlaceCreator p; if (selected == null) {
      p = new PlaceCreator(this, config, "");
    } else {
      p = new PlaceCreator(this, config, selected);
    }
    p.setLocation(getLocationx, getLocationy);
    p.setVisible(true);
  }
  
  private void jToggleButtonConvertActionPerformed(ActionEvent evt) {
    config.Place place = config.getPlace((String)jComboBoxPlace.getSelectedItem());
    config.SourceGame game = config.getGame((String)jComboBoxGame.getSelectedItem());
    config.ConvertOption option = config.getConvertOption((String)jComboBoxStyle.getSelectedItem());
    config.TexturePack texturePack = config.getTexturePack((String)jComboBoxTexturePack.getSelectedItem());
    if (place == null) {
      javax.swing.JOptionPane.showMessageDialog(null, "Choose a Minecraft-Place.", "Choose a Minecraft-Place", 2);
    }
    else if (game == null) {
      javax.swing.JOptionPane.showMessageDialog(null, "Choose a Source game.", "Choose a Source game", 2);
    }
    else if (option == null) {
      javax.swing.JOptionPane.showMessageDialog(null, "Choose a Style.", "Choose a Style", 2);
    }
    else if (texturePack == null) {
      javax.swing.JOptionPane.showMessageDialog(null, "Choose a Texture-Pack.", "Choose a Texture-Pack", 2);
    }
    else {
      convert(place, game, option, texturePack);
    }
  }
}
