package com.atmaluhur.mobisuts_2411500025;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView rvPengumuman;
    private PengumumanAdapter adapter;
    private List<Pengumuman> listPengumuman;
    private SwipeRefreshLayout swipeRefresh;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvPengumuman = view.findViewById(R.id.rv_pengumuman);
        swipeRefresh = view.findViewById(R.id.swipe_refresh);

        // Inisialisasi Data Dummy
        prepareData();

        // Setup RecyclerView
        rvPengumuman.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PengumumanAdapter(listPengumuman);
        rvPengumuman.setAdapter(adapter);

        // Swipe Refresh Logic
        swipeRefresh.setOnRefreshListener(() -> {
            // Simulasi refresh data
            prepareData();
            adapter.notifyDataSetChanged();
            swipeRefresh.setRefreshing(false);
        });
    }

    private void prepareData() {
        listPengumuman = new ArrayList<>();
        listPengumuman.add(new Pengumuman(
                "Pendaftaran Mahasiswa Baru 2024",
                "25 Oktober 2023",
                "Penerimaan Mahasiswa Baru Gelombang 1 telah dibuka. Segera daftarkan diri Anda dan raih beasiswa pendidikan.",
                R.drawable.logo_aplikasi
        ));
        listPengumuman.add(new Pengumuman(
                "Jadwal Ujian Tengah Semester",
                "20 Oktober 2023",
                "Diberitahukan kepada seluruh mahasiswa bahwa UTS akan dilaksanakan mulai tanggal 6 November 2023. Harap cek jadwal masing-masing.",
                R.drawable.logo_aplikasi
        ));
        listPengumuman.add(new Pengumuman(
                "Workshop Mobile Development",
                "15 Oktober 2023",
                "Ikuti workshop Android Development menggunakan Java dan Android Studio. Kuota terbatas hanya untuk 50 peserta.",
                R.drawable.logo_aplikasi
        ));
        listPengumuman.add(new Pengumuman(
                "Seminar Karir & Teknologi",
                "10 Oktober 2023",
                "Seminar mengenai perkembangan teknologi AI dan bagaimana mempersiapkan karir di industri teknologi masa depan.",
                R.drawable.logo_aplikasi
        ));
        listPengumuman.add(new Pengumuman(
                "Lomba Desain UI/UX",
                "05 Oktober 2023",
                "Tunjukkan kreativitasmu dalam merancang antarmuka aplikasi yang ramah pengguna. Menangkan hadiah total jutaan rupiah.",
                R.drawable.logo_aplikasi
        ));
        listPengumuman.add(new Pengumuman(
                "Pengumuman Libur Nasional",
                "01 Oktober 2023",
                "Sehubungan dengan hari besar nasional, perkuliahan akan diliburkan. Informasi lengkap jadwal pengganti akan segera diupdate.",
                R.drawable.logo_aplikasi
        ));
    }
}
