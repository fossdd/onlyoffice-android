package app.editors.manager.mvp.presenters.login;

import android.content.Intent;

import app.editors.manager.R;
import app.editors.manager.app.App;
import app.editors.manager.mvp.models.account.AccountsSqlData;
import app.editors.manager.mvp.models.request.RequestSignIn;
import app.editors.manager.mvp.models.response.ResponseSignIn;
import app.editors.manager.mvp.models.user.User;
import app.editors.manager.mvp.views.login.CommonSignInView;
import lib.toolkit.base.managers.utils.StringUtils;
import moxy.InjectViewState;

@InjectViewState
public class EnterpriseLoginPresenter extends BaseLoginPresenter<CommonSignInView, ResponseSignIn> {

    public static final String TAG = EnterpriseLoginPresenter.class.getSimpleName();

    public static final String TAG_DIALOG_WAITING = "TAG_DIALOG_WAITING";
    public static final String TAG_DIALOG_LOGIN_FACEBOOK = "TAG_DIALOG_LOGIN_FACEBOOK";

    public EnterpriseLoginPresenter() {
        App.getApp().getAppComponent().inject(this);
    }

    @Override
    protected void onTwoFactorAuth(boolean isPhone, AccountsSqlData sqlData) {
        super.onTwoFactorAuth(isPhone, sqlData);
        getViewState().onTwoFactorAuth(isPhone, sqlData);
    }

    @Override
    protected void onGetUser(User user) {
        super.onGetUser(user);
        getViewState().onSuccessLogin();
    }

    @Override
    protected void onTwoFactorAuthApp(boolean isSecret, AccountsSqlData sqlData) {
        super.onTwoFactorAuthApp(isSecret, sqlData);
        getViewState().onTwoFactorAuthTfa(isSecret, sqlData);
    }

    @Override
    protected void onGooglePermission(Intent intent) {
        super.onGooglePermission(intent);
        getViewState().onGooglePermission(intent);
    }

    public void signInPortal(final String login, final String password, final String portal) {
//        mPreferenceTool.setLogin(login);
        if (!StringUtils.isEmailValid(login)) {
            getViewState().onEmailNameError(mContext.getString(R.string.errors_email_syntax_error));
            return;
        }

        getViewState().onWaitingDialog(mContext.getString(R.string.dialogs_sign_in_portal_header_text), TAG_DIALOG_WAITING);
        final RequestSignIn requestSignIn = new RequestSignIn();
        requestSignIn.setUserName(login);
        requestSignIn.setPassword(password);
        signIn(requestSignIn, portal);
    }

}
