package main.scala.Resources

import java.io.File
import java.awt._
import javax.swing._

object Resources{
	var filePaths : Array[File] = Array.empty[File]

	def addToFilePaths (newFile : File) : Array[File] = {
		filePaths = filePaths :+ newFile
		return filePaths
	}
}