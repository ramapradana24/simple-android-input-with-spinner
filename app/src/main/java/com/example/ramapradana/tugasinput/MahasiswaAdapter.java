package com.example.ramapradana.tugasinput;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rama Pradana on 3/16/2018.
 */

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.ViewHolder>{

    List<Mahasiswa> dataset = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_mahasiswa, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Mahasiswa mhs = dataset.get(position);

        holder.mNim.setText(mhs.getNim());
        holder.mNama.setText(mhs.getNama());
        holder.mAlamat.setText(mhs.getAlamat());
        holder.mTelepon.setText(mhs.getTelepon());
        holder.mAgama.setText(mhs.getAgama());
        holder.mJenisKelamin.setText(mhs.getJenis_kelamin());

        if (mhs.getJenis_kelamin().equals("Perempuan")){
            holder.mAvatar.setImageResource(R.drawable.teacher);
        }
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void setData(List<Mahasiswa> mahasiswaListItem){
        this.dataset = mahasiswaListItem;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView mNim;
        TextView mNama;
        TextView mAlamat;
        TextView mJenisKelamin;
        TextView mTelepon;
        TextView mAgama;
        ImageView mAvatar;

        public ViewHolder(View itemView) {
            super(itemView);

            mNim = itemView.findViewById(R.id.tv_nim);
            mNama = itemView.findViewById(R.id.tv_nama);
            mAgama = itemView.findViewById(R.id.tv_agama);
            mJenisKelamin = itemView.findViewById(R.id.tv_jenis_kelamin);
            mTelepon = itemView.findViewById(R.id.tv_telepon);
            mAlamat = itemView.findViewById(R.id.tv_alamat);
            mAvatar = itemView.findViewById(R.id.img_mhs);
        }
    }
}
