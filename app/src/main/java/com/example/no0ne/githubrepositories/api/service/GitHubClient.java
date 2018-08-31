package com.example.no0ne.githubrepositories.api.service;

import com.example.no0ne.githubrepositories.api.model.GitHubRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by no0ne on 1/14/18.
 */

public interface GitHubClient {

    // Define request type
    @GET("users/{user}/repos")
    Call<List<GitHubRepo>> repositoriesForUser(@Path("user") String user);
}
