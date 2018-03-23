package com.example.demo.EudoNet;

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
    this.token = "0xXinZlJxF6nF/sGHKE76tyPdPqCQsCGH7ZirCu1oAq7AQaULCYfNd2iJj7smJTV6lYrHyWsJqYV1KVbEy32wycc9zFJ2h/ZVIqIqbdObSjl1wcFtJYKQG3YF1j0wh0BA1UuJ7+owIhqKt/sjW5KNnXE9UjCVM4SIVbF1QICrxIlaSwp4dyvelNUkV75YbaUpu2UffW6kipznwXozjGRBT3o1up/pbLO0K99k6LcVNAzpnSBqnPfYjh6Bc0SL0eXXLMDqs7rwdV+UxL6zL/Nttzs6KX36qx/x4DofG3UYDDHaMqYTY0FFxyVSIAlOCG2L/mN06JMFWIKvNpygjjmCmkepIHvnYbOpdIdjA==";
  }


  public JSONObject getAllPersons() {
    JSONObject response = new JSONObject();
    try {
      URL url = new URL("http://xrm3.eudonet.com/EudoAPI/Search/200");

      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestProperty("content-type", "application/json");
      connection.addRequestProperty("x-auth", token);
      connection.setRequestMethod("POST");
      connection.setDoOutput(true);
      connection.setDoInput(true);
      connection.setConnectTimeout(5000);
      OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream(),"UTF-8");
      String json = "{\n" +
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
              "     \"Field\": \"209\"\n" +
              "   },\n" +
              "   \"InterOperator\": \"0\"\n" +
              " }\n" +
              "}";
      osw.write(json);
      System.out.println(json);
      response = JsonHelper.readJsonInputStream(connection.getInputStream());
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return response;
  }
}
