package sg.edu.rp.c346.id20022543.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import sg.edu.rp.c346.id20022543.demodatabase.Task;

public class MainActivity extends AppCompatActivity {
    Button btnInsert,btnGetTasks;
    TextView tvResults;
    ListView lv;
    ArrayAdapter<Task> aa;
    ArrayList<Task> al;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert  = findViewById(R.id.btnInsert);
        btnGetTasks = findViewById(R.id.btnGetTask);
        tvResults = findViewById(R.id.tvResult);
        lv = findViewById(R.id.lv);
        al = new ArrayList<>();
        aa = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1,al);
        lv.setAdapter(aa);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity.this);
                dbh.insertTask("Submit RJ","25 Apr 2021");
            }
        });

        btnGetTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity.this);
                ArrayList<String> data = dbh.getTaskContent();

                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    txt += i + ". " + data.get(i) + "\n";
                }
                tvResults.setText(txt);

                al.clear();
                al.addAll(dbh.getTask());
                al = dbh.getTask();
                aa.notifyDataSetChanged();
            }
        });
    }
}