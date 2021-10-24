package com.example.calculatedemol;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView tvScore;
    private TextView tvOp1;
    private TextView tvOp2;
    private TextView tvOperator;
    private TextView tvResult;
    private Button btn0;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btnClear;
    private Button btnOk;
    private int difficult = 20;
    private int score = 0;
    String sp_name="record";
    String high_key="highScore";
    StringBuffer sb = new StringBuffer();

    public CalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalFragment newInstance(String param1, String param2) {
        CalFragment fragment = new CalFragment();
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
        return inflater.inflate(R.layout.fragment_cal, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvScore = getView().findViewById(R.id.tvScore3);
        tvOp1 = getView().findViewById(R.id.tvOp1);
        tvOp2 = getView().findViewById(R.id.tvOp2);
        tvOperator = getView().findViewById(R.id.tvOperator);
        tvResult = getView().findViewById(R.id.result);
        btn0 = getView().findViewById(R.id.btn0);
        btn1 = getView().findViewById(R.id.btn1);
        btn2 = getView().findViewById(R.id.btn2);
        btn3 = getView().findViewById(R.id.btn3);
        btn4 = getView().findViewById(R.id.btn4);
        btn5 = getView().findViewById(R.id.btn5);
        btn6 = getView().findViewById(R.id.btn6);
        btn7 = getView().findViewById(R.id.btn7);
        btn8 = getView().findViewById(R.id.btn8);
        btn9 = getView().findViewById(R.id.btn9);
        btnClear = getView().findViewById(R.id.btnClear);
        btnOk = getView().findViewById(R.id.btnOk);

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnOk.setOnClickListener(this);
        btnClear.setOnClickListener(this);

        createExpress();
    }

    private void createExpress() {
        Random random = new Random();
        int op1 = random.nextInt(difficult);
        int op2 = random.nextInt(difficult);
        String op = "+";
        if (op1 % 2 == 0) {
            op = "-";
        }
        if (op == "-") {
            if (op1 < op2) {
                int temp = op1;
                op1 = op2;
                op2 = temp;
            }
        }
        tvOp1.setText(String.valueOf(op1));
        tvOp2.setText(String.valueOf(op2));
        tvOperator.setText(op);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn0:
                sb.append(0);
                break;
            case R.id.btn1:
                sb.append(1);

                break;
            case R.id.btn2:
                sb.append(2);

                break;
            case R.id.btn3:
                sb.append(3);

                break;
            case R.id.btn4:
                sb.append(4);

                break;
            case R.id.btn5:
                sb.append(5);

                break;
            case R.id.btn6:
                sb.append(6);

                break;
            case R.id.btn7:
                sb.append(7);

                break;
            case R.id.btn8:
                sb.append(8);

                break;
            case R.id.btn9:
                sb.append(9);

                break;
            case R.id.btnOk:
                cal(v);
                return;
            case R.id.btnClear:
                sb.setLength(0);
                tvResult.setText("请开始答题:");
                return;
        }
        tvResult.setText(sb.toString());
    }

    private void cal(View v) {
        int op1 = Integer.parseInt(tvOp1.getText().toString());
        int op2 = Integer.parseInt(tvOp2.getText().toString());
        int result = 0;
        String op  = tvOperator.getText().toString();
        if (op.equals("+")){
            result=op1+op2;
        }else {
            result=op1-op2;
        }
        int result2 = 0;
        try {
            result2= Integer.parseInt(tvResult.getText().toString());
        }catch (NumberFormatException e){
            new AlertDialog.Builder(getActivity())
                    .setTitle("错误")
                    .setMessage("请输入数值")
                    .setPositiveButton("确定",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create()
                    .show();
            return;
        }
        sb.setLength(0);
        if (result==result2) {
            Toast.makeText(getActivity(), "正确", Toast.LENGTH_SHORT).show();
            reset();
        }else {
            Toast.makeText(getActivity(), "错误", Toast.LENGTH_SHORT).show();
            NavController controller = Navigation.findNavController(v);
            if (score<getHightScore()) {
                Bundle bundle = new Bundle();
                bundle.putInt("score",score);
                controller.navigate(R.id.action_calFragment_to_failFragment,bundle);
            }else {
                setHightScore(score);
                Bundle bundle = new Bundle();
                bundle.putInt("score",score);
                controller.navigate(R.id.action_calFragment_to_winFragment,bundle);
            }
        }
    }

    private void reset() {
        score++;
        tvScore.setText("得分:"+score);
        createExpress();
        tvResult.setText("请开始答题:");
    }

    private void setHightScore(int score){
        SharedPreferences preferences = getActivity().getSharedPreferences(sp_name, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putInt(high_key,score);
        edit.commit();
    }

    private int getHightScore(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(sp_name, Context.MODE_PRIVATE);
        int score = sharedPreferences.getInt(high_key, 0);
        return score;
    }
}