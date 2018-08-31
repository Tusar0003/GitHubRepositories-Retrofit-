package com.example.no0ne.githubrepositories.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.no0ne.githubrepositories.R;
import com.example.no0ne.githubrepositories.api.model.GitHubRepo;
import com.example.no0ne.githubrepositories.api.service.GitHubClient;
import com.example.no0ne.githubrepositories.ui.adapter.GitHubRepoAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.pagination_list);

        // Creating retrofit instance
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        // Get client & call object for the request
        GitHubClient client = retrofit.create(GitHubClient.class);
        Call<List<GitHubRepo>> call = client.repositoriesForUser("fs-opensource");

        // We can execute it synchronously and asynchronously
        // Here we are executing it asynchronously since we are in an activity
        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                // body() is the list of all repositories
                List<GitHubRepo> repositories = response.body();

                listView.setAdapter(new GitHubRepoAdapter(MainActivity.this, repositories));
            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
