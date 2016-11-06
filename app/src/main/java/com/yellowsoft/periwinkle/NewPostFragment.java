package com.yellowsoft.periwinkle;

/**
 * Created by subhankar on 11/5/2016.
 */
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewPostFragment extends Fragment {
    private SessionManager session;
    ArrayList<Post> posts = new ArrayList<Post>();
    private RecyclerView recyclerView;
    private FeedAdapter mAdapter;
    SwipeRefreshLayout mSwipeRefreshLayout;

    private static final String TAG = NewPostFragment.class.getSimpleName();

    public NewPostFragment() {
    }

    //public FeedActivity feedActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        session = new SessionManager(getContext());

        //feedActivity = (FeedActivity) getActivity();
        loadDataFromApi();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                loadDataFromApi();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                int curSize = mAdapter.getItemCount();
//                customLoadMoreDataFromApi(posts.get(curSize - 1).getId().toString());
            }
        });


        return view;
    }

    public void loadDataFromApi() {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<List<Post>> call = apiService.getNewposts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if (!response.body().isEmpty()) {
                    posts.removeAll(posts);
                    posts.addAll(response.body());
                    mAdapter = new FeedAdapter(getContext(), posts);
                    mAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(mAdapter);

                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });

    }

    public void customLoadMoreDataFromApi(String lastid) {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<List<Post>> call = apiService.getMoreNewposts(lastid);
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if (!response.body().isEmpty()) {

//                    for (int i = 0; i < response.body().size(); i++) {
//                        Post post = response.body().get(i);
//                        posts.add(post);
//                    }
                    posts.addAll(response.body());

                    int currSize = mAdapter.getItemCount();
//                Post post = response.body().get(0).getPostBody();
                    //mAdapter = new FeedAdapter(getContext(), posts);
                    //recyclerView.setAdapter(mAdapter);
                    mAdapter.notifyItemRangeInserted(currSize, posts.size()-1);
//                    mAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }
}