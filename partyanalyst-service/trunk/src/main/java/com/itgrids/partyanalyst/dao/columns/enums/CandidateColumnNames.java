/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on August 20, 2009
 */
   
   package com.itgrids.partyanalyst.dao.columns.enums;

  /**
 * Candidate Table Column Name Constants in the form enum. 
 * 
 * @author <a href="mailto:sai.basetti@gmail.com">Sai Krishna</a>
    *
 */

   public enum CandidateColumnNames {

               FIRST_NAME("firstname"),
               MIDDLE_NAME("middlename"),
               LAST_NAME("lastname"),
               EMAIL_ADDRESS("emailAddress"),
               PHONE("phone"),
               MOBILE("mobile"),
               ADDRESS("address"),
               EDUCATION("education"),
               GENDER("gender"), CANDIDATE_ID("candidateId");
 
               private final String value;

               public String getValue() {
                           return value;
               }

               private CandidateColumnNames(String value) {
                              this.value=value;
               }
      }
