package org.wso2.carbon.identity.scim.provider.resources;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;
import org.wso2.carbon.identity.scim.provider.impl.IdentitySCIMManager;
import org.wso2.carbon.identity.scim.provider.util.JAXRSResponseBuilder;
import org.wso2.charon.core.config.SCIMConfigConstants;
import org.wso2.charon.core.encoder.Encoder;
import org.wso2.charon.core.encoder.json.JSONEncoder;
import org.wso2.charon.core.exceptions.BadRequestException;
import org.wso2.charon.core.exceptions.CharonException;
import org.wso2.charon.core.exceptions.FormatNotSupportedException;
import org.wso2.charon.core.extensions.UserManager;
import org.wso2.charon.core.objects.AbstractSCIMObject;
import org.wso2.charon.core.objects.SCIMObject;
import org.wso2.charon.core.protocol.ResponseCodeConstants;
import org.wso2.charon.core.protocol.SCIMResponse;
import org.wso2.charon.core.protocol.endpoints.AbstractResourceEndpoint;
import org.wso2.charon.core.protocol.endpoints.UserResourceEndpoint;
import org.wso2.charon.core.schema.SCIMConstants;
import org.wso2.charon.core.util.CopyUtil;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by User on 3/27/2017.
 */
public class SPConfig extends AbstractResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@HeaderParam(SCIMConstants.ACCEPT_HEADER) String format,
                            @HeaderParam(SCIMConstants.AUTHORIZATION_HEADER) String authorization) {

        //obtain the json encoder
        JSONEncoder encoder = new JSONEncoder();

        JSONObject rootObject = new JSONObject();

        try {
            JSONObject bulkObject = new JSONObject();
            bulkObject.put("supported", true);
            bulkObject.put("maxOperations", 1000);
            bulkObject.put("maxPayloadSize", 1048576);

            JSONObject filterObject = new JSONObject();
            filterObject.put("supported", true);
            filterObject.put("maxResults", 200);


            JSONObject patchObject = new JSONObject();
            patchObject.put("supported", true);

            JSONObject xmlDataFormat = new JSONObject();
            xmlDataFormat.put("supported", false);

            JSONObject sortObject = new JSONObject();
            sortObject.put("supported", false);

            JSONObject etagObject = new JSONObject();
            etagObject.put("supported",false);

            JSONObject changePasswordObject = new JSONObject();
            changePasswordObject.put("supported", true);

            JSONArray authenticationSchemesArray = new JSONArray();

            JSONObject authenticationSchemeObject = new JSONObject();
            authenticationSchemeObject.put("name", "HTTP Basic");
            authenticationSchemeObject.put("description", "Authentication Scheme using the Http Basic Standard");
            authenticationSchemeObject.put("specURL","http://www.ietf.org/rfc/rfc2617.txt");
            authenticationSchemeObject.put("documentationUrl", "http://example.com/help/httpBasic.html");
            authenticationSchemeObject.put("type","httpbasic");

            authenticationSchemesArray.put(authenticationSchemeObject);


            rootObject.put("schemas", new JSONArray().put("urn:scim:schemas:core:1.0"));
            rootObject.put("documentationUrl", "http://example.com/help/scim.html");
            rootObject.put("bulk", bulkObject);
            rootObject.put("filter", filterObject);
            rootObject.put("patch", patchObject);
            rootObject.put("changePassword",changePasswordObject);
            rootObject.put("sort", sortObject);
            rootObject.put("etag", etagObject);
            rootObject.put("xmlDataFormat", xmlDataFormat);
            rootObject.put("authenticationSchemes", authenticationSchemesArray);


            Map<String, String> responseHeaders = new HashMap<String, String>();

            //add location header
            responseHeaders.put(SCIMConstants.LOCATION_HEADER, "location to bve added here");
            responseHeaders.put(SCIMConstants.CONTENT_TYPE_HEADER, SCIMConstants.APPLICATION_JSON);

            //put the uri of the service provider config object in the response header parameter.
            SCIMResponse srp = new SCIMResponse(ResponseCodeConstants.CODE_OK, rootObject.toString(), responseHeaders);
            return new JAXRSResponseBuilder().buildResponse(srp);

        } catch (JSONException e) {
            e.printStackTrace();
            return  null;
        }
    }

}
