package xyz.haocode.security.digest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;

/**
 *
 * @author LiuHao
 * @date 2020/4/24 10:07
 * @description SHA-1 SHA-256 or MD5
*/
public class Digest {

    public static void main(String[] args) throws IOException, GeneralSecurityException {
        String algname = "SHA-1";
        MessageDigest alg = MessageDigest.getInstance(algname);
        byte[] input = Files.readAllBytes(Paths.get("files/input.txt"));
        byte[] hash = alg.digest(input);
        String d = "";
        for(int i = 0; i < hash.length;i++){
            //取v值的低八位，保证补码的一致性（计算机中的存储都是以二进制的补码形式存储的）
            int v = hash[i] & 0xFF;
            if(v<16){
                d+="0";
            }
            d += Integer.toString(v,16).toUpperCase()+" ";
        }
        System.out.println(d);
    }
}
