CREATE PROCEDURE CENSUS_DISTRICT_MANAGER ()
BEGIN

   declare      done int(1) default 0;
   declare  	l_state_code	int(10)	;
   declare  	l_district_code	int(10)	;
   declare  	l_tehsil_code	int(10)	;
   declare      l_name varchar(100);
   declare      l_level varchar(10);
   declare  	v_count	int(4)	;
   declare      insert_count int(4) default 0;
   declare      update_count int(4) default 0;

   declare cur_update_state cursor for 
                        SELECT DISTINCT name, state, district, tehsil, level FROM temp_census_master where level = 'DISTRICT';

   DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;
  
   OPEN cur_update_state;
    select  FOUND_ROWS() 'DISTRICTS FETCHED FROM TEMP_CENSUS_MASTER';
    REPEAT
        FETCH cur_update_state INTO  l_name, l_state_code, l_district_code, l_tehsil_code,  l_level;

            IF NOT done THEN
                    SELECT count(*) INTO v_count FROM district WHERE district_name = l_name;
                    IF v_count = 0 THEN
                        INSERT INTO district (district_name,
                                              district_code,
                                              state_id) 
                                     VALUES (l_name,
                                             l_district_code,
                                             (SELECT state_id FROM state WHERE state_code = l_state_code));
                        set insert_count = insert_count + 1;
                    ELSE
                        UPDATE district SET district_code = l_district_code WHERE district_name = l_name;
                        set update_count = update_count + 1;
                    END IF;
         END IF;
    UNTIL done END REPEAT;
    CLOSE cur_update_state;

    SELECT  insert_count 'ROWS INSERTED TO District' ,  update_count 'ROWS UPDATED' ;
