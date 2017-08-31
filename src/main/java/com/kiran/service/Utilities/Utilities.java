package com.kiran.service.Utilities;

import org.springframework.stereotype.Component;

/**
 * @author Kiran
 * @since 8/29/17
 */

@Component
public class Utilities {

    public Utilities() {
    }

    public String trimString(String str, int trim_size)
    {
       return str.substring(trim_size, str.length()-trim_size);
    }


}
