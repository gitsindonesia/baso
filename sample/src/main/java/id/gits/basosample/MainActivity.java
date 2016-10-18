package id.gits.basosample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.Date;

import id.gits.baso.BasoProgressView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    BasoProgressView mBasoProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBasoProgressView = (BasoProgressView) findViewById(R.id.baso_ProgressView);
        findViewById(R.id.baso_btnStart).setOnClickListener(this);
        findViewById(R.id.baso_btnStopAndError).setOnClickListener(this);
        findViewById(R.id.baso_btnStopAndErrorNoImage).setOnClickListener(this);
        findViewById(R.id.baso_btnStopAndGone).setOnClickListener(this);

        mBasoProgressView.setOnButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBasoProgressView.startProgress();
            }
        });


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.baso_btnStart) {
            mBasoProgressView.startProgress();
        } else if (v.getId() == R.id.baso_btnStopAndError) {
            mBasoProgressView.setFinishedImageResource(R.drawable.baso_sample_error);
            mBasoProgressView.stopAndError("Error: " + new Date().getTime());
        } else if (v.getId() == R.id.baso_btnStopAndErrorNoImage) {
            mBasoProgressView.setFinishedImageDrawable(null);
            mBasoProgressView.stopAndError("Error: " + new Date().getTime());
        } else if (v.getId() == R.id.baso_btnStopAndGone) {
            mBasoProgressView.stopAndGone();
        }
    }
}
