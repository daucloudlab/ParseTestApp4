package kz.abcsoft.parse.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private TextView mNameTextView;
    private TextView mAgeTextView;
    private TextView mColorTextView;
    private String mName;
    private String mAge;
    private String mColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        mName = intent.getStringExtra("name");
        mAge = intent.getStringExtra("age");
        mColor = intent.getStringExtra("color");

        mNameTextView = (TextView) findViewById(R.id.detailTextViewName);
        mAgeTextView = (TextView) findViewById(R.id.detailTextViewAge);
        mColorTextView = (TextView) findViewById(R.id.detailTextViewColor);

        mNameTextView.setText(mName);
        mAgeTextView.setText(mAge);
        mColorTextView.setText(mColor);

    }
}