END
GO
CREATE PROCEDURE CENSUS_MANAGER ()
BEGIN

   declare      done int(1) default 0;
   declare      done_town int(1) default 0;
   declare      v_census_year int(4) default 2001;
   declare  	l_STATE	int(2)	;
   declare      l_name varchar(100);
   declare      l_tehsil bigint(15);
   declare      l_district bigint(15);
   declare      l_town_vill bigint(15);
   declare      l_level varchar(10);
   declare      l_tehsil_id bigint(15);
   declare      l_ward int(10);
   declare      l_tehsil_code bigint(15);
   declare      l_township_id bigint(15);
   declare      l_town_vill_ind char(1);
   declare      temp_count int(10);
   declare      temp_name varchar(100);
   declare  	v_state_count	int(4)	;
   declare      state_count int(4) default 0;
   declare      district_count int(4) default 0;
   declare      tehsil_count int(4) default 0;
   declare      township_count int(4) default 0;
   declare      ward_count int(4) default 0;
   declare      census_count int(4) default 0;


    CALL CENSUS_STATE_MANAGER;
    CALL CENSUS_DISTRICT_MANAGER;
    CALL CENSUS_TEHSIL_MANAGER;
    CALL CENSUS_TOWNSHIP_MANAGER;
    CALL CENSUS_WARD_MANAGER;

    
    INSERT INTO census_master ( STATE_ID, LEVEL,  YEAR,   TRU,  
                             No_HH,  TOT_P,  TOT_M,  TOT_F,  P_06,  M_06,  F_06, 
                             P_SC,  M_SC,  F_SC,  P_ST,  M_ST,  F_ST,  
                             P_LIT,  M_LIT,  F_LIT,  P_ILL,  M_ILL,  F_ILL,  
                             TOT_WORK_P,  TOT_WORK_M,  TOT_WORK_F,
                             MAINWORK_P,  MAINWORK_M,  MAINWORK_F,
                             MAIN_CL_P,  MAIN_CL_M, MAIN_CL_F,
                             MAIN_AL_P,  MAIN_AL_M,  MAIN_AL_F,
                             MAIN_HH_P,  MAIN_HH_M,  MAIN_HH_F,
                             MAIN_OT_P,  MAIN_OT_M,  MAIN_OT_F,
                             MARGWORK_P,  MARGWORK_M,  MARGWORK_F,
                             MARG_CL_P,  MARG_CL_M,  MARG_CL_F,
                             MARG_AL_P,  MARG_AL_M,  MARG_AL_F,
                             MARG_HH_P,  MARG_HH_M,  MARG_HH_F,
                             MARG_OT_P,  MARG_OT_M,  MARG_OT_F,
                             NON_WORK_P,  NON_WORK_M,  NON_WORK_F,
                             SEXRATIO, SEXRATIO_SC,  SEXRATIO_ST,  SEXRATIO_06,  
                             HH_SIZE,  SC_PER,  ST_PER,
                             M_LIT_RATE,   F_LIT_RATE,  GENDER_GAP,
                             P_LIT_PER,  M_LIT_PER,  F_LIT_PER,
                             TOT_P_PER,  TOT_WOR_PER_P,  TOT_WOR_PER_M,  TOT_WOR_PER_F,  
                             TOT_MAIN_PER_P,  TOT_MAIN_PER_M,  TOT_MAIN_PER_F,
                             TOT_MARG_PER_P,  TOT_MARG_PER_M,  TOT_MARG_PER_F,
                             NON_WORK_PER_P,  NON_WORK_PER_M,  NON_WORK_PER_F,
                             CL_PER_P,  CL_PER_M,  CL_PER_F,  AL_PER_P,  AL_PER_M,  AL_PER_F,
                             HH_PER_P,  HH_PER_M,  HH_PER_F,  OW_PER_P,  OW_PER_M,  OW_PER_F,
                             CL_MAIN_MARG_P,  CL_MAIN_MARG_M,  CL_MAIN_MARG_F,
                             AL_MAIN_MARG_P,  AL_MAIN_MARG_M,  AL_MAIN_MARG_F,
                             HH_MAIN_MARG_P,  HH_MAIN_MARG_M,  HH_MAIN_MARG_F,
                             OW_MAIN_MARG_P,  OW_MAIN_MARG_M,  OW_MAIN_MARG_F,  WPR )
                      SELECT state_id, LEVEL,  v_census_year,   TRU,  
                             No_HH,  TOT_P,  TOT_M,  TOT_F,  P_06,  M_06,  F_06, 
                             P_SC,  M_SC,  F_SC,  P_ST,  M_ST,  F_ST,  
                             P_LIT,  M_LIT,  F_LIT,  P_ILL,  M_ILL,  F_ILL,  
                             TOT_WORK_P,  TOT_WORK_M,  TOT_WORK_F,
                             MAINWORK_P,  MAINWORK_M,  MAINWORK_F,
                             MAIN_CL_P,  MAIN_CL_M, MAIN_CL_F,
                             MAIN_AL_P,  MAIN_AL_M,  MAIN_AL_F,
                             MAIN_HH_P,  MAIN_HH_M,  MAIN_HH_F,
                             MAIN_OT_P,  MAIN_OT_M,  MAIN_OT_F,
                             MARGWORK_P,  MARGWORK_M,  MARGWORK_F,
                             MARG_CL_P,  MARG_CL_M,  MARG_CL_F,
                             MARG_AL_P,  MARG_AL_M,  MARG_AL_F,
                             MARG_HH_P,  MARG_HH_M,  MARG_HH_F,
                             MARG_OT_P,  MARG_OT_M,  MARG_OT_F,
                             NON_WORK_P,  NON_WORK_M,  NON_WORK_F,
                             SEXRATIO, SEXRATIO_SC,  SEXRATIO_ST,  SEXRATIO_06,  
                             HH_SIZE,  SC_PER,  ST_PER,
                             M_LIT_RATE,   F_LIT_RATE,  GENDER_GAP,
                             P_LIT_PER,  M_LIT_PER,  F_LIT_PER,
                             TOT_P_PER,  TOT_WOR_PER_P,  TOT_WOR_PER_M,  TOT_WOR_PER_F,  
                             TOT_MAIN_PER_P,  TOT_MAIN_PER_M,  TOT_MAIN_PER_F,
                             TOT_MARG_PER_P,  TOT_MARG_PER_M,  TOT_MARG_PER_F,
                             NON_WORK_PER_P,  NON_WORK_PER_M,  NON_WORK_PER_F,
                             CL_PER_P,  CL_PER_M,  CL_PER_F,  AL_PER_P,  AL_PER_M,  AL_PER_F,
                             HH_PER_P,  HH_PER_M,  HH_PER_F,  OW_PER_P,  OW_PER_M,  OW_PER_F,
                             CL_MAIN_MARG_P,  CL_MAIN_MARG_M,  CL_MAIN_MARG_F,
                             AL_MAIN_MARG_P,  AL_MAIN_MARG_M,  AL_MAIN_MARG_F,
                             HH_MAIN_MARG_P,  HH_MAIN_MARG_M,  HH_MAIN_MARG_F,
                             OW_MAIN_MARG_P,  OW_MAIN_MARG_M,  OW_MAIN_MARG_F,  WPR 
                      FROM   temp_census_master census, state
                      WHERE  state_code = STATE
                      AND    LEVEL IN ('STATE');
 
                      set state_count = found_rows();
                       INSERT INTO census_master (  DISTRICT_ID, LEVEL,  YEAR,   TRU,  
                             No_HH,  TOT_P,  TOT_M,  TOT_F,  P_06,  M_06,  F_06, 
                             P_SC,  M_SC,  F_SC,  P_ST,  M_ST,  F_ST,  
                             P_LIT,  M_LIT,  F_LIT,  P_ILL,  M_ILL,  F_ILL,  
                             TOT_WORK_P,  TOT_WORK_M,  TOT_WORK_F,
                             MAINWORK_P,  MAINWORK_M,  MAINWORK_F,
                             MAIN_CL_P,  MAIN_CL_M, MAIN_CL_F,
                             MAIN_AL_P,  MAIN_AL_M,  MAIN_AL_F,
                             MAIN_HH_P,  MAIN_HH_M,  MAIN_HH_F,
                             MAIN_OT_P,  MAIN_OT_M,  MAIN_OT_F,
                             MARGWORK_P,  MARGWORK_M,  MARGWORK_F,
                             MARG_CL_P,  MARG_CL_M,  MARG_CL_F,
                             MARG_AL_P,  MARG_AL_M,  MARG_AL_F,
                             MARG_HH_P,  MARG_HH_M,  MARG_HH_F,
                             MARG_OT_P,  MARG_OT_M,  MARG_OT_F,
                             NON_WORK_P,  NON_WORK_M,  NON_WORK_F,
                             SEXRATIO, SEXRATIO_SC,  SEXRATIO_ST,  SEXRATIO_06,  
                             HH_SIZE,  SC_PER,  ST_PER,
                             M_LIT_RATE,   F_LIT_RATE,  GENDER_GAP,
                             P_LIT_PER,  M_LIT_PER,  F_LIT_PER,
                             TOT_P_PER,  TOT_WOR_PER_P,  TOT_WOR_PER_M,  TOT_WOR_PER_F,  
                             TOT_MAIN_PER_P,  TOT_MAIN_PER_M,  TOT_MAIN_PER_F,
                             TOT_MARG_PER_P,  TOT_MARG_PER_M,  TOT_MARG_PER_F,
                             NON_WORK_PER_P,  NON_WORK_PER_M,  NON_WORK_PER_F,
                             CL_PER_P,  CL_PER_M,  CL_PER_F,  AL_PER_P,  AL_PER_M,  AL_PER_F,
                             HH_PER_P,  HH_PER_M,  HH_PER_F,  OW_PER_P,  OW_PER_M,  OW_PER_F,
                             CL_MAIN_MARG_P,  CL_MAIN_MARG_M,  CL_MAIN_MARG_F,
                             AL_MAIN_MARG_P,  AL_MAIN_MARG_M,  AL_MAIN_MARG_F,
                             HH_MAIN_MARG_P,  HH_MAIN_MARG_M,  HH_MAIN_MARG_F,
                             OW_MAIN_MARG_P,  OW_MAIN_MARG_M,  OW_MAIN_MARG_F,  WPR )
                      SELECT DISTINCT district_id, LEVEL,  v_census_year,   TRU,  
                             No_HH,  TOT_P,  TOT_M,  TOT_F,  P_06,  M_06,  F_06, 
                             P_SC,  M_SC,  F_SC,  P_ST,  M_ST,  F_ST,  
                             P_LIT,  M_LIT,  F_LIT,  P_ILL,  M_ILL,  F_ILL,  
                             TOT_WORK_P,  TOT_WORK_M,  TOT_WORK_F,
                             MAINWORK_P,  MAINWORK_M,  MAINWORK_F,
                             MAIN_CL_P,  MAIN_CL_M, MAIN_CL_F,
                             MAIN_AL_P,  MAIN_AL_M,  MAIN_AL_F,
                             MAIN_HH_P,  MAIN_HH_M,  MAIN_HH_F,
                             MAIN_OT_P,  MAIN_OT_M,  MAIN_OT_F,
                             MARGWORK_P,  MARGWORK_M,  MARGWORK_F,
                             MARG_CL_P,  MARG_CL_M,  MARG_CL_F,
                             MARG_AL_P,  MARG_AL_M,  MARG_AL_F,
                             MARG_HH_P,  MARG_HH_M,  MARG_HH_F,
                             MARG_OT_P,  MARG_OT_M,  MARG_OT_F,
                             NON_WORK_P,  NON_WORK_M,  NON_WORK_F,
                             SEXRATIO, SEXRATIO_SC,  SEXRATIO_ST,  SEXRATIO_06,  
                             HH_SIZE,  SC_PER,  ST_PER,
                             M_LIT_RATE,   F_LIT_RATE,  GENDER_GAP,
                             P_LIT_PER,  M_LIT_PER,  F_LIT_PER,
                             TOT_P_PER,  TOT_WOR_PER_P,  TOT_WOR_PER_M,  TOT_WOR_PER_F,  
                             TOT_MAIN_PER_P,  TOT_MAIN_PER_M,  TOT_MAIN_PER_F,
                             TOT_MARG_PER_P,  TOT_MARG_PER_M,  TOT_MARG_PER_F,
                             NON_WORK_PER_P,  NON_WORK_PER_M,  NON_WORK_PER_F,
                             CL_PER_P,  CL_PER_M,  CL_PER_F,  AL_PER_P,  AL_PER_M,  AL_PER_F,
                             HH_PER_P,  HH_PER_M,  HH_PER_F,  OW_PER_P,  OW_PER_M,  OW_PER_F,
                             CL_MAIN_MARG_P,  CL_MAIN_MARG_M,  CL_MAIN_MARG_F,
                             AL_MAIN_MARG_P,  AL_MAIN_MARG_M,  AL_MAIN_MARG_F,
                             HH_MAIN_MARG_P,  HH_MAIN_MARG_M,  HH_MAIN_MARG_F,
                             OW_MAIN_MARG_P,  OW_MAIN_MARG_M,  OW_MAIN_MARG_F,  WPR 
                      FROM   district, temp_census_master census
                      WHERE  census.DISTRICT = district_code
                      AND    LEVEL = 'DISTRICT';

                      set district_count = found_rows();

                       INSERT INTO census_master ( TEHSIL_ID, LEVEL,  YEAR,   TRU,  
                             No_HH,  TOT_P,  TOT_M,  TOT_F,  P_06,  M_06,  F_06, 
                             P_SC,  M_SC,  F_SC,  P_ST,  M_ST,  F_ST,  
                             P_LIT,  M_LIT,  F_LIT,  P_ILL,  M_ILL,  F_ILL,  
                             TOT_WORK_P,  TOT_WORK_M,  TOT_WORK_F,
                             MAINWORK_P,  MAINWORK_M,  MAINWORK_F,
                             MAIN_CL_P,  MAIN_CL_M, MAIN_CL_F,
                             MAIN_AL_P,  MAIN_AL_M,  MAIN_AL_F,
                             MAIN_HH_P,  MAIN_HH_M,  MAIN_HH_F,
                             MAIN_OT_P,  MAIN_OT_M,  MAIN_OT_F,
                             MARGWORK_P,  MARGWORK_M,  MARGWORK_F,
                             MARG_CL_P,  MARG_CL_M,  MARG_CL_F,
                             MARG_AL_P,  MARG_AL_M,  MARG_AL_F,
                             MARG_HH_P,  MARG_HH_M,  MARG_HH_F,
                             MARG_OT_P,  MARG_OT_M,  MARG_OT_F,
                             NON_WORK_P,  NON_WORK_M,  NON_WORK_F,
                             SEXRATIO, SEXRATIO_SC,  SEXRATIO_ST,  SEXRATIO_06,  
                             HH_SIZE,  SC_PER,  ST_PER,
                             M_LIT_RATE,   F_LIT_RATE,  GENDER_GAP,
                             P_LIT_PER,  M_LIT_PER,  F_LIT_PER,
                             TOT_P_PER,  TOT_WOR_PER_P,  TOT_WOR_PER_M,  TOT_WOR_PER_F,  
                             TOT_MAIN_PER_P,  TOT_MAIN_PER_M,  TOT_MAIN_PER_F,
                             TOT_MARG_PER_P,  TOT_MARG_PER_M,  TOT_MARG_PER_F,
                             NON_WORK_PER_P,  NON_WORK_PER_M,  NON_WORK_PER_F,
                             CL_PER_P,  CL_PER_M,  CL_PER_F,  AL_PER_P,  AL_PER_M,  AL_PER_F,
                             HH_PER_P,  HH_PER_M,  HH_PER_F,  OW_PER_P,  OW_PER_M,  OW_PER_F,
                             CL_MAIN_MARG_P,  CL_MAIN_MARG_M,  CL_MAIN_MARG_F,
                             AL_MAIN_MARG_P,  AL_MAIN_MARG_M,  AL_MAIN_MARG_F,
                             HH_MAIN_MARG_P,  HH_MAIN_MARG_M,  HH_MAIN_MARG_F,
                             OW_MAIN_MARG_P,  OW_MAIN_MARG_M,  OW_MAIN_MARG_F,  WPR )
                      SELECT DISTINCT tehsil_id,  LEVEL,  v_census_year,   TRU,  
                             No_HH,  TOT_P,  TOT_M,  TOT_F,  P_06,  M_06,  F_06, 
                             P_SC,  M_SC,  F_SC,  P_ST,  M_ST,  F_ST,  
                             P_LIT,  M_LIT,  F_LIT,  P_ILL,  M_ILL,  F_ILL,  
                             TOT_WORK_P,  TOT_WORK_M,  TOT_WORK_F,
                             MAINWORK_P,  MAINWORK_M,  MAINWORK_F,
                             MAIN_CL_P,  MAIN_CL_M, MAIN_CL_F,
                             MAIN_AL_P,  MAIN_AL_M,  MAIN_AL_F,
                             MAIN_HH_P,  MAIN_HH_M,  MAIN_HH_F,
                             MAIN_OT_P,  MAIN_OT_M,  MAIN_OT_F,
                             MARGWORK_P,  MARGWORK_M,  MARGWORK_F,
                             MARG_CL_P,  MARG_CL_M,  MARG_CL_F,
                             MARG_AL_P,  MARG_AL_M,  MARG_AL_F,
                             MARG_HH_P,  MARG_HH_M,  MARG_HH_F,
                             MARG_OT_P,  MARG_OT_M,  MARG_OT_F,
                             NON_WORK_P,  NON_WORK_M,  NON_WORK_F,
                             SEXRATIO, SEXRATIO_SC,  SEXRATIO_ST,  SEXRATIO_06,  
                             HH_SIZE,  SC_PER,  ST_PER,
                             M_LIT_RATE,   F_LIT_RATE,  GENDER_GAP,
                             P_LIT_PER,  M_LIT_PER,  F_LIT_PER,
                             TOT_P_PER,  TOT_WOR_PER_P,  TOT_WOR_PER_M,  TOT_WOR_PER_F,  
                             TOT_MAIN_PER_P,  TOT_MAIN_PER_M,  TOT_MAIN_PER_F,
                             TOT_MARG_PER_P,  TOT_MARG_PER_M,  TOT_MARG_PER_F,
                             NON_WORK_PER_P,  NON_WORK_PER_M,  NON_WORK_PER_F,
                             CL_PER_P,  CL_PER_M,  CL_PER_F,  AL_PER_P,  AL_PER_M,  AL_PER_F,
                             HH_PER_P,  HH_PER_M,  HH_PER_F,  OW_PER_P,  OW_PER_M,  OW_PER_F,
                             CL_MAIN_MARG_P,  CL_MAIN_MARG_M,  CL_MAIN_MARG_F,
                             AL_MAIN_MARG_P,  AL_MAIN_MARG_M,  AL_MAIN_MARG_F,
                             HH_MAIN_MARG_P,  HH_MAIN_MARG_M,  HH_MAIN_MARG_F,
                             OW_MAIN_MARG_P,  OW_MAIN_MARG_M,  OW_MAIN_MARG_F,  WPR 
                             FROM (SELECT * FROM temp_census_master WHERE level = 'TEHSIL') CENSUS INNER JOIN
                             (SELECT distinct tehsil_id, tehsil_code, district_code from tehsil, district
                                where tehsil.district_id = district.district_id) tehsils
                             WHERE TEHSIL = tehsils.tehsil_code 
                             AND DISTRICT = tehsils.district_code;
                   
                      set tehsil_count = found_rows();
                     INSERT INTO census_master (TOWNSHIP_ID, LEVEL,  YEAR,   TRU,  
                             No_HH,  TOT_P,  TOT_M,  TOT_F,  P_06,  M_06,  F_06, 
                             P_SC,  M_SC,  F_SC,  P_ST,  M_ST,  F_ST,  
                             P_LIT,  M_LIT,  F_LIT,  P_ILL,  M_ILL,  F_ILL,  
                             TOT_WORK_P,  TOT_WORK_M,  TOT_WORK_F,
                             MAINWORK_P,  MAINWORK_M,  MAINWORK_F,
                             MAIN_CL_P,  MAIN_CL_M, MAIN_CL_F,
                             MAIN_AL_P,  MAIN_AL_M,  MAIN_AL_F,
                             MAIN_HH_P,  MAIN_HH_M,  MAIN_HH_F,
                             MAIN_OT_P,  MAIN_OT_M,  MAIN_OT_F,
                             MARGWORK_P,  MARGWORK_M,  MARGWORK_F,
                             MARG_CL_P,  MARG_CL_M,  MARG_CL_F,
                             MARG_AL_P,  MARG_AL_M,  MARG_AL_F,
                             MARG_HH_P,  MARG_HH_M,  MARG_HH_F,
                             MARG_OT_P,  MARG_OT_M,  MARG_OT_F,
                             NON_WORK_P,  NON_WORK_M,  NON_WORK_F,
                             SEXRATIO, SEXRATIO_SC,  SEXRATIO_ST,  SEXRATIO_06,  
                             HH_SIZE,  SC_PER,  ST_PER,
                             M_LIT_RATE,   F_LIT_RATE,  GENDER_GAP,
                             P_LIT_PER,  M_LIT_PER,  F_LIT_PER,
                             TOT_P_PER,  TOT_WOR_PER_P,  TOT_WOR_PER_M,  TOT_WOR_PER_F,  
                             TOT_MAIN_PER_P,  TOT_MAIN_PER_M,  TOT_MAIN_PER_F,
                             TOT_MARG_PER_P,  TOT_MARG_PER_M,  TOT_MARG_PER_F,
                             NON_WORK_PER_P,  NON_WORK_PER_M,  NON_WORK_PER_F,
                             CL_PER_P,  CL_PER_M,  CL_PER_F,  AL_PER_P,  AL_PER_M,  AL_PER_F,
                             HH_PER_P,  HH_PER_M,  HH_PER_F,  OW_PER_P,  OW_PER_M,  OW_PER_F,
                             CL_MAIN_MARG_P,  CL_MAIN_MARG_M,  CL_MAIN_MARG_F,
                             AL_MAIN_MARG_P,  AL_MAIN_MARG_M,  AL_MAIN_MARG_F,
                             HH_MAIN_MARG_P,  HH_MAIN_MARG_M,  HH_MAIN_MARG_F,
                             OW_MAIN_MARG_P,  OW_MAIN_MARG_M,  OW_MAIN_MARG_F,  WPR )
                   SELECT distinct township_id, LEVEL,  v_census_year,   TRU,  
                             No_HH,  TOT_P,  TOT_M,  TOT_F,  P_06,  M_06,  F_06, 
                             P_SC,  M_SC,  F_SC,  P_ST,  M_ST,  F_ST,  
                             P_LIT,  M_LIT,  F_LIT,  P_ILL,  M_ILL,  F_ILL,  
                             TOT_WORK_P,  TOT_WORK_M,  TOT_WORK_F,
                             MAINWORK_P,  MAINWORK_M,  MAINWORK_F,
                             MAIN_CL_P,  MAIN_CL_M, MAIN_CL_F,
                             MAIN_AL_P,  MAIN_AL_M,  MAIN_AL_F,
                             MAIN_HH_P,  MAIN_HH_M,  MAIN_HH_F,
                             MAIN_OT_P,  MAIN_OT_M,  MAIN_OT_F,
                             MARGWORK_P,  MARGWORK_M,  MARGWORK_F,
                             MARG_CL_P,  MARG_CL_M,  MARG_CL_F,
                             MARG_AL_P,  MARG_AL_M,  MARG_AL_F,
                             MARG_HH_P,  MARG_HH_M,  MARG_HH_F,
                             MARG_OT_P,  MARG_OT_M,  MARG_OT_F,
                             NON_WORK_P,  NON_WORK_M,  NON_WORK_F,
                             SEXRATIO, SEXRATIO_SC,  SEXRATIO_ST,  SEXRATIO_06,  
                             HH_SIZE,  SC_PER,  ST_PER,
                             M_LIT_RATE,   F_LIT_RATE,  GENDER_GAP,
                             P_LIT_PER,  M_LIT_PER,  F_LIT_PER,
                             TOT_P_PER,  TOT_WOR_PER_P,  TOT_WOR_PER_M,  TOT_WOR_PER_F,  
                             TOT_MAIN_PER_P,  TOT_MAIN_PER_M,  TOT_MAIN_PER_F,
                             TOT_MARG_PER_P,  TOT_MARG_PER_M,  TOT_MARG_PER_F,
                             NON_WORK_PER_P,  NON_WORK_PER_M,  NON_WORK_PER_F,
                             CL_PER_P,  CL_PER_M,  CL_PER_F,  AL_PER_P,  AL_PER_M,  AL_PER_F,
                             HH_PER_P,  HH_PER_M,  HH_PER_F,  OW_PER_P,  OW_PER_M,  OW_PER_F,
                             CL_MAIN_MARG_P,  CL_MAIN_MARG_M,  CL_MAIN_MARG_F,
                             AL_MAIN_MARG_P,  AL_MAIN_MARG_M,  AL_MAIN_MARG_F,
                             HH_MAIN_MARG_P,  HH_MAIN_MARG_M,  HH_MAIN_MARG_F,
                             OW_MAIN_MARG_P,  OW_MAIN_MARG_M,  OW_MAIN_MARG_F,  WPR 
                             FROM temp_census_master INNER JOIN
                             (select township_id, township_code, tehsil_code, district_code, TOWNSHIP_NAME
                                from township, (SELECT distinct tehsil_id, tehsil_code, district_code from tehsil, district
                                                        where tehsil.district_id = district.district_id ) tehsils
                                where township.tehsil_id = tehsils.tehsil_id) towns
                             ON TOWN_VILL = towns.township_code
                             AND TEHSIL = towns.tehsil_code 
                             AND DISTRICT = towns.district_code
                             AND NAME = township_name;
 
                 set township_count = found_rows();
                 INSERT INTO census_master ( WARD_ID, LEVEL,  YEAR,   TRU,  
                             No_HH,  TOT_P,  TOT_M,  TOT_F,  P_06,  M_06,  F_06, 
                             P_SC,  M_SC,  F_SC,  P_ST,  M_ST,  F_ST,  
                             P_LIT,  M_LIT,  F_LIT,  P_ILL,  M_ILL,  F_ILL,  
                             TOT_WORK_P,  TOT_WORK_M,  TOT_WORK_F,
                             MAINWORK_P,  MAINWORK_M,  MAINWORK_F,
                             MAIN_CL_P,  MAIN_CL_M, MAIN_CL_F,
                             MAIN_AL_P,  MAIN_AL_M,  MAIN_AL_F,
                             MAIN_HH_P,  MAIN_HH_M,  MAIN_HH_F,
                             MAIN_OT_P,  MAIN_OT_M,  MAIN_OT_F,
                             MARGWORK_P,  MARGWORK_M,  MARGWORK_F,
                             MARG_CL_P,  MARG_CL_M,  MARG_CL_F,
                             MARG_AL_P,  MARG_AL_M,  MARG_AL_F,
                             MARG_HH_P,  MARG_HH_M,  MARG_HH_F,
                             MARG_OT_P,  MARG_OT_M,  MARG_OT_F,
                             NON_WORK_P,  NON_WORK_M,  NON_WORK_F,
                             SEXRATIO, SEXRATIO_SC,  SEXRATIO_ST,  SEXRATIO_06,  
                             HH_SIZE,  SC_PER,  ST_PER,
                             M_LIT_RATE,   F_LIT_RATE,  GENDER_GAP,
                             P_LIT_PER,  M_LIT_PER,  F_LIT_PER,
                             TOT_P_PER,  TOT_WOR_PER_P,  TOT_WOR_PER_M,  TOT_WOR_PER_F,  
                             TOT_MAIN_PER_P,  TOT_MAIN_PER_M,  TOT_MAIN_PER_F,
                             TOT_MARG_PER_P,  TOT_MARG_PER_M,  TOT_MARG_PER_F,
                             NON_WORK_PER_P,  NON_WORK_PER_M,  NON_WORK_PER_F,
                             CL_PER_P,  CL_PER_M,  CL_PER_F,  AL_PER_P,  AL_PER_M,  AL_PER_F,
                             HH_PER_P,  HH_PER_M,  HH_PER_F,  OW_PER_P,  OW_PER_M,  OW_PER_F,
                             CL_MAIN_MARG_P,  CL_MAIN_MARG_M,  CL_MAIN_MARG_F,
                             AL_MAIN_MARG_P,  AL_MAIN_MARG_M,  AL_MAIN_MARG_F,
                             HH_MAIN_MARG_P,  HH_MAIN_MARG_M,  HH_MAIN_MARG_F,
                             OW_MAIN_MARG_P,  OW_MAIN_MARG_M,  OW_MAIN_MARG_F,  WPR )
                   SELECT distinct ward_id, LEVEL,  v_census_year,   TRU,  
                             No_HH,  TOT_P,  TOT_M,  TOT_F,  P_06,  M_06,  F_06, 
                             P_SC,  M_SC,  F_SC,  P_ST,  M_ST,  F_ST,  
                             P_LIT,  M_LIT,  F_LIT,  P_ILL,  M_ILL,  F_ILL,  
                             TOT_WORK_P,  TOT_WORK_M,  TOT_WORK_F,
                             MAINWORK_P,  MAINWORK_M,  MAINWORK_F,
                             MAIN_CL_P,  MAIN_CL_M, MAIN_CL_F,
                             MAIN_AL_P,  MAIN_AL_M,  MAIN_AL_F,
                             MAIN_HH_P,  MAIN_HH_M,  MAIN_HH_F,
                             MAIN_OT_P,  MAIN_OT_M,  MAIN_OT_F,
                             MARGWORK_P,  MARGWORK_M,  MARGWORK_F,
                             MARG_CL_P,  MARG_CL_M,  MARG_CL_F,
                             MARG_AL_P,  MARG_AL_M,  MARG_AL_F,
                             MARG_HH_P,  MARG_HH_M,  MARG_HH_F,
                             MARG_OT_P,  MARG_OT_M,  MARG_OT_F,
                             NON_WORK_P,  NON_WORK_M,  NON_WORK_F,
                             SEXRATIO, SEXRATIO_SC,  SEXRATIO_ST,  SEXRATIO_06,  
                             HH_SIZE,  SC_PER,  ST_PER,
                             M_LIT_RATE,   F_LIT_RATE,  GENDER_GAP,
                             P_LIT_PER,  M_LIT_PER,  F_LIT_PER,
                             TOT_P_PER,  TOT_WOR_PER_P,  TOT_WOR_PER_M,  TOT_WOR_PER_F,  
                             TOT_MAIN_PER_P,  TOT_MAIN_PER_M,  TOT_MAIN_PER_F,
                             TOT_MARG_PER_P,  TOT_MARG_PER_M,  TOT_MARG_PER_F,
                             NON_WORK_PER_P,  NON_WORK_PER_M,  NON_WORK_PER_F,
                             CL_PER_P,  CL_PER_M,  CL_PER_F,  AL_PER_P,  AL_PER_M,  AL_PER_F,
                             HH_PER_P,  HH_PER_M,  HH_PER_F,  OW_PER_P,  OW_PER_M,  OW_PER_F,
                             CL_MAIN_MARG_P,  CL_MAIN_MARG_M,  CL_MAIN_MARG_F,
                             AL_MAIN_MARG_P,  AL_MAIN_MARG_M,  AL_MAIN_MARG_F,
                             HH_MAIN_MARG_P,  HH_MAIN_MARG_M,  HH_MAIN_MARG_F,
                             OW_MAIN_MARG_P,  OW_MAIN_MARG_M,  OW_MAIN_MARG_F,  WPR 
                             FROM temp_census_master INNER JOIN
                             (select ward_id, ward.township_id, township_code, tehsil_code, district_code, ward_name
                                from ward, township, (SELECT distinct tehsil_id, tehsil_code, district_code from tehsil, district
                                                        where tehsil.district_id = district.district_id ) tehsils
                                where township.tehsil_id = tehsils.tehsil_id and township.township_id = ward.township_id) towns
                             ON TOWN_VILL = towns.township_code
                             AND TEHSIL = towns.tehsil_code 
                             AND DISTRICT = towns.district_code
                             AND NAME = ward_name;
                         
                    set ward_count = found_rows();
	SET census_count = state_count + district_count + tehsil_count + township_count + ward_count;
	COMMIT;

    SELECT state_count 'STATE', 
           district_count 'DISTRICTS', 
           tehsil_count 'TEHSILS', 
           township_count 'TOWNSHIPS', 
           ward_count 'WARDS', census_count 'CENSUS';
