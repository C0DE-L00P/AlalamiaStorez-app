package com.secondary.alalamiastorez;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class OffersAdapter extends PagerAdapter {

    private Context mContext;
    private ArrayList<Offer> list;

    public OffersAdapter(Context mContext, ArrayList<Offer> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.offer,container,false);
        if (!list.get(position).getTitle().trim().isEmpty())
        ((TextView) v.findViewById(R.id.offer_title)).setText(list.get(position).getTitle());
        if (!list.get(position).getDetails().trim().isEmpty()) ((TextView) v.findViewById(R.id.offer_details)).setText(list.get(position).getDetails());
        if (list.get(position).getImg() != null) {
            ImageView fimg = v.findViewById(R.id.offer_fimg);
            //TODO: to complete animation
//        fimg.animate().translationY(0);
            Glide.with(mContext).load(list.get(position).getFimg()).into(fimg);
        }
        if (list.get(position).getImg() != null) Glide.with(mContext).load(list.get(position).getImg()).into((ImageView) v.findViewById(R.id.offer_img));
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
