package main.scala.GUI

import java.awt._
import java.awt.event.{ActionListener, ActionEvent}
import javax.swing._
import java.io.File

import main.scala.Resources.Resources
import main.scala.FaceAPI._

class RecognitionPanel extends JPanel{
	setLayout(new BorderLayout)
	
	add(new RecognitionButtonPanel, BorderLayout.EAST)
	add(new FeedbackPanel, BorderLayout.CENTER)
}

class FeedbackPanel extends JPanel{
	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS))

	var i : Int = 1

	var thread : Thread = new Thread {
		override def run {
			while(!Resources.webcamHasStopped){
					if(new File("Pics\\" + i + ".jpg").exists()){
					Thread.sleep(100)
					var facesInPic : Array[String] = FaceDetection.faceDetection("Pics\\" + i + ".jpg")

					for(x <- 0 to (facesInPic.length - 1)){
						var faceRec : (String, Double) = FaceRecognition.faceRecognition(facesInPic(x), Resources.selectedFaceList)
						add(new FaceRecLabel(GetFaceList.getUserData(Resources.selectedFaceList, faceRec._1), faceRec._2))
						repaint()
						revalidate()
					}
			
					i += 1
				}

			}	
		}
	}

	thread.start

}

class FaceRecLabel(studentId : String, conf : Double) extends JLabel{
	setText("Student ID:\t" + studentId + "\t\tConfidence:\t" + conf)
	setHorizontalAlignment(SwingConstants.CENTER)
	setFont(new Font(this.getFont().getName(), Font.PLAIN, 12))
}

class RecognitionButtonPanel extends JPanel{
	var glayout : GridLayout = new GridLayout(3, 0)
	setLayout(glayout)
	setPreferredSize(new Dimension(200, 300))
	val stopButton : JButton = new JButton("Stop Webcam")
	stopButton.addActionListener(new ActionListener(){
		def actionPerformed(e:ActionEvent){
			Resources.webcamApp.stop()
		}
	})

	add(stopButton)
	add(new BackButton)
	add(new ExitButton)
}