package com.secondary.alalamiastorez;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnProductClickListener,View.OnClickListener{

    public static ArrayList<Product> compareList = new ArrayList<>();
    public static ArrayList<Product> checkoutList = new ArrayList<>();
    ArrayList<Product> list;
    public static ArrayList<Offer> offersList;
    Product p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        offersList = new ArrayList<>();
        offersList.add(new Offer("","","https://i.imgur.com/yjapZ8Z.png",""));
        offersList.add(new Offer("Offers Festival","Don't miss many offers coming ahead","https://png.pngtree.com/thumb_back/fh260/background/20190221/ourmid/pngtree-red-tilt-texture-offer-image_11168.jpg","https://i.imgur.com/HOj3XFE.png"));
//        offersList.add(new Offer(" ","Many offers come ahead","https://static.toiimg.com/thumb/msid-75851805,imgsize-515790,width-800,height-600,resizemode-75/75851805.jpg","https://assorted.downloads.oppo.com/static/archives/images/dd/Smartphones/Reno/Exterior-460-x-542.png"));

        final WrapContentHeightViewPager wcvp = findViewById(R.id.offers_vp);
        OffersAdapter oa = new OffersAdapter(this,offersList);
        wcvp.setAdapter(oa);

        wcvp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

            @Override
            public void onPageSelected(int position) {
                //TODO: add animation
//                if (wcvp.getChildAt(position).findViewById(R.id.offer_fimg) != null)
//                wcvp.getChildAt(position).findViewById(R.id.offer_fimg).animate().translationY(-140f).setDuration(2000);
            }

            @Override
            public void onPageScrollStateChanged(int state) { }
        });


        ArrayList<String> colors = new ArrayList<>();
        ArrayList<String> imgsList2 = new ArrayList<>();
        //TODO:attach firebase
        //Layout with ImageView only
        imgsList2.add("https://i.imgur.com/1RyGFyv.png");
        imgsList2.add("https://i.imgur.com/vu6ndjT.png");
        imgsList2.add("https://i.imgur.com/VWKjQ4C.png");
        imgsList2.add("https://i.imgur.com/qoKo55p.png");
        imgsList2.add("https://i.imgur.com/W9new5M.png");
        imgsList2.add("https://i.imgur.com/jk8mHIc.png");
        imgsList2.add("https://i.imgur.com/PYu1QoH.png");
        imgsList2.add("https://i.imgur.com/nUF3sGJ.png");
        imgsList2.add("https://i.imgur.com/XuAixKl.png");

        colors.add("#f6a672");
        colors.add("#1263a1");
        colors.add("#2a2a2a");

        Log.d("TAG", "onCreate: "+colors.size());
        p = new Product("5000","5400","Realme 6 Pro","Qualcomm\n Snapdragon 720G","6gb / 8gb","64gb / 128gb","main: 64mp f/1.8\nSelfie: 16mp f/2.1\n1080p 120fps","Dual sim\n microSIM LTE\n/nanoSIM 5G","4300mA\nFast Charging 30W\nUsb-C port","6.6inch IPS LCD\n2400 × 1080 pixel\n90Hz","Realme","Water drops \nresistance",getResources().getString(R.string.details),colors,imgsList2);

        if (compareList.size() != 0)findViewById(R.id.fab).setVisibility(View.VISIBLE);

        findViewById(R.id.fab).setOnClickListener(this);

        list = new ArrayList<>();
        ArrayList<String> imgsList = new ArrayList<>();
        imgsList.add("https://i.imgur.com/1RyGFyv.png");

        ArrayList<String> imgsListX = new ArrayList<>();
        imgsListX.add("https://i.imgur.com/1RyGFyv.png");

        list.add(new Product("5000","5400","Realme 6 Pro","4",imgsList,null));
        list.add(new Product("5000","5400","Realme 6 Pro","4",imgsListX,"Best seller"));

        RecyclerView rv = findViewById(R.id.devices_rv);
        ProductsAdapter pa = new ProductsAdapter(this,list,this);
        rv.setAdapter(pa);
        rv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        attachToListener();
    }

    void attachToListener(){
        findViewById(R.id.category_btn).setOnClickListener(this);
        findViewById(R.id.go2checkout_btn).setOnClickListener(this);
        findViewById(R.id.devicesCategory_ll).setOnClickListener(this);
        findViewById(R.id.accessoriesCategory_ll).setOnClickListener(this);
        findViewById(R.id.seeAllOffers_btn).setOnClickListener(this);

        SearchView searchView = findViewById(R.id.search_bar);

//        searchView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                findViewById(R.id.category_btn).setVisibility(View.GONE);
//                findViewById(R.id.go2checkout_btn).setVisibility(View.GONE);
//            }
//        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                findViewById(R.id.category_btn).setVisibility(View.GONE);
                findViewById(R.id.go2checkout_btn).setVisibility(View.GONE);
                return false;
            }
            @Override
            public boolean onQueryTextSubmit(String query) { return false; }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                System.out.println("Testing. 1, 2, 3...");
                findViewById(R.id.category_btn).setVisibility(View.VISIBLE);
                findViewById(R.id.go2checkout_btn).setVisibility(View.VISIBLE);
                return false;
            }
        });


    }

    @Override
    public void onProductClicked(int position) {
        Intent intent = new Intent(this,ProductActivity.class);
//        intent.putExtra("ReqProduct",list.get(position));
        intent.putExtra("ReqProduct",p);
        startActivity(intent);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        //TODO: not working with some devices
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.category_btn:
                break;
            case R.id.go2checkout_btn:
                startActivity(new Intent(this,CheckoutActivity.class));
                break;
            case R.id.devicesCategory_ll:
                break;
            case R.id.accessoriesCategory_ll:
                Toast.makeText(this, "Still in progress", Toast.LENGTH_SHORT).show();
                break;
            case R.id.seeAllOffers_btn:
                startActivity(new Intent(this,OffersActivity.class));
                break;
            case R.id.fab:
                startActivity(new Intent(this,CompareActivity.class));
                break;

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (compareList.size() != 0) findViewById(R.id.fab).setVisibility(View.VISIBLE);
    }
}

