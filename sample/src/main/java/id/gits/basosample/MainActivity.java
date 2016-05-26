package id.gits.basosample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import id.gits.baso.BasoProgressView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    BasoProgressView mBasoProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mBasoProgressView = (BasoProgressView) findViewById(R.id.basoProgressView);
        findViewById(R.id.btnStart).setOnClickListener(this);
        findViewById(R.id.btnStop).setOnClickListener(this);

        mBasoProgressView.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBasoProgressView.startProgress();
            }
        });


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnStart) {
            mBasoProgressView.startProgress();
        } else if (v.getId() == R.id.btnStop) {
            mBasoProgressView.stopAndError();
        }
    }
}
