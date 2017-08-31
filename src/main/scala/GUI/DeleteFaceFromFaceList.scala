package main.scala.GUI

import java.awt.event.{ActionListener, ActionEvent}
import javax.swing._
import java.io.File
import main.scala.Resources.Resources
import main.scala.FaceAPI._

class DeleteFaceFromFaceListButton extends JButton{
	setText("Delete face")
	addActionListener(new ActionListener(){

		def actionPerformed(e:ActionEvent){

			val panel : JPanel = new JPanel()

			val listOfIds : Array[String] = ListFaceLists.getListOfIds

			val idsBox : JComboBox[String] = new JComboBox(listOfIds)

			panel.add(new JLabel("Existing face lists: "))
			panel.add(idsBox)

			val listOfFaces : Array[String] = GetFaceList.getFaceListFaceIds(idsBox.getSelectedItem().asInstanceOf[String])

			val faceIdsBox : JComboBox[String] = new JComboBox(listOfFaces)

			panel.add(new JLabel("Faces in the list: "))
			panel.add(faceIdsBox)

			idsBox.addActionListener(new ActionListener(){
				def actionPerformed(e:ActionEvent){
					faceIdsBox.removeAllItems()

					val faces : Array[String] = GetFaceList.getFaceListFaceIds(idsBox.getSelectedItem().asInstanceOf[String])
					for(face <- faces){
						faceIdsBox.addItem(face)
					}
				}
			})

			val result : Int = JOptionPane.showConfirmDialog(null, panel, "Choose a face list and a face ID", JOptionPane.OK_CANCEL_OPTION)

			if(result == JOptionPane.OK_OPTION){
				JOptionPane.showMessageDialog(new JFrame, DeleteFaceFromFaceList.deleteFaceFromFaceList(idsBox.getSelectedItem().asInstanceOf[String], faceIdsBox.getSelectedItem().asInstanceOf[String]));
			}

		}
	})
}