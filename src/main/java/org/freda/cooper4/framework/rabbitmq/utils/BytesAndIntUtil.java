package org.freda.cooper4.framework.rabbitmq.utils;
/**
 * BYTES AND INT
 * @author Rally
 *
 */
public class BytesAndIntUtil {

	private final static byte[] hex = "0123456789ABCDEF".getBytes();
	
	public static int ByteToInt(byte mybyte)
	{
		return mybyte;
	}

	public static byte IntToByte(int i)
	{
		return (byte) i;
	}

	public static int BytesToInt(byte[] mybytes)
	{
		return (((0xFF & mybytes[0]) << 8) + mybytes[1]);
	}

	public static byte[] IntToBytes(int i)
	{
		byte[] mybytes = new byte[2];
		mybytes[1] = (byte) (0xFF & i);
		mybytes[0] = (byte) ((0xFF00 & i) >> 8);
		return mybytes;
	}

	/**
	 *
	 */
	public static byte[] IntToBytes4(int i)
	{
		byte[] mybytes = new byte[4];
		mybytes[3] = (byte) (0xFF & i);
		mybytes[2] = (byte) ((0xFF00 & i) >> 8);
		mybytes[1] = (byte) ((0xFF0000 & i) >> 16);
		mybytes[0] = (byte) ((0xFF000000 & i) >> 24);
		return mybytes;
	}

	public static byte[] LongToBytes4(long i)
	{
		byte[] mybytes = new byte[4];
		mybytes[3] = (byte) (int) (0xFF & i);
		mybytes[2] = (byte) (int) ((0xFF00 & i) >> 8);
		mybytes[1] = (byte) (int) ((0xFF0000 & i) >> 16);
		mybytes[0] = (byte) (int) ((0xFF000000 & i) >> 24);
		return mybytes;
	}

	public static void LongToBytes4(long i, byte[] mybytes)
	{
		mybytes[3] = (byte) (int) (0xFF & i);
		mybytes[2] = (byte) (int) ((0xFF00 & i) >> 8);
		mybytes[1] = (byte) (int) ((0xFF0000 & i) >> 16);
		mybytes[0] = (byte) (int) ((0xFF000000 & i) >> 24);
	}

	public static void IntToBytes(int i, byte[] mybytes)
	{
		mybytes[1] = (byte) (0xFF & i);
		mybytes[0] = (byte) ((0xFF00 & i) >> 8);
	}

	public static final String BytesToHexString(byte[] byteArray)
	{
		StringBuffer sBuffer = new StringBuffer(byteArray.length);
		String sTemp = "";
		for (int i = 0; i < byteArray.length; i++) {
			sTemp = Integer.toHexString(0xFF & byteArray[i]);
			if (sTemp.length() < 2)
				sBuffer.append(0);
			sBuffer.append(sTemp.toUpperCase());
		}
		return sBuffer.toString();
	}

	/**
	 * 
	 */
	public static void IntToBytes4(int i, byte[] mybytes)
	{
		mybytes[3] = (byte) (0xFF & i);
		mybytes[2] = (byte) ((0xFF00 & i) >> 8);
		mybytes[1] = (byte) ((0xFF0000 & i) >> 16);
		mybytes[0] = (byte) (int) ((0xFF000000 & i) >> 24);
	}

	/**
	 * 
	 */
	public static int Bytes4ToInt(byte[] mybytes)
	{
		return ((0xFF & mybytes[0]) << 24 | (0xFF & mybytes[1]) << 16
				| (0xFF & mybytes[2]) << 8 | 0xFF & mybytes[3]);
	}

	/**
	 * 
	 */
	public static long Bytes4ToLong(byte[] mybytes)
	{
		return ((0xFF & mybytes[0]) << 24 | (0xFF & mybytes[1]) << 16
				| (0xFF & mybytes[2]) << 8 | 0xFF & mybytes[3]);
	}

	/**
	 * 
	 */
	public static void BytesCopy(byte[] source, byte[] dest, int sourceBegin, int sourceEnd, int destBegin)
	{
		int index = 0;
		for (int i = sourceBegin; i <= sourceEnd; ++i) {
			dest[(destBegin + index)] = source[i];
			++ index;
		}
	}

	public static String int16StrToint16Str4(final String str)
	{
		if(str.length() >= 4)
		{
			return str.substring(0, 4);			
		}
		else
		{
			String tempStr = "";
			for(int i=0;i<4 - str.length();i++)
			{
				tempStr += "0";
			}
			return tempStr + str;
		}
	}
	
	public static String Bytes2HexString(byte[] b)
	{
		byte[] buff = new byte[2 * b.length];
		for (int i = 0; i < b.length; i++)
		{
			buff[2 * i] = hex[(b[i] >> 4) & 0x0f];
			buff[2 * i + 1] = hex[b[i] & 0x0f];
		}
		return new String(buff);
	}
}
