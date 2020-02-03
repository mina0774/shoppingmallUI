package com.example.heronation;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

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
    //사용자 푸시 알림 체킹 여부
    private String push_check;
    //사용자 생년월일
    private String user_year;
    private String user_month;
    private String user_day;

    //'다음' 표시 버튼
    private Button register_next_button;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userModify_id_text=(EditText)findViewById(R.id.userModify_id_text);
        userModify_check_pw_et=(EditText)findViewById(R.id.userModify_check_pw_et);
        userModify_email_text=(EditText)findViewById(R.id.userModify_email_text);
        userModify_name_text=(EditText)findViewById(R.id.userModify_name_text);
        register_next_button=(Button)findViewById(R.id.register_next_btn);

        //체크박스가 체크 되어있으면, 푸시알림을 Y로 설정 아니면 N으로 설정, API 처리를 위해 이러한 형태로 바꿔줌
        userModify_push_check=(CheckBox) findViewById(R.id.userModify_push_check);
        if(userModify_push_check.isChecked()){
            push_check="Y";
        }
        else{
            push_check="N";
        }

        /*생년월일 TextView클릭시 showDatePicker가 실행됨*/
        TextView register_datepicker = (TextView)findViewById(R.id.textView_register_datepicker);
        register_datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        /* 다음 버튼 클릭시, 회원가입 정보가 서버단으로 넘어가고, 스타일 설정 페이지로 넘어감 */
        register_next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    // API 연동
                    OkHttpClient client=new OkHttpClient().newBuilder().build();
                    MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
                    //성별 설정칸 만들기
                    RequestBody body = RequestBody.create("consumerId="+userModify_id_text.getText().toString()+
                            "&email="+userModify_email_text.getText().toString()+"&name="+userModify_name_text.getText().toString()+
                            "&password="+userModify_check_pw_et.getText().toString()+
                            "&gender=M"+"&termsAdvertisement="+push_check+
                            "&birthYear="+user_year+"&birthMonth="+user_month+"&birthDay="+user_day,mediaType);
                    Request request = new Request.Builder()
                            .url("http://rest.dev.zeyo.co.kr/api/consumers/registry")
                            .method("POST", body)
                            .addHeader("Authorization", "zeyo-api-key QVntgqTsu6jqt7hQSVpF7ZS8Tw==")
                            .addHeader("Accept", "application/json")
                            .addHeader("Content-Type", "application/x-www-form-urlencoded")
                            .build();

                    //네트워크 비동기처리 (enqueue 사용)
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(@NotNull Call call, @NotNull IOException e) {
                            System.out.println("error + Connect Server Error is " + e.toString());
                        }

                        @Override
                        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                            System.out.println("Response Body is " + response.body().string());
                        }
                    });

                Intent intent = new Intent(getApplicationContext(), RegisterBodyActivity.class);
                startActivity(intent);
            }
        });
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
            user_year=String.valueOf(year);
            user_month=String.valueOf(monthOfYear+1);
            user_day= String.valueOf(dayOfMonth);
            textView_register_datepicker.setText(user_year + "-" + user_month
                    + "-" + user_day);
        }
    };
    /*
    public void register_next_btn(View view){
        try {
            // API 연동
            OkHttpClient client=new OkHttpClient().newBuilder().build();
            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            //성별 설정칸 만들기
            RequestBody body = RequestBody.create(mediaType, "consumerId="+userModify_id_text.getText().toString()+
                    "&email="+userModify_email_text.getText().toString()+"&password="+userModify_check_pw_et.getText().toString()+
                    "&gender=M"+"&termsAdvertisement="+push_check+
                    "&birthYear="+user_year+"&birthMonth="+user_month+"&birthDay="+user_day);
            Request request = new Request.Builder()
                    .url("http://rest.dev.zeyo.co.kr/api/consumers/registry")
                    .method("POST", body)
                    .addHeader("Authorization", "zeyo-api-key QVntgqTsu6jqt7hQSVpF7ZS8Tw==")
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build();
            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(this, RegisterBodyActivity.class);
        startActivity(intent);
    }
    */

}
