package com.example.recyclerview;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recyclerview.Adapters.EmailAdapter;
import com.example.recyclerview.model.Email;
import com.example.recyclerview.util.EmailClickListener;

import java.util.ArrayList;
import java.util.List;

//STEP 4 FOR ALLOWING YOUR ITEMS TO BE CLICKED:
//make your activity implement your item click listener interface
public class MainActivity extends AppCompatActivity implements EmailClickListener {

    public static final int MAIN_ACTIVITY_REQUEST = 1001;

    //STEP 5: Declare and initialize your recyclerview
    private RecyclerView mEmailRv;
    private List<Email> mEmailList;
    private TextView mMyTextView;
    private EmailAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        getData();
        setUpRecyclerView();
    }

    //I'll primarily use this function to just initialize my views in my onCreate method
    private void initializeViews() {
        //STEP 5 (CONTINUED)
        mEmailRv = findViewById(R.id.email_rv);
        mMyTextView = findViewById(R.id.my_text_view);
    }

    private void getData() {
        //STEP 0: HAVE SOME DATA TO BE DISPLAYED TO A RECYCLERVIEW
        //Let's create a list of emails that we later want to display onto our
        //recyclerview
        mEmailList = new ArrayList<Email>();
        for (int i=0; i<100; i++) {
            Email currentEmail = new Email(
                    "name " + i,
                    "subject " + i + "i",
                    "body " + i,
                    "https://purepng.com/public/uploads/large/purepng.com-mail-iconsymbolsiconsapple-iosiosios-8-iconsios-8-721522596075clftr.png"
            );

            mEmailList.add(currentEmail);
        }
    }

    public void setUpRecyclerView() {
        //STEP 6: Initialize our adapter and set it to our recyclerview
        myAdapter = new EmailAdapter(mEmailList);
        mEmailRv.setAdapter(myAdapter);

        //STEP 7: Set a linear layout manager to our recyclerview
        mEmailRv.setLayoutManager(
                new LinearLayoutManager(
                        this,
                        RecyclerView.VERTICAL,
                        false
                )
        );
    }

    @Override
    public void onEmailClicked(View view, int position) {

        Email temp  = new Email(
                mEmailList.get(position).getName(),
                mEmailList.get(position).getSubject(),
                mEmailList.get(position).getBody(),
                mEmailList.get(position).getImageUrl());

        Intent intent = new Intent(MainActivity.this, SecondActivity.class);

        intent.putExtra(SecondActivity.EMAIL_INFO, temp);
        intent.putExtra(SecondActivity.EMAIL_POSITION, position);

        startActivityForResult(intent, MAIN_ACTIVITY_REQUEST);

        //STEP 4B FOR ALLOWING YOUR ITEMS TO BE CLICKED
        //define this method
        //define what we want to do in our on click
        Toast.makeText(this,
                "We are now displaying " + mEmailList.get(position).getName(),
                Toast.LENGTH_LONG).show();
        mMyTextView.setText("We are now displaying " + mEmailList.get(position).getName());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == MAIN_ACTIVITY_REQUEST){
            if (resultCode == RESULT_OK){
                if(data != null){
                    Email resultEmail = data.getParcelableExtra(SecondActivity.EMAIL_INFO);
                    int position = data.getIntExtra(SecondActivity.EMAIL_POSITION, 0);
                    myAdapter.setEmail(position, resultEmail);
                }
            }
        }
    }
}