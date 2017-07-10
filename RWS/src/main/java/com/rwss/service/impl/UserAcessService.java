package com.rwss.service.impl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.rwss.service.IUserAcessService;
import com.rwss.utils.IConstants;


@Service
public class UserAcessService implements IUserAcessService {

@Autowired
private Environment environment;

	@Override
	public String updateuserPropertyData(String credential, String actionType) {
		
		try {
			List<String> users = new ArrayList<String>();
			FileOutputStream out = new FileOutputStream("classpath:application.properties");
			FileInputStream in = new FileInputStream("classpath:application.properties");
			Properties props = new Properties();
			props.load(in);
			in.close();
			String userString = "";
			String usersData = environment.getProperty("users");
			if (actionType.equalsIgnoreCase(IConstants.ACTION_INSERT)) {
				users.addAll(Arrays.asList(usersData.split(",")));
				users.add(credential.trim());
				for (int i = 0; i < users.size(); i++) {
					if (i == 0) {
						userString = users.get(i);
					} else {
						userString = userString + "," + users.get(i);
					}

				}
				props.setProperty("users", userString);
				props.store(out, null);
				out.close();
			} else if (actionType.equalsIgnoreCase(IConstants.ACTION_UPDATE)) {

			} else if (actionType.equalsIgnoreCase(IConstants.ACTION_DELETE)) {

			}
			return "Inserted SucessFully";
		} catch (Exception e) {
			return e.getLocalizedMessage();
		}
	}
	
	
	
}
