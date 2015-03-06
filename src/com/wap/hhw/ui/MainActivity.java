package com.wap.hhw.ui;

import java.util.Properties;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wap.hhw.R;

@SuppressLint("SetJavaScriptEnabled")
public class MainActivity extends Activity {

	private WebView webview_main;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();
	}

	private void initView() {
//		String url1 = this.initUrl();
//		System.out.println(url1);
		String url = this.initUrl();
		

		this.webview_main = (WebView) findViewById(R.id.webview_main);
		WebSettings webSettings = webview_main.getSettings();
		// 能够执行Javascript脚本
		webSettings.setJavaScriptEnabled(true);
//		// 设置支持缩放
//		webSettings.setBuiltInZoomControls(true);
		// 加载需要显示的网页
		webview_main.loadUrl(url);
		// 设置Web视图
		webview_main.setWebViewClient(new webViewClient());
	}

	private String initUrl() {
		String url = "";
//		AssetManager am = null;
//		InputStreamReader isr = null;
//		BufferedReader br = null;
//		try {
//			am = this.getAssets();
//			isr = new InputStreamReader(am.open("URL.txt"));
//			br = new BufferedReader(isr);
//			url = br.readLine();
//			br.close();
//			isr.close();
//			am.close();
//		} catch (IOException e) {
//			return null;
//		} finally {
//			try {
//				am.close();
//				isr.close();
//				br.close();
//			} catch (IOException e) {
//				return null;
//			}
//		}

		Properties properties = new Properties();
		try {
			properties.load(this.getAssets().open("const.properties"));
			url = properties.getProperty("url");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return url;
	}

	@Override
	// 设置回退
	// 覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && webview_main.canGoBack()) {
			// goBack()表示返回WebView的上一页面
			webview_main.goBack();
			return true;
		}
		finish();// 结束退出程序
		return false;
	}

	// Web视图
	private class webViewClient extends WebViewClient {
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
	}

}
