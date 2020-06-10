package com.secondary.alalamiastorez;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.secondary.alalamiastorez.MainActivity.checkoutList;

public class CheckoutActivity extends Activity implements View.OnClickListener{

    private CheckoutAdapter ca;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout_activity);

        findViewById(R.id.orderHistory_btn).setOnClickListener(this);
        findViewById(R.id.clearCompareList_btn).setOnClickListener(this);

        final Button orderBtn = findViewById(R.id.order_btn);

        EmptyRecyclerView rv = findViewById(R.id.rv);
        ca = new CheckoutAdapter(checkoutList,this);
        rv.setAdapter(ca);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setEmptyView((ImageView) findViewById(R.id.emptyCheckoutPlaceHolderImg),null);

        if (checkoutList.size() != 0) {findViewById(R.id.clearCompareList_btn).setEnabled(true);
        orderBtn.setVisibility(View.VISIBLE);
        }

        final NestedScrollView nsv = findViewById(R.id.checkout_nested_scroll_view);
        final ExpandableLayout checkout_el = findViewById(R.id.checkout_expandable_layout);
        final EditText customerName = findViewById(R.id.edit_text_customer_name);
        final EditText customerNumber = findViewById(R.id.edit_text_customer_number);
        final EditText customerAddress = findViewById(R.id.edit_text_customer_shipping_address);
        final EditText customerAddDetails = findViewById(R.id.edit_text_customer_additional_details);
        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: worksMain");
                if (orderBtn.getText().equals("order")) {
                    Log.d(TAG, "onClick: works1");
                    if (!checkout_el.isExpanded()) {
                        Log.d(TAG, "onClick: worksSub1");
                        checkout_el.expand();
                        orderBtn.setText("confirm");
                        //TODO: smoothScroll not working
//                    nsv.smoothScrollTo(0, 4000);
                        nsv.fullScroll(View.FOCUS_DOWN);
                        customerName.requestFocus();
                    }
                } else {
                    Log.d(TAG, "onClick: works2 "+ orderBtn.getText());

                    if (customerName.getText().toString().isEmpty()){
                        customerName.setError("Can't be blank");
                        customerName.requestFocus();
                        return;
                    }
                    if (customerNumber.getText().toString().isEmpty()){
                        customerNumber.setError("Can't be blank");
                        customerNumber.requestFocus();
                        return;
                    }
                    if (customerAddress.getText().toString().isEmpty()){
                        customerAddress.setError("Can't be blank");
                        customerAddress.requestFocus();
                        return;
                    }

                    checkout_el.collapse();
                    orderBtn.setText("order");
                    customerName.setText("");
                    customerNumber.setText("");
                    customerAddress.setText("");
                    customerAddDetails.setText("");
                    Toast.makeText(CheckoutActivity.this, "order confirmed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        (findViewById(R.id.cancel_order_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkout_el.isExpanded()) {
                    checkout_el.collapse();
                    orderBtn.setText("order");
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.orderHistory_btn:
                break;
            case R.id.clearCompareList_btn:
                checkoutList.clear();
                ca.notifyDataSetChanged();
                findViewById(R.id.order_btn).setVisibility(View.GONE);
                break;
            case R.id.order_btn:
                //TODO: move code in here later
                break;
        }
    }

    private class CheckoutAdapter extends RecyclerView.Adapter<CheckoutHolder> {

        private ArrayList<Product> list;
        private Context mContext;

        public CheckoutAdapter(ArrayList<Product> list, Context mContext) {
            this.list = list;
            this.mContext = mContext;
        }


        @NonNull
        @Override
        public CheckoutHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new CheckoutHolder(LayoutInflater.from(mContext).inflate(R.layout.checkout_element,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull CheckoutHolder holder, int position) {
            holder.title.setText(list.get(position).getTitle());
            holder.price.setText(list.get(position).getPrice());
            //price //specs //quantity

            Glide.with(mContext).load(list.get(position).getImg().get(0)).into(holder.img);

        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    private class CheckoutHolder extends RecyclerView.ViewHolder{
        private TextView title,price,quantity,specs;
        private ImageView img;
        public CheckoutHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.checkout_title);
            specs = itemView.findViewById(R.id.checkout_details);
            price = itemView.findViewById(R.id.checkout_price_tag);
            img = itemView.findViewById(R.id.checkout_img);
        }
    }
}
