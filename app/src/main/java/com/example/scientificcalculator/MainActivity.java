package com.example.scientificcalculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import org.mariuszgromada.math.mxparser.Expression;

public class MainActivity extends AppCompatActivity {

    private TextView previousCalculation;
    private EditText display;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        previousCalculation = findViewById(R.id.previousCalculationView);
        display = findViewById(R.id.displayEditText);

        display.setShowSoftInputOnFocus(false);
    }

    private void updateText(String strToAdd) {
        String oldstr = display.getText().toString();
        int cursorpos = display.getSelectionStart();
        String leftstr = oldstr.substring(0, cursorpos);
        String rightstr = oldstr.substring(cursorpos);
        display.setText(String.format("%s%s%s", leftstr, strToAdd, rightstr));
        display.setSelection(cursorpos + strToAdd.length());
    }

    public void zeroBTNPush(View view) {
        updateText(getResources().getString(R.string.zeroText));
    }

    public void oneBTNPush(View view) {
        updateText(getResources().getString(R.string.oneText));
    }

    public void twoBTNPush(View view) {
        updateText(getResources().getString(R.string.twoText));
    }

    public void threeBTNPush(View view) {
        updateText(getResources().getString(R.string.threeText));
    }

    public void fourBTNPush(View view) {
        updateText(getResources().getString(R.string.fourText));
    }

    public void fiveBTNPush(View view) {
        updateText(getResources().getString(R.string.fiveText));
    }

    public void sixBTNPush(View view) {
        updateText(getResources().getString(R.string.sixText));
    }

    public void sevenBTNPush(View view) {
        updateText(getResources().getString(R.string.sevenText));
    }

    public void eightBTNPush(View view) {
        updateText(getResources().getString(R.string.eightText));
    }

    public void nineBTNPush(View view) {
        updateText(getResources().getString(R.string.nineText));
    }

    public void parOpenBTNPush(View view) {
        updateText(getResources().getString(R.string.parenthesesOpenText));
    }

    public void parCloseBTNPush(View view) {
        updateText(getResources().getString(R.string.parenthesesCloseText));
    }

    public void divideBTNPush(View view) {
        updateText(getResources().getString(R.string.divText));
    }

    public void multiplyBTNPush(View view) {
        updateText(getResources().getString(R.string.mulText));
    }

    public void subtractBTNPush(View view) {
        updateText(getResources().getString(R.string.subText));
    }

    public void addBTNPush(View view) {
        updateText(getResources().getString(R.string.addText));
    }

    public void decimalBTNPush(View view) {
        updateText(getResources().getString(R.string.dotText));
    }

    public void equalBTNPush(View view) {
        String userExp = display.getText().toString();

        previousCalculation.setText(userExp);

        // Check for inverse trigonometric functions
        if (userExp.contains("sin⁻¹(")) {
            int startIndex = userExp.indexOf("sin⁻¹(") + 6; // Length of "sin⁻¹("
            int endIndex = userExp.indexOf(")", startIndex);
            if (endIndex != -1) {
                String valueStr = userExp.substring(startIndex, endIndex);
                try {
                    double value = Double.parseDouble(valueStr);
                    if (value < -1 || value > 1) {
                        display.setText("Invalid Input for arcsin");
                    } else {
                        double result = Math.asin(value); // Calculate arcsin
                        display.setText(String.valueOf(result));
                    }
                } catch (NumberFormatException e) {
                    display.setText("Invalid Input");
                }
            }
        } else if (userExp.contains("cos⁻¹(")) {
            int startIndex = userExp.indexOf("cos⁻¹(") + 6; // Length of "cos⁻¹("
            int endIndex = userExp.indexOf(")", startIndex);
            if (endIndex != -1) {
                String valueStr = userExp.substring(startIndex, endIndex);
                try {
                    double value = Double.parseDouble(valueStr);
                    if (value < -1 || value > 1) {
                        display.setText("Invalid Input for arccos");
                    } else {
                        double result = Math.acos(value); // Calculate arccos
                        display.setText(String.valueOf(result));
                    }
                } catch (NumberFormatException e) {
                    display.setText("Invalid Input");
                }
            }
        } else if (userExp.contains("tan⁻¹(")) {
            int startIndex = userExp.indexOf("tan⁻¹(") + 6; // Length of "tan⁻¹("
            int endIndex = userExp.indexOf(")", startIndex);
            if (endIndex != -1) {
                String valueStr = userExp.substring(startIndex, endIndex);
                try {
                    double value = Double.parseDouble(valueStr);
                    double result = Math.atan(value); // Calculate arctan
                    display.setText(String.valueOf(result));
                } catch (NumberFormatException e) {
                    display.setText("Invalid Input");
                }
            }
        } else {
            // For other calculations
            Expression exp = new Expression(userExp);
            double result = exp.calculate();

            // Check if the result is NaN and handle it
            if (Double.isNaN(result)) {
                display.setText("Invalid Calculation");
            } else {
                display.setText(String.valueOf(result));
            }
        }

        display.setSelection(display.getText().length());
    }

    public void backspaceBTNPush(View view) {
        int cursorpos = display.getSelectionStart();
        int textLen = display.getText().length();
        if (cursorpos != 0 && textLen != 0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.delete(cursorpos - 1, cursorpos);
            display.setText(selection);
            display.setSelection(cursorpos - 1);
        }
    }

    public void trigSinBTNPush(View view) {
        updateText("sin(");
    }

    public void trigCosBTNPush(View view) {
        updateText("cos(");
    }

    public void trigTanBTNPush(View view) {
        updateText("tan(");
    }

    public void trigArcSinBTNPush(View view) {
        updateText("sin⁻¹(");
    }

    public void trigArcCosBTNPush(View view) {
        updateText("cos⁻¹(");
    }

    public void trigArcTanBTNPush(View view) {
        updateText("tan⁻¹(");
    }

    public void logBTNPush(View view) {
        updateText("log(");
    }

    public void naturalLogBTNPush(View view) {
        updateText("ln(");
    }

    public void sqrtBTNPush(View view) {
        updateText("√");
    }

    public void eBTNPush(View view) {
        updateText("e(");
    }

    public void piBTNPush(View view) {
        updateText("π");
    }

    public void absBTNPush(View view) {
        updateText("abs(");
    }

    private boolean isPrime(int num) {
        if (num <= 1) return false;
        if (num <= 3) return true;
        if (num % 2 == 0 || num % 3 == 0) return false;
        for (int i = 5; i * i <= num; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) return false;
        }
        return true;
    }

    public void primeBTNPush(View view) {
        String userExp = display.getText().toString();

        try {
            int number = Integer.parseInt(userExp);
            boolean isPrime = isPrime(number);
            display.setText(isPrime ? "Prime" : "Not Prime");
        } catch (NumberFormatException e) {
            display.setText("Invalid Input");
        }
    }

    public void xSquaredBTNPush(View view) {
        updateText("^(2)");
    }

    public void xPowerYBTNPush(View view) {
        updateText("^(");
    }

    public void clearBTNPush(View view) {
        display.setText("");
        previousCalculation.setText("");
    }

    public TextView getPreviousCalculation() {
        return previousCalculation;
    }

    public void setPreviousCalculation(TextView previousCalculation) {
        this.previousCalculation = previousCalculation;
    }
}