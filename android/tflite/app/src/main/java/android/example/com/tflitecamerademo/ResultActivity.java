package android.example.com.tflitecamerademo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.hardware.camera2.CameraAccessException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.tflitecamerademo.CameraActivity;
import com.example.android.tflitecamerademo.R;

import static com.example.android.tflitecamerademo.CameraActivity.sCustomFont;

public class ResultActivity extends Activity {

    private static TextView textViewStoryName;
    private static TextView storyTextView;
    private static Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewStoryName = (TextView) findViewById(R.id.textViewStoryName);
        storyTextView = (TextView) findViewById(R.id.storyTextView);
        textViewStoryName.setTypeface(sCustomFont, Typeface.BOLD);
        storyTextView.setTypeface(sCustomFont, Typeface.BOLD);

        Bundle extras = getIntent().getExtras();


        if (extras != null) {
            String storyNameText = extras.getString("STORY_NAME").toUpperCase();
            Resultmodel resultModel = new Resultmodel();

            storyTextView.setText(resultModel.getTextFor(extras.getString("STORY_NAME")));
            textViewStoryName.setText(storyNameText);
        } else {
            textViewStoryName.setText("???");
        }
    }

}
