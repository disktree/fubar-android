package disktree.net.fubar;

import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.ConsoleMessage.MessageLevel;

public class WebChromeClient extends android.webkit.WebChromeClient {

	public String tag;

	public WebChromeClient( String tag ) {
		super();
		this.tag = tag;
	}

	@Override
	public boolean onConsoleMessage( ConsoleMessage msg ) {
		//String str = msg.message() + " -- From line " + msg.lineNumber() + " of " + msg.sourceId();
		String str = msg.lineNumber() + ": " + msg.message() + " (" + msg.sourceId() + ")";
		MessageLevel lvl = msg.messageLevel() ;
		switch(lvl) {
		case DEBUG:
			Log.d( tag, str );
			break;
		case WARNING:
			Log.w( tag, str );
			break;
		case ERROR:
			Log.e( tag, str );
			break;
		case LOG:
			Log.d( tag, str );
			break;
		case TIP:
			Log.d( tag, str );
			break;
		default:
			Log.d( tag, str );
			break;
		}
		//Log.d( tag, msg.message() + " -- From line " + msg.lineNumber() + " of " + msg.sourceId() );
		return true;
	}

}
