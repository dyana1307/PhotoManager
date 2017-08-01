package main.scala.GUI

import swing._
import javax.swing._
import javax.swing.UIManager.LookAndFeelInfo

object MainMenu{
	def main(args: Array[String]) : Unit = {

		try {
    		for ((info : LookAndFeelInfo) <- UIManager.getInstalledLookAndFeels()) {
        		if ("Napkin".equals(info.getName())) {
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
	}
}

import java.awt._
class MainPanel extends JPanel{
	setLayout(new BorderLayout)
	setPreferredSize(new Dimension(500, 500))
	add(new ButtonPanel, BorderLayout.EAST)
	
}

class ButtonPanel extends JPanel{
	var glayout : GridLayout = new GridLayout(4, 0, 20, 30)
	setLayout(glayout)
	setPreferredSize(new Dimension(200, 300))
	var label : JLabel = new JLabel("Welcome to Photo Manager!")
	label.setHorizontalAlignment(SwingConstants.CENTER)
	add(label)
	add(new JButton("Create Album"))
	add(new JButton("Options"))
	add(new JButton("Exit"))
}

