package com.altec.bsbr.app.dl.util;

public class Base64Decoder extends Base64 {
  public static byte[] decode(String base64String) {
    if (base64String == null)
      throw new RuntimeException("null parameter is not supported in method decode()."); 
   // System.out.println("Base64 decoding: " + base64String);
    return decode(base64String.toCharArray());
  }
  
  private static byte[] decode(char[] in) {
    int iLen = in.length;
    if (iLen % 4 != 0)
      throw new IllegalArgumentException("Length of Base64 encoded input string is not a multiple of 4."); 
    while (iLen > 0 && in[iLen - 1] == '=')
      iLen--; 
    int oLen = iLen * 3 / 4;
    byte[] out = new byte[oLen];
    int ip = 0;
    int op = 0;
    while (ip < iLen) {
      int i0 = in[ip++];
      int i1 = in[ip++];
      int i2 = (ip < iLen) ? in[ip++] : 65;
      int i3 = (ip < iLen) ? in[ip++] : 65;
      if (i0 > 127 || i1 > 127 || i2 > 127 || i3 > 127)
        throw new IllegalArgumentException("Illegal character in Base64 encoded data."); 
      int b0 = table2[i0];
      int b1 = table2[i1];
      int b2 = table2[i2];
      int b3 = table2[i3];
      if (b0 < 0 || b1 < 0 || b2 < 0 || b3 < 0)
        throw new IllegalArgumentException("Illegal character in Base64 encoded data."); 
      int o0 = b0 << 2 | b1 >>> 4;
      int o1 = (b1 & 0xF) << 4 | b2 >>> 2;
      int o2 = (b2 & 0x3) << 6 | b3;
      out[op++] = (byte)o0;
      if (op < oLen)
        out[op++] = (byte)o1; 
      if (op < oLen)
        out[op++] = (byte)o2; 
    } 
    return out;
  }
}
