package main.scala.Resources

import java.io.File
import java.awt._
import javax.swing._
import util.control.Breaks._
import org.apache.commons.io.{FileUtils, FilenameUtils}
import main.scala.GUI.FaceWebcamDetectorApp

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

	var mainPanel : JPanel = new JPanel

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

	var selectedFaceList : String = ""

	var filePaths : Array[File] = Array.empty[File]

	def addToFilePaths (newFile : File) : Array[File] = {
		filePaths = filePaths :+ newFile
		return filePaths
	}

	var webcamHasStopped : Boolean = false

	val webcamApp : FaceWebcamDetectorApp = new FaceWebcamDetectorApp()

	var currentWebcamThread : Thread = new Thread()

	def createNewWebcamThread{
		val webcamThread : Thread = new Thread {
		override def run {
			Resources.webcamApp.start
			}
		}
		currentWebcamThread = webcamThread
	}

	def deleteSnapshots{
		var i : Int = 1
			breakable{
				while(true){
					if(new File("Pics\\" + i + ".jpg").exists()){
						FileUtils.deleteQuietly(FileUtils.getFile("Pics/" + i + ".jpg"))
						i += 1
					}
					else break
				}
			}
	}
}