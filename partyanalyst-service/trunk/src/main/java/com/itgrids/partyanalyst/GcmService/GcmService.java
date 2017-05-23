package com.itgrids.partyanalyst.GcmService;

import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.itgrids.partyanalyst.dto.NotificationDeviceVO;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

/*
 * @Srishailm
 * @Swadhin
 * @Sanjay
 */
public class GcmService {
	
	private static final Logger LOG = Logger.getLogger(GcmService.class);
	JsonParser parser = new JsonParser();
	public NotificationDeviceVO sendNotification(String registeredId,String youtbeUrl, JsonObject notification, List<String> notificationKeysList,Long userId) {

		String result = null;
		final String GOOGLE_SERVER_KEY = IConstants.GOOGLE_SERVER_KEY;
		NotificationDeviceVO notificationsVO = new NotificationDeviceVO();
		try {
			CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
			URL url = new URL(IConstants.FCM_URL.trim());
			JsonObject json = new JsonObject();
			if (commonMethodsUtilService.isListOrSetValid(notificationKeysList)) {
				int successcount = 0;
				int failurecount = 0;
				json.add("notification", notification);
				notification.addProperty("youtubeLink", youtbeUrl);
				json.add("data", notification);
				for (String registereduId : notificationKeysList) {
					json.addProperty("to", registereduId);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setUseCaches(false);
					conn.setDoInput(true);
					conn.setDoOutput(true);
					conn.setRequestMethod("POST");
					conn.setRequestProperty("Authorization", "key="+ GOOGLE_SERVER_KEY);
					conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
					OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream(),
						     Charset.forName("UTF-8").newEncoder());
					wr.write(json.toString());
					wr.flush();
					InputStream is = conn.getInputStream();
					String resultValue = convertStreamToString(is);
					JsonObject jsonResult = parser.parse(resultValue).getAsJsonObject();
					if (jsonResult.get("failure").getAsInt() == 0) {
						LOG.info("success Multicat_id="+ jsonResult.get("multicast_id").getAsString());
						result= "SUCCESS";
						successcount++;
					}else{
						result= "FAILURE";
						failurecount++;
					}
					json.remove("to");
				}

				notificationsVO.setSuccessCount(Long.valueOf(successcount));
				notificationsVO.setFailureCount(Long.valueOf(failurecount));

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
