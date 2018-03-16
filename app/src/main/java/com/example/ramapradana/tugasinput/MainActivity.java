package com.example.ramapradana.tugasinput;

import android.content.Intent;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    List<Mahasiswa> listMahasiswa = new ArrayList<>();
    Button btnSimpan;
    RadioGroup mRadioJenisKelamin;
    EditText etNim;
    EditText etNama;
    EditText etAlamat;
    EditText etTelepon;
    String jenisKelamin = "";


    private String spinerAgama;
    private static final String ARRAYLIST_MHS_EXTRA = "ARRAY_MHS";
    private static final String SAVE_MHS = "MHS_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnSimpan = findViewById(R.id.btn_save_mhs);
        etNim = findViewById(R.id.et_nim_mhs);
        etNama = findViewById(R.id.et_nama_mahasiswa);
        etAlamat = findViewById(R.id.et_alamat_mhs);
        etTelepon = findViewById(R.id.et_hp_mhs);
        mRadioJenisKelamin = findViewById(R.id.rg_jenis_kelamin);

        Spinner spinner = findViewById(R.id.spin_agama);
        if (spinner != null){
            spinner.setOnItemSelectedListener(this);
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.labels_arrays_agama, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (spinner != null){
            spinner.setAdapter(adapter);
        }

        if ((ArrayList<Mahasiswa>)getIntent().getSerializableExtra(ARRAYLIST_MHS_EXTRA) != null){
            listMahasiswa = (ArrayList<Mahasiswa>)getIntent().getSerializableExtra(ARRAYLIST_MHS_EXTRA);
        }


        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nim = etNim.getText().toString();
                String nama = etNama.getText().toString();
                String alamat = etAlamat.getText().toString();
                String telepon = etTelepon.getText().toString();

                if (cekInput(nim) || cekInput(nama) || cekInput(spinerAgama) || cekInput(telepon) || cekInput(jenisKelamin) || cekInput(alamat)){
                    Toast.makeText(getApplicationContext(), "semua kolom isian harus diisi terlebih dahulu!", Toast.LENGTH_SHORT).show();
                    etAlamat.setText(alamat);
                    etNama.setText(nama);
                    etNim.setText(nim);
                    etTelepon.setText(telepon);
                }
                else{
                    Mahasiswa mhs = new Mahasiswa();
                    mhs.setAgama(spinerAgama);
                    mhs.setAlamat(alamat);
                    mhs.setJenis_kelamin(jenisKelamin);
                    mhs.setNama(nama);
                    mhs.setNim(nim);
                    mhs.setTelepon(telepon);

                    listMahasiswa.add(mhs);

                    etAlamat.setText("");
                    etNama.setText("");
                    etNim.setText("");
                    etTelepon.setText("");
                    mRadioJenisKelamin.clearCheck();

                    Snackbar.make(view, "Berhasil memasukkan " + nama + " ke datastore", Snackbar.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_detail:
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(ARRAYLIST_MHS_EXTRA, (Serializable) listMahasiswa);
                startActivity(intent);
                break;

        }
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        spinerAgama = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void addDataListMahasiswa(Mahasiswa mahasiswa){
        listMahasiswa.add(mahasiswa);
    }

    public void onRadioClickListener(View view){
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.rb_laki_laki:
                if (checked){
                    jenisKelamin = "Laki-Laki";
                }
                break;

            case R.id.rb_perempuan:
                if (checked){
                    jenisKelamin = "Perempuan";
                }
                break;

                default:
                    jenisKelamin = "";
                    break;
        }
    }

    public boolean cekInput(String input){
        if (input.equals("")){
            return true;
        }
        return false;
    }
}
