    package com.example.androidfirebasecomicreader;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.androidfirebasecomicreader.Adapter.MyViewPagerAdapter;
import com.example.androidfirebasecomicreader.Model.Chapter;
import com.wajahatkarim3.easyflipviewpager.BookFlipPageTransformer;

    public class ViewComic extends AppCompatActivity {

    ViewPager viewPager;
    TextView txt_chapter_name;
    View back,next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_comic);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        txt_chapter_name = (TextView) findViewById(R.id.txt_chapter_name);
        back = findViewById(R.id.chapter_back);
        next = findViewById(R.id.chapter_next);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Common.chapterIndex == 0)
                    Toast.makeText(ViewComic.this,"You are reading first chapter",Toast.LENGTH_SHORT).show();
                else {
                    Common.chapterIndex--;
                    fetchLinks(Common.chapterList.get(Common.chapterIndex));
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Common.chapterIndex == Common.chapterList.size()-1)
                    Toast.makeText(ViewComic.this,"You are reading last chapter",Toast.LENGTH_SHORT).show();
                else {
                    Common.chapterIndex++;
                    fetchLinks(Common.chapterList.get(Common.chapterIndex));
                }
            }
        });
        fetchLinks(Common.chapterSelected);
    }

        private void fetchLinks(Chapter chapter) {
            if(chapter.Links != null) {
                if(chapter.Links.size() > 0) {
                    MyViewPagerAdapter adapter = new MyViewPagerAdapter(getBaseContext(),chapter.Links);
                    viewPager.setAdapter(adapter);

                    txt_chapter_name.setText(Common.formatString(Common.chapterSelected.Name));

                    //Animation
                    BookFlipPageTransformer bookFlipPageTransformer = new BookFlipPageTransformer();
                    bookFlipPageTransformer.setScaleAmountPercent(10f);
                    viewPager.setPageTransformer(true,bookFlipPageTransformer);
                }
                else {
                    Toast.makeText(this,"Image unavailable", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                Toast.makeText(this,"This Chapter is not available yet...",Toast.LENGTH_SHORT).show();
            }
        }
    }