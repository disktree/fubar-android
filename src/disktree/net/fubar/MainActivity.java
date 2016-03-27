package disktree.net.fubar;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import disktree.net.fubar.App;

public class MainActivity extends Activity {

	static final int SYSTEM_UI_FLAGS =
		View.SYSTEM_UI_FLAG_LAYOUT_STABLE
		| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
		| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
		| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
		| View.SYSTEM_UI_FLAG_FULLSCREEN
		| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
	protected WebView webview;

    @Override
    public void onCreate( Bundle savedInstanceState ) {

        super.onCreate( savedInstanceState );

		requestWindowFeature( Window.FEATURE_NO_TITLE );
		getWindow().addFlags( WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON );
		setupSystemUi();

		webview = new WebView( MainActivity.this );
        webview.setBackgroundColor( 0x00000000 );
        //webview.clearCache(true);
        //webview.setInitialScale(0);

		webview.setWebViewClient( new WebViewClient() );
		webview.setWebChromeClient( new WebChromeClient( App.TAG ) );

		WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled( true );
        settings.setAllowContentAccess( true );
        settings.setAllowFileAccess( true );
        settings.setAllowFileAccessFromFileURLs( true );
        settings.setAllowUniversalAccessFromFileURLs( true );
        settings.setDomStorageEnabled( true );
		settings.setJavaScriptCanOpenWindowsAutomatically( true );
        //settings.setUseWideViewPort( true );
        //settings.setLayoutAlgorithm(LayoutAlgorithm.NORMAL);

		webview.addJavascriptInterface( new WebApp(this), "AndroidApp" );

		final View decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener( new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if( (visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0 ) {
                    decorView.setSystemUiVisibility( SYSTEM_UI_FLAGS );
                }
            }
        });

		webview.loadUrl( "file:///android_asset/index.html" );

		setContentView( webview );
    }

	@Override
    public void onResume() {
        super.onResume();
        setupSystemUi();
    }

	@Override
	public void onConfigurationChanged( Configuration newConfig ) {
		super.onConfigurationChanged( newConfig );
		if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
		   android.widget.Toast.makeText(this, "landscape", android.widget.Toast.LENGTH_SHORT).show();
	   } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
		   android.widget.Toast.makeText(this, "portrait", android.widget.Toast.LENGTH_SHORT).show();
	   }
	}

	@Override
    public void onWindowFocusChanged( boolean hasFocus ) {
        super.onWindowFocusChanged( hasFocus );
        if( hasFocus )
            setupSystemUi();
    }

	private final void setupSystemUi() {
        getWindow().getDecorView().setSystemUiVisibility( SYSTEM_UI_FLAGS );
    }

	private final void log( String str ) {
        android.util.Log.d( App.TAG, str );
    }
}
