package com.project.certificate.util;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;

import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import com.project.certificate.entity.Candidate;

@Component
public class PdfGeneratorUtil {

    public byte[] generate(Candidate candidate) throws Exception {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(writer);

        // =========================
        // PAGE SIZE
        // =========================

        PageSize pageSize = PageSize.A4.rotate();

        // CREATE SINGLE PAGE
        PdfPage pdfPage = pdf.addNewPage(pageSize);

        // DOCUMENT
        Document doc = new Document(pdf, pageSize);

        // =========================
        // COLORS
        // =========================

        Color NAVY = new DeviceRgb(24, 44, 74);
        Color GOLD = new DeviceRgb(201, 162, 39);
        Color DARK_GRAY = new DeviceRgb(90, 90, 90);

        // =========================
        // MARGINS
        // =========================

        doc.setMargins(35, 50, 35, 50);

        // =========================
        // BORDER DESIGN
        // =========================

        PdfCanvas canvas = new PdfCanvas(pdfPage);

        Rectangle page = pdfPage.getPageSize();

        // OUTER GOLD BORDER
        canvas.setStrokeColor(GOLD);
        canvas.setLineWidth(4);

        canvas.rectangle(
                20,
                20,
                page.getWidth() - 40,
                page.getHeight() - 40
        );

        canvas.stroke();

        // INNER NAVY BORDER
        canvas.setStrokeColor(NAVY);
        canvas.setLineWidth(1.5f);

        canvas.rectangle(
                35,
                35,
                page.getWidth() - 70,
                page.getHeight() - 70
        );

        canvas.stroke();

        // =========================
        // LOGO
        // =========================

        Image logo = new Image(
                ImageDataFactory.create(
                        getClass().getResource("/static/logo.png")
                )
        );

        logo.setWidth(90);
        logo.setHorizontalAlignment(HorizontalAlignment.CENTER);

        doc.add(logo);

        // =========================
        // ORGANIZATION NAME
        // =========================

        doc.add(
                new Paragraph(candidate.getOrganizationName().toUpperCase())
                        .setFontSize(20)
                        .setBold()
                        .setFontColor(NAVY)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setMarginBottom(8)
        );

        // =========================
        // TITLE
        // =========================

        doc.add(
                new Paragraph("CERTIFICATE OF COMPLETION")
                        .setFontSize(28)
                        .setBold()
                        .setFontColor(GOLD)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setMarginBottom(18)
        );
       

        // =========================
        // SUBTEXT
        // =========================

        doc.add(
                new Paragraph("This certificate is proudly presented to")
                        .setFontSize(15)
                        .setFontColor(DARK_GRAY)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setMarginBottom(10)
        );

        // =========================
        // NAME
        // =========================

        Paragraph name = new Paragraph(candidate.getName().toUpperCase())
                .setFontSize(26)
                .setBold()
                .setFontColor(NAVY)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(6);

        name.setBorderBottom(new SolidBorder(GOLD, 2));

        name.setWidth(350);

        name.setHorizontalAlignment(HorizontalAlignment.CENTER);

        doc.add(name);

        // =========================
        // COURSE MESSAGE
        // =========================

        doc.add(
                new Paragraph("For successfully completing the course")
                        .setFontSize(14)
                        .setFontColor(DARK_GRAY)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setMarginTop(14)
                        .setMarginBottom(6)
        );

        // =========================
        // COURSE NAME
        // =========================

        doc.add(
                new Paragraph(candidate.getCourseName())
                        .setFontSize(22)
                        .setBold()
                        .setFontColor(GOLD)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setMarginBottom(14)
        );

        // =========================
        // SCORE
        // =========================

        doc.add(
                new Paragraph("Score Achieved : " + candidate.getScore() + "%")
                        .setFontSize(15)
                        .setBold()
                        .setFontColor(NAVY)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setMarginBottom(10)
        );

        // =========================
        // FOOTER DETAILS
        // =========================

        doc.add(
                new Paragraph(
                        "Issued On : " + LocalDate.now()
                                + "     |     Certificate ID : CERT-"
                                + candidate.getId()
                )
                        .setFontSize(11)
                        .setFontColor(DARK_GRAY)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setMarginBottom(10)
        );

        // =========================
        // SIGNATURE TABLE
        // =========================

        Table signTable = new Table(
                UnitValue.createPercentArray(new float[]{1, 1})
        ).useAllAvailableWidth();

        signTable.setMarginTop(5);

        // EMPTY LEFT CELL

        signTable.addCell(
                new Cell()
                        .setBorder(Border.NO_BORDER)
        );

        // RIGHT CELL

        Cell signCell = new Cell()
                .setBorder(Border.NO_BORDER)
                .setTextAlignment(TextAlignment.RIGHT);

        // SIGN IMAGE

        Image sign = new Image(
                ImageDataFactory.create(
                        getClass().getResource("/static/sign.png")
                )
        );

        sign.setWidth(45);
        sign.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        signCell.add(sign);

        // LINE

        signCell.add(
                new Paragraph("___________________")
                        .setFontColor(DARK_GRAY)
                        .setFontSize(10)
                        .setMarginTop(0)
                        .setPaddingRight(0)
                        .setMarginBottom(0)
        );

        // COORDINATOR NAME

        signCell.add(
                new Paragraph(candidate.getCoordinatorName())
                        .setBold()
                        .setFontSize(12)
                        .setFontColor(NAVY)
                        .setMarginTop(0)
                        .setMarginBottom(0)
        );

        // ROLE

        signCell.add(
                new Paragraph("Training Coordinator")
                        .setFontSize(10)
                        .setFontColor(DARK_GRAY)
                        .setMarginTop(0)
                        .setMarginBottom(0)
        );

        signTable.addCell(signCell);

        doc.add(signTable);

        // =========================
        // CLOSE DOCUMENT
        // =========================

        doc.close();

        return baos.toByteArray();
    }
}