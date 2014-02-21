
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;

import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.model.User;
import com.itgrids.partyanalyst.security.EncryptDecrypt;

public class UserDAOHibernateTest extends BaseDaoTestCase{

	private IUserDAO userDAO;

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	/*public void test()
	{
		userDAO.getAll();
	}*/
	
	/*public void testCheckUserPassword()
	{
		List<User> list = userDAO.checkUserPassword("7258695", 12l);
		System.out.println(list.size());
	}*/
	
	/*public void testChangeUserPassword()
	{
	Integer result = userDAO.changeUserPassword("7258695", 12l, IConstants.TRUE, new Date());
	System.out.println(result);
	}*/
	
	/*public void testGetUserIdByUserName()
	{
		List<Object> list = userDAO.getUserIdByUserName("Anuradha@aa.com");
		System.out.println(list.get(0));
	}*/
	
	/*public void testFindByUserNameAndPassword()
	{
		User user = userDAO.findByUserNameAndPassword("aaaa@aa.com", "3685171");
		System.out.println(user.getUserName());
	}*/
	
	/*public void test()
	{
		System.out.println(userDAO.getUserProfileImageNameByUserId(1l));
	}*/
	
	/*public void testGetUserLocationDetailsByUserIds()
	{
		List<Long> userIds = new ArrayList<Long>(0);
		userIds.add(1l);
		
		List<Object[]> list = userDAO.getUserLocationDetailsByUserIds(userIds);
		System.out.println(list.size());
		
		for(Object[] params : list)
		{
			System.out.println();
			for(Object obj : params)
			System.out.print("----\t"+obj.toString());
		}
	}*/
	/*public void testSaveUserProfileImageNameToDB()
	{
		Integer result = userDAO.saveUserProfileImageNameToDB(1l, "1.jpg");
		System.out.println(result);
	}
	
	public void testGetUserByUserId()
	{
		User user = userDAO.getUserByUserId(1l);
		System.out.println(user.getFirstName());
	}*/
	
	/*public void testAllRegisteredUsersData()
	{
		List<Object[]> users = userDAO.allRegisteredUsersData();
		System.out.println(users.size());
		if(users != null && users.size() > 0)
		{
			for(Object[] result : users)
			{
				System.out.println(result[0]+" ----- "+result[1]+" "+result[2]);
			}
		}
		
	}*/
	/*public void testGetUserByUserName()
	{
		List<User> params = userDAO.getUserByUserName("a.dakavaram@itgrids.com");
		for(User list : params)
		{
			System.out.println(list.getLastName());
		}
	}*/

/*public void testGetUserEmail()
	{
		List<Object[]> result = userDAO.getUserEmail(2l);
		System.out.println(result.size());
		if(result != null && result.size() > 0)
		{
		for(Object[] params: result)
		{
			System.out.println(params[0]);
			System.out.println(params[1].toString()+" "+params[2].toString());
			System.out.println(params[3]);
		}
	 }
	}*/
	
	/*public void testchangeUserNameAsEmail()
	{
		List<User> params = userDAO.changeUserNameAsEmail("nunna@gmail.com");
		for(User list : params)
		{
			System.out.println(list.getLastName());
		}
		
	}*/
	
	/*public void testGetUserDetails()
	{
		List result = userDAO.getUserDetails("Chintu@anu.com");
		System.out.println(result.size());
	}*/
	
	/*public void testCheckAnonymousUserLogin()
	{
		List<User> result = userDAO.checkAnonymousUserLogin("anuradha","anuradha");
		System.out.println(result.size());
		if(result != null && result.size() >0)
		{
		for(User userDetails : result)
		{
			System.out.println(userDetails.getUserName());
			System.out.println(userDetails.getPassword());
		}
		}
	}*/
	
