package com.yangtech.marsstory.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yangtech.marsstory.R;
import com.yangtech.marsstory.model.Page;
import com.yangtech.marsstory.model.Story;


public class StoryActivity extends ActionBarActivity {
    private Story mStory = new Story();
    private ImageView mImageView;
    private TextView mTextView;
    private String mName;
    private Button mButton1;
    private Button mButton2;
    private Page mCurrentpage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        Intent intent = getIntent();
        mName = intent.getStringExtra(getString(R.string.main_key_name));

        if (mName == null) {
            mName = "Friend";
        }
        mButton1 = (Button)findViewById(R.id.story_btn1);
        mButton2 = (Button)findViewById(R.id.story_btn2);
        mImageView = (ImageView)findViewById(R.id.storyImageView);
        mTextView = (TextView)findViewById(R.id.story_text);

        loadPage(0);



    }

    private void loadPage(int choice) {
        mCurrentpage = mStory.getPage(choice);
        Drawable drawable = getResources().getDrawable(mCurrentpage.getImageId());
        mImageView.setImageDrawable(drawable);

        String storyText = String.format(mCurrentpage.getText(), mName);

        mTextView.setText(storyText);

        if (mCurrentpage.isFinal()) {
            mButton1.setVisibility(View.INVISIBLE);
            mButton2.setText("Play Again");
            mButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            return;
        }

        mButton1.setText(mCurrentpage.getChoice1().getText());
        mButton2.setText(mCurrentpage.getChoice2().getText());

        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mCurrentpage.isFinal()) {
                    int nextPage = mCurrentpage.getChoice1().getNextPage();
                    loadPage(nextPage);
                }
            }
        });

        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mCurrentpage.isFinal()){
                    int nextPage = mCurrentpage.getChoice2().getNextPage();
                    loadPage(nextPage);
                }
            }
        });

    }
}