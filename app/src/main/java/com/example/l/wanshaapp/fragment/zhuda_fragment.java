package com.example.l.wanshaapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.l.wanshaapp.DynamicChoiceness.SerializableMap;
import com.example.l.wanshaapp.JCVideoPlayerStandard.player;
import com.example.l.wanshaapp.R;
import com.example.l.wanshaapp.adapter.RecyAdapter;
import com.example.l.wanshaapp.bean.Small;
import com.example.l.wanshaapp.fenleiresource.jiemiresource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class zhuda_fragment extends Fragment {


    private List<Map<String, Object>> dataList;
    private List<Small> fruitList = new ArrayList<Small>();
    private List<Small> fruitList2 = new ArrayList<Small>();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.zhuda_main, null);
        
        initFruits();
        initFruits2();
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.Recyfind);
        RecyclerView recyclerView2 = (RecyclerView) view.findViewById(R.id.Recyorder);
        ImageView imageView = (ImageView) view.findViewById(R.id.zhandou);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getActivity());

        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView2.setLayoutManager(layoutManager2);

        RecyAdapter adapter = new RecyAdapter(getActivity(),fruitList);
        RecyAdapter adapter2 = new RecyAdapter(getActivity(),fruitList2);


        recyclerView.setAdapter(adapter);
        recyclerView2.setAdapter(adapter2);

        return view;


    }


    private void initFruits() {



        for (int i = 0; i < 1; i++) {
            Small apple = new Small("Apple", R.drawable.fenlei_1);
            fruitList2.add(apple);
            Small banana = new Small("Banana", R.drawable.fenlei_2);
            fruitList2.add(banana);
            Small orange = new Small("Orange", R.drawable.fenlei_3);
            fruitList2.add(orange);
            Small watermelon = new Small("Watermelon", R.drawable.fenlei_4);
            fruitList2.add(watermelon);
            Small pear = new Small("Pear", R.drawable.fenlei_5);
            fruitList2.add(pear);
            Small grape = new Small("Grape", R.drawable.fenlei_6);
            fruitList2.add(grape);
            Small pineapple = new Small("Pineapple", R.drawable.fenlei_7);
            fruitList2.add(pineapple);
            Small strawberry = new Small("Strawberry", R.drawable.fenlei_8);
            fruitList2.add(strawberry);
            Small cherry = new Small("Cherry", R.drawable.fenlei_3);
            fruitList2.add(cherry);
            Small mango = new Small("Mango", R.drawable.fenlei_6);
            fruitList2.add(mango);

        }


       }

    private void initFruits2() {



        for (int i = 0; i < 1; i++) {
            Small apple = new Small("Apple", R.drawable.fenlei_jiaose_1);
            fruitList.add(apple);
            Small banana = new Small("Banana", R.drawable.fenlei_kapai_2);
            fruitList.add(banana);
            Small orange = new Small("Orange", R.drawable.fenlei_kongbu_7);
            fruitList.add(orange);
            Small watermelon = new Small("Watermelon", R.drawable.fenlei_kapai_8);
            fruitList.add(watermelon);
            Small pear = new Small("Pear", R.drawable.fenlei_jiaose_6);
            fruitList.add(pear);
            Small grape = new Small("Grape", R.drawable.fenlei_jiaose_9);
            fruitList.add(grape);
            Small pineapple = new Small("Pineapple", R.drawable.fenlei_kapai_9);
            fruitList.add(pineapple);
            Small strawberry = new Small("Strawberry", R.drawable.fenlei_maoxian_8);
            fruitList.add(strawberry);
            Small cherry = new Small("Cherry", R.drawable.fenlei_maoxian_3);
            fruitList.add(cherry);
            Small mango = new Small("Mango", R.drawable.fenlei_kongbu_6);
            fruitList.add(mango);

        }


    }




    }








