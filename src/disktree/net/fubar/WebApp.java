package disktree.net.fubar;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.webkit.JavascriptInterface;

public final class WebApp {

    private Activity activity;

    public WebApp( Activity a ) {
        activity = a;
    }

    @JavascriptInterface
    public void shareImage( String text ) {
        Intent sendIntent = new Intent();
        sendIntent.setAction( Intent.ACTION_SEND );
        //sendIntent.putExtra( Intent.EXTRA_SUBJECT, subject );
        sendIntent.putExtra( Intent.EXTRA_TEXT, text );
        sendIntent.setType( "text/plain" );
        activity.startActivity( sendIntent );
    }

    @JavascriptInterface
    public void downloadImage( String url, String file, String description, String title ) {

        android.util.Log.d( App.TAG, url );

        DownloadManager.Request request = new DownloadManager.Request( Uri.parse( url ) );
        request.setDescription( description );
        request.setTitle( title );
        request.setDestinationInExternalPublicDir( Environment.DIRECTORY_DOWNLOADS, "myfile.gif" );

        DownloadManager manager = (DownloadManager) activity.getSystemService( Context.DOWNLOAD_SERVICE );
        manager.enqueue( request );

        /*
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
                    long downloadId = intent.getLongExtra(
                            DownloadManager.EXTRA_DOWNLOAD_ID, 0);
                    Query query = new Query();
                    query.setFilterById(enqueue);
                    Cursor c = dm.query(query);
                    if (c.moveToFirst()) {
                        int columnIndex = c
                                .getColumnIndex(DownloadManager.COLUMN_STATUS);
                        if (DownloadManager.STATUS_SUCCESSFUL == c
                                .getInt(columnIndex)) {

                            ImageView view = (ImageView) findViewById(R.id.imageView1);
                            String uriString = c
                                    .getString(c
                                            .getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                            view.setImageURI(Uri.parse(uriString));
                        }
                    }
                }
            }
        };

        //registerReceiver( receiver, new IntentFilter( DownloadManager.ACTION_DOWNLOAD_COMPLETE ) );
        */

        android.widget.Toast.makeText( activity, "Download complete", android.widget.Toast.LENGTH_SHORT ).show();
    }

    @JavascriptInterface
    public void showToast( String msg ) {
        android.widget.Toast.makeText( activity, msg, android.widget.Toast.LENGTH_SHORT ).show();
    }
}
