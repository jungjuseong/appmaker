package com.clbee.appmaker.controller;

import com.clbee.appmaker.service.*;
import com.clbee.appmaker.util.AuthenticationException;
import com.clbee.appmaker.util.Entity;
import com.clbee.appmaker.security.MyUserDetails;
import com.clbee.appmaker.model.Member;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.beans.PropertyVetoException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class HomeController {	
	@Autowired
	InAppCategoryService inAppCategoryService;
	
	@Autowired
	AppService appService; 
	
	@Autowired
	InAppService inAppService; 

	@Autowired
	MessageSource messageSource;
	
	@Autowired
	DeviceService deviceService;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	LocaleResolver localeResolver;
	
	@Autowired
	SessionRegistry sessionRegistry;
	
	@GetMapping(value = "index.html")
	public String home( HttpServletRequest request, HttpSession session  ) {
		System.out.println(localeResolver.resolveLocale(request));
		
		String addr= ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes())
	       .getRequest().getRemoteAddr();

		return "index";
	}

	@PostMapping("couponGenerate.html")
	public @ResponseBody String contentsCouponGeratePOST( String coupon_num, HttpServletRequest request, HttpSession session ) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");//edit for the    format you need
		final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    final int N = alphabet.length();

	    Random r = new Random();
	    int totalCount = 1;
	    String string ="";
		
		while(totalCount == 1){
			string = format.format(new Date());
			for (int i = 0; i < 4; i++) {
				string += alphabet.charAt(r.nextInt(N));
			}
			Entity param = new Entity();
			param.setValue("coupon_num", string);
			List listCnt = appService.getCountOfIdenticalCouponNumForAll(param);
			HashMap temp = (HashMap)listCnt.get(0);
			totalCount = Integer.parseInt(temp.get("IdenticalCouponCOUNT").toString());
	    }
		return string;
	}

	@RequestMapping( value = "getCurrentTime.html", method=RequestMethod.POST)
	public @ResponseBody String getCurrentTimePOST( String coupon_num, HttpServletRequest request, HttpSession session ) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//edit for the    format you need
		String date = format.format(new Date());
		return date;
	}

	@RequestMapping( value = "printAnswer.html", method=RequestMethod.POST)
	public void writeJsonAnswerPOST( @RequestBody  String jsonObject, HttpSession session ) throws JsonParseException, IOException  {
		try {
			FileWriter file = new FileWriter("c:\\data\\test.json");
			file.write(jsonObject);
			file.flush();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping( value = "viewJsonAnswer.html", method={RequestMethod.POST, RequestMethod.GET}, produces = "application/json; charset=utf8")
	public ModelAndView viewJsonAnswerPOST( HttpSession session ) throws JsonParseException, IOException  {
		ModelAndView mv = new ModelAndView();
		ObjectMapper mapper = new ObjectMapper();
		BufferedReader fileReader = new BufferedReader(
				new FileReader("c:\\data\\test.json"));
		JsonNode rootNode = mapper.readTree(fileReader);
		System.out.println("toString = " + rootNode.toString());
		/*Object obj = JsonParser jp = parse(new FileReader("c:\\test.json"));*/
		mv.addObject("json",	rootNode.toString());
		mv.setViewName("sampleAnswer");
		return mv;
	}
	
	/*int limitUser =  Integer.parseInt(messageSource.getMessage("limit.user.count", null, Locale.getDefault()));
	int permitUser = memberService.selectCountWithPermisionUserByCompanySeq(memberVO.getCompanySeq());
	
	
	try{
		
		if("".equals(memberVO.getEmail())) {
			if( permitUser >= limitUser ){
				modelAndView.addObject("msg", messageSource.getMessage("extend.local.088", null, localeResolver.resolveLocale(request)));
				modelAndView.addObject("type", "href");
				modelAndView.addObject("url", "/man/user/list.html?page=1");
				modelAndView.setViewName("inc/dummy");
			}*/
	
	@RequestMapping( value = "/logoutFlagOff.html", method=RequestMethod.POST )
	public @ResponseBody int logoutFlagOffPOST( HttpSession session, String userSeq ) throws JsonParseException, IOException  {
		int result = 0;
		System.out.println("Helo IN logOff Flag");
		MemberVO memberVO = new MemberVO();
		memberVO.setLoginStatus("2");
		result = memberService.updateMemberInfo(memberVO, Integer.parseInt(userSeq));
		
		return result;
	}
	
	
	@RequestMapping( value = "/userLimitIsOver.html", method=RequestMethod.POST )
	public @ResponseBody int userLimitIsOverPOST( HttpSession session, String companySeq ) throws JsonParseException, IOException  {
		int limitUser =  Integer.parseInt(messageSource.getMessage("limit.user.count", null, Locale.getDefault()));
		int permitUser = memberService.selectCountWithPermisionUserByCompanySeq(Integer.parseInt(companySeq));
		
		if(permitUser >= limitUser) return 0;
		else return 1;
	}
	
	@RequestMapping( value = "/deviceIsOver200.html", method=RequestMethod.POST )
	public @ResponseBody int deviceIsOver200POST( HttpSession session ) throws JsonParseException, IOException  {
		
		myUserDetails activeUser = null;
		
		if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String) {
			throw new AuthenticationException();
		}else {
			activeUser = (myUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		
		int count = deviceService.countDeviceIsAvailable(activeUser.getMemberVO().getCompanySeq());
		return count;
	}
	
	@RequestMapping( value = "/categoryIsDuplicated.html", method=RequestMethod.POST )
	public @ResponseBody int categoryIsDuplicatedPOST( String storeBundleId, String categoryName, HttpSession session ) throws JsonParseException, IOException  {
		
		myUserDetails activeUser = null;
		
		if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String) {
			throw new AuthenticationException();
		}else {
			activeUser = (myUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		
		int count = inAppCategoryService.categoryIsDuplicated(storeBundleId, categoryName);
		return count;
	}
}