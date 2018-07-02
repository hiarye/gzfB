package hnpbc.controller.login;

import hnpbc.bean.WebToken;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

@RestController
@ResponseBody
public class JWTController {

    @RequestMapping(value = "/getJwt",method = {RequestMethod.POST,RequestMethod.GET})
    public WebToken getJwt(HttpServletRequest request){
        System.out.println("This is getJwt");
        HttpSession session = request.getSession();
        WebToken bean = new WebToken();
        System.out.println(session.getId());
        if(session == null || session.getAttribute("username")==null || "".equals(session.getAttribute("username").toString().trim())){
            bean.setExpire("");
            bean.setData("");
            return bean;
        }
        byte[] keySecretBytes = DatatypeConverter.parseBase64Binary("test");
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
        Key signingKey = new SecretKeySpec(keySecretBytes, signatureAlgorithm.getJcaName());

        String compactJws = Jwts.builder()
                .setSubject("huangzw")
                .signWith(SignatureAlgorithm.HS512,signingKey)
                .compact();
        bean.setExpire("");
        bean.setData(compactJws);

        System.out.println(Jwts.parser().setSigningKey(signingKey).parseClaimsJws(compactJws).getBody().getSubject());
        return bean;
    }

    @RequestMapping(value = "/checkJwt",method = {RequestMethod.POST,RequestMethod.GET})
    public WebToken checkJwt(HttpServletRequest request){
        String token = request.getHeader("Authorization");

        WebToken bean = new WebToken();

        if (token==null || "".equals(token.trim())) {
            return bean;
        } else {
            System.out.println(token);
            return bean;
        }
    }
}
