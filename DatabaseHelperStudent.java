package com.mitsol.project.teachercr;

/**
 * Created by Master on 21/12/2018 with project TeacherCR.
 * Contact # +923315665044
 */

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelperStudent extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME= "student.db";
    private static final String TABLE_NAME= "student";
    private static final String COLUMN_ID= "ID";
    private static final String COLUMN_NAME= "NAME";
    private static final String COLUMN_EMAIL= "EMAIL";
    private static final String COLUMN_REG= "REG";
    private static final String COLUMN_BATCH= "BATCH";
    private static final String COLUMN_DEPARTMENT= "DEPT";
    private static final String COLUMN_MOBILE= "MOB";
    //Table contacts
    //First TIme All Contact Saved in this Table App Table
    private static final String TABLE_CONTACTS= "contacts";
    private static final String COLUMN_ID_CONTACTS= "ID_CONTACTS";
    private static final String COLUMN_NAME_CONTACTS= "CONTACTS";
    private static final String COLUMN_MOBILE_CONTACTS= "MCONTACTS";
    //tABLE gROUP
    private static final String TABLE_GROUP_CONTACTS= "group_table";
    public static final String COLUMN_ID_GROUP_CONTACTS= "ID_CONTACTS_GROUP";
    private static final String COLUMN_NAME_GROUP= "NAME_CONTACTS_GROUP";
    private static final String COLUMN_NAME_GROUP_CONTACTS= "NAME_OF_CONTACT";
    private static final String COLUMN_MOBILE_GROUP_CONTACTS= "MOBILE_OF_CONTACT";


    //Constructor
    DatabaseHelperStudent(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table "+ TABLE_NAME +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,EMAIL TEXT,REG TEXT,BATCH TEXT,DEPT TEXT,MOB TEXT,l1 TEXT,l2 TEXT,l3 TEXT,l4 TEXT,l5 TEXT)");
        //Create Table Contacts
        db.execSQL("create table "+ TABLE_CONTACTS +"(ID_CONTACTS INTEGER PRIMARY KEY AUTOINCREMENT,CONTACTS TEXT,MCONTACTS TEXT)");
        //Create Table Group
        db.execSQL("create table "+ TABLE_GROUP_CONTACTS +"(ID_CONTACTS_GROUP INTEGER PRIMARY KEY AUTOINCREMENT,NAME_CONTACTS_GROUP TEXT,NAME_OF_CONTACT TEXT,MOBILE_OF_CONTACT TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROUP_CONTACTS);
        onCreate(db);
    }
    boolean insertData(String name, String mob)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_NAME,name);
        contentValues.put(COLUMN_MOBILE,mob);
        long result=db.insert(TABLE_NAME,null,contentValues);
        return result != -1;
    }
    Cursor getAlldata()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.rawQuery("select * from "+TABLE_NAME,null);
    }
    Cursor getAlldataContacts()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.rawQuery("select * from "+TABLE_CONTACTS,null);
    }
    int search(String mob)
    {
        int a=-1;
        SQLiteDatabase db=this.getReadableDatabase();
        String[] cloms={COLUMN_NAME, COLUMN_ID, COLUMN_REG, COLUMN_BATCH, COLUMN_DEPARTMENT, COLUMN_EMAIL, COLUMN_MOBILE};
        @SuppressLint("Recycle") Cursor res=db.query(TABLE_NAME,cloms,COLUMN_MOBILE + " = ? ",new String[] {mob},null,null,null);
        while (res.moveToNext())
        {
            a=0;
        }
        return a;
        //If Number Found it return 0 else return -1;
    }
    int searchrepeat(String mob)//Can't add those number from contact list that are alresdy in database
    {
        int a=-1;
        SQLiteDatabase db=this.getReadableDatabase();
        String[] cloms={COLUMN_NAME_CONTACTS, COLUMN_ID_CONTACTS, COLUMN_MOBILE_CONTACTS};
        @SuppressLint("Recycle") Cursor res=db.query(TABLE_CONTACTS,cloms,COLUMN_MOBILE_CONTACTS + " = ? ",new String[] {mob},null,null,null);
        while (res.moveToNext())
        {
            a=0;
        }
        return a;
        //If Number Found it return 0 else return -1;
    }


    void deletest(String mob)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_NAME, " MOB = ? ", new String[]{mob});
        //return n;
    }

    boolean update_name_number(String name, String number, String old_mobile)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_NAME,name);
        contentValues.put(COLUMN_MOBILE,number);

        db.update(TABLE_NAME,contentValues, "MOB = ? ",new String[]{old_mobile});
        return true;
    }
    long insert_contacts(String name, String mobile)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_NAME_CONTACTS,name);
        contentValues.put(COLUMN_MOBILE_CONTACTS,mobile);
        return db.insert(TABLE_CONTACTS,null,contentValues);
    }
