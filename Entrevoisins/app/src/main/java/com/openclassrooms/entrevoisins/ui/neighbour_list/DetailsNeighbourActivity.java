package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsNeighbourActivity extends AppCompatActivity {
    @BindView(R.id.profile_image)
    ImageView mImage;
    @BindView(R.id.profile_name)
    TextView mProfileName;
    @BindView(R.id.profile_name2)
    TextView mProfileName2;
    @BindView(R.id.place)
    TextView mPlace;
    @BindView(R.id.phone)
    TextView mPhone;
    @BindView(R.id.link)
    TextView mLink;
    @BindView(R.id.about_me)
    TextView mAboutMe;
    @BindView(R.id.Up)
    ImageView mBackButton;
    @BindView(R.id.floatingFavoriteButton)
    FloatingActionButton mFavoriteButton;

    private NeighbourApiService mApiService;
    private Boolean isNeighbourFavorite;
    private int sizeFavoriteList;
    private int sizeNeighbourList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_details_neighbour );

        ButterKnife.bind( this );

        mApiService = DI.getNeighbourApiService();
        //get intent
        Intent intent = getIntent();
        Neighbour neighbour = intent.getParcelableExtra( "neighbour" );
        //set view with setNeighbour(neighbour)
        setNeighbour( neighbour );

        isNeighbourFavorite = neighbour.isFavorite();
        setFavoriteButton( isNeighbourFavorite );

        sizeFavoriteList = mApiService.getFavoriteNeighbours().size();
        sizeNeighbourList = mApiService.getNeighbours().size();

        mBackButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( v.getContext(), ListNeighbourActivity.class );
                finish();
                v.getContext().startActivity( intent );

            }
        } );

        mFavoriteButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast;
                //On bloque l'ajout aux favoris à (taille list Neighbour - 1)
                if (!isNeighbourFavorite) {
                    toast = Toast.makeText( getApplicationContext(), neighbour.getName() + " Favorite = YES !", Toast.LENGTH_SHORT );
                    mApiService.switchNeighbourTypeFavorite( neighbour, false );
                    isNeighbourFavorite = true;
                } else {
                    toast = Toast.makeText( getApplicationContext(), neighbour.getName() + " Favorite = NO !", Toast.LENGTH_SHORT );
                    mApiService.switchNeighbourTypeFavorite( neighbour, true );
                    isNeighbourFavorite = false;
                }
                toast.show();
                setFavoriteButton( isNeighbourFavorite );
            }
        } );
    }

    public void setNeighbour(Neighbour neighbour) {

        String url = neighbour.getAvatarUrl().replace("150" , "300");
        Glide.with(this)
                .load(url)
                .into(mImage);

        String profileName = neighbour.getName();
        mProfileName.setText( profileName );
        mProfileName2.setText(profileName);

        String place = neighbour.getAddress().replace(";" , "à");
        mPlace.setText( place );

        String phone = neighbour.getPhoneNumber();
        mPhone.setText( phone );

        String link =  neighbour.getName().toLowerCase();
        mLink.setText( "www.facebook.fr//" + link);

        String aboutMe = neighbour.getAboutMe();
        mAboutMe.setText( aboutMe );
    }

    public void setFavoriteButton(Boolean isFavorite ) {
        if (isFavorite == true) {

            mFavoriteButton.setImageResource( R.drawable.ic_star_gold_24dp );
        }
        else {
            mFavoriteButton.setImageResource( R.drawable.ic_star_white_24dp );
        }
    }

}