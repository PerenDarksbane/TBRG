package com.ymcmp.tbrg.guicmp

import java.awt.BorderLayout
import java.awt.event.{ActionEvent, ActionListener}
import java.io._
import javax.swing.{JComboBox, JPanel, JTextArea}

/**
  * Created by Plankp on 2016-03-27.
  */
class StreamTextArea extends JPanel(new BorderLayout()) {

  private val inputBoxOptions = Array[String]()

  private val textArea = new JTextArea()
  private val inputBox = new JComboBox[String](inputBoxOptions)

  this add(textArea, BorderLayout.CENTER)
  this add(inputBox, BorderLayout.PAGE_END)

  textArea setWrapStyleWord true
  textArea setLineWrap true
  textArea setEditable false

  inputBox setEditable true

  def modifyComboBoxItems: Array[String] = inputBoxOptions

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
  })

  def setCaretPosition(position: Int): Unit = textArea.setCaretPosition(position)

  def getCaretPosition: Int = textArea.getCaretPosition

  override def toString: String = textArea.getText

  private val userInput = new PipedOutputStream()

  val inStream = new PipedInputStream(userInput)

  inputBox addActionListener new ActionListener {
    override def actionPerformed(e: ActionEvent): Unit = {
      val tmp = inputBox.getSelectedItem.toString
      if (tmp.trim.length > 0) {
        // Prevent same action being fired twice
        append(" ").append(tmp).append("\n\n")
        tmp.toCharArray foreach (userInput write _)
        userInput write '\n'
        inputBox.setSelectedItem("")
      }
    }
  }
}
