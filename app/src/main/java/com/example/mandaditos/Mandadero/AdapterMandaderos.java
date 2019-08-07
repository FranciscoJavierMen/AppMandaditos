package com.example.mandaditos.Mandadero;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mandaditos.R;
import com.github.pavlospt.roundedletterview.RoundedLetterView;

import java.util.ArrayList;
import java.util.List;

public class AdapterMandaderos extends RecyclerView.Adapter<AdapterMandaderos.ViewHolder> {

    private List<ListaMandaderos> listaMandaderos;

    public AdapterMandaderos(List<ListaMandaderos> listaMandaderos) {
        this.listaMandaderos = listaMandaderos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mandaderos, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ListaMandaderos modelo = listaMandaderos.get(position);

        holder.nombre.setText(modelo.getNombre());
        holder.icono.setTitleText(extraerLetra(modelo.getNombre()));

        if (modelo.getEstado().equals("disponible")){
            holder.estado.setColorFilter(R.color.verde);
        }
        if (modelo.getEstado().equals("ocupado")){
            holder.estado.setColorFilter(R.color.rojo);
        }
    }


    private String extraerLetra(String nombre){
        return String.valueOf(nombre.charAt(0));
    }

    @Override
    public int getItemCount() {
        if (listaMandaderos != null){
            return listaMandaderos.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nombre;
        private ImageView estado;
        private RoundedLetterView icono;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.txtNombreServicio);
            estado = itemView.findViewById(R.id.imgStatus);
            icono = itemView.findViewById(R.id.circleIcon);
        }
    }
}
