package com.secondary.alalamiastorez;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import static android.content.ContentValues.TAG;
import static com.secondary.alalamiastorez.MainActivity.checkoutList;
import static com.secondary.alalamiastorez.MainActivity.compareList;

public class ProductActivity extends Activity implements View.OnClickListener{
    private TextView description;
    private Product product;
    private BottomSheetBehavior bottomSheetBehavior;
    private LinearLayout ll;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.product_activity);
        product = getIntent().getExtras().getParcelable("ReqProduct");

        ll = findViewById(R.id.bottomSheetLayout);
        bottomSheetBehavior = BottomSheetBehavior.from(ll);

        WrapContentHeightViewPager productVP = findViewById(R.id.product_vp);
        ProductVPAdapter productVPAdapter = new ProductVPAdapter(product.getImg(), this);
        productVP.setAdapter(productVPAdapter);

        ((TextView)findViewById(R.id.cpu_details)).setText(product.getCpu());
        ((TextView) findViewById(R.id.ram_details)).setText(product.getRam());
        ((TextView) findViewById(R.id.storage_details)).setText(product.getStorage());
        ((TextView) findViewById(R.id.camera_details)).setText(product.getCamera());
        ((TextView) findViewById(R.id.screen_details)).setText(product.getScreen());
        ((TextView) findViewById(R.id.battery_details)).setText(product.getBattery());
        ((TextView) findViewById(R.id.sim_details)).setText(product.getSim());
        ((TextView) findViewById(R.id.product_price)).setText(product.getPrice()+" EGP");
        ((TextView) findViewById(R.id.product_details)).setText(product.getDetails());

        findViewById(R.id.product_buy).setOnClickListener(this);
        findViewById(R.id.product_back).setOnClickListener(this);
        findViewById(R.id.product_fav).setOnClickListener(this);
        findViewById(R.id.product_compare).setOnClickListener(this);
        findViewById(R.id.product_compare).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                startActivity(new Intent(v.getContext(),CompareActivity.class));
                return true;
            }
        });

        if (product.getOldPrice() != null){
            TextView priceOld = findViewById(R.id.product_oldPrice);
            priceOld.setText(product.getOldPrice());
            priceOld.setPaintFlags(priceOld.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
//
//        description = findViewById(R.id.product_details);
//        description.setText(getResources().getString(R.string.details));
//        description.setTextColor(Color.TRANSPARENT);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.product_back:
                finish();
                break;
            case R.id.product_fav:
                break;
            case R.id.product_compare:
                compareList.add(product);
                Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
                break;
            case R.id.product_buy:
                Log.d(TAG, "onClick: Expaaaaaaaand");

                int i = 0;
                for (String color : product.getColors()) {
                    ImageView iv = new ImageView(this);
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(100, 100);
                    lp.setMargins(0, 0, 5, 0);
                    iv.setLayoutParams(lp);
                    iv.setId(i);
                    iv.setBackground(drawCircle(Color.parseColor(color)));
                    iv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            checkoutList.add(product);
                            Toast.makeText(ProductActivity.this, "Added to cart", Toast.LENGTH_SHORT).show();
                            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                            ll.removeAllViews();
                        }
                    });
                    ll.addView(iv);
                    i++;
                }
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

                checkoutList.add(product);
                Toast.makeText(ProductActivity.this, "Added to cart", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public static GradientDrawable drawCircle(int backgroundColor) {
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.OVAL);
        shape.setCornerRadii(new float[]{0, 0, 0, 0, 0, 0, 0, 0});
        shape.setColor(backgroundColor);
//        shape.setStroke(4, borderColor);
        return shape;
    }

    private static class ProductVPAdapter extends PagerAdapter {

        private ArrayList<String> list;
        private Context mContext;

        ProductVPAdapter(ArrayList<String> list, Context mContext) {
            this.list = list;
            this.mContext = mContext;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.product_img,container,false);
            Glide.with(mContext).load(list.get(position)).into((ImageView)v.findViewById(R.id.product_imgs));
            container.addView(v);
            return v;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }
}
