package main.scala.GUI

import java.awt.event.{ActionListener, ActionEvent}
import javax.swing._
import java.io.File
import main.scala.Resources.Resources

class StartWebcamButton extends JButton{
	setText("Start Webcam")
	addActionListener(new ActionListener(){

		def actionPerformed(e:ActionEvent){
			Resources.getCurrentPanel
			Resources.switchPanel(new RecognitionPanel)
			Resources.createNewWebcamThread
			Resources.currentWebcamThread.start
		}
	})
}