package com.example.sam.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.mozilla.javascript.Context;


public class MainActivity extends AppCompatActivity {
    String value;
    View[] buttons;
    TextView screen;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context rhino = Context.enter();
        value = "";
        screen = (TextView) findViewById(R.id.numberField);
        updateScreen();
        buttons = new View[20];
        buttons[0] =  findViewById(R.id.button1);
        buttons[1] =  findViewById(R.id.button2);
        buttons[2] =  findViewById(R.id.button3);
        buttons[3] =  findViewById(R.id.button4);
        buttons[4] =  findViewById(R.id.button5);
        buttons[5] =  findViewById(R.id.button6);
        buttons[6] =  findViewById(R.id.button7);
        buttons[7] =  findViewById(R.id.button8);
        buttons[8] =  findViewById(R.id.button9);
        buttons[9] =  findViewById(R.id.button10);
        buttons[10] =  findViewById(R.id.button11);
        buttons[11] =  findViewById(R.id.button12);
        buttons[12] =  findViewById(R.id.button13);
        buttons[13] =  findViewById(R.id.button14);
        buttons[14] =  findViewById(R.id.button15);
        buttons[15] =  findViewById(R.id.button16);
        buttons[16] =  findViewById(R.id.button17);
        buttons[17] =  findViewById(R.id.button18);
        buttons[18] =  findViewById(R.id.button19);
        buttons[19] =  findViewById(R.id.button20);
        for(int i = 0; i < buttons.length; i++) {
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click(v);
                }
            });
        }

    }

    private void click(View v) {
        Button btn = (Button) v;
        String text = btn.getText().toString();

        if(text.equals("=")) {
            parseEquation();
        } else if(text.equals("DEL")){
            if (value.length() > 1) {
                value = value.substring(0, value.length() - 1);
            } else {
                value = "0";
            }
        } else if(text.equals("AC")){
            value = "0";
        } else {
            if(value.equals("0")) {
                value = "";
            }
            value +=btn.getText();
        }
        updateScreen();
    }
    private void updateScreen() {
        screen.setText(value);
    }
    private void parseEquation(){
        String expression = "eval(\"" + value + "\")";
        rhino.setOptimizationLevel(-1);
        try {
            value = rhino.evaluateString(rhino.initStandardObjects(), expression, "JavaScript", 1, null).toString()+"";
        } catch (Exception e) {
            value = "NaN";
        } finally {
            Context.exit();
        }
        updateScreen();
    }






}
