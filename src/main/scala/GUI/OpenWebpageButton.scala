package main.scala.GUI

import java.awt.event.{ActionListener, ActionEvent}
import javax.swing._
import java.awt._
import java.net.URI

class OpenWebpageButton extends JButton{
	setText("Fingerprint Scanner System")
	addActionListener(new ActionListener(){

		def actionPerformed(e:ActionEvent){
			java.awt.Desktop.getDesktop().browse(new URI("localhost:8080"))
		}
	})
}