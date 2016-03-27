package com.ymcmp.tbrg

import java.io.PrintStream
import javax.swing.{JFrame, JScrollPane, JTextArea, UIManager}

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

  val txtBody = new StreamTextArea()
  add(txtBody)

  txtBody.cbAutoCmpPat = "[A-Z_]{2,}".r

  System.setOut(txtBody.outStream)
  System.setIn(txtBody.inStream)

  setSize(400, 400)
  setResizable(false)
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
  setVisible(true)
}
