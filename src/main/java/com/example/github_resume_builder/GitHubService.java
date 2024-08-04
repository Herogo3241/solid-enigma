package com.example.github_resume_builder;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GitHubService {

    private final RestTemplate restTemplate;
    private final String GITHUB_API_URL = "https://api.github.com";

    public GitHubService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public GitHubUser getUserProfile(String username, String token) {
        String url = GITHUB_API_URL + "/users/" + username;
        return restTemplate.getForObject(url, GitHubUser.class);
    }

    public GitHubRepo[] getUserRepos(String username, String token) {
        String url = GITHUB_API_URL + "/users/" + username + "/repos";
        return restTemplate.getForObject(url, GitHubRepo[].class);
    }
}
