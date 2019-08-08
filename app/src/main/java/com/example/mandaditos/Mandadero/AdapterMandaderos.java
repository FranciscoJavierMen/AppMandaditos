package com.example.mandaditos.Mandadero;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ImageViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mandaditos.R;
import com.github.pavlospt.roundedletterview.RoundedLetterView;

import java.util.List;

public class AdapterMandaderos extends RecyclerView.Adapter<AdapterMandaderos.ViewHolder> {

    private List<ModeloMandaderos> modeloMandaderos;
    private Context context;

    public AdapterMandaderos(List<ModeloMandaderos> modeloMandaderos, Context context) {
        this.modeloMandaderos = modeloMandaderos;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.item_mandaderos, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ModeloMandaderos modelo = modeloMandaderos.get(position);

        holder.nombre.setText(modelo.getNombre());
        holder.icono.setTitleText(extraerLetra(modelo.getNombre()));
        holder.icono.setBackgroundColor(Color.rgb(randomNumber(), randomNumber(), randomNumber()));

        if (modelo.getEstado().equals("disponible")){
            ImageViewCompat.setImageTintList(holder.estado, ContextCompat.getColorStateList(context, R.color.verde));
        }
        if (modelo.getEstado().equals("ocupado")){
            ImageViewCompat.setImageTintList(holder.estado, ContextCompat.getColorStateList(context, R.color.naranja));
        }
    }

    private int randomNumber(){
        return (int)(Math.random()*((255-1)+1))+1;
    }

    private String extraerLetra(String nombre){
        if (!String.valueOf(nombre).isEmpty()){
            return String.valueOf(nombre.charAt(0));
        }
        return "M";
    }

    @Override
    public int getItemCount() {
        if (modeloMandaderos != null){
            return modeloMandaderos.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nombre;
        private ImageView estado;
        private RoundedLetterView icono;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.txtNombreServicioM);
            estado = itemView.findViewById(R.id.imgStatusM);
            icono = itemView.findViewById(R.id.circleIconM);
        }
    }
}
