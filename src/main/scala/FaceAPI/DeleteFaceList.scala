package main.scala.FaceAPI;

//// This sample uses the Apache HTTP client from HTTP Components (http://hc.apache.org/httpcomponents-client-ga/)
import java.net.URI;
import java.io.File

import org.apache.commons.io.FileUtils
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;


object DeleteFaceList {
	def deleteFaceList(listId : String) : String = {
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
	val uriBase : String = "https://westeurope.api.cognitive.microsoft.com/face/v1.0/facelists/" + listId;

		val httpclient : HttpClient = HttpClients.createDefault();

		try {
			val builder : URIBuilder = new URIBuilder(uriBase);

			val uri : URI = builder.build();
			val request : HttpDelete = new HttpDelete(uri);
			request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);

			val response : HttpResponse = httpclient.execute(request);
			val entity : HttpEntity = response.getEntity();

			if (entity != null) {
				val entityString : String = EntityUtils.toString(entity)
				if (entityString.isEmpty()){
					FileUtils.deleteDirectory(new File("FaceLists/" + listId))
					return("List " + listId + " was deleted successfully!")
				}
				else{
					val jsonObject : JSONObject = new JSONObject(entityString)
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
