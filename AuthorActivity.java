package com.example.sqlite_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AuthorActivity extends AppCompatActivity {

    ArrayList<Author> lits_authors = new ArrayList<Author>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);

        EditText edt_id = findViewById(R.id.edt_id);
        EditText edt_name = findViewById(R.id.edt_name);
        EditText edt_address = findViewById(R.id.edt_address);
        EditText edt_email = findViewById(R.id.edt_email);
        GridView gv_Display = findViewById(R.id.gridview_list);
        DBHelper dbHelper = new DBHelper(this);

//        gv_Display.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Author au = lits_authors.get(i);
//                edt_id.setText(au.getIdAuthor()+"");
//                edt_address.setText(au.getAddress());
//                edt_name.setText(au.getName());
//                edt_email.setText(au.getEmail());
//            }
//        });

        Button btn_save = findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Author author = new Author();
                author.setIdAuthor(Integer.parseInt(edt_id.getText().toString()));
                author.setName(edt_name.getText().toString());
                author.setAddress(edt_address.getText().toString());
                author.setEmail(edt_email.getText().toString());

                if(dbHelper.insertAuthor(author) > 0) {
                    Toast.makeText(getApplicationContext(), "Bạn đã lưu thành công", Toast.LENGTH_SHORT).show();
                    edt_id.setText("");
                    edt_name.setText("");
                    edt_email.setText("");
                    edt_address.setText("");
                }
                else
                    Toast.makeText(getApplicationContext(), "Bạn lưu không thành công", Toast.LENGTH_SHORT).show();
            }
        });

        Button btn_select = findViewById(R.id.btn_select);
        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Author> list_Author;
                ArrayList<String> list_string = new ArrayList<>();

                list_Author = dbHelper.getallAuthor();
                for (Author au: list_Author){
                    list_string.add(au.getIdAuthor() + "");
                    list_string.add(au.getName());
                    list_string.add(au.getAddress());
                    list_string.add(au.getEmail());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(AuthorActivity.this, android.R.layout.simple_list_item_1
                        , list_string);
                gv_Display.setAdapter(adapter);
            }
        });

        Button btn_Update = findViewById(R.id.btn_update);
        btn_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Author author = new Author();
                author.setIdAuthor(Integer.parseInt(edt_id.getText().toString()));
                author.setName(edt_name.getText().toString());
                author.setAddress(edt_address.getText().toString());
                author.setEmail(edt_email.getText().toString());

                if(dbHelper.updateAuthor(author) > 0)
                    Toast.makeText(getApplicationContext(), "cập nhật thành công", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "cập nhật không thành công", Toast.LENGTH_SHORT).show();
            }
        });

        Button btn_xoa = findViewById(R.id.btn_delete);
        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Author author = new Author();
                author.setIdAuthor(Integer.parseInt(edt_id.getText().toString()));
                author.setName(edt_name.getText().toString());
                author.setAddress(edt_address.getText().toString());
                author.setEmail(edt_email.getText().toString());

                if(dbHelper.deleteAuthor(author) > 0)
                    Toast.makeText(getApplicationContext(), "xóa thành công", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "xóa không thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }
}