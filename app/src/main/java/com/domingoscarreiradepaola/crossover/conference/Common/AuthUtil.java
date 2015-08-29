package com.domingoscarreiradepaola.crossover.conference.Common;

import android.content.Context;

import com.domingoscarreiradepaola.crossover.conference.R;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Created by domin on 28/08/2015.
 */
@EBean
public class AuthUtil {

    @RootContext
    public Context context;

    public String getToken(String key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, UnsupportedEncodingException {

        return new CryptLib().encrypt(this.context.getString(R.string.token), key);
    }

    public String getKey() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, UnsupportedEncodingException {

        String key = SharedPreferencesUtil.get(this.context, this.context.getString(R.string.password));

        return key;
    }
}