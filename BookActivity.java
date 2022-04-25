package com.example.sqlite_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        EditText edt_id = findViewById(R.id.edt_id_book);
        EditText edt_title = findViewById(R.id.edt_title);
        EditText edt_idAuthor = findViewById(R.id.edt_id_author);
        GridView gv_Display = findViewById(R.id.gridview_book);
        DBHelper dbHelper = new DBHelper(this);

        Button btn_save = findViewById(R.id.btn_save_book);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = new Book();
                book.setId(Integer.parseInt(edt_id.getText().toString()));
                book.setTitle(edt_title.getText().toString());
                book.setId_author(Integer.parseInt(edt_idAuthor.getText().toString()));

                if(dbHelper.insertBook(book) > 0)
                    Toast.makeText(getApplicationContext(), "Bạn đã lưu thành công", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "Bạn lưu không thành công", Toast.LENGTH_SHORT).show();
            }
        });

        Button btn_select = findViewById(R.id.btn_select_book);
        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Book> list_Book;
                ArrayList<String> list_string = new ArrayList<>();

                list_Book = dbHelper.getallBook();
                for (Book bo: list_Book){
                    list_string.add(bo.getId() + "");
                    list_string.add(bo.getTitle());
                    list_string.add(bo.getId_author() + "");
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(BookActivity.this, android.R.layout.simple_list_item_1
                        , list_string);
                gv_Display.setAdapter(adapter);
            }
        });

        Button btn_update = findViewById(R.id.btn_update_book);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}