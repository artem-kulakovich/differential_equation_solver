package by.bntu.fitr.model.microsoft_api;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class WordHandler {

    public static void handle(List<String> info) throws IOException {


        XWPFDocument xwpfDocument = new XWPFDocument();

        /*
        XWPFTable table = xwpfDocument.createTable();
       // XWPFTableRow row = table.getRow(0);


        XWPFTableRow tableRowOne = table.getRow(0);
        tableRowOne.getCell(0).setText(String.format("%.3f", xCords[0]));
        for (int i = 1; i < xCords.length; i++) {
            tableRowOne.addNewTableCell().setText(String.format("%.3f", xCords[i]));
        }

        XWPFTableRow tableRowTwo = table.createRow();
        for (int i = 0; i < yCords.length; i++) {
            tableRowTwo.getCell(i).setText(String.format("%.3f", yCords[i]));
        }
*/

        for(int i = 0;i<info.size();i++){
            XWPFParagraph paragraph = xwpfDocument.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText(info.get(i));
        }

        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\Artyom\\IdeaProjects\\course_work\\src\\by\\bntu\\fitr\\templates\\result.docx");
        xwpfDocument.write(fileOutputStream);




    }
}
