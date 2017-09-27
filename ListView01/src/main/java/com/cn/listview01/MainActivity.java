package com.cn.listview01;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private Context mContext;
    private List<Fruit> fruitList = new ArrayList<Fruit>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        mContext=this;
        initFruits();
        FruitAdapter adapter = new FruitAdapter(mContext, R.layout.fruit_item, fruitList);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit = fruitList.get(position);
                Toast.makeText(mContext, fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initFruits() {
        for (int i = 0; i < 3; i++) {
            Fruit apple = new Fruit("苹果", R.drawable.apple_pic);
            fruitList.add(apple);
            Fruit banana = new Fruit("香蕉", R.drawable.banana_pic);
            fruitList.add(banana);
            Fruit orange = new Fruit("橘子", R.drawable.orange_pic);
            fruitList.add(orange);
            Fruit watermelon = new Fruit("西瓜", R.drawable.watermelon_pic);
            fruitList.add(watermelon);
            Fruit pear = new Fruit("梨子", R.drawable.pear_pic);
            fruitList.add(pear);
            Fruit grape = new Fruit("葡萄", R.drawable.grape_pic);
            fruitList.add(grape);
            Fruit pineapple = new Fruit("菠萝", R.drawable.pineapple_pic);
            fruitList.add(pineapple);
            Fruit strawberry = new Fruit("草莓", R.drawable.strawberry_pic);
            fruitList.add(strawberry);
            Fruit cherry = new Fruit("樱桃", R.drawable.cherry_pic);
            fruitList.add(cherry);
            Fruit mango = new Fruit("芒果", R.drawable.mango_pic);
            fruitList.add(mango);
        }
    }

}
