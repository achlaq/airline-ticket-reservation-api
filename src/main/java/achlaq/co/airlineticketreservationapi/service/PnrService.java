package achlaq.co.airlineticketreservationapi.service;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.function.Function;

@Service
public class PnrService {

    private static final String ALPHANUM = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";
    private final SecureRandom rnd = new SecureRandom();

    public String generateUnique(Function<String,Boolean> existsFn, int len){
        String p;
        do {
            var sb = new StringBuilder(len);
            for(int i=0;i<len;i++) sb.append(ALPHANUM.charAt(rnd.nextInt(ALPHANUM.length())));
            p = sb.toString();
        } while (existsFn.apply(p));
        return p;
    }
}