	/*public void testGetPasswordNotUpdatdUsersList()
	{
		List<Object[]> list = userDAO.getPasswordNotUpdatdUsersList();
		System.out.println(list.size());
		if(list != null && list.size() >0)
		{
			for(Object[] params : list)
			{
				System.out.println(params[0]);
				System.out.println(params[1]);
			}
		}
	}*/
	/*public void testGetAllUsersMobile()
	{
		List<Object[]> list = userDAO.getAllUsersMobile();
		if(list != null &&list.size() > 0)
		{
			for(Object[] params : list)
			{
				System.out.println(params[0]);
				System.out.println(params[1]);
				System.out.println(params[2]);
				System.out.println(params[3]);
			}
			
		}
	}*/
	
	/*public void testGetAllMobilenosAsUnique()
	{
		Object list = userDAO.getAllMobilenosAsUnique();
		System.out.println(list);
	}*/
	
	/*public void testFindByUserRegistrationId()
	{
		List<User> result = userDAO.findByUserRegistrationId(1l);
		if(result != null && result.size() > 0)
		{
			for(User userDetails : result)
			{
				System.out.println(userDetails.getUserName());
				System.out.println(userDetails.getPassword());
			}
		}
	}*/
	
	/*public void testGetAllRegisteredUsers()
	{
		List<User> details = userDAO.getAllRegisteredUsers();
		Assert.assertEquals(33, details.size());
	}*/
	
	/*public void testGetSubusersByParentUserId()
	{
		List<Object[]> list = userDAO.getSubusersByParentUserId(1l);
		System.out.println(list.size());
		if(list != null && list.size() > 0)
		{
			for(Object[] params : list)
			{
				System.out.println(params[0]);
				System.out.println(params[1]);
			}
		}
	}*/
	
	/*public void testGetAnanymousUserLocationDetailsByIds()
	{
		List<Long> userIds = new ArrayList<Long>();
		userIds.add(611l); 
		List list = userDAO.getAnanymousUserLocationDetailsByIds(userIds);
		System.out.println(list.size());
		if(list !=null && list.size()>0){
			Object[] values = (Object[])list.get(0);
			Long districtId = (Long)values[2];
			Long constiId = (Long)values[4];
			System.out.println("districtId : "+districtId+"constiId : "+constiId);
			for(Object val:values)
				System.out.println(val!=null?val.toString():"");
			
		}
			
	}*/
	/*public void testGetAnanymousUserLocationDetailsByIds()
	{
		List<Long> userIds = new ArrayList<Long>();
		userIds.add(611l); 
		List list = userDAO.getAnanymousUserLocationDetailsByIds(userIds);
		System.out.println(list.size());
		if(list !=null && list.size()>0){
			Object[] values = (Object[])list.get(0);
			
			List locationIds = new ArrayList();
			locationIds.add((Long)values[0]);
			locationIds.add((Long)values[2]);
			locationIds.add((Long)values[4]);
			
			System.out.println(locationIds.get(0));
			System.out.println(locationIds.get(1));
			System.out.println(locationIds.get(2));
		}
	
			
	}*/
	
	/*public void testGetUserEmailByUserId()
	{
		List<Object[]> list = userDAO.getUserEmailByUserId(1l);
		if(list != null && list.size() >0)
		{
			for(Object[] params : list)
			{
				System.out.println(params[0]);
				System.out.println(params[1]);
				System.out.println(params[2]);
			}
		}
	}*/
	
	/*public void testGetUserBasicDetailsForProfile()
	{
		List<Object[]> userList = userDAO.getUserBasicDetailsForProfile(1l);
		System.out.println(userList.size());
		if(userList !=null && userList.size() > 0)
		{
			for(Object[] params : userList)
			{
				System.out.println(params[1]);
			}
		}
		
	}*/
	
	
	
	/*public void testGetUserBasicDetailsForProfile()
	{
		List<Long> userId = new ArrayList<Long>(0);
		userId.add(1l);
		userId.add(2l);
		List<Object[]> userList = userDAO.getUserBasicDetailsForProfile(userId);
		System.out.println(userList.size());
		if(userList !=null && userList.size() > 0)
		{
			for(Object[] params : userList)
			{
				System.out.println(params[1]);
			}
		}
		
	}*/
	
