package com.birdpeek.mynotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private NotificationCompat.Builder mBuilder;
    private Bitmap bitmapProfile, bitmapProfile2;
    private NotificationManager notificationManager;
    private PendingIntent pendingIntent;
    private Intent intent;
    private NotificationCompat.Builder notifiBuilder;
    private android.support.v4.app.NotificationCompat.InboxStyle[] inboxStyle = new android.support.v4.app.NotificationCompat.InboxStyle[10];
    private int count1 = 0, count2 = 0;
    private android.support.v4.app.NotificationCompat.BigPictureStyle bigPictureStyle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Button btnSignal1 = (Button) findViewById(R.id.signal1);
        Button btnSignal2 = (Button) findViewById(R.id.signal2);
        BitmapFactory.decodeResource(getResources(),
                R.drawable.scene1);
        ImageView imageview = (ImageView) findViewById(R.id.imageview);
        imageview.setImageResource(R.mipmap.scene1);
        imageview.setVisibility(View.GONE);
        bitmapProfile = drawableToBitmap(imageview.getDrawable());
        imageview.setImageResource(R.mipmap.scene2);
        bitmapProfile2 = drawableToBitmap(imageview.getDrawable());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onClick(View v){

        switch(v.getId()){
            case R.id.signal1:
                if(inboxStyle[0] == null)
                    inboxStyle[0] = new NotificationCompat.InboxStyle();
                count1++;
                inboxStyle[0].addLine("Line signal1: " + count1);
                inboxStyle[0].setSummaryText("Summary total: " + count1);

                intent = new Intent(this, ResultActivity.class);
                pendingIntent = PendingIntent.getActivity(this, 0/*Request code*/, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                notifiBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.logo_bual)
                        .setLargeIcon(bitmapProfile)
                        .setContentTitle("Here is Content Title Scene1")
                        .setContentText("A content message of scene1")
                        .setTicker("Here I can view scene1..")
                        .setLights(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent), 1000, 1000)
                        .setAutoCancel(true)
                        .setStyle(inboxStyle[0])
//                        .addAction(R.drawable.reply_released, "Reply", pendingIntentFCM)
                        .setContentIntent(pendingIntent);
                notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

                bigPictureStyle = new NotificationCompat.BigPictureStyle(notifiBuilder);
                bigPictureStyle.bigLargeIcon(bitmapProfile);
                bigPictureStyle.bigPicture(bitmapProfile);
                bigPictureStyle.setBigContentTitle("Attachment");
                bigPictureStyle.setSummaryText("Click on the image for full screen preview");

                notificationManager.notify(0 /*ID of notification*/, bigPictureStyle.build());
//                notificationManager.notify(0 /*ID of notification*/, notifiBuilder.build());

                break;
            case R.id.signal2:
                if(inboxStyle[1] == null)
                    inboxStyle[1] = new NotificationCompat.InboxStyle();
                count2++;
                inboxStyle[1].addLine("Line signal2: " + count2);
                inboxStyle[1].setSummaryText("Summary total: " + count2);

                intent = new Intent(this, Result2Activity.class);
                pendingIntent = PendingIntent.getActivity(this, 0/*Request code*/, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                notifiBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.logo_bual)
                        .setLargeIcon(bitmapProfile2)
                        .setContentTitle("Here is Content Title Scene2")
                        .setContentText("A content message of scene2")
                        .setTicker("Here I can view scene2..")
                        .setLights(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark), 1000, 1000)
                        .setAutoCancel(true)
                        .setStyle(inboxStyle[1])
//                        .addAction(R.drawable.reply_released, "Reply", pendingIntentFCM)
                        .setContentIntent(pendingIntent);
                notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

                bigPictureStyle = new NotificationCompat.BigPictureStyle(notifiBuilder);
                bigPictureStyle.bigLargeIcon(bitmapProfile2);
                bigPictureStyle.bigPicture(bitmapProfile2);
                bigPictureStyle.setBigContentTitle("Attachment");
                bigPictureStyle.setSummaryText("Click on the image for full screen preview");

                notificationManager.notify(1 /*ID of notification*/, bigPictureStyle.build());
//                notificationManager.notify(1 /*ID of notification*/, notifiBuilder.build());

                break;
            default:
                break;
        }
    }

    public static Bitmap drawableToBitmap (Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
