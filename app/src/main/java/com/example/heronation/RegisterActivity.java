package com.example.heronation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import retrofit2.Call;

import java.util.Calendar;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public class RegisterActivity extends AppCompatActivity {
    private EditText userModify_id_text;
    private EditText userModify_check_pw_et;
    private EditText userModify_email_text;
    private EditText userModify_name_text;
    private EditText userModify_edit_pw_et;
    private CheckBox userModify_push_check;
    private CheckBox userModify_male;
    private CheckBox userModify_female;
    //사용자 푸시 알림 체킹 여부
    private String push_check;
    //성별
    private static String gender_info;
    //사용자 생년월일
    private static String user_year;
    private static String user_month;
    private static String user_day;

    //'회원가입' 표시 버튼
    private Button register_next_button;

    //HttpConnection class의 객체를 받음
    private HttpConnection httpConnection = HttpConnection.getInstance();

    //회원가입이 정상적으로 이루어졌는지 확인하는 부분
    private int response_code;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userModify_id_text = (EditText) findViewById(R.id.userModify_id_text);
        userModify_check_pw_et = (EditText) findViewById(R.id.userModify_check_pw_et);
        userModify_edit_pw_et = (EditText) findViewById(R.id.userModify_edit_pw_et);
        userModify_email_text = (EditText) findViewById(R.id.userModify_email_text);
        userModify_name_text = (EditText) findViewById(R.id.userModify_name_text);
        register_next_button = (Button) findViewById(R.id.register_next_btn);
        userModify_male = (CheckBox) findViewById(R.id.userModify_male);
        userModify_female = (CheckBox) findViewById(R.id.userModify_female);
        userModify_push_check = (CheckBox) findViewById(R.id.userModify_push_check);

        /*생년월일 TextView클릭시 showDatePicker가 실행됨*/
        TextView register_datepicker = (TextView) findViewById(R.id.textView_register_datepicker);
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
                //Retrofit을 이용하여 회원가입을 위한 사용자 정보를 서버로 넘겨주는 작업을 진행함
                RegisterUserInfo();
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
            user_year = String.valueOf(year);
            user_month = String.valueOf(monthOfYear + 1);
            user_day = String.valueOf(dayOfMonth);
            textView_register_datepicker.setText(user_year + "-" + user_month
                    + "-" + user_day);
        }
    };

    private void RegisterUserInfo(){
        String authorization="zeyo-api-key QVntgqTsu6jqt7hQSVpF7ZS8Tw==";
        String accept="application/json";
        String content_type="application/x-www-form-urlencoded";

         RegisterService registerService=ServiceGenerator.createService(RegisterService.class);
         Call<String> request=registerService.postInfo(authorization,accept,content_type,userModify_id_text.getText().toString(),
                 userModify_email_text.getText().toString(),
                 userModify_name_text.getText().toString(),
                 userModify_check_pw_et.getText().toString(),
                 gender_info ,push_check,
                 user_year, user_month , user_day);

         //요청 부분
         request.enqueue(new Callback<String>() {
             @Override
             public void onResponse(Call<String> call, Response<String> response) {
                 System.out.println("Response" + response.code()); //204 사이의 값이 나왔을 때는 회원가입이 정상적으로 이루어짐
                 response_code = response.code();
                 //204의 값이 나오지 않으면, 회원가입이 정상적으로 이루어지지 않음 + 분기처리 필요 (할일)
                 if (response.code() != 204) {
                     backgroundThreadShortToast(getApplicationContext(), "정보를 다시 입력해주세요.");
                     return;
                 } else if(response.code() == 204){
                     backgroundThreadShortToast(getApplicationContext(),"회원가입이 완료 되었습니다.");
                 }

                 Intent intent = new Intent(getApplicationContext(), loginPageActivity.class);
                 intent.putExtra("user_id", userModify_id_text.getText().toString());
                 startActivity(intent);
                 finish();
             }

             @Override
             public void onFailure(Call<String> call, Throwable t) {
                 System.out.println("error + Connect Server Error is " + t.toString());
             }
         });

    }

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

    //인터페이스 - 추상 메소드(구현부가 없는 메시드)의 모임
    /* retrofit은 인터페이스에 기술된 명세를 Http API(호출 가능한 객체)로 전환해줌
    => 우리가 요청할 API들에 대한 명세만을 Interface에 기술해두면 됨.
     */
    public interface RegisterService{
        @FormUrlEncoded
        @POST("api/consumers/registry")
        Call<String> postInfo(@Header("Authorization") String authorization,
                              @Header("Accept") String accept,
                              @Header("Content-Type") String content_type,
                              @Field("consumerId") String consumerID, @Field("email") String email,
                              @Field("name") String name, @Field("password") String password,
                              @Field("gender") String gender, @Field("termsAdvertisement") String termsAdvertisement,
                              @Field("birthYear") String birthYear, @Field("birthMonth") String birthMonth, @Field("birthDay") String birthDay);
    }

}

