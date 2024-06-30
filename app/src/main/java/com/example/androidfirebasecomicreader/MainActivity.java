package com.example.androidfirebasecomicreader;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfirebasecomicreader.Adapter.MyComicAdapter;
import com.example.androidfirebasecomicreader.Interface.IComicLoadDone;
import com.example.androidfirebasecomicreader.Model.Comic;
import com.example.androidfirebasecomicreader.Common.Common;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recycler_comic;
    TextView txt_comic;

    //Database
    DatabaseReference comics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //Init Database
        comics = FirebaseDatabase.getInstance().getReference("Comic");

        loadComic();

        recycler_comic = (RecyclerView) findViewById(R.id.recycler_comic);
        recycler_comic.setHasFixedSize(true);
        recycler_comic.setLayoutManager(new GridLayoutManager(this,2));

        txt_comic = (TextView) findViewById(R.id.txt_comic);

    }

    private void loadComic() {

        comics.addListenerForSingleValueEvent(new ValueEventListener() {
            List<Comic> comic_load = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot comicSnapShot:snapshot.getChildren()) {
                    Comic comic = comicSnapShot.getValue(Comic.class);
                    comic_load.add(comic);
                }
                onComicLoadDoneListener(comic_load);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void onComicLoadDoneListener(List<Comic> comicList) {
        Common.comicList = comicList;

        recycler_comic.setAdapter(new MyComicAdapter(getBaseContext(),comicList));

        txt_comic.setText(new StringBuilder("NEW COMIC (")
        .append(comicList.size())
        .append(")"));


    }
}