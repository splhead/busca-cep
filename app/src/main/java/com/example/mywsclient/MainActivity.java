package com.example.mywsclient;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mywsclient.databinding.ActivityMainBinding;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnSearchCEP.setOnClickListener(view1 -> {
            String cep = binding.etCEP.getText().toString();

            if (!cep.equals("") && cep.length() == 8) {
                HttpService service = new HttpService(cep);
                try {
                    CEP response = service.execute().get();
                    binding.tvResult.setText(response.toString());
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}