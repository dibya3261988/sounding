package com.example.sounding.project;
//UF7

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

public class Layout_8 extends AppCompatActivity {
    Button tombol7;
    Intent pindah7;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_8);
        spNamen = (Spinner) findViewById(R.id.sp_name);
        spNamen2 = (Spinner) findViewById(R.id.sp_name2);
        spNamen3 = (Spinner) findViewById(R.id.sp_name3);

        initUI();
        initEvent();

        tombol7 = (Button)findViewById(R.id.ke_layout_1);

        tombol7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //kode untuk pindah ke actifity lain
                pindah7 = new Intent(Layout_8.this, MainActivity.class);
                startActivity(pindah7);
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
        else {
            hitungLuas();
            addEmployee();

        }
    }

    private void hitungLuas(){
        double jari = 1099/6.28;
        double phi = Double.parseDouble("3.14".toString());
        double p3 = Double.parseDouble("0.33".toString());
        double kons = Double.parseDouble("40".toString());
        double SG = Double.parseDouble("1.193".toString());
        double x;
        Float lebar = Float.parseFloat(edtLebar.getText().toString());

        double luas = (phi*(jari*jari)*lebar/1000);
        double luas2 = ((p3*phi*jari*jari)*kons/1000);
        double luas3 = (luas + luas2);
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        String numberAsString = decimalFormat.format(luas3);
        edtLuas.setText(numberAsString);
        double beratkg = (luas3 * SG);
        edtBeratKg.setText(decimalFormat.format(beratkg)+"");

        String pil1 = spNamen.getSelectedItem().toString();
        char ch;
        ch = pil1.charAt(2);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
        String strDate = mdformat.format(calendar.getTime());
        String dapil = "/";
        String pil2 = spNamen2.getSelectedItem().toString();
        String pil3 = spNamen3.getSelectedItem().toString();

        String tank = "UF7";
        String Logs = edtLogsheet.getText().toString();
        String Customer = edtCustomer.getText().toString();
        String Tujuan = edtTujuan.getText().toString();
        String Nopol = edtNopol.getText().toString();
        String Driver = edtDriver.getText().toString();

        edtKesimpulan.setText(pil1 + dapil + strDate + dapil + tank + dapil + decimalFormat.format(beratkg) + dapil + pil2 + dapil + pil3);
        edtKesimpulan2.setText( Logs + dapil + ch + dapil + Customer.toUpperCase() + dapil + Tujuan.toUpperCase() + dapil + Nopol.toUpperCase() + dapil + Driver.toUpperCase());

    }

