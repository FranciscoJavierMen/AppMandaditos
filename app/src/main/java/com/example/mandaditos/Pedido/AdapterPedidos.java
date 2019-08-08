package com.example.mandaditos.Pedido;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mandaditos.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AdapterPedidos extends RecyclerView.Adapter<AdapterPedidos.ViewHolder> {

    private List<ModeloPedidos> listaPedidos;
    private Context context;

    public AdapterPedidos(List<ModeloPedidos> listaPedidos, Context context) {
        this.listaPedidos = listaPedidos;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterPedidos.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View v = layoutInflater.inflate(R.layout.item_pedidos, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPedidos.ViewHolder holder, int position) {
        final ModeloPedidos model = listaPedidos.get(position);

        holder.mandadero.setText(String.valueOf(model.getIdCliente()));
        holder.descripcion.setText(model.getDescripcion());
        Date date = model.getFecha();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        holder.fecha.setText(strDate);
        holder.estado.setText(model.getEstado());

        switch (model.getEstado()){
            case "aceptado":
                holder.estado.setTextColor(ContextCompat.getColor(context, R.color.verde));
                break;
            case "rechazado":
                holder.estado.setTextColor(ContextCompat.getColor(context, R.color.rojo));
                break;
            case "finalizado":
                holder.estado.setTextColor(ContextCompat.getColor(context, R.color.azul));
                break;
        }
    }

    @Override
    public int getItemCount() {
        if (listaPedidos != null){
            return listaPedidos.size();
        }

        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mandadero, descripcion, fecha, estado;

        public ViewHolder(@NonNull View view) {
            super(view);

            mandadero = view.findViewById(R.id.txtNombreServicio);
            descripcion = view.findViewById(R.id.txtDescripcionPedido);
            fecha = view.findViewById(R.id.txtFechaPedido);
            estado = view.findViewById(R.id.txtEstado);
        }
    }
}
