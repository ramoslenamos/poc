package com.example.demo;

import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class EudoNetAPI {
  String token;

  public EudoNetAPI() throws ProtocolException {
    this.token = "Ns76s0JCf6VPKt73aoLzwzboWI7ZPoxT8wzzsVO0DYJiHzli4DJUCpjGdk8jR0tcHJLA0S3JvfQUR+asiJo8Sl4Ux4MG8Um4KWuLQm8P8/RV266rrVL+KgU56XbMQLiWXFCHtkpUQlZxfFcqABrPzbdBXOB1TAxhb0zafdtuHc2IytYpEegV/7zLr6dCjNLIScio4rIjACmZ4xKHWDOMO9/HpX8TCTlKIZD1Et3oH0x8dOOFo4QCadtK4dm2iwTZFziWaPiR7rI/+xEuZq1rz/dytiDrUpnfytaNv8YZo/BaVXOyyghIB1pYl2CS2MJw7TtMZDVf4X1J+MziS8cPsQ22+Ml3GC8JCnbCiA==";
  }

  public JSONObject getAllPersons() {
    JSONObject response = new JSONObject();
    try {
      URL url = new URL("http://xrm3.eudonet.com/EudoAPI/Search/200");
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestProperty("Content-Type", "application/json");
      connection.addRequestProperty("Authorization", "Basic " + token);
      OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
      osw.write("{\n" +
              " \"ShowMetadata\": true,\n" +
              " \"RowsPerPage\": 50,\n" +
              " \"NumPage\": 10,\n" +
              " \"ListCols\": [\n" +
              "   201, 202, 209\n" +
              " ],\n" +
              " \"WhereCustom\": {\n" +
              "   \"WhereCustoms\": [\n" +
              "     {}\n" +
              "   ],\n" +
              "   \"Criteria\": {\n" +
              "     \"Operator\": 0,\n" +
              "     \"Field\": \"209\",\n" +
              "   },\n" +
              "   \"InterOperator\": 0\n" +
              " },\n" +
              "}");
      response = JsonHelper.readJsonInputStream(connection.getInputStream());
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return response;
  }
}
