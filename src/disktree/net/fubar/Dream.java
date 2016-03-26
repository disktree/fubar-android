package disktree.net.fubar;

import android.content.Intent;
import android.provider.Settings;
import android.service.dreams.DreamService;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class Dream extends DreamService {

	static final boolean DEBUG = true;
    static final String TAG = "fadr";

	private static final void log( String msg ) {
        if( DEBUG ){
            android.util.Log.d( TAG, msg );
        }
    }

	static int systemUiFlags =
		View.SYSTEM_UI_FLAG_LAYOUT_STABLE
		| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
		| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
		| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
		| View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
		| View.SYSTEM_UI_FLAG_IMMERSIVE
		| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

	private WebView webview;

	@Override
	public void onDreamingStarted() {
		super.onDreamingStarted();
		log( "======= onDreamingStarted =======");
	}

	@Override
	public void onDreamingStopped() {
		super.onDreamingStopped();
		log( "======= onDreamingStopped =======");
	}

	@Override
	public void onAttachedToWindow() {

		super.onAttachedToWindow();

		setFullscreen( true );
        setInteractive( true );
		//getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION );
		getWindow().getDecorView().setSystemUiVisibility( systemUiFlags );

		setContentView( R.layout.dream );

		webview = (WebView) findViewById( R.id.webview );
        webview.setBackgroundColor(0x00000000);
        //webview.clearCache(true);

		WebSettings settings = webview.getSettings();
		settings.setJavaScriptEnabled( true );
        settings.setAllowContentAccess( true );
        settings.setAllowFileAccess( true );
        settings.setAllowFileAccessFromFileURLs( true );
        settings.setAllowUniversalAccessFromFileURLs( true );
        settings.setDomStorageEnabled( true );
		settings.setJavaScriptCanOpenWindowsAutomatically( true );

		//webview.addJavascriptInterface( new WebApp(this), "AndroidApp" );

		webview.loadUrl( "file:///android_asset/index.html" );
	}

	@Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        log( "======= onDetachedFromWindow =======");
    }

}
