package com.itgrids.partyanalyst.model;

import java.io.Serializable;

public class BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private volatile Object object;
    private Long id;
   
    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }
               
    public boolean equals(Object obj) {
        final boolean returner;
        if (obj instanceof BaseModel) {
            return getObject().equals(((BaseModel)obj).getObject());
        } else {
            returner = false;
        }
        return returner;
    }
   
    public int hashCode() {
        return getObject().hashCode();
    }
   
    private Object getObject() {
        if (object != null || object == null && id == null) {
            if (object == null) { //Avoid the performance impact of synchronized if we can
                synchronized(this) {
                    if (object == null)
                        object = new Object();
                }
            }
            return object;
        }
        return id;
    }
} 