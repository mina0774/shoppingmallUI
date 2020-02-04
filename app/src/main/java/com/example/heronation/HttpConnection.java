package com.example.heronation;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.http2.Http2Connection;


public class HttpConnection {

    private OkHttpClient client;
    private MediaType mediaType;
    private static HttpConnection instance=new HttpConnection();
    /* 내부에서 단 한번만 객체를 생성하도록 싱글톤 패턴을 적용 (동일한 인스턴스를 재사용하게끔 함, 클래스가 최초 한번만 메모리에 할당됨)*/
    public static HttpConnection getInstance(){
        return instance;
    }
    private HttpConnection(){ this.client=new OkHttpClient();}

    /* 웹 서버로 요청 */
  public void requestWebServer(final Context context, String parameter, String url,Callback callback) {

      mediaType = MediaType.parse("application/x-www-form-urlencoded");
      RequestBody body = RequestBody.create(parameter, mediaType);
      Request request = new Request.Builder()
              .url(url)
              .method("POST", body)
              .addHeader("Authorization", "zeyo-api-key QVntgqTsu6jqt7hQSVpF7ZS8Tw==")
              .addHeader("Accept", "application/json")
              .addHeader("Content-Type", "application/x-www-form-urlencoded")
              .build();

      //네트워크 비동기처리 (enqueue 사용)
      client.newCall(request).enqueue(callback);
  }

}
