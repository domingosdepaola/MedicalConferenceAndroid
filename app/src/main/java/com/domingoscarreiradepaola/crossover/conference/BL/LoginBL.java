package com.domingoscarreiradepaola.crossover.conference.BL;

import android.content.Context;

import com.domingoscarreiradepaola.crossover.conference.Common.CryptLib;
import com.domingoscarreiradepaola.crossover.conference.Common.SharedPreferencesUtil;
import com.domingoscarreiradepaola.crossover.conference.Common.StringUtil;
import com.domingoscarreiradepaola.crossover.conference.DAO.DatabaseHelper;
import com.domingoscarreiradepaola.crossover.conference.Entity.User;
import com.domingoscarreiradepaola.crossover.conference.Interfaces.IUserDao;
import com.domingoscarreiradepaola.crossover.conference.Interfaces.IUserProfileDao;
import com.domingoscarreiradepaola.crossover.conference.R;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.OrmLiteDao;
import org.androidannotations.annotations.RootContext;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Created by domin on 29/08/2015.
 */
@EBean
public class LoginBL {
    //Business Logic
    @OrmLiteDao(helper = DatabaseHelper.class)
    public IUserDao userDao;

    @OrmLiteDao(helper = DatabaseHelper.class)
    public IUserProfileDao profileDao;

    @RootContext
    public Context context;

    public String EncriptPassword(String password) {
        String criptoPassword = "";
        try {
            criptoPassword = new CryptLib().encrypt(password, CryptLib.DEFAULT_KEY);
        } catch (Exception ex) {

        }
        return criptoPassword;
    }
    public boolean login(String userName, String password) throws SQLException, IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException {

        String criptoPassword = EncriptPassword(password);

        List<User> listUsuario = this.userDao.getByUserNameAndPassword(userName, criptoPassword);

        boolean login = listUsuario.size() > 0;

        if (login) {

            String registrationId;

            String savedUser = SharedPreferencesUtil.get(this.context, this.context.getString(R.string.user_id));
            int savedUserId = 0;

            if (!StringUtil.isNullOrEmpty(savedUser))
                savedUserId = Integer.valueOf(savedUser);

            User usuario = listUsuario.get(0);

            if (savedUserId != usuario.Id) {

                this.refresh(usuario);

                SharedPreferencesUtil.save(this.context, this.context.getString(R.string.get_gcm_id), String.valueOf(true));
                SharedPreferencesUtil.save(this.context, this.context.getString(R.string.user_id), String.valueOf(usuario.Id));
                SharedPreferencesUtil.save(this.context, this.context.getString(R.string.password), criptoPassword);
                SharedPreferencesUtil.save(this.context, this.context.getString(R.string.user), this.seriealizeUser(usuario));
            }
        }

        return login;
    }

    private String seriealizeUser(User user) throws IOException {

        ObjectMapper objMapper = new ObjectMapper();
        String jsonString = objMapper.writeValueAsString(user);

        return jsonString;
    }

    public User getLoggedUser() {

        String jsonString = SharedPreferencesUtil.get(this.context, this.context.getString(R.string.user));
        User loggedUser = null;

        try {
            loggedUser = new ObjectMapper().readValue(jsonString, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return loggedUser;
    }

    public void logout() throws SQLException {
        int userId = Integer.valueOf(SharedPreferencesUtil.get(this.context, this.context.getString(R.string.user_id)));

        SharedPreferencesUtil.save(this.context, this.context.getString(R.string.get_gcm_id), String.valueOf(true));
        SharedPreferencesUtil.remove(this.context, this.context.getString(R.string.user_id));
        SharedPreferencesUtil.remove(this.context, this.context.getString(R.string.password));

        this.cleanIdGcm(userId);
    }

    private void cleanIdGcm(int userId) throws SQLException {

        if (userId != 0) {

            User user = this.userDao.queryForId(userId);
            user.IdGcm = null;

            this.userDao.update(user);
        }
    }

    private void refresh(User user) throws SQLException {

        this.profileDao.refresh(user.UserProfile);
    }
}
