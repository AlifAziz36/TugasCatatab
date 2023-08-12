package com.example.tugascatatanuas.ui.notes;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tugascatatanuas.data.helper.DBHelper;
import com.example.tugascatatanuas.data.token;
import com.example.tugascatatanuas.databinding.ActivityUpdateNotesBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateNotesActivity extends AppCompatActivity{
    private DatabaseReference DB;
    private ActivityUpdateNotesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DB = FirebaseDatabase.getInstance(token.getDB_URL()).getReference();
        Intent i = getIntent();

        // Set value
        binding.uptNoteTitle.setText(i.getStringExtra("title"));
        binding.uptNoteCategory.setText(i.getStringExtra("category"));
        binding.uptNoteDescription.setText(i.getStringExtra("description"));

        // Disable editing of category
        binding.uptNoteCategory.setEnabled(false);

        // Save button on click
        binding.uptNoteSaveBtn.setOnClickListener(v -> {
            // Validasi judul tidak boleh kosong
            String updatedTitle = binding.uptNoteTitle.getText().toString();
            if (TextUtils.isEmpty(updatedTitle)) {
                binding.uptNoteTitle.setError("Judul tidak boleh kosong");
                return;
            }

            // Menghapus catatan lama dan menyimpan yang baru
            DBHelper.deleteNote(DB,
                    i.getStringExtra("userId"),
                    i.getStringExtra("category"),
                    i.getStringExtra("title")
            );
            DBHelper.saveNotes(DB,
                    i.getStringExtra("userId"),
                    updatedTitle,
                    binding.uptNoteCategory.getText().toString(),
                    binding.uptNoteDescription.getText().toString()
            );

            // Menampilkan pesan berhasil diubah
            Toast.makeText(this, "Data berhasil diubah !",
                    Toast.LENGTH_SHORT).show();

            goToNoteDetail(i.getStringExtra("category"));
        });

        // Back button on click
        binding.uptNoteBackBtn.setOnClickListener(v -> {
            goToNoteDetail(i.getStringExtra("category"));
        });
    }

    public void goToNoteDetail(String category) {
        Intent intent = new Intent(this, DetailNotesActivity.class);
        intent.putExtra("category", category);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