/*
    private void hitungLuas(){
        int lebar = Integer.parseInt(edtLebar.getText().toString());
        if (lebar > 40) {
            double phi = Double.parseDouble("3.14".toString());
            double p3 = Double.parseDouble("0.33".toString());
            double kons = Double.parseDouble("40".toString());
            double SG = Double.parseDouble("1.193".toString());
            double x;
            if (lebar == 41) {
                x = 1;
            } else if (lebar == 42) {
                x = 2;
            } else if (lebar == 43) {
                x = 3;
            } else if (lebar == 44) {
                x = 4;
            } else if (lebar == 45) {
                x = 5;
            } else if (lebar == 46) {
                x = 6;
            } else if (lebar == 47) {
                x = 7;
            } else if (lebar == 48) {
                x = 8;
            } else if (lebar == 49) {
                x = 9;
            } else if (lebar == 50) {
                x = 10;
            } else if (lebar == 51) {
                x = 11;
            } else if (lebar == 52) {
                x = 12;
            } else if (lebar == 53) {
                x = 13;
            } else if (lebar == 54) {
                x = 14;
            } else if (lebar == 55) {
                x = 15;
            } else if (lebar == 56) {
                x = 16;
            } else if (lebar == 57) {
                x = 17;
            } else if (lebar == 58) {
                x = 18;
            } else if (lebar == 59) {
                x = 19;
            } else if (lebar == 60) {
                x = 20;
            } else if (lebar == 61) {
                x = 21;
            } else if (lebar == 62) {
                x = 22;
            } else if (lebar == 63) {
                x = 23;
            } else if (lebar == 64) {
                x = 24;
            } else if (lebar == 65) {
                x = 25;
            } else if (lebar == 66) {
                x = 26;
            } else if (lebar == 67) {
                x = 27;
            } else if (lebar == 68) {
                x = 28;
            } else if (lebar == 69) {
                x = 29;
            } else if (lebar == 70) {
                x = 30;
            } else if (lebar == 71) {
                x = 31;
            } else if (lebar == 72) {
                x = 32;
            } else if (lebar == 73) {
                x = 33;
            } else if (lebar == 74) {
                x = 34;
            } else if (lebar == 75) {
                x = 35;
            } else if (lebar == 76) {
                x = 36;
            } else if (lebar == 77) {
                x = 37;
            } else if (lebar == 78) {
                x = 38;
            } else if (lebar == 79) {
                x = 39;
            } else if (lebar == 80) {
                x = 40;
            } else if (lebar == 81) {
                x = 41;
            } else if (lebar == 82) {
                x = 42;
            } else if (lebar == 83) {
                x = 43;
            } else if (lebar == 84) {
                x = 44;
            } else if (lebar == 85) {
                x = 45;
            } else if (lebar == 86) {
                x = 46;
            } else if (lebar == 87) {
                x = 47;
            } else if (lebar == 88) {
                x = 48;
            } else if (lebar == 89) {
                x = 49;
            } else if (lebar == 90) {
                x = 50;
            } else if (lebar == 91) {
                x = 51;
            } else if (lebar == 92) {
                x = 52;
            } else if (lebar == 93) {
                x = 53;
            } else if (lebar == 94) {
                x = 54;
            } else if (lebar == 95) {
                x = 55;
            } else if (lebar == 96) {
                x = 56;
            } else if (lebar == 97) {
                x = 57;
            } else if (lebar == 98) {
                x = 58;
            } else if (lebar == 99) {
                x = 59;
            } else if (lebar == 100) {
                x = 60;
            } else if (lebar == 101) {
                x = 61;
            } else if (lebar == 102) {
                x = 62;
            } else if (lebar == 103) {
                x = 63;
            } else if (lebar == 104) {
                x = 64;
            } else if (lebar == 105) {
                x = 65;
            } else if (lebar == 106) {
                x = 66;
            } else if (lebar == 107) {
                x = 67;
            } else if (lebar == 108) {
                x = 68;
            } else if (lebar == 109) {
                x = 69;
            } else if (lebar == 110) {
                x = 70;
            } else if (lebar == 111) {
                x = 71;
            } else if (lebar == 112) {
                x = 72;
            } else if (lebar == 113) {
                x = 73;
            } else if (lebar == 114) {
                x = 74;
            } else if (lebar == 115) {
                x = 75;
            } else if (lebar == 116) {
                x = 76;
            } else if (lebar == 117) {
                x = 77;
            } else if (lebar == 118) {
                x = 78;
            } else if (lebar == 119) {
                x = 79;
            } else if (lebar == 120) {
                x = 80;
            } else if (lebar == 121) {
                x = 81;
            } else if (lebar == 122) {
                x = 82;
            } else if (lebar == 123) {
                x = 83;
            } else if (lebar == 124) {
                x = 84;
            } else if (lebar == 125) {
                x = 85;
            } else if (lebar == 126) {
                x = 86;
            } else if (lebar == 127) {
                x = 87;
            } else if (lebar == 128) {
                x = 88;
            } else if (lebar == 129) {
                x = 89;
            } else if (lebar == 130) {
                x = 90;
            } else if (lebar == 131) {
                x = 91;
            } else if (lebar == 132) {
                x = 92;
            } else if (lebar == 133) {
                x = 93;
            } else if (lebar == 134) {
                x = 94;
            } else if (lebar == 135) {
                x = 95;
            } else if (lebar == 136) {
                x = 96;
            } else if (lebar == 137) {
                x = 97;
            } else if (lebar == 138) {
                x = 98;
            } else if (lebar == 139) {
                x = 99;
            } else if (lebar == 140) {
                x = 100;
            } else if (lebar == 141) {
                x = 101;
            } else if (lebar == 142) {
                x = 102;
            } else if (lebar == 143) {
                x = 103;
            } else if (lebar == 144) {
                x = 104;
            } else if (lebar == 145) {
                x = 105;
            } else if (lebar == 146) {
                x = 106;
            } else if (lebar == 147) {
                x = 107;
            } else if (lebar == 148) {
                x = 108;
            } else if (lebar == 149) {
                x = 109;
            } else if (lebar == 150) {
                x = 110;
            } else if (lebar == 151) {
                x = 111;
            } else if (lebar == 152) {
                x = 112;
            } else if (lebar == 153) {
                x = 113;
            } else if (lebar == 154) {
                x = 114;
            } else if (lebar == 155) {
                x = 115;
            } else if (lebar == 156) {
                x = 116;
            } else if (lebar == 157) {
                x = 117;
            } else if (lebar == 158) {
                x = 118;
            } else if (lebar == 159) {
                x = 119;
            } else if (lebar == 160) {
                x = 120;
            } else if (lebar == 161) {
                x = 121;
            } else if (lebar == 162) {
                x = 122;
            } else if (lebar == 163) {
                x = 123;
            } else if (lebar == 164) {
                x = 124;
            } else if (lebar == 165) {
                x = 125;
            } else if (lebar == 166) {
                x = 126;
            } else if (lebar == 167) {
                x = 127;
            } else if (lebar == 168) {
                x = 128;
            } else if (lebar == 169) {
                x = 129;
            } else if (lebar == 170) {
                x = 130;
            } else if (lebar == 171) {
                x = 131;
            } else if (lebar == 172) {
                x = 132;
            } else if (lebar == 173) {
                x = 133;
            } else if (lebar == 174) {
                x = 134;
            } else if (lebar == 175) {
                x = 135;
            } else if (lebar == 176) {
                x = 136;
            } else if (lebar == 177) {
                x = 137;
            } else if (lebar == 178) {
                x = 138;
            } else if (lebar == 179) {
                x = 139;
            } else if (lebar == 180) {
                x = 140;
            } else if (lebar == 181) {
                x = 141;
            } else if (lebar == 182) {
                x = 142;
            } else if (lebar == 183) {
                x = 143;
            } else if (lebar == 184) {
                x = 144;
            } else if (lebar == 185) {
                x = 145;
            } else if (lebar == 186) {
                x = 146;
            } else if (lebar == 187) {
                x = 147;
            } else if (lebar == 188) {
                x = 148;
            } else if (lebar == 189) {
                x = 149;
            } else if (lebar == 190) {
                x = 150;
            } else if (lebar == 191) {
                x = 151;
            } else if (lebar == 192) {
                x = 152;
            } else if (lebar == 193) {
                x = 153;
            } else if (lebar == 194) {
                x = 154;
            } else if (lebar == 195) {
                x = 155;
            } else if (lebar == 196) {
                x = 156;
            } else if (lebar == 197) {
                x = 157;
            } else if (lebar == 198) {
                x = 158;
            } else if (lebar == 199) {
                x = 159;
            } else if (lebar == 200) {
                x = 160;
            } else if (lebar == 201) {
                x = 161;
            } else if (lebar == 202) {
                x = 162;
            } else if (lebar == 203) {
                x = 163;
            } else if (lebar == 204) {
                x = 164;
            } else if (lebar == 205) {
                x = 165;
            } else if (lebar == 206) {
                x = 166;
            } else if (lebar == 207) {
                x = 167;
            } else if (lebar == 208) {
                x = 168;
            } else if (lebar == 209) {
                x = 169;
            } else if (lebar == 210) {
                x = 170;
            } else if (lebar == 211) {
                x = 171;
            } else if (lebar == 212) {
                x = 172;
            } else if (lebar == 213) {
                x = 173;
            } else if (lebar == 214) {
                x = 174;
            } else if (lebar == 215) {
                x = 175;
            } else if (lebar == 216) {
                x = 176;
            } else if (lebar == 217) {
                x = 177;
            } else if (lebar == 218) {
                x = 178;
            } else if (lebar == 219) {
                x = 179;
            } else if (lebar == 220) {
                x = 180;
            } else if (lebar == 221) {
                x = 181;
            } else if (lebar == 222) {
                x = 182;
            } else if (lebar == 223) {
                x = 183;
            } else if (lebar == 224) {
                x = 184;
            } else if (lebar == 225) {
                x = 185;
            } else if (lebar == 226) {
                x = 186;
            } else if (lebar == 227) {
                x = 187;
            } else if (lebar == 228) {
                x = 188;
            } else if (lebar == 229) {
                x = 189;
            } else if (lebar == 230) {
                x = 190;
            } else if (lebar == 231) {
                x = 191;
            } else if (lebar == 232) {
                x = 192;
            } else if (lebar == 233) {
                x = 193;
            } else if (lebar == 234) {
                x = 194;
            } else if (lebar == 235) {
                x = 195;
            } else if (lebar == 236) {
                x = 196;
            } else if (lebar == 237) {
                x = 197;
            } else if (lebar == 238) {
                x = 198;
            } else if (lebar == 239) {
                x = 199;
            } else if (lebar == 240) {
                x = 200;
            } else if (lebar == 241) {
                x = 201;
            } else if (lebar == 242) {
                x = 202;
            } else if (lebar == 243) {
                x = 203;
            } else if (lebar == 244) {
                x = 204;
            } else if (lebar == 245) {
                x = 205;
            } else if (lebar == 246) {
                x = 206;
            } else if (lebar == 247) {
                x = 207;
            } else if (lebar == 248) {
                x = 208;
            } else if (lebar == 249) {
                x = 209;
            } else if (lebar == 250) {
                x = 210;
            } else if (lebar == 251) {
                x = 211;
            } else if (lebar == 252) {
                x = 212;
            } else if (lebar == 253) {
                x = 213;
            } else if (lebar == 254) {
                x = 214;
            } else if (lebar == 255) {
                x = 215;
            } else if (lebar == 256) {
                x = 216;
            } else if (lebar == 257) {
                x = 217;
            } else if (lebar == 258) {
                x = 218;
            } else if (lebar == 259) {
                x = 219;
            } else if (lebar == 260) {
                x = 220;
            } else if (lebar == 261) {
                x = 221;
            } else if (lebar == 262) {
                x = 222;
            } else if (lebar == 263) {
                x = 223;
            } else if (lebar == 264) {
                x = 224;
            } else if (lebar == 265) {
                x = 225;
            } else if (lebar == 266) {
                x = 226;
            } else if (lebar == 267) {
                x = 227;
            } else if (lebar == 268) {
                x = 228;
            } else if (lebar == 269) {
                x = 229;
            } else if (lebar == 270) {
                x = 230;
            } else if (lebar == 271) {
                x = 231;
            } else if (lebar == 272) {
                x = 232;
            } else if (lebar == 273) {
                x = 233;
            } else if (lebar == 274) {
                x = 234;
            } else if (lebar == 275) {
                x = 235;
            } else if (lebar == 276) {
                x = 236;
            } else if (lebar == 277) {
                x = 237;
            } else if (lebar == 278) {
                x = 238;
            } else if (lebar == 279) {
                x = 239;
            } else if (lebar == 280) {
                x = 240;
            } else if (lebar == 281) {
                x = 241;
            } else if (lebar == 282) {
                x = 242;
            } else if (lebar == 283) {
                x = 243;
            } else if (lebar == 284) {
                x = 244;
            } else if (lebar == 285) {
                x = 245;
            } else if (lebar == 286) {
                x = 246;
            } else if (lebar == 287) {
                x = 247;
            } else if (lebar == 288) {
                x = 248;
            } else if (lebar == 289) {
                x = 249;
            } else if (lebar == 290) {
                x = 250;
            } else if (lebar == 291) {
                x = 251;
            } else if (lebar == 292) {
                x = 252;
            } else if (lebar == 293) {
                x = 253;
            } else if (lebar == 294) {
                x = 254;
            } else if (lebar == 295) {
                x = 255;
            } else if (lebar == 296) {
                x = 256;
            } else if (lebar == 297) {
                x = 257;
            } else if (lebar == 298) {
                x = 258;
            } else if (lebar == 299) {
                x = 259;
            } else if (lebar == 300) {
                x = 260;
            } else if (lebar == 301) {
                x = 261;
            } else if (lebar == 302) {
                x = 262;
            } else if (lebar == 303) {
                x = 263;
            } else if (lebar == 304) {
                x = 264;
            } else if (lebar == 305) {
                x = 265;
            } else if (lebar == 306) {
                x = 266;
            } else if (lebar == 307) {
                x = 267;
            } else if (lebar == 308) {
                x = 268;
            } else if (lebar == 309) {
                x = 269;
            } else if (lebar == 310) {
                x = 270;
            } else if (lebar == 311) {
                x = 271;
            } else if (lebar == 312) {
                x = 272;
            } else if (lebar == 313) {
                x = 273;
            } else if (lebar == 314) {
                x = 274;
            } else if (lebar == 315) {
                x = 275;
            } else if (lebar == 316) {
                x = 276;
            } else if (lebar == 317) {
                x = 277;
            } else if (lebar == 318) {
                x = 278;
            } else if (lebar == 319) {
                x = 279;
            } else if (lebar == 320) {
                x = 280;
            } else if (lebar == 321) {
                x = 281;
            } else if (lebar == 322) {
                x = 282;
            } else if (lebar == 323) {
                x = 283;
            } else if (lebar == 324) {
                x = 284;
            } else if (lebar == 325) {
                x = 285;
            } else if (lebar == 326) {
                x = 286;
            } else if (lebar == 327) {
                x = 287;
            } else if (lebar == 328) {
                x = 288;
            } else if (lebar == 329) {
                x = 289;
            } else if (lebar == 330) {
                x = 290;
            } else if (lebar == 331) {
                x = 291;
            } else if (lebar == 332) {
                x = 292;
            } else if (lebar == 333) {
                x = 293;
            } else if (lebar == 334) {
                x = 294;
            } else if (lebar == 335) {
                x = 295;
            } else if (lebar == 336) {
                x = 296;
            } else if (lebar == 337) {
                x = 297;
            } else if (lebar == 338) {
                x = 298;
            } else if (lebar == 339) {
                x = 299;
            } else if (lebar == 340) {
                x = 300;
            } else if (lebar == 341) {
                x = 301;
            } else if (lebar == 342) {
                x = 302;
            } else if (lebar == 343) {
                x = 303;
            } else if (lebar == 344) {
                x = 304;
            } else if (lebar == 345) {
                x = 305;
            } else if (lebar == 346) {
                x = 306;
            } else if (lebar == 347) {
                x = 307;
            } else if (lebar == 348) {
                x = 308;
            } else if (lebar == 349) {
                x = 309;
            } else if (lebar == 350) {
                x = 310;
            } else if (lebar == 351) {
                x = 311;
            } else if (lebar == 352) {
                x = 312;
            } else if (lebar == 353) {
                x = 313;
            } else if (lebar == 354) {
                x = 314;
            } else if (lebar == 355) {
                x = 315;
            } else if (lebar == 356) {
                x = 316;
            } else if (lebar == 357) {
                x = 317;
            } else if (lebar == 358) {
                x = 318;
            } else if (lebar == 359) {
                x = 319;
            } else if (lebar == 360) {
                x = 320;
            } else if (lebar == 361) {
                x = 321;
            } else if (lebar == 362) {
                x = 322;
            } else if (lebar == 363) {
                x = 323;
            } else if (lebar == 364) {
                x = 324;
            } else if (lebar == 365) {
                x = 325;
            } else if (lebar == 366) {
                x = 326;
            } else if (lebar == 367) {
                x = 327;
            } else if (lebar == 368) {
                x = 328;
            } else if (lebar == 369) {
                x = 329;
            } else if (lebar == 370) {
                x = 330;
            } else if (lebar == 371) {
                x = 331;
            } else if (lebar == 372) {
                x = 332;
            } else if (lebar == 373) {
                x = 333;
            } else if (lebar == 374) {
                x = 334;
            } else if (lebar == 375) {
                x = 335;
            } else if (lebar == 376) {
                x = 336;
            } else if (lebar == 377) {
                x = 337;
            } else if (lebar == 378) {
                x = 338;
            } else if (lebar == 379) {
                x = 339;
            } else if (lebar == 380) {
                x = 340;
            } else if (lebar == 381) {
                x = 341;
            } else if (lebar == 382) {
                x = 342;
            } else if (lebar == 383) {
                x = 343;
            } else if (lebar == 384) {
                x = 344;
            } else if (lebar == 385) {
                x = 345;
            } else if (lebar == 386) {
                x = 346;
            } else if (lebar == 387) {
                x = 347;
            } else if (lebar == 388) {
                x = 348;
            } else if (lebar == 389) {
                x = 349;
            } else if (lebar == 390) {
                x = 350;
            } else if (lebar == 391) {
                x = 351;
            } else if (lebar == 392) {
                x = 352;
            } else if (lebar == 393) {
                x = 353;
            } else if (lebar == 394) {
                x = 354;
            } else if (lebar == 395) {
                x = 355;
            } else if (lebar == 396) {
                x = 356;
            } else if (lebar == 397) {
                x = 357;
            } else if (lebar == 398) {
                x = 358;
            } else if (lebar == 399) {
                x = 359;
            } else if (lebar == 400) {
                x = 360;
            } else if (lebar == 401) {
                x = 361;
            } else if (lebar == 402) {
                x = 362;
            } else if (lebar == 403) {
                x = 363;
            } else if (lebar == 404) {
                x = 364;
            } else if (lebar == 405) {
                x = 365;
            } else if (lebar == 406) {
                x = 366;
            } else if (lebar == 407) {
                x = 367;
            } else if (lebar == 408) {
                x = 368;
            } else if (lebar == 409) {
                x = 369;
            } else if (lebar == 410) {
                x = 370;
            } else if (lebar == 411) {
                x = 371;
            } else if (lebar == 412) {
                x = 372;
            } else if (lebar == 413) {
                x = 373;
            } else if (lebar == 414) {
                x = 374;
            } else if (lebar == 415) {
                x = 375;
            } else if (lebar == 416) {
                x = 376;
            } else if (lebar == 417) {
                x = 377;
            } else if (lebar == 418) {
                x = 378;
            } else if (lebar == 419) {
                x = 379;
            } else if (lebar == 420) {
                x = 380;
            } else if (lebar == 421) {
                x = 381;
            } else if (lebar == 422) {
                x = 382;
            } else if (lebar == 423) {
                x = 383;
            } else if (lebar == 424) {
                x = 384;
            } else if (lebar == 425) {
                x = 385;
            } else if (lebar == 426) {
                x = 386;
            } else if (lebar == 427) {
                x = 387;
            } else if (lebar == 428) {
                x = 388;
            } else if (lebar == 429) {
                x = 389;
            } else if (lebar == 430) {
                x = 390;
            } else if (lebar == 431) {
                x = 391;
            } else if (lebar == 432) {
                x = 392;
            } else if (lebar == 433) {
                x = 393;
            } else if (lebar == 434) {
                x = 394;
            } else if (lebar == 435) {
                x = 395;
            } else if (lebar == 436) {
                x = 396;
            } else if (lebar == 437) {
                x = 397;
            } else if (lebar == 438) {
                x = 398;
            } else if (lebar == 439) {
                x = 399;
            } else if (lebar == 440) {
                x = 400;
            } else if (lebar == 441) {
                x = 401;
            } else if (lebar == 442) {
                x = 402;
            } else if (lebar == 443) {
                x = 403;
            } else if (lebar == 444) {
                x = 404;
            } else if (lebar == 445) {
                x = 405;
            } else if (lebar == 446) {
                x = 406;
            } else if (lebar == 447) {
                x = 407;
            } else if (lebar == 448) {
                x = 408;
            } else if (lebar == 449) {
                x = 409;
            } else if (lebar == 450) {
                x = 410;
            } else if (lebar == 451) {
                x = 411;
            } else if (lebar == 452) {
                x = 412;
            } else if (lebar == 453) {
                x = 413;
            } else if (lebar == 454) {
                x = 414;
            } else if (lebar == 455) {
                x = 415;
            } else if (lebar == 456) {
                x = 416;
            } else if (lebar == 457) {
                x = 417;
            } else if (lebar == 458) {
                x = 418;
            } else if (lebar == 459) {
                x = 419;
            } else if (lebar == 460) {
                x = 420;
            } 			else if (lebar == 461) {
                x = 421;
            } else if (lebar == 462) {
                x = 422;
            } else if (lebar == 463) {
                x = 423;
            } else if (lebar == 464) {
                x = 424;
            } else if (lebar == 465) {
                x = 425;
            } else if (lebar == 466) {
                x = 426;
            } else if (lebar == 467) {
                x = 427;
            } else if (lebar == 468) {
                x = 428;
            } else if (lebar == 469) {
                x = 429;
            } else if (lebar == 470) {
                x = 430;
            } else if (lebar == 471) {
                x = 431;
            } else if (lebar == 472) {
                x = 432;
            } else if (lebar == 473) {
                x = 433;
            } else if (lebar == 474) {
                x = 434;
            } else if (lebar == 475) {
                x = 435;
            } else if (lebar == 476) {
                x = 436;
            } else if (lebar == 477) {
                x = 437;
            } else if (lebar == 478) {
                x = 438;
            } else if (lebar == 479) {
                x = 439;
            } else if (lebar == 480) {
                x = 440;
            } else if (lebar == 481) {
                x = 441;
            } else if (lebar == 482) {
                x = 442;
            } else if (lebar == 483) {
                x = 443;
            } else if (lebar == 484) {
                x = 444;
            } else if (lebar == 485) {
                x = 445;
            } else if (lebar == 486) {
                x = 446;
            } else if (lebar == 487) {
                x = 447;
            } else if (lebar == 488) {
                x = 448;
            } else if (lebar == 489) {
                x = 449;
            } else {
                x = 450;
            }
            double luas = Math.round((p3*phi*175*175)*kons);
            double luas2 = Math.round((phi*175*175)*x);
            double luas3 = Math.round(luas + luas2);
            NumberFormat format = new DecimalFormat("0.#");
            edtLuas.setText(NumberFormat.getNumberInstance(Locale.US).format(luas3)+"");
            double beratkg = Math.round(luas3 * SG);
            edtBeratKg.setText(NumberFormat.getNumberInstance(Locale.US).format(beratkg)+"");
        } else {
            double phi = Double.parseDouble("3.14".toString());
            double p3 = Double.parseDouble("0.33".toString());
            double seperempat = Double.parseDouble("0.25".toString());
            double SG = Double.parseDouble("1.193".toString());

            double luas = Math.round((p3*phi*175*175)*lebar);
            NumberFormat format = new DecimalFormat("0.#");
            edtLuas.setText(NumberFormat.getNumberInstance(Locale.US).format(luas)+"");
            double beratkg = Math.round(luas * SG);
            edtBeratKg.setText(NumberFormat.getNumberInstance(Locale.US).format(beratkg)+"");
        }

    }



*/

    //Dibawah ini merupakan perintah untuk Menambahkan  (CREATE)
    private void addEmployee()
    {
        String ctank = "UF7";

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
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
}
