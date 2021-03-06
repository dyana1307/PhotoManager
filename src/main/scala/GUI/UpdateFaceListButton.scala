package main.scala.GUI

import java.awt.event.{ActionListener, ActionEvent}
import javax.swing._
import java.awt._
import java.io.File
import main.scala.Resources.Resources
import main.scala.FaceAPI._

class UpdateFaceListButton(listPanel : ListFaceListsPanel) extends JButton{
	setText("Update Face List")
	addActionListener(new ActionListener(){

		def actionPerformed(e:ActionEvent){
			val frame : JFrame = new JFrame()
			val panel : JPanel = new JPanel()

			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS))
			panel.setPreferredSize(new Dimension(300, 100))


			val listOfIds : Array[String] = ListFaceLists.getListOfIds
			val idsBox : JComboBox[String] = new JComboBox(listOfIds)

			val listNameField : JTextField = new JTextField(20)
			val listUserDataField : JTextField = new JTextField(20)

			val textFieldsPanel : JPanel = new JPanel()
			textFieldsPanel.setLayout(new GridLayout(3, 2, 0, 0))

			val listIdLabel : JLabel = new JLabel("List ID: ")
			listIdLabel.setHorizontalAlignment(SwingConstants.CENTER)
			textFieldsPanel.add(listIdLabel)
			textFieldsPanel.add(idsBox)

			val listNameLabel : JLabel = new JLabel("List Name: ")
			listNameLabel.setHorizontalAlignment(SwingConstants.CENTER)
			textFieldsPanel.add(listNameLabel)
			textFieldsPanel.add(listNameField)

			val userDataLabel : JLabel = new JLabel("User Data: ")
			userDataLabel.setHorizontalAlignment(SwingConstants.CENTER)
			textFieldsPanel.add(userDataLabel)
			textFieldsPanel.add(listUserDataField)

			panel.add(textFieldsPanel)

			val result : Int = JOptionPane.showConfirmDialog(null, panel, "Update existent face list", JOptionPane.OK_CANCEL_OPTION)

			if(result == JOptionPane.OK_OPTION){

				JOptionPane.showMessageDialog(frame, UpdateFaceList.updateFaceList(idsBox.getSelectedItem().asInstanceOf[String], listNameField.getText(), listUserDataField.getText()));
				listPanel.remove(listPanel.getLayout().asInstanceOf[BorderLayout].getLayoutComponent(BorderLayout.CENTER))
				listPanel.add(listPanel.getFaceListsList, BorderLayout.CENTER)
				listPanel.repaint()
				listPanel.revalidate()
			}
		}
	})
}