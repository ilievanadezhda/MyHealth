package com.example.myhealth;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "MyDatabase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Users(name VARCHAR, username VARCHAR, password VARCHAR);");
        db.execSQL("CREATE TABLE DailyReports(name VARCHAR, username VARCHAR, date VARCHAR, flag_cough VARCHAR, flag_headache VARCHAR, flag_fever VARCHAR, flag_stuffynose VARCHAR, flag_sorethroat VARCHAR, flag_fatigue VARCHAR, flag_nausea VARCHAR, flag_stomachache VARCHAR, flag_happy VARCHAR, flag_neutral VARCHAR, flag_sad VARCHAR, bloodpressure VARCHAR, bodytemperature VARCHAR);");
        db.execSQL("CREATE TABLE Medications(name VARCHAR, username VARCHAR, date VARCHAR, medication_name VARCHAR, medication_type VARCHAR, medication_manufacturer VARCHAR, medication_count VARCHAR, medication_dosage VARCHAR, flag_morning VARCHAR, flag_noon VARCHAR, flag_evening VARCHAR, flag_monday VARCHAR, flag_tuesday VARCHAR, flag_wednesday VARCHAR, flag_thursday VARCHAR, flag_friday VARCHAR, flag_saturday VARCHAR, flag_sunday VARCHAR);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addNewUser(String name, String username, String password) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("username", username);
        contentValues.put("password", password);
        database.insert("Users", null, contentValues);
    }

    public boolean checkIfUsernameExists(String username) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor c = database.rawQuery("SELECT * FROM Users WHERE username LIKE '" + username + "'", null);
        if (c.moveToNext()) {
            c.close();
            return true; //Врати точно ако корисничкото име постои
        }
        c.close();
        return false; //Врати неточно ако корисничкото име не постои
    }

    public String returnPassword(String username) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor c = database.rawQuery("SELECT * FROM Users WHERE username LIKE '" + username + "'", null);
        if (c.moveToNext()) {
            return c.getString(2);
        }
        c.close();
        return "";
    }

    public String returnName(String username) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor c = database.rawQuery("SELECT * FROM Users WHERE username LIKE '" + username + "'", null);
        if (c.moveToNext()) {
            return c.getString(0);
        }
        c.close();
        return "";
    }

    public void insertIntoDailyReports(String name, String username, String date, String flag_cough, String flag_headache, String flag_fever, String flag_stuffynose, String flag_sorethroat, String flag_fatigue, String flag_nausea, String flag_stomachache, String flag_happy, String flag_neutral, String flag_sad, String bloodpressure, String bodytemperature) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("username", username);
        contentValues.put("date", date);
        contentValues.put("flag_cough", flag_cough);
        contentValues.put("flag_headache", flag_headache);
        contentValues.put("flag_fever", flag_fever);
        contentValues.put("flag_stuffynose", flag_stuffynose);
        contentValues.put("flag_sorethroat", flag_sorethroat);
        contentValues.put("flag_fatigue", flag_fatigue);
        contentValues.put("flag_nausea", flag_nausea);
        contentValues.put("flag_stomachache", flag_stomachache);
        contentValues.put("flag_happy", flag_happy);
        contentValues.put("flag_neutral", flag_neutral);
        contentValues.put("flag_sad", flag_sad);
        contentValues.put("bloodpressure", bloodpressure);
        contentValues.put("bodytemperature", bodytemperature);
        database.insert("DailyReports", null, contentValues);
    }

    public List<String> getDailyReport(String username, String date) {
        List<String> dailyreport = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor c1 = database.rawQuery("SELECT * FROM DailyReports WHERE username = '" + username + "' AND date = '" + date + "'", null);
        if (c1.moveToFirst()) {
            for (int i = 3; i < 16; i++) {
                dailyreport.add(c1.getString(i));
            }
        }
        c1.close();
        return dailyreport;
    }

    public boolean checkIfDailyReportExists(String username, String date) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor c1 = database.rawQuery("SELECT * FROM DailyReports WHERE username = '" + username + "' AND date = '" + date + "'", null);
        if (c1.moveToFirst()) {
            c1.close();
            return true; //Постои извештај за тој ден
        }
        return false; //Не постои извештај за тој ден
    }

    public List<String> getDates(String username) {
        SQLiteDatabase database = this.getReadableDatabase();
        List<String> datesList = new ArrayList<>();
        Cursor c1 = database.rawQuery("SELECT * FROM DailyReports WHERE username LIKE '" + username + "'", null);
        while (c1.moveToNext()) {
            datesList.add(c1.getString(2));
        }
        c1.close();
        return datesList;
    }

    public void insertIntoMedications(String name, String username, String date, String medication_name, String medication_type, String medication_manufacturer, String medication_count, String medication_dosage, String flag_morning, String flag_noon, String flag_evening, String flag_monday, String flag_tuesday, String flag_wednesday, String flag_thursday, String flag_friday, String flag_saturday, String flag_sunday) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("username", username);
        contentValues.put("date", date);
        contentValues.put("medication_name", medication_name);
        contentValues.put("medication_type", medication_type);
        contentValues.put("medication_manufacturer", medication_manufacturer);
        contentValues.put("medication_count", medication_count);
        contentValues.put("medication_dosage", medication_dosage);
        contentValues.put("flag_morning", flag_morning);
        contentValues.put("flag_noon", flag_noon);
        contentValues.put("flag_evening", flag_evening);
        contentValues.put("flag_monday", flag_monday);
        contentValues.put("flag_tuesday", flag_tuesday);
        contentValues.put("flag_wednesday", flag_wednesday);
        contentValues.put("flag_thursday", flag_thursday);
        contentValues.put("flag_friday", flag_friday);
        contentValues.put("flag_saturday", flag_saturday);
        contentValues.put("flag_sunday", flag_sunday);
        database.insert("Medications", null, contentValues);
    }

    public List<List<String>> getMyMedications(String username) {
        SQLiteDatabase database = this.getReadableDatabase();
        List<List<String>> mymedications = new ArrayList<List<String>>();
        Cursor c1 = database.rawQuery("SELECT * FROM Medications WHERE username LIKE '" + username + "'", null);
        while (c1.moveToNext()) {
            List<String> row = new ArrayList();
            String medication_name = c1.getString(3);
            String medication_type = c1.getString(4);
            String medication_count = c1.getString(6);
            if (Integer.parseInt(medication_count) > 0) {
                row.add(medication_name);
                row.add(medication_type);
                row.add(medication_count);
                mymedications.add(row);
            } else if (Integer.parseInt(medication_count) <= 0) {
                this.deleteMedication(username, medication_name);
            }
        }
        c1.close();
        return mymedications;
    }

    public boolean checkIfMedicationExists(String username, String medication_name) {
        //Ќе проверува дали во базата има лек со такво име, но и ќе проверува дали medication_count е 0 или помало
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor c1 = database.rawQuery("SELECT * FROM Medications WHERE username = '" + username + "' AND medication_name = '" + medication_name + "'", null);
        if (c1.moveToNext()) {
            if (Integer.parseInt(c1.getString(6)) > 0) {
                return true;
            }
            return false;
            }
        return false;
        }
    public void deleteMedication(String username, String medication_name) {
        SQLiteDatabase database = this.getReadableDatabase();
        database.execSQL("DELETE FROM Medications WHERE username = '" + username + "' AND medication_name = '" + medication_name + "'");
        database.close();
    }

    public List<List<String>> getMedications(String username, int day, int partofday) {
        SQLiteDatabase database = this.getReadableDatabase();
        List<List<String>> medications = new ArrayList<>();
        switch (day) {
            case 2:
                switch (partofday) {
                    case 1:
                        Cursor c1 = database.rawQuery("SELECT * FROM Medications WHERE username = '" + username + "' AND flag_monday = '" + 1 + "' AND flag_morning = '" + 1 + "'", null);
                        while (c1.moveToNext()) {
                            List<String> row = new ArrayList();
                            String medication_name = c1.getString(3);
                            String medication_dosage = c1.getString(7);
                            row.add(medication_name);
                            row.add(medication_dosage);
                            medications.add(row);
                        }
                        if (medications.isEmpty()) {
                            List<String> row = new ArrayList();
                            row.add("-");
                            row.add("-");
                            medications.add(row);
                        }
                        return medications;
                    case 2:
                        Cursor c2 = database.rawQuery("SELECT * FROM Medications WHERE username = '" + username + "' AND flag_monday = '" + 1 + "' AND flag_noon = '" + 1 + "'", null);
                        while (c2.moveToNext()) {
                            List<String> row = new ArrayList();
                            String medication_name = c2.getString(3);
                            String medication_dosage = c2.getString(7);
                            row.add(medication_name);
                            row.add(medication_dosage);
                            medications.add(row);
                        }
                        if (medications.isEmpty()) {
                            List<String> row = new ArrayList();
                            row.add("-");
                            row.add("-");
                            medications.add(row);
                        }
                        return medications;
                    case 3:
                        Cursor c3 = database.rawQuery("SELECT * FROM Medications WHERE username = '" + username + "' AND flag_monday = '" + 1 + "' AND flag_evening = '" + 1 + "'", null);
                        while (c3.moveToNext()) {
                            List<String> row = new ArrayList();
                            String medication_name = c3.getString(3);
                            String medication_dosage = c3.getString(7);
                            row.add(medication_name);
                            row.add(medication_dosage);
                            medications.add(row);
                        }
                        if (medications.isEmpty()) {
                            List<String> row = new ArrayList();
                            row.add("-");
                            row.add("-");
                            medications.add(row);
                        }
                        return medications;
                }
                break;
            case 3:
                switch (partofday) {
                    case 1:
                        Cursor c1 = database.rawQuery("SELECT * FROM Medications WHERE username = '" + username + "' AND flag_tuesday = '" + 1 + "' AND flag_morning = '" + 1 + "'", null);
                        while (c1.moveToNext()) {
                            List<String> row = new ArrayList();
                            String medication_name = c1.getString(3);
                            String medication_dosage = c1.getString(7);
                            row.add(medication_name);
                            row.add(medication_dosage);
                            medications.add(row);
                        }
                        if (medications.isEmpty()) {
                            List<String> row = new ArrayList();
                            row.add("-");
                            row.add("-");
                            medications.add(row);
                        }
                        return medications;
                    case 2:
                        Cursor c2 = database.rawQuery("SELECT * FROM Medications WHERE username = '" + username + "' AND flag_tuesday = '" + 1 + "' AND flag_noon = '" + 1 + "'", null);
                        while (c2.moveToNext()) {
                            List<String> row = new ArrayList();
                            String medication_name = c2.getString(3);
                            String medication_dosage = c2.getString(7);
                            row.add(medication_name);
                            row.add(medication_dosage);
                            medications.add(row);
                        }
                        if (medications.isEmpty()) {
                            List<String> row = new ArrayList();
                            row.add("-");
                            row.add("-");
                            medications.add(row);
                        }
                        return medications;
                    case 3:
                        Cursor c3 = database.rawQuery("SELECT * FROM Medications WHERE username = '" + username + "' AND flag_tuesday = '" + 1 + "' AND flag_evening = '" + 1 + "'", null);
                        while (c3.moveToNext()) {
                            List<String> row = new ArrayList();
                            String medication_name = c3.getString(3);
                            String medication_dosage = c3.getString(7);
                            row.add(medication_name);
                            row.add(medication_dosage);
                            medications.add(row);
                        }
                        if (medications.isEmpty()) {
                            List<String> row = new ArrayList();
                            row.add("-");
                            row.add("-");
                            medications.add(row);
                        }
                        return medications;
                }
                break;
            case 4:
                switch (partofday) {
                    case 1:
                        Cursor c1 = database.rawQuery("SELECT * FROM Medications WHERE username = '" + username + "' AND flag_wednesday = '" + 1 + "' AND flag_morning = '" + 1 + "'", null);
                        while (c1.moveToNext()) {
                            List<String> row = new ArrayList();
                            String medication_name = c1.getString(3);
                            String medication_dosage = c1.getString(7);
                            row.add(medication_name);
                            row.add(medication_dosage);
                            medications.add(row);
                        }
                        if (medications.isEmpty()) {
                            List<String> row = new ArrayList();
                            row.add("-");
                            row.add("-");
                            medications.add(row);
                        }
                        return medications;
                    case 2:
                        Cursor c2 = database.rawQuery("SELECT * FROM Medications WHERE username = '" + username + "' AND flag_wednesday = '" + 1 + "' AND flag_noon = '" + 1 + "'", null);
                        while (c2.moveToNext()) {
                            List<String> row = new ArrayList();
                            String medication_name = c2.getString(3);
                            String medication_dosage = c2.getString(7);
                            row.add(medication_name);
                            row.add(medication_dosage);
                            medications.add(row);
                        }
                        if (medications.isEmpty()) {
                            List<String> row = new ArrayList();
                            row.add("-");
                            row.add("-");
                            medications.add(row);
                        }
                        return medications;
                    case 3:
                        Cursor c3 = database.rawQuery("SELECT * FROM Medications WHERE username = '" + username + "' AND flag_wednesday = '" + 1 + "' AND flag_evening = '" + 1 + "'", null);
                        while (c3.moveToNext()) {
                            List<String> row = new ArrayList();
                            String medication_name = c3.getString(3);
                            String medication_dosage = c3.getString(7);
                            row.add(medication_name);
                            row.add(medication_dosage);
                            medications.add(row);
                        }
                        if (medications.isEmpty()) {
                            List<String> row = new ArrayList();
                            row.add("-");
                            row.add("-");
                            medications.add(row);
                        }
                        return medications;
                }
                break;
            case 5:
                switch (partofday) {
                    case 1:
                        Cursor c1 = database.rawQuery("SELECT * FROM Medications WHERE username = '" + username + "' AND flag_thursday = '" + 1 + "' AND flag_morning = '" + 1 + "'", null);
                        while (c1.moveToNext()) {
                            List<String> row = new ArrayList();
                            String medication_name = c1.getString(3);
                            String medication_dosage = c1.getString(7);
                            row.add(medication_name);
                            row.add(medication_dosage);
                            medications.add(row);
                        }
                        if (medications.isEmpty()) {
                            List<String> row = new ArrayList();
                            row.add("-");
                            row.add("-");
                            medications.add(row);
                        }
                        return medications;
                    case 2:
                        Cursor c2 = database.rawQuery("SELECT * FROM Medications WHERE username = '" + username + "' AND flag_thursday = '" + 1 + "' AND flag_noon = '" + 1 + "'", null);
                        while (c2.moveToNext()) {
                            List<String> row = new ArrayList();
                            String medication_name = c2.getString(3);
                            String medication_dosage = c2.getString(7);
                            row.add(medication_name);
                            row.add(medication_dosage);
                            medications.add(row);
                        }
                        if (medications.isEmpty()) {
                            List<String> row = new ArrayList();
                            row.add("-");
                            row.add("-");
                            medications.add(row);
                        }
                        return medications;
                    case 3:
                        Cursor c3 = database.rawQuery("SELECT * FROM Medications WHERE username = '" + username + "' AND flag_thursday = '" + 1 + "' AND flag_evening = '" + 1 + "'", null);
                        while (c3.moveToNext()) {
                            List<String> row = new ArrayList();
                            String medication_name = c3.getString(3);
                            String medication_dosage = c3.getString(7);
                            row.add(medication_name);
                            row.add(medication_dosage);
                            medications.add(row);
                        }
                        if (medications.isEmpty()) {
                            List<String> row = new ArrayList();
                            row.add("-");
                            row.add("-");
                            medications.add(row);
                        }
                        return medications;
                }
                break;
            case 6:
                switch (partofday) {
                    case 1:
                        Cursor c1 = database.rawQuery("SELECT * FROM Medications WHERE username = '" + username + "' AND flag_friday = '" + 1 + "' AND flag_morning = '" + 1 + "'", null);
                        while (c1.moveToNext()) {
                            List<String> row = new ArrayList();
                            String medication_name = c1.getString(3);
                            String medication_dosage = c1.getString(7);
                            row.add(medication_name);
                            row.add(medication_dosage);
                            medications.add(row);
                        }
                        if (medications.isEmpty()) {
                            List<String> row = new ArrayList();
                            row.add("-");
                            row.add("-");
                            medications.add(row);
                        }
                        return medications;
                    case 2:
                        Cursor c2 = database.rawQuery("SELECT * FROM Medications WHERE username = '" + username + "' AND flag_friday = '" + 1 + "' AND flag_noon = '" + 1 + "'", null);
                        while (c2.moveToNext()) {
                            List<String> row = new ArrayList();
                            String medication_name = c2.getString(3);
                            String medication_dosage = c2.getString(7);
                            row.add(medication_name);
                            row.add(medication_dosage);
                            medications.add(row);
                        }
                        if (medications.isEmpty()) {
                            List<String> row = new ArrayList();
                            row.add("-");
                            row.add("-");
                            medications.add(row);
                        }
                        return medications;
                    case 3:
                        Cursor c3 = database.rawQuery("SELECT * FROM Medications WHERE username = '" + username + "' AND flag_friday = '" + 1 + "' AND flag_evening = '" + 1 + "'", null);
                        while (c3.moveToNext()) {
                            List<String> row = new ArrayList();
                            String medication_name = c3.getString(3);
                            String medication_dosage = c3.getString(7);
                            row.add(medication_name);
                            row.add(medication_dosage);
                            medications.add(row);
                        }
                        if (medications.isEmpty()) {
                            List<String> row = new ArrayList();
                            row.add("-");
                            row.add("-");
                            medications.add(row);
                        }
                        return medications;
                }
                break;
            case 7:
                switch (partofday) {
                    case 1:
                        Cursor c1 = database.rawQuery("SELECT * FROM Medications WHERE username = '" + username + "' AND flag_saturday = '" + 1 + "' AND flag_morning = '" + 1 + "'", null);
                        while (c1.moveToNext()) {
                            List<String> row = new ArrayList();
                            String medication_name = c1.getString(3);
                            String medication_dosage = c1.getString(7);
                            row.add(medication_name);
                            row.add(medication_dosage);
                            medications.add(row);
                        }
                        if (medications.isEmpty()) {
                            List<String> row = new ArrayList();
                            row.add("-");
                            row.add("-");
                            medications.add(row);
                        }
                        return medications;
                    case 2:
                        Cursor c2 = database.rawQuery("SELECT * FROM Medications WHERE username = '" + username + "' AND flag_saturday = '" + 1 + "' AND flag_noon = '" + 1 + "'", null);
                        while (c2.moveToNext()) {
                            List<String> row = new ArrayList();
                            String medication_name = c2.getString(3);
                            String medication_dosage = c2.getString(7);
                            row.add(medication_name);
                            row.add(medication_dosage);
                            medications.add(row);
                        }
                        if (medications.isEmpty()) {
                            List<String> row = new ArrayList();
                            row.add("-");
                            row.add("-");
                            medications.add(row);
                        }
                        return medications;
                    case 3:
                        Cursor c3 = database.rawQuery("SELECT * FROM Medications WHERE username = '" + username + "' AND flag_saturday = '" + 1 + "' AND flag_evening = '" + 1 + "'", null);
                        while (c3.moveToNext()) {
                            List<String> row = new ArrayList();
                            String medication_name = c3.getString(3);
                            String medication_dosage = c3.getString(7);
                            row.add(medication_name);
                            row.add(medication_dosage);
                            medications.add(row);
                        }
                        if (medications.isEmpty()) {
                            List<String> row = new ArrayList();
                            row.add("-");
                            row.add("-");
                            medications.add(row);
                        }
                        return medications;
                }
                break;
            case 1:
                switch (partofday) {
                    case 1:
                        Cursor c1 = database.rawQuery("SELECT * FROM Medications WHERE username = '" + username + "' AND flag_sunday = '" + 1 + "' AND flag_morning = '" + 1 + "'", null);
                        while (c1.moveToNext()) {
                            List<String> row = new ArrayList();
                            String medication_name = c1.getString(3);
                            String medication_dosage = c1.getString(7);
                            row.add(medication_name);
                            row.add(medication_dosage);
                            medications.add(row);
                        }
                        if (medications.isEmpty()) {
                            List<String> row = new ArrayList();
                            row.add("-");
                            row.add("-");
                            medications.add(row);
                        }
                        return medications;
                    case 2:
                        Cursor c2 = database.rawQuery("SELECT * FROM Medications WHERE username = '" + username + "' AND flag_sunday = '" + 1 + "' AND flag_noon = '" + 1 + "'", null);
                        while (c2.moveToNext()) {
                            List<String> row = new ArrayList();
                            String medication_name = c2.getString(3);
                            String medication_dosage = c2.getString(7);
                            row.add(medication_name);
                            row.add(medication_dosage);
                            medications.add(row);
                        }
                        if (medications.isEmpty()) {
                            List<String> row = new ArrayList();
                            row.add("-");
                            row.add("-");
                            medications.add(row);
                        }
                        return medications;
                    case 3:
                        Cursor c3 = database.rawQuery("SELECT * FROM Medications WHERE username = '" + username + "' AND flag_sunday = '" + 1 + "' AND flag_evening = '" + 1 + "'", null);
                        while (c3.moveToNext()) {
                            List<String> row = new ArrayList();
                            String medication_name = c3.getString(3);
                            String medication_dosage = c3.getString(7);
                            row.add(medication_name);
                            row.add(medication_dosage);
                            medications.add(row);
                        }
                        if (medications.isEmpty()) {
                            List<String> row = new ArrayList();
                            row.add("-");
                            row.add("-");
                            medications.add(row);
                        }
                        return medications;
                }
                break;
        }
        return medications;
    }
    public void decrement(String username, int day, int partofday) {
        SQLiteDatabase database = this.getWritableDatabase();
        switch (day) {
            case 2:
                switch (partofday) {
                    case 1:
                        database.execSQL("UPDATE Medications SET medication_count = medication_count - 1 WHERE username = '" + username + "' AND flag_monday = '" + 1 + "' AND flag_morning = '" + 1 + "'");
                        break;
                    case 2:
                        database.execSQL("UPDATE Medications SET medication_count = medication_count - 1 WHERE username = '" + username + "' AND flag_monday = '" + 1 + "' AND flag_noon = '" + 1 + "'");
                        break;
                    case 3:
                        database.execSQL("UPDATE Medications SET medication_count = medication_count - 1 WHERE username = '" + username + "' AND flag_monday = '" + 1 + "' AND flag_evening = '" + 1 + "'");
                        break;
                }
                break;
            case 3:
                switch (partofday) {
                    case 1:
                        database.execSQL("UPDATE Medications SET medication_count = medication_count - 1 WHERE username = '" + username + "' AND flag_tuesday = '" + 1 + "' AND flag_morning = '" + 1 + "'");
                        break;
                    case 2:
                        database.execSQL("UPDATE Medications SET medication_count = medication_count - 1 WHERE username = '" + username + "' AND flag_tuesday = '" + 1 + "' AND flag_noon = '" + 1 + "'");
                        break;
                    case 3:
                        database.execSQL("UPDATE Medications SET medication_count = medication_count - 1 WHERE username = '" + username + "' AND flag_tuesday = '" + 1 + "' AND flag_evening = '" + 1 + "'");
                        break;
                }
                break;
            case 4:
                switch (partofday) {
                    case 1:
                        database.execSQL("UPDATE Medications SET medication_count = medication_count - 1 WHERE username = '" + username + "' AND flag_wednesday = '" + 1 + "' AND flag_morning = '" + 1 + "'");
                        break;
                    case 2:
                        database.execSQL("UPDATE Medications SET medication_count = medication_count - 1 WHERE username = '" + username + "' AND flag_wednesday = '" + 1 + "' AND flag_noon = '" + 1 + "'");
                        break;
                    case 3:
                        database.execSQL("UPDATE Medications SET medication_count = medication_count - 1 WHERE username = '" + username + "' AND flag_wednesday = '" + 1 + "' AND flag_evening = '" + 1 + "'");
                        break;
                }
                break;
            case 5:
                switch (partofday) {
                    case 1:
                        database.execSQL("UPDATE Medications SET medication_count = medication_count - 1 WHERE username = '" + username + "' AND flag_thursday = '" + 1 + "' AND flag_morning = '" + 1 + "'");
                        break;
                    case 2:
                        database.execSQL("UPDATE Medications SET medication_count = medication_count - 1 WHERE username = '" + username + "' AND flag_thursday = '" + 1 + "' AND flag_noon = '" + 1 + "'");
                        break;
                    case 3:
                        database.execSQL("UPDATE Medications SET medication_count = medication_count - 1 WHERE username = '" + username + "' AND flag_thursday = '" + 1 + "' AND flag_evening = '" + 1 + "'");
                        break;
                }
                break;
            case 6:
                switch (partofday) {
                    case 1:
                        database.execSQL("UPDATE Medications SET medication_count = medication_count - 1 WHERE username = '" + username + "' AND flag_friday = '" + 1 + "' AND flag_morning = '" + 1 + "'");
                        break;
                    case 2:
                        database.execSQL("UPDATE Medications SET medication_count = medication_count - 1 WHERE username = '" + username + "' AND flag_friday = '" + 1 + "' AND flag_noon = '" + 1 + "'");
                        break;
                    case 3:
                        database.execSQL("UPDATE Medications SET medication_count = medication_count - 1 WHERE username = '" + username + "' AND flag_friday = '" + 1 + "' AND flag_evening = '" + 1 + "'");
                        break;
                }
                break;
            case 7:
                switch (partofday) {
                    case 1:
                        database.execSQL("UPDATE Medications SET medication_count = medication_count - 1 WHERE username = '" + username + "' AND flag_saturday = '" + 1 + "' AND flag_morning = '" + 1 + "'");
                        break;
                    case 2:
                        database.execSQL("UPDATE Medications SET medication_count = medication_count - 1 WHERE username = '" + username + "' AND flag_saturday = '" + 1 + "' AND flag_noon = '" + 1 + "'");
                        break;
                    case 3:
                        database.execSQL("UPDATE Medications SET medication_count = medication_count - 1 WHERE username = '" + username + "' AND flag_saturday = '" + 1 + "' AND flag_evening = '" + 1 + "'");
                        break;
                }
                break;
            case 1:
                switch (partofday) {
                case 1:
                    database.execSQL("UPDATE Medications SET medication_count = medication_count - 1 WHERE username = '" + username + "' AND flag_sunday = '" + 1 + "' AND flag_morning = '" + 1 + "'");
                    break;
                case 2:
                    database.execSQL("UPDATE Medications SET medication_count = medication_count - 1 WHERE username = '" + username + "' AND flag_sunday = '" + 1 + "' AND flag_noon = '" + 1 + "'");
                    break;
                case 3:
                    database.execSQL("UPDATE Medications SET medication_count = medication_count - 1 WHERE username = '" + username + "' AND flag_sunday = '" + 1 + "' AND flag_evening = '" + 1 + "'");
                    break;
            }
                break;
        }
    }
}