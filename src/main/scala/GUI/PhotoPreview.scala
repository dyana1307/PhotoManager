package main.scala.GUI

import java.awt._
import javax.swing._
import main.scala.Resources.Resources

class PhotoPreview extends JPanel{

	setPreferredSize(new Dimension(Resources.frameWidth, Resources.frameHeight))
	setLayout(new BorderLayout())

	var layeredPane : JLayeredPane = new JLayeredPane
	layeredPane.setPreferredSize(new Dimension(Resources.frameWidth, Resources.frameHeight))

	var picPanel : JPanel = new JPanel
	var label: JLabel = new JLabel(new ImageIcon(new ImageIcon(Resources.selectedFilePath).getImage().getScaledInstance(Resources.frameWidth, Resources.frameHeight, Image.SCALE_SMOOTH)))
	picPanel.add(label)
	var rectPanel : JPanel = new DetectedFacesPanel(Resources.selectedFilePath)
	picPanel.setBounds(0, 0, Resources.frameWidth, Resources.frameHeight)
	rectPanel.setBounds(0, 0, Resources.frameWidth, Resources.frameHeight)
	layeredPane.add(picPanel, new Integer(1))
	layeredPane.add(rectPanel, new Integer(2))

	add(layeredPane, BorderLayout.CENTER)
	add(new BackButton, BorderLayout.SOUTH)
}