package main.scala.GUI

import java.awt.event.{ActionListener, ActionEvent}
import javax.swing._

class ExitButton extends JButton{
	setText("Exit")
	addActionListener(new ActionListener() {
		def actionPerformed(e:ActionEvent) { System.exit(0) }
		})
}