package com.example.pr7;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.pr7.R;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
public class MainActivity extends AppCompatActivity {
    private EditText yearEditText, monthEditText, dayEditText;
    private TextView overdueTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        yearEditText = findViewById(R.id.edit_year);
        monthEditText = findViewById(R.id.edit_month);
        dayEditText = findViewById(R.id.edit_day);
        overdueTextView = findViewById(R.id.tv_overdue);
        Button calculateButton = findViewById(R.id.btn_calculate);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateOverdue();
            }
        });
    }
    private void calculateOverdue() {
        String yearString = yearEditText.getText().toString().trim();
        String monthString = monthEditText.getText().toString().trim();
        String dayString = dayEditText.getText().toString().trim();
        if (yearString.isEmpty() || monthString.isEmpty() || dayString.isEmpty()) {
            overdueTextView.setText("Please enter all fields");
            return;
        }
        int year = Integer.parseInt(yearString);
        int month = Integer.parseInt(monthString);
        int day = Integer.parseInt(dayString);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            Calendar dueDateCalendar = Calendar.getInstance();
            dueDateCalendar.set(year, month - 1, day); // Month in Calendar starts from 0
            Date dueDate = dueDateCalendar.getTime();
            Date currentDate = new Date();
            long diff = currentDate.getTime() - dueDate.getTime();
            long overdueDays = diff / (1000 * 60 * 60 * 24); // Convert milliseconds to days
            overdueTextView.setText("Overdue Days: " + overdueDays);
        } catch (Exception e) {
            overdueTextView.setText("Invalid date");
        }
    }
}
