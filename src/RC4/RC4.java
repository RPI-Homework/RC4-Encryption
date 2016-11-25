package RC4;

public class RC4
{
	private int[] Key;
	public void SetKey(String NewKey)
	{
		Key = new int[(NewKey.length() + 2) / 3];
		int x = 0, y = 0;
		for(int i = 0; i < NewKey.length(); i++)
		{
			switch(NewKey.charAt(i))
			{
				case ' ':
					Key[x] = y;
					y = 0;
					x++;
					break;
				case '0':
					y = y * 16;
					break;
				case '1':
					y = y * 16 + 1;
					break;
				case '2':
					y = y * 16 + 2;
					break;
				case '3':
					y = y * 16 + 3;
					break;
				case '4':
					y = y * 16 + 4;
					break;
				case '5':
					y = y * 16 + 5;
					break;
				case '6':
					y = y * 16 + 6;
					break;
				case '7':
					y = y * 16 + 7;
					break;
				case '8':
					y = y * 16 + 8;
					break;
				case '9':
					y = y * 16 + 9;
					break;
				case 'a':
				case 'A':
					y = y * 16 + 10;
					break;
				case 'b':
				case 'B':
					y = y * 16 + 11;
					break;
				case 'c':
				case 'C':
					y = y * 16 + 12;
					break;
				case 'd':
				case 'D':
					y = y * 16 + 13;
					break;
				case 'e':
				case 'E':
					y = y * 16 + 14;
					break;
				case 'f':
				case 'F':
					y = y * 16 + 15;
					break;
			}
		}
		Key[x] = y;
	}
	private String intbin(int y)
	{
		String s = new String();
		while(y > 0)
		{
			switch(y%2)
			{
				case 1:
					s = '1' + s;
					break;
				case 0:
					s = '0' + s;
					break;
			}
			y /= 2;
		}
		while (s.length() < 8)
		{
			s = '0' + s;
		}
		return s;
	}
	private char XOR(char c1, char c2)
	{
		if((c1 == '0' && c2 == '0') || (c1 == '1' && c2 == '1'))
		{
			return '0';
		}
		else
		{
			return '1';
		}
	}
	private String XOR(String s1, String s2)
	{
		return "" +
				XOR(s1.charAt(1-1), s2.charAt(1-1)) +
				XOR(s1.charAt(2-1), s2.charAt(2-1)) +
				XOR(s1.charAt(3-1), s2.charAt(3-1)) +
				XOR(s1.charAt(4-1), s2.charAt(4-1)) +
				XOR(s1.charAt(5-1), s2.charAt(5-1)) +
				XOR(s1.charAt(6-1), s2.charAt(6-1)) +
				XOR(s1.charAt(7-1), s2.charAt(7-1)) +
				XOR(s1.charAt(8-1), s2.charAt(8-1));
	}
	public String Encrypt(String Text)
	{
		String cipher = new String();
		int[] key = new int[256];
		int[] sbox = new int[256];
		for(int i = 0; i < 256; i++)
		{
			key[i] = Key[i % Key.length];
			sbox[i] = i;
		}
		int y = 0;
		for(int i = 0; i < 256; i++)
		{
			y = (y + sbox[i] + key[i]) % 256;
			int temp = sbox[i];
			sbox[i] = sbox[y];
			sbox[y] = temp;
		}
		
		y = 0;
		int x = 0, g = 0;
		for(int i = 0; i < Text.length(); i += 8)
		{
			x = (x + 1) % 256;
			y = (y + sbox[x]) % 256;
			int temp = sbox[x];
			sbox[x] = sbox[y];
			sbox[y] = temp;
			g = sbox[(sbox[x] + sbox[y]) % 256];
			cipher = cipher + XOR(Text.substring(i, i+8), intbin(g));
		}
		return cipher;
	}
	public String Decrypt(String Text)
	{
		return Encrypt(Text);
	}
	static private String hexbin(String Hex)
	{
		String s = new String();
		for(int i = 0; i < Hex.length(); i++)
		{
			switch(Hex.charAt(i))
			{
				case '0':
					s = s + "0000";
					break;
				case '1':
					s = s + "0001";
					break;
				case '2':
					s = s + "0010";
					break;
				case '3':
					s = s + "0011";
					break;
				case '4':
					s = s + "0100";
					break;
				case '5':
					s = s + "0101";
					break;
				case '6':
					s = s + "0110";
					break;
				case '7':
					s = s + "0111";
					break;
				case '8':
					s = s + "1000";
					break;
				case '9':
					s = s + "1001";
					break;
				case 'a':
				case 'A':
					s = s + "1010";
					break;
				case 'b':
				case 'B':
					s = s + "1011";
					break;
				case 'c':
				case 'C':
					s = s + "1100";
					break;
				case 'd':
				case 'D':
					s = s + "1101";
					break;
				case 'e':
				case 'E':
					s = s + "1110";
					break;
				case 'f':
				case 'F':
					s = s + "1111";
					break;
			}
		}
		return s;
	}
	static private String binhex(String Hex)
	{
		String s = new String();
		for(int i = 0; i < Hex.length(); i += 4)
		{
			String Comp = Hex.substring(i, i+4);
			if(Comp.compareTo("0000") == 0)
			{
				s = s + '0';
			}
			else if(Comp.compareTo("0001") == 0)
			{
				s = s + '1';
			}
			else if(Comp.compareTo("0010") == 0)
			{
				s = s + '2';
			}
			else if(Comp.compareTo("0011") == 0)
			{
				s = s + '3';
			}
			else if(Comp.compareTo("0100") == 0)
			{
				s = s + '4';
			}
			else if(Comp.compareTo("0101") == 0)
			{
				s = s + '5';
			}
			else if(Comp.compareTo("0110") == 0)
			{
				s = s + '6';
			}
			else if(Comp.compareTo("0111") == 0)
			{
				s = s + '7';
			}
			else if(Comp.compareTo("1000") == 0)
			{
				s = s + '8';
			}
			else if(Comp.compareTo("1001") == 0)
			{
				s = s + '9';
			}
			else if(Comp.compareTo("1010") == 0)
			{
				s = s + 'A';
			}
			else if(Comp.compareTo("1011") == 0)
			{
				s = s + 'B';
			}
			else if(Comp.compareTo("1100") == 0)
			{
				s = s + 'C';
			}
			else if(Comp.compareTo("1101") == 0)
			{
				s = s + 'D';
			}
			else if(Comp.compareTo("1110") == 0)
			{
				s = s + 'E';
			}
			else if(Comp.compareTo("1111") == 0)
			{
				s = s + 'F';
			}
		}
		return s;
	}
	static public void Text()
	{
		RC4 R = new RC4();
		//Test1
		R.SetKey("4B 65 79");//Key
		String s = binhex(R.Encrypt(hexbin("50 6C 61 69 6E 74 65 78 74")));//Plaintext -> BBF316E8D940AF0AD3
		s = binhex(R.Decrypt(hexbin(s)));//506C61696E74657874
		//Test2
		R.SetKey("57 69 6B 69");//Wiki
		s = binhex(R.Encrypt(hexbin("70 65 64 69 61")));//pedia -> 1021BF0420
		s = binhex(R.Decrypt(hexbin(s)));//7065646961
		//Test 3
		R.SetKey("53 65 63 72 65 74");//Secret
		s = binhex(R.Encrypt(hexbin("41 74 74 61 63 6B 32 61 74 32 64 61 77 6E")));//Attack at dawn -> 45A01F645FC35B383552544B9BF5
		s = binhex(R.Decrypt(hexbin(s)));//41747461636B326174326461776E
	}
}
