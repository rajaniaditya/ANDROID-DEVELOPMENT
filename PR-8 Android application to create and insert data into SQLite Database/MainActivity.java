package com.example.pr8;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    private EditText editTextName;
    private Button buttonSave;
    private TextView textViewData;
    private SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextName = findViewById(R.id.editTextName);
        buttonSave = findViewById(R.id.buttonSave);
        textViewData = findViewById(R.id.textViewData);
        editTextName.setTextColor(getResources().getColor(android.R.color.black));
        buttonSave.setTextColor(getResources().getColor(android.R.color.black));
        buttonSave.setBackgroundColor(getResources().getColor(android.R.color.white));
        textViewData.setTextColor(getResources().getColor(android.R.color.black));
        sqLiteDatabase = openOrCreateDatabase("MyDatabase", MODE_PRIVATE, null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Names (Name VARCHAR)");
        showData();
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString().trim();
                if (!name.isEmpty()) {
                    insertData(name);
                    editTextName.setText("");
                    showData();
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void insertData(String name) {
        String query = "INSERT INTO Names VALUES ('" + name + "')";
        sqLiteDatabase.execSQL(query);
        Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
    }
    private void showData() {
        StringBuilder stringBuilder = new StringBuilder();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Names", null);
        if (cursor.moveToFirst()) {
            do {
                stringBuilder.append(cursor.getString(0)).append("\n");
            } while (cursor.moveToNext());
        }
        textViewData.setText("Saved Data:\n" + stringBuilder.toString());
        cursor.close();
    }
}
