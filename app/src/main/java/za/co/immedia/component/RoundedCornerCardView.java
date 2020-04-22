package za.co.immedia.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import za.co.immedia.R;


public class RoundedCornerCardView extends LinearLayout {

    private TextView tvTitleText;
    private LinearLayout llBackground;
    private ImageView ivSuperHero;

    CharSequence urlImage;
    CharSequence titleText;

    public RoundedCornerCardView(Context context, AttributeSet arrSet) {
        super(context,arrSet);
        initView(context, arrSet);
    }

    private void initView(Context mContext, AttributeSet arrSet) {
        inflate(mContext, R.layout.component_rounded_corner_card_view, this);
        tvTitleText = findViewById(R.id.tvTitleText);
        ivSuperHero = findViewById(R.id.ivSuperHero);
        TypedArray typedArray = mContext.obtainStyledAttributes(arrSet, R.styleable.RoundedCornerCardView, 0, 0);
        urlImage = typedArray.getText(R.styleable.RoundedCornerCardView_impactImage);
        titleText = typedArray.getText(R.styleable.RoundedCornerCardView_titleText);

        typedArray.recycle();

        setUrlImage(urlImage);
        setTitleText(titleText);
    }


    public CharSequence getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(CharSequence url) {
        Glide.with(this)
                .load(url)
                .placeholder(R.drawable.thunder) //placeholder
                .into(ivSuperHero);

    }

    public CharSequence getTitleText() {
        return tvTitleText.getText();
    }

    public void setTitleText(CharSequence titleText) {
       tvTitleText.setText(titleText);
    }


}
