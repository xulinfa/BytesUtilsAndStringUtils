package com.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class StringUtils
{
  public static boolean isMacthIp(String ip)
  {
    if ((ip != null) && (!ip.isEmpty()))
    {
      String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";

      if (ip.matches(regex)) {
        return true;
      }
    }
    return false;
  }

  public static boolean isEmpty(String str)
  {
    if ((str == null) || ("".equals(str))) {
      return true;
    }
    return false;
  }

  public static boolean isNullOrEmpty(String str)
  {
    if ((str == null) || ("".equals(str)) || ("null".equals(str))) {
      return true;
    }
    return false;
  }

  public static boolean isDigital(String str)
  {
    if (!isEmpty(str))
      return str.matches("[0-9]+");
    return false;
  }

  public static int length(String value)
  {
    int valueLength = 0;
    String chinese = "[Α-￥]";

    for (int i = 0; i < value.length(); i++)
    {
      String temp = value.substring(i, i + 1);

      if (temp.matches(chinese))
      {
        valueLength += 2;
      }
      else {
        valueLength++;
      }
    }
    return valueLength;
  }

  public static List<String> stringsToList(String[] items)
  {
    List lists = new ArrayList();
    for (int i = 0; i < items.length; i++) {
      lists.add(items[i]);
    }
    return lists;
  }

  public static String fill(String sour, String fillStr, int len, boolean isLeft)
  {
    if (sour == null) {
      sour = "";
    }
    int fillLen = len - length(sour);
    String fill = "";
    for (int i = 0; i < fillLen; i++) {
      fill = fill + fillStr;
    }
    if (isLeft) {
      return fill + sour;
    }
    return sour + fill;
  }

  public static String paddingString(String strData, int nLen, String subStr, int nOption)
  {
    String strHead = "";
    String strEnd = "";

    int i = strData.length();
    if (i >= nLen) {
      return strData;
    }
    int addCharLen = 0;
    switch (nOption) {
    case 0:
      addCharLen = (nLen - i) / subStr.length();
      for (i = 0; i < addCharLen; i++) {
        strHead = strHead + subStr;
      }
      return strHead + strData;
    case 1:
      addCharLen = (nLen - i) / subStr.length();
      for (i = 0; i < addCharLen; i++) {
        strEnd = strEnd + subStr;
      }
      return strData + strEnd;
    case 2:
      addCharLen = (nLen - i) / (subStr.length() * 2);
      for (i = 0; i < addCharLen; i++) {
        strHead = strHead + subStr;
        strEnd = strEnd + subStr;
      }
      return strHead + strData + strEnd;
    }
    return strData;
  }

  public static String intToBcd(int value, int bytesNum)
  {
    switch (bytesNum) {
    case 1:
      if ((value >= 0) && (value <= 99)) {
        return paddingString(String.valueOf(value), 2, "0", 0);
      }
      break;
    case 2:
      if ((value >= 0) && (value <= 999)) {
        return paddingString(String.valueOf(value), 4, "0", 0);
      }

      break;
    case 3:
      if ((value >= 0) && (value <= 999)) {
        return paddingString(String.valueOf(value), 3, "0", 0);
      }
      break;
    }

    return "";
  }

  public static String hexToStr(String value)
    throws UnsupportedEncodingException
  {
    return new String(BytesUtils.hexToBytes(value), "GBK");
  }

  public static String strToHex(String value)
  {
    return BytesUtils.bytesToHex(BytesUtils.getBytes(value));
  }

  public static String paddingZeroToHexStr(String value, int option)
  {
    if (value.length() % 2 == 0) {
      return value;
    }

    if (option == 0) {
      return "0" + value;
    }
    if (option == 1) {
      return value + "0";
    }

    return value;
  }

  public static boolean checkHexStr(String value)
  {
    if (value == null) return false;

    int len = value.length();
    if (len == 0) return false;

    for (int i = 0; i < len; i++) {
      if (((value.charAt(i) < '0') || (value.charAt(i) > '9')) && 
        ((value.charAt(i) < 'a') || (value.charAt(i) > 'f')) && (
        (value.charAt(i) < 'A') || (value.charAt(i) > 'F'))) {
        return false;
      }
    }
    return true;
  }

  public static String binaryToHex(String value)
  {
    String result = "";
    char[] hexVocable = { '0', '1', '2', '3', 
      '4', '5', '6', '7', 
      '8', '9', 'A', 'B', 
      'C', 'D', 'E', 'F' };
    String[] binString = { "0000", "0001", "0010", "0011", 
      "0100", "0101", "0110", "0111", 
      "1000", "1001", "1010", "1011", 
      "1100", "1101", "1110", "1111" };

    int len = value.length();
    for (int i = 0; i < len; i += 4) {
      for (int j = 0; j < 16; j++) {
        if (binString[j].equals(value.substring(i, i + 4))) {
          result = result + hexVocable[j];
          break;
        }
      }
    }

    return result;
  }

  public static String hexToBinary(String value)
  {
    String result = "";
    char[] hexVocable = { '0', '1', '2', '3', 
      '4', '5', '6', '7', 
      '8', '9', 'A', 'B', 
      'C', 'D', 'E', 'F' };
    String[] binString = { "0000", "0001", "0010", "0011", 
      "0100", "0101", "0110", "0111", 
      "1000", "1001", "1010", "1011", 
      "1100", "1101", "1110", "1111" };

    int len = value.length();
    for (int i = 0; i < len; i++) {
      for (int j = 0; j < 16; j++) {
        if (value.charAt(i) == hexVocable[j]) {
          result = result + binString[j];
          break;
        }
      }
    }

    return result;
  }

  public static String getBinaryString(byte[] value)
  {
    String result = "";

    int len = value.length;

    for (int i = 0; i < len; i++) {
      result = result + String.valueOf(value[i]);
    }

    return result;
  }
}