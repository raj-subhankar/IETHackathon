package com.yellowsoft.periwinkle;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by subhankar on 11/5/2016.
 */

public class ProfileActivity extends AppCompatActivity {

    ArrayList<Post> posts = new ArrayList<Post>();
    private FeedAdapter mAdapter;
    private RecyclerView recyclerView;
    private SessionManager session;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

//        recyclerView = (RecyclerView) findViewById(R.id.recycler);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(linearLayoutManager);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tlUserProfileTabs);
        tabLayout.setupWithViewPager(viewPager);

        setupTabIcons();

        session = new SessionManager(getApplicationContext());
        String id = session.getId();
        String profilePic = session.getImageUrl();
        String name = session.getName();
        String email = session.getEmail();

        ImageView userProfilePhoto = (ImageView) findViewById(R.id.ivUserProfilePhoto);
        Picasso.with(this).load(profilePic).into(userProfilePhoto);
        Log.d("url",profilePic);
        TextView userName = (TextView) findViewById(R.id.tvUserName);
        userName.setText(name);
        TextView userEmail = (TextView) findViewById(R.id.tvUserEmail);
        userEmail.setText(email);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Launch upload activity
                Intent intent = new Intent(ProfileActivity.this,
                        CreateCategory.class);
                startActivity(intent);
                finish();
            }
        });

//        ApiInterface apiService =
//                ApiClient.getClient().create(ApiInterface.class);
//
//        Call<List<Post>> call = apiService.getUserPosts(id);
//        call.enqueue(new Callback<List<Post>>() {
//            @Override
//            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
//
//                if (!response.body().isEmpty()) {
//                    posts.removeAll(posts);
//                    posts.addAll(response.body());
//                    mAdapter = new FeedAdapter(getApplicationContext(), posts);
//                    mAdapter.notifyDataSetChanged();
//                    recyclerView.setAdapter(mAdapter);
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Post>> call, Throwable t) {
//                // Log error here since request failed
////                Log.e(TAG, t.toString());
//            }
//        });
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_grid_on_white);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_list_white);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_place_white);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_label_white);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CategoryFragment(), "NEW");
        adapter.addFragment(new TestFragment(), "TOP");
        adapter.addFragment(new TestFragment(), "NEW");
        adapter.addFragment(new TestFragment(), "TOP");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }
    }
}
