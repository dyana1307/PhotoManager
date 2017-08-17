package main.scala.GUI

import java.awt.event.{ActionListener, ActionEvent}
import javax.swing._
import java.awt._
import java.io.File
import main.scala.Resources.Resources
import main.scala.FaceAPI.DeleteFaceList

class DeleteFaceListButton extends JButton{
	setText("Delete Face List")
	addActionListener(new ActionListener(){

		def actionPerformed(e:ActionEvent){
			val frame : JFrame = new JFrame()
			val panel : JPanel = new JPanel()

			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS))

			val listIdField : JTextField = new JTextField(20)

			panel.add(new JLabel("List ID: "))
			panel.add(listIdField)

			val result : Int = JOptionPane.showConfirmDialog(null, panel, "Delete new face list", JOptionPane.OK_CANCEL_OPTION)

			if(result == JOptionPane.OK_OPTION){

				JOptionPane.showMessageDialog(frame, DeleteFaceList.deleteFaceList(listIdField.getText()));
			}

		}
	})
}