END
GO
CREATE PROCEDURE CENSUS_STATE_MANAGER ()
BEGIN

   declare      done int(1) default 0;
   declare  	l_state_code	int(10)	;
   declare  	l_district_code	int(10)	;
   declare  	l_tehsil_code	int(10)	;
   declare      l_name varchar(100);
   declare      l_level varchar(10);
   declare  	v_count	int(4)	;
   declare      insert_count int(4) default 0;
   declare      update_count int(4) default 0;
  
   declare cur_update_state cursor for 
                        SELECT DISTINCT name, state, district, tehsil, level FROM temp_census_master where level = 'STATE';

   DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;
  
   OPEN cur_update_state;
   select  FOUND_ROWS() 'STATES FETCHED FROM TEMP_CENSUS_MASTER' ;
    REPEAT
        FETCH cur_update_state INTO  l_name, l_state_code, l_district_code, l_tehsil_code,  l_level;

            IF NOT done THEN
                    SELECT count(*) INTO v_count FROM state WHERE state_name = l_name;

                    IF v_count = 0 THEN
                        INSERT INTO party_analyst_schema.state (state_name,
                                                           state_code) 
                                             VALUES (l_name, l_state_code);
                        set insert_count = insert_count + 1;
                    ELSE

                        SELECT count(*) INTO v_count FROM state WHERE state_code = l_state_code;
                        IF v_count = 0 THEN
                                UPDATE state SET state_code = l_state_code 
                                            WHERE state_name = l_name;
                                set update_count = update_count + 1;
                        END IF; 
                    END IF;

            END IF;
    UNTIL done END REPEAT;
    CLOSE cur_update_state;

    SELECT  insert_count 'ROWS INSERTED TO STATE' ,  update_count 'ROWS UPDATED' ;
