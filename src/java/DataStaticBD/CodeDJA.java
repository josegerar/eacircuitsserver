/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataStaticBD;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.codec.binary.Base64;

import static java.util.stream.Collectors.joining;
import java.util.stream.Stream;

/**
 *
 * @author tonyp
 */
public class CodeDJA {

    private static final String staticKey = "SRXD";
    private SecureRandom aleatorio;

    public CodeDJA() {
        try {
            aleatorio = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("error en el Ramdon D:");
        }
    }
    
    
    
    private static final String[][] bufferLetters = new String[][]{
        {"zyC", "pLd", "aFo", "Jre", "Klc", "oMy", "qnP", "kfI", "iSz", "Gcu"},
        {"ZLo", "VmQ", "oLP", "RDg", "ZdP", "DcV", "oEG", "IuK", "AsM", "LOw"},
        {"AZM", "SFR", "OGH", "MVX", "YFD", "KTC", "DSK", "URF", "ACD", "HWX"},
        {"fme", "xia", "rtp", "acf", "cid", "oeb", "pwe", "zuw", "nvs", "amb"}
    };
    
    private static final String[][] minBufferLetters = new String[][]{
        {"b", "L", "o", "r", "l", "Y", "q", "P", "z", "G"},
        {"P", "V", "m", "Q", "c", "L", "X", "u", "A", "a"},
        {"t", "Y", "z", "P", "x", "w", "E", "T", "k", "o"},
        {"D", "f", "M", "I", "z", "d", "e", "p", "Q", "S"}
    };

    public String cifradoCesar(String texto, int codigo) {
        StringBuilder cifrado = new StringBuilder();
        codigo = codigo % 26;
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) >= 'a' && texto.charAt(i) <= 'z') {
                if ((texto.charAt(i) + codigo) > 'z') {
                    cifrado.append((char) (texto.charAt(i) + codigo - 26));
                } else {
                    cifrado.append((char) (texto.charAt(i) + codigo));
                }
            } else if (texto.charAt(i) >= 'A' && texto.charAt(i) <= 'Z') {
                if ((texto.charAt(i) + codigo) > 'Z') {
                    cifrado.append((char) (texto.charAt(i) + codigo - 26));
                } else {
                    cifrado.append((char) (texto.charAt(i) + codigo));
                }
            }
        }
        return cifrado.toString();
    }

    //método para descifrar el texto
    public String descifradoCesar(String texto, int codigo) {
        StringBuilder cifrado = new StringBuilder();
        codigo = codigo % 26;
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) >= 'a' && texto.charAt(i) <= 'z') {
                if ((texto.charAt(i) - codigo) < 'a') {
                    cifrado.append((char) (texto.charAt(i) - codigo + 26));
                } else {
                    cifrado.append((char) (texto.charAt(i) - codigo));
                }
            } else if (texto.charAt(i) >= 'A' && texto.charAt(i) <= 'Z') {
                if ((texto.charAt(i) - codigo) < 'A') {
                    cifrado.append((char) (texto.charAt(i) - codigo + 26));
                } else {
                    cifrado.append((char) (texto.charAt(i) - codigo));
                }
            }
        }
        return cifrado.toString();
    }

    private int letterToInteger(String information) {
        int num = 0;
        for (int i = 0; i < information.length(); i++) {
            num += (int) information.charAt(i);
        }
        return num + 3;
    }

    public String encodeDJA(String texto) {
        if (isNumeric(texto)) {
            texto = getSuperID(texto);
            texto = transform(texto);
            texto = cifradoCesar(texto, letterToInteger(staticKey));
            texto = cifradoCesar(texto, 12);
            byte[] bytesEncoded = Base64.encodeBase64(texto.getBytes());
            return new String(bytesEncoded);
        } else {
            return "";
        }
    }

    public String decodeDJA(String texto) {
        byte[] valueDecoded = Base64.decodeBase64(texto);
        texto = descifradoCesar(new String(valueDecoded), 12);
        texto = descifradoCesar(texto, letterToInteger(staticKey));
        texto = untransform(texto);
        return texto;
    }

    public static String untransform(String formato) {
        String texto = "";
        for (int i = 0; i < formato.length() / 3; i++) {
            Boolean Rowflag = false;
            String part = formato.substring((i * 3), (i * 3) + 3);
            for (int bffRow = 0; bffRow < bufferLetters.length; bffRow++) {
                for (int bffCol = 0; bffCol < bufferLetters[0].length; bffCol++) {
                    if (part.equals(bufferLetters[bffRow][bffCol])) {
                        texto += bffCol;
                        Rowflag = true;
                    }
                }

            }
            if (Rowflag == false) {
                texto += "ñ";
            }
        }
        return texto;
    }

    public static String transform(String formato) {
        String texto = "";
        for (int i = 0; i < formato.length(); i++) {
            // y = i%%4;
            int y = i % 4;
            String caracter = String.valueOf(formato.charAt(i));
            int num = Integer.parseInt(caracter);
            texto += bufferLetters[y][num];
        }
        return texto;
    }

    private static boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
    
    public static String getSuperID(String num) {
        num = num.replaceAll("[^0-9]", "");
        String zeros = repeat("0",15 - num.length());
        return zeros+num;
    }

    private static String repeat(String str, int times) {
        return new String(new char[times]).replace("\0", str); 
    }
    
    
    public String getEmailCode(){
        String result = "";
        for (int i = 0; i < 10; i++) {
            int fila =aleatorio.nextInt(minBufferLetters.length);
            int col =aleatorio.nextInt(minBufferLetters[0].length);
            result += minBufferLetters[fila][col];
        }
        return cifradoCesar(result,letterToInteger(staticKey)+2);
    }
    
    public String getMaxAlea(){
        String result = "";
        for (int i = 0; i < 12; i++) {
            int fila =aleatorio.nextInt(bufferLetters.length);
            int col =aleatorio.nextInt(bufferLetters[0].length);
            result += bufferLetters[fila][col];
        }
        return cifradoCesar(result,letterToInteger(staticKey)+2);
    }
//    public String getEmailPass(String param1, String param2) {
//        String nam = "", allparam = param1 + param2;
//        for (int i = 0; i < 12; i++) {
//            nam += String.valueOf(allparam.charAt(aleatorio.nextInt(allparam.length())));
//        }
//        return getcodif(nam);
//    }
//
    public String getImgName(String text) {
        String txt = text.replaceAll("[^A-Za-z]", "");
        text = getSuperID(txt);
//        String result = "";
//        for (int i = 0; i < txt.length(); i++) {
//            int fila =aleatorio.nextInt(minBufferLetters.length);
//            int col =aleatorio.nextInt(minBufferLetters[0].length);
//            result += minBufferLetters[fila][col];
//        }
        return cifradoCesar(text,letterToInteger(staticKey)+2);
    }
}
