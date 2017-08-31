package main.scala.GUI

import java.awt.event.{ActionListener, ActionEvent}
import javax.swing._
import java.awt._
import java.io.File
import main.scala.Resources.Resources
import main.scala.FaceAPI._

class DeleteFaceListButton(listPanel : ListFaceListsPanel) extends JButton{
	setText("Delete Face List")
	addActionListener(new ActionListener(){

		def actionPerformed(e:ActionEvent){
			val frame : JFrame = new JFrame()
			val panel : JPanel = new JPanel()

			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS))

			val listOfIds : Array[String] = ListFaceLists.getListOfIds

			val idsBox : JComboBox[String] = new JComboBox(listOfIds)

			panel.add(new JLabel("Existing Lists: "))
			panel.add(idsBox)

			val result : Int = JOptionPane.showConfirmDialog(null, panel, "Delete new face list", JOptionPane.OK_CANCEL_OPTION)

			if(result == JOptionPane.OK_OPTION){

				JOptionPane.showMessageDialog(frame, DeleteFaceList.deleteFaceList(idsBox.getSelectedItem().asInstanceOf[String]));
				listPanel.remove(listPanel.getLayout().asInstanceOf[BorderLayout].getLayoutComponent(BorderLayout.CENTER))
				listPanel.add(listPanel.getFaceListsList, BorderLayout.CENTER)
				listPanel.repaint()
				listPanel.revalidate()
			}

		}
	})
}