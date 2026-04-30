package com.atmaluhur.mobisuts_2411500025;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.atmaluhur.mobisuts_2411500025.api.RetrofitClient;
import com.atmaluhur.mobisuts_2411500025.model.PengumumanResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private RecyclerView rvPengumuman;
    private PengumumanAdapter adapter;
    private List<PengumumanResponse> listPengumuman = new ArrayList<>();
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

        rvPengumuman.setLayoutManager(new LinearLayoutManager(getContext()));

        loadDataPengumuman();

        swipeRefresh.setOnRefreshListener(() -> {
            loadDataPengumuman();
        });
    }

    private void loadDataPengumuman() {
        swipeRefresh.setRefreshing(true);

        RetrofitClient.getInstance().getPengumuman().enqueue(new Callback<List<PengumumanResponse>>() {
            @Override
            public void onResponse(Call<List<PengumumanResponse>> call, Response<List<PengumumanResponse>> response) {
                swipeRefresh.setRefreshing(false);

                if (response.isSuccessful() && response.body() != null) {
                    listPengumuman = response.body();

                    adapter = new PengumumanAdapter(getContext(), listPengumuman);
                    rvPengumuman.setAdapter(adapter);
                } else {
                    Toast.makeText(getContext(), "Gagal mengambil data dari server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<PengumumanResponse>> call, Throwable t) {
                swipeRefresh.setRefreshing(false);
                Toast.makeText(getContext(), "Error Koneksi: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}