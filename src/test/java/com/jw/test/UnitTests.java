package com.jw.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jw.controller.MenuController;
import com.jw.dao.UsersDAOImplement;


public class UnitTests {

	public static Logger log = LoggerFactory.getLogger(UnitTests.class);
	
	@Test
	public void testPassword() {
		log.info("In testPassword");
		assertEquals(true, MenuController.isValidPassword("IkjlMld45+!"));
		assertEquals(true, MenuController.isValidPassword("IkjlMld45"));
		assertEquals(false, MenuController.isValidPassword("123"));
		assertEquals(false, MenuController.isValidPassword("daskljfasdflkj"));
		assertEquals(false, MenuController.isValidPassword("KDFkjdfkj"));
		assertEquals(false, MenuController.isValidPassword("KDFkjdfkj@#$"));
	}
	
	@Test
	public void testUsername() {
		log.info("In testUsername");
		assertEquals(false, MenuController.isValidUserName("3lkjsdf"));
		assertEquals(false, MenuController.isValidUserName("lkjsdf(**&"));
		assertEquals(false, MenuController.isValidUserName("lkjsdf#"));
		assertEquals(false, MenuController.isValidUserName("lkjsdf*"));
		assertEquals(true, MenuController.isValidUserName("lkjsdf"));
		assertEquals(true, MenuController.isValidUserName("lkjs8767865"));
		assertEquals(true, MenuController.isValidUserName("lkjs8767865ojklkj"));
		assertEquals(true, MenuController.isValidUserName("weroiu@gmail.com"));
		assertEquals(true, MenuController.isValidUserName("weroiu234@qq.com"));
		assertEquals(true, MenuController.isValidUserName("weroiu34324@gmail.com"));
		assertEquals(true, MenuController.isValidUserName("wer234oiu34324@gmail.com"));
		assertEquals(true, MenuController.isValidUserName("wer234.oiu34324@gmail.com"));
	}
	
	@Test
	public void testEmail() {
		log.info("In testEmail");
		assertEquals(true, MenuController.isValidEmail("weroiu@gmail.com"));
		assertEquals(true, MenuController.isValidEmail("weroiu234@qq.com"));
		assertEquals(true, MenuController.isValidEmail("weroiu34324@gmail.com"));
		assertEquals(true, MenuController.isValidEmail("wer234oiu34324@gmail.com"));
		assertEquals(true, MenuController.isValidEmail("wer234.oiu34324@gmail.com"));
	}
	
	@Test
	public void testUserName() {
		log.info("In testUserName");
		
		UsersDAOImplement userImpl = new UsersDAOImplement();
		
		boolean result = userImpl.findWithUserName("lsdjf234");
		assertEquals(false, result);
		
	}

}
