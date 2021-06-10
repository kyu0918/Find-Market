package com.example.fleemarket;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


public class WebViewActivity extends AppCompatActivity {
    private WebView daum_webView;

    class MyJavaScriptInterface
    {
        @JavascriptInterface
        @SuppressWarnings("unused")
        public void processDATA(String data) {

            Bundle extra = new Bundle();
            Intent intent = new Intent();
            extra.putString("data", data);
            intent.putExtras(extra);
            setResult(RESULT_OK, intent);
            finish();

        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        ActionBar ab = getSupportActionBar();
        ab.hide();


        daum_webView = (WebView) findViewById(R.id.webView);
        daum_webView.getSettings().setJavaScriptEnabled(true);
        daum_webView.addJavascriptInterface(new MyJavaScriptInterface(), "Android");

        daum_webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {

                daum_webView.loadUrl("javascript:sample2_execDaumPostcode();");
            }
        });

        //browser.loadUrl("file:///android_asset/daum.html");
        //browser.loadUrl("http://www.daddyface.com/public/daum.html");
        //browser.loadUrl("http://cdn.rawgit.com/jolly73-df/DaumPostcodeExample/master/DaumPostcodeExample/app/src/main/assets/daum.html");
        daum_webView.loadUrl("http://www.inspond.com/daum.html");
        // 경고! 위 주소대로 서비스에 사용하시면 파일이 삭제됩니다.
        // 꼭 자신의 웹 서버에 해당 파일을 복사해서 주소를 변경해 사용하세요.


    }   // onCreat()

}
