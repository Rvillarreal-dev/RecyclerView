package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.recyclerview.model.Email;

public class SecondActivity extends AppCompatActivity {

    private TextView mNameResult, mSubjectResult, mBodyResult;
    private Button mSubmit;
    private EditText mEditName, mEditSub, mEditBody;
    private ImageView emailIcon;
    public static String EMAIL_INFO = "EMAIL_OBJECT_INFO";
    public static String EMAIL_POSITION = "EMAIL_OBJECT_POSITION";
    private Email editEmail;
    private int mPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mNameResult = findViewById(R.id.name_tv2);
        mSubjectResult = findViewById(R.id.subject_tv2);
        mBodyResult = findViewById(R.id.body_tv2);
        mSubmit = findViewById(R.id.submit_button);
        mEditName = findViewById(R.id.edit_name);
        mEditSub = findViewById(R.id.edit_subject);
        mEditBody = findViewById(R.id.edit_body);
        emailIcon = findViewById(R.id.email_icon2);

        Intent recievedIntent = getIntent();

        if (recievedIntent != null) {

            editEmail = recievedIntent.getParcelableExtra(EMAIL_INFO);
            mPosition = recievedIntent.getIntExtra(EMAIL_POSITION, 0);

            mNameResult.setText(editEmail.getName());
            mSubjectResult.setText(editEmail.getSubject());
            mBodyResult.setText(editEmail.getBody());
            Glide.with(this)
                    .load(editEmail.getImageUrl())
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(emailIcon);
        }

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent replyIntent = new Intent();
                editEmail.setName(mEditName.getText().toString());
                editEmail.setSubject(mEditSub.getText().toString());
                editEmail.setBody(mEditBody.getText().toString());

                replyIntent.putExtra(EMAIL_INFO, editEmail);
                replyIntent.putExtra(EMAIL_POSITION, mPosition);
                setResult(RESULT_OK, replyIntent);
                finish();
            }
        });
    }
}