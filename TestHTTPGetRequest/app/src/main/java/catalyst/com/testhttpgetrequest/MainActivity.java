package catalyst.com.testhttpgetrequest;

import android.app.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

//import cz.msebera.android.httpclient.client.HttpClient;
//import cz.msebera.android.httpclient.client.ResponseHandler;
//import cz.msebera.android.httpclient.client.methods.HttpGet;
//import cz.msebera.android.httpclient.impl.client.BasicResponseHandler;
//import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

public class MainActivity extends Activity {

    TextView name,emailid,loginid,password;
    EditText fname,email,login,pass;
    Button saveme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name   =  (TextView)findViewById(R.id.textName);
        emailid   =  (TextView)findViewById(R.id.textEmail);
        loginid   =  (TextView)findViewById(R.id.textLogin);
        password   =  (TextView)findViewById(R.id.textPassword);

        fname     =  (EditText)findViewById(R.id.name);
        email      =  (EditText)findViewById(R.id.email);
        login       =  (EditText)findViewById(R.id.loginname);
        pass       =  (EditText)findViewById(R.id.password);

      saveme=(Button)findViewById(R.id.save);


        saveme.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                //ALERT MESSAGE
                Toast.makeText(getBaseContext(), "Please wait, connecting to server.", Toast.LENGTH_LONG).show();

                try {

                    // URLEncode user defined data

                    String loginValue = URLEncoder.encode(login.getText().toString(), "UTF-8");
                    String fnameValue = URLEncoder.encode(fname.getText().toString(), "UTF-8");
                    String emailValue = URLEncoder.encode(email.getText().toString(), "UTF-8");
                    String passValue = URLEncoder.encode(pass.getText().toString(), "UTF-8");

                    // Create http client object to send request to server

                    HttpClient Client = new DefaultHttpClient();

                    // Create URL string

                    String URL = "http://developer.codeniques.com/testfile.php?user=" + loginValue + "&name=" + fnameValue + "&email=" + emailValue + "&pass=" + passValue;

                    Log.i("httpget", URL);

                    try {
                        String SetServerString = "";

                        // Create Request to server and get response

                        HttpGet httpget = new HttpGet(URL);
                        ResponseHandler<String> responseHandler = new BasicResponseHandler();
                        SetServerString = Client.execute(httpget, responseHandler);

                        // Show response on activity

                        name.setText(SetServerString);
                        emailid.setText(SetServerString);
                        loginid.setText(SetServerString);
                        password.setText(SetServerString);
                    } catch (Exception ex) {
//                        name.setText("Fail!");
//                        emailid.setText("Fail!");
//                        loginid.setText("Fail!");
//                        password.setText("Fail!");
                    }
                } catch (UnsupportedEncodingException ex) {
//                    name.setText("Fail");
//                    emailid.setText("Fail");
//                    loginid.setText("Fail");
//                    password.setText("Fail");
                }
            }
        });
    }
}