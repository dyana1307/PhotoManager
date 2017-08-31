package main.scala.GUI

import java.awt.event.{ActionListener, ActionEvent}
import javax.swing._
import java.awt._
import java.io.File
import scala.swing.event._
import main.scala.Resources.Resources
import main.scala.FaceAPI.ListFaceLists

class ListFaceListsPanel extends JPanel{

	setLayout(new BorderLayout)

	add(getFaceListsList, BorderLayout.CENTER)
	add(getFaceListsButtonsPanel, BorderLayout.EAST)

	def getFaceListsHeader : JPanel = {
		val panel : JPanel = new JPanel
		panel.setMaximumSize(new Dimension(600, 50))
		panel.setLayout(new GridLayout(0, 3, 20, 20))

		var headerIdLabel : JLabel = new JLabel("List ID")
		headerIdLabel.setHorizontalAlignment(SwingConstants.CENTER)

		var headerNameLabel : JLabel = new JLabel("List Name")
		headerNameLabel.setHorizontalAlignment(SwingConstants.CENTER)

		var headerUserDataLabel : JLabel = new JLabel("User Data")
		headerUserDataLabel.setHorizontalAlignment(SwingConstants.CENTER)

		panel.add(headerIdLabel)
		panel.add(headerNameLabel)
		panel.add(headerUserDataLabel)

		return panel
	}

	def getFaceListsList : JPanel = {

		var panel : JPanel = new JPanel
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS))

		panel.add(getFaceListsHeader)

		val faceLists : Array[(String, String, String)] = ListFaceLists.listFaceLists

		for(list <- faceLists){
			val panelInfo : JPanel = new JPanel
			panelInfo.setLayout(new GridLayout(0, 3, 20, 20))
			panelInfo.setMaximumSize(new Dimension(600, 20))

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

		return panel
	}

	def getFaceListsButtonsPanel : JPanel = {
		var panel : JPanel = new JPanel
		var glayout : GridLayout = new GridLayout(7, 0)
		panel.setLayout(glayout)

		panel.add(new BackButton)
		panel.add(new CreateFaceListButton(this))
		panel.add(new DeleteFaceListButton(this))
		panel.add(new UpdateFaceListButton(this))
		panel.add(new AddFaceToFaceListButton())
		panel.add(new DeleteFaceFromFaceListButton())
		panel.add(new ExitButton)

		return panel
	}
}
