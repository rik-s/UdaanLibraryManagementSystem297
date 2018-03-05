package com.example.rik.librarymanagementsystem;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView txt1,txt2,txt3;
    EditText etxt1,etxt2,etxt3,etxt4;
    Button b1,b2,b3,b4;


    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        b1=(Button)findViewById(R.id.button1);
        b2=(Button)findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.button3);
        b4=(Button)findViewById(R.id.button4);

        etxt1=(EditText)findViewById(R.id.editText1);
        etxt2=(EditText)findViewById(R.id.editText2);
        etxt3=(EditText)findViewById(R.id.editText3);
        etxt4=(EditText)findViewById(R.id.editText4);



        db=openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS library(bookname VARCHAR,name VARCHAR,duration VARCHAR);");
//        Toast.makeText(this, "Working", Toast.LENGTH_SHORT).show();

        db.execSQL("INSERT INTO library VALUES ('Anarchist Cook BOOK','archit','5');");
        db.execSQL("INSERT INTO library VALUES ('Wings of fire','shouham','4');");
        db.execSQL("INSERT INTO library VALUES ('Goblet of fire','','');");
        db.execSQL("INSERT INTO library VALUES ('Who moved my cheese','rohan','3');");
        db.execSQL("INSERT INTO library VALUES ('How to say No ?','souptik','1');");
        db.execSQL("INSERT INTO library VALUES ('Anarchist Cook BOOK','sidhaant','7');");

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(etxt1.getText().toString().trim().length()==0)
                {
                    showMessage("Error","Please enter the name of the book");
                    return;
                }
                Cursor c=db.rawQuery("SELECT * FROM library WHERE bookname='"+etxt1.getText()+"' and name=NULL", null);
                if(c.getCount()==0)
                {

                    showMessage("Message", "No books found");
                }

                StringBuffer buffer=new StringBuffer();
                while(c.moveToNext())
                {
                    buffer.append("Book name: "+c.getString(0)+"\n");
                    buffer.append("Subscriber: "+c.getString(1)+"\n");
                    buffer.append("Days left: "+c.getString(2)+"\n\n");
                }
                showMessage("Library Book Details", buffer.toString());

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(etxt2.getText().toString().trim().length()==0 || etxt4.getText().toString().trim().length()==0)
                {
                    showMessage("Error","Please fill in the name of the book and subscriber's name");
                    return;
                }

            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(etxt3.getText().toString().trim().length()==0)
                {
                    showMessage("Error","Please enter the book and student name");
                    return;
                }

            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor c=db.rawQuery("SELECT * FROM library", null);
                if(c.getCount()==0)
                {
                    showMessage("Error", "No records found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(c.moveToNext())
                {
                    buffer.append("Book name: "+c.getString(0)+"\n");
                    buffer.append("Subscriber: "+c.getString(1)+"\n");
                    buffer.append("Days left: "+c.getString(2)+"\n\n");
                }
                showMessage("Library Book Details", buffer.toString());

            }
        });



    }

    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}
