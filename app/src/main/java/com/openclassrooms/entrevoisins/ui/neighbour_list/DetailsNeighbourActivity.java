package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

public class DetailsNeighbourActivity extends AppCompatActivity {

    private FloatingActionButton mFavoriteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_neighbour);

        // Neighbour to display
        Neighbour neighbour = getIntent().getParcelableExtra("Neighbour");

        // Associate views with the corresponding id's in the layout
        ImageView profilPicture = findViewById(R.id.activity_details_profil_picture);
        TextView name1Text = findViewById(R.id.activity_details_name1_text);
        TextView name2Text = findViewById(R.id.activity_details_name2_text);
        TextView locationText = findViewById(R.id.activity_details_location_text);
        TextView phoneText = findViewById(R.id.activity_details_phone_text);
        TextView siteText = findViewById(R.id.activity_details_site_txt);
        mFavoriteButton = findViewById(R.id.activity_details_favorite_btn);
        TextView aboutMe = findViewById(R.id.activity_details_about_me_text);

        // Retrieve all the data nedded to display the neighbour details
        Glide.with(this)
                .load(neighbour.getAvatarUrl())
                .into(profilPicture);
        name1Text.setText(neighbour.getName());
        name2Text.setText(neighbour.getName());
        locationText.setText(neighbour.getAddress());
        phoneText.setText(neighbour.getPhoneNumber());
        siteText.setText(getString(R.string.facebook) + neighbour.getName().toLowerCase());
        aboutMe.setText(neighbour.getAboutMe());

        // Is the neighbor already in the favorites list
        NeighbourApiService apiService = DI.getNeighbourApiService();
        Boolean favorite = apiService.getFavoriteNeighbour(neighbour);
        if (favorite){
            mFavoriteButton.setImageResource(R.drawable.ic_baseline_star_24);
        } else {
            mFavoriteButton.setImageResource(R.drawable.ic_baseline_star_border_24);
        }
        //Detection of click on favorite button
        mFavoriteButton.setOnClickListener(v -> {
            if (apiService.getFavoriteNeighbour(neighbour)){
                mFavoriteButton.setImageResource(R.drawable.ic_baseline_star_border_24);
                apiService.deleteFavoriteNeighbour(neighbour);
            } else {
                mFavoriteButton.setImageResource(R.drawable.ic_baseline_star_24);
                apiService.addFavoriteNeighbour(neighbour);
            }
        });
    }
}