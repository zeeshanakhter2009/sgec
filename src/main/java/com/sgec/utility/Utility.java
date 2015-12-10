package com.sgec.utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class Utility {

    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String httpGet(String targetURL, String input) {

        StringBuilder builder = new StringBuilder();
        try {
        //   System.out.println("targetURL+input == " + targetURL + input);
            //String encodedUrl = URLEncoder.encode(input, "UTF-8");
//            String encodedUrl = URLEncoder.encode(input, "ISO-8859-1");
            // System.out.println("targetURL+encodedUrl == " + targetURL + "?" + encodedUrl);

            URL restServiceURL = new URL(targetURL + "?" + input);

            HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL
                    .openConnection();
            httpConnection.setRequestMethod("GET");
            httpConnection.setRequestProperty("Accept", "text/plain");

            if (httpConnection.getResponseCode() != 200) {
                throw new RuntimeException(
                        "HTTP GET Request Failed with Error code : "
                        + httpConnection.getResponseCode());
            }
            BufferedReader responseBuffer = new BufferedReader(
                    new InputStreamReader((httpConnection.getInputStream())));

            String output;
         //   System.out.println("Output from Server:  \n");

            while ((output = responseBuffer.readLine()) != null) {
              //  System.out.println(output);
                builder.append(output);
            }
           // System.out.println(":: Response String :: \n" + builder.toString());
            httpConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public static String httpPost(String targetURL, String input) {
        StringBuilder stringBuilder = new StringBuilder();
        try {

            URL targetUrl = new URL(targetURL);
            HttpURLConnection httpConnection = (HttpURLConnection) targetUrl.openConnection();
            httpConnection.setDoOutput(true);
            httpConnection.setRequestMethod("POST");
            httpConnection.setRequestProperty("Content-Type", "application/json");

            //String input = "{\"userAgent\":\"" + userAgent + "\",\"ticket\":\"" + ticket + "\",\"sqId\":\"" + sqId + "\"}";
            OutputStream outputStream = httpConnection.getOutputStream();
            outputStream.write(input.getBytes());
            outputStream.flush();

            if (httpConnection.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + httpConnection.getResponseCode());
            }
            BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(
                    (httpConnection.getInputStream())));
            String output;
          //  System.out.println("Output from Server:\n");
            while ((output = responseBuffer.readLine()) != null) {
               // System.out.println(output);
                stringBuilder.append(output);
            }

            httpConnection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    int randomNumberGenerator() {
        int NUMBER_RANGE = 99999;
        Random random = new Random();
        return random.nextInt(NUMBER_RANGE);
    }

//    public static void main(String[] args) throws NoSuchAlgorithmException {
//        System.out.println(getMD5("1_100"));
//        Date date = new Date();
//        System.out.println("1400567676357 Today is " + (date.getTime() + 1400567676357l));
//        String targetURL = "http://192.168.10.197:8085/wowza/rest/emp/getStreamingUrl";
//
//    }
    
    
      
}
