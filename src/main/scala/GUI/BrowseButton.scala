package main.scala.GUI

import java.awt.event.{ActionListener, ActionEvent}
import javax.swing._
import java.io.File
import main.scala.Resources.Resources

class BrowseButton extends JButton{
	setText("Browse")
	addActionListener(new ActionListener(){

		def actionPerformed(e:ActionEvent){
			var initialPath : File = new File("C:/Users/Dyana/workspace/PhotoM/Pics")
			var fileChooser : JFileChooser = new JFileChooser(initialPath)
			fileChooser.setMultiSelectionEnabled(true)
			var rVal : Int = fileChooser.showOpenDialog(null)
			if(rVal == JFileChooser.APPROVE_OPTION){
				Resources.selectedFilePath = fileChooser.getSelectedFile().getPath()
				Resources.getCurrentPanel
				Resources.switchPanel(new PhotoPreview)
			}
		}
	})
}