package com.inventory.utils;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;

public class PDFFactory {
	
	public static ByteArrayOutputStream generateDocument(ItemProtocol base) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
	        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(baos));
			Document doc = new Document(pdfDoc);
			PdfFont titleFont = PdfFontFactory.createFont(FontConstants.TIMES_BOLD);
			PdfFont textFont = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
			Paragraph newLine = new Paragraph(new Text("\n"));
			Path path = Paths.get("src\\main\\resources\\templates\\UNITBV_LOGO.png");
			Image img = new Image(ImageDataFactory.create(path.toAbsolutePath().toString()));
			img.scaleAbsolute(200, 50);
			doc.add(img);
			Paragraph title = new Paragraph(base.getTitle());
			title.setFont(titleFont);
			title.setFontSize(base.getTitleFontSize());
			title.setFixedLeading(50.0f);
			title.setTextAlignment(TextAlignment.CENTER);
			Paragraph subtitle = new Paragraph(base.getSubtitle().getText());
			subtitle.setFont(textFont);
			subtitle.setFontSize(base.getTextFontSize());
			subtitle.setTextAlignment(TextAlignment.CENTER);
			doc.add(title);
			doc.add(newLine);
			doc.add(subtitle);
			doc.add(newLine);
			for(PDFField line : base.getFields()) {
				Paragraph par = new Paragraph(line.getText());
				par.setFont(textFont);
				par.setFontSize(base.getTextFontSize());
				par.setFixedLeading(base.getLineSpacing());
				doc.add(par);
			}
			doc.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return baos;
	}
}
