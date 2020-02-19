package com.example.sounding.project;
//Layout2 adalah untuk tangki UF1

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;


public class Layout_2 extends AppCompatActivity implements View.OnClickListener{

    Button tombol;
    Intent pindah;
    private Spinner spNamen;
    private Spinner spNamen2;
    private Spinner spNamen3;

    private EditText edtLebar;
    private EditText edtLuas;
    private EditText edtBeratKg;
    private EditText edtLogsheet;
    private EditText edtCustomer;
    private EditText edtTujuan;
    private EditText edtNopol;
    private EditText edtDriver;

    private Button btnHitungLuas;
    private EditText edtKesimpulan;
    private EditText edtKesimpulan2;
    private EditText strDate2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_2);
        spNamen = (Spinner) findViewById(R.id.sp_name);
        spNamen2 = (Spinner) findViewById(R.id.sp_name2);
        spNamen3 = (Spinner) findViewById(R.id.sp_name3);

        initUI();
        initEvent();




        tombol = (Button)findViewById(R.id.ke_layout_1);

        tombol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kode untuk pindah ke actifity lain
                pindah = new Intent(Layout_2.this, MainActivity.class);
                startActivity(pindah);
                //saat pindah, activity yg sekarang langsung ditutup
                //agar saat menekan tombol kembali tidak bolak-balik
                finish();
            }
        });



        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView tdate = (TextView) findViewById(R.id.date);
                                long date = System.currentTimeMillis();
                                SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy\nhh:mm:ss a");
                                String dateString = sdf.format(date);
                                tdate.setText(dateString);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();
    }


    private void initUI(){
        edtLebar = (EditText) findViewById(R.id.editTextLebar);
        edtLuas = (EditText) findViewById(R.id.editTextLuas);
        edtBeratKg = (EditText) findViewById(R.id.editTextBeratKg);
        edtLogsheet = (EditText) findViewById(R.id.editTextLogsheet);
        edtCustomer = (EditText) findViewById(R.id.editTextCustomer);
        edtTujuan = (EditText) findViewById(R.id.editTextTujuan);
        edtNopol = (EditText) findViewById(R.id.editTextNopol);
        edtDriver = (EditText) findViewById(R.id.editTextDriver);
        btnHitungLuas = (Button) findViewById(R.id.buttonHitungLuas);
        edtKesimpulan = (EditText) findViewById(R.id.editTextKesimpulan);
        edtKesimpulan2 = (EditText) findViewById(R.id.editTextKesimpulan2);

    }

    private void initEvent(){
        btnHitungLuas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cekKondisi();
                //hitungLuas();
            }
        });
    }

    private void cekKondisi() {
        if(edtLogsheet.getText().toString().length()==0){
            edtLogsheet.setError("Nomor Logsheet diperlukan!");
            } else if(edtCustomer.getText().toString().length()==0) {
            edtCustomer.setError("Cust diperlukan!");
        } else if(edtTujuan.getText().toString().length()==0) {
            edtTujuan.setError("Tujuan diperlukan!");
        } else if(edtNopol.getText().toString().length()==0) {
            edtNopol.setError("Nopol diperlukan!");
        } else if(edtDriver.getText().toString().length()==0) {
            edtDriver.setError("Driver diperlukan!");
        } else if(edtLebar.getText().toString().length()==0){
                            edtLebar.setError("Level diperlukan!");
        }
        else
         {
            hitungLuas();

         }
    }


    //Dibawah ini merupakan perintah untuk Menambahkan  (CREATE)
    private void addEmployee()
    {
        String ctank = "UF1";

        final String level = edtLebar.getText().toString().trim();
        final String ltr = edtLuas.getText().toString().trim();
        final String volume = edtBeratKg.getText().toString().trim();
        final String nologs = edtLogsheet.getText().toString().trim();
        final String custumer = edtCustomer.getText().toString().trim();
        final String tujuan = edtTujuan.getText().toString().trim();
        final String nopol = edtNopol.getText().toString().trim();
        final String driver = edtDriver.getText().toString().trim();
        final String tank;
        tank = ctank;
        final String keterangan = edtKesimpulan.getText().toString().trim();
        final String keterangan2 = edtKesimpulan2.getText().toString().trim();
        final String stat1 = spNamen.getSelectedItem().toString(); //nama tangki
        final String produk1 = spNamen2.getSelectedItem().toString(); //nama produk
        final String oprt1 = spNamen3.getSelectedItem().toString(); //nama operator
        //final String tanggal = strDate2.getText().toString(); //tanggal


        class AddEmployee extends AsyncTask<Void, Void, String> {

            ProgressDialog loading;

           // @Override
           // protected void onPreExecute() {
           //    super.onPreExecute();
           //    loading = ProgressDialog.show(Layout_2.this,"Menambahkan...","Tunggu...",false,false);
           //}

           //@Override
           //protected void onPostExecute(String s) {
           //    super.onPostExecute(s);
           //    loading.dismiss();
           //     Toast.makeText(Layout_2.this,s,Toast.LENGTH_LONG).show();
           //}

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> params = new HashMap<>();
                params.put(konfigurasi.KEY_EMP_LEVEL,level);
                params.put(konfigurasi.KEY_EMP_LTR,ltr);
                params.put(konfigurasi.KEY_EMP_VOLUME,volume);
                params.put(konfigurasi.KEY_EMP_NOLOGS,nologs);
                params.put(konfigurasi.KEY_EMP_CUSTUMER,custumer);
                params.put(konfigurasi.KEY_EMP_TUJUAN,tujuan);
                params.put(konfigurasi.KEY_EMP_NOPOL,nopol);
                params.put(konfigurasi.KEY_EMP_DRIVER,driver);
                params.put(konfigurasi.KEY_EMP_TANK,tank);
                params.put(konfigurasi.KEY_EMP_STATUS,stat1);
                params.put(konfigurasi.KEY_EMP_PRODUK,produk1);
                params.put(konfigurasi.KEY_EMP_OPERATOR,oprt1);
                params.put(konfigurasi.KEY_EMP_KETERANGAN,keterangan);
                params.put(konfigurasi.KEY_EMP_KETERANGAN2,keterangan2);
                //params.put(konfigurasi.KEY_EMP_TANGGAL,tanggal);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(konfigurasi.URL_ADD, params);
                return res;
            }
        }

        new AddEmployee().execute();
    }

    private void hitungLuas(){
        double jari = 1512.5/6.28;
        Float lebar = Float.parseFloat(edtLebar.getText().toString());
        double phi = Double.parseDouble("3.14".toString());
        double SG = Double.parseDouble("1.290".toString());

        double luas = (phi*(jari*jari)*lebar/1000);
        //NumberFormat format = new DecimalFormat("0.#");
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        String numberAsString = decimalFormat.format(luas);
        //edtLuas.setText(decimalFormat.getNumberInstance(Locale.GERMANY).format(luas));
        edtLuas.setText(numberAsString);

        double beratkg = (luas * SG);
        DecimalFormat decimalFormat2 = new DecimalFormat("#,##0.00");
        String numberAsString2 = decimalFormat2.format(beratkg);
        edtBeratKg.setText(numberAsString2);
        //edtBeratKg.setText(DecimalFormat.getNumberInstance(Locale.GERMANY).format(beratkg));
        String pil1 = spNamen.getSelectedItem().toString();
        char ch;
        ch = pil1.charAt(2);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
        String strDate = mdformat.format(calendar.getTime());

        //SimpleDateFormat mdformat2 = new SimpleDateFormat("yyyy-MM-dd");
        //String strDate2 = mdformat2.format(calendar.getTime());

        String dapil = "/";
        String pil2 = spNamen2.getSelectedItem().toString();
        String pil3 = spNamen3.getSelectedItem().toString();
        String tank = "UF1";
        String Logs = edtLogsheet.getText().toString();
        String Customer = edtCustomer.getText().toString();
        String Tujuan = edtTujuan.getText().toString();
        String Nopol = edtNopol.getText().toString();
        String Driver = edtDriver.getText().toString();
        edtKesimpulan.setText(pil1 + dapil + strDate + dapil + tank + dapil + DecimalFormat.getNumberInstance(Locale.GERMANY).format(beratkg) + dapil + pil2 + dapil + pil3.toUpperCase());
        edtKesimpulan2.setText( Logs + dapil + ch + dapil + Customer.toUpperCase() + dapil + Tujuan.toUpperCase() + dapil + Nopol.toUpperCase() + dapil + Driver.toUpperCase());
        addEmployee();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onClick(View view) {

    }
}