package user.imageapp.imageapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.URLUtil;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity  {

    private EditText mUrlEditText;

    private Uri mDefaultUrl =
            Uri.parse("http://www.dre.vanderbilt.edu/~schmidt/robot.png");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUrlEditText = (EditText) findViewById(R.id.url);

    }


    private Intent makeDownloadImageIntent(Uri url) {
        // Create an intent that will download the image from the web.
        // TODO -- you fill in here, replacing "false" with the proper
        // code.
        Intent downloadImageIntent = new Intent(this, DownloadImageActivity.class);
        downloadImageIntent.setData(url);

        return downloadImageIntent;
    }

    public void downloadImage(View view) {
        try {
            // Hide the keyboard.
            hideKeyboard(this,
                    mUrlEditText.getWindowToken());

            Uri uri = getUrl();
            Intent downloadIntent = makeDownloadImageIntent(uri);

            startActivity(downloadIntent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    protected Uri getUrl() {
        Uri url = null;

        // Get the text the user typed in the edit text (if anything).
        url = Uri.parse(mUrlEditText.getText().toString());

        // If the user didn't provide a URL then use the default.
        String uri = url.toString();
        if (uri == null || uri.equals(""))
            url = mDefaultUrl;

        // Do a sanity check to ensure the URL is valid, popping up a
        // toast if the URL is invalid.
        // @@ TODO -- you fill in here, replacing "true" with the
        // proper code.
        if (URLUtil.isValidUrl(url.toString()))
            return url;
        else {
            Toast.makeText(this,
                    "Invalid URL",
                    Toast.LENGTH_SHORT).show();
            return null;
        }

    }

    public void hideKeyboard(Activity activity,
                             IBinder windowToken) {
        InputMethodManager mgr =
                (InputMethodManager) activity.getSystemService
                        (Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(windowToken,
                0);
    }



}