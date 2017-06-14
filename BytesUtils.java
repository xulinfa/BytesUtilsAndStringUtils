package com.utils;

import java.nio.charset.Charset;

public class BytesUtils
{
  public static final String GBK = "GBK";
  public static final String UTF8 = "utf-8";
  public static final char[] ascii = "0123456789ABCDEF".toCharArray();
  private static char[] HEX_VOCABLE = { '0', '1', '2', '3', '4', '5', '6', 
    '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

  public static byte[] getBytes(short data)
  {
    byte[] bytes = new byte[2];
    bytes[0] = ((byte)((data & 0xFF00) >> 8));
    bytes[1] = ((byte)(data & 0xFF));
    return bytes;
  }

  public static byte[] getBytes(char data)
  {
    byte[] bytes = new byte[2];
    bytes[0] = ((byte)(data >> '\b'));
    bytes[1] = ((byte)data);
    return bytes;
  }

  public static byte[] getBytes(boolean data)
  {
    byte[] bytes = new byte[1];
    bytes[0] = ((byte)(data ? 1 : 0));
    return bytes;
  }

  public static byte[] getBytes(int data)
  {
    byte[] bytes = new byte[4];
    bytes[0] = ((byte)((data & 0xFF000000) >> 24));
    bytes[1] = ((byte)((data & 0xFF0000) >> 16));
    bytes[2] = ((byte)((data & 0xFF00) >> 8));
    bytes[3] = ((byte)(data & 0xFF));
    return bytes;
  }

  public static byte[] getBytes(long data)
  {
    byte[] bytes = new byte[8];
    bytes[0] = ((byte)(int)(data >> 56 & 0xFF));
    bytes[1] = ((byte)(int)(data >> 48 & 0xFF));
    bytes[2] = ((byte)(int)(data >> 40 & 0xFF));
    bytes[3] = ((byte)(int)(data >> 32 & 0xFF));
    bytes[4] = ((byte)(int)(data >> 24 & 0xFF));
    bytes[5] = ((byte)(int)(data >> 16 & 0xFF));
    bytes[6] = ((byte)(int)(data >> 8 & 0xFF));
    bytes[7] = ((byte)(int)(data & 0xFF));
    return bytes;
  }

  public static byte[] getBytes(float data)
  {
    int intBits = Float.floatToIntBits(data);
    return getBytes(intBits);
  }

  public static byte[] getBytes(double data)
  {
    long intBits = Double.doubleToLongBits(data);
    return getBytes(intBits);
  }

  public static byte[] getBytes(String data, String charsetName)
  {
    Charset charset = Charset.forName(charsetName);
    return data.getBytes(charset);
  }

  public static byte[] getBytes(String data)
  {
    return getBytes(data, "GBK");
  }

  public static boolean getBoolean(byte[] bytes)
  {
    return bytes[0] == 1;
  }

  public static boolean getBoolean(byte[] bytes, int index)
  {
    return bytes[index] == 1;
  }

  public static short getShort(byte[] bytes)
  {
    return (short)(0xFF00 & bytes[0] << 8 | 0xFF & bytes[1]);
  }

  public static short getShort(byte[] bytes, int startIndex)
  {
    return (short)(0xFF00 & bytes[startIndex] << 8 | 0xFF & bytes[(startIndex + 1)]);
  }

  public static char getChar(byte[] bytes)
  {
    return (char)(0xFF00 & bytes[0] << 8 | 0xFF & bytes[1]);
  }

  public static char getChar(byte[] bytes, int startIndex)
  {
    return (char)(0xFF00 & bytes[startIndex] << 8 | 0xFF & bytes[(startIndex + 1)]);
  }

  public static int getInt(byte[] bytes)
  {
    return 0xFF000000 & bytes[0] << 24 | 0xFF0000 & bytes[1] << 16 | 
      0xFF00 & bytes[2] << 8 | 0xFF & bytes[3];
  }

  public static int getInt(byte[] bytes, int startIndex)
  {
    return 0xFF000000 & bytes[startIndex] << 24 | 
      0xFF0000 & bytes[(startIndex + 1)] << 16 | 
      0xFF00 & bytes[(startIndex + 2)] << 8 | 0xFF & bytes[(startIndex + 3)];
  }

  public static long getLong(byte[] bytes)
  {
    return 0x0 & bytes[0] << 56 | 
      0x0 & bytes[1] << 48 | 
      0x0 & bytes[2] << 40 | 
      0x0 & bytes[3] << 32 | 
      0xFF000000 & bytes[4] << 24 | 
      0xFF0000 & bytes[5] << 16 | 
      0xFF00 & bytes[6] << 8 | 0xFF & bytes[7];
  }

  public static long getLong(byte[] bytes, int startIndex)
  {
    return 0x0 & bytes[startIndex] << 56 | 
      0x0 & bytes[(startIndex + 1)] << 48 | 
      0x0 & bytes[(startIndex + 2)] << 40 | 
      0x0 & bytes[(startIndex + 3)] << 32 | 
      0xFF000000 & bytes[(startIndex + 4)] << 24 | 
      0xFF0000 & bytes[(startIndex + 5)] << 16 | 
      0xFF00 & bytes[(startIndex + 6)] << 8 | 0xFF & bytes[(startIndex + 7)];
  }

  public static float getFloat(byte[] bytes)
  {
    return Float.intBitsToFloat(getInt(bytes));
  }

  public static float getFloat(byte[] bytes, int startIndex)
  {
    byte[] result = new byte[4];
    System.arraycopy(bytes, startIndex, result, 0, 4);
    return Float.intBitsToFloat(getInt(result));
  }

  public static double getDouble(byte[] bytes)
  {
    long l = getLong(bytes);
    return Double.longBitsToDouble(l);
  }

  public static double getDouble(byte[] bytes, int startIndex)
  {
    byte[] result = new byte[8];
    System.arraycopy(bytes, startIndex, result, 0, 8);
    long l = getLong(result);
    return Double.longBitsToDouble(l);
  }

  public static String getString(byte[] bytes, String charsetName)
  {
    return new String(bytes, Charset.forName(charsetName));
  }

  public static String getString(byte[] bytes)
  {
    return getString(bytes, "GBK");
  }

  public static byte[] str2bcd(String hex, boolean isLeft)
  {
    if ((hex == null) || ("".equals(hex))) {
      return null;
    }

    if (hex.length() % 2 != 0) {
      if (StringUtils.isEmpty("0")) {
        hex = hex + "0";
      }
      else if (isLeft)
        hex = "0" + hex;
      else {
        hex = hex + "0";
      }

    }

    int len = hex.length() / 2;

    byte[] result = new byte[len];
    char[] chArr = hex.toCharArray();
    for (int i = 0; i < len; i++) {
      int pos = i * 2;
      result[i] = ((byte)(toByte(chArr[pos]) << 4 | toByte(chArr[(pos + 1)])));
    }
    return result;
  }

  public static byte[] hexStringToBytes(String hex)
  {
    if ((hex == null) || ("".equals(hex))) {
      return null;
    }
    int len = hex.length() / 2;
    byte[] result = new byte[len];
    char[] chArr = hex.toCharArray();
    for (int i = 0; i < len; i++) {
      int pos = i * 2;
      result[i] = ((byte)(toByte(chArr[pos]) << 4 | toByte(chArr[(pos + 1)])));
    }
    return result;
  }

  public static byte[] hexStringToBytes(String hex, String subStr, boolean isLeft)
  {
    if ((hex == null) || ("".equals(hex))) {
      return null;
    }

    if (hex.length() % 2 != 0) {
      if (StringUtils.isEmpty(subStr)) {
        hex = hex + "0";
      }
      else if (isLeft)
        hex = subStr + hex;
      else {
        hex = hex + subStr;
      }

    }

    int len = hex.length() / 2;

    byte[] result = new byte[len];
    char[] chArr = hex.toCharArray();
    for (int i = 0; i < len; i++) {
      int pos = i * 2;
      result[i] = ((byte)(toByte(chArr[pos]) << 4 | toByte(chArr[(pos + 1)])));
    }
    return result;
  }

  public static byte[] hexToBytes(String hex)
  {
    if (hex.length() % 2 != 0)
      throw new IllegalArgumentException(
        "input string should be any multiple of 2!");
    hex.toUpperCase();

    byte[] byteBuffer = new byte[hex.length() / 2];

    byte padding = 0;
    boolean paddingTurning = false;
    for (int i = 0; i < hex.length(); i++) {
      if (paddingTurning) {
        char c = hex.charAt(i);
        int index = indexOf(hex, c);
        padding = (byte)(padding << 4 | index);
        byteBuffer[(i / 2)] = padding;
        padding = 0;
        paddingTurning = false;
      } else {
        char c = hex.charAt(i);
        int index = indexOf(hex, c);
        padding = (byte)(padding | index);
        paddingTurning = true;
      }
    }

    return byteBuffer;
  }

  private static int indexOf(String input, char c)
  {
    for (int i = 0; i < HEX_VOCABLE.length; i++) {
      if (c == HEX_VOCABLE[i]) {
        return i;
      }
    }
    throw new IllegalArgumentException("err input:" + input);
  }

  public static String bcdToString(byte[] bcds)
  {
    if ((bcds == null) || (bcds.length == 0)) {
      return null;
    }
    byte[] temp = new byte[2 * bcds.length];
    for (int i = 0; i < bcds.length; i++) {
      temp[(i * 2)] = ((byte)(bcds[i] >> 4 & 0xF));
      temp[(i * 2 + 1)] = ((byte)(bcds[i] & 0xF));
    }
    StringBuffer res = new StringBuffer();
    for (int i = 0; i < temp.length; i++) {
      res.append(ascii[temp[i]]);
    }
    return res.toString();
  }

  public static int bcdToInt(byte value)
  {
    return (value >> 4) * 10 + (value & 0xF);
  }

  public static String bytesToHex(byte[] bs)
  {
    StringBuilder sb = new StringBuilder();
    byte[] arrayOfByte = bs; int j = bs.length; for (int i = 0; i < j; i++) { byte b = arrayOfByte[i];
      int high = b >> 4 & 0xF;
      int low = b & 0xF;
      sb.append(HEX_VOCABLE[high]);
      sb.append(HEX_VOCABLE[low]);
    }
    return sb.toString();
  }

  public static String bytesToHex(byte[] bs, int len)
  {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < len; i++) {
      byte b = bs[i];
      int high = b >> 4 & 0xF;
      int low = b & 0xF;
      sb.append(HEX_VOCABLE[high]);
      sb.append(HEX_VOCABLE[low]);
    }
    return sb.toString();
  }

