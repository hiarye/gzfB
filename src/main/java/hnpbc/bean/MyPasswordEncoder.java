package hnpbc.bean;


import hnpbc.bean.crypto.BCryptPasswordEncoder;
import hnpbc.bean.crypto.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyPasswordEncoder {
    private static PasswordEncoder passwordEncoder = null;

    public MyPasswordEncoder() {
        if (passwordEncoder == null) {
            passwordEncoder = new BCryptPasswordEncoder();
        }
    }
    public String encode(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean matches(String password,String encode) {
        return passwordEncoder.matches(password,encode);
    }
}
