package disktree.net.fubar;

import android.app.Activity;

public final class App {

    public static final String TAG = "fubar";
    public static final boolean DEBUG = true;

    public static void setupSystemUi( Activity activity, int flags ) {
        activity.getWindow().getDecorView().setSystemUiVisibility( flags );
    }
}
