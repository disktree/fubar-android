package disktree.net.fubar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;

public class MainActivity extends Activity {

	protected WebView webview;
	protected int systemUiFlags;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate( savedInstanceState );

		this.requestWindowFeature( Window.FEATURE_NO_TITLE );
		this.getWindow().addFlags( WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON );

		systemUiFlags =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_FULLSCREEN
            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

		setupSystemUi();

        setContentView( R.layout.activity_main );

		webview = (WebView) findViewById(R.id.webview);
        webview.setBackgroundColor(0x00000000);
        //webview.clearCache(true);
        //webview.setInitialScale(0);

		WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowContentAccess(true);
        settings.setAllowFileAccess(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setDomStorageEnabled(true);
        //settings.setUseWideViewPort(true);
        //settings.setLayoutAlgorithm(LayoutAlgorithm.NORMAL);

		webview.addJavascriptInterface( new WebApp(this), "AndroidApp" );

		final View decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener( new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if( (visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0 ) {
                    decorView.setSystemUiVisibility( systemUiFlags );
                }
            }
        });

		webview.loadUrl( "file:///android_asset/index.html" );
    }

	@Override
    public void onResume() {
        super.onResume();
        setupSystemUi();
    }

	@Override
    public void onWindowFocusChanged( boolean hasFocus ) {
        super.onWindowFocusChanged( hasFocus );
        if( hasFocus )
            setupSystemUi();
    }

	private void setupSystemUi() {
        getWindow().getDecorView().setSystemUiVisibility( systemUiFlags );
    }

	private final void log( String str ) {
        android.util.Log.d( App.TAG, str );
    }
}
