package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;

public class DetailsNeighbourActivity extends AppCompatActivity {

    private ImageView mProfilPicture;
    private TextView mName1Text;
    private TextView mName2Text;
    private TextView mLocationText;
    private TextView mPhoneText;
    private TextView mSiteText;
    private FloatingActionButton mFavoriteButton;
    private TextView mAboutMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_neighbour);

        // Récupération du voisin
        Neighbour neighbour = getIntent().getParcelableExtra("Neighbour");

        //
        mProfilPicture = (ImageView) findViewById(R.id.activity_details_profil_picture);
        mName1Text = (TextView) findViewById(R.id.activity_details_name1_text);
        mName2Text = (TextView) findViewById(R.id.activity_details_name2_text);
        mLocationText = (TextView) findViewById(R.id.activity_details_location_text);
        mPhoneText = (TextView) findViewById(R.id.activity_details_phone_text);
        mSiteText = (TextView) findViewById(R.id.activity_details_site_txt);
        mFavoriteButton = (FloatingActionButton) findViewById(R.id.activity_details_favorite_btn);
        mAboutMe = (TextView) findViewById(R.id.activity_details_about_me_text);

        // Alimentation des différents champs avec les attributs du voisin
        Glide.with(this)
                .load(neighbour.getAvatarUrl())
                .into(mProfilPicture);
        mName1Text.setText(neighbour.getName());
        mName2Text.setText(neighbour.getName());
        mLocationText.setText(neighbour.getAddress());
        mPhoneText.setText(neighbour.getPhoneNumber());
        mSiteText.setText("www.facebook.fr/" + neighbour.getName().toLowerCase());
        mAboutMe.setText(neighbour.getAboutMe());

        // Gestion du bouton favoris
        if (neighbour.getFavorite()){
            mFavoriteButton.setImageResource(R.drawable.ic_baseline_star_24);
        } else {
            mFavoriteButton.setImageResource(R.drawable.ic_baseline_star_border_24);
        }

        mFavoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}