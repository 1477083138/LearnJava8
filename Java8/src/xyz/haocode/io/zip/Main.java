package xyz.haocode.io.zip;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 *
 * @author LiuHao
 * @date 2020/4/22 17:22
 * @description 解压文件并读取内容
*/
public class Main {

    public static void main(String[] args) {
        try {

            readZipFile("C:\\Users\\Administrator\\Desktop\\MftInquiryFaAssetSrv_A2B274695E6C8ECEE054020820F74A3B.zip");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void readZipFile(String file) throws Exception {
        ZipFile zf = new ZipFile(file);
        Enumeration zipEnum = zf.entries();
        InputStream in = new BufferedInputStream(new FileInputStream(file));
        ZipInputStream zin = new ZipInputStream(in);
        while (zipEnum.hasMoreElements()) {
            ZipEntry ze = (ZipEntry) zipEnum.nextElement();
            if (ze.isDirectory()) {
            } else {
                System.err.println("file - " + ze.getName() + " : "
                        + ze.getSize() + " bytes");
                long size = ze.getSize();
                System.out.println(size);
                System.out.println(ze.getName());

                if (size > 0) {
                    if(ze.getName().endsWith(".properties")) {
                        BufferedReader br = new BufferedReader(
                                new InputStreamReader(zf.getInputStream(ze)));
                        String line;
                        while ((line = br.readLine()) != null) {
                            System.out.println(line);
                        }
                        br.close();
                    }else if(ze.getName().endsWith(".dat")){
                        BufferedReader br = new BufferedReader(
                                new InputStreamReader(zf.getInputStream(ze)));
                        String line;
                        while ((line = br.readLine()) != null) {
                            final String splitOp = (char)31+","+(char)31;
                            String[] lines = line.split(splitOp);
                            //按照下标顺序给相应字段赋值
                            for(String rowInfo : lines){
                                System.out.print(rowInfo+" | ");
                            }

                            break;
                        }
                        br.close();
                    }
                }
                System.out.println();
            }
        }
        zin.closeEntry();
    }
}
