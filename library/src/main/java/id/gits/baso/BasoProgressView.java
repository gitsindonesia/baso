package id.gits.baso;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by ibun on 01/04/16.
 */
public class BasoProgressView extends RelativeLayout {
    private ViewGroup stoppedLayout;
    private TextView stoppedTvMessage;
    private ImageView stoppedPicture;
    //    private ImageView progressingPicture;
    private Button stoppedButton;

    private ProgressBar progressBar;
    private ViewGroup progressLayout;
    private TextView progressText;

    private String mProgressText;
    private String mRetryText;
    private String mErrorText;
    private boolean mIsProgressing;
    private boolean mIsError;
    private Drawable mStoppedImageDrawable;
    private boolean mIsRetriable;

    public BasoProgressView(Context context) {
        super(context);
        injectViews();
    }

    public BasoProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        injectViews();

        applyAttrs(context, attrs);
    }

    public BasoProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        injectViews();

        applyAttrs(context, attrs);
    }

    private void applyAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.BasoProgressView,
                0, 0);

        try {
            mStoppedImageDrawable = a.getDrawable(R.styleable.BasoProgressView_srcErrorImage);
            mProgressText = a.getString(R.styleable.BasoProgressView_progressText);
            mErrorText = a.getString(R.styleable.BasoProgressView_defaultErrorText);
            mRetryText = a.getString(R.styleable.BasoProgressView_retryText);
            mIsProgressing = a.getBoolean(R.styleable.BasoProgressView_firstProgressing, true);
            mIsRetriable = a.getBoolean(R.styleable.BasoProgressView_retriable, false);
        } finally {
            a.recycle();
        }

        initLayout();
    }

    private void initLayout() {
        if (mIsError) {
            stoppedTvMessage.setText(mErrorText);
            stoppedButton.setText(mRetryText);
            stoppedPicture.setImageDrawable(mStoppedImageDrawable);
        }

        progressText.setText(mProgressText);
        stoppedButton.setVisibility(mIsRetriable ? View.VISIBLE : View.GONE);
        stoppedLayout.setVisibility(mIsProgressing ? View.GONE : View.VISIBLE);
        progressLayout.setVisibility(mIsProgressing ? View.VISIBLE : View.GONE);
    }

    public void setProgressText(String progressText) {
        mProgressText = progressText;

        initLayout();
    }

    public void setOnRetryClickListener(OnClickListener retryListener) {
        stoppedButton.setOnClickListener(retryListener);
    }

    public void startProgress() {
        mIsProgressing = true;

        initLayout();
    }

    public void stopAndGone() {
        mIsProgressing = false;
        mIsRetriable = false;

        initLayout();
    }

    public void stopAndError() {
        stopAndError(mErrorText, mIsRetriable);
    }

    public void stopAndError(String errorMessage) {
        stopAndError(errorMessage, mIsRetriable);
    }

    public void stopAndError(String errorMessage, boolean isRetriable) {
        mIsProgressing = false;
        mIsRetriable = isRetriable;
        mIsError = true;

        if (!TextUtils.isEmpty(errorMessage))
            mErrorText = errorMessage;

        initLayout();
    }

    private void injectViews() {
        inflate(getContext(), R.layout.view_baso, this);
        progressLayout = (ViewGroup) findViewById(R.id.basoProgressLayout);
        progressText = (TextView) findViewById(R.id.basoProgtessText);
        stoppedLayout = (ViewGroup) findViewById(R.id.basoStoppedLayout);
        stoppedTvMessage = (TextView) findViewById(R.id.basoStoppedText);
        stoppedPicture = (ImageView) findViewById(R.id.basoStoppedImage);
        stoppedButton = (Button) findViewById(R.id.basoStoppedButton);
    }
}
