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

public class BeneficiaryAdapter extends RecyclerView.Adapter<BeneficiaryAdapter.ViewHolder> {

    Context context;
    ArrayList beneficiary_id, beneficiary_name, beneficiary_account_id;

    Button delete_button;

    BeneficiaryAdapter(Context context,
                       ArrayList beneficiary_id,
                       ArrayList beneficiary_name,
                       ArrayList beneficiary_account_id){
        this.context = context;
        this.beneficiary_id = beneficiary_id;
        this.beneficiary_name = beneficiary_name;
        this.beneficiary_account_id = beneficiary_account_id;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.beneficiary_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Context appCnt = context.getApplicationContext();

        holder.beneficiary_row_name.setText(String.valueOf(beneficiary_name.get(position)));
        holder.beneficiary_row_account_id.setText(String.valueOf(beneficiary_account_id.get(position)));
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper myDB = new DatabaseHelper(appCnt);
                myDB.removeBeneficiary(beneficiary_id.get(position).toString().trim());
            }
        });
    }

    @Override
    public int getItemCount() {
        return beneficiary_name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView beneficiary_row_name, beneficiary_row_account_id;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            beneficiary_row_name = itemView.findViewById(R.id.transaction_recipient);
            beneficiary_row_account_id = itemView.findViewById(R.id.transaction_row_account_id);
            delete_button = itemView.findViewById(R.id.delete_button);

        }
    }
}
