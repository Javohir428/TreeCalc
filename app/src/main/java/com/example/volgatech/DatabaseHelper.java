package com.example.volgatech;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteAssetHelper {
    private static final String DB_NAME = "DB3";
    private static final int DB_VER=1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    public List<Area> getDates(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect={"id_trial_area", "Forestry", "Local_Forestry", "Kvartal", "Vydel", "Nomer_PP", "Square", "Date"};
        String sqlTable="trial_area";

        qb.setTables(sqlTable);
        Cursor c = qb.query(db,sqlSelect,null,null,null,null,null);

        final List<Area> result = new ArrayList<>();
        if (c.moveToFirst()){
            do{
                result.add(new Area(c.getInt(c.getColumnIndex("id_trial_area")),
                        c.getString(c.getColumnIndex("Forestry")),
                        c.getString(c.getColumnIndex("Local_Forestry")),
                        c.getInt(c.getColumnIndex("Kvartal")),
                        c.getInt(c.getColumnIndex("Vydel")),
                        c.getInt(c.getColumnIndex("Nomer_PP")),
                        c.getFloat(c.getColumnIndex("Square")),
                        c.getString(c.getColumnIndex("Date"))));
            } while (c.moveToNext());
        }
        return result;
    }

    public List<Tree> getTreeDates(String id){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect={"Poroda", "Diameter", "Delovye", "Poludelovye", "Drova", "RH", "Stupen"};
        String sqlTable="register";
        String [] iD = { id };


        qb.setTables(sqlTable);
        Cursor c = qb.query(db,sqlSelect,"id_trial_area=?",iD,null,null,null);

        final List<Tree> result = new ArrayList<>();
        if (c.moveToFirst()){
            do{
                result.add(new Tree(c.getString(c.getColumnIndex("Poroda")),
                        c.getInt(c.getColumnIndex("Diameter")),
                        c.getInt(c.getColumnIndex("Delovye")),
                        c.getInt(c.getColumnIndex("Poludelovye")),
                        c.getInt(c.getColumnIndex("Drova")),
                        c.getInt(c.getColumnIndex("RH")),
                        c.getInt(c.getColumnIndex("Stupen"))));
            } while (c.moveToNext());
        }
        return result;
    }

    public void addDatesArea(Area area){
        SQLiteDatabase db = getWritableDatabase();
        String query = String.format("INSERT INTO trial_area (Forestry, Local_Forestry, Kvartal, Vydel, Nomer_PP, Square, Date) VALUES('%s', '%s', '%s', '%s', '%s', '%s', '%s');",
                area.getForestry(),
                area.getLocalForestry(),
                area.getKvartal(),
                area.getVidel(),
                area.getNumber_pp(),
                area.getArea(),
                area.getDate());
        db.execSQL(query);
    }

    public void addDatesTree(Tree tree, String id){
        SQLiteDatabase db = getWritableDatabase();
        String query = String.format("INSERT INTO register (id_trial_area, Poroda, Diameter, Delovye, Poludelovye, Drova, RH, Stupen) VALUES('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');",
                id,
                tree.getPoroda(),
                tree.getDiametr(),
                tree.getDel_kol(),
                tree.getPol_del_kol(),
                tree.getDrova_kol(),
                tree.getRh(),
                tree.getStupen());
        db.execSQL(query);
    }

    public void removeFromDatabaseArea(int id) {
        SQLiteDatabase db = getWritableDatabase();
        String query = String.format("DELETE FROM trial_area WHERE id_trial_area = '%s'", id);
        db.execSQL(query);
    }

    public void removeFromDatabaseRegister(int id) {
        SQLiteDatabase db = getWritableDatabase();
        String query = String.format("DELETE FROM register WHERE id_trial_area = '%s'", id);
        db.execSQL(query);
    }

    public int getLastId() {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT ROWID from trial_area order by ROWID DESC limit 1";

        //Log.d(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null && c.moveToFirst()) {
            return (int) c.getLong(0); //The 0 is the column index, we only have 1 column, so the index is 0
        }

        return 0;
    }

    public Sortiment getSortiment(String poroda, int razryad_visot, int stupen_tolshini) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = String.format("SELECT V, Kr, Sr1, Sr2, Ml, Del_Tex, Del_Drov, Del_Otx, Dr_Tex, Dr_Drov, Dr_Otx, Kora FROM Sortiment WHERE Poroda='%s' AND RH = %s AND Stupen = %s", poroda, razryad_visot, stupen_tolshini);

        //Log.d(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            Sortiment rowData = new Sortiment();
            rowData.setV((c.getFloat(c.getColumnIndex("V"))));
            rowData.setKr((c.getFloat(c.getColumnIndex("Kr"))));
            rowData.setSr1((c.getFloat(c.getColumnIndex("Sr1"))));
            rowData.setSr2((c.getFloat(c.getColumnIndex("Sr2"))));
            rowData.setMl((c.getFloat(c.getColumnIndex("Ml"))));
            rowData.setDel_tex((c.getFloat(c.getColumnIndex("Del_Tex"))));
            rowData.setDel_drov((c.getFloat(c.getColumnIndex("Del_Drov"))));
            rowData.setDel_otx((c.getFloat(c.getColumnIndex("Del_Otx"))));
            rowData.setDr_tex((c.getFloat(c.getColumnIndex("Dr_Tex"))));
            rowData.setDr_drov((c.getFloat(c.getColumnIndex("Dr_Drov"))));
            rowData.setDr_otx((c.getFloat(c.getColumnIndex("Dr_Otx"))));
            rowData.setKora((c.getFloat(c.getColumnIndex("Kora"))));
            return rowData;
        } else {
            Sortiment rowData = new Sortiment();
            rowData.setV(0);
            rowData.setKr(0);
            rowData.setSr1(0);
            rowData.setKora(0);
            rowData.setMl(0);
            rowData.setDel_tex(0);
            rowData.setDel_drov(0);
            rowData.setDel_otx(0);
            rowData.setDr_tex(0);
            rowData.setDr_drov(0);
            rowData.setDr_otx(0);
            rowData.setKora(0);
            return rowData;
        }
    }

}
