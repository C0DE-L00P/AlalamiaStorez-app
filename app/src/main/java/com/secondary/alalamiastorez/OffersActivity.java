package com.secondary.alalamiastorez;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.secondary.alalamiastorez.MainActivity.offersList;

public class OffersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offers);
        getSupportActionBar().setTitle("Offers");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        EmptyRecyclerView erv =findViewById(R.id.offersAll_erv);
        OffersAcAdapter offersAcAdapter = new OffersAcAdapter(this,offersList);
        erv.setAdapter(offersAcAdapter);
        erv.setLayoutManager(new LinearLayoutManager(this));
        erv.setEmptyView((ImageView)findViewById(R.id.emptyView),null);
        //TODO: EmptyRecyclerView Image
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private static class OffersAcAdapter extends RecyclerView.Adapter<OffersAcAdapter.OfferAcHolder>{

        private Context mContext;
        private ArrayList<Offer> list;

        OffersAcAdapter(Context mContext, ArrayList<Offer> list) {
            this.mContext = mContext;
            this.list = list;
        }

        @NonNull
        @Override
        public OfferAcHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.offer,parent,false);
            return new OfferAcHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull OfferAcHolder holder, int position) {

            if (!list.get(position).getTitle().trim().isEmpty()) holder.title.setText(list.get(position).getTitle());
            if (!list.get(position).getDetails().trim().isEmpty()) holder.details.setText(list.get(position).getDetails());
            if (list.get(position).getFimg() != null) Glide.with(mContext).load(list.get(position).getFimg()).into(holder.fimg);
            if (list.get(position).getImg() != null) Glide.with(mContext).load(list.get(position).getImg()).into(holder.img);
        }


        @Override
        public int getItemCount() {
            return list.size();
        }

        static class OfferAcHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            private TextView title,details;
            private ImageView img,fimg;

            OfferAcHolder(@NonNull View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.offer_title);
                details = itemView.findViewById(R.id.offer_details);
                img = itemView.findViewById(R.id.offer_img);
                fimg = itemView.findViewById(R.id.offer_fimg);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                //TODO: add Action to it
                Toast.makeText(v.getContext(), "Place Holder Action", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