//    List<DataProfile> getProfile()
//    {
//        List<DataProfile> contactList = new ArrayList<DataProfile>();
//        // Select All Query
//        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS+" order by "+COLUMN_NAME_CONTACTS;
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        // looping through all rows and adding to list
//        if (cursor.moveToFirst()) {
//            do {
//                DataProfile myContact = new DataProfile();
//                myContact.setIdd(Integer.parseInt(cursor.getString(0)));
//                myContact.setName(cursor.getString(1));
//                myContact.setMobile(cursor.getString(2));
//                // Adding contact to list
//                contactList.add(myContact);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        // return contact list
//        return contactList;
//    }
//    List<DataProfile> getProfileApp()
//    {
//        List<DataProfile> contactList = new ArrayList<DataProfile>();
//        // Select All Query
//        String selectQuery = "SELECT  * FROM " + TABLE_NAME+" order by "+COLUMN_NAME;
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//        // looping through all rows and adding to list
//        if (cursor.moveToFirst()) {
//            do {
//                DataProfile myContact = new DataProfile();
//                myContact.setIdd(Integer.parseInt(cursor.getString(0)));
//                myContact.setName(cursor.getString(1));
//                myContact.setMobile(cursor.getString(6));
//                // Adding contact to list
//                contactList.add(myContact);
//            } while (cursor.moveToNext());
//        }
//
//        cursor.close();
//        // return contact list
//        return contactList;
//    }
//    void deleteAllDataFromApp()
//    {
//        SQLiteDatabase db=this.getWritableDatabase();
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
//        onCreate(db);
//    }
//
//    List<DataProfile> getProfileByName(String name)
//    {
//        List<DataProfile> contactList = new ArrayList<DataProfile>();
//        // Select All Query
//        String selectQuery = "SELECT  * FROM " + TABLE_NAME+" where "+COLUMN_NAME+" like '%"+name+"%'";
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//        // looping through all rows and adding to list
//        if (cursor.moveToFirst()) {
//            do {
//                DataProfile myContact = new DataProfile();
//                myContact.setIdd(Integer.parseInt(cursor.getString(0)));
//                myContact.setName(cursor.getString(1));
//                myContact.setMobile(cursor.getString(6));
//                // Adding contact to list
//                contactList.add(myContact);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        // return contact list
//        return contactList;
//    }
//
//    List<DataProfile> getContactByName(String name)
//    {
//        List<DataProfile> contactList = new ArrayList<DataProfile>();
//        // Select All Query
//        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS+" where "+COLUMN_NAME_CONTACTS+" like '%"+name+"%'";
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//        // looping through all rows and adding to list
//        if (cursor.moveToFirst()) {
//            do {
//                DataProfile myContact = new DataProfile();
//                myContact.setIdd(Integer.parseInt(cursor.getString(0)));
//                myContact.setName(cursor.getString(1));
//                myContact.setMobile(cursor.getString(2));
//                // Adding contact to list
//                contactList.add(myContact);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        // return contact list
//        return contactList;
//    }
    //Group Table Function
    public boolean insertDataToGroup(String name,String mob,String groupName)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_MOBILE_GROUP_CONTACTS,mob);
        contentValues.put(COLUMN_NAME_GROUP_CONTACTS,name);
        contentValues.put(COLUMN_NAME_GROUP,groupName);
        long result=db.insert(TABLE_GROUP_CONTACTS,null,contentValues);
        return result != -1;
    }
    public Cursor getAlldataGroup()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.rawQuery("select * from "+TABLE_GROUP_CONTACTS,null);
    }
    public Cursor getAllGroupName()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.rawQuery("select Distinct("+COLUMN_NAME_GROUP+")"  +" from "+TABLE_GROUP_CONTACTS,null);
    }
