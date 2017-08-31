package main.scala.GUI

import java.awt.event.{ActionListener, ActionEvent}
import javax.swing._
import java.io.File
import main.scala.Resources.Resources

class FaceListsPanelButton extends JButton{
	setText("Face Lists")
	addActionListener(new ActionListener(){

		def actionPerformed(e:ActionEvent){
			Resources.getCurrentPanel
			Resources.switchPanel(new ListFaceListsPanel)
		}
	})
}