package disktree.net.fubar;

import android.preference.PreferenceActivity;
import android.os.Bundle;
import android.view.View;

public class DreamSettingsActivity extends PreferenceActivity {

    @Override
    public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		addPreferencesFromResource( R.xml.dream_preferences );
    }
}