END
GO
CREATE PROCEDURE CENSUS_TEHSIL_MANAGER ()
BEGIN

   declare      done int(1) default 0;
   declare  	l_state_code	int(10)	;
   declare  	l_district_code	int(10)	;
   declare  	l_tehsil_code	int(10)	;
   declare      l_name varchar(100);
   declare      l_level varchar(10);
   declare  	v_count	int(4)	;
   declare      insert_count int(4) default 0;
   declare      update_count int(4) default 0;

   declare cur_update_state cursor for 
                        SELECT DISTINCT name, state, district, tehsil, level FROM temp_census_master where level = 'TEHSIL';

   DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;
  
   SELECT COUNT(*) INTO v_count FROM (
                SELECT distinct TEHSIL, DISTRICT, NAME 
                                FROM tehsil, district, temp_census_master
                                      WHERE tehsil_name = name
                                                        and district_code = district
                                                        and tehsil_code = tehsil
                                                        and tehsil.district_id = district.district_id
                                                        and level = 'TEHSIL') A;

   OPEN cur_update_state;
   select  FOUND_ROWS() 'TEHSILS FETCHED FROM TEMP_CENSUS_MASTER';
  
        REPEAT
            FETCH cur_update_state INTO  l_name, l_state_code, l_district_code, l_tehsil_code,  l_level;

                IF NOT done THEN

                        SELECT count(distinct tehsil_id) INTO v_count FROM tehsil, district, state
                                            WHERE tehsil_name = l_name
                                                            and district_code = l_district_code
                                                            and tehsil_code = l_tehsil_code
                                                            and state.state_code = l_state_code
                                                            and state.state_id = district.state_id
                                                            and tehsil.district_id = district.district_id;

                        IF v_count = 0 THEN
                            INSERT INTO tehsil (tehsil_name,
                                               tehsil_code,
                                               district_id)
                                         VALUES (l_name, 
                                                 l_tehsil_code,
                                                 (SELECT district_id FROM district WHERE district_code = l_district_code));
                            set insert_count = insert_count + 1;
                      ELSE
                            UPDATE tehsil SET tehsil_code = l_tehsil_code 
                                        WHERE tehsil_name = l_name 
                                        and district_id = (select distinct district_id  
                                                                    FROM district, state 
                                                                    where district_code = l_district_code
                                                                    and state_code = l_state_code
                                                                    and state.state_id = district.district_id);
                            set update_count = update_count + 1;
                      END IF;

             END IF;
        UNTIL done END REPEAT;

    CLOSE cur_update_state;
     
    SELECT  insert_count 'ROWS INSERTED TO TEHSIL' ,  update_count 'ROWS UPDATED' ;

    INSERT INTO tehsil (tehsil_name,
                                           tehsil_code,
                                           district_id)
                                     SELECT DISTINCT NAME, 
                                            TEHSIL, 
                                            district_id
                                    FROM temp_census_master, district 
                                    WHERE DISTRICT = district_code
                                    AND LEVEL = 'DISTRICT' 
                                    AND TEHSIL = 0 
                                    AND (SELECT COUNT(*) FROM tehsil WHERE tehsil.district_id = district.district_id) = 0; 
     SELECT FOUND_ROWS() 'DISTRICTS CONSIDERED AS TEHSILS';
