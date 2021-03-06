package main.scala.GUI

import org.bytedeco.javacpp.helper.opencv_core.AbstractCvScalar
import org.bytedeco.javacpp.opencv_core._
import org.bytedeco.javacpp.opencv_objdetect.CascadeClassifier
import org.bytedeco.javacpp.{opencv_imgproc, opencv_core}
import org.bytedeco.javacv.FrameGrabber.ImageMode
import org.bytedeco.javacv.{OpenCVFrameGrabber, CanvasFrame}
import org.bytedeco.javacpp.opencv_highgui
import scala.collection.mutable
import util.control.Breaks._
import java.io.File
import java.awt._
import javax.swing._
import main.scala.Resources.Resources

/**
 * Created by chimpler on 7/13/14.
 */
class FaceWebcamDetectorApp{

  // holder for a single detected face: contains face rectangle`
  case class Face(id: Int, faceRect: Rect)

  // we need to clone the rect because openCV is recycling rectangles created by the detectMultiScale method
  private def cloneRect(rect: Rect) : Rect = {
    new Rect(rect.x, rect.y, rect.width, rect.height)
  }

  class FaceDetector() {
    // read the haar classifier xml files for face, left eye and right eye
    val faceXmlPath : File = new File("target/scala-2.10/classes/haarcascade_frontalface_default.xml")
    val faceXml = faceXmlPath.getAbsolutePath()
    val windowsPath : String = faceXml.replace("/", "\\\\").substring(2, faceXml.length + "/".r.findAllMatchIn(faceXml).length)
    val faceCascade = new CascadeClassifier(windowsPath)

    def detect(greyMat: Mat): Seq[Face] = {
      val faceRects = new Rect()
      faceCascade.detectMultiScale(greyMat, faceRects, 1.05, 6, 0, new Size(100, 100), new Size(500, 500))
      for(i <- 0 until faceRects.limit()) yield {
        val faceRect = faceRects.position(i)
        Face(i, cloneRect(faceRect))
      }
    }
  }

  def start(){
    val canvas = new CanvasFrame("Webcam")
    canvas.setCanvasSize(840,480)
    

    val faceDetector = new FaceDetector
    //  //Set Canvas frame to close on exit
    canvas.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE)

    //Declare FrameGrabber to import output from webcam
    val grabber = new OpenCVFrameGrabber(0)
    grabber.setImageWidth(640)
    grabber.setImageHeight(480)
    grabber.setBitsPerPixel(CV_8U)
    grabber.setImageMode(ImageMode.COLOR)
    grabber.start()

    var lastRecognitionTime = 0L
    var lastSaveTime = 0L
    val cvFont = new CvFont()
    cvFont.hscale(0.6f)
    cvFont.vscale(0.6f)
    cvFont.font_face(FONT_HERSHEY_SIMPLEX)

    val mat = new Mat(640, 480, CV_8UC3)
    val greyMat = new Mat(640, 480, CV_8U)
    var faces: Seq[Face] = Nil
    var i : Int = 1

      while (!Resources.webcamHasStopped) {

       val img = grabber.grab()
       cvFlip(img, img, 1)

        // run the recognition every 200ms to not use too much CPU
        if (System.currentTimeMillis() - lastRecognitionTime > 200) {
          mat.copyFrom(img.getBufferedImage)
          opencv_imgproc.cvtColor(mat, greyMat, opencv_imgproc.CV_BGR2GRAY, 1)
          opencv_imgproc.equalizeHist(greyMat, greyMat)
          faces = faceDetector.detect(greyMat)
          lastRecognitionTime = System.currentTimeMillis()

        }

        if(System.currentTimeMillis() - lastSaveTime > 5000 && !faces.isEmpty){
          mat.copyFrom(img.getBufferedImage)
          opencv_imgproc.cvtColor(mat, greyMat, opencv_imgproc.CV_BGR2GRAY, 1)
          opencv_imgproc.equalizeHist(greyMat, greyMat)
          opencv_highgui.imwrite("Pics\\" + i + ".jpg", mat)
          i += 1
          lastSaveTime = System.currentTimeMillis()
        }

        // draw the face rectangles with the caption
        for(f <- faces) {
          // draw the face rectangle
          cvRectangle(img,
            opencv_core.cvPoint(f.faceRect.x, f.faceRect.y),
            opencv_core.cvPoint(f.faceRect.x + f.faceRect.width, f.faceRect.y + f.faceRect.height), AbstractCvScalar.RED, 1, CV_AA, 0)
        }
        canvas.showImage(img)
      }

      if(Resources.webcamHasStopped){
        canvas.dispose()
        Resources.currentWebcamThread.interrupt
        Resources.webcamHasStopped = false
      }
  }

  def stop(){
    Resources.webcamHasStopped = true

  }
  

}
