package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;


public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {


    public ListNeighbourPagerAdapter(FragmentManager fm) {
        super(fm);
    }



    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {

        switch(position) {
            case 0 :
                return NeighbourFragment.newInstance(false); // possibilité d'ajouter un boolean en parametre de newInstance() afin de n'utiliser qu'un seul fragment et layout
            case 1 :
                return NeighbourFragment.newInstance(true);
            default :
                return null;
        }

    }

    /**
     * get the number of pages
     * @return
     */
    @Override
    public int getCount() {
        return 2;
    }


}