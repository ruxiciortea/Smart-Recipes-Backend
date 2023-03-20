package com.ruxiciortea.Smart.Recipes.Util;

import com.ruxiciortea.Smart.Recipes.Model.User;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class ReportsGenerator {

    public void generateNewUserReport(User user) {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        try {
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            contentStream.setFont(PDType1Font.COURIER, 12);
            contentStream.beginText();
            contentStream.showText("Created user: " + user.getFirstname() + user.getLastname());
            contentStream.endText();
            contentStream.close();

            document.save("user" + user.getId().toString() + ".pdf");
            document.close();
        } catch (Exception e) {
            System.out.println("Could not generate reports.");
        }
    }

}
