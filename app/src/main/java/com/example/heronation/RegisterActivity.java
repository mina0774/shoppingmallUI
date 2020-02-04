package com.example.heronation;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity{
    private EditText userModify_id_text;
    private EditText userModify_check_pw_et;
    private EditText userModify_email_text;
    private EditText userModify_name_text;
    private CheckBox userModify_push_check;
    private CheckBox userModify_male;
    private CheckBox userModify_female;
    //사용자 푸시 알림 체킹 여부
    private String push_check;
    //성별
    private String gender_info;
    //사용자 생년월일
    private String user_year;
    private String user_month;
    private String user_day;

    //'회원가입' 표시 버튼
    private Button register_next_button;

    //HttpConnection class의 객체를 받음
    private HttpConnection httpConnection=HttpConnection.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userModify_id_text=(EditText)findViewById(R.id.userModify_id_text);
        userModify_check_pw_et=(EditText)findViewById(R.id.userModify_check_pw_et);
        userModify_email_text=(EditText)findViewById(R.id.userModify_email_text);
        userModify_name_text=(EditText)findViewById(R.id.userModify_name_text);
        register_next_button=(Button)findViewById(R.id.register_next_btn);
        userModify_male=(CheckBox)findViewById(R.id.userModify_male);
        userModify_female=(CheckBox)findViewById(R.id.userModify_female);
        userModify_push_check=(CheckBox) findViewById(R.id.userModify_push_check);

        /*생년월일 TextView클릭시 showDatePicker가 실행됨*/
        TextView register_datepicker = (TextView)findViewById(R.id.textView_register_datepicker);
        register_datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        /* 회원가입 버튼 클릭시, 회원가입 정보가 서버단으로 넘어가고, 스타일 설정 페이지로 넘어감 */
        register_next_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //체크박스가 체크 되어있으면, 푸시알림을 Y로 설정 아니면 N으로 설정, API 처리를 위해 이러한 형태로 바꿔줌
                if (userModify_push_check.isChecked()) {
                    push_check = "Y";
                } else {
                    push_check = "N";
                }

                //성별 여자이면 F, 남자이면 M, API 처리를 위해 이러한 형태로 바꿔줌
                if (userModify_male.isChecked()) {
                    gender_info = "M";
                } else if (userModify_female.isChecked()) {
                    gender_info = "F";
                }

                new Thread() {

                    public void run() {

                        String parameter = "consumerId=" + userModify_id_text.getText().toString() +
                                "&email=" + userModify_email_text.getText().toString() +
                                "&name=" + userModify_name_text.getText().toString() +
                                "&password=" + userModify_check_pw_et.getText().toString() +
                                "&gender=" + gender_info +
                                "&termsAdvertisement=" + push_check +
                                "&birthYear=" + user_year + "&birthMonth=" + user_month + "&birthDay=" + user_day;
                        String url = "http://rest.dev.zeyo.co.kr/api/consumers/registry";
                        
                        httpConnection.requestWebServer(getApplicationContext(), parameter, url, callback);
                    }

                    ;
                }.start();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
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
            System.out.println("Response" + response.code()); //204 사이의 값이 나왔을 때는 회원가입이 정상적으로 이루어짐
            //204의 값이 나오지 않으면, 회원가입이 정상적으로 이루어지지 않음
            if (response.code() != 204) {
                backgroundThreadShortToast(getApplicationContext(), response.body().string());
            }
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


        /*각 생년 월일 입력받음*/
        private void showDatePicker() {
            DatePickerFragment date = new DatePickerFragment();
            /**
             * Set Up Current Date Into dialog
             */
            Calendar calender = Calendar.getInstance();
            Bundle args = new Bundle();
            args.putInt("year", calender.get(Calendar.YEAR));
            args.putInt("month", calender.get(Calendar.MONTH));
            args.putInt("day", calender.get(Calendar.DAY_OF_MONTH));
            date.setArguments(args);
            /**
             * Set Call back to capture selected date
             */
            date.setCallBack(ondate);
            date.show(getSupportFragmentManager(), "Date Picker");
        }

        /*입력 받은것 출력*/
        DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

                TextView textView_register_datepicker = (TextView) findViewById(R.id.textView_register_datepicker);
                user_year = String.valueOf(year);
                user_month = String.valueOf(monthOfYear + 1);
                user_day = String.valueOf(dayOfMonth);
                textView_register_datepicker.setText(user_year + "-" + user_month
                        + "-" + user_day);
            }
        };


}
