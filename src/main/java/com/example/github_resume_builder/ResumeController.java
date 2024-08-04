package com.example.github_resume_builder;

import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Controller
public class ResumeController {

    private final GitHubService gitHubService;
    private final PdfService pdfService;
    private final GeminiService geminiService;

    @Autowired
    public ResumeController(GitHubService gitHubService, PdfService pdfService, GeminiService geminiService) {
        this.gitHubService = gitHubService;
        this.pdfService = pdfService;
        this.geminiService = geminiService;
    }

    @GetMapping("/")
    public String showForm() {
        return "index";
    }

    @GetMapping("/select-repos")
    public String selectRepos(@RequestParam String username, Model model) {
        GitHubRepo[] repos = gitHubService.getUserRepos(username, null);
        model.addAttribute("repos", repos);
        model.addAttribute("username", username);
        return "select-repos";
    }

    @PostMapping("/resume")
    public String getResume(@RequestParam String username,
                            @RequestParam(required = false) String[] selectedRepos,
                            @RequestParam String phone,
                            @RequestParam(required = false) String location,
                            Model model) {
        GitHubUser user = gitHubService.getUserProfile(username, null);
        GitHubRepo[] repos = gitHubService.getUserRepos(username, null);

        // Generate descriptions for selected repositories
//        for (GitHubRepo repo : repos) {
//            if (contains(selectedRepos, repo.getName())) {
//                String description = geminiService.getProjectDescription(repo.getName());
//                repo.setDescription(description); // Assuming a setter for description
//            }
//        }

        model.addAttribute("user", user);
        model.addAttribute("repos", repos);
        model.addAttribute("selectedRepos", selectedRepos);
        model.addAttribute("phone", phone);
        model.addAttribute("location", location);
        return "resume";
    }

    @PostMapping("/download-pdf")
    public HttpEntity<InputStreamResource> downloadPdf(@RequestParam String username,
                                                       @RequestParam(required = false) String[] selectedRepos,
                                                       @RequestParam String phone,
                                                       @RequestParam(required = false) String location) throws IOException, DocumentException, DocumentException {
        GitHubUser user = gitHubService.getUserProfile(username, null);
        GitHubRepo[] repos = gitHubService.getUserRepos(username, null);

        // Generate descriptions for selected repositories
//        for (GitHubRepo repo : repos) {
//            if (contains(selectedRepos, repo.getName())) {
//                String description = geminiService.getProjectDescription(repo.getName());
//                repo.setDescription(description); // Assuming a setter for description
//            }
//        }

        byte[] pdfBytes = pdfService.generateAtsFriendlyPdf(user, repos, selectedRepos, phone, location);

        ByteArrayInputStream bis = new ByteArrayInputStream(pdfBytes);
        InputStreamResource resource = new InputStreamResource(bis);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=resume.pdf");

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }

    private boolean contains(String[] array, String value) {
        if (array == null) return false;
        for (String element : array) {
            if (element.equals(value)) {
                return true;
            }
        }
        return false;
    }
}
