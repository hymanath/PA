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

import java.util.Date;
import java.util.List;

import com.itgrids.partyanalyst.model.Blog;
import com.itgrids.partyanalyst.service.IBlogService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.conversion.annotations.Conversion;
import com.opensymphony.xwork2.conversion.annotations.TypeConversion;
import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validation;

@Validation()
@Conversion()
public class HelloWorldAction extends ActionSupport {
    
	private static final long serialVersionUID = 1L;
	
	private Date now;
    private String name;
    
    private IBlogService blogService;
    
    public IBlogService getBlogService() {
		return blogService;
	}
	public void setBlogService(IBlogService blogService) {
		this.blogService = blogService;
	}
	@TypeConversion(converter = "com.itgrids.partyanalyst.DateConverter")
    @RequiredFieldValidator(message = "Please enter the date")
    public void setDateNow(Date now) { this.now = now; }
    public Date getDateNow() { return now; }
   
    @RequiredStringValidator(message = "Please enter a name", trim = true)
    public void setName(String name) { this.name = name; }
    public String getName() { return this.name; }
    
    public String execute() throws Exception {
    	
    	List<Blog> blogs = getBlogService().getAll();
    	for(Blog blog:blogs){
    		System.out.println(blog);
    	}
        return SUCCESS;
    }
}
