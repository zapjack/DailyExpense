package com.kiowok.dailyexpense;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class DailyExpenseActivity extends Activity {
    TextView groceriesTotal, diningTotal, month;
    Button addGroceries, addDining;
    EditText editGroceries, editDining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_expense);
        groceriesTotal = (TextView) findViewById(R.id.groceriesTotal);
        diningTotal = (TextView) findViewById(R.id.diningTotal);
        month = (TextView) findViewById(R.id.month);
        addGroceries = (Button) findViewById(R.id.addGroceries);
        addDining = (Button) findViewById(R.id.addDining);
        editDining = (EditText) findViewById(R.id.editDining);
        editGroceries = (EditText) findViewById(R.id.editGroceries);
    }

    public void onQuery(View view) {
        BackgroundDBTask backgroundDBTask = new BackgroundDBTask(this);
        backgroundDBTask.execute("query");
    }

    public void onAddGroceries(View view) {
        String amount = editGroceries.getText().toString().trim();
        BackgroundDBTask backgroundDBTask = new BackgroundDBTask(this);
        backgroundDBTask.execute("add", "groceries", amount);
    }

    public void onAddDining(View view) {

    }

    private class BackgroundDBTask extends AsyncTask<String, Void, String> {
        Context context;
        AlertDialog alertDialog;

        BackgroundDBTask (Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... param) {
            String result = ""; // "Starting Response\n";
            String action = param[0];
            String urlBasic = "http://kiowok.com/trc/basic.php";
            if (action.equals("add")) {
                String cat = param[1];
                String amount = param[2];

                action = "sumGroceries";

                try {
                    URL url = new URL(urlBasic);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String urlData =
                            URLEncoder.encode("action", "UTF-8") + "=" + URLEncoder.encode(action, "UTF-8") + "&" +
                                    URLEncoder.encode("category", "UTF-8") + "=" + URLEncoder.encode(cat, "UTF-8") + "&" +
                                    URLEncoder.encode("amount", "UTF-8") + "=" + URLEncoder.encode(amount, "UTF-8");
                    bufferedWriter.write(urlData);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                    String line;

                    while ((line = bufferedReader.readLine()) != null) {
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;

                } catch (MalformedURLException e) {
                    result = e.toString();
                } catch (IOException e) {
                    result = e.toString();
                }
            }
            else if (action.equals("add")) {
                String cat = param[1];
                String amount = param[2];

                try {
                    URL url = new URL(urlBasic);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String urlData =
                            URLEncoder.encode("action", "UTF-8") + "=" + URLEncoder.encode(action, "UTF-8") + "&" +
                                    URLEncoder.encode("category", "UTF-8") + "=" + URLEncoder.encode(cat, "UTF-8") + "&" +
                                    URLEncoder.encode("amount", "UTF-8") + "=" + URLEncoder.encode(amount, "UTF-8");
                    bufferedWriter.write(urlData);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                    String line;

                    while ((line = bufferedReader.readLine()) != null) {
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;

                } catch (MalformedURLException e) {
                    result = e.toString();
                } catch (IOException e) {
                    result = e.toString();
                }
            }
            else if (action.equals("query")) {
                try {
                    URL url = new URL(urlBasic);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String urlData = URLEncoder.encode("want", "UTF-8") + "=" + URLEncoder.encode("UTF-8");
                    bufferedWriter.write(urlData);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                    String line;

                    while ((line = bufferedReader.readLine()) != null) {
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;

                } catch (MalformedURLException e) {
                    result = e.toString();
                } catch (IOException e) {
                    result = e.toString();
                }
            }
            else if (action.equals("query")) {
                try {
                    URL url = new URL(urlBasic);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String urlData = URLEncoder.encode("want", "UTF-8") + "=" + URLEncoder.encode("UTF-8");
                    bufferedWriter.write(urlData);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                    String line;

                    while ((line = bufferedReader.readLine()) != null) {
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;

                } catch (MalformedURLException e) {
                    result = e.toString();
                } catch (IOException e) {
                    result = e.toString();
                }
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            groceriesTotal.setText(result);
            // alertDialog.setMessage(result);
            // alertDialog.show();
        }

        @Override
        protected void onPreExecute() {
            alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle("Response");
        }
    }
}
