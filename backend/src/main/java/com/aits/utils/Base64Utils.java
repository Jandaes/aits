package com.aits.utils;

/**
 * @author jared
 */
public class Base64Utils
{
  private static final int ii_Fillchar = 61;
  private static final String is_Cvt = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

  public static String encodeRetStr(byte[] info)
  {
    if ((info == null) || (info.length == 0)) {
      throw new IllegalArgumentException("参数为空");
    }
    int c = 0;
    int len = info.length;
    StringBuffer ret = new StringBuffer((len / 3 + 1) * 4);
    for (int i = 0; i < len; i++) {
      c = info[i] >> 2 & 0x3F;
      ret.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(c));
      c = info[i] << 4 & 0x3F;

      i++; if (i < len) {
        c |= info[i] >> 4 & 0xF;
      }
      ret.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(c));
      if (i < len) {
        c = info[i] << 2 & 0x3F;
        i++; if (i < len) {
          c |= info[i] >> 6 & 0x3;
        }
        ret.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(c));
      } else {
        i++;
        ret.append('=');
      }
      if (i < len) {
        c = info[i] & 0x3F;
        ret.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(c));
      } else {
        ret.append('=');
      }
    }
    return ret.toString();
  }
  public static byte[] decodeStrRetBin(String info) throws IllegalArgumentException, Exception {
    return decodeBinRetBin(getBinaryBytes(info));
  }
  public static byte[] decodeBinRetBin(byte[] info) throws IllegalArgumentException, Exception {
    return getBinaryBytes(decodeBinRetStr(info));
  }
  public static String decodeBinRetStr(byte[] info) throws IllegalArgumentException, Exception {
    if ((info == null) || (info.length == 0)) {
      throw new IllegalArgumentException("参数为空");
    }
    int c0 = 0;
    int c1 = 0;
    int len = info.length;
    StringBuffer ret = new StringBuffer(len * 3 / 4);
    for (int i = 0; i < len; i++) {
      c0 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(info[i]);
      i++;
      c1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(info[i]);
      c0 = c0 << 2 | c1 >> 4 & 0x3;
      ret.append((char)c0);
      i++; if (i < len) {
        c0 = info[i];
        if (61 == c0) {
          break;
        }
        c0 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf((char)c0);
        c1 = c1 << 4 & 0xF0 | c0 >> 2 & 0xF;
        ret.append((char)c1);
      }

      i++; if (i < len) {
        c1 = info[i];
        if (61 == c1) {
          break;
        }
        c1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf((char)c1);
        c0 = c0 << 6 & 0xC0 | c1;
        ret.append((char)c0);
      }
    }
    return ret.toString();
  }

  public static byte[] getBinaryBytes(String info)
  {
    byte[] ret = new byte[info.length()];
    for (int i = 0; i < ret.length; i++) {
      ret[i] = ((byte) info.charAt(i));
    }
    return ret;
  }
}