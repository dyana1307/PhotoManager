package main.scala.GUI

import main.scala.Resources.Resources

import java.awt._
import java.awt.event.{ActionListener, ActionEvent}
import javax.swing._

class BackButton extends JButton{
	setText("Back")
	addActionListener(new ActionListener() {

		def actionPerformed(e : ActionEvent){
			Resources.switchPanel(Resources.previousPanel)
		}
	})
}