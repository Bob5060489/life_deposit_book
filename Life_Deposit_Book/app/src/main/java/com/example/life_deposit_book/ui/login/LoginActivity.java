package com.example.life_deposit_book.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.life_deposit_book.MainActivity;
import com.example.life_deposit_book.R;

public class LoginActivity extends AppCompatActivity {
    private LoginViewModel loginViewModel;
    String exampleID = "606410071";
    public String examplePwd = "123456";
    String temp;
    String []testing;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final TextView textView1 = findViewById(R.id.textView);
        final EditText usernameEditText = findViewById(R.id.studentID);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);  //登入等待條

        new Thread(new Runnable(){
            @Override
            public void run(){
                MysqlCon con = new MysqlCon();
                con.run();
                final String data = con.getData();
                Log.v("OK",data);
                textView1.post(new Runnable() {
                    public void run() {
                        temp = getTestData(data);
                        textView1.setText("連結成功");
                        //System.out.println(data);

                        testing = temp.split(" ");

                        for (int i = 0; i < 2; i++){
                            System.out.println(testing[i]);
                        }

                        /*___________________________________________________________________________________________*/

                        loginViewModel.getLoginFormState().observe(LoginActivity.this, new Observer<LoginFormState>() {
                            @Override
                            public void onChanged(@Nullable LoginFormState loginFormState) {
                                if (loginFormState == null) {
                                    return;
                                }
                                loginButton.setEnabled(loginFormState.isDataValid()); //登入鍵反灰變成有用
                                if (loginFormState.getUsernameError() != null) {
                                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                                }
                                if (loginFormState.getPasswordError() != null) {
                                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                                }
                            }
                        });

                        loginViewModel.getLoginResult().observe(LoginActivity.this, new Observer<LoginResult>() { //?
                            @Override
                            public void onChanged(@Nullable LoginResult loginResult) {
                                if (loginResult == null) {
                                    return;
                                }
                                loadingProgressBar.setVisibility(View.GONE);
                                if (loginResult.getError() != null) {
                                    showLoginFailed(loginResult.getError());
                                }
                                if (loginResult.getSuccess() != null) {
                                    updateUiWithUser(loginResult.getSuccess(), usernameEditText, passwordEditText, testing);
                                }
                                setResult(Activity.RESULT_OK);

                                //Complete and destroy login activity once successful
                                finish();
                            }
                        });

                        TextWatcher afterTextChangedListener = new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                // ignore
                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                // ignore
                            }

                            @Override
                            public void afterTextChanged(Editable s) {
                                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                                        passwordEditText.getText().toString());
                            }
                        };
                        usernameEditText.addTextChangedListener(afterTextChangedListener);
                        passwordEditText.addTextChangedListener(afterTextChangedListener);
                        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

                            @Override
                            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                                if (actionId == EditorInfo.IME_ACTION_DONE) {
                                    loginViewModel.login(usernameEditText.getText().toString(),
                                            passwordEditText.getText().toString());
                                }
                                return false;
                            }
                        });

                        loginButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                loadingProgressBar.setVisibility(View.VISIBLE);
                                loginViewModel.login(usernameEditText.getText().toString(),
                                        passwordEditText.getText().toString());
                            }
                        });

//                        if(testing[0].equals(usernameEditText.getText().toString()) && testing[1].equals(passwordEditText.getText().toString())){//有問題的寫法:StudentID == exampleID，永遠不會成立。
//                            Toast.makeText(getApplicationContext(), "登入成功", Toast.LENGTH_LONG).show();//新的activity成立之後，要拿取資料庫中的存款簿紀錄。
//                            Intent MainPage = new Intent(this, MainActivity.class);
//
//                            //這邊會把登入資訊傳到記事頁面。
//
//                            startActivity(MainPage);
//                        }
                    }
                });

            }
        }).start();
    }

    private void updateUiWithUser(LoggedInUserView model, EditText usernameEditText, EditText passwordEditText, String[] loginString) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        //Log.d("STATE", usernameEditText.getText().toString());
        //Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
        //Log.d("STATE", exampleID);
        //Log.d("STATE", usernameEditText.getText().toString());

        if(usernameEditText.getText().toString().equals(loginString[0]) && passwordEditText.getText().toString().equals(loginString[1])){//有問題的寫法:StudentID == exampleID，永遠不會成立。
            Toast.makeText(getApplicationContext(), "登入成功", Toast.LENGTH_LONG).show();//新的activity成立之後，要拿取資料庫中的存款簿紀錄。
            Intent MainPage = new Intent(this, MainActivity.class);

            //這邊會把登入資訊傳到記事頁面。

            startActivity(MainPage);
        }
//        if(usernameEditText.getText().toString().equals("606410071") && passwordEditText.getText().toString().equals("123456")){//有問題的寫法:StudentID == exampleID，永遠不會成立。
//            Toast.makeText(getApplicationContext(), "登入成功", Toast.LENGTH_LONG).show();//新的activity成立之後，要拿取資料庫中的存款簿紀錄。
//            Intent MainPage = new Intent(this, MainActivity.class);
//
//            //這邊會把登入資訊傳到記事頁面。
//
//            startActivity(MainPage);
//        }
        else{
            System.out.println(testing[0]);
            System.out.println(testing[1]);
            System.out.println(usernameEditText.getText().toString());
            System.out.println(passwordEditText.getText().toString());
            System.out.println(usernameEditText.getText().toString().equals(loginString[0]));
            System.out.println(passwordEditText.getText().toString().equals(loginString[1]));
            Toast.makeText(getApplicationContext(), "登入失敗", Toast.LENGTH_LONG).show(); //應該是還要保留狀態。
        }
    }

    private String getTestData(String data) {
        String tmp = data;
        return tmp;
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}
