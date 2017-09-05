package main.scala.FaceAPI;

//// This sample uses the Apache HTTP client from HTTP Components (http://hc.apache.org/httpcomponents-client-ga/)
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

object AddFaceToFaceList {
	def addFaceToFaceList(listId : String, userData : String, picLink : String) : String = {
	// **********************************************
	// *** Update or verify the following values. ***
	// **********************************************

	// Replace the subscriptionKey string value with your valid subscription
	// key.
	val subscriptionKey : String = "f46eacf7b3e241f792ae9111d2ca95c7";

	// Replace or verify the region.
	//
	// You must use the same region in your REST API call as you used to obtain
	// your subscription keys.
	// For example, if you obtained your subscription keys from the westus
	// region, replace
	// "westcentralus" in the URI below with "westus".
	//
	// NOTE: Free trial subscription keys are generated in the westcentralus
	// region, so if you are using
	// a free trial subscription key, you should not need to change this region.

	val uriBase : String = "https://westeurope.api.cognitive.microsoft.com/face/v1.0/facelists/" + listId + "/persistedFaces";

		val httpclient : HttpClient = HttpClients.createDefault();

		try {
			val builder : URIBuilder = new URIBuilder(uriBase);

			builder.setParameter("userData", userData);

			val uri : URI = builder.build();

			val request : HttpPost = new HttpPost(uri);
			request.setHeader("Content-Type", "application/octet-stream");
			request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);

			// Request body
			val picToByte : Array[Byte] = Files.readAllBytes(Paths.get(picLink));
			val reqEntity : ByteArrayEntity = new ByteArrayEntity(picToByte, ContentType.APPLICATION_OCTET_STREAM);
			request.setEntity(reqEntity);

			val response : HttpResponse = httpclient.execute(request);
			val entity : HttpEntity = response.getEntity();

			if (entity != null) {
				val entityString : String = EntityUtils.toString(entity)
				val jsonObject : JSONObject = new JSONObject(entityString)

				if(jsonObject.has("persistedFaceId")){
					return "The ID of the added face is: " + jsonObject.get("persistedFaceId") + "."
				}
				else{
					return jsonObject.getJSONObject("error").get("message").toString()
				}
				
			}
			return null
		} catch {
            case exception: Exception => {
                // Display error message.
                println(exception.getMessage);
                return null
            }
        }
	}
}