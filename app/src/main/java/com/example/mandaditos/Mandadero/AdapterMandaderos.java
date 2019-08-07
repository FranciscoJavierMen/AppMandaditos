package com.example.mandaditos.Mandadero;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mandaditos.R;
import com.github.pavlospt.roundedletterview.RoundedLetterView;

import java.util.List;

public class AdapterMandaderos extends RecyclerView.Adapter<AdapterMandaderos.ViewHolder> {

    private List<ListaMandaderos> listaMandaderos;
    private Context context;

    public AdapterMandaderos(List<ListaMandaderos> listaMandaderos, Context context) {
        this.listaMandaderos = listaMandaderos;
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
        final ListaMandaderos modelo = listaMandaderos.get(position);

        holder.nombre.setText(modelo.getNombre());
        holder.icono.setTitleText(extraerLetra(modelo.getNombre()));
        if (modelo.getEstado().equals("ocupado")){
            holder.estado.setColorFilter(ContextCompat.getColor(context, R.color.rojo), android.graphics.PorterDuff.Mode.MULTIPLY);
        }
        if (modelo.getEstado().equals("disponible")){
            holder.estado.setColorFilter(ContextCompat.getColor(context, R.color.verde), android.graphics.PorterDuff.Mode.MULTIPLY);
        }
    }


    private String extraerLetra(String nombre){
        if (!String.valueOf(nombre).isEmpty()){
            return String.valueOf(nombre.charAt(0));
        }
        return "M";
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

            nombre = itemView.findViewById(R.id.txtNombreServicioM);
            estado = itemView.findViewById(R.id.imgStatusM);
            icono = itemView.findViewById(R.id.circleIconM);
        }
    }
}
