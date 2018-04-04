package jakescounterapp.jaked.example.com.cryptoapi;

import android.content.Intent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowApplication;

import static junit.framework.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class WelcomeActivityTest {

    @Test
    public void clickingSettings_shouldStartSettings(){
        MainActivity activity = Robolectric.setupActivity(MainActivity.class);
        activity.findViewById(R.id.settings).performClick();

        Intent expectedIntent = new Intent(activity, SettingsActivity.class);
        Intent actual = ShadowApplication.getInstance().getNextStartedActivity();
        assertEquals(expectedIntent.getComponent(), actual.getComponent());

    }
}
