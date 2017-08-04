package main.scala.GUI


import main.scala.Resources.Resources
	import java.awt.image.BufferedImage
	import java.awt._
	import javax.imageio.ImageIO
	import javax.swing._
	import java.io.File
	import math._
	import main.scala.FaceAPI.FaceDetection


class DetectedFacesPanel(val picLink : String) extends JPanel{
	setOpaque(false)
	setPreferredSize(new Dimension(Resources.frameWidth, Resources.frameHeight))

	var bimg : BufferedImage = ImageIO.read(new File(picLink))

	var facesArray : Array[(Int, Int, Int, Int)] = FaceDetection.faceDetect(picLink)

	override def paintComponent(g: Graphics) = {
		super.paintComponent(g)
		g.setColor(Color.CYAN)
		for (face <- facesArray){
			g.drawRect(round(face._2.toFloat / bimg.getWidth() * Resources.frameWidth), round(face._1.toFloat / bimg.getHeight() * Resources.frameHeight), round(face._3.toFloat / bimg.getWidth() * Resources.frameWidth), round(face._4.toFloat / bimg.getHeight() * Resources.frameHeight))
		}
	}

}