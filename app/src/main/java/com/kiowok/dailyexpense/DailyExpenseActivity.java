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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DailyExpenseActivity extends Activity {
    String curDate = "1999-01";

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

    @Override
    protected void onStart() {
        super.onStart();

        Calendar cal = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        curDate = df.format(cal.getTime());

        BackgroundDBTask backgroundDBTask = new BackgroundDBTask(this);
        backgroundDBTask.execute("getTotals", curDate);
        month.setText(convertDayYear(curDate));
    }

    public void onAddGroceries(View view) {
        Calendar cal = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = df.format(cal.getTime());

        String amount = editGroceries.getText().toString().trim();
        BackgroundDBTask backgroundDBTask = new BackgroundDBTask(this);
        backgroundDBTask.execute("add", "groceries", amount, date);
        editGroceries.setText("");
    }

    public void onAddDining(View view) {
        Calendar cal = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = df.format(cal.getTime());

        String amount = editDining.getText().toString().trim();
        BackgroundDBTask backgroundDBTask = new BackgroundDBTask(this);
        backgroundDBTask.execute("add", "dining", amount, date);
        editDining.setText("");
    }

    private class BackgroundDBTask extends AsyncTask<String, Void, String> {
        Context context;
        AlertDialog alertDialog;

        BackgroundDBTask (Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... param) {
            String result = "";
            String action = param[0];
            String urlBasic = "http://kiowok.com/trc/basic.php";
            if (action.equals("add")) {
                String cat = param[1];
                String amount = param[2];
                String date = param[3];

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
                                    URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode(date, "UTF-8") + "&" +
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
            else if (action.equals("getTotals")) {
                String date = param[1];
                try {
                    URL url = new URL(urlBasic);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String urlData = URLEncoder.encode("action", "UTF-8") + "=" + URLEncoder.encode(action, "UTF-8") + "&" +
                            URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode(date, "UTF-8");
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
            String [] totals = result.split("#");

            groceriesTotal.setText(
                    String.format("%.2f", Double.parseDouble(totals[0]))
            );
            diningTotal.setText(
                    String.format("%.2f", Double.parseDouble(totals[1]))
            );
        }

        @Override
        protected void onPreExecute() {
            alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle("Response");
        }
    }

    private String convertDayYear(String in) {
        String [] month = {"", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        String [] tokens = in.split("-");
        int index = Integer.parseInt(tokens[1]);

        return month[index] + " " + tokens[0];
    }
}

/*
At kiowok.com/trc/conn.php:

<?php
$db_name = "alan_trc";
$mysql_username = "alan_trc";
$mysql_password = "oneONE2";
$server_name = "kiowok.com";
$conn=mysqli_connect(localhost, $mysql_username, $mysql_password, $db_name);
?>


At kiowok.com/trc/basic.php:

<?php
require "conn.php";

$action = $_POST["action"];
$date = $_POST["date"];
$cat = $_POST["category"];
$amount = $_POST["amount"];
$yearMonth = substr($date, 0, 7);

if ($action == "add") {
    $mysql_qry = "insert into expenses (date, category, amount) values ('$date', '$cat', $amount);";
    $result = mysqli_query($conn ,$mysql_qry);

    $mysql_qry = "SELECT SUM(amount) AS sum FROM expenses WHERE date LIKE '$yearMonth%' AND category = 'groceries';";
    $result = mysqli_query($conn ,$mysql_qry);
    $row = $result->fetch_assoc();
    $sum = $row['sum'];

    $mysql_qry = "SELECT SUM(amount) AS sum FROM expenses WHERE date LIKE '$yearMonth%' AND category = 'dining';";
    $result = mysqli_query($conn ,$mysql_qry);
    $row = $result->fetch_assoc();
    $sum = $sum . "#" . $row['sum'];

    echo $sum;
}
else if ($action == "getTotals") {
    $mysql_qry = "SELECT SUM(amount) AS sum FROM expenses WHERE date LIKE '$yearMonth%' AND category = 'groceries';";
    $result = mysqli_query($conn ,$mysql_qry);
    $row = $result->fetch_assoc();
    $sum = $row['sum'];

    $mysql_qry = "SELECT SUM(amount) AS sum FROM expenses WHERE date LIKE '$yearMonth%' AND category = 'dining';";
    $result = mysqli_query($conn ,$mysql_qry);
    $row = $result->fetch_assoc();
    $sum = $sum . "#" . $row['sum'];

    echo $sum;
}
else if ($action == "all") {
    echo "top of all...";
    $mysql_qry = "select * from expenses;";
    $result = mysqli_query($conn ,$mysql_qry);
    echo "RESULT: " + $result;
    if (mysqli_num_rows($result) > 0) {
        while($row = $result->fetch_assoc()) {
            echo "id: " . $row["id"]. " - category: " . $row["category"]. " - amount: " . $row["amount"]. "<br>";
        }
    }
    else {
        echo "Not successful...";
    }
}
?>
 */
