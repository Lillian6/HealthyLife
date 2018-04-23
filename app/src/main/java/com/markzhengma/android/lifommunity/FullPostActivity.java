package com.markzhengma.android.lifommunity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FullPostActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef;
    private TextView textDisplay;
    private float textSize = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_post);
        textDisplay = (TextView)findViewById(R.id.full_post_content);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Post");

    }

    //inflates the xml file
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
//      return true;
        return super.onCreateOptionsMenu(menu);

    }

    //onOptionsItemSelected() handles menu item actions. Uses switch statement
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.share:
                Toast.makeText(this, "Shared", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.email:
                String title = ((TextView) findViewById(R.id.full_post_title)).getText().toString();
                String content = ((TextView) findViewById(R.id.full_post_content)).getText().toString();
                Toast.makeText(this,"Send email", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_SUBJECT, title);
                intent.putExtra(Intent.EXTRA_TEXT, content);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                return true;
            case R.id.Home_action:
                Intent homeIntent = new Intent(this, MainActivity.class);
                startActivity(homeIntent);
                return true;
            case R.id.Post_action:
                Intent postIntent = new Intent(this, PostActivity.class);
                startActivity(postIntent);
                return true;
            case R.id.Text_Size_Bigger:
                textSize ++;
                textDisplay.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}