END
GO
CREATE PROCEDURE CENSUS_TOWNSHIP_MANAGER ()
BEGIN

   declare      done int(1) default 0;
   declare  	l_state_code	int(10)	;
   declare  	l_district_code	int(10)	;
   declare  	l_tehsil_code	int(10)	;
   declare  	l_town_code	int(10)	;
   declare      l_name varchar(100);
   declare      l_level varchar(10);
   declare      l_tehsil_id int(10);
   declare  	v_count	int(4)	;
   declare      temp_count int(4);
   declare      temp_name varchar(100);
   declare      insert_count int(4) default 0;
   declare      update_count int(4) default 0;

   declare cur_update_state cursor for 
                        SELECT DISTINCT name, state, district, tehsil, town_vill, level FROM temp_census_master where level IN ('TOWN', 'VILLAGE');

   DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;
  
   OPEN cur_update_state;
   select  FOUND_ROWS() 'TOWNS_VILLAGES FETCHED FROM TEMP_CENSUS_MASTER';
  
        REPEAT
            FETCH cur_update_state INTO  l_name, l_state_code, l_district_code, l_tehsil_code, l_town_code, l_level;

                IF NOT done THEN

                       SELECT COUNT(*) INTO v_count FROM township, tehsil, district, state
                                      WHERE township_name = l_name
                                                        and township_code = l_town_code
                                                        and township.tehsil_id = tehsil.tehsil_id
                                                        and district_code = l_district_code
                                                        and tehsil_code = l_tehsil_code
                                                        and tehsil.district_id = district.district_id
                                                        and district.state_id = state.state_id
                                                        and state_code = l_state_code;

                        IF v_count = 0 THEN
                               IF l_level = 'TOWN' THEN
                                       select count(*) into temp_count from temp_census_master where town_vill = l_town_code
                                                    and level = l_level and tehsil=l_tehsil_code and district=l_district_code;

                                         select distinct tehsil_id into l_tehsil_id 
                                                 from tehsil, district 
                                                 where tehsil_code = l_tehsil_code
                                                 and tehsil.district_id = district.district_id
                                                 and district.district_code = l_district_code;

                                        if (temp_count > 1) then

                                            select distinct name into temp_name 
                                                    from temp_census_master
                                                    where town_vill = l_town_code and level = 'TOWN' and tru='Urban' and tehsil=l_tehsil_code and district=l_district_code
                                                    and name not like '%+OG) (Part)%' and name like '% (M%' and name not like '%OG)';

                                            insert into township(township_name, township_code, tehsil_id, township_type, greater_town)
                                                    values (temp_name, l_town_code, l_tehsil_id, 'T', 'N');

                                            select distinct name into temp_name 
                                                    from temp_census_master
                                                    where town_vill = l_town_code and level = 'TOWN' and tru='Urban' and tehsil=l_tehsil_code and district=l_district_code
                                                    and name not like '%(M)' 
                                                    and (name like '%OG)' or name like '%+OG) (Part)%');
                                            insert into township( township_name, township_code, tehsil_id, township_type, greater_town)
                                                    values (temp_name, l_town_code, l_tehsil_id, 'T', 'Y');

                                        end if;

                                        if (temp_count = 1) then
                                            select distinct name into temp_name 
                                                    from temp_census_master
                                                    where town_vill = l_town_code and level = l_level and tehsil=l_tehsil_code and district=l_district_code;

                                            insert into township( township_name, township_code, tehsil_id, township_type, greater_town)
                                                    values (temp_name, l_town_code, l_tehsil_id, 'T', 'N');
                                        end if;

                                    set insert_count = insert_count + 1;

                             ELSE 
                                    INSERT INTO township (township_name, township_code,
                                              township_type,
                                              tehsil_id,
                                              greater_town)
                                            SELECT l_name, l_town_code, 'V',  tehsil_id, 'N'
                                                FROM tehsil, district, state
                                                WHERE tehsil_code = l_tehsil_code
                                                AND tehsil.district_id = district.district_id
                                                AND district.district_code = l_district_code
                                                AND state_code = l_state_code
                                                AND state.state_id = district.state_id;
                             END IF;
                      END IF;
             END IF;
        UNTIL done END REPEAT;

    CLOSE cur_update_state;
    

    SELECT  insert_count 'ROWS INSERTED TO TOWNSHIP' ,  update_count 'ROWS UPDATED' ;

