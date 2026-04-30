package com.atmaluhur.mobisuts_2411500025;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PengumumanAdapter extends RecyclerView.Adapter<PengumumanAdapter.ViewHolder> {

    private List<Pengumuman> listPengumuman;

    public PengumumanAdapter(List<Pengumuman> listPengumuman) {
        this.listPengumuman = listPengumuman;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pengumuman, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pengumuman item = listPengumuman.get(position);
        holder.tvJudul.setText(item.getJudul());
        holder.tvTanggal.setText(item.getTanggal());
        holder.tvDeskripsi.setText(item.getDeskripsi());
        holder.imgPengumuman.setImageResource(item.getGambar());
    }

    @Override
    public int getItemCount() {
        return listPengumuman.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPengumuman;
        TextView tvJudul, tvTanggal, tvDeskripsi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPengumuman = itemView.findViewById(R.id.img_pengumuman);
            tvJudul = itemView.findViewById(R.id.tv_judul);
            tvTanggal = itemView.findViewById(R.id.tv_tanggal);
            tvDeskripsi = itemView.findViewById(R.id.tv_deskripsi);
        }
    }
}
