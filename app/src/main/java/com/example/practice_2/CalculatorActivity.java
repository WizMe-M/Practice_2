package com.example.practice_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.RecognitionService;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity {

    TextView txtInput;
    TextView txtResult;
    boolean isDotInputted;
    Calculator calculator;
    String b, a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        calculator = new Calculator();
        txtInput = findViewById(R.id.txtInput);
        txtResult = findViewById(R.id.txtResult);
        b = "";
        a = "";
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        String input = String.valueOf(txtInput.getText());
        String output = String.valueOf(txtResult.getText());

        outState.putString("INPUT", input);
        outState.putString("OUTPUT", output);

        outState.putString("A", a);
        outState.putString("B", b);
        outState.putInt("OPERATION", calculator.operation.ordinal());
        outState.putBoolean("DOT", isDotInputted);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        a = savedInstanceState.getString("A");
        b = savedInstanceState.getString("B");
        isDotInputted = savedInstanceState.getBoolean("DOT");
        calculator = new Calculator();
        int enumAnInt = savedInstanceState.getInt("OPERATION");
        calculator.operation = operations.values()[enumAnInt];
        String input = savedInstanceState.getString("INPUT");
        String output = savedInstanceState.getString("OUTPUT");
        txtResult.setText(output);
        txtInput.setText(input);

    }

    public void inputDigit(View view) {
        Button pressedButton = (Button) view;
        String text = String.valueOf(pressedButton.getText());
        if (b.equals("0")) b = text;
        else b += text;
        txtInput.setText(b);
    }

    public void inputDot(View view) {
        if (isDotInputted) return;

        if (b.equals("")) b = "0.";
        else b += ".";
        isDotInputted = true;
        txtInput.setText(b);
    }

    public void negateNumber(View view) {
        double number = Double.parseDouble(b);
        number = -number;
        b = String.valueOf(number);
        txtInput.setText(b);
    }

    public void plus(View view) {
        calculator.operation = operations.Sum;
        a = b;
        b = "";
        txtInput.setText("");
        isDotInputted = false;
    }

    public void minus(View view) {
        calculator.operation = operations.Minus;
        a = b;
        b = "";
        txtInput.setText("");
        isDotInputted = false;
    }

    public void multiply(View view) {
        calculator.operation = operations.Multiply;
        a = b;
        b = "";
        txtInput.setText("");
        isDotInputted = false;
    }

    public void divide(View view) {
        calculator.operation = operations.Divide;
        a = b;
        b = "";
        txtInput.setText("");
        isDotInputted = false;
    }

    public void equal(View view) {
        if (b.equals("") || a.equals("")) return;

        double A = Double.parseDouble(a);
        double B = Double.parseDouble(b);

        double result = calculator.solve(A, B);
        txtResult.setText(String.valueOf(result));
        txtInput.setText("");
        b = "";
        a = "";
        isDotInputted = false;
    }

    public void reverseNumber(View view) {
        double number = Double.parseDouble(b);
        number = 1 / number;
        b = String.valueOf(number);
        txtInput.setText(b);
    }

    public void square(View view) {
        double number = Double.parseDouble(b);
        number = calculator.square(number);
        b = String.valueOf(number);
        txtInput.setText(b);
    }

    public void sqrt(View view) {
        double number = Double.parseDouble(b);
        number = calculator.sqrt(number);
        b = String.valueOf(number);
        txtInput.setText(b);
    }

    public void factorial(View view) {
        double number = Double.parseDouble(b);
        if (number % 1 != 0) return;

        number = calculator.factorial(number);
        b = String.format("%.0f", number);
        txtInput.setText(b);
    }

    public void delete(View view) {
        if (b.equals("")) return;

        if (b.charAt(b.length() - 1) == ',') isDotInputted = false;
        b = b.substring(0, b.length() - 1);
        txtInput.setText(b);
    }

    public void clear(View view) {
        b = "";
        txtInput.setText("");
        isDotInputted = false;
    }

    public void clearAll(View view) {
        b = "";
        a = "";
        txtInput.setText("");
        txtResult.setText("");
        isDotInputted = false;
    }
}