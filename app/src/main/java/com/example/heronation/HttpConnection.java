package com.example.heronation;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Credentials;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.http2.Http2Connection;


public class HttpConnection {

    private OkHttpClient client;
    private static HttpConnection instance=new HttpConnection();

    /* 로그인을 위한 분기처리를 위한 고정값 */
    private static String register_autorization;
    private static String login_autorization;
    private static String heronation_api_login_key;
    private static String heronation_api_uniqId_key;

    /* 내부에서 단 한번만 객체를 생성하도록 싱글톤 패턴을 적용 (동일한 인스턴스를 재사용하게끔 함, 클래스가 최초 한번만 메모리에 할당됨)*/
    public static HttpConnection getInstance(){
        return instance;
    }
    private HttpConnection() {
        this.client = new OkHttpClient();
    }

    /* POST 회원가입 웹 서버로 요청 */
  public void requestWebServer(String parameter, String url,Callback callback) {

      MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
      RequestBody body = RequestBody.create(parameter, mediaType);
      Request request;

          register_autorization="zeyo-api-key QVntgqTsu6jqt7hQSVpF7ZS8Tw==";
          request = new Request.Builder()
                  .url(url)
                  .method("POST", body)
                  .addHeader("Authorization",register_autorization )
                  .addHeader("Accept", "application/json")
                  .addHeader("Content-Type", "application/x-www-form-urlencoded")
                  .build();

      //네트워크 비동기처리 (enqueue 사용)
      client.newCall(request).enqueue(callback);
  }

    /* POST 로그인 웹 서버로 요청 */
    public void requestWebServer(String user_id,String user_password, String parameter, String url,Callback callback) {

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(parameter, mediaType);
        Request request;

            login_autorization= "Basic emV5b191c2VyOmlhbXVzZXI=";
            heronation_api_login_key="66Gc6re4T1Prk5zsnKDsl5RaRVlU7J24VEU=";
            heronation_api_uniqId_key="jvvzfj7p";
            request = new Request.Builder()
                    .url(url)
                    .method("POST", body)
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("heronation-api-login-key", heronation_api_login_key)
                    .addHeader("heronation-api-uniqId-key",heronation_api_uniqId_key)
                    .addHeader("Authorization",login_autorization)
                    .build();

        //네트워크 비동기처리 (enqueue 사용)
        client.newCall(request).enqueue(callback);
    }


}
