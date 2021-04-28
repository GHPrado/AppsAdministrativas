package com.example.stocksimple;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class MyDbAdapter {

    myDbHelper myhelper;

    public MyDbAdapter(Context context) {
        myhelper = new myDbHelper(context);
    }

    public ArrayList<String> getDescripcion(String code) {
        ArrayList descripcion = new ArrayList<String>();
        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor cursor6 =db.rawQuery("SELECT * FROM " + myDbHelper.TABLE_NAME_STOCK + " WHERE " + myDbHelper.codigoBarra + " = " + code, null );
        while (cursor6.moveToNext()) {
            descripcion.add(cursor6.getString(cursor6.getColumnIndex(myDbHelper.descripcion)));
        }
        db.close();

        return descripcion;
    }

    public long insertCompraVenta(  String escaneado,
                                    String descripcion,
                                    Float cantidad) {
        long id;

        SQLiteDatabase db = myhelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(myDbHelper.codigoBarra, escaneado);
        contentValues.put(myDbHelper.descripcion, descripcion);
        contentValues.put(myDbHelper.cantidad, cantidad);

        id = db.insert(myDbHelper.TABLE_NAME_STOCK, null, contentValues);
        db.close();

        return id;
    }

    public long insertPrecio(   String escaneado,
                                //String descripcion,
                                Float costo,
                                Float precio,
                                String fecha) {
        long id;

        SQLiteDatabase db = myhelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(myDbHelper.codigo_item, escaneado);
        //contentValues.put(myDbHelper.descripcion, descripcion);
        contentValues.put(myDbHelper.costo, costo);
        contentValues.put(myDbHelper.precio, precio);
        contentValues.put(myDbHelper.fecha, fecha);

        id = db.insert(myDbHelper.TABLE_NAME_PRECIOS, null, contentValues);
        db.close();

        return id;
    }

    public Float getCantidadStock(String code) {
        Float cantidad = null;
        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor cursor5 =db.rawQuery("SELECT SUM(" + myDbHelper.cantidad +") FROM " + myDbHelper.TABLE_NAME_STOCK + " WHERE " + myDbHelper.codigoBarra + " = " + code , null );
        if(cursor5.moveToFirst()) {cantidad = cursor5.getFloat(0);}
        db.close();
        return cantidad;
    }

    public ArrayList<String> getidPrecio(String code) {
        ArrayList costo = new ArrayList<String>();
        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor cursor6 =db.rawQuery("SELECT * FROM " + myDbHelper.TABLE_NAME_PRECIOS + " WHERE " + myDbHelper.codigo_item + " = " + code + " ORDER BY " + myDbHelper.UID_precio + " DESC", null );
        while (cursor6.moveToNext()) {
            costo.add(cursor6.getString(cursor6.getColumnIndex(myDbHelper.costo)));
        }
        db.close();

        return costo;
    }

    public ArrayList<String> getCodItem(String code) {
        ArrayList costo = new ArrayList<String>();
        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor cursor6 =db.rawQuery("SELECT * FROM " + myDbHelper.TABLE_NAME_PRECIOS + " WHERE " + myDbHelper.codigo_item + " = " + code + " ORDER BY " + myDbHelper.UID_precio + " DESC", null );
        while (cursor6.moveToNext()) {
            costo.add(cursor6.getString(cursor6.getColumnIndex(myDbHelper.costo)));
        }
        db.close();

        return costo;
    }

    public ArrayList<String> getCosto(String code) {
        ArrayList costo = new ArrayList<String>();
        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor cursor6 =db.rawQuery("SELECT * FROM " + myDbHelper.TABLE_NAME_PRECIOS + " WHERE " + myDbHelper.codigo_item + " = " + code + " ORDER BY " + myDbHelper.UID_precio + " DESC", null );
        while (cursor6.moveToNext()) {
            costo.add(cursor6.getString(cursor6.getColumnIndex(myDbHelper.costo)));
        }
        db.close();

        return costo;
    }

    public ArrayList<String> getPrecio(String code) {
        ArrayList precio = new ArrayList<String>();
        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor cursor6 =db.rawQuery("SELECT * FROM " + myDbHelper.TABLE_NAME_PRECIOS + " WHERE " + myDbHelper.codigo_item + " = " + code + " ORDER BY " + myDbHelper.UID_precio + " DESC", null );
        while (cursor6.moveToNext()) {
            precio.add(cursor6.getString(cursor6.getColumnIndex(myDbHelper.precio)));
        }
        db.close();

        return precio;
    }

    public ArrayList<String> getFecha(String code) {
        ArrayList fecha = new ArrayList<String>();
        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor cursor6 =db.rawQuery("SELECT * FROM " + myDbHelper.TABLE_NAME_PRECIOS + " WHERE " + myDbHelper.codigo_item + " = " + code + " ORDER BY " + myDbHelper.UID_precio + " DESC", null );
        while (cursor6.moveToNext()) {
            fecha.add(cursor6.getString(cursor6.getColumnIndex(myDbHelper.fecha)));
        }
        db.close();

        return fecha;
    }

    public ArrayList<String> getidPrecio() {
        ArrayList costo = new ArrayList<String>();
        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor cursor6 =db.rawQuery("SELECT * FROM " + myDbHelper.TABLE_NAME_PRECIOS + " ORDER BY " + myDbHelper.UID_precio + " DESC", null );
        while (cursor6.moveToNext()) {
            costo.add(cursor6.getString(cursor6.getColumnIndex(myDbHelper.UID_precio)));
        }
        db.close();

        return costo;
    }

    public ArrayList<String> getCodItem() {
        ArrayList costo = new ArrayList<String>();
        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor cursor6 =db.rawQuery("SELECT * FROM " + myDbHelper.TABLE_NAME_PRECIOS + " ORDER BY " + myDbHelper.UID_precio + " DESC", null );
        while (cursor6.moveToNext()) {
            costo.add(cursor6.getString(cursor6.getColumnIndex(myDbHelper.codigo_item)));
        }
        db.close();

        return costo;
    }

    public ArrayList<String> getCosto() {
        ArrayList costo = new ArrayList<String>();
        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor cursor6 =db.rawQuery("SELECT * FROM " + myDbHelper.TABLE_NAME_PRECIOS + " ORDER BY " + myDbHelper.UID_precio + " DESC", null );
        while (cursor6.moveToNext()) {
            costo.add(cursor6.getString(cursor6.getColumnIndex(myDbHelper.costo)));
        }
        db.close();

        return costo;
    }

    public ArrayList<String> getPrecio() {
        ArrayList precio = new ArrayList<String>();
        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor cursor6 =db.rawQuery("SELECT * FROM " + myDbHelper.TABLE_NAME_PRECIOS + " ORDER BY " + myDbHelper.UID_precio + " DESC", null );
        while (cursor6.moveToNext()) {
            precio.add(cursor6.getString(cursor6.getColumnIndex(myDbHelper.precio)));
        }
        db.close();

        return precio;
    }

    public ArrayList<String> getFecha() {
        ArrayList fecha = new ArrayList<String>();
        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor cursor6 =db.rawQuery("SELECT * FROM " + myDbHelper.TABLE_NAME_PRECIOS + " ORDER BY " + myDbHelper.UID_precio + " DESC", null );
        while (cursor6.moveToNext()) {
            fecha.add(cursor6.getString(cursor6.getColumnIndex(myDbHelper.fecha)));
        }
        db.close();

        return fecha;
    }

    public ArrayList<String> getidItem() {
        ArrayList costo = new ArrayList<String>();
        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor cursor6 =db.rawQuery("SELECT * FROM " + myDbHelper.TABLE_NAME_STOCK + " ORDER BY " + myDbHelper.UID_item + " DESC", null );
        while (cursor6.moveToNext()) {
            costo.add(cursor6.getString(cursor6.getColumnIndex(myDbHelper.UID_item)));
        }
        db.close();

        return costo;
    }

    public ArrayList<String> getCodigoBarra() {
        ArrayList costo = new ArrayList<String>();
        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor cursor6 =db.rawQuery("SELECT * FROM " + myDbHelper.TABLE_NAME_STOCK + " ORDER BY " + myDbHelper.UID_item + " DESC", null );
        while (cursor6.moveToNext()) {
            costo.add(cursor6.getString(cursor6.getColumnIndex(myDbHelper.codigoBarra)));
        }
        db.close();

        return costo;
    }

    public ArrayList<String> getDescripcion() {
        ArrayList costo = new ArrayList<String>();
        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor cursor6 =db.rawQuery("SELECT * FROM " + myDbHelper.TABLE_NAME_STOCK + " ORDER BY " + myDbHelper.UID_item + " DESC", null );
        while (cursor6.moveToNext()) {
            costo.add(cursor6.getString(cursor6.getColumnIndex(myDbHelper.descripcion)));
        }
        db.close();

        return costo;
    }

    public ArrayList<String> getCantidad() {
        ArrayList precio = new ArrayList<String>();
        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor cursor6 =db.rawQuery("SELECT * FROM " + myDbHelper.TABLE_NAME_STOCK + " ORDER BY " + myDbHelper.UID_item + " DESC", null );
        while (cursor6.moveToNext()) {
            precio.add(cursor6.getString(cursor6.getColumnIndex(myDbHelper.cantidad)));
        }
        db.close();

        return precio;
    }

    public long modificarBDStock(String UID_item, String codigoBarra, String descripcion, Float cantidad) {

        long id;

        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.UID_item, UID_item);
        contentValues.put(myDbHelper.codigoBarra, codigoBarra);
        contentValues.put(myDbHelper.descripcion, descripcion);
        contentValues.put(myDbHelper.cantidad, cantidad);
        id = db.insert(myDbHelper.TABLE_NAME_STOCK, null, contentValues);
        db.close();
        return id;
    }

    public long modificarBDPrecio(String UID_precio, String codigo_item, Float costo, Float precio, String fecha) {

        long id;

        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.UID_precio, UID_precio);
        contentValues.put(myDbHelper.codigo_item, codigo_item);
        contentValues.put(myDbHelper.costo, costo);
        contentValues.put(myDbHelper.precio, precio);
        contentValues.put(myDbHelper.fecha, fecha);
        id = db.insert(myDbHelper.TABLE_NAME_PRECIOS, null, contentValues);
        db.close();
        return id;
    }

    public void borrarDatosStock() {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        db.delete(myDbHelper.TABLE_NAME_STOCK,null,null);
        db.close();
    }

    public void borrarDatosPrecio() {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        db.delete(myDbHelper.TABLE_NAME_PRECIOS,null,null);
        db.close();
    }

