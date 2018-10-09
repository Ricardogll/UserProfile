package com.example.ricardogl.userprofileapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class UserProfileActivity extends AppCompatActivity {

    private UserInfo userinfo;
    private Gson gson;
    private TextView nameview;
    private TextView handleview;
    private TextView followingview;
    private TextView followersview;
    private TextView aboutview;
    private ImageView profilepicview;
    private ImageView backgroundview;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        gson = new Gson();

        nameview= findViewById(R.id.nameview);
        handleview= findViewById(R.id.handleview);
        followersview= findViewById(R.id.followersview);
        followingview= findViewById(R.id.followingview);
        aboutview= findViewById(R.id.aboutview);
        profilepicview= findViewById(R.id.profilepicview);
        backgroundview= findViewById(R.id.backgroundview);


        try {
            InputStream stream = getAssets().open("RicardoGutierrez.json");
            InputStreamReader reader = new InputStreamReader(stream);
            userinfo = gson.fromJson(reader,UserInfo.class);
            updateUserInfo();
            //Glide.vith    file:///android_asset/

        }catch (IOException e) {
            Toast.makeText(UserProfileActivity.this, "Error loading data", Toast.LENGTH_SHORT).show();
        }





    }


    private void updateUserInfo(){
            String aux = userinfo.getName()+" " + userinfo.getLastname();
            nameview.setText(aux);
            handleview.setText(userinfo.getHandle());
            followingview.setText(userinfo.getFollowing());
            followersview.setText(userinfo.getFollowers());
            aboutview.setText(userinfo.getAbout());

            Glide.with(UserProfileActivity.this).load("file:///android_asset/background.png").into(backgroundview);
            Glide.with(UserProfileActivity.this).load("file:///android_asset/profileimage.png").apply(RequestOptions.circleCropTransform()).into(profilepicview);
    }
}
