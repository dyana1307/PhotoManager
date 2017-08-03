package main.scala.GUI

import swing._
import javax.swing._
import javax.swing.UIManager.LookAndFeelInfo
import main.scala.Resources.Resources

object MainMenu{
	def main(args: Array[String]) : Unit = {

		try {
    		for ((info : LookAndFeelInfo) <- UIManager.getInstalledLookAndFeels()) {
        		if ("Nimbus".equals(info.getName())) {
            		UIManager.setLookAndFeel(info.getClassName())
        		}
    		}
	}
		var mainPanel = new MainPanel
		var frame = new MainFrame{
			title = "Photo Manager"
			contents = Component.wrap(mainPanel)
		}
		frame.visible = true
		import java.io.File
		var file : File = new File("Pics/CoverPic.jpg")
		Resources.addToFilePaths(file)
		for (x <- Resources.filePaths){
			println(x.getPath())
		}

	}
}

import java.awt._
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import java.io.File
class MainPanel extends JPanel{
	var label: JLabel = new JLabel(new ImageIcon(new ImageIcon("Pics/CoverPic.jpg").getImage().getScaledInstance(600, 500, Image.SCALE_SMOOTH)))
	setLayout(new BorderLayout)
	setPreferredSize(new Dimension(800, 500))
	add(label, BorderLayout.CENTER)
	add(new ButtonPanel, BorderLayout.EAST)
	
}

class ButtonPanel extends JPanel{
	var glayout : GridLayout = new GridLayout(4, 0, 20, 30)
	setLayout(glayout)
	setPreferredSize(new Dimension(200, 300))
	var label : JLabel = new JLabel("Welcome to Photo Manager!")
	label.setHorizontalAlignment(SwingConstants.CENTER)
	add(label)
	add(new BrowseButton)
	add(new JButton("Options"))
	add(new ExitButton)
}