	/*public void testgetAllUsersInSelectedLocations()
	{
		List<Long> locationIds = new ArrayList<Long>();
		locationIds.add(228l);
		List<Object> list = userDAO.getAllUsersInSelectedLocations(locationIds, "constituency", 100l, 0l, null);
		System.out.println(list.size());
		if(list != null && list.size() > 0)
		{
			for(int i=0;i<list.size(); i++)
			{
				Object[] result = (Object[])list.get(i);
				System.out.println(result[6]+"  "+result[7]);
				System.out.println(result[8]+"  "+result[9]);
				
			}
		}
	}*/
	/*public void testgetNotConnectedUsersInSelectedLocations()
	{
		List<Long> locationIds = new ArrayList<Long>();
		locationIds.add(10l);
		List<Long> otherUsers = new ArrayList<Long>();
		otherUsers.add(10l);
		
		List<Object> list = userDAO.getNotConnectedUsersInSelectedLocations(817l, locationIds, "DISTRICT", otherUsers, 2l, 0l, null);
		System.out.println(list.size());
		if(list != null && list.size() > 0)
		{
			for(int i=0;i<list.size(); i++)
			{
				Object[] result = (Object[])list.get(i);
				System.out.println(result[6]+"  "+result[7]);
				System.out.println(result[8]+"  "+result[9]);
				
			}
		}
	}
	*/
	
	/*public void testGetUserNameAndPWDByUserId()
	{
		List<Object[]> list = userDAO.getUserNameAndPwdByUserId(1l);
		for(Object[] params : list)
			System.out.println(params[0]+" "+params[1]);
	}*/
	
/*	
	public void testgetAllUsersCountInSelectedLocations()
	{
		List<Long> locationIds = new ArrayList<Long>(0);
		//locationIds.add(467l);467, , , , , , , 
		locationIds.add(467l);
		locationIds.add(217l);
		locationIds.add(213l);
		locationIds.add(209l);
		locationIds.add(228l);
		locationIds.add(218l);
		locationIds.add(229l);
		locationIds.add(219l);
		Long count = userDAO.getAllUsersCountInSelectedLocations(locationIds,IConstants.CONSTITUENCY_LEVEL,"");
		System.out.println(count);
	}*/
	
	/*public void testupdateAllUsersPasswords()
	{	System.out.println(new Date());
		List<User> users  =userDAO.updateAllUsersPasswords();
		
		
		int i = 0;
		System.out.println(new Date());
		System.out.println(users.size());
		
		for(User user:users){
			String secretKey = EncryptDecrypt.getSecretKey();
			EncryptDecrypt phash = new EncryptDecrypt(secretKey);
			String encryptedPassword = phash.encryptText(user.getPassword());
			
			user.setHashKeyTxt(secretKey);
			user.setPasswdHashTxt(encryptedPassword);
			userDAO.save(user);			
			i++;
			
			if(i == 1)
			System.out.println("UPDATE user SET hash_key_txt = '"+secretKey +"',passwd_hash_txt = '"+encryptedPassword+"' WHERE user_id = "+user.getUserId()+";");
		}
		
	}*/
	
