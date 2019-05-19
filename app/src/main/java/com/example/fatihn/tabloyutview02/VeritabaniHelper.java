package com.example.fatihn.tabloyutview02;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VeritabaniHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "veresiyedb"; //Veritabanı Adı
    private static final String TABLE_NAME = "kisilertb"; //Tablo Adı
    private static String KISI_ID="id";
    private static String KISI_AD ="ad";
    private static String KISI_TELEFON="telefon";
    private static String KISI_ADRES ="adres";
    private static String KISI_TUTAR="tutar";

    public VeritabaniHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       String CREATION_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KISI_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KISI_AD + " TEXT,"
                + KISI_TELEFON + " TEXT,"
                + KISI_ADRES + " TEXT,"
                + KISI_TUTAR + " TEXT" + ")";
       db.execSQL(CREATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);//onCreate fonksiyonu çağrılarak oluşturduğumuz veritabanı tablosunda güncelleme yaptık.
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }
    //
    //Veritabanı işlemleri
    //
    //Kişi Ekleme
    public boolean veriEkle(String ad, String telefon, String adres, String tutar) {
        SQLiteDatabase db = this.getWritableDatabase();//SQLiteDatabase sınıfında yazılabilir bağlantı açıyoruz.
        ContentValues cv = new ContentValues();//contentValues sınıfından bir nesne oluşturuyoruz.ContentValues değerleri tutmamızı sağlıyor.
        cv.put(KISI_AD, ad);//Ad alıyoruz.
        cv.put(KISI_TELEFON, telefon);
        cv.put(KISI_ADRES, adres);
        cv.put(KISI_TUTAR, tutar);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Log.i("tag", "İşlem Başarılı");//ekleme olması durumunda bu çıktıyı verir.
            db.close();
            return false;
        } else {
            Log.e("tag", "İşlem Başarısız");//ekleme olmaması durumunda bu çıktıyı verir.
            db.close();
            return true;
        }

    }
    //Kişi Sil
    public void veriSil(String ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,KISI_ID + " = ?", new String[]{ID});
        db.close();
    }
    // Tüm listeyi getir
    public List<VeriKisilerModel> veriGetir(){
        List<VeriKisilerModel> data=new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME+" ;",null);
        //StringBuffer stringBuffer = new StringBuffer();
        VeriKisilerModel vm = null;
        while (cursor.moveToNext()) {
            vm= new VeriKisilerModel();
            String id = cursor.getString(cursor.getColumnIndexOrThrow("id"));
            String ad = cursor.getString(cursor.getColumnIndexOrThrow("ad"));
            String telefon = cursor.getString(cursor.getColumnIndexOrThrow("telefon"));
            String adres = cursor.getString(cursor.getColumnIndexOrThrow("adres"));
            String tutar = cursor.getString(cursor.getColumnIndexOrThrow("tutar"));

            vm.setId(id);
            vm.setAd(ad);
            vm.setTelefon(telefon);
            vm.setAdres(adres);
            vm.setTutar(tutar);
           // stringBuffer.append(vm);
            data.add(vm);
        }

        for (VeriKisilerModel mo:data ) {

            Log.i("Merhaba",""+mo.getAd());
        }
        cursor.close();
        db.close();
        return data;
    }

    public ArrayList<VeriKisilerModel> aramaYap(String adi) {
        //List<VeriKisilerModel> data = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<VeriKisilerModel> liste = new ArrayList<>();
        String sorgu = "SELECT * FROM " + TABLE_NAME + " WHERE " + KISI_AD + " LIKE '%" + adi + "%'";
        Cursor cursor = db.rawQuery(sorgu, null);
        if (cursor != null) {
            VeriKisilerModel vm = null;
            if (cursor.moveToFirst()) {
                do {
                    vm = new VeriKisilerModel();
                    String id = cursor.getString(cursor.getColumnIndexOrThrow("id"));
                    String ad = cursor.getString(cursor.getColumnIndexOrThrow("ad"));
                    String telefon = cursor.getString(cursor.getColumnIndexOrThrow("telefon"));
                    String adres = cursor.getString(cursor.getColumnIndexOrThrow("adres"));
                    String tutar = cursor.getString(cursor.getColumnIndexOrThrow("tutar"));
                    vm.setId(id);
                    vm.setAd(ad);
                    vm.setTelefon(telefon);
                    vm.setAdres(adres);
                    vm.setTutar(tutar);
                    liste.add(vm);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        db.close();
        return liste;
    }

    public void veriGuncelle( String AD, String TELEFON, String ADRES, String TUTAR,String ID){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KISI_AD,AD);
        cv.put(KISI_TELEFON,TELEFON);
        cv.put(KISI_ADRES,ADRES);
        cv.put(KISI_TUTAR, TUTAR);
        //cv.put(KISI_ID,ID);
        db.update(TABLE_NAME, cv, KISI_ID + " = ?",
                new String[]{ String.valueOf(ID) });
        db.close();
    }

    public boolean borcEkle(String ID, String Tutar){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        //cv.put("id",ID);
        cv.put("tutar",Tutar);
        //db.update(TABLE_NAME, cv, "id"+"="+ID,null);
       db.update(TABLE_NAME, cv, KISI_ID+" = "+ID, null);
        return true;
    }

    public void borcDus(String ID, String Tutar){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        //cv.put("id",ID);
        cv.put("tutar",Tutar);
        db.update(TABLE_NAME, cv, "id"+"="+ID,null);
        // db.update(TABLE_NAME, cv, KISI_ID+" = "+ID, null);
    }

    public float toplamBorc(){
        float top=0;
        SQLiteDatabase db=this.getReadableDatabase();
        String sorgu="SELECT sum(tutar) as Total FROM "+TABLE_NAME;
        Cursor c=db.rawQuery(sorgu,null);
       if (c.moveToFirst())
            top = (c.getFloat(c.getColumnIndex("Total")));
            return top;
    }

    public VeriKisilerModel kisiGetir(String ID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sorgu= "SELECT * FROM " + TABLE_NAME + " WHERE " + KISI_ID + " = " + ID;
        Cursor c = db.rawQuery(sorgu, null);
        if (c != null) {
            c.moveToFirst();
            VeriKisilerModel kisi=new VeriKisilerModel();
            kisi.setId(c.getString(c.getColumnIndexOrThrow(KISI_ID)));
            kisi.setAd(c.getString(c.getColumnIndexOrThrow(KISI_AD)));
            kisi.setTelefon(c.getString(c.getColumnIndexOrThrow(KISI_TELEFON)));
            kisi.setAdres(c.getString(c.getColumnIndexOrThrow(KISI_ADRES)));
            kisi.setTutar(c.getString(c.getColumnIndexOrThrow(KISI_TUTAR)));
            return kisi;
        } else {
            return null;
        }
    }

}