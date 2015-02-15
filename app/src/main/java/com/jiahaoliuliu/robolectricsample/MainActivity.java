package com.jiahaoliuliu.robolectricsample;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.OutputStream;


public class MainActivity extends ActionBarActivity {

    TextView my_hello_text_view;
    Button mClickMeBtn;
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        System.out.println("Calling static method on AppUtils " + new AppUtils().getNumberUsersRandomly());
        setContentView(R.layout.activity_main);
        my_hello_text_view = (TextView) findViewById(R.id.my_hello_text_view);
        mClickMeBtn = (Button) findViewById(R.id.clickMeBtn);

        int w = 200, h = 200;
        Bitmap.Config conf = Bitmap.Config.ARGB_8888; // see other conf types
        Bitmap bmp = Bitmap.createBitmap(w, h, conf); // this creates a MUTABLE bitmap
        bmp.compress(Bitmap.CompressFormat.PNG, 200, new OutputStream() {
            @Override
            public void write(int oneByte) throws IOException {
                System.out.println("Writting in the outputStream " + oneByte);
            }
        });
    }

    public void clickMeBtnPressed(View view) {
        my_hello_text_view.setText(getString(R.string.ok_thanks));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
