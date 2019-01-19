package android.example.com.tflitecamerademo;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RawRes;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.android.tflitecamerademo.R;

import static com.example.android.tflitecamerademo.CameraActivity.sCustomFont;

public class ResultAudioActivity extends Activity {

    private static TextView textViewStoryName;
    private Button buttonPlayStop;
    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    private ImageButton backButton;

    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_audio);
        Bundle extras = getIntent().getExtras();

        backButton = (ImageButton) findViewById(R.id.back);

        buttonPlayStop = (Button) findViewById(R.id.buttonPlayStop);
        buttonPlayStop.setTypeface(sCustomFont, Typeface.BOLD);
        buttonPlayStop.setTextSize(getResources().getDimension(R.dimen.textSizeSmall));

        textViewStoryName = (TextView) findViewById(R.id.textViewStoryName);
        textViewStoryName.setTypeface(sCustomFont, Typeface.BOLD);



        if (extras != null) {
            String storyNameText = extras.getString("STORY_NAME").toUpperCase();
            textViewStoryName.setText(storyNameText);
            initViews(extras.getString("STORY_NAME"));
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

    private void initViews(String name) {
        Resources res = getResources();
        Log.i("info", name);
        int soundId = res.getIdentifier(name, "raw", getPackageName());
        mediaPlayer = MediaPlayer.create(this, soundId);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setMax(mediaPlayer.getDuration());
        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                seekChange(v);
                return false;
            }
        });
    }

    private void seekChange(View v){
        if(mediaPlayer.isPlaying()){
            SeekBar sb = (SeekBar)v;
            mediaPlayer.seekTo(sb.getProgress());
        }
    }

    public void playAndStop(View v){
        if (buttonPlayStop.getText() == getString(R.string.play_str)) {
            buttonPlayStop.setText(getString(R.string.pause_str));
            try{
                mediaPlayer.start();
                startPlayProgressUpdater();
            }catch (IllegalStateException e) {
                mediaPlayer.pause();
            }
        }else {
            buttonPlayStop.setText(getString(R.string.play_str));
            mediaPlayer.pause();
        }
    }

    public void startPlayProgressUpdater() {
        seekBar.setProgress(mediaPlayer.getCurrentPosition());

        if (mediaPlayer.isPlaying()) {
            Runnable notification = new Runnable() {
                public void run() {
                    startPlayProgressUpdater();
                }
            };
            handler.postDelayed(notification,1000);
        }else{
            mediaPlayer.pause();
            buttonPlayStop.setText(getString(R.string.play_str));
            seekBar.setProgress(0);
        }
    }

}
