package com.atmaluhur.mobisuts_2411500025;

import android.content.Context;
import android.content.Intent; // Tambahkan import ini
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.atmaluhur.mobisuts_2411500025.model.PengumumanResponse;
import com.bumptech.glide.Glide;

import java.util.List;

public class PengumumanAdapter extends RecyclerView.Adapter<PengumumanAdapter.ViewHolder> {

    private List<PengumumanResponse> listPengumuman;
    private Context context;

    public PengumumanAdapter(Context context, List<PengumumanResponse> listPengumuman) {
        this.context = context;
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
        PengumumanResponse item = listPengumuman.get(position);

        holder.tvJudul.setText(item.getJudul());
        holder.tvTanggal.setText(item.getTanggal());
        holder.tvDeskripsi.setText(item.getDeskripsi());

        Glide.with(context)
                .load(item.getFoto())
                .placeholder(R.drawable.logo_aplikasi)
                .error(R.drawable.logo_aplikasi)
                .into(holder.imgPengumuman);

        // --- TAMBAHKAN LOGIKA KLIK DI SINI ---
        holder.btnSelengkapnya.setOnClickListener(v -> {
            Intent intent = new Intent(context, Pengumuman.class);
            intent.putExtra("JUDUL", item.getJudul());
            intent.putExtra("TANGGAL", item.getTanggal());
            intent.putExtra("DESKRIPSI", item.getDeskripsi());
            intent.putExtra("FOTO", item.getFoto());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listPengumuman != null ? listPengumuman.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPengumuman;
        TextView tvJudul, tvTanggal, tvDeskripsi;
        TextView btnSelengkapnya; // Deklarasikan tombolnya

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPengumuman = itemView.findViewById(R.id.img_pengumuman);
            tvJudul = itemView.findViewById(R.id.tv_judul);
            tvTanggal = itemView.findViewById(R.id.tv_tanggal);
            tvDeskripsi = itemView.findViewById(R.id.tv_deskripsi);

            // Inisialisasi ID tombol (Pastikan ID di XML adalah btn_selengkapnya)
            btnSelengkapnya = itemView.findViewById(R.id.btn_selengkapnya);
        }
    }
}