//    List<DataProfile> getContactByGroup(String name,String groupName)
//    {
//        List<DataProfile> contactList = new ArrayList<DataProfile>();
//        // Select All Query
//        String selectQuery = "SELECT  * FROM " + TABLE_GROUP_CONTACTS+" where "+COLUMN_NAME_GROUP+"='"+groupName+"' AND "+COLUMN_NAME_GROUP_CONTACTS+" like '%"+name+"%'";
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//        if (cursor.moveToFirst()) {
//            do {
//                DataProfile myContact = new DataProfile();
//                myContact.setIdd(Integer.parseInt(cursor.getString(0)));
//                myContact.setName(cursor.getString(2));
//                myContact.setMobile(cursor.getString(3));
//                contactList.add(myContact);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        return contactList;
//    }
//    List<DataProfile> getContactByFromGroup(String groupName)
//    {
//        List<DataProfile> contactList = new ArrayList<DataProfile>();
//        // Select All Query
//        String selectQuery = "SELECT  * FROM " + TABLE_GROUP_CONTACTS+" where "+COLUMN_NAME_GROUP+" ='"+groupName+"'";
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//        // looping through all rows and adding to list
//        if (cursor.moveToFirst()) {
//            do {
//                DataProfile myContact = new DataProfile();
//                myContact.setIdd(Integer.parseInt(cursor.getString(0)));
//                myContact.setName(cursor.getString(2));
//                myContact.setMobile(cursor.getString(3));
//                // Adding contact to list
//                contactList.add(myContact);
//            } while (cursor.moveToNext());
//        }
//        //Toast.makeText(this,cursor.getCount(),Toast.LENGTH_LONG).show();
//        cursor.close();
//        // return contact list
//        return contactList;
//    }
    Cursor getNumberByGroup(String mob,String gName)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String[] cloms={COLUMN_NAME_GROUP,COLUMN_NAME_GROUP_CONTACTS,COLUMN_MOBILE_GROUP_CONTACTS};
        return db.query(TABLE_GROUP_CONTACTS,cloms, COLUMN_MOBILE_GROUP_CONTACTS+" = '"+mob+"'" +" AND "+ COLUMN_NAME_GROUP+" = '"+gName+"'",null,null,null,null);
    }
    Cursor getContactByGroupForMessage(String gName)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String[] cloms={COLUMN_NAME_GROUP,COLUMN_NAME_GROUP_CONTACTS,COLUMN_MOBILE_GROUP_CONTACTS};
        return db.query(TABLE_GROUP_CONTACTS,cloms, COLUMN_NAME_GROUP+" = '"+gName+"'",null,null,null,null);
    }
    void deletestfromGroup(String mob,String groupName)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_GROUP_CONTACTS, COLUMN_MOBILE_GROUP_CONTACTS+" = ? and "+COLUMN_NAME_GROUP+"= ?" , new String[] {mob , groupName});
    }
    void update_name_number_group(String name, String number, String old_mobile,String groupName)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_NAME_GROUP_CONTACTS,name);
        contentValues.put(COLUMN_MOBILE_GROUP_CONTACTS,number);
        contentValues.put(COLUMN_NAME_GROUP,groupName);
        db.update(TABLE_GROUP_CONTACTS,contentValues, COLUMN_MOBILE_GROUP_CONTACTS+" = ? and "+COLUMN_NAME_GROUP+"= ?" , new String[] {old_mobile , groupName});

    }
}