  public static String bytesToHex(byte[] bs, int offset, int len)
  {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < len; i++) {
      byte b = bs[(offset + i)];
      int high = b >> 4 & 0xF;
      int low = b & 0xF;
      sb.append(HEX_VOCABLE[high]);
      sb.append(HEX_VOCABLE[low]);
    }
    return sb.toString();
  }

  public static String byteToHex(byte b)
  {
    StringBuilder sb = new StringBuilder();
    int high = b >> 4 & 0xF;
    int low = b & 0xF;
    sb.append(HEX_VOCABLE[high]);
    sb.append(HEX_VOCABLE[low]);
    return sb.toString();
  }

  public static String negate(byte[] src)
  {
    if ((src == null) || (src.length == 0)) {
      return null;
    }
    byte[] temp = new byte[2 * src.length];
    for (int i = 0; i < src.length; i++) {
      byte tmp = (byte)(0xFF ^ src[i]);
      temp[(i * 2)] = ((byte)(tmp >> 4 & 0xF));
      temp[(i * 2 + 1)] = ((byte)(tmp & 0xF));
    }
    StringBuffer res = new StringBuffer();
    for (int i = 0; i < temp.length; i++) {
      res.append(ascii[temp[i]]);
    }
    return res.toString();
  }

  public static boolean compareBytes(byte[] a, byte[] b)
  {
    if ((a == null) || (a.length == 0) || (b == null) || (b.length == 0) || 
      (a.length != b.length)) {
      return false;
    }
    if (a.length == b.length) {
      for (int i = 0; i < a.length; i++) {
        if (a[i] != b[i])
          return false;
      }
    }
    else {
      return false;
    }
    return true;
  }

  public static boolean compareBytes(byte[] a, byte[] b, int len)
  {
    if ((a == null) || (a.length == 0) || (b == null) || (b.length == 0) || 
      (a.length < len) || (b.length < len)) {
      return false;
    }
    for (int i = 0; i < len; i++) {
      if (a[i] != b[i]) {
        return false;
      }
    }
    return true;
  }

  public static String bytesToBinaryString(byte[] items)
  {
    if ((items == null) || (items.length == 0)) {
      return null;
    }
    StringBuffer buf = new StringBuffer();
    byte[] arrayOfByte = items; int j = items.length; for (int i = 0; i < j; i++) { byte item = arrayOfByte[i];
      buf.append(byteToBinaryString(item));
    }
    return buf.toString();
  }

  public static String byteToBinaryString(byte item)
  {
    byte a = item;
    StringBuffer buf = new StringBuffer();
    for (int i = 0; i < 8; i++) {
      buf.insert(0, a % 2);
      a = (byte)(a >> 1);
    }
    return buf.toString();
  }

  public static byte[] xor(byte[] a, byte[] b)
  {
    if ((a == null) || (a.length == 0) || (b == null) || (b.length == 0) || 
      (a.length != b.length)) {
      return null;
    }
    byte[] result = new byte[a.length];
    for (int i = 0; i < a.length; i++) {
      result[i] = ((byte)(a[i] ^ b[i]));
    }
    return result;
  }

  public static byte[] xor(byte[] a, byte[] b, int len)
  {
    if ((a == null) || (a.length == 0) || (b == null) || (b.length == 0)) {
      return null;
    }
    if ((a.length < len) || (b.length < len)) {
      return null;
    }
    byte[] result = new byte[len];
    for (int i = 0; i < len; i++) {
      result[i] = ((byte)(a[i] ^ b[i]));
    }
    return result;
  }

  public static byte[] shortToBytes(int num)
  {
    byte[] temp = new byte[2];
    for (int i = 0; i < 2; i++) {
      temp[i] = ((byte)(num >>> 8 - i * 8 & 0xFF));
    }
    return temp;
  }

  public static int bytesToShort(byte[] arr)
  {
    int mask = 255;
    int temp = 0;
    int result = 0;
    for (int i = 0; i < 2; i++) {
      result <<= 8;
      temp = arr[i] & mask;
      result |= temp;
    }
    return result;
  }

  public static byte[] intToBytes(int num)
  {
    byte[] temp = new byte[4];
    for (int i = 0; i < 4; i++) {
      temp[i] = ((byte)(num >>> 24 - i * 8 & 0xFF));
    }
    return temp;
  }

  public static byte[] intToBytes(int src, int len)
  {
    if ((len < 1) || (len > 4)) {
      return null;
    }
    byte[] temp = new byte[len];
    for (int i = 0; i < len; i++) {
      temp[(len - 1 - i)] = ((byte)(src >>> 8 * i & 0xFF));
    }
    return temp;
  }

  public static int bytesToInt(byte[] arr)
  {
    int mask = 255;
    int temp = 0;
    int result = 0;
    for (int i = 0; i < 4; i++) {
      result <<= 8;
      temp = arr[i] & mask;
      result |= temp;
    }
    return result;
  }

  public static byte[] longToBytes(long num)
  {
    byte[] temp = new byte[8];
    for (int i = 0; i < 8; i++) {
      temp[i] = ((byte)(int)(num >>> 56 - i * 8 & 0xFF));
    }
    return temp;
  }

  public static long bytesToLong(byte[] arr)
  {
    int mask = 255;
    int temp = 0;
    long result = 0L;
    int len = Math.min(8, arr.length);
    for (int i = 0; i < len; i++) {
      result <<= 8;
      temp = arr[i] & mask;
      result |= temp;
    }
    return result;
  }

  public static byte toByte(char c)
  {
    byte b = (byte)"0123456789ABCDEF".indexOf(c);
    return b;
  }

  public static int bytesToIntWhereByteLengthEquals2(byte[] lenData)
  {
    if (lenData.length != 2) {
      return -1;
    }
    byte[] fill = new byte[2];
    byte[] real = new byte[4];
    System.arraycopy(fill, 0, real, 0, 2);
    System.arraycopy(lenData, 0, real, 2, 2);
    int len = byteToInt(real);
    return len;
  }

  public static int byteToInt(byte[] byteVal)
  {
    int result = 0;
    for (int i = 0; i < byteVal.length; i++) {
      int tmpVal = byteVal[i] << 8 * (3 - i);
      switch (i) {
      case 0:
        tmpVal &= -16777216;
        break;
      case 1:
        tmpVal &= 16711680;
        break;
      case 2:
        tmpVal &= 65280;
        break;
      case 3:
        tmpVal &= 255;
      }

      result = result | 
        tmpVal;
    }
    return result;
  }

  public static byte CheckXORSum(byte[] bData)
  {
    byte sum = 0;
    for (int i = 0; i < bData.length; i++) {
      sum = (byte)(sum ^ bData[i]);
    }
    return sum;
  }

  public static int bytesToInt(byte[] data, int offset, int len)
  {
    int mask = 255;
    int temp = 0;
    int result = 0;
    len = Math.min(len, 4);
    for (int i = 0; i < len; i++) {
      result <<= 8;
      temp = data[(offset + i)] & mask;
      result |= temp;
    }
    return result;
  }

  public static String TrimCharLeft(String source, char c)
  {
    String beTrim = String.valueOf(c);
    source = source.trim();

    String beginChar = source.substring(0, 1);
    while (beginChar.equalsIgnoreCase(beTrim)) {
      source = source.substring(1, source.length());
      beginChar = source.substring(0, 1);
    }
    return source;
  }

  public static String TrimCharRight(String source, char c)
  {
    String beTrim = String.valueOf(c);
    source = source.trim();

    String endChar = source.substring(source.length() - 1, source.length());
    while (endChar.equalsIgnoreCase(beTrim)) {
      source = source.substring(0, source.length() - 1);
      endChar = source.substring(source.length() - 1, source.length());
    }
    return source;
  }
}