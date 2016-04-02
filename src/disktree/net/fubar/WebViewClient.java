package disktree.net.fubar;

import android.webkit.WebView;

public class WebViewClient extends android.webkit.WebViewClient {

    @Override
    public void onPageFinished( WebView view, String url ) {
        super.onPageFinished( view, url );
        android.util.Log.d( App.TAG, url );
    }

}
