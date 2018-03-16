package com.example.ramapradana.tugasinput;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    List<Mahasiswa> listMahasiswa = new ArrayList<>();
    private static final String ARRAYLIST_MHS_EXTRA = "ARRAY_MHS";
    RecyclerView rvMhs;
    MahasiswaAdapter mhsAdapter;
    TextView mMhsKosong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        rvMhs = findViewById(R.id.rv_list_mahasiswa);
        mMhsKosong = findViewById(R.id.tv_mhs_kosong);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Daftar Mahasiswa");

        listMahasiswa = (ArrayList<Mahasiswa>)getIntent().getSerializableExtra(ARRAYLIST_MHS_EXTRA);

        if (listMahasiswa.size() == 0){
            mMhsKosong.setText("Belum ada mahasiswa yang dimasukkan!");
        }

        mhsAdapter = new MahasiswaAdapter();
        mhsAdapter.setData(listMahasiswa);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        rvMhs.setLayoutManager(linearLayoutManager);
        rvMhs.setAdapter(mhsAdapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home){
            Intent intent = new Intent(DetailActivity.this, MainActivity.class);
            intent.putExtra(ARRAYLIST_MHS_EXTRA, (Serializable) listMahasiswa);
            finish();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(DetailActivity.this, MainActivity.class);
        intent.putExtra(ARRAYLIST_MHS_EXTRA, (Serializable) listMahasiswa);
        finish();
    }
}
