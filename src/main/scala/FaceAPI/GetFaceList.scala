package main.scala.FaceAPI;

//// This sample uses the Apache HTTP client from HTTP Components (http://hc.apache.org/httpcomponents-client-ga/)
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

object GetFaceList {
	def getFaceList(listId : String) : Array[(String,String)] = {
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
			val request : HttpGet = new HttpGet(uri);
			request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);

			val response : HttpResponse = httpclient.execute(request);
			val entity : HttpEntity = response.getEntity();

			if (entity != null) {
				val entityString : String = EntityUtils.toString(entity)
				val jsonObject : JSONObject = new JSONObject(entityString)
				var listsDetails : Array[(String, String)] = Array.empty[(String, String)]
				if(jsonObject.has("persistedFaces")){

					for(i <- 0 to (jsonObject.getJSONArray("persistedFaces").length() - 1)){
						var id : String = jsonObject.getJSONArray("persistedFaces").getJSONObject(i).getString("persistedFaceId")
						var userData = jsonObject.getJSONArray("persistedFaces").getJSONObject(i).get("userData")

						var userDataString : String = userData + ""

						if(userDataString == "null"){
							userDataString = "No user data"
						}

						var listDetails : (String, String) = (id, userDataString)
						listsDetails = listsDetails :+ listDetails
					}
				}
				return listsDetails
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

	def getFaceId(listId : String, userData : String) : String = {
		val listDetails : Array[(String, String)] = getFaceList(listId)

		for(list <- listDetails){
			if(list._2 == userData) return list._1
		}
		return "Id not found"
	}

	def getFaceListFaceIds(listId : String) : Array[String] = {
		val listDetails : Array[(String, String)] = getFaceList(listId)
		var listIds : Array[String] = Array.empty[String]

		for(list <- listDetails){
			listIds = listIds :+ list._1
		}

		return listIds

	}

	def getUserData(listId : String, faceId : String) : String = {
		val listDetails : Array[(String, String)] = getFaceList(listId)

		for(list <- listDetails){
			if(list._1 == faceId) return list._2
		}
		return "Data not found"
	}


	def getListOfUserData(listId : String) : Array[String] = {
		val listDetails : Array[(String, String)] = getFaceList(listId)
		var userDataList : Array[String] = Array.empty[String]

		for(list <- listDetails){
			userDataList = userDataList :+ list._2
		}
		return userDataList
	}
}