class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductHolder>{

    private Context mContext;
    private ArrayList<Product> list;
    private OnProductClickListener listener;

    ProductsAdapter(Context mContext, ArrayList<Product> list,OnProductClickListener listener) {
        this.mContext = mContext;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.product,parent,false);
        return new ProductHolder(v,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        holder.price.setText(list.get(position).getPrice()+" EGP");
        if (list.get(position).getOldPrice() != null){
            holder.oldPrice.setText(list.get(position).getOldPrice()+" EGP");
            holder.oldPrice.setPaintFlags(holder.oldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }

        if (list.get(position).getSpecialTag()!= null){
            holder.specialTag.setVisibility(View.VISIBLE);
            holder.specialTag.setText(list.get(position).getSpecialTag());
        }

        holder.title.setText(list.get(position).getTitle());
        Glide.with(mContext).load(list.get(position).getImg().get(0)).into(holder.img);
        //TODO: Rating
        holder.rb.setRating(Float.valueOf(list.get(position).getRate()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ProductHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView price,title,oldPrice,specialTag;
        private ImageView img;
        private RatingBar rb;
        private OnProductClickListener listener;
        ProductHolder(@NonNull View itemView,OnProductClickListener listener) {
            super(itemView);
            price = itemView.findViewById(R.id.product_price);
            title = itemView.findViewById(R.id.product_title);
            oldPrice = itemView.findViewById(R.id.product_oldPrice);
            rb = itemView.findViewById(R.id.product_rb);
            img = itemView.findViewById(R.id.product_img);
            specialTag = itemView.findViewById(R.id.product_specialTag);
            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onProductClicked(getAdapterPosition());
        }
    }
}
