package com.kevinjanvier.slauversion1.Signup;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import android.app.ProgressDialog;

import com.kevinjanvier.slauversion1.R;


public class RegisterPage extends AppCompatActivity implements View.OnClickListener {

    private EditText usernamedit;
    private EditText emailedit;
    private EditText phonedit;
    private EditText passwordedit;

    private Button buttonregister;

    private static final String REGISTER_URL="http://stulrikacollegebembe.com/slau/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);


        usernamedit = (EditText) findViewById(R.id.usernamedit);
        emailedit = (EditText) findViewById(R.id.emailedit);
        phonedit = (EditText) findViewById(R.id.phonedit);
        passwordedit = (EditText) findViewById(R.id.passwordedit);
        buttonregister = (Button) findViewById(R.id.buttonregister);
        buttonregister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonregister){
            registerUser();
        }
    }

    private void registerUser() {
        String username = usernamedit.getText().toString().trim().toLowerCase();
        String email = emailedit.getText().toString().trim().toLowerCase();
        String telephone = phonedit.getText().toString().trim().toLowerCase();
        String password = passwordedit.getText().toString().trim().toLowerCase();

        register(username, email, telephone, password);
    }

    private void register(String username, String email, String telephone, String password) {
//
//        String urlSuffix = "?username="+username+"&email="+email+"&telephone="+telephone+"&password="+password;
        class RegisterUser extends AsyncTask<String, Void, String>{
            ProgressDialog loading;

            UserRegister ruc = new UserRegister();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(RegisterPage.this, "Please Wait",null, true, true);
//                Intent intent = new Intent(RegisterPage.this, MainActivity.class);
//                startActivity(intent);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {
//                String s = params[0];
//                BufferedReader bufferedReader = null;
//                try {
//                    URL url = new URL(REGISTER_URL+s);
//                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
//                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
//
//                    String result;
//
//                    result = bufferedReader.readLine();
//
//                    return result;
//                }catch(Exception e){
//                    return null;
//                }
//                HashMap<String, String> data = new HashMap<String, String>();
//                data.put("username", params[0]);
//                data.put("email", params[1]);
//                data.put("telephone", params[2]);
//                data.put("password", params[3]);
//
//
//                String result = ruc.sendPostRequest(REGISTER_URL, data);
//
//                return result;
                HashMap<String, String> data = new HashMap<String,String>();
                data.put("username",params[0]);
                data.put("email",params[1]);
                data.put("telephone",params[2]);
                data.put("password",params[3]);

                String result = ruc.sendPostRequest(REGISTER_URL,data);

                return  result;
            }
        }
//        RegisterUser ru = new RegisterUser();
//        ru.execute(urlSuffix);

        RegisterUser ru = new RegisterUser();
        ru.execute(username, email, telephone, password);
        }
    }