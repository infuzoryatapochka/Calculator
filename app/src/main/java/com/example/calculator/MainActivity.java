package com.example.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.math.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_history, tv_result;
    private Button btn_delete, btn_equal, btn_point, btn_deg, btn_mult, btn_minus, btn_plus, btn_sqr, btn_x, btn_sign, btn_back,
            btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9;
    private double num1 = 0;
    private double num2 = 0;
    private BigDecimal result = BigDecimal.valueOf(0);
    private int code_operation = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }


    private void initViews(){
        tv_history = (TextView) findViewById(R.id.tv_history);
        tv_result = (TextView) findViewById(R.id.tv_result);
        btn_equal = (Button) findViewById(R.id.btn_equal);
        btn_delete = (Button) findViewById (R.id.btn_delete);
        btn_point = (Button) findViewById (R.id.btn_point);
        btn_deg = (Button) findViewById (R.id.btn_deg);
        btn_mult = (Button) findViewById (R.id.btn_mult);
        btn_minus = (Button) findViewById (R.id.btn_minus);
        btn_plus = (Button) findViewById (R.id.btn_plus);
        btn_sqr = (Button) findViewById (R.id.btn_sqr);
        btn_x = (Button) findViewById (R.id.btn_x);
        btn_sign = (Button) findViewById (R.id.btn_sign);
        btn_back = (Button) findViewById (R.id.btn_back);
        btn_0 = (Button) findViewById (R.id.btn_0);
        btn_1 = (Button) findViewById (R.id.btn_1);
        btn_2 = (Button) findViewById (R.id.btn_2);
        btn_3 = (Button) findViewById (R.id.btn_3);
        btn_4 = (Button) findViewById (R.id.btn_4);
        btn_5 = (Button) findViewById (R.id.btn_5);
        btn_6 = (Button) findViewById (R.id.btn_6);
        btn_7 = (Button) findViewById (R.id.btn_7);
        btn_8 = (Button) findViewById (R.id.btn_8);
        btn_9 = (Button) findViewById (R.id.btn_9);

        btn_equal.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_deg.setOnClickListener(this);
        btn_mult.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_sqr.setOnClickListener(this);
        btn_x.setOnClickListener(this);
        btn_sign.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
    }

    private void putSymbol(String newNumber) {
        tv_result.setText(tv_result.getText().toString().concat(newNumber));
    }

    private void putToHistory (String newNumber){
        tv_history.setText(tv_result.getText().toString().concat(newNumber));
    }

    private double calcNumber(){
        double num = 0;
        try {
            num = Double.parseDouble(tv_result.getText().toString());
        } catch(Exception e) {
            Toast.makeText(this, "Error Format Number", Toast.LENGTH_SHORT).show();
        }
        return num;
    }

    private void getFirstNumber(){
        num1 = calcNumber();
        tv_result.setText(null);
    }

    private void calcResult(){
        switch (code_operation){
            case 1:
                result = BigDecimal.valueOf(num1 + num2);
                break;
            case 2:
                result = BigDecimal.valueOf(num1 - num2);
                break;
            case 3:
                result = BigDecimal.valueOf(num1 * num2);
                break;
            case 4:
                if (num2 == 0) {
                    tv_result.setText("Division by zero");
                    tv_history.setText(null);
                    return;
                }
                result = BigDecimal.valueOf(num1 / num2);
                break;
        }
        tv_result.setText(String.valueOf(result.stripTrailingZeros()));
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_0:
                putSymbol("0");
                break;
            case R.id.btn_1:
                putSymbol("1");
                break;
            case R.id.btn_2:
                putSymbol("2");
                break;
            case R.id.btn_3:
                putSymbol("3");
                break;
            case R.id.btn_4:
                putSymbol("4");
                break;
            case R.id.btn_5:
                putSymbol("5");
                break;
            case R.id.btn_6:
                putSymbol("6");
                break;
            case R.id.btn_7:
                putSymbol("7");
                break;
            case R.id.btn_8:
                putSymbol("8");
                break;
            case R.id.btn_9:
                putSymbol("9");
                break;
            case R.id.btn_plus:
                code_operation = 1;
                putToHistory("+");
                getFirstNumber();
                break;
            case R.id.btn_minus:
                code_operation = 2;
                putToHistory("-");
                getFirstNumber();
                break;
            case R.id.btn_mult:
                code_operation = 3;
                putToHistory("*");
                getFirstNumber();
                break;
            case R.id.btn_deg:
                code_operation = 4;
                putToHistory("/");
                getFirstNumber();
                break;
            case R.id.btn_equal:
                num2 = calcNumber();
                tv_history.setText(tv_history.getText().toString().concat(String.valueOf(BigDecimal.valueOf(num2).stripTrailingZeros())));
                calcResult();
                break;
            case R.id.btn_back:
                tv_result.setText(tv_result.getText().toString().substring(0, tv_result.length()-1));
                break;
            case R.id.btn_delete:
                tv_result.setText(null);
                tv_history.setText(null);
                break;
            case R.id.btn_point:
                putSymbol(".");
                break;
            case R.id.btn_sign:
                if (tv_result.getText().toString().contains("-")){
                    tv_result.setText(tv_result.getText().toString().replace("-", ""));
                } else
                tv_result.setText("-"+tv_result.getText().toString());
                break;
            case R.id.btn_sqr:
                tv_history.setText("√"+tv_result.getText().toString());
                tv_result.setText(String.valueOf(Math.sqrt(Double.valueOf(tv_result.getText().toString()))));
                break;
            case R.id.btn_x:
                double x = Double.valueOf(tv_result.getText().toString());
                tv_history.setText("1/"+tv_result.getText().toString());
                tv_result.setText(String.valueOf(1/x));
                break;
        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        tv_result.setText(savedInstanceState.getString
                ("SaveTvResult"));
        tv_result.setText(savedInstanceState.getString("SaveTvResult"));
        tv_history.setText(savedInstanceState.getString
                ("SaveTvHistory"));
        tv_history.setText(savedInstanceState.getString("SaveTvHistory"));
        num1 = savedInstanceState.getDouble("SaveNum1");
        num2 = savedInstanceState.getDouble("SaveNum2");
        BigDecimal result = new BigDecimal(savedInstanceState.getDouble("SaveResult"));
        code_operation = savedInstanceState.getInt("SaveCodeOperation");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString("SaveTvResult",
                tv_result.getText().toString());
        outState.putString("SaveTvHistory",
                tv_history.getText().toString());
        outState.putString("SaveTvResult", tv_result.getText().toString());
        outState.putString("SaveTvHistory", tv_history.getText().toString());
        outState.putDouble("SaveNum1", num1);
        outState.putDouble("SaveNum2", num2);
        outState.putDouble("SaveResult", result.doubleValue());
        outState.putInt("SaveCodeOperation", code_operation);
    }
}


