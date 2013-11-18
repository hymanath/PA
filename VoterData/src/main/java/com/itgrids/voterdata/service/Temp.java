package com.itgrids.voterdata.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Temp {
	
	public static void main(String args[]) throws IOException {
		try{
		String [] strings = {"abused_comments","address","address_contact","address_type","alliance_groups","" +
				"ananymous_user","announcement","approval_details","assembly_local_election_body","assembly_local_election_body_ward",
				"assigned_problem_progress","block","blood_group","booth","booth_constituency_election","booth_constituency_election_voter",
				"booth_local_body_ward","booth_publication_voter","booth_result","booth_village","booth_village_census","cadre","cadre_family_member_info","cadre_language_efficiency","cadre_level","cadre_online_registration","cadre_participated_training_camps","cadre_problem_details",
				"cadre_problems","cadre_role","cadre_role_relation","cadre_skills","calltracking_detail","calltracking_problem","candidate","candidate_address","candidate_booth_result","candidate_caste","candidate_consolidation_task","candidate_page_custom_pages","candidate_phone","candidate_profile_description","candidate_result",
				"candidate_social","candidate_subscriptions","candidate_updates_email","candidate_website","caste","caste_category","caste_category_group","caste_state","category","census_master","census_parameter","census_year","classified_problems","client_problem_push","comment","comment_category_candidate","comment_category_constituency",
				"comment_category_party","comment_data","comment_data_category",
				"constituency","constituency_census_details","constituency_election","constituency_election_result","constituency_lead_candidate","constituency_previous_district","constituency_subscriptions","constituency_urban_percentage","content","content_notes","content_type","country","custom_message","custom_page","custom_page_type","delimitation_block","delimitation_constituency","delimitation_constituency_assembly_details",
				"delimitation_constituency_mandal_details","delimitation_constituency_town","delimitation_district","delimitation_district_data","delimitation_village","delimitation_ward","delimitation_year","department_organisation","district","educational_qualifications","election","election_alliance","election_alliances","election_governing_body","election_governing_body_position","election_ministers","election_scope","election_type",
				"entitlement","epaper","epaper_url",
				"event_action_organizer","event_action_plan","favorite_link_page","feedback","feedback_comment","feedback_task","file","file_gallary","file_multiple_news","file_paths","file_source_language","file_type","fund_source","gallary","group_entitlement","group_entitlement_relation","group_member","groups","hamlet","hamlet_booth_election","hamlet_booth_publication","homepage_question","homepage_question_answers","influencing_people","influencing_people_position",
				"information_source","job","job_run_details","key_candidate","language","local_election_body","local_election_body_ward","local_group_region","locality","message_to_candidate",
				"message_to_party","message_type","minister_type","ministry","ministry_scope","module_details","module_region_scopes","my_group","news_flag","news_importance","news_problem","nomination","nomination_history","occupation","opinion_poll","opinion_poll_comments","opinion_poll_question_options","opinion_poll_questions","opinion_poll_result","panchayat","panchayat_hamlet","party","party_cadre_skills","party_election_district_result","party_election_district_result_with_alliance","party_election_result","party_election_result_with_alliance","party_election_result_with_area_type","party_election_result_with_gender_analysis","party_election_state_result","party_election_state_result_with_alliance","party_gallery","party_important_date","party_manifesto","party_page_custom_pages","party_profile_description","party_rebel","party_rebel_candidate","party_social","party_subscriptions","party_training_camps",
				"party_updates_email","party_wkg_committee_designation","party_working_committee","personal_user_group","phone_number","political_changes","political_changes_information_source","pollingstationdetails","position_scope","problem","problem_activity","problem_and_problem_source","problem_assigned_cadre","problem_assigned_department","problem_backup","problem_classification","problem_comments",
				"problem_complete_location","problem_external_source","problem_file","problem_files","problem_fund_source","problem_history","problem_impact_level","problem_likes","problem_location","problem_progress","problem_rating","problem_source","problem_source_scope","problem_source_scope_concerned_department","problem_status","problem_type","profile_opts","publication_date","publication_election","questions_repository","region_scopes","region_scopes_problem_type","registration","role","search_engine_ipaddress","settings_option","sms_history","sms_module","social_category","social_network_site","source","source_language","special_page","special_page_custom_pages","special_page_data","special_page_description","special_page_gallery","special_page_highlights","special_page_info","special_page_meta_info","special_page_subscriptions","special_page_updates_email","state","state_region","state_region_district",
				"static_group","static_local_group","static_user_designation","static_user_group","static_users","tehsil","temp_census_master","township","user","user_address","user_announcement","user_approval_details","user_candidate_relation","user_category","user_connectedto","user_constituency_access_info","user_constituency_scope","user_country_access_info","user_district_access_info","user_entitlement_group_region","user_event","user_events_organizer","user_favorite_links","user_gallary","user_group","user_group_entitlement","user_group_privileges","user_group_relation","user_groups","user_imp_dates","user_login_details","user_mappings_history","user_party_relation","user_privacy_settings","user_problem","user_problem_approval","user_profile_opts","user_referral_emails","user_relation","user_roles","user_sms_track","user_social_network_site","user_state_access_info","user_subuser_relation","user_tracking","user_voter_category","user_voter_category_value","user_voter_details","village_booth_election","visibility","voter","voter_age_info","voter_age_range","voter_cast_basic_info","voter_cast_info","voter_category_value","voter_family_info","voter_family_range","voter_info","voter_modification","voter_modification_age_info","voter_modification_info","voter_modification_temp",
				"voter_party_info","voter_report_level","voter_status","voter_temp","ward"}; 
		
		String str = "mysqldump.exe --no-create-info=FALSE --order-by-primary=FALSE --force=FALSE --no-data=FALSE --tz-utc=TRUE --flush-privileges=FALSE --compress=FALSE --replace=FALSE --host=199.85.212.11 --insert-ignore=FALSE --extended-insert=TRUE --user=dakavara_pa --password=1tGrids@123 --quote-names=TRUE --hex-blob=FALSE --complete-insert=FALSE --add-locks=TRUE --port=3306 --disable-keys=TRUE --delayed-insert=FALSE --create-options=TRUE --delete-master-logs=FALSE --comments=TRUE --default-character-set=utf8 --max_allowed_packet=1G --flush-logs=FALSE --dump-date=TRUE --lock-tables=TRUE --allow-keywords=FALSE --events=FALSE 'dakavara_pa' '";
		
		File resultFile  = new File("C:/ServerDump/NewVoterData.txt");
	
		BufferedWriter outwriter = new BufferedWriter(new FileWriter(resultFile));
		
		StringBuilder sb = new StringBuilder();
		
		for(String string : strings)
		{
			sb.append(str+string+"' > D:\\ServerDump\\Temp\\"+string+".sql"+"\n\n");
		}
		outwriter.write(sb.toString());
    	outwriter.close();
    	System.out.println("End");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
