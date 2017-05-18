package com.itgrids.partyanalyst.GcmService;

import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.itgrids.partyanalyst.dto.NotificationDeviceVO;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.mysql.jdbc.jdbc2.optional.SuspendableXAConnection;
/*
 * @Srishailm
 * @Swadhin
 */
public class GcmService {
	
	private static final Logger LOG = Logger.getLogger(GcmService.class);
	private static int successcount = 0;
	private static int failurecount = 0;
	JsonParser parser = new JsonParser();
	public NotificationDeviceVO sendNotification(String registeredId,JsonObject notification, List<String> notificationKeysList, Long userId) {

		String result = null;
		final String GOOGLE_SERVER_KEY = IConstants.GOOGLE_SERVER_KEY;
		NotificationDeviceVO notificationsVO = new NotificationDeviceVO();
		try {
			CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
			URL url = new URL(IConstants.FCM_URL.trim());
			JsonObject json = new JsonObject();
			if (commonMethodsUtilService.isListOrSetValid(notificationKeysList)) {
				for (String registereduId : notificationKeysList) {
					json.add("notification", notification);
					json.add("data", notification);
					json.addProperty("to", registereduId);
					json.addProperty("click_action", "OPEN_ACTIVITY");
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setUseCaches(false);
					conn.setDoInput(true);
					conn.setDoOutput(true);
					conn.setRequestMethod("POST");
					conn.setRequestProperty("Authorization", "key="+ GOOGLE_SERVER_KEY);
					conn.setRequestProperty("Content-Type", "application/json");
					OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
					wr.write(json.toString()); 
					wr.flush();
					InputStream is = conn.getInputStream();
					String resultValue = convertStreamToString(is);
					JsonObject jsonResult = parser.parse(resultValue).getAsJsonObject();
					if (jsonResult.get("failure").getAsInt() == 0) {
						LOG.info("success Multicat_id="+jsonResult.get("multicast_id").getAsString());
						result= "SUCCESS";
						successcount++;
					}else{
						result= "FAILURE";
						failurecount++;
					}
					json.remove("to");
					json.remove("notification");
					json.remove("click_action");
					json.remove("data");
				}
				
				notificationsVO.setSuccessCount((long) successcount);
				notificationsVO.setSuccessCount((long) failurecount);
				
			}
		}catch(Exception e){
			LOG.info(e.getMessage());
			result ="FAILURE";
		}
		notificationsVO.setStatus(result);
		return notificationsVO;
	}
	//string converstion from input stream
	static String convertStreamToString(InputStream is) {
	    Scanner s = new Scanner(is).useDelimiter("\\A");
	    return s.hasNext() ? s.next() : "";
	}
}
