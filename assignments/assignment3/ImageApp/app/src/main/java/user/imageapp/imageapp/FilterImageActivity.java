package user.imageapp.imageapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class FilterImageActivity extends Activity implements FilterImageFragment.TaskCallbacks {

    private static final String TAG_TASK_FRAGMENT = "task_fragment";

    private FilterImageFragment mTaskFragment;
    TextView progressText;
        Uri filteredImage = null;
        ProgressDialog progress;
        public static final String PREFS_NAME = "MyPrefsFile";
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        setContentView(R.layout.filter);

        FragmentManager fm = getFragmentManager();
        mTaskFragment = (FilterImageFragment) fm.findFragmentByTag(TAG_TASK_FRAGMENT);

        // If the Fragment is non-null, then it is currently being
        // retained across a configuration change.
        if (mTaskFragment == null) {
            mTaskFragment = new FilterImageFragment();
            fm.beginTransaction().add(mTaskFragment, TAG_TASK_FRAGMENT).commit();
        }
      progressText = (TextView) findViewById(R.id.filter);
    }

    @Override
    public void onBackPressed() {
    }


    @Override
    public void onPreExecute() {
        progressText.setText("Filtering image... ");
    }

    @Override
    public void onProgressUpdate(int percent) {

    }

    @Override
    public void onCancelled() {

    }

    @Override
    public void onPostExecute(Uri image) {
        progressText.setText("Image filtered");
         filteredImage = image;
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse("file://" + image.toString()), "image/*");
        startActivity(intent);
    }

    // The four methods below are called by the TaskFragment when new
    // progress updates or results are available. The MainActivity
    // should respond by updating its UI to indicate the change.


    public void backHome (View v){
        Intent homeIntent = new Intent(this, MainActivity.class);
        startActivity(homeIntent);
    }


}