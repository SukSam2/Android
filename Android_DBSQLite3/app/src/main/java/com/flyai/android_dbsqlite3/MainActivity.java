package com.flyai.android_dbsqlite3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    SQLiteDatabase myDB;
    SimpleAdapter myADT;
    ArrayList<String> aryMBRList;
    ArrayAdapter<String> adtMembers;
    ListView lstView;
    String strRecord = null;
//    String strSQL = null;
    ContentValues insertValue;
    Cursor allRCD;
    Button btnInsert, btnUpdate, btnDelete, btnSearch;
    EditText edtCarType, edtCarPower;
    String strSQL = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtCarType = (EditText) findViewById(R.id.editCarType);
        edtCarPower = (EditText) findViewById(R.id.editCarPower);

        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(this);

        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(this);

        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(this);

        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(this);

        lstView = (ListView) findViewById(R.id.lstMember);
        lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String[] strData = null;
                strData = aryMBRList.get(position).toString().split("\t\t");

                edtCarType.setText(strData[0]);
                edtCarPower.setText(strData[1]);

            }
        });

        //Create DB(DB name: CarInformation)
        myDB = this.openOrCreateDatabase("CarInformation", MODE_PRIVATE, null);
        myDB.execSQL("Drop table if exists Carlist");

        //Create Table(Table name: Carlist)
        myDB.execSQL("Create table Carlist ( " +
                " _id integer primary key autoincrement, " +
                "CarType text not null, " + "CarPower text not null);");

        //Insert Data into CarList Table
        myDB.execSQL("Insert into Carlist " +
                "(CarType, CarPower) values ('BMW 528i', '2800');");

        //Insert Data into Carlist table
        insertValue = new ContentValues();
        insertValue.put("CarType", "Benz 320");
        insertValue.put("CarPower", "3200");
        myDB.insert("Carlist", null, insertValue);

        getDBData(null);

        if(myDB != null) myDB.close();
    }

    public void getDBData(String strWhere){

        //Get DB data from CarList table
        allRCD = myDB.query("Carlist", null, strWhere, null, null, null, null, null);

        //Create arrayList
        aryMBRList = new ArrayList<String>();
        if (allRCD != null){
            if (allRCD.moveToFirst()){
                do{
                    strRecord = allRCD.getString(1) + "\t\t" + allRCD.getString(2);
                    aryMBRList.add(strRecord);
                }while(allRCD.moveToNext());
            }
        }
        adtMembers = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, aryMBRList);

        //Create ListView
        lstView.setAdapter(adtMembers);
        lstView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

    }

    @Override
    public void onClick(View v){
        //Create DB(DB name: CarInformation)
        myDB = this.openOrCreateDatabase("CarInformation", MODE_PRIVATE, null);

        if(v == btnInsert){
            //Insert record into Carlist
            insertValue = new ContentValues();
            insertValue.put("CarType", edtCarType.getText().toString());
            insertValue.put("CarPower", edtCarPower.getText().toString());
            myDB.insert("Carlist", null, insertValue);
            getDBData(null);  //Get DB data from Carlist table
        } else if (v == btnUpdate) {
            strSQL = "Update Carlist Set ";
            strSQL += "Cartype = " + "'" + edtCarType.getText().toString() + "', ";
            strSQL += "CarPower = " + "'" + edtCarPower.getText().toString() + "' ";
            strSQL += "Where CarType = '" + edtCarType.getText().toString() + "';" ;
            myDB.execSQL(strSQL);
            getDBData(null);
        } else if (v == btnDelete) {
            strSQL = "Delete From Carlist ";
            strSQL += " Where CarType = '" + edtCarType.getText().toString() + "';";
            myDB.execSQL(strSQL);
            getDBData(null);
        } else if (v == btnSearch) {
            strSQL = "CarType = '" + edtCarType.getText().toString() + "'";
            getDBData(strSQL);
        }
        if (myDB != null) myDB.close(); //Close DB
    }

}