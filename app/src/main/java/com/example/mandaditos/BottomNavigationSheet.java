package com.example.mandaditos;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.mandaditos.Mandadero.Mandaderos;
import com.example.mandaditos.Pedido.Pedidos;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.navigation.NavigationView;

public class BottomNavigationSheet extends BottomSheetDialogFragment implements
            Pedidos.OnFragmentInteractionListener,
            Mandaderos.OnFragmentInteractionListener,
            Perfil.OnFragmentInteractionListener{

    private Pedidos pedidos;
    private Mandaderos mandaderos;
    private Perfil perfil;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pedidos = new Pedidos();
        mandaderos = new Mandaderos();
        perfil = new Perfil();
    }

    //Método para establecer el fragment seleccionado dentro del FrameLayout
    public void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contenedor, fragment);
        fragmentTransaction.commit();
    }

    public static BottomNavigationSheet newInstance() {

        Bundle args = new Bundle();

        BottomNavigationSheet fragment = new BottomNavigationSheet();
        fragment.setArguments(args);
        return fragment;
    }

    //Bottom Sheet Callback
    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {

        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }

        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            //check the slide offset and change the visibility of close button
            if (slideOffset > 0.5) {
                closeButton.setVisibility(View.VISIBLE);
            } else {
                closeButton.setVisibility(View.VISIBLE);
            }
        }
    };

    private ImageView closeButton;

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(final Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        //Get the content View
        View contentView = View.inflate(getContext(), R.layout.fragment_bottomsheet, null);
        dialog.setContentView(contentView);

        NavigationView navigationView = contentView.findViewById(R.id.navigation_view);

        //implement navigation menu item click event
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_inicio:
                        setFragment(pedidos);
                        dismiss();
                        break;
                    case R.id.nav_mandaderos:
                        setFragment(mandaderos);
                        dismiss();
                        break;
                    case R.id.nav_perfil:
                        setFragment(perfil);
                        dismiss();
                        break;
                }
                return false;
            }
        });
        closeButton = contentView.findViewById(R.id.close_image_view);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dismiss bottom sheet
                dismiss();
            }
        });

        //Set the coordinator layout behavior
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();

        //Set callback
        if (behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