/*
    public ArrayList<String> getDescripcion() {
        ArrayList descripcion = new ArrayList<String>();
        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor cursor6 =db.rawQuery("SELECT * FROM " + myDbHelper.TABLE_NAME_STOCK + " ORDER BY " + myDbHelper.UID_item + " DESC", null );
        while (cursor6.moveToNext()) {
            descripcion.add(cursor6.getString(cursor6.getColumnIndex(myDbHelper.descripcion)));
        }
        db.close();

        return descripcion;
    }

 */


/*


    public long insertEgreso(String text_ing_escaneado,
                             String descripcion,
                             Float cantidad,
                             Float precio,
                             Float rentab,
                             String fecha) {        long id;

        SQLiteDatabase db = myhelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(myDbHelper.item_codigo, text_ing_escaneado);
        contentValues.put(myDbHelper.item_descripcion, descripcion);
        contentValues.put(myDbHelper.item_cantidad, -cantidad);
        contentValues.put(myDbHelper.item_precio, precio);
        contentValues.put(myDbHelper.item_rentab, rentab);
        contentValues.put(myDbHelper.item_fecha, fecha);

        id = db.insert(myDbHelper.TABLE_NAME_ITEMS, null, contentValues);
        db.close();

        return id;
    }

    public void mostrarEsteDatoVenta(int id) {


    }

    public void borrarEsteDatoVenta(int id) {


    }

    public void borrarDatos() {
        SQLiteDatabase db = myhelper.getWritableDatabase();
       db.delete(myDbHelper.TABLE_NAME_ITEMS,null,null);
               db.close();
}




    public ArrayList<String> getUID_item() {
        ArrayList UID_item = new ArrayList<String>();
        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor cursor6 =db.rawQuery("SELECT * FROM " + myDbHelper.TABLE_NAME_ITEMS + " ORDER BY " + myDbHelper.UID_item + " DESC", null );
        while (cursor6.moveToNext()) {
            UID_item.add(cursor6.getString(cursor6.getColumnIndex(myDbHelper.UID_item)));
        }
        db.close();

        return UID_item;
    }



    public ArrayList<String> getCantidad() {
        ArrayList cantidad = new ArrayList<String>();
        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor cursor5 =db.rawQuery("SELECT * FROM " + myDbHelper.TABLE_NAME_ITEMS + " ORDER BY " + myDbHelper.UID_item + " DESC", null );
        while (cursor5.moveToNext()) {
            cantidad.add(cursor5.getString(cursor5.getColumnIndex(myDbHelper.item_cantidad)));
        }
        db.close();
        return cantidad;
    }

    public ArrayList<String> getPrecio() {
        ArrayList precio = new ArrayList<String>();
        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor cursor4 =db.rawQuery("SELECT * FROM " + myDbHelper.TABLE_NAME_ITEMS + " ORDER BY " + myDbHelper.UID_item + " DESC", null );
        while (cursor4.moveToNext()) {
            precio.add(cursor4.getString(cursor4.getColumnIndex(myDbHelper.item_precio)));
        }
        db.close();

        return precio;
    }

    public ArrayList<String> getRenta() {
        ArrayList renta = new ArrayList<String>();
        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor cursor3 =db.rawQuery("SELECT * FROM " + myDbHelper.TABLE_NAME_ITEMS + " ORDER BY " + myDbHelper.UID_item + " DESC", null );
        while (cursor3.moveToNext()) {
            renta.add(cursor3.getString(cursor3.getColumnIndex(myDbHelper.item_rentab)));
        }
        db.close();

        return renta;
    }

    public ArrayList<String> getFecha() {
        ArrayList fecha = new ArrayList<String>();
        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor cursor3 =db.rawQuery("SELECT * FROM " + myDbHelper.TABLE_NAME_ITEMS + " ORDER BY " + myDbHelper.UID_item + " DESC", null );
        while (cursor3.moveToNext()) {
            fecha.add(cursor3.getString(cursor3.getColumnIndex(myDbHelper.item_fecha)));
        }
        db.close();

        return fecha;
    }



    public ArrayList<String> getCodigo() {
        ArrayList codigo = new ArrayList<String>();
        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor cursor3 =db.rawQuery("SELECT * FROM " + myDbHelper.TABLE_NAME_ITEMS + " ORDER BY " + myDbHelper.UID_item + " DESC", null );
        while (cursor3.moveToNext()) {
            codigo.add(cursor3.getString(cursor3.getColumnIndex(myDbHelper.item_codigo)));
        }
        db.close();

        return codigo;
    }

    public Float getCantidad(String code) {
        Float cantidad = null;
        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor cursor5 =db.rawQuery("SELECT SUM(" + myDbHelper.item_cantidad +") FROM " + myDbHelper.TABLE_NAME_ITEMS + " WHERE " + myDbHelper.item_codigo + " = " + code , null );
        if(cursor5.moveToFirst()) {cantidad = cursor5.getFloat(0);}
        db.close();
        return cantidad;
    }

    public ArrayList<String> getUltCantidad(String code) {
        ArrayList precio = new ArrayList<String>();
        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor cursor4 =db.rawQuery("SELECT * FROM " + myDbHelper.TABLE_NAME_ITEMS + " WHERE " + myDbHelper.item_codigo + " = " + code + " AND " + myDbHelper.item_cantidad + " > 0 ORDER BY " + myDbHelper.UID_item + " DESC", null );
        while (cursor4.moveToNext()) {
            precio.add(cursor4.getString(cursor4.getColumnIndex(myDbHelper.item_cantidad)));
        }
        db.close();

        return precio;
    }

    public ArrayList<String> getPrecio(String code) {
        ArrayList precio = new ArrayList<String>();
        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor cursor4 =db.rawQuery("SELECT * FROM " + myDbHelper.TABLE_NAME_ITEMS + " WHERE " + myDbHelper.item_codigo + " = " + code + " AND " + myDbHelper.item_cantidad + " > 0 ORDER BY " + myDbHelper.UID_item + " DESC", null );
        while (cursor4.moveToNext()) {
            precio.add(cursor4.getString(cursor4.getColumnIndex(myDbHelper.item_precio)));
        }
        db.close();

        return precio;
    }

    public ArrayList<String> getRenta(String code) {
        ArrayList renta = new ArrayList<String>();
        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor cursor3 =db.rawQuery("SELECT * FROM " + myDbHelper.TABLE_NAME_ITEMS + " WHERE " + myDbHelper.item_codigo + " = " + code + " AND " + myDbHelper.item_cantidad + " > 0 ORDER BY " + myDbHelper.UID_item + " DESC", null );
        while (cursor3.moveToNext()) {
            renta.add(cursor3.getString(cursor3.getColumnIndex(myDbHelper.item_rentab)));
        }
        db.close();

        return renta;
    }

    public ArrayList<String> getFecha(String code) {
        ArrayList fecha = new ArrayList<String>();
        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor cursor3 =db.rawQuery("SELECT * FROM " + myDbHelper.TABLE_NAME_ITEMS + " WHERE " + myDbHelper.item_codigo + " = " + code + " AND " + myDbHelper.item_cantidad + " > 0 ORDER BY " + myDbHelper.UID_item + " DESC", null );
        while (cursor3.moveToNext()) {
            fecha.add(cursor3.getString(cursor3.getColumnIndex(myDbHelper.item_fecha)));
        }
        db.close();

        return fecha;
    }
*/

    static class myDbHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "StockSimpleV8";    // Database Name
        private static final int DATABASE_Version = 1;    // Database Version

        private static final String TABLE_NAME_STOCK = "stock";
        private static final String UID_item="_iditem";
        private static final String codigoBarra = "codigo";
        private static final String descripcion = "descripcion";
        private static final String cantidad = "cantidad";

        private static final String CREATE_TABLE1 = "CREATE TABLE "+TABLE_NAME_STOCK+ " ("+
                UID_item+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                codigoBarra+" VARCHAR(255)," +
                descripcion+" VARCHAR(255),"+
                cantidad+" FLOAT(20,2)"+
                ");";

        private static final String TABLE_NAME_PRECIOS = "precios";
        private static final String UID_precio="idPrecio";
        private static final String codigo_item = "codItem";
        private static final String costo = "costo";
        private static final String precio = "precio";
        private static final String fecha = "fecha";

        private static final String CREATE_TABLE2 = "CREATE TABLE "+TABLE_NAME_PRECIOS+ " ("+
                UID_precio+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                codigo_item+" VARCHAR(255),"+
                costo+" FLOAT(20,2),"+
                precio+" FLOAT(20,2),"+
                fecha+" VARCHAR(255)" +
                ", FOREIGN KEY("+
                codigo_item+") REFERENCES "+
                TABLE_NAME_STOCK+"("+
                codigoBarra+")"+
                ");";

        private static final String DROP_TABLE1="DROP TABLE IF EXISTS "+TABLE_NAME_STOCK;
        private static final String DROP_TABLE2="DROP TABLE IF EXISTS "+TABLE_NAME_PRECIOS;

        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE1);
                db.execSQL(CREATE_TABLE2);
            } catch (Exception e) {
                Toast.makeText(context, " ERROR DE GRABACION !!!   : "+e.toString(), Toast.LENGTH_LONG).show();

            }

        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                db.execSQL(DROP_TABLE1);
                db.execSQL(DROP_TABLE2);
                onCreate(db);
            }catch (Exception e) {
                Toast.makeText(context, " ERROR DE GRABACION !!!   "+e.toString(), Toast.LENGTH_LONG).show();

            }
        }
    }
}
