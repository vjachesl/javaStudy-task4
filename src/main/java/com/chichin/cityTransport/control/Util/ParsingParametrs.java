package com.chichin.cityTransport.control.Util;

import org.apache.log4j.Logger;

import java.util.*;

/**
 * Created by viacheslav on 20.06.15.
 */
public class ParsingParametrs {
    private static final Logger LOG = Logger.getLogger(ParsingParametrs.class);

    public static List<String> parseParametres(Enumeration param){
        List<String> res = new ArrayList<String>();
        while (param.hasMoreElements())
                res.add((String) param.nextElement());
        LOG.debug("Was parsed parametres and found "+res.size());
        return res;
    }
}
