/*
 * Copyright 2006 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.itgrids.partyanalyst;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

/**
 * 
 */
public class DateConverter extends StrutsTypeConverter {
    public Object convertFromString(Map context, String[] values, Class toClass) {
        if (values != null && values.length > 0 && values[0] != null && values[0].length() > 0) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            try {
                return sdf.parse(values[0]);
            }
            catch(ParseException e) {
                ;//TODO: do somehting here later
            }
        }
        return null;
    }
    public String convertToString(Map context, Object o) {
        if (o instanceof Date) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            return sdf.format((Date)o);
        }
        return "";
    }
}

