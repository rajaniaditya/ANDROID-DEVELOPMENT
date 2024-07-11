package com.example.pr6;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    private EditText rupeesInput;
    private Button convertButton;
    private TextView resultText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rupeesInput = findViewById(R.id.rupees_input);
        convertButton = findViewById(R.id.convert_button);
        resultText = findViewById(R.id.result_text);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertRupeesToDollars();
            }
        });
    }
    private void convertRupeesToDollars() {
        String rupeesString = rupeesInput.getText().toString();
        if (!rupeesString.isEmpty()) {
            double rupees = Double.parseDouble(rupeesString);
            double dollars = rupees / 74.5; // assuming 1 USD = 74.5 INR
            resultText.setText(String.format("%.2f USD", dollars));
        } else {
            resultText.setText("Please enter Rupees");
        }
    }
}
