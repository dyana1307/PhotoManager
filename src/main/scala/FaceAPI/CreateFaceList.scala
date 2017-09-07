package main.scala.FaceAPI;

//This sample uses the Apache HTTP client library(org.apache.httpcomponents:httpclient:4.2.4)
//and the org.json library (org.json:json:20170516).

import java.net.URI;
import java.io.File
import java.nio.file.{Path, Paths, Files}

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

object CreateFaceList {
    def createFaceList(listId : String, listName : String, userData : String) : String = {
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
            val request : HttpPut = new HttpPut(uri);
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);

            // Request body
            val reqEntity : StringEntity = new StringEntity(
                    "{\"name\":\"" + listName + "\",\"userData\":\"" + userData + "\"}");
            request.setEntity(reqEntity);

            val response : HttpResponse = httpclient.execute(request);
            val entity : HttpEntity = response.getEntity();

            if (entity != null) {
                val entityString : String = EntityUtils.toString(entity)
                if (entityString.isEmpty()){
                    //println(Files.createDirectory(Paths.get("FaceLists/" + listId), null))
                    var dir : File = new File("FaceLists/" + listId)
                    dir.mkdir()
                    return("List " + listName + " was created successfully!")
                }
                else{
                    val jsonObject : JSONObject = new JSONObject(entityString)
                    return jsonObject.getJSONObject("error").get("message").toString()
                }
            }
            return "Entity was null"
        } catch {
            case exception: Exception => {
                // Display error message.
                println(exception.getMessage);
                return null
            }
        }
    }
}