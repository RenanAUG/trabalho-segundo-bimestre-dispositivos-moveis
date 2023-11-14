package com.example.pontovenda.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pontovenda.R;
import com.example.pontovenda.model.Pagamento;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PagamentoListAdapter extends RecyclerView.Adapter<PagamentoListAdapter.ViewHolder> {

    private ArrayList<Pagamento> listaPagamento = new ArrayList<>();
    private Context context;

    /**
     * Construtor da classe
     * @param listaPagamento: lista que será utilizado para retornar os dados a serem exibidos
     * @param context: onde será exibido a lista
     */
    public PagamentoListAdapter(ArrayList<Pagamento> listaPagamento, Context context) {
        this.listaPagamento = listaPagamento;
        this.context = context;
    }

    /**
     * Método responsável em carregar o arquivo de layout na tela
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     * @return o arquivo xml com seus componentes
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listPagamento = layoutInflater.inflate(R.layout.item_list_pagamento, parent, false);

        return new ViewHolder(listPagamento);
    }

    /**
     * Método que adiciona os dados de Aluno na tela
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pagamento pagamentoSelecionado = listaPagamento.get(position);
        holder.tvIdPedido.setText(String.valueOf(pagamentoSelecionado.getIdPagamento()));
        holder.tvValor.setText(String.valueOf(pagamentoSelecionado.getValorPagamento()));
        holder.tvDataPagamento.setText(String.valueOf(pagamentoSelecionado.getDataPagamento()));
        holder.tvCodPedido.setText(String.valueOf(pagamentoSelecionado.getCodPedido()));
    }

    /**
     * Retorna a quantidade de elementos contidos na lista
     * @return
     */
    @Override
    public int getItemCount() {
        return this.listaPagamento.size();
    }

    /**Classe que vincula o componente do xml para ser manipulado**/
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvValor;
        public TextView tvDataPagamento;
        public TextView tvCodPedido;
        public TextView tvIdPedido;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tvIdPedido = itemView.findViewById(R.id.tvIdPedido);
            this.tvValor = itemView.findViewById(R.id.tvValor);
            this.tvDataPagamento = itemView.findViewById(R.id.tvDataPagamento);
            this.tvCodPedido = itemView.findViewById(R.id.tvCodPedido);
        }
    }
}
