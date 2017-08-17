package main.scala.GUI

import java.awt.event.{ActionListener, ActionEvent}
import javax.swing._
import java.awt._
import java.io.File
import main.scala.Resources.Resources
import main.scala.FaceAPI.ListFaceLists

class ListFaceListsButton extends JButton{
	setText("List All Face Lists")
	addActionListener(new ActionListener(){

		def actionPerformed(e:ActionEvent){
			val frame : JFrame = new JFrame()
			val panel : JPanel = new JPanel()

			val faceLists : Array[(String, String, String)] = ListFaceLists.listFaceLists

			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS))

			val headerPanel : JPanel = new JPanel
			headerPanel.setLayout(new GridLayout(0, 3, 20, 20))

			var headerIdLabel : JLabel = new JLabel("List ID")
			headerIdLabel.setHorizontalAlignment(SwingConstants.CENTER)

			var headerNameLabel : JLabel = new JLabel("List Name")
			headerNameLabel.setHorizontalAlignment(SwingConstants.CENTER)

			var headerUserDataLabel : JLabel = new JLabel("User Data")
			headerUserDataLabel.setHorizontalAlignment(SwingConstants.CENTER)

			headerPanel.add(headerIdLabel)
			headerPanel.add(headerNameLabel)
			headerPanel.add(headerUserDataLabel)

			panel.add(headerPanel)

			for(list <- faceLists){
				val panelInfo : JPanel = new JPanel
				panelInfo.setLayout(new GridLayout(0, 3))

				var idLabel : JLabel = new JLabel(list._1)
				idLabel.setHorizontalAlignment(SwingConstants.CENTER)

				var nameLabel : JLabel = new JLabel(list._2)
				nameLabel.setHorizontalAlignment(SwingConstants.CENTER)

				var userDataLabel : JLabel = new JLabel(list._3)
				userDataLabel.setHorizontalAlignment(SwingConstants.CENTER)

				panelInfo.add(idLabel)
				panelInfo.add(nameLabel)
				panelInfo.add(userDataLabel)

				panel.add(panelInfo)
			}

			val result : Int = JOptionPane.showConfirmDialog(null, panel, "Existing face lists", JOptionPane.OK_CANCEL_OPTION)

		}
	})
}