	/*public void testCreateHashKeyAndValueForAPassword()
	{
		String secretKey = EncryptDecrypt.getSecretKey();
		EncryptDecrypt phash = new EncryptDecrypt(secretKey);
		String encryptedPassword = phash.encryptText("guest@pa");
		System.out.println(secretKey);
		System.out.println(encryptedPassword);
	}
	public void testCheckSuggestionOrder(){
		List<String> ids = new ArrayList<String>();
		ids.add("VERY STRONG");
		ids.add("STRONG");
		ids.add("OK");
		ids.add("POOR");
		ids.add("VERY POOR");
		ids.add("WOREST");
		int x = -1;
		for(int j = ids.size()-1;j>0;j--){//2009
				x = x+1;
			for(int i = 0;i< (ids.size()-1-x);i++)
			{//2004
				System.out.println(ids.get(i)+","+ids.get(j));
			}
		}
		 x = -1;
		 System.out.println("-------------------");
		for(int j = ids.size()-1;j>0;j--){//2004
				x = x+1;
			for(int i = 0;i< (ids.size()-1-x);i++)
			{//2009
				System.out.println(ids.get(j)+","+ids.get(i));
			}
		}
		 System.out.println("-------------------");
		for(int i = 0;i<ids.size();i++){
			System.out.println(ids.get(i)+","+ids.get(i));
		}
	}*/
	/*public void testCheckSuggestionOrderNew(){
		List<String> ids = new ArrayList<String>();
		ids.add("WOREST");
		ids.add("VERY POOR");
		ids.add("POOR");
		ids.add("OK");
		ids.add("STRONG");
		ids.add("VERY STRONG");
		
		
		
		
	  for(int z = ids.size()-2;z>=0;z--){
		int x = -1;
		for(int j = 0;j<ids.size()-z-1;j++){//2009
			for(int i = ids.size()-1;i>=z+1;i--)
			{//2004
				System.out.println(ids.get(j)+","+ids.get(i));
			}
		}
	  }
	  int z = 1;
	  for(z = 1;z<ids.size();z++){
		  for(int j = 0;j<z;j++){//2009
				for(int i = ids.size()-z+j;i>=ids.size()-z+j;i--)
				{//2004
					System.out.println(ids.get(j)+","+ids.get(i)+","+z);
				}
			}
		  }
	  z = z-1;
	  System.out.println("-----------------");
	  for(int y = 2;y<=ids.size()-1;y++){
		  for(int j = y-2;j<y;j++){
			  z++;
			  System.out.println(ids.get(y-1)+","+ids.get(j)+","+z);
				for(int i = j,l=y;i>0&&l<ids.size()-1;i--,l++)
				{
					System.out.println(ids.get(l)+","+ids.get(i-1)+","+z);
				}
			}
		  } 
		 for(int z = 5;z<=ids.size()-1;z++){
		  for(int j = z-2;j<z;j++){
			  System.out.println(ids.get(z-1)+","+ids.get(j));
				for(int i = j,l=z;i>0&&l<ids.size()-1;i--,l++)
				{
					System.out.println(ids.get(l)+","+ids.get(i-1));
				}
			}
		  } 
		int x = -1;
		 System.out.println("-------------------");
		for(int j = ids.size()-1;j>0;j--){//2004
				x = x+1;
			for(int i = 0;i< (ids.size()-1-x);i++)
			{//2009
				System.out.println(ids.get(j)+","+ids.get(i));
			}
		}
		 System.out.println("-------------------");
		for(int i = 0;i<ids.size();i++){
			System.out.println(ids.get(i)+","+ids.get(i));
		}
	}*/
	
	
	
