package hnpbc.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.xml.bind.DatatypeConverter;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    static public Date str2date(String str) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(str, pos);
        return strtodate;
    }

    static public Integer str2int(String str) {
        if (str == null || "".equals(str.trim())) {
            return null;
        }else {
            try{
                return Integer.parseInt(str);
            }catch (Exception e){
                return null;
            }
        }
    }

    static public String decodeJwt(String webtoken) {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary("gzfxt"))
                .parseClaimsJws(webtoken).getBody();
        return claims.getSubject();
    }
}
