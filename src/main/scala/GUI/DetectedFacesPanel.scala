package main.scala.GUI

	import java.awt.image.BufferedImage
	import java.awt._
	import javax.imageio.ImageIO
	import javax.swing._
	import java.io.File
	import math._


class DetectedFacesPanel(val picLink : String) extends JPanel{
	setOpaque(false)

	var bimg : BufferedImage = ImageIO.read(new File(picLink))

	override def paintComponent(g: Graphics) = {
		super.paintComponent(g)
		g.setColor(Color.CYAN)
		g.drawRect(round((331 : Float) / bimg.getWidth() * 500), round(((144 : Float) / bimg.getHeight() * 500)), round((60: Float) / bimg.getWidth() * 500), round((60: Float) / bimg.getHeight() * 500))
		// g.drawRect(331, 144, 60, 60)
		// g.drawRect(98, 47, 52, 52)
		// g.drawRect(131, 116, 43, 43)
		// g.drawRect(290, 58, 52, 52)
		// g.drawRect(209, 141, 48, 48)
		// g.drawRect(195, 40, 43, 43)
	}

}