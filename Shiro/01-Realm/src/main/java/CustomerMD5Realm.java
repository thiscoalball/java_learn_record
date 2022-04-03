import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class CustomerMD5Realm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String principal = (String) token.getPrincipal();
        if("jason".equals(principal)){
            /**
             * 用户名
             * 加密后的密码
             * 随机盐
             * 当前realm的名称
             */
            return new SimpleAuthenticationInfo(principal,
                    "883f4ab001cc3f258a2f0c3c3bc68288",
                    ByteSource.Util.bytes("1a2b3c4d"),
                    this.getName());
        }
        return null;
    }
}
