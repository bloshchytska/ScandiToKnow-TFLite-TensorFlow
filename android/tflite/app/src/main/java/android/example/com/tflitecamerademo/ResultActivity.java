package android.example.com.tflitecamerademo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.hardware.camera2.CameraAccessException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.android.tflitecamerademo.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.example.android.tflitecamerademo.CameraActivity.sCustomFont;
import static com.example.android.tflitecamerademo.CameraActivity.sStoryFont;

public class ResultActivity extends Activity {

    private static TextView textViewStoryName;
    private static TextView storyTextView;
    private static ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewStoryName = (TextView) findViewById(R.id.textViewStoryName);
        storyTextView = (TextView) findViewById(R.id.storyTextView);
        backButton = (ImageButton) findViewById(R.id.back);
        textViewStoryName.setTypeface(sCustomFont, Typeface.BOLD);
        storyTextView.setTypeface(sStoryFont, Typeface.BOLD);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            String storyNameText = extras.getString("STORY_NAME").toUpperCase();

            if (extras.getString("STORY_NAME") == "huginundmunin") {
                storyNameText = "HUGIN UND MUNIN";
            } else if (extras.getString("STORY_NAME") == "hellaundhellheim") {
                storyNameText = "HELLA UND HELLHEIM";
            } else if (extras.getString("STORY_NAME") == "geriundfreki") {
                storyNameText = "GERI UND FREKI";
            }


            textViewStoryName.setText(storyNameText);

            String story = readTextFile(extras.getString("STORY_NAME"));

            storyTextView.setText(story);

            if (story.length() > 0) {
            } else {
                storyTextView.setText("Keine Geschichte gefunden");
            }

        } else {
            textViewStoryName.setText("???");
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
    }

    private String readTextFile(String name) {

        BufferedReader reader = null;
        String textFromFile = "";
        String textFileName = "text/" + name + ".txt";

        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open(textFileName), "UTF-8"));

            String mLine = "";
            while ((mLine = reader.readLine()) != null) {
                textFromFile += mLine;
            }
        } catch (IOException e) {
            Log.e("exception", e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e("exception by closing", e.getMessage());
                }
            }
        }
        return textFromFile;
    }
}
