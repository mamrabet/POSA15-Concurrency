package user.imageapp.imageapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class DownloadImageActivity extends Activity implements DownloadImageFragment.TaskCallbacks {

    private static final String TAG_TASK_FRAGMENT = "task_fragment";

    private DownloadImageFragment mTaskFragment;
    TextView progressText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download);

        FragmentManager fm = getFragmentManager();
        mTaskFragment = (DownloadImageFragment) fm.findFragmentByTag(TAG_TASK_FRAGMENT);

        // If the Fragment is non-null, then it is currently being
        // retained across a configuration change.
        if (mTaskFragment == null) {
            mTaskFragment = new DownloadImageFragment();
            fm.beginTransaction().add(mTaskFragment, TAG_TASK_FRAGMENT).commit();
        }

        // TODO: initialize views, restore saved state, etc.
        progressText = (TextView) findViewById(R.id.download);
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public void onPreExecute() {
        progressText.setText("Downloading image... ");
    }

    @Override
    public void onProgressUpdate(int percent) {


    }

    @Override
    public void onCancelled() {

    }

    @Override
    public void onPostExecute(Uri image) {
        progressText.setText("Download Finished... ");
        Intent FilterImageIntent = new Intent(this, FilterImageActivity.class);
        FilterImageIntent.setData(image);
        startActivity(FilterImageIntent);
    }

    // The four methods below are called by the TaskFragment when new
    // progress updates or results are available. The MainActivity
    // should respond by updating its UI to indicate the change.

    public void backHome (View v){
        Intent homeIntent = new Intent(this, MainActivity.class);
        startActivity(homeIntent);
    }



}