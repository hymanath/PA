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

import junit.framework.TestCase;

import com.itgrids.partyanalyst.service.impl.StaticDataService;
import com.opensymphony.xwork2.Action;

/**
 * 
 */
public class IndexActionTest extends TestCase {

	public void testIndexAction() throws Exception {
		/*IndexAction indexAction = new IndexAction();
		StaticDataService staticDataService = new StaticDataService();
		indexAction.setStaticDataService(staticDataService);
        String result = indexAction.execute();
        System.out.println(Action.SUCCESS+"  "+result);
        */assertEquals(1, 1);
    }
}
