package main.scala.GUI

import java.awt._
import java.awt.event.{ActionListener, ActionEvent}
import javax.swing._

import main.scala.Resources.Resources

class RecognitionPanel extends JPanel{
	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS))
	setSize(new Dimension(200, 480))
	val stopButton : JButton = new JButton("Stop")
	stopButton.addActionListener(new ActionListener(){
		def actionPerformed(e:ActionEvent){
			Resources.webcamApp.stop()
		}
	})
	add(stopButton)
	add(new BackButton)
	add(new ExitButton)


}