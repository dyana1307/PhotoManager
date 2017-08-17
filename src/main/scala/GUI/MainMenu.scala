package main.scala.GUI

import swing._
import java.awt.Point
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
		Resources.mainPanel.add(new MainPanel)
		var frame = new MainFrame{
			title = "Photo Manager"
			contents = Component.wrap(Resources.mainPanel)
			location = Resources.centreOfScreen
		}
		frame.visible = true
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
	var glayout : GridLayout = new GridLayout(8, 0)
	setLayout(glayout)
	setPreferredSize(new Dimension(200, 300))
	var label : JLabel = new JLabel("Welcome to Photo Manager!")
	label.setHorizontalAlignment(SwingConstants.CENTER)
	add(label)
	add(new BrowseButton)
	add(new CreateFaceListButton())
	add(new DeleteFaceListButton())
	add(new UpdateFaceListButton())
	add(new ListFaceListsButton())
	add(new JButton("Options"))
	add(new ExitButton)
}

