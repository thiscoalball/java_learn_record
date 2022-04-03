import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

public class ShiroMD5Test {
    @Test
    public void showMD5(){
        //不加盐
        //4297f44b13955235245b2497399d7a93
        Md5Hash md5Hash01 = new Md5Hash("123123");
        System.out.println(md5Hash01.toHex());

        // MD5+随机盐加密，无散列
        //3d17ec91339be67055dc64482f611912
        Md5Hash md5Hash02 = new Md5Hash("123123","1a2b3c4d");
        System.out.println(md5Hash02.toHex());

        // MD5+随机盐加密+散列1024
        //883f4ab001cc3f258a2f0c3c3bc68288
        Md5Hash md5Hash03 = new Md5Hash("123123","1a2b3c4d",1024);
        System.out.println(md5Hash03.toHex());
    }
}
