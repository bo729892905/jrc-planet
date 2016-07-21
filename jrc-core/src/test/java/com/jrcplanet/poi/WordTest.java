package com.jrcplanet.poi;

import org.apache.poi.xwpf.usermodel.*;
import org.junit.Test;

import java.io.*;
import java.util.List;

/**
 * POI WORD 测试类
 * Created by rxb on 2016/7/5.
 */
public class WordTest {
    @Test
    public void testRead() {
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            is = new FileInputStream("D:\\TEST.docx");
            XWPFDocument doc = new XWPFDocument(is);
            List<XWPFParagraph> paras = doc.getParagraphs();

            //替换段落文字
            paras.forEach(para->{
                List<XWPFRun> runs = para.getRuns();
                runs.forEach(run->{
                    String text = run.getText(run.getTextPosition());
                    text = text.replaceAll("秦皇岛", "青岛");
                    run.setText(text, 0);
                });
            });

            //替换表格文字、添加行
            List<XWPFTable> tables = doc.getTables();
            tables.forEach(table->{
                List<XWPFTableRow> rows = table.getRows();
                //替换表格文字
                rows.forEach(row -> {
                    List<XWPFTableCell> cells = row.getTableCells();
                    cells.forEach(cell->{
                        String text = cell.getText();
                        text = text.replace("陈小姣", "张爱静");
                        cell.removeParagraph(0);
                        cell.setText(text);
                    });
                });

                //添加行
                XWPFTableRow newRow = table.getRow(rows.size() - 1);

            });
            fos = new FileOutputStream("D:\\TEST2.docx");
            doc.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
