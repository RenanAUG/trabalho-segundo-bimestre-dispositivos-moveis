package com.example.pontovenda.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pontovenda.R;
import com.example.pontovenda.model.Caixa;

import java.util.ArrayList;

public class CaixaListAdapter extends RecyclerView.Adapter<CaixaListAdapter.ViewHolder>{

    private ArrayList<Caixa> listaCaixa;

    private Context context;


    public CaixaListAdapter(ArrayList<Caixa> listaCaixa, Context context) {
        this.listaCaixa = listaCaixa;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listaCaixa = layoutInflater.inflate(R.layout.item_list_caixa, parent, false);

        return new ViewHolder(listaCaixa);
    }

    @Override
    public void onBindViewHolder(@NonNull CaixaListAdapter.ViewHolder holder, int position) {
        Caixa caixaSelecionado = listaCaixa.get(position);

        holder.tvidCaixa.setText(String.valueOf(caixaSelecionado.getIdCaixa()));
        holder.tvDataAbertura.setText(caixaSelecionado.getDataAbertura());
        holder.tvDataFechamento.setText(caixaSelecionado.getDataFechacmento());
    }

    @Override
    public int getItemCount() {
        return this.listaCaixa.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvidCaixa;
        public TextView tvDataAbertura;
        public TextView tvDataFechamento;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvidCaixa = itemView.findViewById(R.id.tvIdCaixa);
            tvDataAbertura = itemView.findViewById(R.id.tvDataAbertura);
            tvDataFechamento = itemView.findViewById(R.id.tvDataFechamento);
        }
    }
}
