package com.itgrids.eliteclub.service.tests;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.itgrids.eliteclub.service.WebserviceHandlerSevice;
import com.itgrids.eliteclub.util.IConstants;


@RunWith(SpringJUnit4ClassRunner.class)
//ApplicationContext will be loaded from "classpath:/app-config.xml"
@ContextConfiguration("/applicationContext.xml")
@ActiveProfiles("dev")
public class SampleServiceTest {
	private static final Logger LOG = LogManager.getLogger();

	@Autowired
	private WebserviceHandlerSevice webserviceHandlerSeviceImpl;

	@Test
	public void test()
	{
		/*
		System.out.println(webserviceHandlerSeviceImpl);
		webserviceHandlerSeviceImpl.loadObject(2);*/
		String calluid=UUID.randomUUID().toString();
	    String phoneno="9505485043";	    
	    String voiceid="101";
		
		 RestTemplate restTemplate = new RestTemplate();
		// String url="http://103.241.182.18/ConVoxBCT/VoiceApp.php?calluid="+calluid+"&phoneno="+phoneno+"&voiceid="+voiceid;
		 String url="http://www.brpreiss.com/books/opus5/programs/pgm02_01.txt";
		 String page = restTemplate.getForObject(url, String.class);
		 
		 System.out.println(page);
	}
	public static void main(String[] args) throws IOException {
		ArrayList<String> list= new ArrayList<String>();

		list.add("4bce787a-f486-44f9-ae2f-11eedbb9ad4e");
		list.add("fb8ac4f8-59b8-460d-a48b-012986655241");
		list.add("9adfcacc-d2ab-4830-a5b1-9b3a0b7c18d5");
		list.add("33291cbd-e94e-4e79-bca3-de7cc8cebad6");
		list.add("9f417042-233d-4445-ac0c-c7f0b44a1005");
		list.add("5c424752-c9a2-4c10-8633-658c1b60270b");
		list.add("226ea17c-4faf-45ad-b669-79746ffa7697");
		list.add("0ba1d2f3-f5b8-47be-973c-9a0b59abb901");
		list.add("58194e00-49dd-4f3d-a859-71d07f6a9b61");
		list.add("07831077-f8c5-48ce-8e09-f4d91624ca25");
		list.add("73d6b30d-856c-4675-9ae0-a910e3f8b98f");
		list.add("920e2343-e594-4c59-b0a9-d5a1476173ac");
		list.add("e0957761-de38-4d44-a321-471640b6fe5f");
		list.add("a108ac21-6b63-4f3e-ab74-b9d489594f3d");
		list.add("cf498daa-a4e8-4251-a09e-c6bfbe4ba0ed");
		list.add("1ce7e693-4f96-4bb5-928b-22b5ad8c9b9a");
		list.add("66984afa-207d-4e8f-bf7f-51baa96e0464");
		list.add("d0e052f7-064f-41a5-98e6-908528e4bdc7");
		list.add("8382e8ce-5143-4e90-91a3-31090685b24e");
		list.add("b63076e0-45a9-47e1-8c8e-438ab0c5d3dc");
		list.add("a37ad5fe-7e22-4f6f-ab67-39ee73977f75");
		list.add("5f4dc1c5-5194-48d1-a772-4c232d1d489b");
		list.add("4fe2bc99-a122-4b9c-8929-a3cbd2a46b27");
		list.add("4caac26a-73bb-4596-bc3c-75148c2e41b6");
		list.add("f42e0c37-5e5c-44eb-9544-4a25be11a224");
		list.add("386a5e95-6f86-4d64-b66f-1a2bc1dc8c07");
		list.add("bf22b43b-9e4d-489c-be9c-a94cadce0845");
		list.add("219e2f8c-2cea-467b-b73d-ad47eb612ef5");
		list.add("78fc83a1-3cc5-4710-b915-9175af18a0a6");
		list.add("fa4cc1ba-705b-4d15-afe0-ccfa0dca7f12");
		list.add("e5798836-599c-4ede-b911-62ead13305ad");
		list.add("43524c7c-37be-4711-a977-de037e22e208");
		list.add("1cf8be63-b8bb-46e5-b468-f673f1ed946c");
		list.add("f296ce79-a3ca-42b0-bc91-17a33cafaf5f");
		list.add("ac834b57-6c5d-4ae5-80fd-761834e0fe07");
		list.add("6cec8f0f-8445-457c-ba7c-8fc17dadfd2f");
		list.add("c7ca76be-381c-4b69-9755-0aa0f25de23b");
		list.add("0965a7b6-0eee-42bd-84b0-06b849461fc1");
		list.add("ec5159e0-5516-45b7-83db-658d2bd1f015");
		list.add("1a6e4bee-b20d-4593-9e7f-ac01c152825c");
		list.add("e12298a4-c200-4c6d-ae32-ad2e93f8040a");
		list.add("204b2ac3-d731-45c9-8fe2-0f636819fe77");
		list.add("bc40ad30-19fc-4877-a331-6be0badc2900");
		list.add("bbbf11ee-a3c7-45b5-a2e8-f3c221ffa63e");
		list.add("3fea9fa6-de08-4354-8e6b-455f9ba74094");
		list.add("3093be27-e551-4ac8-8b0e-73a10e439262");
		list.add("d25e8804-1575-4d3e-b8f7-495f4181a8c8");
		list.add("28c85c07-fc6b-43a6-9548-20c2151b6cda");
		list.add("3c570058-4aa0-489b-988f-c8bdb0186fff");
		list.add("f2188c09-8c0f-4273-b747-20d747239fc5");
		list.add("0f67a4dd-76e3-498d-805e-ed1bcf31b44c");
		list.add("6bcfb716-e077-4810-a09e-294275ed4325");
		list.add("43932992-f7fa-4861-bd39-de5007e96dfb");
		list.add("619b92c8-e1be-4ee1-84f0-f517df06b96d");
		list.add("054ce3e9-7172-44ae-929e-7a4130427c4a");
		list.add("cfbc3f21-1af6-4779-8fc3-41f8cb682f4b");
		list.add("7eb77b40-759a-4922-b30f-0cb668a1d274");
		list.add("9b8e3b48-99b9-4510-a290-9b217db02d52");
		list.add("46a9d064-b49d-424c-9476-bed2021373bb");
		list.add("12d06de4-a054-4627-8909-df6175b75fd5");
		list.add("5ddea23a-f8dd-49e0-9627-be587084e36f");
		list.add("2280b879-4e57-4448-b635-647a129cef11");
		list.add("d7ef4a5b-ba0d-4839-bf65-03afc2cd2602");
		list.add("4ed93961-ac40-4efb-9dc5-80a8205411d0");
		list.add("d961337a-9c41-410c-9a84-94b35eda40e0");
		list.add("dfd285ea-701e-4fc7-9da0-8eceea58487d");
		list.add("5f451b7a-4f35-405c-8c75-777e03a2afd1");
		list.add("c17588b7-36e8-49b1-bb8f-dbc13f742b3b");
		list.add("c8cd56ee-d6d9-4b74-86b7-9924125edb8b");
		list.add("f97c2b35-5c78-4f01-8463-c3aaaafcc00e");
		list.add("9ece4425-496d-4ad9-bdb3-b40786366aa8");
		list.add("ef149250-310d-435b-923d-10ac0f959fb5");
		list.add("b800f8a3-2671-4572-94ac-77746d7c1fec");
		list.add("49b759bb-ac98-4bec-bf2d-531b91c996da");
		list.add("7858595f-26d8-4e7e-b25c-52d6665258fc");
		list.add("fb005d77-a202-4b13-8120-0f01d9e76649");
		list.add("8ba5f8b5-c613-4d5c-8e11-1b97664686f4");
		list.add("2b7d88c3-adad-4752-bc8d-a3653def90eb");
		list.add("08d66379-9aec-491e-af77-1ca09239136a");
		list.add("9a9b4b45-c29e-4df4-a21a-faba0b9ec3f8");
		list.add("b53b04da-f0cb-491c-ab6c-7735f5206ac2");
		list.add("79ccaec2-fab1-4e3b-a5a5-252b2718b902");
		list.add("255cd275-a5dc-438b-9130-df015db439df");
		list.add("54b3bdfd-0808-40c7-be91-2a1dae031521");
		list.add("b354fc61-8b49-4eae-a627-217d53d91a9c");
		list.add("1d91eeb1-abd3-4a87-99a2-2d3f2eb219e2");
		list.add("758abcd7-f34e-4826-b53c-509566f5b11e");
		list.add("90fd5ca5-fe2b-4067-a35c-4476bc274b38");
		list.add("6ac10f1d-ffe3-42b8-8745-ee170c2652b1");
		list.add("87f60d27-3e2e-45bb-89be-9c74e1f2b7ac");
		list.add("ebea5162-4bd1-4475-8537-9cb08e40c395");
		list.add("15f7a382-3eb6-480a-af55-c1b46c80f0ac");
		list.add("b4803333-e781-4dff-8c02-71461143916d");
		list.add("73d32676-fc2d-4939-a270-cff7c2d0a87d");
		list.add("c685fbac-724b-4857-aced-8fcf0668d0d3");
		list.add("6781df56-6412-4508-bbe9-9be61f272933");
		list.add("8d3957dd-5134-4af9-97c8-1b5826cd480b");
		list.add("10428c55-7a50-4190-8ea9-bdd772aa30a1");
		list.add("6fb56d35-d36f-4102-983f-6ea7d67b9384");
		list.add("88009866-30d2-4f75-b5db-1b30891f16b9");
		list.add("a8a2dc76-07d9-40bf-89b1-0c93d592ce7a");
		list.add("473ab41d-5df1-4b4c-81c8-97a654683b64");
		list.add("4b06abd2-4b41-4ce0-886d-2ad79d35c784");
		list.add("7e6fe22b-001c-45e6-a847-9fc2a5404940");
		list.add("f556baeb-8f82-4c16-a006-7e3ae8b87614");
		list.add("599d92a4-12f1-4b85-a01b-111ed2f5936e");
		list.add("d02b8bea-7311-4d89-96a4-1db005459342");
		list.add("f641f3be-6667-4cbd-b77b-0246a5c28841");
		list.add("333afe0b-919d-4018-b8c0-e4fe8c1d27ef");
		list.add("8b5bfce4-925c-4090-82a7-0478fded229c");
		list.add("e7f99744-4862-407e-bfd5-00be74970e2f");
		list.add("4a837a1c-6fd1-4c3b-9a50-941b70de0ecb");
		list.add("e1634db7-8bb9-49c1-95cc-4d84f282f9c8");
		list.add("982d0e5a-ec66-4f52-a761-a94caff85449");
		list.add("8324b9ca-4493-4625-9187-2c5c59cb015b");
		list.add("9ad766c5-ec30-456a-9333-00f50f22c09a");
		list.add("122f7af3-9be8-4938-a8cb-321d70252b8a");
		list.add("a5750143-eb0d-484a-a431-198a5a71299f");
		list.add("fc80ee07-38da-4d5a-b0ec-5d000b34c79f");
		list.add("88274d64-2a84-43c7-9bd3-41fae2513009");
		list.add("694de92d-844f-4521-aaf4-bde9176cb78b");
		list.add("5192de9a-b4d7-449f-b272-3c075137c4f7");
		list.add("a214da48-2d1c-408a-b987-5417f58519ce");
		list.add("d313d734-091c-4de7-8803-09694734fec2");
		list.add("39968685-257c-4009-8149-6590f2332c50");
		list.add("f752bdc0-f9fc-43ee-9acb-48fe55d889ad");
		list.add("c2a7b8d8-8b85-4e6a-9edc-e0f09e9514fb");
		list.add("bfff786e-9b5c-46a8-b102-8686fcef3101");
		list.add("6892712f-03d5-49bd-b24b-3ed611579098");
		list.add("7fa99251-1f43-4593-96a3-f9120f972260");
		list.add("6f5f8763-b0e7-43c5-a39f-fa52e65c0f5c");
		list.add("7e15e3db-3bca-45b5-af56-0f90ad3d4d09");
		list.add("4890d5b3-7c02-4671-9a38-3c9d1ea912e4");
		list.add("c040ebfe-f802-4e55-a98c-343827baf852");
		list.add("da763211-4e14-4a2f-bff5-6398259dc715");
		list.add("f5f0d268-caab-477b-b2b7-84336ea19280");
		list.add("0c19ad55-9d4f-419d-b132-2a0481ea3f47");
		list.add("22ee0245-e93a-4a85-9a1b-4818675aa212");
		list.add("750b93b1-27b1-407a-b9f4-cd5a6b619be1");
		list.add("98d5e66e-e820-4460-9a4b-9d0d95cf5f7b");
		list.add("06d1ddc0-f307-4738-aa5e-bee58d53c14d");
		list.add("e1c68daf-c699-4cbb-89fa-6dbf1f3cde7e");
		list.add("a5a857da-336f-4270-8cf3-f41c9bcc4bfa");
		list.add("436479de-5bcf-4c4f-90a0-7a02ff24abbd");
		list.add("e451175d-f5dd-4cd4-a2e8-973ac8984e61");
		list.add("dd4d8f83-b2cc-4b0c-8264-f9993e2800f6");
		list.add("e047ddc7-c96d-420d-aed7-4bf637c810bb");
		list.add("e33f3db3-f84b-405d-bcb4-854e18588081");
		list.add("d0dbc5de-4a3f-494a-8959-253b14154b35");
		list.add("cdf61abc-959e-41b6-9dec-fb8a2739eafa");
		list.add("ffc7fa7b-503c-4ac6-9356-6ae78673a685");
		list.add("98f0cd93-d61f-4de3-988d-aa5e7eb73514");
		list.add("65f2693a-0afa-4d94-a060-e34600a21a22");
		list.add("60ce01c0-0acc-4e2d-af9b-b7eab9b5233d");
		list.add("81e164c5-a334-4c91-8410-295529e3b7bd");
		list.add("a516cca8-84d1-4e2b-b79b-494fdf1f1376");
		list.add("79099a7b-6a48-4ca9-9859-2dbbc5377f96");
		list.add("06862185-17c3-4fb8-b985-a64297b2d23f");
		list.add("b5f8adb8-8ac1-4d40-b4d1-d58b94df1c41");
		list.add("b799c4ce-5194-43f1-ab45-ea5a4a07566c");
		list.add("1eb5baf9-33e5-46eb-86d0-b6a4c728e4c3");
		list.add("660cee58-d60e-4a08-97c3-cf8f13dd3ce6");
		list.add("49f96f65-4531-47e8-8ad5-db4dec756fc9");
		list.add("134ec470-6199-4d98-9f82-b0e23f270902");
		list.add("49325a0f-1911-4c9a-9e95-d6eedc9e7f93");
		list.add("d4738839-8be4-4ef8-85f9-1cc49e76043f");
		list.add("19a02458-45ab-47f4-bbde-a5d054408d6f");
		list.add("f8cb810a-8f97-404e-b5bc-3f48c801d8c9");
		list.add("62d6859d-c7eb-4238-a871-a3287ca4007d");
		list.add("e5d3ac0b-c1a0-40ad-b691-669647d7c86b");
		list.add("b46fe80a-9c13-4316-bb55-a6299b54bae1");
		list.add("48834cc3-a5a1-4486-b41e-d34563339e61");
		list.add("8a36e4d7-350c-42da-ad6b-a20dccae142e");
		list.add("c3a61b90-ebe9-4b0f-98a8-29612ed631d5");
		list.add("598798af-d696-413c-bc39-e4c9300bdd8b");
		list.add("2c8b41c0-1d83-420d-8a0f-cbd3f3bc10ea");
		list.add("61942c03-ae3d-4d6a-9164-cf86b4edd5b1");
		list.add("82890f94-9e0c-490a-a506-1e67059af899");
		list.add("4d034004-d791-4f28-ab39-acb2e8a4afbc");
		list.add("e62b5a7d-bc95-4e8b-a087-4a9882ebc2d8");
		list.add("e7c10017-6124-4704-87f4-620d55cbdd5e");
		list.add("aa05de44-fe6a-4820-bd1d-d44f1c517f29");
		list.add("6f2ad568-da2f-4ae3-80d7-4e609e113fa1");
		list.add("8f574d48-6083-4a9b-a0fe-e6b8c9855802");
		list.add("33ca6099-7c63-493f-9a53-48d6bbf2089a");
		list.add("ccedc7f6-370d-4c5f-9012-27ecacec08e1");
		list.add("29dd17d5-9925-484a-ace2-fdefd8b57ef1");
		list.add("20d3308f-a676-4cd7-81b0-64aa703021d0");
		list.add("9697defd-4d33-4fef-a7cc-a1f4f606c2a2");
		list.add("6efa98c8-8348-4faa-a892-3b73edb8ec34");
		list.add("66da9c15-642c-49c1-abd6-a556187c6469");
		list.add("6f0306a9-01bc-46ed-a96a-e70ef9067fcd");
		list.add("1285c032-8890-42b8-bdda-23b528f25f49");
		list.add("e7ec1a0e-e9c9-45ae-9579-d13171a3a8a8");
		list.add("0f0059cc-0b8e-45b4-8b69-aa755cd00fd0");
		list.add("6f8f1cb8-8ee8-4598-acc1-a0d6128d22a3");
		list.add("dca0a677-6ba9-40bb-9b3f-a64bc1da1541");
		list.add("ab053c1b-3e5f-433d-9cea-d3ae4e1053b5");
		list.add("1275ec9a-5486-4940-9e11-ce0ac9972d7d");
		list.add("356936c9-8442-4cd9-8227-ea7d1edc5aa1");
		list.add("ee1f1447-07e5-4bc1-a692-eea87075a71f");
		list.add("ff37497f-3dda-45e9-9cb4-f695fa53c82f");
		list.add("32de277c-66aa-4071-bd87-adbf9132b4b1");
		list.add("2b5cfad6-565e-4d18-b377-b32080968f9c");
		list.add("e9105c65-1da5-4b4d-bd80-3c3ab965f6f6");
		list.add("7eb1ffdc-156d-4dd8-a751-61f5dfe11ba3");
		list.add("8cd97914-6112-45f2-8d31-8671d79ad6d0");
		list.add("9a2ab0a9-df5f-4017-81a8-21a352a405d0");
		list.add("122b7419-6587-470d-a769-6d3cf8f50fcb");
		list.add("680ce675-e2e1-4e42-a916-a92d874cd556");
		list.add("393a65be-6a37-4f1c-bde8-23b64525b007");
		list.add("becec144-2a87-421a-957e-992a59975104");
		list.add("4851047f-0d00-4f03-a8d1-027f7fe075b6");
		list.add("993197a6-6244-4b9c-8bbd-99eed29ed738");
		list.add("86df9d0b-320a-4358-8ced-bc72df826217");
		list.add("0a9195d7-1932-42ef-a23a-ac2e79d6ed43");
		list.add("ce3aa50d-990f-42c8-a1b0-786f06e13284");
		list.add("38ad0b23-b14d-4b09-99f3-147c699eea80");
		list.add("32a5faef-db40-441f-8300-4742df1c7979");
		list.add("b114d989-0d9c-4fb0-88e5-cb02d56ae287");
		list.add("523da3cc-6668-45c3-94ef-5c2f0d065fbc");
		list.add("66bc40c7-1f5e-458e-89ea-a0ac64bb620a");
		list.add("d43d545c-fbde-46c4-8644-b92a0a40185e");
		list.add("eebffe3d-6107-4472-bfa0-4db99c522671");
		list.add("fb1c11d6-0128-4124-962a-9a61093e668d");
		list.add("87582bb7-d90f-4187-a1e8-bec73d91752b");
		list.add("e758dbf1-ea37-43bd-929d-7a55c37f03be");
		list.add("75e66c0b-e689-4697-aee3-ad2d56523af2");
		list.add("98f51d9d-c523-444c-96bb-1095e787c168");
		list.add("ff3a86d3-055a-42d6-ab55-4bb3f657a2d4");
		list.add("7c2b7449-3b99-4127-9d30-7cf3abf1d1c3");
		list.add("3422e67f-c9a2-4ec1-a217-21fc6f716689");
		list.add("7d3e0900-c6bd-4efd-86bf-206731c44423");
		list.add("a7de525c-e403-4a77-bfff-f5731dd71f66");
		list.add("b924fb02-1b85-4595-9cdf-70c75b03aae2");
		list.add("e29e18ca-af59-4415-a135-5e2d3bf25568");
		list.add("45e958de-ed47-458f-aa49-0f1cea530e72");
		list.add("17f571b7-3eee-468a-8abe-1f6ac731cbfa");
		list.add("598b54a6-3d62-43ca-b7aa-cef5120f069b");
		list.add("1d4a7d6c-b86c-471f-9315-17e31e91f4e2");
		list.add("1a47a57d-f8a4-47a5-8c13-c55586eb8745");
		list.add("5d21e2d0-2318-4163-8014-6b0972492bc5");
		list.add("9a1ef1e5-0851-4337-b45a-7c64ff2bfa32");
		list.add("5c34cbe4-c98b-4133-b19a-e0ff3feb2bd7");
		list.add("70900728-6ff3-4de9-a3fc-06921d0e992f");
		list.add("dc357327-8988-4b4a-aecd-ee58ee8f2aa4");
		list.add("7674fe83-4d19-4fdc-bef7-6997b569a140");
		list.add("f3285b37-25f5-4cb0-9408-25df679ddb9d");
		list.add("8ff9bdad-7f0a-4f5f-9528-10cbc55e8f06");
		list.add("9fd0cbd6-d739-4b47-9213-8eddb961ee78");
		list.add("ba0432f1-a08e-472c-a316-bd1b4317aa01");
		list.add("3c0bba3c-21f5-4a43-935a-11f42fa1063f");
		list.add("f4ed99ad-495f-4db1-8927-20f4505d8584");
		list.add("95166234-0102-45ad-8ec3-04562d99adc1");
		list.add("7bb8ca0f-1ec0-42b8-8276-b9db22620671");
		list.add("feae0493-6d7c-447f-b9d3-8161751041d8");
		list.add("74132775-9dd7-47c0-a92b-8668a3f3d581");
		list.add("c87607ce-d085-4a41-ac96-7d2fe13e0db6");
		list.add("4ae84c29-7c63-4171-9f3c-6205d731d8a5");
		list.add("8fbdcd7c-2eec-48ca-9876-3bec35ca90fa");
		list.add("2ff1b060-4c6f-4e00-8932-ea1e85517c5b");
		list.add("61dbd578-3a71-42a9-ba9b-19235b5355e4");
		list.add("82449361-85f1-4385-9c65-773aeb31c132");
		list.add("031127c2-f001-4305-b2fc-fdef8e68a86c");
		list.add("68661312-f3ec-42c1-a590-402d811121d8");
		list.add("3b520e26-641d-4a69-bcbc-41bd9d8ffa0e");
		list.add("f8bc2dde-3113-4ceb-bca0-a706fd69fdea");
		list.add("3d4fd60b-7f0e-4a04-aded-bedd9076e132");
		list.add("ca2c726a-5fd8-4b6f-9bcd-85e7818c3911");
		list.add("832fe5db-5c93-49cf-a125-d959b364605d");
		list.add("d35e2742-d3a4-4ea7-a9b1-556fc1176e34");
		list.add("4c007fbc-71e8-4ee1-8a98-f883d75a09d0");
		list.add("052ee73e-5431-4839-82a5-9ffb765ca82b");
		list.add("9519c4f8-efa8-4306-9954-627e0ac394ce");
		list.add("eb293f6b-2af9-49e9-8926-12dd12da6e77");
		list.add("9d559557-6b31-411d-9d16-377c40ca5f95");
		list.add("e482ff87-5f30-4239-8caf-a3ec15a6d9f8");
		list.add("c95fa3cb-0b6f-4d4d-ba15-e73396c4d1c2");
		list.add("cda79261-f9bf-49af-80da-894a9dd80795");
		list.add("47576981-7ed5-4168-afd8-ec5f48606f81");
		list.add("782e5f0f-33c9-4bc4-bbe9-7b04ab2c22e3");
		list.add("a64d6b67-a7dd-4e07-b599-aa61b02432cf");
		list.add("1c741c82-bc61-454e-83b5-7107377997cf");
		list.add("f9c0d455-fe91-4603-8bc9-2484c67c8b7f");
		list.add("ebfd4422-63bf-44f4-84bd-b7595a3082dc");
		list.add("16766fdd-9553-44ca-b512-82850728d84b");
		list.add("8578225f-ff0b-4c3f-ac38-11a08ac7435b");
		list.add("5584e80d-85a2-47a0-bf2e-2f51727cd0fa");
		list.add("41386ac6-d0db-42ad-b099-d45812937583");
		list.add("fd864010-6ffb-4743-ad6b-6ad5bf932d59");
		list.add("126f9e8c-02a7-49d1-878d-72eabeebf249");
		list.add("2462bb2a-143a-4ee0-aa2d-998f0d523bdc");
		list.add("0f989fc8-f14e-4c67-ae4c-3b725ecc7660");
		list.add("8ab480c9-3e58-44b3-a156-61b3cc748f6c");
		list.add("85ea28f3-d2c6-421f-8330-e05eaf97bfbf");
		list.add("86005282-d608-4447-a1f3-65ecd5879d24");
		list.add("cd04ed3e-f5be-4e40-b1e3-4e133df2fa13");
		list.add("921f84d6-e706-49fb-a671-974fefee0d76");
		list.add("9863abe7-b175-4b8a-a84e-f1e15702587f");
		list.add("d278acc7-3bd7-4d8f-8c6b-444929ea72dd");
		list.add("0a01f9be-2b16-4182-b0ab-b9cbe1ed405e");
		list.add("411f6bae-7453-4abd-a7c9-4ac12137613b");
		list.add("0f605f22-6674-4b59-86da-c4ff12d4de96");
		list.add("c25a3ffe-f5fd-422f-a62c-e33a913d33d7");
		list.add("7e137374-6870-4aa1-83cb-33f5bbe5cc3d");
		list.add("43e295d7-fe0c-4372-9cb4-899a7f5e8423");
		list.add("e7eb8e18-8259-4e6c-8888-49f65d5620d6");
		list.add("e7b0b004-65a7-4f18-9bf0-9e556076e981");
		list.add("559b3971-f461-4641-a41e-82d08172062e");
		list.add("63128758-564c-4d5a-bbdf-6b704e180044");
		list.add("339a7da2-24a9-425d-9c24-3c373e3fd874");
		list.add("b18eac0f-67de-4c91-9bf3-a827e68f73bf");
		list.add("637c02dc-1126-4755-9539-10de0850e3c7");
		list.add("86fc4826-7a33-4151-8f7c-02bb2b8009da");
		list.add("988fdde2-b380-445d-b7db-73e8f7d365e8");
		list.add("54dda0e3-2a09-4420-b316-6ff26e4b3148");
		list.add("c213085e-152d-48b1-9843-df41c6a1d20e");
		list.add("461d6c5c-c1bb-4325-a216-f0360356982c");
		list.add("d78539ff-671d-43e9-80a8-ecb23fb54127");
		list.add("f6084294-78fd-4e75-9be8-ed36f29d13d4");
		list.add("5e76abe5-ab54-4522-913c-6ff5d7b19a05");
		list.add("99943b45-58a3-49f4-8d16-18663d496eb8");
		list.add("41364378-f167-44c7-bc5f-c05d02f34bb7");
		list.add("c4489fbf-c5fa-46e4-a8ae-ca0cc60a5b7b");
		list.add("99751fdd-ab3e-4c14-8e11-76367855f47f");
		list.add("754ac3b8-21f8-4611-8964-6e970399dc91");
		list.add("5daa4fcf-e14a-4606-b21a-46065691e018");
		list.add("3aee2d56-ace3-4987-a96f-b6175f4ceab6");
		list.add("523188f5-0e05-43e8-aae5-238ffba03f93");
		list.add("ebc991ad-d03c-42bc-bff4-778fcf901dd9");
		list.add("ee361ae6-41a4-4805-9068-1aacee552d5d");
		list.add("6f761390-1b70-40a9-8ce1-037747e4d167");
		list.add("c8c3bab9-0d15-470f-9013-7243bf279d19");
		list.add("a281c554-9aba-4a75-b963-f7c18484e308");
		list.add("f51d5945-100c-457f-a968-487876170547");
		list.add("e503f427-a06e-4f1e-a19b-e0fde831498f");
		list.add("e22218c1-4af5-4844-aaa5-dd7c96aa5132");
		list.add("3be62544-6214-4f74-a99f-0eb38fd6e25b");
		list.add("fd231aae-e010-446a-85ea-3910505526f6");
		list.add("d45a2221-9958-4b59-9f52-d2edf19cdb5c");
		list.add("015fa64d-b67a-454a-aedf-ed455d1c18b2");
		list.add("04e93be9-c193-4ad2-97b5-b0a2a14428e4");
		list.add("ad828b16-e8e4-473b-88a9-2ec589108311");
		list.add("c078c40b-c8eb-4265-9e17-2580cd8fd77b");
		list.add("6a66d1c2-c021-4c1e-aabf-f2d10375df5a");
		list.add("15a25386-8e1f-4d07-a1f2-0fef93dc8650");
		list.add("8f57ff5c-2c50-44dd-bb73-a34fb14c590f");
		list.add("24412aab-16aa-4f0e-bd68-5fc931c237b9");
		list.add("5d88a276-15c4-4d55-bd0d-3eb6ba161aaf");
		list.add("21279e2c-9714-4397-9a19-6c51e5b1e32c");
		list.add("3f0ecadd-23c5-4e73-be4a-6abb1082dd85");
		list.add("13d28680-517b-4526-b05c-bef9fcde4eba");
		list.add("70ac9c0b-2adc-4a7b-9b64-1de6ee9fa842");
		list.add("0d31f4b5-bc65-47b8-a8af-0f9e8d5d00f0");
		list.add("0cffc523-f201-4a47-ba3a-7a7bfec84480");
		list.add("27099cdd-078a-426b-9a72-3473ae8a114f");
		list.add("2363bfbe-11d3-48e5-9e29-63c16d7d1630");
		list.add("661a6afb-a1e5-4c2b-9523-e787102c18d6");
		list.add("c38b64c6-6f20-4902-aee2-0d516d12cead");
		list.add("8c7b8e6d-c58d-4eec-adec-b454882f49a4");
		list.add("8ebd478a-6dae-48cc-a001-733b03075360");
		list.add("99236947-f5a2-4af4-baab-0d03023e1f6c");
		list.add("ef02de0c-dd62-4d78-a5dd-45c831ac8946");
		list.add("e7a6960f-15b9-480c-bec8-4fc3197c1706");
		list.add("e9f7ea28-7ca2-444d-a6f1-bbba67302ce6");
		list.add("480e152c-2e97-4317-97c9-2ffe4a368eeb");
		list.add("371d7dc5-48e9-4f81-92a8-e66062e5abbd");
		list.add("ee206c37-1aff-4fe7-b884-25da26418053");
		list.add("9e15efea-4cae-401a-b37a-ede1722bb3e6");
		list.add("9547c611-5f1e-48f3-8e09-bea5dc8c463f");
		list.add("651c929b-4cb3-4e2b-a75b-8c1bd9bff0a6");
		list.add("d459d360-3777-486d-8c5e-e1abf67f2d4e");
		list.add("7a035d20-99c4-463c-b71c-108765886123");
		list.add("10d31a59-2ace-4fcb-abc8-d705ca3b2f75");
		list.add("82fcb272-00c3-4a0d-af03-c3dbaf34de67");
		list.add("69b588a6-8c33-4c31-b49c-9261520f1add");
		list.add("5352b2b9-b322-4e97-8df6-35f0f9a7d8ce");
		list.add("a22a5f30-bc78-493e-b637-470e945e2055");
		list.add("2d09e2ab-58fa-43db-9fb5-8cb126882470");
		list.add("127d6978-c3ea-430f-af62-30f063e95d55");
		list.add("10b5cc01-84be-41af-b7ed-eac5b148a078");
		list.add("bba278c8-d8fc-4771-803c-197f91c0200c");
		list.add("5cb7f359-32fc-40f1-8706-65b90ef6a81d");
		list.add("b93c1126-a487-4437-8051-36bc434a529c");
		list.add("ef559a4c-3c75-4795-a635-27d4850af335");
		list.add("7203669b-09d0-4ee9-ab1b-544efcafaa35");
		list.add("1474bcd2-b245-4116-a7d5-d57604b48e6b");
		list.add("3bc3c685-18ae-4d59-839a-aa7e85a7bd1d");
		list.add("30ce72cd-eff1-4269-8af1-afbc21899c15");
		list.add("e522678e-bf5a-45c4-adc8-7df0555b5176");
		list.add("0654dfc9-9363-4364-9f82-041c267dfb1d");
		list.add("ed28645e-288d-4f11-b711-11aeb0460472");
		list.add("d1febc20-da8d-4137-8f98-4be655c48ae4");
		list.add("046c8f41-0085-4403-ba82-f0f952b86fdd");
		list.add("7c884d83-3e49-4a21-9bdc-9d0b3ffbcf5d");
		list.add("d8826928-43eb-4675-9651-74475b178d5c");
		list.add("fc1f3ae3-84bc-494d-8cd2-2f765c6dd32a");
		list.add("243c57af-dbc3-4928-87a3-3e9397c1d220");
		list.add("620b3387-928b-46fd-846a-d816ae5826d5");
		list.add("4096f060-e2f7-496f-b88d-b4d8ad406735");
		list.add("f5f89c6d-8436-4604-9820-3ea518820f9c");
		list.add("338980fa-c28c-4eea-9b07-aefd1ca2ccb4");
		list.add("4c9746cc-f4f8-498a-8a95-87a8395bbf31");
		list.add("f7cfcdfc-f671-4cf3-ab69-282e0f44f1a3");
		list.add("9aec06e1-2a5c-4cd9-87b1-dc8ad0988603");
		list.add("86281d8d-6192-40d7-a4cf-ea4e6bf8cff9");
		list.add("0729a7bc-6499-40fc-af1c-cc9b1e8e342e");
		list.add("af004351-9604-4eff-9411-5e5c974bee46");
		list.add("6137f60b-cf3f-40c8-989f-9395d29d9c90");
		list.add("52bd7a46-beb1-4d6e-be57-1fceb37ab690");
		list.add("40a64cde-5242-4e62-ae04-96809b46d2bb");
		list.add("8733c2fc-8b8c-49b2-b641-2a5ddba358ae");
		list.add("16be0ef0-38a0-4f41-83e8-244ef3853715");
		list.add("65d2936d-4c09-494b-8d4e-fcf6eacb2c75");
		list.add("5b403148-03d1-4332-b7d0-b6097c031035");
		list.add("28b22c2b-f52e-439a-b6e8-a45f73557b7f");
		list.add("69a5ebc7-5c35-4864-9b62-00bb51d32e0a");
		list.add("f8ee5c06-5082-47fd-b242-f2802ffba60f");
		list.add("ad1d80e1-c125-47bb-abe8-852266b21f32");
		list.add("546d4d41-74ca-4d4f-ad29-5537475ddf55");
		list.add("fb730ced-9650-40b0-a243-3edcbb88fb96");
		list.add("9c30dbef-c476-4f4a-bfda-c3ba14446236");
		list.add("2ccc46d8-0227-47ca-898e-deabbb37c4c7");
		list.add("02a47430-a4de-464a-97f3-4c934274907e");
		list.add("c321d048-2e3b-4a2f-a415-644c86ae3e66");
		list.add("d7889b5b-52ed-4a8a-b09b-462af28fac30");
		list.add("7d492d47-3efa-44b8-beb8-ea5794047d2c");
		list.add("3a3d8603-74c1-402b-9201-a3c96a24976d");
		list.add("bfc0a677-17c3-4628-94c7-faccf9c0ef31");
		list.add("1208f3c1-c9e4-42b7-a500-91128cb5b06f");
		list.add("1e64683b-d9d6-4162-914b-a9bee482fecc");
		list.add("53847ed4-7352-4cd6-8225-6c138ab97f3c");
		list.add("a722d6fe-16de-4bec-91a4-eda0917502e7");
		list.add("b7d2d230-c84e-45fd-a4de-5f94dcec6a31");
		list.add("c88b5bb5-16a0-4021-8082-6b6216cb0e4b");
		list.add("bd0a47a6-8388-4714-b22c-cbafaf7e6ec3");
		list.add("61489f5c-f113-4afd-8817-37051e28dd9d");
		list.add("4150b4f2-216b-4bb8-9138-9a830c1f79c1");
		list.add("8b03fe4a-f2d3-4dd1-bc2c-02c6f771cb04");
		list.add("db99df16-e339-4273-8700-ca9eb3f37afa");
		list.add("11c649c0-bc38-43eb-8a8e-d26690ad56c3");
		list.add("793b9899-eda7-49d1-b4a2-4d116ed3259c");
		list.add("e2aadbc9-e653-4636-b97c-a91666a4da86");
		list.add("70a26ef3-c054-4b4f-b75e-598527a366b2");
		list.add("ae6f023a-c40b-410e-8d30-c236cf665056");
		list.add("0977ba93-7f69-4764-818e-3195796a7033");
		list.add("7171b43a-ee43-4d51-955d-cc6ea2d83a0f");
		list.add("a64f3b31-25c4-473e-9f0c-1e34d238a5b1");
		list.add("6241cad7-f573-412e-bfa4-bb8be5e63ad4");
		list.add("959c1010-0dfc-4521-8f68-ffd692e8ff70");
		list.add("87e60988-a791-4d13-a38b-fbe1ae5040ff");
		list.add("9771b62b-32a9-45cb-89c6-2b22d84c49bf");
		list.add("2edc4477-08fb-4262-8089-30b12382a039");
		list.add("cdab0065-1d87-4e38-b91b-1dafa2f7ec39");
		list.add("4ccd19f6-f1e9-4818-a81d-e9c5c8586e64");
		list.add("3582cd11-1669-4eba-b31f-552507c76793");
		list.add("d2d2f29c-6940-4274-9f7e-51173c0a6f36");
		list.add("498f52b2-7d5f-4436-ab7b-35428f9a92f6");
		list.add("83f5ad55-907d-4ada-91c0-bd4b8c12ac7d");
		list.add("5b28cac8-6769-4de9-aaeb-d59f6ebcd11b");
		list.add("93887a98-99e2-4e7b-9c09-657828d07aba");
		list.add("b7b6a79f-0061-4682-8ae1-eaa92fe942cd");
		list.add("b33a6968-3526-48de-b566-fd14afe1fab9");
		list.add("7e108b9e-7a2f-4247-8a2d-e6b97b990e9d");
		list.add("46976f8a-13f5-4ebc-901c-c1b89d222ad3");
		list.add("be052031-a2fe-40ff-9f8d-8c7eb21ce7c4");
		list.add("ba28c662-544f-4d3e-a43b-326aadb62c61");
		list.add("57ecbeb9-8712-4bf3-9c71-8fe47fcb470f");
		list.add("8af82a3c-ea30-49ae-96f3-a91a2cd1b24b");
		list.add("d342b0fd-1cf0-49d6-93a7-bf409a339c20");
		list.add("95b75c7b-3bae-4817-93fa-1b8a7f8ac216");
		list.add("75b68d1a-6f5c-47ed-8b84-632b501178a4");
		list.add("064073f3-00ca-43f4-9eb3-294101ef08d4");
		list.add("7ccd3eba-6358-4ca7-9194-bc415a0e8c0e");
		list.add("1c935576-58a0-4124-b491-4860444a1899");
		list.add("5a2b843c-d7b3-4155-90be-47961023e100");
		list.add("975b3613-b92d-43f5-bb1c-f546e1373db2");
		list.add("ff9b4dd1-0878-415e-8478-af886711b4b9");
		list.add("988a8b44-eeea-4ec6-b4c5-6162c2d683f0");
		list.add("8ad4f2ed-e736-454d-985b-888b05a41edb");
		list.add("ca77b828-5102-4a52-8cb8-28da63e4291b");
		list.add("2026c34f-545a-45e0-b23c-10e0fc962f24");
		list.add("6dc75c35-6e96-4252-a9bb-8b5c4ab33b4f");
		list.add("d3639d5d-9d3f-4ccb-9817-693209b05354");
		list.add("bbe3b1d4-7ecd-4ba7-90a2-9f077071bebd");
		list.add("08209e7b-7e43-4aeb-aa51-6656ff0cfb3e");
		list.add("b767974a-b4f8-4bab-b0b3-f2c0a0f91025");
		list.add("86f8d888-d58c-4ab1-9093-757438e46bd6");
		list.add("b2868485-1612-4ac0-b6a6-0f77f88efdd6");
		list.add("c66ee0cb-70e5-4b74-a6f2-7ad19dde032d");
		list.add("0485175f-2f7f-41e5-9ebd-bffa9f69515b");
		list.add("e5c7c2fc-a36a-41fa-92ed-eb97e0a9ed8c");
		list.add("6df00aa5-434e-45fb-bc0a-dbd2ac3b1d98");
		list.add("243c57af-dbc3-4928-87a3-3e9397c1d220");
		list.add("1fbcd5b6-33a3-4924-84ad-1f98a8906ed3");
		list.add("f8e78f60-ce80-4990-a675-ed4c0409c67c");
		list.add("ef9703e3-0e2f-43ef-8313-e041963fa1f1");
		list.add("a28946cc-e1f1-4c52-a00b-b80debf58b32");
		list.add("bf2842c7-1726-4fbb-8895-6cbf34a55ffd");
		list.add("f8b5cc60-ad2b-4462-a4c6-52a12030f1d9");
		list.add("f0fd2d98-00c1-449f-8227-fcb57d6a2a09");
		list.add("b950917d-2a44-4dbc-8ede-a6363bb90257");
		list.add("cbe0bb68-103e-4be3-9aa7-a2597eba4e28");
		list.add("c8cc4d6b-92c6-4bd5-9c6c-a97b9972028c");
		list.add("82bd17d8-71ec-4a68-9ee2-4508f686faa0");
		list.add("d2e46128-aec4-4e79-87e4-57af0afef4e3");
		list.add("60ec68e4-f344-4cfb-9a29-c713f414079b");
		list.add("ed12d3c7-65f9-4bc8-8b17-025a931ceef2");
		list.add("a205de62-5596-459d-a7c7-1ef2a56a50a8");
		list.add("c95bafb9-3029-4b43-a5bd-fff46b19999c");
		list.add("b17931e1-9e01-4f75-b09f-87ae4d4db12b");
		list.add("f03d6e77-ec03-4de0-9331-4e5c30348b7a");
		list.add("2f0df6f6-682a-4d56-9881-3ea51af14f32");
		list.add("67734bc8-ca45-4510-97fc-4e1277be6349");
		list.add("4516c9d2-a2d9-4eef-a822-438f5fe874bf");
		list.add("b6b822f7-9bab-4e4f-b425-4ef4c25e0a4c");
		list.add("774174f7-4e88-4497-b0fa-67bcc690fa67");
		list.add("97d0e157-b887-461a-aeeb-798144557807");
		list.add("ab53033a-e17c-4a85-b477-b23152a80819");
		list.add("e8f49642-a234-4ebe-98fb-d165c7c9206c");
		list.add("cd8a3907-5569-4218-a6ef-1279bc7dc3fe");
		list.add("c0f6a229-b9fd-434c-99d0-38c5b399f37c");
		list.add("26c435e6-d59b-4cc5-b487-a103139ff7c2");
		list.add("5b06d488-546d-492e-bfbb-22449b864a3e");
		list.add("d755f07a-dc48-4f2e-b0c9-6b3fd243535a");
		list.add("1a56fd5c-bc40-4b14-bee5-9c6e49e6cfe0");
		list.add("8d72b951-7d54-40e9-84f6-992b5aca73a3");
		list.add("349cbba6-b47c-4878-b668-049112c59845");
		list.add("eeb2cfe6-e240-4905-b49d-7129afad764e");
		list.add("5a4e6379-2598-4ec5-99c7-5750c46a31d1");
		list.add("2bd282a1-0078-4e53-ac4a-2ea3ee96ba5d");
		list.add("dee25417-16fc-4933-924b-0bfd2961be08");
		list.add("3b55b26f-4233-42ed-a220-119fa662369d");
		list.add("7603d4a5-7f2d-4400-9c35-e2c1f88b8af7");
		list.add("52d651fa-4eae-44c5-b356-a6f0ed371e01");
		list.add("158a7bb8-6a7a-4c5a-aa9f-85b5e2e4ae37");
		list.add("774ba746-21d9-4a99-9142-f95b0bb1750a");
		list.add("9c583df2-6659-4f6b-ae5c-c70791c8afc9");
		list.add("f4bae3c8-6a78-4927-bf3d-d8b3ca40cf79");
		list.add("7b5623ba-9720-4d5d-85ca-3b624f620648");
		list.add("6ff6e10f-6fdc-4b44-9aeb-a3e8c7b27964");
		list.add("3da7ed1b-7a42-459d-90a5-24054c49d727");
		list.add("ccd66e23-5772-420e-af23-3923b7cb670b");
		list.add("d07dbd90-e4a9-4a58-89e9-ad386e2bc57f");
		list.add("c5cdeb21-dea6-4a94-a1d8-484eb24874f5");
		list.add("87d7e8e5-5bfd-4467-a91f-a05342fd82c1");
		list.add("6ede2d6b-3222-4a16-9c0d-1d931d0f11f3");
		list.add("8f3ab406-1ead-4aa9-bf85-4f014a956b81");
		list.add("1adc10f6-5077-4548-b922-525e22096967");
		list.add("5aebb922-bf40-460a-98d9-3dad491c4db0");
		list.add("462e4e3e-a993-4c5d-8627-fa2e77355255");
		list.add("e0bd5ab0-b070-40d9-a4ba-d47d142d1306");
		list.add("7a8c96b9-15b1-4deb-a9f2-7b9e673585b7");
		list.add("52442af3-d0c9-4abc-b5c7-48f5338f0998");
		list.add("211dc59d-82ed-4f92-b870-dd9d95d53da4");
		list.add("b67a9b5a-a7ab-483e-b7d6-2d335ceb025e");
		list.add("930fbfa5-4296-4416-bcf2-7c92ff735a18");
		list.add("596b06f7-4d76-409b-979a-87b759dfb6a6");
		list.add("c076eda7-4f49-4e53-b15d-a6dbe592ab3a");
		list.add("700a2e2b-0c9f-4cad-bcf8-021db9550789");
		list.add("c26a9942-7d90-4ceb-9128-a495837cee93");
		list.add("ef011d78-a2b3-486d-94c9-b8c712d55669");
		list.add("1989fd02-4f85-404a-b8c0-0171d6580f2e");
		list.add("6f163e6f-2b88-4604-8315-baff0b8f996c");
		list.add("928d544d-afc0-43b7-8f8a-61e3d0d5eb0a");
		list.add("882bd7b7-f782-4684-a63b-fdb34236391a");
		list.add("a81e1ed0-6171-4c3f-92e8-26b7884bd0aa");
		list.add("05836807-036e-4949-a713-2202c8254d33");
		list.add("ddb71282-6edc-40f3-b260-a26ac7e6b746");
		list.add("bdddead7-41b9-447c-b3e1-508439f8ad46");
		list.add("8bb0f068-ec79-47d9-86f7-e5034521f0bb");
		list.add("680ffbff-be71-46f9-b319-efe9b3f05ee6");
		list.add("46e0567c-02c5-44df-8c39-2db9e2b2cf08");
		list.add("ab48ae83-5b0e-43c8-8afb-cd1923ada10c");
		list.add("1f6d212a-99fb-47f3-959e-337c31481e73");
		list.add("b93a6a9a-1c8f-49f3-bab4-319f0d427694");
		list.add("c940c226-7c88-4d87-8e45-281658946f45");
		list.add("039ecddd-9bf3-4c38-bb40-27ed0cb4cb00");
		list.add("c786c019-f50d-48c1-a2e8-b61f5f70e767");
		list.add("4a938b8a-c880-4085-8ee5-7bbb427c6188");
		list.add("0ea3867d-dbf8-4679-9f54-390664579d6d");
		list.add("2ced794d-0de0-446c-91a6-26658c4c77b3");
		list.add("6dafcc6b-977d-4fe2-a0ff-31a60ce41c61");
		list.add("1662c6d3-a770-4477-99dc-733b11658d2d");
		list.add("944ef11b-dd45-4635-aa82-77ec970bcd7b");
		list.add("ab998e74-6bdd-4a8f-85de-67ebe3085e94");
		list.add("656e961f-b2b3-4e26-8116-d2f36295a9f0");
		list.add("8025af90-706c-4617-9f0b-960a021de046");
		list.add("46e20220-b12b-488d-a872-76cec5f1c91d");
		list.add("076554a0-1cb5-4834-a285-019078282676");
		list.add("2e343ef2-c950-4c34-b4b8-1221c8273d00");
		list.add("79d3e473-9a2d-40ab-98c7-ca9655fc0954");
		list.add("27a14a7f-2a00-4016-b3e6-103911572bb9");
		list.add("b59eb685-f5e8-4d41-957e-43997ebd8f69");
		list.add("02cca469-3cb1-4468-8f86-eb3d61cd941a");
		list.add("e9df5b4a-8282-4477-9f07-c563bd6a4d1e");
		list.add("2b85536f-fed7-4e0c-ad0f-65568b11a427");
		list.add("8be0fb8b-bb72-4c53-bf80-45d45029dfe1");
		list.add("9170ad82-e68f-4fa0-946d-e704ad795396");
		list.add("ab81e4fc-e8dd-4d55-b318-7b1b8975aa04");
		list.add("d26b8d3a-b722-4896-a667-e76a64899139");
		list.add("6af080b8-cb38-4de3-b246-9117e9a8a44f");
		list.add("aa1cffdd-11d6-4bce-969b-388c063256a5");
		list.add("5aec9ecd-ddc8-4fbf-87f7-cf4247eb0a2d");
		list.add("13922c79-52b0-4652-81e1-e1eb1b11cd4d");
		list.add("0f2559c8-7069-4806-bbdf-6ed1458e3cac");
		list.add("a626b15c-3a0c-44af-9dde-4fb0360c27b2");
		list.add("2d89fb40-199c-464a-932f-cd78d9ef1b88");
		list.add("43e295d7-fe0c-4372-9cb4-899a7f5e8423");
		list.add("b96d5437-4cb5-4f65-90db-7f893c65441b");
		list.add("e51c4be0-c51d-41e6-b586-719f9f5242a1");
		list.add("c23cc898-b1b3-4d3b-8f07-5286da9b4e40");
		list.add("e18d832c-5c9b-45ca-a9e6-6039f3542f0a");
		list.add("71cfeaee-b691-4627-abc8-80bf9fc5297d");
		list.add("349f9dfb-2930-40e5-908a-adb122310a46");
		list.add("726666e3-cf8b-4134-83ae-cb28bbc4a4a9");
		list.add("5370972b-bb09-403c-9951-2f21a3f0b3dd");
		list.add("b9751378-8396-4a9e-aafe-8313d4013cce");
		list.add("09a81801-8344-476c-bfd0-fabf4c246e77");
		list.add("5fa11dae-ca14-49bd-a3d4-5b4b00d32b27");
		list.add("3c202996-2484-4f50-b4b0-aad10a0226f9");
		list.add("454c300b-1998-4a34-9538-dd0e24e577b4");
		list.add("14237d5e-63cc-458c-9378-936d791e9b2e");
		list.add("7717cecd-6a77-4d67-8ad6-0c80899beb30");
		list.add("6ac94bdb-886e-40c2-961c-1e9d3c367723");
		list.add("dd194692-10e2-4eaa-bafe-d995ff6958d6");
		list.add("ee912c49-25a1-493e-9d15-bd1a2663f470");
		list.add("3003c80f-e783-4031-9310-d764c6cf5be9");
		list.add("3cc542b4-5273-4834-847a-aeab348f1384");
		list.add("f63e7d79-955f-4db9-ab31-edfeeea65b54");
		list.add("79e6a8c8-7096-4427-9e21-77d664c4155b");
		list.add("ba8c62f5-278d-4299-b90f-0de4b0e6c7aa");
		list.add("8c9eb3ec-ca5d-4765-82c3-f5d9d44c6701");
		list.add("7de47003-d4f3-4666-8bf2-6a5330fe6b38");
		list.add("196ca50c-4ebc-4bb9-ae68-67e42515a6ee");
		list.add("433e4324-d9b1-4dec-b20d-0d347e5b9e96");
		list.add("06b95f8b-69a8-493d-bd91-4ee98031177b");
		list.add("1af3533f-a847-45bc-9799-cdc410973768");
		list.add("d0964bef-dd38-43da-8c4d-f092ed31b3bf");
		list.add("8fbbadbb-7501-4cc0-aa16-fa6ad83f707b");
		list.add("e3d421fc-cefc-4fbe-b116-da34eabd3187");
		list.add("7603aaf6-f094-41e3-a293-e34ab5b52609");
		list.add("fc3072b9-9b70-458a-bcc6-b72e903cad57");
		list.add("fc355bc2-698d-42ec-a01f-4113a8afd2e3");
		list.add("211a9a5c-dcf5-4564-be9d-3f7cddc88437");
		list.add("4ab3a1cb-1e18-484a-b70c-7bbbfa51bf4b");
		list.add("e795c7dd-4874-48bb-bf6a-2a92ffd47cfc");
		list.add("d77c0171-a3b7-42b2-87aa-61b81a11c514");
		list.add("b0fc433c-0a13-4c43-854e-6628e9190f92");
		list.add("69740e96-09c1-478e-8279-2c9a957c8a9a");
		list.add("a16b07ce-9f3b-42b5-ae55-790158049a48");
		list.add("5a28664c-bca7-4c73-91a0-80cc92eb94c9");
		list.add("88125db3-209a-4ddb-bea5-a5e09286417c");
		list.add("a4823b32-a7be-4e29-967a-81ad2e4adaa6");
		list.add("46154486-1c93-4d99-8925-a91099628faf");
		list.add("67becbc4-3b0e-4ffc-817f-a65a3d5f1158");
		list.add("95ffcd15-9647-4f4f-a9a1-5e20ffdf1678");
		list.add("70635bac-e544-4d75-a12d-05f71b8cfcd6");
		list.add("5c4864c9-64f3-4f31-95f7-a5f3312a7909");
		list.add("4784796d-927b-4438-b6e3-33312c4dee08");
		list.add("ad87dd36-e1cb-46f1-8e52-f8f47d86dc1f");
		list.add("fd6ceafb-c870-4468-80ba-a0c4e74a8caf");
		list.add("e522b67f-f49e-48fb-bb5f-1f77b0fd0c66");
		list.add("d4bddee5-a41e-4621-9cfe-94e93ad51a83");
		list.add("75de0acb-21ba-4c9f-91c6-e68ee33abe2d");
		list.add("dec6b298-2d62-41a6-89fa-b82416770b8d");
		list.add("e21a4097-28c6-44b5-982f-42847e79b1be");
		list.add("d88d6175-6cf1-4caa-9f93-63e0572e0c6b");
		list.add("b076f30d-c0a0-472e-94c6-ce64080ae569");
		list.add("6f4d5f90-0cce-4883-a9c8-1f1f0e59e2d9");
		list.add("ed144079-c250-47fd-b104-215593e57e90");
		list.add("87050a54-5b4b-40bc-8b9b-7cd5524be39c");
		list.add("1951dfb3-020e-485f-a9a5-3ea4253301d9");
		list.add("a3e5d3f9-63f1-49ee-85fc-b9d8c66bb48c");
		list.add("698ffea0-bbd7-4f79-9f15-cfae58b520e7");
		list.add("0ec3fe33-b141-4b7d-b26c-5580af601c87");
		list.add("fa25de32-139e-4ad6-9145-890e1d9c056d");
		list.add("0d17a9c6-d31c-4a15-a256-0d08f75c51a4");
		list.add("86d39128-c0f6-49a5-ade1-1239e9d104bf");
		list.add("884acf47-7670-4dcf-8029-0f9a0c90f73f");
		list.add("77de1461-788b-4752-8953-fb1a221f6132");
		list.add("bf06d30d-4a57-4dd0-b647-562b77f07084");
		list.add("7d0d5015-b765-4b84-a683-bdd8d36c0135");
		list.add("7cfa9674-d8a7-4126-8f09-dd1b46990391");
		list.add("f37a12e8-b78a-4afa-b3c2-dbaebb1067ac");
		list.add("9fea23da-0f6b-47a3-a4ec-c119a6831912");
		list.add("ef69da7e-1c67-42b5-9d5f-46dcee9558a5");
		list.add("fd1743e5-f055-4eaa-87f5-9028aaf2863c");
		list.add("70e7f877-1866-4a99-893c-97722e17a8df");
		list.add("c4a0bc6d-b7bd-4c86-9c5a-7403bbea9296");
		list.add("eb70e5ae-c38f-48de-8258-6a7924419885");
		list.add("e50876d9-fb19-40d8-bc40-4098a8ede555");
		list.add("c097d81f-9608-48cf-9773-bf9ab3947475");
		list.add("80b71e7e-13ac-4c8d-b66f-e41c50b0ff4f");
		list.add("14b5dd8f-0c22-49fd-aa5f-ed9c137629b4");
		list.add("10038896-4fa3-4e0c-87d0-b8dcb24d5338");
		list.add("9d392a29-c4e5-40ad-879b-80ddd68a5636");
		list.add("afafd4a7-bd38-4b76-bcca-79cf7b6c7435");
		list.add("cdc2c322-45c9-458c-b56d-1f4bb6b16586");
		list.add("f736cd51-56f0-4cb6-8c36-f704951d94ba");
		list.add("8f5d5d4f-b017-4052-a68b-16cc99a80cef");
		list.add("f01aefb3-0cb7-48a5-9459-f1b3867f9ea4");
		list.add("e62c73fb-6b3f-46d7-9e2c-69774236abe5");
		list.add("9cd89809-533c-4dad-b598-36923473a9ae");
		list.add("fefdec02-a20f-4b55-9447-4fcf51de114d");
		list.add("44524d51-b1e1-4693-b4f7-57011a6420a0");
		list.add("b435d6e3-8461-4ef6-9a6b-e86b2fd09500");
		list.add("059384a6-de69-45df-aaa0-f67eb35a5cf8");
		list.add("f01b4df2-1a04-4e05-9772-8b42700f5512");
		list.add("6f631da8-3191-4bdf-9baf-3032d6b9fafb");
		list.add("90f56204-9191-4b35-b415-ab4c8f3172bd");
		list.add("a5b2701c-b9f6-4356-a06a-834365671d7a");
		list.add("6aba3a28-bcb4-414d-b20c-f8783d71d098");
		list.add("2f86b9b4-ad6d-4e98-9879-bb618ae427c7");
		list.add("7f1b24d7-7fe5-4e11-8e63-32b05304e153");
		list.add("c5b61959-cea0-4733-8769-887eefeeec1b");
		list.add("4f05281f-45cb-44af-8dbb-896d182de454");
		list.add("ca06e4a9-2347-49d7-8341-2fdacdce1c53");
		list.add("39833ad9-3ee4-4f9a-a5a3-306dfc10fdf4");
		list.add("b46c3bd8-6c8e-4080-80bd-ff602ed89183");
		list.add("a9b66ddf-fd52-42de-ad60-0e7c30ec28e4");
		list.add("97fd8f98-9e56-48e3-954e-a1154c55b106");
		list.add("f4945ad9-a788-468e-b06f-ed8dd8d531fe");
		list.add("0a8d57ec-62f3-4228-8dbe-353c0fb3390f");
		list.add("7cfb95ef-1c3e-4d67-92ef-99f9a3fc1b65");
		list.add("3f59bbc0-0ea2-4d22-9e43-56e9411582bf");
		list.add("6f9a7320-54ff-4045-a358-e9a17c445f03");
		list.add("d2f5c46f-ff5d-49de-baeb-ff797e3f23f7");
		list.add("0062ebac-b708-4780-8ad2-c23d4466a7b5");
		list.add("76c96981-7e12-4aeb-b77d-32236028ad45");
		list.add("deae8f84-90cf-4052-a33c-c85bfd001231");
		list.add("619f3ece-8030-482d-bcd5-453b5c7ebee8");
		list.add("b16ddb90-a6b6-4a5e-9fc5-ac2e8deaf6f9");
		list.add("bdf318cb-3a3b-43f1-9ca5-b2f8426a164b");
		list.add("e3aaa58d-c557-4ff5-a029-bb08b33dac1b");
		list.add("06fa07ef-4161-4b87-bd57-99f0d0718493");
		list.add("af58a712-24e1-4d43-adcf-d0cd78d8a61b");
		list.add("9d5d3575-dbde-4e36-b315-4198fa711b54");
		list.add("9f40068c-0c5e-4f67-97c8-474b43551f55");
		list.add("ee398476-cd2a-42c2-b627-94f9f97bc737");
		list.add("8c27f09b-623c-42c7-ae26-620cbb466504");
		list.add("2c21d4b1-bea2-4aac-9cc7-ada6d79a9ab7");
		list.add("2e9c1258-792e-410e-b241-8885fa4b0c5c");
		list.add("24d03f03-94ba-491a-91c6-27b6ed4e4a2d");
		list.add("63e436fa-5415-435b-824f-de852deda3e8");
		list.add("b7003831-06ba-41c1-9cca-e69cea135a19");
		list.add("578435da-dbac-41e0-be96-236fe5e07058");
		list.add("33a20563-ddb2-4e94-81ab-b85a8e364306");
		list.add("b0023833-1007-4848-befb-9e3b05a48a81");
		list.add("c3ea5cd8-a854-446f-ac18-602ab0a75820");
		list.add("e6faa926-139f-453c-9af6-e6b8afd7f827");
		list.add("d4aa1ce5-b9f4-4494-9cf2-8ceb2b23410b");
		list.add("b9900705-6d40-4e66-893e-b37fba9112c1");
		list.add("1a34ee4c-3ccf-43d7-a117-da720523515d");
		list.add("eb20ecc1-6340-46a2-ba98-ba42d5bdcc92");
		list.add("533f4e5d-d9de-4c19-9e87-6656be7dc331");
		list.add("4995d8de-3e52-424b-bdb1-043437952340");
		list.add("072e43a5-b0bd-4838-8492-fcc63561edd6");
		list.add("e57f7657-2d24-4740-b41b-0863f219c41a");
		list.add("c50f0b15-d128-4df1-973d-8ca68cba59e2");
		list.add("616f5ed3-1845-4dbe-8a2b-74065f33fe1a");
		list.add("9420bc30-5fcc-40ca-9d38-0c499f6660e1");
		list.add("d8c836d6-456b-4941-9616-5d4feb475878");
		list.add("13939433-c286-4eb7-8734-81f68e7dbdc5");
		list.add("e4e68696-013d-4218-82e5-8640c4d69cd6");
		list.add("9f6f5dac-6b59-4e8d-a249-284ca2498daa");
		list.add("40275b60-27bf-4421-87cf-6c1e24ffc9e4");
		list.add("2cf2240e-9cbb-4415-b575-096ce7a5d025");
		list.add("b13f4e41-d0c3-4fa6-852e-9a0cfc050d19");
		list.add("76fd1a48-c9f9-47e8-8571-a24da56e1270");
		list.add("213fe399-566f-4619-acc8-b995defad6a7");
		list.add("73362b22-fabe-40a4-8351-e22132e6d203");
		list.add("6ca487d3-d10b-438b-b98f-b47ed2753b9e");
		list.add("7b46746e-062c-4eb8-b0e9-7e9f66fa5bcf");
		list.add("567777be-2f53-42a9-b5b1-95e107a1d637");
		list.add("1aca6fa7-a2a5-4395-b72a-c324d04fe0a7");
		list.add("55c7ec27-b77f-4008-b950-27030b170bac");
		list.add("b1f03b09-c3b5-4147-ba32-00e155249587");
		list.add("232cf2cb-1c95-4234-a23e-65991e190521");
		list.add("601312a6-d20f-43ff-8c90-d0a4d8aa5500");
		list.add("0a0fca53-2957-4230-aff8-ef4138b679b9");
		list.add("db2b39f2-fb68-4b73-89fc-f97e38fff8da");
		list.add("b8e39dc1-9daf-48eb-8879-3e5d6d27c9b2");
		list.add("94ac6c35-893a-47fa-b2ba-93cc998e1e12");
		list.add("97debb85-e3e1-484a-9c38-ecbf6c52fe8c");
		list.add("2f5668ea-1c0b-4e88-92d2-8052a147b053");
		list.add("c80e5714-313b-4197-a577-c0538064c182");
		list.add("f6e55e32-a5e5-4243-9209-771b64af061f");
		list.add("76ccd056-89ef-47c2-96df-03c8bbd34c9c");
		list.add("046c019b-0f3a-41b0-832d-e9260eb757ed");
		list.add("d334ac6d-4fd1-45ed-a8aa-24859ddddcdb");
		list.add("835cfe59-b888-49c2-bc03-2222ce610638");
		list.add("be132044-8ba8-4de4-bc6c-148a969c169a");
		list.add("b3ee794c-1983-4c8e-9d05-3acdd8f0ac4f");
		list.add("e979047d-621b-41c0-b01c-0f12bf97cfdc");
		list.add("f28bb381-a4e8-4751-9aec-65ebdbbc1475");
		list.add("0eb0272d-4134-4224-812e-b978c1f91c24");
		list.add("fc3e23fd-4626-4dba-91e9-7e43e47a1da3");
		list.add("3f15c2ef-a8c4-4806-a472-dd58ef6de726");
		list.add("1bcb5619-4e53-4ef0-af95-49728be3d621");
		list.add("9ea46a0d-17c8-4e6e-b0ff-9b8b14aff9bf");
		list.add("6a0fa9dd-e323-4e8e-b121-de958e479583");
		list.add("88dd0241-3fb6-4c23-8230-fa521a77ea1e");
		list.add("8dc60977-f1c4-490a-8b97-0fadbeb12cf8");
		list.add("a0211ad0-df4b-4cbf-9a16-0a959487709f");
		list.add("29836863-9287-4bd1-8096-96e2ee3d0912");
		list.add("902b0188-a016-4544-880f-6fc0ba88fcce");
		list.add("ea88fa24-73bc-4283-8706-7ec8a7a29702");
		list.add("5c695fc3-daf6-4556-82e6-22f159d0893f");
		list.add("463bc571-b864-47ff-906a-3737725a94e9");
		list.add("6e5e974d-4ad6-4273-a93d-7f73da14c795");
		list.add("8611120a-22aa-4b1b-8478-4ec0da49804a");
		list.add("9545edc3-ce8d-49d6-9f7f-85ef9472a07d");
		list.add("50e85457-41d8-4d7c-8063-0a0854c0e332");
		list.add("76947ded-8b4d-48e6-a4e2-3c68fe64eae8");
		list.add("8439ccc2-b068-471b-99c6-f16e8f206690");
		list.add("964af12e-159d-42ea-b856-5892f2f902c6");
		list.add("642a45a8-fdf9-4474-9ae6-458848f820b5");
		list.add("6c5c1a4b-63ad-4344-bd68-faeb3abd4bff");
		list.add("b6392a9d-302a-407c-b5a4-1768dc3f7f05");
		list.add("44ff5152-baef-4c15-b6fa-cbc64106aad3");
		list.add("d657585c-600d-4413-8123-d76282909ded");
		list.add("7fd0e615-83e9-4ed5-ae1c-7cdd5af05755");
		list.add("8049999e-ac23-4278-9f6d-b065224de26a");
		list.add("7a244040-aab7-4236-ade4-7e2867c82a66");
		list.add("fce9abd1-c6ec-463c-aae7-719763ee9f6a");
		list.add("04a38a3c-a5c8-420b-b91c-18d8c6e50b40");
		list.add("d19b065a-3955-4da3-919a-c4064062db80");
		list.add("98cec0ee-1ff7-48f1-911b-0b8c10bca31a");
		list.add("2638c767-819b-4a99-b486-04693627b0b1");
		list.add("f115e27e-717c-4736-be94-9850f2f46ee8");
		list.add("d27c75ba-8696-4fb2-82bf-a3757031265e");
		list.add("0cda492e-8487-4de7-b0ed-b46ad60753dd");
		list.add("3f0218f4-1dae-4618-b3d6-6a3a8a20d3f9");
		list.add("b4142d22-0049-4edd-8d75-ba15ede3024d");
		list.add("61a3b214-9058-442c-ad59-5e61e512f200");
		list.add("21104937-5fef-4b5d-a803-74868d79e010");
		list.add("98767e6f-bacb-4c63-af2f-c9eb410b6f3d");
		list.add("76c393e6-89ba-4783-934c-de011095adb8");
		list.add("6efb8f2e-50ca-4f0f-a4e5-e8f4d206e170");
		list.add("dbceb94b-ebb4-4863-b9ab-929709e72300");
		list.add("c60bf819-d8a0-42b5-9d78-1c0a58dc22e1");
		list.add("05546089-fa52-430e-922e-955abf727de1");
		list.add("1a89533b-5cc6-4563-a6e3-fc5c30f34906");
		list.add("d6a18baa-bdb9-4972-86c4-5286499585a2");
		list.add("88fde3cb-0c6d-41b7-8f27-a6308605e7d6");
		list.add("d3dfaf9c-f95c-4734-81ca-7b8505d6430f");
		list.add("3d58d708-50ef-4fa2-b6c8-aac77402ebbe");
		list.add("8ae58ca0-39cb-46e8-9618-87b1357a9b45");
		list.add("7c070103-51e6-4171-a16f-b6fd15f383fe");
		list.add("90b2a697-411d-4ba7-9f7b-48f1e9fc2482");
		list.add("dbde84a4-88f0-420e-9cb6-103618aa54d7");
		list.add("b4ec7d1b-c834-4187-9693-40997a5b77b7");
		list.add("52ebb64b-5c7f-4e9b-8ed2-ec5bcde55656");
		list.add("91110874-eaf1-4400-b2d2-951510578fb9");
		list.add("5b2b9de4-bdd9-4034-8ddf-d82b3b346f98");
		list.add("f0489789-4fe2-4c76-b2bd-7b4d878c8021");
		list.add("93cf05a3-b3b6-4152-9d77-140403a87c2b");
		list.add("419e6a16-75ae-4844-9d99-916b1b875b6c");
		list.add("3843e719-3434-42db-ac0b-9c14bb537563");
		list.add("07689341-ff6e-42ee-b272-c64fc204b433");
		list.add("5d17ddfc-dcf1-48fb-ba27-eeec05ad686f");
		list.add("264e0a29-d49f-4fe3-a00a-229b1adccf25");
		list.add("2cd35cc6-9cdc-49d3-b33f-3e73e02776a7");
		list.add("1cb1bb5d-374b-42cf-a8d5-4de38eaca249");
		list.add("9a1cd9e7-77aa-4c4a-8953-54f3ed2aabb2");
		list.add("81ac77a5-654d-4282-9da5-b5665a1d73d0");
		list.add("ce591215-a976-4151-bce7-8b0c539473cf");
		list.add("44718483-22d5-43e8-8adf-3556c9e1de50");
		list.add("e79ac68b-5246-44f3-8242-1936051b7913");
		list.add("e0482d96-0695-4ae8-a24e-28fa853bc97a");
		list.add("bb5165ad-37fe-4522-887c-56e3b054e309");
		list.add("1c6beff7-d988-4fe7-b7bd-f625afa0815f");
		list.add("2349a64b-2227-4b31-940a-bf734d3c2345");
		list.add("9fbc499c-1b8b-4da6-826b-fa6c1ff63145");
		list.add("ef017c39-9edb-4986-87f5-e96bceef3bdd");
		list.add("f066c63a-b7d1-481b-be6e-551c52f11858");
		list.add("e625b206-a992-49e0-a474-0a576bc76667");
		list.add("97c58ef5-cd8c-46a1-b2c4-fefb715846ad");
		list.add("59f9b9f6-c54d-47ea-86fb-e1584a761c6f");
		list.add("386ba2a3-c838-49de-8f8e-fc39d727ab13");
		list.add("fcef3d77-67d8-4f03-a340-b3d84c6d5de5");
		list.add("7681de7b-915e-4229-981f-81a310f48327");
		list.add("518caf8a-54bb-4f1f-bfe9-ffbef415abda");
		list.add("acb53304-77c9-4a55-af53-c2941a53c5c6");
		list.add("fdb35a68-939a-48b5-81da-b411b91824f9");
		list.add("4bd033e1-26e1-45e6-af78-f411817906b7");
		list.add("0e581799-c6c5-46a6-92bb-481de5fc1264");
		list.add("d333a6a3-c899-4b2c-b368-104c7a3044f5");
		list.add("d2ee230d-18bf-4889-8d8c-227aa42dd524");
		list.add("261f1fb6-f921-4545-922e-f1b8a811cd4f");
		list.add("86366a08-3e10-4bbf-9680-cf8ce10811d5");
		list.add("1942efab-fb8f-45d3-8797-b9c56babb8dc");
		list.add("925f3436-2421-422c-a9d4-c664409089c5");
		list.add("837c58f4-690e-490f-b6e0-c7dc81d9c6a5");
		list.add("e89ebd98-a53a-45f4-9d7c-c7bf9e6167b3");
		list.add("245bfee4-b7d7-4ebf-be0c-07f6c3bc4cc2");
		list.add("26e672d4-24fa-41a9-a2e8-ba82c195ee91");
		list.add("111f89b2-e309-4336-8b10-953fb12af462");
		list.add("47316832-3dd3-47f0-a9f4-7d743f500076");
		list.add("a8ca5d29-1674-49c7-9a39-a11cd7b25459");
		list.add("497340f6-af4f-435b-85da-1f8986ccb9f3");
		list.add("6d5591a7-02c6-43b4-b564-b4a5cdcf229b");
		list.add("2163cb03-a459-4563-a7cd-e843ac40f860");
		list.add("5c865712-d3b4-4b2a-835d-d7cce4577ba7");
		list.add("3355586d-1197-4257-a765-3de1fec84e3e");
		list.add("01fe6725-2c9b-4190-98f2-7540ad65561d");
		list.add("c4e6f616-d57b-4041-8661-cf00109606b2");
		list.add("4245bb88-6f4b-429b-85f4-7e04b59210aa");
		list.add("2989cd68-60f0-44ca-832e-b09f6ba5802f");
		list.add("4ae0d363-671b-4106-959c-998374ba73fe");
		list.add("32c62e34-cf16-433d-9359-9cfc92ecdce1");
		list.add("bac0abe3-8aed-4880-a9b2-713b1206785f");
		list.add("b42a081e-a987-425a-8748-b3cb0968c353");
		list.add("5ccfbca3-c075-44a7-80a9-ea6302daeee6");
		list.add("a8db723e-b358-4748-b0b0-f6df201daaca");
		list.add("11784c5a-aa7c-4b4a-b198-fcaf16762c3e");
		list.add("ba7485d1-b256-43a9-85ba-b6964bb1b471");
		list.add("d2d2e23e-b161-4edd-9728-c26d519cf5a5");
		list.add("564f6b52-03a0-4d5c-b19b-bc380cfabe13");
		list.add("629dd6b8-3ada-48f3-945f-3d6be20a87c2");
		list.add("64ef8d7c-6a26-4650-8b80-dcca980d14ba");
		list.add("8dc34b7f-16d6-4786-8e69-db5c733d2d37");
		list.add("42193678-1f80-4c21-960a-ba0ea92bd80c");
		list.add("430fb5f4-759d-4b46-8378-73f429054dae");
		list.add("d881f543-b773-4f4b-b83b-c46ef51d6ee2");
		list.add("00411890-4701-4705-a268-3ad7c16278f8");
		list.add("897075a5-531b-4fc8-8f4f-e6d4657d46d2");
		list.add("0b278fd4-a6d9-4c78-83a7-063f8687bbf6");
		list.add("ac0df9bb-7a09-4cb6-a46b-86c88abae6d2");
		list.add("a270a04e-963b-413f-93ed-3980a2d3a7db");
		list.add("d32c6ab7-af4c-43d9-aa8c-9187eec63a21");
		list.add("8335c425-c59b-4e13-8eee-9e83fa73b18f");
		list.add("b033be88-c460-4379-9af0-00ee58cba71c");
		list.add("d8cc969e-bdf0-4b68-9848-74861dc2a519");
		list.add("bc12c666-35e3-4445-8b0b-b7421cb0c481");
		list.add("9ac5b7fc-abbb-4e61-b356-5d62660b0b12");
		list.add("43f1fa3d-aac0-453c-a91c-a08a0a50c088");
		list.add("c5f31d59-b412-49f6-98d9-4228de5f1384");
		list.add("71942c00-3fd7-4d76-a5cc-747bd9c3b280");
		list.add("d55949e2-fd35-4b74-b23e-87e7ff5e373d");
		list.add("139b3f78-4ce8-42bc-ac2b-0c1e2241dcc7");
		list.add("cc2f4d8a-0b77-4613-b924-5b629d4f9177");
		list.add("aa5a0187-c380-4063-867c-fdbdb431b648");
		list.add("aa75fddb-62d0-4825-b2be-f5a5b1aaecd3");
		list.add("43954d1f-145c-4337-81aa-04011c954bc4");
		list.add("07eccf5a-063a-41e4-8dfd-d61cbfa952ff");
		list.add("41532c94-d4f1-457f-a639-7ec9859f25de");
		list.add("6da86fce-5c48-4e41-9a60-0024b3458595");
		list.add("17663d49-9f08-49f7-a1d8-06b77eb99b15");
		list.add("fe640a7e-70c4-4c92-bb63-45a138cee925");
		list.add("0ea5b6e3-0a25-4e78-b08c-c9c041b1c9c1");
		list.add("182bfc28-26b4-463f-a804-702671f11650");
		list.add("d709d143-bcec-4285-b4ef-52a4152abc2c");
		list.add("c927b15f-38ac-4883-9d54-8095b6a9e8c5");
		list.add("372f9954-e955-44df-9108-bb5a10edb5b9");
		list.add("529a0f25-b98b-4695-86ac-d64c368bd3a5");
		list.add("f9ad18dd-1549-45db-b78a-69ee94a287a6");
		list.add("64a46f7d-e7b0-4d20-9959-d5b930c0cc06");
		list.add("a0de9168-1b1d-4f47-9c64-918f73152de3");
		list.add("a6398484-e0df-4a2f-98c0-a637e8842634");
		list.add("33f678fa-8c1c-486c-a7e9-00ddf94e9832");
		list.add("e9ee4397-def4-4bff-b9a8-db0bb6f256bc");
		list.add("2e12603c-2038-412d-8a08-84c34dec2466");
		list.add("2802fbba-e9de-4a20-932e-fc6c852355e8");
		list.add("0c6db1d4-a309-4f2d-8fbf-015aefcd1ce7");
		list.add("0a478351-c695-4594-b14f-53197cdc043b");
		list.add("952300bd-0f97-4ab9-a7d4-f772d4364bfe");
		list.add("e300a831-ab33-4b23-b879-b34d8fdb49e7");
		list.add("d7205123-0df9-4024-a234-d48ae3bb9794");
		list.add("7d9c4322-a6df-4e70-8850-befafe044604");
		list.add("595cde7c-d950-472a-a8ba-8557d7237791");
		list.add("c9e7c7ac-38ab-47c2-b9e1-9256ff703b80");
		list.add("7fef9abc-72fa-409d-bcc6-32057bf8f8a3");
		list.add("fe623fa3-8869-4f52-a66c-72a9039bcda1");
		list.add("8a115742-a9cf-46e8-bef2-10b11b30d692");
		list.add("65ebe238-a57a-4fd4-94bf-7ef037cf6308");
		list.add("9cdca420-6ac8-44bc-b7b2-9a8db7203730");
		list.add("4499a867-941c-4e17-9c75-a0528344c64e");
		list.add("080715fa-dbbc-47de-a48c-c5af55169197");
		list.add("9cf6e6aa-bd89-4473-8666-02af7535ead6");
		list.add("8636d636-306e-457e-84ee-c98964dd5dbb");
		list.add("836f845e-9ecf-438e-9c32-7c4b008fbda1");
		list.add("45540472-83c0-41d9-89db-1a889a907e83");
		list.add("d4ac470d-baed-40d4-bc33-cb3beb2ef449");
		list.add("8264070f-4b75-4a7e-ac48-a28eec0f0b96");
		list.add("8b776326-3e0b-4e4c-bd68-94c2e2c9b897");
		list.add("3fb67526-8598-4668-bc93-b3047d701190");
		list.add("3bcb900d-b616-4c48-88f1-2e88dfbc5fc6");
		list.add("ef5732fe-10cd-456d-8048-54a2317a0496");
		list.add("07da8b9a-fef8-4756-b886-5be04da9ed63");
		list.add("2b073743-bfb3-4d46-b07a-7910cc918204");
		list.add("8f8d19f8-6aff-4eb2-8e9a-01a5e829db53");
		list.add("16e9cfb1-7e86-4834-8465-8aba9bd6e187");
		list.add("b1136e6e-0592-48a3-a8e7-7a5c61509a48");
		list.add("45c74625-693c-4b19-9528-ccc4cd0b91e5");
		list.add("52d35da1-4963-4574-914d-f2d60d25761c");
		list.add("99296001-7096-4090-b5de-b5622776588c");
		list.add("8b317db9-5716-4397-a36b-d5bd4b66e712");
		list.add("2f2e1a1e-86a8-4f15-b718-18978775e95b");
		list.add("013ab793-5b5b-46df-90a2-09c47ccd5f03");
		list.add("57a0c56e-2966-48cf-b644-460f6b25e32a");
		list.add("2c6fac16-e7ac-4803-9e35-f27cd98059f1");
		list.add("4e5a3707-557a-41b2-a699-feb18cc1b2fa");
		list.add("832ec7a9-d599-4fd4-8cfc-f75860575a7b");
		list.add("0a1e330e-bca9-4a72-83c5-fb76d2dc022a");
		list.add("f62bacb4-7364-4251-a096-8f8bcb691351");
		list.add("b186f790-9580-47d6-9c67-5a2851c77222");
		list.add("123fc97c-66cb-491a-bb76-8b32c3f5a256");
		list.add("1a86e0ef-6f37-4820-b54b-7b932081c903");
		list.add("3863eeb1-2a1c-44c4-9086-651650c12ba7");
		list.add("9adab767-5c8c-46ab-b9e7-fb10f1b8fe7b");
		list.add("3cd1b511-bd4a-4084-9cdb-2056a4a7a280");
		list.add("d5ade87d-ab2d-4c67-a979-849dc25b9429");
		list.add("235d8b60-0f11-4aed-a278-bc24e73c831a");
		list.add("38b8c0b7-0973-4fee-8f0a-5229b3d7afdf");
		list.add("fbe3677c-99ea-4727-82a1-1f292c710786");
		list.add("0b82a276-32e7-414f-8cb3-86d96d11a23b");
		list.add("a37b2c31-1376-4991-824a-709bceb70d89");
		list.add("98074ccb-6f7c-4c41-b49b-12c525147bf3");
		list.add("18f0a783-d88b-495d-893c-d458da99808a");
		list.add("6f3cc811-515d-4b09-adb5-9c0a2c10f7a8");
		list.add("89fc913e-8559-4bda-acbf-bf171fbf274b");
		list.add("afdf7644-cf53-4711-900c-b9f5791862f0");
		list.add("8f46ef71-5be9-48c3-ac2e-c59f42621c69");
		list.add("d555b762-9bb4-44ee-ad9b-b2b66cdcf8ad");
		list.add("465e1786-bffd-42f1-8ee9-ee4b789dcec0");
		list.add("acea5d50-5ab8-4fbe-bb38-4210b65092cb");
		list.add("c9aba168-389a-4172-a570-b28977243048");
		list.add("637f64d6-7eba-4c00-b09b-564d3bf420ac");
		list.add("ff1fabf1-8b43-4778-8ef9-f37c09fb84cf");
		list.add("3535005e-8912-4f87-999f-a636af6f33bb");
		list.add("c7e0e9a9-efd0-46c1-bbc7-659f92029ead");
		list.add("c30641b1-39c3-4bcb-844d-a1667639a05a");
		list.add("1073239c-0fb0-4d34-a1cc-45001db3656c");
		list.add("872cb1bb-9f18-4e9c-a7df-0c59fed26699");
		list.add("8dffc14e-1a33-4f7a-8e02-8e1eb7760b73");
		list.add("6bfc428a-bdf8-4c9b-b990-e2612efeacac");
		list.add("9731d22b-bb9f-47fa-a7cd-4a1bb065388e");
		list.add("fa18f2a3-4312-4368-a0a8-b190a94249e6");
		list.add("717e12d2-fd7f-402a-b046-fd0411fce2c1");
		list.add("a3e240ed-1026-4626-ad1b-a96bd3d2a9f4");
		list.add("d4878cfc-236b-4e4e-a219-860676192706");
		list.add("9cd9eefb-c8a2-4785-a043-114de2b141ef");
		list.add("a9960a8d-4277-4ad8-a03a-5a5c94472549");
		list.add("12bad510-1351-4a2c-82a4-3ff30a61c776");
		list.add("a9ebb5dc-54ab-4772-8ea8-e7384a8b0992");
		list.add("b18560eb-7e9f-4421-a672-f0f145b89075");
		list.add("43e295d7-fe0c-4372-9cb4-899a7f5e8423");
		list.add("41a18376-13cf-488e-96d1-aec05e7f0622");
		list.add("6d29c65a-7a2f-43c7-bb7c-db4b4cef105f");
		list.add("76cd7a07-c4ff-486e-8541-379ed30a35e1");
		list.add("41a702de-5c01-4ca5-8928-feea926e2cf6");
		list.add("f6fe8a8a-8279-4020-be1e-76ca8ff28551");
		list.add("5d46e1a3-3747-4b17-95aa-f756be338191");
		list.add("2000c710-9e30-4224-b498-d440cefcb121");
		list.add("1ca63b97-dda6-41b1-be9a-6e012cac004e");
		list.add("b09b7811-c3b0-456f-a05b-f9ea20120a64");
		list.add("ecd57f1f-b232-4e91-af5b-b685ca65dc80");
		list.add("3ebf794c-72f0-4e04-b29b-0a49f65a71c0");
		list.add("1999449f-f0ab-4f8a-8b5b-c9084c879128");
		list.add("6e932a43-8bb7-4bcb-bea5-515fc210dffa");
		
		// fileInput = new FileInputStream("C:\\Documents and Settings\\Administrator\\Desktop\\anil.txt");

		
		// BufferedReader br = new BufferedReader(new FileReader("C:\\Documents and Settings\\Administrator\\Desktop\\anil.txt"));
		 BufferedReader br = new BufferedReader(new FileReader("E:\\DB_data_Local_Test_webservice\\catalina.out.chunk018"));
		 StringBuilder sb = new StringBuilder();
		 try {
		       
		        String line = br.readLine();

		        while (line != null) {
		            sb.append(line);
		         
		            line = br.readLine();
		        }
		       // String everything = sb.toString();
		        
		      //  System.out.println(everything);
		    }catch (Exception e) {
			e.printStackTrace();
			} finally {
		        br.close();
		    }
		
		try {
			Thread.sleep(5000);
		}catch (Exception e) {
			
		}
		 br=null;
		    
		   // String patern="(com.itgrids.survey.webServices.WebServiceHandler.saveSurveyResponseData\\(WebServiceHandler.java:113\\) - Saving survey response data from app:)(\\{\"results.*?regionId\":[0-9]\\}\\]\\})";
		    String patern="(com.itgrids.survey.webServices.WebServiceHandler.saveSurveyResponseData\\(WebServiceHandler.java:113\\) - Saving survey response data from app:)(\\{\"results.*?\"uniqueId\":\"(.*?)\",\"districtId\".*?regionId\":[0-9]\\}\\]\\})";

		
		    List<String> urlsList=new ArrayList<String>();
		    Pattern pat =    Pattern.compile(patern, Pattern.CASE_INSENSITIVE);
			Matcher matcher=pat.matcher(sb);
			
			 while (matcher.find()) {
			   
			    String urls=matcher.group(2).toString();
				if(list.contains(matcher.group(3)))
					if(!urlsList.contains(urls))
				  urlsList.add(urls);
				// System.out.println(matcher.group(3));
		
			    }
			 




System.out.println("=============list size==="+urlsList.size());
System.out.println("=============list2 size==="+list.size());


//urlsList.add(urls);
			 
			 
			 
			List<String> exceptionOccuredList = new ArrayList<String>();
			List<String> exceptionOccuredList1 = new ArrayList<String>();
			List<String> exceptionOccuredList3 = new ArrayList<String>();
	       System.out.println("======== size()"+urlsList.size());
			int index = 0;
	       List<String> urlsTempList = urlsList.subList(0,1060);
			
			for (String string : urlsTempList) {
				
				System.out.println("Now At -----> "+index++);
	        	callWebService(string, exceptionOccuredList);
		
	        	 System.out.println("end=======g======");
		
	        }
			for (String string2 : exceptionOccuredList) {
				
			
	        	callWebService(string2, exceptionOccuredList1);
	        	 System.out.println("end=============");
				
			}
			for (String string3 : exceptionOccuredList1) {
				callWebService(string3, exceptionOccuredList3);
			}
			
			try {
				BufferedWriter outwriter = new BufferedWriter(new FileWriter(new File("E:\\WS_result\\ExceResults.txt")));
				StringBuilder sbr = new StringBuilder();
				
				for(String str : exceptionOccuredList3)
					sbr.append(str+"\n");
				outwriter.write(sbr.toString());
				outwriter.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		
	}
	
	
	public static  void  callWebService(String string2,List<String> exceptionOccuredList1)
	{
		
    	try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	try {
	 
	
	      RestTemplate restTemplate = new RestTemplate();

		 // String url="http://192.168.3.51:8080/Survey/WebService/saveSurveyresponseData";
	      String url="http://74.208.7.129:8080/Survey/WebService/saveSurveyresponseData";


       

		     HttpHeaders requestHeaders = new HttpHeaders();
			 requestHeaders.setContentType(MediaType.APPLICATION_JSON);
			 requestHeaders.setAccept(Collections.singletonList(new MediaType("application","json")));

			 
			 ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<String>(string2, requestHeaders), String.class);

			System.out.println(response.getStatusCode());
			 System.out.println(response);
			



    }catch (Exception e) {
		e.printStackTrace();
		exceptionOccuredList1.add(string2);
	}

		
		
	}
	public  static void sendSmsByTakingMessage(String message,PostMethod post1,HttpClient client1)
	{
		
		
			HttpClient client = null;
			PostMethod post = null;
			
			/*Long count = getRemainingSmsLeftForUser(userId) - phoneNumbers.length;
			
			if(count < 0)
				return (long)ResultCodeMapper.FAILURE;

			 */		
			
			client = new HttpClient(new MultiThreadedHttpConnectionManager());// here we are getting the HttpClient For Sending Sms

			client.getHttpConnectionManager().getParams().setConnectionTimeout(
					Integer.parseInt("30000"));

			StringBuilder sb = new StringBuilder();// to append all the mobile n os with comma seperator
			
		
			LOG.debug("Mobile Nos :" + sb.toString());
		    
		    post = new PostMethod("http://api.smscountry.com/SMSCwebservice_bulk.aspx");
			
			post.addParameter("User", IConstants.ADMIN_USERNAME_FOR_SMS);
			post.addParameter("passwd", IConstants.ADMIN_PASSWORD_FOR_SMS);
			//post.addParameter("sid", IConstants.ADMIN_SENDERID_FOR_SMS);
			
		    post.addParameter("mobilenumber", "919505485043,918686810285");
			//post.addParameter("message", message);
			post.addParameter("mtype",  "N" );
			post.addParameter("DR", "Y");
		    post.addParameter("message", message);
		int statusCode=0;
		try {
		 statusCode = client.executeMethod(post);
		} catch (HttpException e) {
			LOG.error("SmsCountrySmsService.sendSMS failed: "+ e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			LOG.error("SmsCountrySmsService.sendSMS failed: "+ e.getMessage());
			e.printStackTrace();
		}
		catch (Exception e) {
			LOG.error("SmsCountrySmsService.sendSMS failed: "+ e.getMessage());
			e.printStackTrace();
		}
		
		LOG.debug(post.getStatusLine().toString()+"***"+statusCode+"*****"+post.getQueryString());
		if (statusCode != HttpStatus.SC_OK)
		{
			LOG.error("SmsCountrySmsService.sendSMS failed: "+ post.getStatusLine());
			LOG.debug("SmsCountrySmsService.sendSMS failed: "+ post.getStatusLine());
		}
		post.releaseConnection();
		
		try {
			LOG.debug(post.getResponseBodyAsString());
		} catch (IOException e) {
							e.printStackTrace();
		}
	}
	
	
}
