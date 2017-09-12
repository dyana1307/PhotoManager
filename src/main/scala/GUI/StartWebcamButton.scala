package main.scala.GUI

import java.awt.event.{ActionListener, ActionEvent}
import javax.swing._
import java.awt._
import java.io.File
import main.scala.Resources.Resources

import main.scala.FaceAPI.ListFaceLists

class StartWebcamButton extends JButton{
	setText("Start Webcam")
	addActionListener(new ActionListener(){

		def actionPerformed(e:ActionEvent){

			val panel : JPanel = new JPanel()

			val listOfIds : Array[String] = ListFaceLists.getListOfIds
			val idsBox : JComboBox[String] = new JComboBox(listOfIds)

			panel.add(new JLabel("Face Lists: "))
			panel.add(idsBox)

			val result : Int = JOptionPane.showConfirmDialog(null, panel, "Select face list for recognition", JOptionPane.OK_CANCEL_OPTION)

			if(result == JOptionPane.OK_OPTION){
				Resources.selectedFaceList = idsBox.getSelectedItem().asInstanceOf[String]
				Resources.getCurrentPanel
				Resources.switchPanel(new RecognitionPanel)
				Resources.createNewWebcamThread
				Resources.currentWebcamThread.start
			}
		}
	})
}