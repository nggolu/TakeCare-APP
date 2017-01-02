package com.example.goku.swe_main;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import it.sephiroth.android.library.tooltip.Tooltip;

/**
 * Created by goku on 22/10/16.
 */

public class PopupWindowToolTipsArrow extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_message);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }



    public void bottomToolTipDialogBox(View view) {
        Button toolTipShowButton = (Button) findViewById(R.id.button_tooltip_bottom);
        Tooltip.make(this,
                new Tooltip.Builder(101)
                        .anchor(toolTipShowButton, Tooltip.Gravity.BOTTOM)
                        .closePolicy(new Tooltip.ClosePolicy()
                                .insidePolicy(true, false)
                                .outsidePolicy(true, false), 4000)
                        .activateDelay(900)
                        .showDelay(400)
                        .text("Informative: I'm travelling through the sent area.I might feel unsafe in this locality.\n Alert: I'm in a panic situation. Reach out to me IMMEDIATELY.")
                        .maxWidth(600)
                        .withArrow(true)
                        .withOverlay(true).build()
        ).show();
    }

   /* public void topToolTipDialogBox(View view) {
        Button toolTipShowButton = (Button) findViewById(R.id.button2);
        Tooltip.make(this,
                new Tooltip.Builder(101)
                        .anchor(toolTipShowButton, Tooltip.Gravity.TOP)
                        .closePolicy(new Tooltip.ClosePolicy()
                                .insidePolicy(true, false)
                                .outsidePolicy(true, false), 4000)
                        .activateDelay(900)
                        .showDelay(400)
                        .text("Hint: I'm in a panic situation. Reach out to me IMMEDIATELY.")
                        .maxWidth(600)
                        .withArrow(true)
                        .withOverlay(true).build()
        ).show();
    }*/



    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("PopupWindowToolTipsArrow Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
