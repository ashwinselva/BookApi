package android.example.bookapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText mBookInput;
    TextView mTitleText, mAuthorText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBookInput = findViewById(R.id.bookInput);
    }

    public void searchBooks(View view) {
        String queryString = mBookInput.getText().toString();
        mTitleText=findViewById(R.id.bookName);
        mAuthorText=findViewById(R.id.authorName);
        new FetchBookTask(mTitleText, mAuthorText).execute(queryString);
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveData();
    }

    private void saveData() {

        String data = mBookInput.getText().toString();
        //create a file using shared preferences
        SharedPreferences preferences = getSharedPreferences("cognizant_prefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("bookname",data);
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        restoreData();
    }

    private void restoreData() {
        SharedPreferences preferences = getSharedPreferences("cognizant_prefs",MODE_PRIVATE);
        String dataRead = preferences.getString("bookname","");    //getString(s => input parameter, s1=> default input if s is not available)
        mBookInput.setText(dataRead);
    }
}