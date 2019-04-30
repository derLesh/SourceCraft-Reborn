package gui;

import config.Config;
import config.ConvertOption;
import config.Place;
import config.SourceGame;
import config.TexturePack;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.LayoutStyle.ComponentPlacement;
import minecraft.MCRtoVMFconverter;
import minecraft.map.DefaultMinecraftMap;

public class GUI
        extends JFrame
{
  private static final String frameTitle = "Sourcecraft - MCR to VMF Converter";
  private MCRtoVMFconverter converter;
  private Config config;
  private String[] places;
  private ComboBoxModel cbmPlaceOptions;
  private String defaultPlace;
  private String[] games;
  private ComboBoxModel cbmGameOptions;
  private String defaultGame;
  private String[] style;
  private ComboBoxModel cbmStyleOptions;
  private String defaultStyle;
  private String[] texturePacks;
  private ComboBoxModel cbmTexturePackOptions;
  private JButton jButton3;
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
  private JMenuBar jMenuBar1;
  private JToggleButton jToggleButtonConvert;

  public static void main(String[] args)
  {
    GUI g = new GUI(new MCRtoVMFconverter());
    g.setVisible(true);
  }

  private void askPath()
  {
    if ((!this.config.getSteamPath().exists()) || (!this.config.getMinecraftPath().exists()) || (!this.config.steamUserNameExists()))
    {
      setVisible(false);
      PathAsker pa = new PathAsker(this, this.config);
      pa.setVisible(true);
    }
  }

  public GUI(MCRtoVMFconverter converter)
  {
    this.converter = converter;
    this.config = converter.getConfig();

    String tempPlace = "<new>";
    this.places = this.config.getPlaceNamesWithDefault(tempPlace);
    this.cbmPlaceOptions = new DefaultComboBoxModel(this.places);
    this.defaultPlace = tempPlace;

    this.games = this.config.getGameNames();
    this.cbmGameOptions = new DefaultComboBoxModel(this.games);

    this.style = this.config.getConvertOptionNames();
    this.cbmStyleOptions = new DefaultComboBoxModel(this.style);
    if (this.games.length > 0) {
      this.defaultStyle = this.config.getConvertOption(this.config.getGame(this.games[0]).getDefaultConvertOption()).getName();
    }
    this.texturePacks = this.config.getTexturePacks();
    this.cbmTexturePackOptions = new DefaultComboBoxModel(this.texturePacks);

    initComponents();
    setDefaultPlace(this.config.getPlace());
    setDefaultGame(this.config.getGame());
    setDefaultStyle(this.config.getConvertOption());
    setDefaultPack(this.config.getPack());

    this.jComboBoxPlace.setSelectedItem(this.config.getPlace());
    this.defaultGame = this.config.getGame();

    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);

    askPath();
  }

  private void setDefaultStyle(String style)
  {
    if (style != null) {
      this.jComboBoxStyle.setSelectedItem(style);
    }
  }

  private void setDefaultGame(String game)
  {
    if (game != null) {
      this.jComboBoxGame.setSelectedItem(game);
    }
  }

  private void setDefaultPlace(String place)
  {
    if (place != null) {
      this.jComboBoxPlace.setSelectedItem(place);
    }
  }

  private void setDefaultPack(String pack)
  {
    if (pack != null) {
      this.jComboBoxTexturePack.setSelectedItem(pack);
    }
  }

  public void setDefaultPlaceNew(String place)
  {
    this.places = this.config.getPlaceNamesWithDefault("<new>");
    this.cbmPlaceOptions = new DefaultComboBoxModel(this.places);
    this.jComboBoxPlace.setModel(this.cbmPlaceOptions);
    if (place != null) {
      this.jComboBoxPlace.setSelectedItem(place);
    }
    this.converter.writeConfig();
  }

  public File saveDialog(SourceGame game, Place place)
  {
    File fileFolder = game.getGamePath(this.config);

    JFileChooser fileChooser = new JFileChooser();
    if (fileFolder.exists()) {
      fileChooser.setCurrentDirectory(fileFolder);
    }
    fileChooser.setSelectedFile(new File(place.getName() + ".vmf"));
    int state = fileChooser.showSaveDialog(this);
    if (state == 0)
    {
      File file = fileChooser.getSelectedFile();
      return file;
    }
    return null;
  }

  public void writeConfig()
  {
    this.converter.writeConfig();
  }

  public void convert(Place place, SourceGame game, ConvertOption option, TexturePack pack)
  {
    assert (place != null);assert (game != null);assert (option != null);assert (pack != null);

    File saveTarget = saveDialog(game, place);
    if (saveTarget != null)
    {
      DefaultMinecraftMap map = this.converter.getMinecraftMap(place, game, option, pack);
      game.setGamePath(saveTarget);
      map.save(saveTarget);
      this.converter.checkIfTexturesExist(game, pack);
      JOptionPane.showMessageDialog(null, "Use Source SDK to edit " + saveTarget.getName() + ".", "Success", 1);
    }
    this.config.setPlace(place.getName());
    this.config.setGame(game.getName());
    this.config.setConvertOption(option.getName());
    this.config.setPack(pack.getName());
    this.converter.writeConfig();
    this.jToggleButtonConvert.setVisible(true);
  }

  private void initComponents()
  {
    this.jLabel3 = new JLabel();
    this.jButton3 = new JButton();
    this.jLabel1 = new JLabel();
    this.jLabel2 = new JLabel();
    this.jLabel4 = new JLabel();
    this.jButtonPlace = new JButton();
    this.jButtonGame = new JButton();
    this.jButtonStyle = new JButton();
    this.jComboBoxGame = new JComboBox();
    this.jComboBoxStyle = new JComboBox();
    this.jLabel5 = new JLabel();
    this.jButtonTexturePack = new JButton();
    this.jComboBoxTexturePack = new JComboBox();
    this.jComboBoxPlace = new JComboBox();
    this.jToggleButtonConvert = new JToggleButton();
    this.jMenuBar1 = new JMenuBar();

    this.jLabel3.setText("jLabel3");

    this.jButton3.setText("jButton3");

    setDefaultCloseOperation(3);
    setTitle("Sourcecraft - MCR to VMF Converter");
    setCursor(new Cursor(0));
    setResizable(false);

    this.jLabel1.setText("Minecraft-Place");

    this.jLabel2.setText("Source-Game");

    this.jLabel4.setText("Style");

    this.jButtonPlace.setText("...");
    this.jButtonPlace.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        GUI.this.jButtonPlaceActionPerformed(evt);
      }
    });
    this.jButtonGame.setText("...");
    this.jButtonGame.setEnabled(false);

    this.jButtonStyle.setText("...");
    this.jButtonStyle.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        GUI.this.jButtonStyleActionPerformed(evt);
      }
    });
    this.jComboBoxGame.setModel(this.cbmGameOptions);
    this.jComboBoxGame.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        GUI.this.jComboBoxGameActionPerformed(evt);
      }
    });
    this.jComboBoxStyle.setModel(this.cbmStyleOptions);
    this.jComboBoxStyle.setSelectedItem(this.defaultStyle);

    this.jLabel5.setText("Texture-Pack");

    this.jButtonTexturePack.setText("...");
    this.jButtonTexturePack.setEnabled(false);

    this.jComboBoxTexturePack.setModel(this.cbmTexturePackOptions);

    this.jComboBoxPlace.setModel(this.cbmPlaceOptions);
    this.jComboBoxPlace.setSelectedItem(this.defaultPlace);

    this.jToggleButtonConvert.setText("Convert");
    this.jToggleButtonConvert.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent evt)
      {
        GUI.this.jToggleButtonConvertActionPerformed(evt);
      }
    });
    setJMenuBar(this.jMenuBar1);

    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel1).addComponent(this.jLabel2).addComponent(this.jLabel4).addComponent(this.jLabel5)).addGap(26, 26, 26).addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup().addComponent(this.jComboBoxGame, -2, 138, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.jButtonGame)).addGroup(layout.createSequentialGroup().addComponent(this.jComboBoxPlace, -2, 138, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButtonPlace)).addGroup(layout.createSequentialGroup().addComponent(this.jComboBoxStyle, -2, 138, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButtonStyle)).addGroup(layout.createSequentialGroup().addComponent(this.jComboBoxTexturePack, -2, 138, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButtonTexturePack))).addContainerGap()).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap(228, 32767).addComponent(this.jToggleButtonConvert).addContainerGap()));

    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel1).addComponent(this.jButtonPlace).addComponent(this.jComboBoxPlace, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel2).addComponent(this.jButtonGame).addComponent(this.jComboBoxGame, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel4).addComponent(this.jComboBoxStyle, -2, -1, -2).addComponent(this.jButtonStyle)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel5).addComponent(this.jButtonTexturePack).addComponent(this.jComboBoxTexturePack, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 18, 32767).addComponent(this.jToggleButtonConvert).addContainerGap()));

    pack();
  }

  private void jButtonStyleActionPerformed(ActionEvent evt)
  {
    ConvertOption optionSelected = this.config.getConvertOption((String)this.jComboBoxStyle.getSelectedItem());
    StyleEditor se = new StyleEditor(this, optionSelected);
    se.setLocation(getLocation().x, getLocation().y);
    se.setVisible(true);
  }

  private void jComboBoxGameActionPerformed(ActionEvent evt)
  {
    int index = this.jComboBoxGame.getSelectedIndex();
    SourceGame game = this.config.getGame((String)this.cbmGameOptions.getElementAt(index));
    String gameStyle = game.getDefaultConvertOption();
    this.jComboBoxStyle.setSelectedItem(gameStyle);
  }

  private void jButtonPlaceActionPerformed(ActionEvent evt)
  {
    Place selected = this.config.getPlace((String)this.cbmPlaceOptions.getSelectedItem());
    PlaceCreator p;
    if (selected == null) {
      p = new PlaceCreator(this, this.config, "");
    } else {
      p = new PlaceCreator(this, this.config, selected);
    }
    p.setLocation(getLocation().x, getLocation().y);
    p.setVisible(true);
  }

  private void jToggleButtonConvertActionPerformed(ActionEvent evt)
  {
    Place place = this.config.getPlace((String)this.jComboBoxPlace.getSelectedItem());
    SourceGame game = this.config.getGame((String)this.jComboBoxGame.getSelectedItem());
    ConvertOption option = this.config.getConvertOption((String)this.jComboBoxStyle.getSelectedItem());
    TexturePack texturePack = this.config.getTexturePack((String)this.jComboBoxTexturePack.getSelectedItem());
    if (place == null) {
      JOptionPane.showMessageDialog(null, "Choose a Minecraft-Place.", "Choose a Minecraft-Place", 2);
    } else if (game == null) {
      JOptionPane.showMessageDialog(null, "Choose a Source game.", "Choose a Source game", 2);
    } else if (option == null) {
      JOptionPane.showMessageDialog(null, "Choose a Style.", "Choose a Style", 2);
    } else if (texturePack == null) {
      JOptionPane.showMessageDialog(null, "Choose a Texture-Pack.", "Choose a Texture-Pack", 2);
    } else {
      convert(place, game, option, texturePack);
    }
  }
}
