package com.yellowsoft.periwinkle;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by subhankar on 11/6/2016.
 */

public class CategoryDetailActivity extends AppCompatActivity{
    ArrayList<Post> posts = new ArrayList<Post>();
    private FeedAdapter mAdapter;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_category_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        String thumbnail = getIntent().getStringExtra("thumbnail");
        final String categoryid = getIntent().getStringExtra("categoryid");
        String title = getIntent().getStringExtra("title");

        if (toolbar != null) {
            toolbar.setTitle(title);
            setSupportActionBar(toolbar);
        }

        ImageView image = (ImageView) findViewById(R.id.image);
        Picasso.with(this).load(thumbnail).into(image);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<List<Post>> call = apiService.getPostsByCategories(categoryid);
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if (!response.body().isEmpty()) {
                    posts.removeAll(posts);
                    posts.addAll(response.body());
                    mAdapter = new FeedAdapter(getApplicationContext(), posts);
                    mAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(mAdapter);

                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                // Log error here since request failed
//                Log.e(TAG, t.toString());
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Launch upload activity
                Intent intent = new Intent(CategoryDetailActivity.this,
                        UploadActivity.class);
                intent.putExtra("categoryid", categoryid);
                startActivity(intent);
                finish();
            }
        });
    }

}