	public void test()
	{
		List<String> list = new ArrayList<String>();
		
		list.add("po_1_Adilabad");
		list.add("po_2_Peddapalli");
		list.add("po_3_Karimnagar");
		list.add("po_4_Nizamabad");
		list.add("po_5_Zahirabad");
		list.add("po_6_Medak");
		list.add("po_7_Malkajgiri");
		list.add("po_8_Secunderabad");
		list.add("po_9_Hyderabad");
		list.add("po_10_Chevella");
		list.add("po_11_Mahbubnagar");
		list.add("po_12_Nagarkurnool");
		list.add("po_13_Nalgonda");
		list.add("po_14_Bhongir");
		list.add("po_15_Warangal");
		list.add("po_16_Mahabubabad");
		list.add("po_17_Khammam");
		list.add("po_18_Araku");
		list.add("po_19_Srikakulam");
		list.add("po_20_Vizianagaram");
		list.add("po_21_Visakhapatnam");
		list.add("po_22_Anakapalli");
		list.add("po_23_Kakinada");
		list.add("po_24_Amalapuram");
		list.add("po_25_Rajahmundry");
		list.add("po_26_Narsapuram");
		list.add("po_27_Eluru");
		list.add("po_28_Machilipatnam");
		list.add("po_29_Vijayawada");
		list.add("po_30_Guntur");
		list.add("po_31_Narasaraopet");
		list.add("po_32_Bapatla");
		list.add("po_33_Ongole");
		list.add("po_34_Nandyal");
		list.add("po_35_Kurnool");
		list.add("po_36_Anantapur");
		list.add("po_37_Hindupur");
		list.add("po_38_Kadapa");
		list.add("po_39_Nellore");
		list.add("po_40_Tirupati");
		list.add("po_41_Rajampet");
		list.add("po_42_Chittoor");

		
		List<String> list1 = new ArrayList<String>();
		
		list1.add("adil@123");
		list1.add("pedd@123");
		list1.add("kari@123");
		list1.add("niza@123");
		list1.add("zahi@123");
		list1.add("meda@123");
		list1.add("malk@123");
		list1.add("secu@123");
		list1.add("hyde@123");
		list1.add("chev@123");
		list1.add("mahb@123");
		list1.add("naga@123");
		list1.add("nalg@123");
		list1.add("bhon@123");
		list1.add("wara@123");
		list1.add("maha@123");
		list1.add("kham@123");
		list1.add("arak@123");
		list1.add("srik@123");
		list1.add("vizi@123");
		list1.add("visa@123");
		list1.add("anak@123");
		list1.add("kaki@123");
		list1.add("amal@123");
		list1.add("raja@123");
		list1.add("nars@123");
		list1.add("elur@123");
		list1.add("mach@123");
		list1.add("vija@123");
		list1.add("gunt@123");
		list1.add("nara@123");
		list1.add("bapa@123");
		list1.add("ongo@123");
		list1.add("nand@123");
		list1.add("kurn@123");
		list1.add("anan@123");
		list1.add("hind@123");
		list1.add("kada@123");
		list1.add("nell@123");
		list1.add("tiru@123");
		list1.add("raja@123");
		list1.add("chit@123");
		
		List<String> list2 = new ArrayList<String>();
		
		
		list2.add("Adilabad");
		list2.add("Peddapalli");
		list2.add("Karimnagar");
		list2.add("Nizamabad");
		list2.add("Zahirabad");
		list2.add("Medak");
		list2.add("Malkajgiri");
		list2.add("Secunderabad");
		list2.add("Hyderabad");
		list2.add("Chevella");
		list2.add("Mahbubnagar");
		list2.add("Nagarkurnool");
		list2.add("Nalgonda");
		list2.add("Bhongir");
		list2.add("Warangal");
		list2.add("Mahabubabad");
		list2.add("Khammam");
		list2.add("Araku");
		list2.add("Srikakulam");
		list2.add("Vizianagaram");
		list2.add("Visakhapatnam");
		list2.add("Anakapalli");
		list2.add("Kakinada");
		list2.add("Amalapuram");
		list2.add("Rajahmundry");
		list2.add("Narsapuram");
		list2.add("Eluru");
		list2.add("Machilipatnam");
		list2.add("Vijayawada");
		list2.add("Guntur");
		list2.add("Narasaraopet");
		list2.add("Bapatla");
		list2.add("Ongole");
		list2.add("Nandyal");
		list2.add("Kurnool");
		list2.add("Anantapur");
		list2.add("Hindupur");
		list2.add("Kadapa");
		list2.add("Nellore");
		list2.add("Tirupati");
		list2.add("Rajampet");
		list2.add("Chittoor");


		
		
		for(int i=0;i<list.size();i++)
		{
			
			String hashKey = EncryptDecrypt.getSecretKey();
			
			EncryptDecrypt encryptDecrypt = new EncryptDecrypt(hashKey);
			
			String encryPtedPassword = encryptDecrypt.encryptText(list1.get(i));
			
			
			String query = "INSERT INTO `survey`.`user` (`firstname`, `username`, `mobile`, `passwd_hash_txt`, `hash_key_txt`, `user_type_id`) VALUES ('"
					+ list2.get(i)
					+ "', '"
					+ list.get(i)
					+ "', '9999999999', '"+encryPtedPassword+"', '"+hashKey+"', '9');";
			
			
			System.out.println(query);
			
		}

		
	}
}