END
GO
CREATE PROCEDURE CENSUS_WARD_MANAGER ()
BEGIN

   declare      done int(1) default 0;
   declare  	l_state_code	int(10)	;
   declare  	l_district_code	int(10)	;
   declare  	l_tehsil_code	int(10)	;
   declare  	l_town_code	int(10)	;
   declare  	l_ward_code	int(10)	;
   declare      l_name varchar(100);
   declare      l_level varchar(10);
   declare  	v_count	int(4)	;
   declare      insert_count int(4) default 0;
   declare      update_count int(4) default 0;

   declare cur_update_state cursor for 
                        SELECT DISTINCT name, state, district, tehsil, town_vill, ward, level FROM temp_census_master where level = 'WARD';

   DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;
  
   OPEN cur_update_state;
   select  FOUND_ROWS() 'WARDS FETCHED FROM TEMP_CENSUS_MASTER';
  
        REPEAT
            FETCH cur_update_state INTO  l_name, l_state_code, l_district_code, l_tehsil_code, l_town_code, l_ward_code, l_level;

                IF NOT done THEN

                       SELECT COUNT(*) INTO v_count FROM (
                            SELECT distinct ward_id, township.township_id, tehsil.tehsil_id, district.district_id,state.state_id, l_name 
                                FROM ward, township, tehsil, district, state
                                      WHERE ward_name = l_name
                                                        and ward_code = l_ward_code
                                                        and ward.township_id = township.township_id
                                                        and township_code = l_town_code
                                                        and township.tehsil_id = tehsil.tehsil_id
                                                        and district_code = l_district_code
                                                        and tehsil_code = l_tehsil_code
                                                        and tehsil.district_id = district.district_id
                                                        and district.state_id = state.state_id
                                                        and state_code = l_state_code) A;

                        IF v_count = 0 THEN
                            INSERT INTO ward(ward_name, township_id, ward_code)
                                      SELECT l_name, A.township_id, l_ward_code FROM
                                               (SELECT distinct township_code, township_id, tehsil_code, TOWNSHIP_NAME, district_code, state_code FROM tehsil, township, district, state
                                                                where tehsil.tehsil_id = township.tehsil_id and district.district_id = tehsil.district_id 
                                                                 and township.greater_town = 'N' and district.state_id = state.state_id) A 
                                        WHERE A.TOWNSHIP_CODE = l_town_code
                                        and a.tehsil_code = l_tehsil_code
                                        and a.district_code = l_district_code
                                        and a.state_code = l_state_code;

                            set insert_count = insert_count + 1;
                      END IF;
             END IF;
        UNTIL done END REPEAT;

    CLOSE cur_update_state;
    
    SELECT  insert_count 'ROWS INSERTED TO WARD' ,  update_count 'ROWS UPDATED' ;

END
GO
CREATE INDEX fk_tehsil_census
	ON census_master(tehsil_id)
GO
CREATE INDEX fk_town_census
	ON census_master(township_id)
GO
CREATE INDEX fk_state_census
	ON census_master(state_id)
GO
CREATE INDEX fk_district_census
	ON census_master(district_id)
GO
CREATE INDEX fk_ward_census
	ON census_master(ward_id)
GO
CREATE INDEX fk_ward_year_census
	ON census_master(ward_id, year)
GO
CREATE INDEX fk_tehsil_year_census
	ON census_master(tehsil_id, year)
GO
CREATE INDEX fk_township_year_census
	ON census_master(township_id, year)
GO
CREATE INDEX fk_district_year_census
	ON census_master(district_id, year)
GO
CREATE INDEX fk_state_year_census
	ON census_master(state_id, year)
GO

ALTER TABLE STATE AUTO_INCREMENT = 1
GO
ALTER TABLE DISTRICT AUTO_INCREMENT = 1
GO
ALTER TABLE TEHSIL AUTO_INCREMENT = 1
GO
ALTER TABLE TOWNSHIP AUTO_INCREMENT = 1
GO
ALTER TABLE WARD AUTO_INCREMENT = 1
GO