package com.example.fragment;

import android.hardware.HardwareBuffer;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuFragment newInstance(String param1, String param2) {
        MenuFragment fragment = new MenuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView rv = getView().findViewById(R.id.rv);
        MenuAdapter menuAdapter = new MenuAdapter(readData(),(MainActivity) getActivity());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(menuAdapter);
    }

    public List<Hobbies> readData() {
        List<Hobbies> hobbies = new ArrayList<>();
        Map<String, Integer> imgId = new HashMap<String, Integer>() {{
            put("肖申克的救赎", R.drawable.d1);
            put("辛德勒的名单", R.drawable.d2);
            put("盗梦空间", R.drawable.d3);
            put("阿甘正传", R.drawable.d4);
            put("Faded", R.drawable.y1);
            put("By My Side", R.drawable.y2);
            put("Solo", R.drawable.y3);
            put("War3", R.drawable.game1);
            put("IDM", R.drawable.idm);
            put("Ollydbg", R.drawable.ollydbg);
            put("Fiddler", R.drawable.fiddler);
            put("assembly language", R.drawable.language1);
            put("Lua", R.drawable.lua);
        }};
        try {
            InputStream inputStream = getResources().getAssets().open("data.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            Hobbies hobbie = null;
            for (int i = 0; (line = reader.readLine()) != null; i++) {
                if (i == 0)
                    hobbie = new Hobbies();
                switch (i) {
                    case 0:
                        hobbie.setTag(line);
                        break;
                    case 1:
                        hobbie.setName(line);
                        Integer img = imgId.get(line);
                        if (img != null)
                            hobbie.setImg(img);
                        else
                            hobbie.setImg(R.drawable.ic_launcher_background);
                        break;
                    case 2:
                        hobbie.setType(line);
                        break;
                    case 3:
                        hobbie.setRecommend(line);
                        break;
                    case 4:
                        hobbie.setSynopsis(line);
                        break;
                    default:
                        i = -1;
                }
                if (i >= 4) {
                    hobbies.add(hobbie);
                    hobbie = null;
                    i = -1;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hobbies;
    }
}