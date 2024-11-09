package com.lara.inchestometersconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText inputtedInches;
    Button calculate;
    TextView displayResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViews();
        // conditional logic - if the 'inches' EditText value is empty, and displays the Toast alert to the user asking them to input a value if it is empty
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inchesText = inputtedInches.getText().toString();
                if (inchesText.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please input a value.", Toast.LENGTH_LONG).show();
                } else {
                    displayResult(convertInchesToMeter(inchesText));
                }
            }
        });

    }

    // findViews() - stores the Views as Fields (Class variables)
    void findViews() {
        inputtedInches = findViewById(R.id.edit_text_inches);
        calculate = findViewById(R.id.button_calculate);
        displayResult = findViewById(R.id.text_view_display_result);
    }

    // performs the math and returns the decimal height value
    private double convertInchesToMeter(String inchesText) {
        int inchesTextToInt = Integer.parseInt(inchesText);
        return inchesTextToInt * 0.00254;
    }
    // displayResult - formats the value as a string to 2 decimal places and displays it
    private void displayResult(double inchesInMeters) {
        DecimalFormat formatInches = new DecimalFormat("0.00");
        String result = formatInches.format(inchesInMeters);

        displayResult.setText(result + "meters");
    }
}