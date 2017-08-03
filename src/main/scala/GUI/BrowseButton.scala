package main.scala.GUI

import java.awt.event.{ActionListener, ActionEvent}
import javax.swing._
import main.scala.Resources.Resources

class BrowseButton extends JButton{
	setText("Browse")
	addActionListener(new ActionListener(){

		def actionPerformed(e:ActionEvent){
			var fileChooser : JFileChooser = new JFileChooser
			fileChooser.setMultiSelectionEnabled(true)
			var rVal : Int = fileChooser.showOpenDialog(null)
			//if(rVal == JFIleChooser.APPROVE_OPTION)

		}
	})

	import java.io.File
	var file : File = new File("Pics/test.png")
	Resources.addToFilePaths(file)
}