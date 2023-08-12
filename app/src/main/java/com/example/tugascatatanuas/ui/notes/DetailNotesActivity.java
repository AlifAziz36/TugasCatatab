package com.example.tugascatatanuas.ui.notes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.tugascatatanuas.data.token;
import com.example.tugascatatanuas.databinding.ActivityDetailNotesBinding;
import com.example.tugascatatanuas.model.Note;
import com.example.tugascatatanuas.ui.MainActivity;
import com.example.tugascatatanuas.ui.adapter.NotesAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DetailNotesActivity extends AppCompatActivity {
    private String category;
    private NotesAdapter notesView;
    private DatabaseReference DB;
    private FirebaseAuth Auth;
    private ArrayList<Note> list_note;

    private ActivityDetailNotesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();

        Auth = FirebaseAuth.getInstance();

        // Set category
        this.category = intent.getStringExtra("category");

        // Set title for this activity
        this.setTitle("Note " + this.category);

        // Move to previous activity
        binding.noteDetailBackBtn.setOnClickListener(v -> {
            Intent moveToMain = new Intent(this, MainActivity.class);
            moveToMain.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(moveToMain);
            finish();
        });

        // Add button on click
        binding.noteDetailAddBtn.setOnClickListener(v -> {
            Intent moveToAdd = new Intent(this, AddNotesActivity.class);
            moveToAdd.putExtra("backTo", "note_detail");
            moveToAdd.putExtra("category", this.category);
            moveToAdd.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(moveToAdd);
            finish();
        });

        // Get list category
        DB = FirebaseDatabase.getInstance(token.getDB_URL()).getReference("notes/" + Auth.getUid() + "/" + this.category);
        DB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list_note = new ArrayList<>();

                for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    Note note = snapshot.getValue(Note.class);
                    list_note.add(note);
                }

                // Set recyclerView
                notesView = new NotesAdapter(DetailNotesActivity.this, list_note, category);
                binding.recycleNoteDetail.setAdapter(notesView);
                binding.recycleNoteDetail.setLayoutManager(new LinearLayoutManager(DetailNotesActivity.this));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Error", databaseError.getMessage());
            }
        });
    }
}
