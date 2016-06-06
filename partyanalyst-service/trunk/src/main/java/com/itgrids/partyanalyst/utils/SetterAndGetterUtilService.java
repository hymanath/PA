/*
 * Srishailam Pittala
 * SWADHIN-ITGRIDS
 * DATE:2-6-2016
 */
package com.itgrids.partyanalyst.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

public class SetterAndGetterUtilService {
	private static final Logger   LOG = Logger.getLogger(SetterAndGetterUtilService.class);
	
	/*
	 * Srishailam Pittala 
	 * SWADHIN-ITGRIDS
	 * DATE:2-6-2016
	 * inputs : List<Object[]>, setting Properties Name List in VO , setting fully Qualified VO Name
	 * output List<VO> fully Qualified VO Name like "com.itgrids.partyanalyst.dto.ActivityVO"
	 * */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List setValuesToVO(List<Object[]> resultList,String[] setterPropertiesList,String settingVOClassName){
		Object voObject ="";
		List returnList = new ArrayList();
		try{
			ClassLoader classLoader = this.getClass().getClassLoader();
			Class voClass = classLoader.loadClass(settingVOClassName);
			Constructor voConstructor = voClass.getConstructor();
			voObject = voConstructor.newInstance();
			
			List<String> parameterList = Arrays.asList(setterPropertiesList);
			if(resultList != null && resultList.size()>0){
				for (Object[] param : resultList) {
					for(int i=0;i<param.length;i++){
						String fieldName = parameterList.get(i);
						if(param[i]!=null)
							set(voObject,fieldName,param[i].toString());
						else
							set(voObject,fieldName,"");
					}
					returnList.add(voObject);
					voObject = voConstructor.newInstance();
				}
			}
		}catch(Exception e){
			LOG.error("Error Occured while setting values to "+voObject+" Object",e);
			returnList = null;
		}
		return returnList;
	}
	
	/*
	 * set method will set one value to the field of vo class, But all of these dynamically.
	 * First argument represents vo class object,
	 * Second argument represents appropriate field and
	 * third argument represents value need to be set.
	 */
	public void set(Object object, String fieldName, String fieldValue){
		Class<?> clazz = object.getClass();
	    if (clazz != null) {
	        try {
	            Field field = clazz.getDeclaredField(fieldName);
	            field.setAccessible(true);
	            Type type = field.getType();
	            /*if the data type of field and the value which is going to set to the field
	             * is string then if condition is true so,
	             * set method will work without exception. But if the value which is going to set
	             * is String and the data type of field is not String, set() method of Field class will throw exception, so
	             * we need to convert the value to the field type, so to modify the data type call modify() method.
	             * That method will take care all these things.
	             *
	             */
	            if(type.equals(String.class)){
	            	field.set(object, fieldValue);
	            	
	            }else{
	            	Object modifiedFieldValue = modify(type, fieldValue);
	            	field.set(object, modifiedFieldValue);
	            }
	        } catch (NoSuchFieldException e) {
	            clazz = clazz.getSuperclass();
	        	LOG.error("",e);
	        } catch (Exception e) {
	            throw new IllegalStateException(e);
	        }
	    }
	}
	
	/*
     * type parameter holds the information of field, check the type  of field
     * if type is Double the change the data type of second parameter to Double, like this
     * check for all wrapper classes in java. So I have written eight conditions.
     * finally this method will return same value with modified data type.
     */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> T modify(Type type, String fieldValue) {
        T ret = null;
        Constructor constructor=null;
        
        try {
        	//1.wraper Class Double
        	if((type.equals(Double.class))){
        		constructor = Double.class.getConstructor(new Class[]{String.class});
        		if(fieldValue.length()==0)
        			ret=(T)constructor.newInstance("0");
        		else
        			ret=(T)constructor.newInstance(fieldValue);
        	}
        	//2.wraper Class Long
        	else if((type.equals(Long.class))){
        		constructor = Long.class.getConstructor(new Class[]{String.class});
        		if(fieldValue.length()==0)
        			ret=(T)constructor.newInstance("0");
        		else
        			ret=(T)constructor.newInstance(fieldValue);
        	}
        	//3.wraper Class Float
        	else if((type.equals(Float.class))){
        		constructor = Float.class.getConstructor(new Class[]{String.class});
        		if(fieldValue.length()==0)
        			ret=(T)constructor.newInstance("0");
        		else
        			ret=(T)constructor.newInstance(fieldValue);
        	}
        	//4.wraper Class Integer
        	else if((type.equals(Integer.class))){
        		constructor = Integer.class.getConstructor(new Class[]{String.class});
        		if(fieldValue.length()==0)
        			ret=(T)constructor.newInstance("0");
        		else
        			ret=(T)constructor.newInstance(fieldValue);
        	}
        	//5.wraper Class Boolean
        	else if((type.equals(Boolean.class))){
        		constructor = Boolean.class.getConstructor(new Class[]{String.class});
            	ret=(T)constructor.newInstance(fieldValue);
        	}
        	//6.wraper Class Byte
        	else if((type.equals(Byte.class))){
        		constructor = Byte.class.getConstructor(new Class[]{String.class});
            	ret=(T)constructor.newInstance(fieldValue);
        	}
        	//7.wraper Class Short
        	else if((type.equals(Short.class))){
        		constructor = Short.class.getConstructor(new Class[]{String.class});
            	ret=(T)constructor.newInstance(fieldValue);
        	}
        	//8.wraper Class Character
        	else if((type.equals(Character.class))){
        		constructor = Character.class.getConstructor(new Class[]{String.class});
            	ret=(T)constructor.newInstance(fieldValue);
        	}
            return ret;
        } catch (Exception  e) {
        	LOG.error("",e);
        }
		return ret;
	}
}
