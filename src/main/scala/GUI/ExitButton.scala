package main.scala.GUI

import java.awt.event.{ActionListener, ActionEvent}
import javax.swing._
import main.scala.Resources.Resources

class ExitButton extends JButton{
	setText("Exit")
	addActionListener(new ActionListener() {
		def actionPerformed(e:ActionEvent) { Resources.deleteSnapshots; System.exit(0) }
		})
}