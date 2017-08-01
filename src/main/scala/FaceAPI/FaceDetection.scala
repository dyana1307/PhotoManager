// This sample uses the Apache HTTP client library(org.apache.httpcomponents:httpclient:4.2.4)
// and the org.json library (org.json:json:20170516).

  package main.scala.test

  import swing._
  import javax.swing.{JLayeredPane, JPanel, JLabel, ImageIcon}
  import java.awt.{Image, Graphics, Color, Rectangle}
  
  object FaceDetection{
  def main(args: Array[String]) : Unit = {
    var test  = new FaceDetectionClass("http://www.abta.org/assets/images/treatments/support-group-image-resize.png");
    println(test.faceDetect);
    var panel = new PanelDemo;
    var frame = new MainFrame{
    	title = "Test"
    	contents = Component.wrap(panel)
    }
    frame.visible = true
  }
}

	class RectPanel(val picLink : String) extends JPanel{

	import java.awt.image.BufferedImage
	import javax.imageio.ImageIO
	import java.io.File
	import math._

	setOpaque(false)
	var bimg : BufferedImage = ImageIO.read(new File(picLink))
	println("Width: " + bimg.getWidth() + " Height: " + bimg.getHeight() + "Percentage width: " + (round((141 : Float) / bimg.getWidth() * 500)) + "Percentage Height: " + round(((209 : Float) / bimg.getHeight() * 500)))
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

	class PicPanel(val picLink : String) extends JPanel{
		var label: JLabel = new JLabel(new ImageIcon(new ImageIcon(picLink).getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH)))
		add(label)
	}
  

  class PanelDemo extends JLayeredPane {
  	setPreferredSize(new Dimension(500, 500))
  	val picLink : String = "Pics/test.png"
  	var panel1 : PicPanel = new PicPanel(picLink)
  	var panel2 : RectPanel = new RectPanel(picLink)
  	panel1.setBounds(0, 0, 500, 500)
  	panel2.setBounds(0, 0, 500, 500)
	add(panel1, new Integer(1))
	add(panel2, new Integer(2))

  }

  class FaceDetectionClass(val picLink : String){
    
    def faceDetect : String = {

    import java.net.URI
    import org.apache.http.HttpEntity
    import org.apache.http.HttpResponse
    import org.apache.http.client.HttpClient
    import org.apache.http.client.methods.HttpPost
    import org.apache.http.entity.StringEntity
    import org.apache.http.impl.client.DefaultHttpClient
    import org.apache.http.client.utils.URIBuilder
    import org.apache.http.util.EntityUtils
    import org.json.JSONArray
    import org.json.JSONObject
    // **********************************************
    // *** Update or verify the following values. ***
    // **********************************************

    // Replace the subscriptionKey string value with your valid subscription key.
    val subscriptionKey: String = "f46eacf7b3e241f792ae9111d2ca95c7";

    // Replace or verify the region.
    //
    // You must use the same region in your REST API call as you used to obtain your subscription keys.
    // For example, if you obtained your subscription keys from the westus region, replace
    // "westcentralus" in the URI below with "westus".
    //
    // NOTE: Free trial subscription keys are generated in the westcentralus region, so if you are using
    // a free trial subscription key, you should not need to change this region.
    val uriBase: String = "https://westeurope.api.cognitive.microsoft.com/face/v1.0/detect";

    val httpclient: HttpClient = new DefaultHttpClient;
    try {
      var builder: URIBuilder = new URIBuilder(uriBase);

      // Request parameters. All of them are optional.
      builder.setParameter("returnFaceId", "true");
      //builder.setParameter("returnFaceLandmarks", "false");
      //builder.setParameter("returnFaceAttributes", "age,gender,headPose,smile,facialHair,glasses,emotion,hair,makeup,occlusion,accessories,blur,exposure,noise");

      // Prepare the URI for the REST API call.
      var uri: URI = builder.build();
      var request: HttpPost = new HttpPost(uri);

      // Request headers.
      request.setHeader("Content-Type", "application/json");
      request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);

      // Request body.
      val reqEntity = new StringEntity("{\"url\":\"" + picLink + "\"}");
      request.setEntity(reqEntity);

      // Execute the REST API call and get the response entity.
      var response: HttpResponse = httpclient.execute(request);
      var entity: HttpEntity = response.getEntity();

      if (entity != null) {
        // Format and display the JSON response.

        var response: String = "REST Response:\n";
        var jsonString: String = EntityUtils.toString(entity).trim();

        if (jsonString.charAt(0) == '[') {
          var jsonArray: JSONArray = new JSONArray(jsonString);
          return response + jsonArray.toString(2);
        } else if (jsonString.charAt(0) == '{') {
          var jsonObject: JSONObject = new JSONObject(jsonString);
          return response + jsonObject.toString(2);
        } else {
          return response + jsonString;
        }
      }
      return "Null entity";
    } catch {
      case exception: Exception => {
        // Display error message.
        return exception.getMessage;
      }
    }
  }
}