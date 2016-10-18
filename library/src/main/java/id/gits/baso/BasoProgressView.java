package id.gits.baso;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by ibun on 01/04/16.
 */
public class BasoProgressView extends RelativeLayout {
    public static final int DEF_FINISHED_IMAGE_WIDTH = 120;
    public static final int DEF_FINISHED_TEXT_SIZE = 18;

    private ViewGroup mStoppedLayout;
    private TextView mStoppedTextView;
    private ImageView mStoppedImageView;
    private Button mStoppedButton;

    private ViewGroup mProgressingLayout;
    private ProgressBar mProgressingBar;
    private TextView mProgressingTextView;

    private String mFinishedButtonText;
    private Drawable mFinishedButtonBackground;
    private String mFinishedText;
    private float mFinishedTextSize;
    private Drawable mFinishedImageDrawable;
    private int mFinishedImageWidth;
    private int mFinishedImageHeight;

    private String mProgressText;

    private BasoStatus mBasoStatus = BasoStatus.PROGRESSING;

    private enum BasoStatus {
        PROGRESSING,
        ERROR,
        GONE
    }

    public BasoProgressView(Context context) {
        this(context, null);
    }

    public BasoProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);

        if (isInEditMode()) {
            mBasoStatus = BasoStatus.ERROR;
        }
        injectViews();
        applyAttrs(context, attrs);
    }

    private void injectViews() {
        inflate(getContext(), R.layout.view_baso, this);
        mProgressingLayout = (ViewGroup) findViewById(R.id.baso_ProgressingLayout);
        mProgressingTextView = (TextView) findViewById(R.id.baso_ProgressingText);
        mStoppedLayout = (ViewGroup) findViewById(R.id.baso_StoppedLayout);
        mStoppedTextView = (TextView) findViewById(R.id.baso_StoppedText);
        mStoppedImageView = (ImageView) findViewById(R.id.baso_StoppedImage);
        mStoppedButton = (Button) findViewById(R.id.baso_StoppedButton);
    }

    private void applyAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.BasoProgressView,
                0, 0);

        try {
            mFinishedButtonText = a.getString(R.styleable.BasoProgressView_baso_finishButtonText);
            mFinishedButtonBackground = a.getDrawable(R.styleable.BasoProgressView_baso_finishButtonBackground);
            mFinishedText = a.getString(R.styleable.BasoProgressView_baso_finishText);
            float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, DEF_FINISHED_TEXT_SIZE, context.getResources().getDisplayMetrics());
            mFinishedTextSize = a.getDimension(R.styleable.BasoProgressView_baso_finishTextSize, px);
            mFinishedImageDrawable = a.getDrawable(R.styleable.BasoProgressView_baso_finishSrc);
            px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEF_FINISHED_IMAGE_WIDTH, context.getResources().getDisplayMetrics());
            mFinishedImageWidth = (int) a.getDimension(R.styleable.BasoProgressView_baso_finishSrcWidth, px);
            mFinishedImageHeight = (int) a.getDimension(R.styleable.BasoProgressView_baso_finishSrcHeight, 0);
            mProgressText = a.getString(R.styleable.BasoProgressView_baso_progressText);
        } finally {
            a.recycle();
        }

        initLayout();
    }

    private void initLayout() {
        //init progressing
        mStoppedButton.setVisibility(TextUtils.isEmpty(mProgressText) ? View.GONE : View.VISIBLE);
        mProgressingTextView.setText(mProgressText);

        //init finished button
        setFinishedButtonText(mFinishedButtonText);
        setFinishedButtonBackground(mFinishedButtonBackground);

        //init finished image
        setFinishedImageDrawable(mFinishedImageDrawable);
        setFinishedImageLayoutParam(mFinishedImageWidth, mFinishedImageHeight);

        //init finished text
        setFinishedText(mFinishedText);
        setFinishedTextSize(mFinishedTextSize);

        //toggle layout
        toggleLayout();
    }

    private void toggleLayout() {
        if (mBasoStatus == BasoStatus.PROGRESSING) {
            mStoppedLayout.setVisibility(View.GONE);
            mProgressingLayout.setVisibility(View.VISIBLE);
            setVisibility(View.VISIBLE);
        } else if (mBasoStatus == BasoStatus.ERROR) {
            mStoppedLayout.setVisibility(View.VISIBLE);
            mProgressingLayout.setVisibility(View.GONE);
            setVisibility(View.VISIBLE);
        } else {
            mStoppedLayout.setVisibility(View.GONE);
            mProgressingLayout.setVisibility(View.GONE);
            setVisibility(View.GONE);
        }
    }

    /**
     * Set listener for stopped button click event
     *
     * @param onClickListener
     */
    public void setOnButtonClickListener(OnClickListener onClickListener) {
        mStoppedButton.setOnClickListener(onClickListener);
    }

    /**
     * Show progress bar and progress text below it (if any)
     */
    public void startProgress() {
        mBasoStatus = BasoStatus.PROGRESSING;
        toggleLayout();
    }

    /**
     * Hide all views
     */
    public void stopAndGone() {
        mBasoStatus = BasoStatus.GONE;

        toggleLayout();
    }

    /**
     * Show stopped layout
     */
    public void stop() {
        mBasoStatus = BasoStatus.ERROR;

        toggleLayout();
    }

    /**
     * Set stopped text and show stopped layout
     * @param errorMessage
     */
    public void stopAndError(String errorMessage) {
        mBasoStatus = BasoStatus.ERROR;
        setFinishedText(errorMessage);

        toggleLayout();
    }

    //==== GETTER & SETTER ====//

    public String getFinishedButtonText() {
        return mFinishedButtonText;
    }

    public void setFinishedButtonText(String finishedButtonText) {
        mFinishedButtonText = finishedButtonText;
        mStoppedButton.setVisibility(TextUtils.isEmpty(mFinishedButtonText) ? View.GONE : View.VISIBLE);
        mStoppedButton.setText(mFinishedButtonText);

    }

    public Drawable getFinishedButtonBackground() {
        return mFinishedButtonBackground;
    }

    public void setFinishedButtonBackground(Drawable finishedButtonBackground) {
        mFinishedButtonBackground = finishedButtonBackground;
        if (finishedButtonBackground != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                mStoppedButton.setBackground(mFinishedButtonBackground);
            } else {
                mStoppedButton.setBackgroundDrawable(mFinishedButtonBackground);
            }
        }
    }

    public String getFinishedText() {
        return mFinishedText;
    }

    public void setFinishedText(String finishedText) {
        mFinishedText = finishedText;
        mStoppedTextView.setVisibility(TextUtils.isEmpty(mFinishedText) ? View.GONE : View.VISIBLE);
        mStoppedTextView.setText(mFinishedText);
    }

    public float getFinishedTextSize() {
        return mFinishedTextSize;
    }

    public void setFinishedTextSize(float finishedTextSize) {
        mFinishedTextSize = finishedTextSize;
        mStoppedTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mFinishedTextSize);
    }

    public Drawable getFinishedImageDrawable() {
        return mFinishedImageDrawable;
    }

    public void setFinishedImageDrawable(Drawable finishedImageDrawable) {
        mFinishedImageDrawable = finishedImageDrawable;
        mStoppedImageView.setVisibility(mFinishedImageDrawable == null ? View.GONE : View.VISIBLE);
        if (mFinishedImageDrawable != null) {
            mStoppedImageView.setImageDrawable(mFinishedImageDrawable);
        }
    }

    public void setFinishedImageResource(@DrawableRes int resource) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), resource);
        setFinishedImageDrawable(drawable);
    }

    public float getFinishedImageWidth() {
        return mFinishedImageWidth;
    }

    public float getFinishedImageHeight() {
        return mFinishedImageHeight;
    }

    public void setFinishedImageLayoutParam(int width, int height) {
        mFinishedImageWidth = width;
        mFinishedImageHeight = height;

        int sWidth = mFinishedImageWidth;
        if (mFinishedImageWidth <= 0) {
            sWidth = LinearLayout.LayoutParams.WRAP_CONTENT;
        }

        int sHeight = mFinishedImageHeight;
        if (mFinishedImageHeight <= 0) {
            sHeight = LinearLayout.LayoutParams.WRAP_CONTENT;
        }

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(sWidth, sHeight);
        params.bottomMargin = (int) (getResources().getDisplayMetrics().density * 24);
        mStoppedImageView.setLayoutParams(params);
    }

}
