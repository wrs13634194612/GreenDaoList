package com.example.iosdialogdemo;



        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

public class LanguageViewHolder extends RecyclerView.ViewHolder {
    TextView orderNumber;
    TextView language;
    Button btnDelete;

    public LanguageViewHolder(@NonNull View itemView) {
        super(itemView);
        orderNumber = itemView.findViewById(R.id.tvOrderNumber);
        language = itemView.findViewById(R.id.tvLanguage);
        btnDelete = itemView.findViewById(R.id.btnDelete);
    }
}