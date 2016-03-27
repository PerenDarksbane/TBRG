package com.ymcmp.tbrg.guicmp

import java.awt.BorderLayout
import java.awt.event.{ActionEvent, ActionListener}
import java.io._
import java.util.StringTokenizer
import javax.swing._

import scala.util.matching.Regex

/**
  * Created by Plankp on 2016-03-27.
  */
class StreamTextArea extends JPanel(new BorderLayout()) {

  val inputBoxOptions = new DefaultComboBoxModel[String]()

  private val textArea = new JTextArea()
  private val inputBox = new JComboBox[String](inputBoxOptions)

  this add(new JScrollPane(textArea), BorderLayout.CENTER)
  this add(inputBox, BorderLayout.PAGE_END)

  textArea setWrapStyleWord true
  textArea setLineWrap true
  textArea setEditable false

  inputBox setEditable true

  var cbAutoCmpPat: Regex = "".r

  def getComboBoxModel: DefaultComboBoxModel[String] = inputBoxOptions

  def append(str: String): StreamTextArea = {
    textArea append str
    this
  }

  def setText(t: String): StreamTextArea = {
    textArea setText t
    this
  }

  val outStream = new PrintStream(new OutputStream() {
    override def write(b: Int): Unit = {
      textArea append s"${b.asInstanceOf[Char]}"
    }
  }) {

    override def println(x: scala.Any): Unit = {
      val toks = new StringTokenizer(x.toString)
      var s: String = ""
      while (toks.hasMoreTokens) {
        s = toks.nextToken
        if ((cbAutoCmpPat findFirstIn s).isDefined) {
          // No need to trim `s` since it was extracted at the tokenizer phase
          while (s.matches(".+\\W$"))
            s = s.substring(0, s.length - 1)
          // Call trim here since word might have been "OPT ,"
          inputBoxOptions.addElement(s.trim)
        }
      }
      super.println(x)
    }
  }

  def setCaretPosition(position: Int): Unit = textArea.setCaretPosition(position)

  def getCaretPosition: Int = textArea.getCaretPosition

  override def toString: String = textArea.getText

  private val userInput = new PipedOutputStream()

  val inStream = new PipedInputStream(userInput)

  // Um... Don't ask me how this works... It just does...
  inputBox addActionListener new ActionListener {
    override def actionPerformed(e: ActionEvent): Unit = {
      val stmp = inputBox.getSelectedItem
      if (stmp != null) {
        inputBoxOptions.removeAllElements() // Clean up
        inputBoxOptions.setSelectedItem("")
        val tmp = stmp.toString
        if (tmp.trim.length > 0) {
          // Prevent same action being fired twice
          append(" ") append tmp append "\n\n"
          tmp.toCharArray foreach (userInput write _)
          userInput write '\n'
        }
      }
    }
  }
}
