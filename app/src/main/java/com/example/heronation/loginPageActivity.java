package com.example.heronation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class  loginPageActivity extends AppCompatActivity {
    private EditText login_id_et;
    private EditText login_password_et;
    private Button login_button;

    //HttpConnection class의 객체를 받음
    private HttpConnection httpConnection=HttpConnection.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        login_id_et=(EditText)findViewById(R.id.login_id_et);
        login_password_et=(EditText)findViewById(R.id.login_password_et);
        login_button=(Button)findViewById(R.id.login_button);

        login_id_et.setText( getIntent().getStringExtra("user_id") );

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread() {

                    public void run() {
                        /* body 부분의 값 */
                        String parameter = "username="+login_id_et.getText().toString()
                                +"&password="+login_password_et.getText().toString()
                                +"&grant_type=password";
                        /* 웹서버 URL */
                        String url = "http://rest.dev.zeyo.co.kr/oauth/token";
                        httpConnection.requestWebServer(login_id_et.getText().toString(),login_password_et.getText().toString(),parameter, url, callback);
                    }
                }.start();

            }
        });

    }
    private final Callback callback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            System.out.println("error + Connect Server Error is " + e.toString());
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            System.out.println("Response" + response.code());
            if(response.code()!=200){
                backgroundThreadShortToast(getApplicationContext(), "등록되지 않은 아이디거나 아이디 또는 비밀번호가 일치하지 않습니다.");
                return;
            }

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    };

    //Toast는 비동기 태스크 내에서 처리할 수 없으므로, 메인 쓰레드 핸들러를 생성하여 toast가 메인쓰레드에서 생성될 수 있도록 처리해준다.
    public static void backgroundThreadShortToast(final Context context, final String msg) {
        if (context != null && msg != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


}
