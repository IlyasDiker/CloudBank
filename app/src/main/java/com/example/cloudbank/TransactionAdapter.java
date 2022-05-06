package com.example.cloudbank;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    Context context;
    ArrayList transaction_id, transaction_account_id, transaction_description, transaction_created_at, transaction_amount;


    Button delete_button;

    TransactionAdapter(Context context,
                       ArrayList transaction_id,
                       ArrayList transaction_account_id,
                       ArrayList transaction_description,
                       ArrayList transaction_amount,
                       ArrayList transaction_created_at){
        this.context = context;
        this.transaction_id = transaction_id;
        this.transaction_account_id = transaction_account_id;
        this.transaction_description = transaction_description;
        this.transaction_amount = transaction_amount;
        this.transaction_created_at = transaction_created_at;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.transaction_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Context appCnt = context.getApplicationContext();

        holder.transaction_recipient.setText(String.valueOf(transaction_account_id.get(position)));
        holder.transaction_row_account_id.setText(String.valueOf(transaction_description.get(position)));
        holder.transaction_amount.setText(String.valueOf(transaction_amount.get(position))+"$");
    }

    @Override
    public int getItemCount() {
        return transaction_id.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView transaction_recipient, transaction_row_account_id, transaction_amount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            transaction_recipient = itemView.findViewById(R.id.transaction_recipient);
            transaction_row_account_id = itemView.findViewById(R.id.transaction_row_account_id);
            transaction_amount = itemView.findViewById(R.id.transaction_amount);
        }
    }
}
