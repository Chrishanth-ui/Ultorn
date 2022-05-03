package com.cs.ultron;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cs.ultron.R;

public class Calculator extends AppCompatActivity {

    private EditText display;
    private String oldNum;
    private String operator;
    private String newNum;
    private boolean isNewOp = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        display = findViewById(R.id.display);
        display.setShowSoftInputOnFocus(false);


    }

    private void updateText(String strToAdd) {
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);

        display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
        display.setSelection(cursorPos + 1);




    }
    public void numEvent(View view){
        if (isNewOp){
            display.setText("");
        }

        isNewOp = false;

        Button buNum = findViewById(view.getId());
        String value = display.getText().toString();

        Log.i("Number : ", String.valueOf(view.getId()));

        switch (buNum.getId()){
            case R.id.btnOne :
                value += "1";
                break;
            case R.id.btnTwo :
                value += "2";
                break;
            case R.id.btnThree :
                value += "3";
                break;
            case R.id.btnFour :
                value += "4";
                break;
            case R.id.btnFive :
                value += "5";
                break;
            case R.id.btnSix :
                value += "6";
                break;
            case R.id.btnSeven :
                value += "7";
                break;
            case R.id.btnEight :
                value += "8";
                break;
            case R.id.btnNine :
                value += "9";
                break;
            case R.id.btnZero :
                value += "0";
                break;
            case R.id.btnPlusorMinus :
                value += "-";
                break;
            case R.id.btnPoint :
                value += ".";
                break;

            default:
                value = "";

        }

        display.setText(value);
    }

    public void opEvent(View view){
        Button btnOp = findViewById(view.getId());

        switch (btnOp.getId()){
            case R.id.btnAddition :
                operator = "+";
                break;
            case R.id.btnSubtraction :
                operator = "-";
                break;
            case R.id.btnMultiplication :
                operator = "*";
                break;
            case R.id.btnDivision :
                operator = "/";
                break;
            case R.id.btnPercentage :
                operator = "%";
                break;

            default:
                operator = null;


        }

        oldNum = display.getText().toString();
        isNewOp = true;
    }

    public void btnEqual (View view){
        if(operator != null){
            String newNum = display.getText().toString();
            Double finalNum = 0.00;
            switch (operator){
                case "+" :
                    finalNum = Double.parseDouble(oldNum) + Double.parseDouble(newNum);
                    break;
                case "-" :
                    finalNum = Double.parseDouble(oldNum) - Double.parseDouble(newNum);
                    break;
                case "*" :
                    finalNum = Double.parseDouble(oldNum) * Double.parseDouble(newNum);
                    break;
                case "/" :
                    finalNum = Double.parseDouble(oldNum) / Double.parseDouble(newNum);
                    break;
                case "%" :
                    finalNum = Double.parseDouble(oldNum) % Double.parseDouble(newNum);
                    break;

                default:
                    finalNum = 0.00;

            }

            display.setText(finalNum.toString());
            isNewOp = true;
        }
    }










    public void ClearBtn (View view){
        display.setText("");

    }

    public void BracketBtn (View view){
        int cursorPos = display.getSelectionStart();
        int openBrack = 0;
        int closeBrack = 0;
        int textLen = display.getText().length();

        for(int i = 0; i < cursorPos; i++){
            if(display.getText().toString().substring(i, i+1).equals("(")){
                openBrack += 1;
            }
            if(display.getText().toString().substring(i, i+1).equals(")")) {
                closeBrack += 1;
            }
        }

        if (openBrack == closeBrack || display.getText().toString().substring(textLen-1, textLen).equals("(")) {
            updateText("(");
            display.setSelection(cursorPos + 1);
        }
        if (closeBrack < openBrack && !display.getText().toString().substring(textLen-1, textLen).equals("(")) {
            updateText(")");
            display.setSelection(cursorPos + 1);
        }

    }





    public void backSpc(View view){
        String text = display.getText().toString();
        String[] value =  text.split("");

        if (text != null && text.length() > 0 ){
            text = text.substring(0,text.length() - 1);

            display.setText(text);
        }
//        if(value.length > 0){
//           text.replace(value[text.length() - 1],"");
//
//            Log.i("Last value" , text);
//            Log.i("Last value" , value[text.length() - 1]);
//
//            display.setText(text);
//
//        }
    }


}