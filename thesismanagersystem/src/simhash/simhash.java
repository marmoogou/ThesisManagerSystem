package simhash;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class simhash implements Serializable {
	 public static int compute(char[] str1, char[] str2)
	    {	
	        int substringLength1 = str1.length;
	        int substringLength2 = str2.length;
	 
	        // �����ά�����¼������A[i]��B[j]��LCS�ĳ���
	        int[][] opt = new int[substringLength1 + 1][substringLength2 + 1];
	 
	        // �Ӻ���ǰ����̬�滮�������������⡣Ҳ�ɴ�ǰ����
	        for (int i = substringLength1 - 1; i >= 0; i--)
	        {
	            for (int j = substringLength2 - 1; j >= 0; j--)
	            {
	                if (str1[i] == str2[j])
	                    opt[i][j] = opt[i + 1][j + 1] + 1;// ״̬ת�Ʒ���
	                else
	                    opt[i][j] = Math.max(opt[i + 1][j], opt[i][j + 1]);// ״̬ת�Ʒ���
	            }
	        }
	        System.out.println("substring1:" + new String(str1));
	        System.out.println("substring2:" + new String(str2));
	        System.out.print("LCS:");
	 
	        int i = 0, j = 0;
	        while (i < substringLength1 && j < substringLength2)
	        {
	            if (str1[i] == str2[j])
	            {
	                System.out.print(str1[i]);
	                i++;
	                j++;
	            }
	            else if (opt[i + 1][j] >= opt[i][j + 1])
	                i++;
	            else
	                j++;
	        }
	        System.out.println();
	        return opt[0][0];
	    }
	 
	    public static boolean compute(String str1, String str2)
	    {
	        if( (double)compute(str1.toCharArray(), str2.toCharArray())/str1.length()>0.8) {
	        	System.out.println(compute(str1.toCharArray(), str2.toCharArray()));
	        	System.out.println(compute(str1.toCharArray(), str2.toCharArray())/str1.length());
	        	
	        	return false;
	        }else {
	        	System.out.println(compute(str1.toCharArray(), str2.toCharArray()));
	        	System.out.println(compute(str1.toCharArray(), str2.toCharArray())/str1.length());
	        	return true;
	        }
	    }
	}
	  


