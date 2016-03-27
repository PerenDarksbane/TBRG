package com.ymcmp.tbrg

import java.awt.event.{ActionEvent, ActionListener}
import java.io.PrintStream
import javax.swing._

import com.ymcmp.tbrg.guicmp.StreamTextArea

/**
  * Created by Plankp on 2016-03-27.
  */
object AppGui {
  def main(args: Array[String]): Unit = {
    // This file is just a delegate to the cmd line switch -g or --gui
    App.main(Array("-g"))
  }

  def enterGui(): Unit = {
    try {
      try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName)
        // Using System L&F
      } catch {
        case _: Exception => {} // Using METAL L&F
      }
      new AppGui() // Default mode is enter GUI mode
    } catch {
      case e: java.awt.HeadlessException => println("Using fallback mode")
    }
  }
}

class AppGui extends JFrame("Text based RPG") {

  val topMenuBar = new JMenuBar
  this setJMenuBar topMenuBar

  val menuHelp = new JMenu("Help")
  topMenuBar add menuHelp

  val menuItemHelp = new JMenuItem("Help")
  menuHelp add menuItemHelp

  menuItemHelp.addActionListener(new ActionListener() {
    def actionPerformed(e: ActionEvent): Unit =
      JOptionPane.showMessageDialog(null, "Type in the response based on the prompts. If you do not know \nwhat to enter, click on the drop down arrow on the combo box \nand it might have the valid answers presented")
  })

  val menuItemAbout = new JMenuItem("About")
  menuHelp add menuItemAbout

  menuItemAbout.addActionListener(new ActionListener() {
    def actionPerformed(e: ActionEvent): Unit =
      JOptionPane.showMessageDialog(null, "Made with love by Prototype Games\nTo find out more, go to http://prototype-games.github.io")
  })

  val txtBody = new StreamTextArea
  this add txtBody

  txtBody.cbAutoCmpPat = "[A-Z_]{2,}".r

  System setOut txtBody.outStream
  System setIn txtBody.inStream

  this setSize(400, 400)
  this setResizable false
  this setDefaultCloseOperation JFrame.EXIT_ON_CLOSE
  this setVisible true
}
