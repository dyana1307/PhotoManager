package main.scala.GUI

import java.awt.event.{ActionListener, ActionEvent}
import javax.swing._
import java.io.File
import main.scala.Resources.Resources
import main.scala.FaceAPI._

class AddFaceToFaceListButton extends JButton{
	setText("Add face")
	addActionListener(new ActionListener(){

		def actionPerformed(e:ActionEvent){
			var initialPath : File = new File("C:/Users/Dyana/workspace/PhotoM/Pics")
			var fileChooser : JFileChooser = new JFileChooser(initialPath)
			//fileChooser.setMultiSelectionEnabled(true)
			var rVal : Int = fileChooser.showOpenDialog(null)
			if(rVal == JFileChooser.APPROVE_OPTION){

				val panel : JPanel = new JPanel()

				val listOfIds : Array[String] = ListFaceLists.getListOfIds

				val idsBox : JComboBox[String] = new JComboBox(listOfIds)

				panel.add(new JLabel("Existing face lists: "))
				panel.add(idsBox)

				val userDataField : JTextField = new JTextField(20)
				val userDataLabel : JLabel = new JLabel("Student ID: ")
				userDataLabel.setHorizontalAlignment(SwingConstants.CENTER)

				panel.add(userDataLabel)
				panel.add(userDataField)

				val result : Int = JOptionPane.showConfirmDialog(null, panel, "Choose a face list", JOptionPane.OK_CANCEL_OPTION)

				if(result == JOptionPane.OK_OPTION){
					JOptionPane.showMessageDialog(new JFrame, AddFaceToFaceList.addFaceToFaceList(idsBox.getSelectedItem().asInstanceOf[String], userDataField.getText() , fileChooser.getSelectedFile().getPath()));
				}

			}
		}
	})
}