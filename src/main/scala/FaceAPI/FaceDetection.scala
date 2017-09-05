// This sample uses the Apache HTTP client library(org.apache.httpcomponents:httpclient:4.2.4)
// and the org.json library (org.json:json:20170516).

package main.scala.FaceAPI

import main.scala.Resources.Resources

import swing._
import javax.swing._
import java.awt._
import java.io.File

import java.net.URI
import org.apache.http.HttpEntity
import org.apache.http.HttpResponse
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.{ByteArrayEntity, ContentType}
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.client.utils.URIBuilder
import org.apache.http.util.EntityUtils
import org.json.JSONArray
import org.json.JSONObject

object FaceDetection{

	def faceDetection(picLink : String) : Array[String] = {

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
			request.setHeader("Content-Type", "application/octet-stream");
			request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);

			// Request body.
			import java.nio.file.{Files, Paths}
			var picToByte : Array[Byte] = Files.readAllBytes(Paths.get(picLink))
			var reqEntity = new ByteArrayEntity(picToByte, ContentType.APPLICATION_OCTET_STREAM)
			request.setEntity(reqEntity);

			// Execute the REST API call and get the response entity.
			var response: HttpResponse = httpclient.execute(request);
			var entity: HttpEntity = response.getEntity();

			if (entity != null) {
				// Retrieve the face detection details 
				var entityString: String = EntityUtils.toString(entity);
				println(entityString)
				var jsonArray: JSONArray = new JSONArray(entityString);
				var faceIds : Array[String] = Array.empty[String] 
				for (i <- 0 to (jsonArray.length - 1)){
					var faceId : String = jsonArray.getJSONObject(i).getString("faceId")
					faceIds = faceIds :+ faceId
				}
				return faceIds;
			}
			return null;
		} catch {
			case exception: Exception => {
				// Display error message.
				println(exception.getMessage);
				return null
			}
		}
	}
}