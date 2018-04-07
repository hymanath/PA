package com.itgrids.voterdata.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class ClientMultipartFormPost {

     public static void main(String[] args) throws Exception 
     {

          CloseableHttpClient httpclient = HttpClients.createDefault();
          try {
        	  ImageAndStringConverter converter = new ImageAndStringConverter();
             HttpPost httppost = new HttpPost("http://139.59.3.60:9595/news/");

             FileBody bin = new FileBody(new File("C:\\Users\\Administrator\\Desktop\\dataset.json"));

             HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("temp", bin).build();
             httppost.setEntity(reqEntity);

             System.out.println("executing request " + httppost.getRequestLine());
             CloseableHttpResponse response = httpclient.execute(httppost);
           try {
                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                HttpEntity resEntity = response.getEntity();
                String theString = IOUtils.toString(resEntity.getContent());
                theString = theString.substring(18,theString.length()-2);
                converter.convertBase64StringToImage(theString, "D:/Servers/wordCloud_Flask/4.png");
                BufferedWriter outwriter = new BufferedWriter(new FileWriter("D:/Servers/wordCloud_Flask/4.txt"));
                outwriter.write(theString);
                outwriter.close();
                if (resEntity != null) {
                     System.out.println("Response content length: " +    resEntity.getContentLength());
                }
              EntityUtils.consume(resEntity);
             } finally {
                 response.close();
            }
       } finally {
          httpclient.close();
      }
   }
}
