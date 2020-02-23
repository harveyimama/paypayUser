package com.techland.paypay.user.commands;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.techland.paypay.user.config.Settings;
import com.techland.paypay.user.helper.Constants;
import com.techland.paypay.user.util.LogFeed;


public final class UserSecurity {
	
	
	static String encrypt(final String val)
	{
        StringBuilder sb = new StringBuilder();
        
        try 
        {
            MessageDigest messageDigest = MessageDigest.getInstance(Settings.HASH);
            byte[] bs;
            bs = messageDigest.digest(val.getBytes());
            for(int i = 0; i < bs.length; i++)
            {
                String hexVal = Integer.toHexString(0xFF & bs[i]);
                if(hexVal.length() == 1) 
                {
                    sb.append("0");
                }
                sb.append(hexVal);
            }
        }
        catch (NoSuchAlgorithmException ex) 
        {
        	new LogFeed().getInstance(Constants.ENCRYPT_ERROR,UserSecurity.class,ex.getMessage()).process();
        }
        return sb.toString();
    }
	

}
