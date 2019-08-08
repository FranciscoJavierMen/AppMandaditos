package com.example.mandaditos.Mandadero;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mandaditos.R;
import com.example.mandaditos.Services.ApiInterface;
import com.example.mandaditos.Services.ApiMandadero;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Mandaderos extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    //Elementos de las vistas
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView recyclerMandaderos;
    private List<ModeloMandaderos> modeloMandaderos;
    private AdapterMandaderos adapter;

    public Mandaderos() {
        // Required empty public constructor
    }

    public static Mandaderos newInstance(String param1, String param2) {
        Mandaderos fragment = new Mandaderos();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        modeloMandaderos = new ArrayList<>();
        View v = inflater.inflate(R.layout.fragment_mandaderos, container, false);
        refreshLayout = v.findViewById(R.id.swipeMandaderos);
        recyclerMandaderos = v.findViewById(R.id.recyclerMandaderos);

        refreshLayout.setColorSchemeResources(
                R.color.colorPrimary
        );
        refreshLayout.setProgressBackgroundColorSchemeResource(R.color.fondoClaro);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                cargarDatos();
            }
        });

        cargarDatos();
        return v;
    }

    private void cargarDatos(){
        ApiInterface api = ApiMandadero
                .getMandaderos()
                .create(ApiInterface.class);

        Call<List<ModeloMandaderos>> call = api.getMandaderos();
        call.enqueue(new Callback<List<ModeloMandaderos>>() {
            @Override
            public void onResponse(Call<List<ModeloMandaderos>> call, Response<List<ModeloMandaderos>> response) {
                refreshLayout.setRefreshing(false);
                assert response.body() != null;
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<ModeloMandaderos>> call, Throwable t) {
                refreshLayout.setRefreshing(false);
                Log.d("TAG", "Error: "+t.toString());
                Toast.makeText(getContext(), "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateDataList(List<ModeloMandaderos> modeloMandaderos) {
        adapter = new AdapterMandaderos(modeloMandaderos, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerMandaderos.setLayoutManager(layoutManager);
        recyclerMandaderos.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
