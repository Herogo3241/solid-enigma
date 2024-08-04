package com.example.github_resume_builder;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PdfService {

    public byte[] generateAtsFriendlyPdf(GitHubUser user, GitHubRepo[] repos, String[] selectedRepos, String phone, String location) throws DocumentException, IOException {
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PdfWriter.getInstance(document, baos);
        document.open();

        // Font setup
        BaseFont arialBaseFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.EMBEDDED);
        Font arialFont = new Font(arialBaseFont, 12);
        BaseFont helveticaBoldBaseFont = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.EMBEDDED);
        Font helveticaBoldFont = new Font(helveticaBoldBaseFont, 12);

        // Title
        Paragraph title = new Paragraph("Resume", helveticaBoldFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(new Paragraph("\n"));

        // Personal Information
        Paragraph personalInfoTitle = new Paragraph("Personal Information", helveticaBoldFont);
        personalInfoTitle.setSpacingBefore(10);
        personalInfoTitle.setSpacingAfter(5);
        document.add(personalInfoTitle);
        document.add(new Paragraph("Name: " + user.getName(), arialFont));
        document.add(new Paragraph("GitHub: " + user.getLogin(), arialFont));
        document.add(new Paragraph("Bio: " + user.getBio(), arialFont));
        document.add(new Paragraph("Location: " + location, arialFont));
        document.add(new Paragraph("Phone: " + phone, arialFont));
        document.add(new Paragraph("\n"));

        // Experience (Repositories)
        Paragraph experienceTitle = new Paragraph("Experience", helveticaBoldFont);
        experienceTitle.setSpacingBefore(10);
        experienceTitle.setSpacingAfter(5);
        document.add(experienceTitle);

        for (GitHubRepo repo : repos) {
            if (contains(selectedRepos, repo.getName())) {
                document.add(new Paragraph("Repository: " + repo.getName(), arialFont));
                document.add(new Paragraph("Description: " + repo.getDescription(), arialFont));
//                document.add(new Paragraph("URL: " + repo.getHtmlUrl(), arialFont));
                document.add(new Paragraph("\n"));
            }
        }

        document.close();
        return baos.toByteArray();
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
