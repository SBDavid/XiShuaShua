package com.sbdavid.www.xishuashua;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int PICK_CONTACT_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView)findViewById(R.id.myTextView);
        textView.setText(textView.getText() + " onCreate");

    }

    @Override
    protected void onPause() {
        super.onPause();
        TextView textView = (TextView)findViewById(R.id.myTextView);
        textView.setText(textView.getText() + " onPause");

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void jump(View view)  {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, PICK_CONTACT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == PICK_CONTACT_REQUEST) {
            Cursor cursor = getContentResolver().query(data.getData(), new String[] {ContactsContract.Contacts.DISPLAY_NAME}, null, null, null);
            if (cursor.moveToFirst()) { // True if the cursor is not empty
                int columnIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                String name = cursor.getString(columnIndex);
                Intent intent = new Intent(this,ShowNameActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        TextView textView = (TextView)findViewById(R.id.myTextView);
        textView.setText(textView.getText() + " onStart");
    }
    @Override
    protected void onResume() {
        super.onResume();
        TextView textView = (TextView)findViewById(R.id.myTextView);
        textView.setText(textView.getText() + " onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        TextView textView = (TextView)findViewById(R.id.myTextView);
        textView.setText(textView.getText() + " onStop");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        TextView textView = (TextView)findViewById(R.id.myTextView);
        textView.setText(textView.getText() + " onDestroy");
    }
}
