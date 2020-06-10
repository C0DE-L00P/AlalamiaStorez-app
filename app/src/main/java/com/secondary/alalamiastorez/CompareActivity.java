package com.secondary.alalamiastorez;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import androidx.annotation.Nullable;

import static com.secondary.alalamiastorez.MainActivity.compareList;

public class CompareActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compare_activity);

        GridLayout gl = findViewById(R.id.compare_grid);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(300,LinearLayout.LayoutParams.MATCH_PARENT);
        lp.gravity = Gravity.FILL_VERTICAL;

        gl.setColumnCount(compareList.size());
        gl.setRowCount(9);

        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.height = GridLayout.LayoutParams.MATCH_PARENT;
        params.width = 300;

        RelativeLayout.LayoutParams lpStart = new RelativeLayout.LayoutParams(160,160);
        lpStart.setMargins(20,20,20,20);

        int[] ic_imgs = {0,R.drawable.ic_cpu,R.drawable.ic_ram,R.drawable.ic_storage,R.drawable.ic_lens,R.drawable.ic_sim,R.drawable.ic_battery,R.drawable.ic_screen,R.drawable.ic_storage};

        for (int ic : ic_imgs){
            ImageView iv = new ImageView(this);
            if (ic != 0)Glide.with(this).load(ic).into(iv);
            iv.setLayoutParams(lpStart);
            iv.setPadding(30,30,30,30);
            gl.addView(iv);
        }

//        TextView tv = new TextView(this);
//        tv.setText("Special");
//        tv.setTextSize(16f);
//        tv.setLayoutParams(lpStart);
//        tv.setPadding(30,30,30,30);
//        tv.setGravity(Gravity.CENTER);
//        gl.addView(tv);


        for (int i = 0; i<compareList.size();i++){

            //Title element
            LinearLayout ll = new LinearLayout(this);
            ImageView img = new ImageView(this);
            TextView title = new TextView(this);
            title.setText(compareList.get(i).getTitle());
            LinearLayout.LayoutParams lpImg = new LinearLayout.LayoutParams(160,160);
            lpImg.setMargins(0,10,0,6);
            img.setLayoutParams(lpImg);
            Glide.with(this).load(compareList.get(i).getImg().get(0)).into(img);
            ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            ll.setPadding(0,10,0,10);
            ll.setOrientation(LinearLayout.VERTICAL);
            ll.addView(img);
            ll.addView(title);
            ll.setGravity(Gravity.CENTER);
            title.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            title.setGravity(Gravity.CENTER);
            title.setTextColor(Color.BLACK);
            title.setTypeface(null, Typeface.BOLD);


            gl.addView(ll,lp);
            lp.gravity = Gravity.CENTER;

            //Cpu element
            TextView cpu = new TextView(this);
            cpu.setText(compareList.get(i).getCpu());
            cpu.setGravity(Gravity.CENTER);
            cpu.setPadding(4,10,4,10);
            cpu.setLayoutParams(params);
            gl.addView(cpu,lp);

            //Ram element
            TextView ram = new TextView(this);
            ram.setText(compareList.get(i).getRam());
            ram.setPadding(4,10,4,10);
            gl.addView(ram,lp);
            ram.setLayoutParams(params);

            ram.setGravity(Gravity.CENTER|Gravity.FILL_VERTICAL);

            //Storage element
            TextView storage = new TextView(this);
            storage.setText(compareList.get(i).getStorage());
            storage.setGravity(Gravity.CENTER);
            storage.setPadding(4,10,4,10);
            gl.addView(storage,lp);

            //Camera element
            TextView camera = new TextView(this);
            camera.setText(compareList.get(i).getCamera());
            camera.setGravity(Gravity.CENTER);
            camera.setPadding(4,10,4,10);
            gl.addView(camera,lp);

            //SIM element
            TextView sim = new TextView(this);
            sim.setText(compareList.get(i).getSim());
            sim.setGravity(Gravity.CENTER);
            sim.setPadding(4,10,4,10);
            gl.addView(sim,lp);

            //Battery element
            TextView battery = new TextView(this);
            battery.setText(compareList.get(i).getBattery());
            battery.setGravity(Gravity.CENTER);
            battery.setPadding(4,10,4,10);
            gl.addView(battery,lp);

            //Screen element
            TextView screen = new TextView(this);
            screen.setText(compareList.get(i).getScreen());
            screen.setGravity(Gravity.CENTER);
            screen.setPadding(4,10,4,10);
            gl.addView(screen,lp);

            //Special element
            TextView special = new TextView(this);
            special.setText(compareList.get(i).getSpecialAdds());
            special.setGravity(Gravity.CENTER);
            special.setPadding(4,10,4,10);
            gl.addView(special,lp);
        }


    }
}
