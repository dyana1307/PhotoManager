package main.scala.Resources

import java.io.File
import java.awt._
import javax.swing._

object Resources{

	var frameWidth : Int = 800
	var frameHeight : Int = 500

	def getScreenWidth : Int = {
		return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width
	}

	def getScreenHeight : Int = {
		return java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height
	}

	val centreOfScreen : Point = new Point((getScreenWidth - frameWidth) / 2, (getScreenHeight - frameHeight) / 2)

	var mainPanel : JPanel = new JPanel()

	var previousPanel : JPanel = new JPanel

	def switchPanel(newPanel: JPanel) = {
		mainPanel.removeAll
		mainPanel.add(newPanel)
		newPanel.setPreferredSize(mainPanel.getSize())
		mainPanel.revalidate()
		mainPanel.repaint()
	}

	def getCurrentPanel = {
		previousPanel = mainPanel.getComponent(0).asInstanceOf[JPanel]
	}

	var selectedFilePath : String = "Pics/test.png"

	var filePaths : Array[File] = Array.empty[File]

	def addToFilePaths (newFile : File) : Array[File] = {
		filePaths = filePaths :+ newFile
		return filePaths
	}

}