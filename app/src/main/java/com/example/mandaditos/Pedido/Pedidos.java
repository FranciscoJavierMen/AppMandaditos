package com.example.mandaditos.Pedido;

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

public class Pedidos extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private SwipeRefreshLayout refreshPedidos;
    private RecyclerView recyclerPedidos;
    private List<ModeloPedidos> listaPedidos;
    private AdapterPedidos adapter;

    public Pedidos() {
        // Required empty public constructor
    }

    public static Pedidos newInstance(String param1, String param2) {
        Pedidos fragment = new Pedidos();
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
        listaPedidos = new ArrayList<>();
        View v = inflater.inflate(R.layout.fragment_pedidos, container, false);

        refreshPedidos = v.findViewById(R.id.swipePedidos);
        recyclerPedidos = v.findViewById(R.id.recyclerPedidos);

        refreshPedidos.setColorSchemeResources(
                R.color.colorPrimary
        );
        refreshPedidos.setProgressBackgroundColorSchemeResource(R.color.fondoClaro);
        refreshPedidos.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });

        cargarDatos();
        return  v;
    }

    private void cargarDatos(){
        ApiInterface api = ApiMandadero
                .getMandaderos()
                .create(ApiInterface.class);

        Call<List<ModeloPedidos>> call = api.getpedidos();
        call.enqueue(new Callback<List<ModeloPedidos>>() {
            @Override
            public void onResponse(Call<List<ModeloPedidos>> call, Response<List<ModeloPedidos>> response) {
                refreshPedidos.setRefreshing(false);
                assert response.body() != null;
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<ModeloPedidos>> call, Throwable t) {
                refreshPedidos.setRefreshing(false);
                Log.d("TAG", "Error: "+t.toString());
                Toast.makeText(getContext(), "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateDataList(List<ModeloPedidos> modeloMandaderos) {
        adapter = new AdapterPedidos(modeloMandaderos, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerPedidos.setLayoutManager(layoutManager);
        recyclerPedidos.setAdapter(adapter);
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
