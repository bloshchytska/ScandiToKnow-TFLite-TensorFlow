package android.example.com.tflitecamerademo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android.tflitecamerademo.R;

public class ResultActivity extends Activity {

    private static TextView textViewStoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewStoryName = (TextView) findViewById(R.id.textViewStoryName);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String storyNameText = extras.getString("STORY_NAME");
            textViewStoryName.setText(storyNameText);
        } else {
            textViewStoryName.setText("???");
        }
    }
}
