package com.example.pontovenda.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pontovenda.R;
import com.example.pontovenda.model.Pedido;

import java.util.ArrayList;

public class PedidoListAdapter extends RecyclerView.Adapter<PedidoListAdapter.ViewHolder> {

    private ArrayList<Pedido> listaPedido;
    private Context context;

    public PedidoListAdapter(ArrayList<Pedido> listaPedido, Context context) {
        this.listaPedido = listaPedido;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listaPedido = layoutInflater.inflate(R.layout.item_list_pedidos, parent, false);

        return new ViewHolder(listaPedido);
    }

    @Override
    public void onBindViewHolder(@NonNull PedidoListAdapter.ViewHolder holder, int position) {
        Pedido pedidoSelecionado = listaPedido.get(position);

        Log.e("testeBidHolder", String.valueOf(pedidoSelecionado.getIdPedido()));

        holder.tvId.setText(String.valueOf(pedidoSelecionado.getIdPedido()));
        holder.tvNome.setText(pedidoSelecionado.getNome());
        holder.tvQuantidade.setText(String.valueOf(pedidoSelecionado.getQuantidade()));
        holder.tvValor.setText(String.valueOf(pedidoSelecionado.getValorTotal()));
    }

    @Override
    public int getItemCount() {
        return this.listaPedido.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvId;
        public TextView tvNome;
        public TextView tvQuantidade;
        public TextView tvValor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tvId = itemView.findViewById(R.id.tvId);
            this.tvNome = itemView.findViewById(R.id.tvNome);
            this.tvQuantidade = itemView.findViewById(R.id.tvQuantidade);
            this.tvValor = itemView.findViewById(R.id.tvValor);
        }
    }
}
