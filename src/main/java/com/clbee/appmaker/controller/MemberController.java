package com.clbee.appmaker.controller;

import com.clbee.appmaker.model.*;
import com.clbee.appmaker.model.list.LicenseList;
import com.clbee.appmaker.service.*;
import com.clbee.appmaker.util.ResourceNotFoundException;
import com.clbee.appmaker.security.MyUserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	JavaMailSender mailSender;
	
	@Autowired
	MessageSource messageSource;

	@Autowired
	LocaleResolver localeResolver;
	
	@Autowired
	GroupService groupService;

	@Autowired
	GroupViewMenuService groupViewMenuService;

	@Autowired
	LicenseService licenseService;

	@GetMapping("member/join/ok.html")
	public ModelAndView memberJoinOk(HttpServletRequest request ) {
		ModelAndView modelAndView = new ModelAndView();
		Random random = new Random();
		
		String validId = request.getParameter("validId");
		Member member = memberService.findByCustomInfo("emailChkSession", validId);

		int limitUser =  Integer.parseInt(messageSource.getMessage("limit.user.count", null, localeResolver.resolveLocale(request)));

		if (member == null) {
			modelAndView.addObject("msg", messageSource.getMessage("app.control.006", null, localeResolver.resolveLocale(request)));
			modelAndView.addObject("type", "href");
			modelAndView.addObject("url", "/index.html");
			modelAndView.setViewName("inc/dummy");
			return modelAndView;
		}
		int permitUser = memberService.selectCountWithPermisionUserByCompanySeq(member.getCompanySeq());
		if(permitUser >= limitUser ){
			modelAndView.setViewName("07_member/member_join_ok");
			modelAndView.addObject("emailChk", false);
			return modelAndView;
		}
		int result = memberService.verifyIfExists("emailChkSession", validId);
		
		switch (result){
			case 0 : 
				throw new ResourceNotFoundException();
			case 1 :
				if("1".equals(member.getCompanyGb())){ 
					int companySeq = member.getCompanySeq();
					Company company = companyService.findByCustomInfo("companySeq", companySeq);
					member.setUserStatus("4");
					company.setCompanyStatus("4");
					member.setEmailChkDt(new Date());
					member.setEmailChkGb("Y");
					member.setEmailChkSession(changeSHA256(String.valueOf(System.currentTimeMillis()+random.nextInt())));
					memberService.updateMemberInfo(member, member.getUserSeq());
					companyService.updateCompanyInfo(company, companySeq);
				}else {
					member.setUserStatus("4");
					member.setEmailChkDt(new Date());
					member.setEmailChkGb("Y");
					
					member.setEmailChkSession(changeSHA256(String.valueOf(System.currentTimeMillis()+random.nextInt())));
					memberService.updateMemberInfo(member, member.getUserSeq());
				}
				modelAndView.setViewName("07_member/member_join_ok");
				modelAndView.addObject("emailChk", true);
				return modelAndView;
			case 2 : 
				System.out.println("There are two value duplicated with email_chk_session");
				throw new ResourceNotFoundException();
		}
		modelAndView.setViewName("07_member/member_join_ok");
		modelAndView.addObject("emailChk", true);

		return modelAndView;
	}

	@GetMapping("member/join.html")
	public ModelAndView home(HttpServletRequest request ) {
		ModelAndView modelAndView = new ModelAndView();
		
		List<GroupUser> groupList = groupService.getSelectListGroup(0);

		modelAndView.addObject("groupList", groupList);
		modelAndView.setViewName("07_member/member_join");
		
		return modelAndView;
	}

	@PostMapping("member/join.html")
	public String join(Member member, Company company, HttpServletRequest request ) {
		
		member.setEmailChkSession(changeSHA256(member.getUserId()));
		member.setUserPw(changeSHA256(member.getUserPw()));
		member.setRegIp(request.getRemoteAddr());
		if("1".equals(member.getCompanyGb())){
			int companySeq = companyService.insertCompanyInfoWithProcedure(company);
			member.setCompanySeq(companySeq);
		}
		member.setRegDt(new Date());
		memberService.addMember(member);
		System.out.println("sending Email@@@@@@@@@@@@@@@@@@");
		
		String from=messageSource.getMessage("send.email.ID", null, localeResolver.resolveLocale(request));
		//message : PageCreator ���� �����Դϴ�.
		String subject = messageSource.getMessage("member.control.007", null, localeResolver.resolveLocale(request));
			try {
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
				messageHelper.setTo(member.getEmail());

				messageHelper.setText(messageSource.getMessage("member.control.008", null, localeResolver.resolveLocale(request)) +"\n"+ "http://" + messageSource.getMessage("basic.Info.IP", null, localeResolver.resolveLocale(request))+"/member/join/ok.html?validId="+changeSHA256(member.getUserId()));
				messageHelper.setFrom(from);
				messageHelper.setSubject(subject); 
				mailSender.send(message);
			} catch(Exception e){
				System.out.println(e);
			}
		return "redirect:/index.html";
	}
	
	@PostMapping("member/userIdValidation.html")
	public @ResponseBody int userIdValidation(String userId ){
		return memberService.verifyIfExists("userId", userId);
	}
	
	@PostMapping("member/emailValidation.html")
	public @ResponseBody int emailValidation(String inputEmail ){
		return memberService.verifyIfExists("email", inputEmail);
	}
	
	@PostMapping("userStatusValid.html")
	public @ResponseBody int userStatusValid(String userId, String userPw ){
		
		Member member = memberService.findByUserName(userId);
		if(member == null) {
			return 6;
		}else if(!"".equals(userId) && !"".equals(userPw)) {
			int loginResult = memberService.logInVerify(userId, changeSHA256(userPw));
			if(loginResult < 0) return 6;
			else if(loginResult == 1){
				if("4".equals(member.getUserStatus())) {
					Member updated = new Member();
					updated.setLoginDt(new Date());
					updated.setUserStartDt(member.getUserStartDt());
					updated.setUserEndDt(member.getUserEndDt());
					memberService.updateMemberInfo(updated, member.getUserSeq());
					return 4;
				}	
				else {
					return Integer.parseInt(member.getUserStatus());
				}
			}else if(loginResult == 2) {
				return 7;
			}else {
				return Integer.parseInt(member.getUserStatus());
			}
		}else {
			return 6;
		}
	}

	@GetMapping("mypage/password.html")
	public ModelAndView mypagePasswordGET(String userId ){
		ModelAndView modelAndView = new ModelAndView();
		
		MyUserDetails activeUser = (MyUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		
		for(GrantedAuthority auth : authentication.getAuthorities()) {
			if(auth.getAuthority().equals("ROLE_COMPANY_MEMBER") || auth.getAuthority().equals("ROLE_INDIVIDUAL_MEMBER") || auth.getAuthority().equals("ROLE_USER")) {
				var menuList = groupViewMenuService.selectViewMenu(activeUser.getMember().getGroupName());
				
				modelAndView.addObject("menuLarge", menuList.get("menuLarge"));
			}
		}
		modelAndView.setViewName("06_mypage/mypage_password");
		return modelAndView;
	}

	@PostMapping("mypage/modify.html")
	public ModelAndView mypageModifyPOST(HttpServletRequest request,  Member form, Company formCom, String modify_gb ){
		ModelAndView modelAndView = new ModelAndView();
		
		MyUserDetails activeUser = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if("modify_password".equals(modify_gb)){	
			if(activeUser.getPassword().equals(changeSHA256(form.getUserPw()))){
				Member dbPassword  = memberService.findByCustomInfo("userId", activeUser.getUsername() );
				Company company = companyService.findByCustomInfo("companySeq", activeUser.getMember().getCompanySeq());
				modelAndView.addObject("company", company);
				modelAndView.addObject("member", dbPassword);
				modelAndView.setViewName("06_mypage/mypage_modify");
			}
			else {
				modelAndView.addObject("validPassword", false);
				modelAndView.setViewName("/inc/dummy");
			}
		}
		else {
			form.setUserPw(changeSHA256(form.getUserPw()));
			
			if ("5".equals(form.getUserStatus())) {
				form.setEmailChkSession(changeSHA256(form.getUserId()));
				String from = messageSource.getMessage("send.email.ID", null, localeResolver.resolveLocale(request));
				String subject = messageSource.getMessage("member.control.007", null, localeResolver.resolveLocale(request));
				try {
					MimeMessage message = mailSender.createMimeMessage();
					MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
					messageHelper.setTo(form.getEmail());

					messageHelper.setText(messageSource.getMessage("member.control.008", null, localeResolver.resolveLocale(request)) + "\n"+ "http://"+messageSource.getMessage("basic.Info.IP", null, localeResolver.resolveLocale(request)) + "/member/join/ok.html?validId="+changeSHA256(form.getUserId()));
					messageHelper.setFrom(from);
					messageHelper.setSubject(subject);
					mailSender.send(message);
				} catch(Exception e) {
					System.out.println(e);
				}
			}
			
			form.setChgDt(new Date());
			form.setChgIp(request.getRemoteAddr());
			memberService.updateMemberInfo(form, form.getUserSeq());
			
			Member dbModify = memberService.findByCustomInfo("userId", form.getUserId() );
			companyService.updateCompanyInfo(formCom, dbModify.getCompanySeq());
			Company dbComModify = companyService.findByCustomInfo("companySeq", dbModify.getCompanySeq());
			modelAndView.addObject("modifySuccess", true);
			modelAndView.addObject("company", dbComModify);
			modelAndView.addObject("member", dbModify);
			modelAndView.setViewName("06_mypage/mypage_modify");
		}
		
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		
		for (GrantedAuthority auth : authentication.getAuthorities()) {
			if (auth.getAuthority().equals("ROLE_COMPANY_MEMBER") || auth.getAuthority().equals("ROLE_INDIVIDUAL_MEMBER") || auth.getAuthority().equals("ROLE_USER")) {
				var menuList = groupViewMenuService.selectViewMenu(activeUser.getMember().getGroupName());
				modelAndView.addObject("menuLarge", menuList.get("menuLarge"));
			}
		}
		return modelAndView;
	}
	
	@PostMapping("mypage/modifyCustom.html")
	public @ResponseBody String mypaOST(String userPw ){
		MyUserDetails activeUser = (MyUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		return activeUser.getMember().getEmail();
	}

//	@PostMapping("/mypage/modify.html")
//	public String mypagePOST(String userPw ){
//		throw new ResourceNotFoundException();
//	}

	@GetMapping("/mypage/withDrawal.html")
	public String mypageWithDrawal(String userPw ){
		return "06_mypage/mypage_withdrawal";
	}

	@PostMapping("/mypage/withDrawal.html")
	public @ResponseBody int mypageWithDrawalPOST(String userSeq, String companySeq ){
		
		int intUserSeq = Integer.parseInt(userSeq);
		int intCompanySeq = Integer.parseInt(companySeq);
		int companyResult = 1;
		
		if(intCompanySeq != 0) {
			Company updateCom = new Company();
			updateCom.setCompanyStatus("1"); // Ż��
			updateCom.setWithdrawalDt(new Date());
			companyResult = companyService.updateCompanyInfo(updateCom, intCompanySeq);
		}
		
		Member updateMem = new Member();
		updateMem.setUserStatus("1");	// Ż���
		updateMem.setWithdrawalDt(new Date());
		int memberResult = memberService.updateMemberInfo(updateMem, intUserSeq);

		/* 탈퇴처리 -> 삭제되는것 아님 */
		if(companyResult == 1 && memberResult == 1) {
			return 1;
		}else {
			return 0;
		}
	}
	
	public String changeSHA256(String str){
		String SHA = ""; 
		try{
			MessageDigest sh = MessageDigest.getInstance("SHA-256"); 
			sh.update(str.getBytes()); 
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer(); 
			for(int i = 0 ; i < byteData.length ; i++){
				sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
			}
			SHA = sb.toString();
			
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace(); 
			SHA = null; 
		}
		return SHA;
	}	
	
	@PostMapping("/findid.html")
	public @ResponseBody String findId(Member member, HttpServletRequest request ) {
		
		String firstName = request.getParameter("fm_first_name");
		String lastName  = request.getParameter("fm_last_name");
		String email     = request.getParameter("fm_email1");
		
		member.setFirstName(firstName);
		member.setLastName(lastName);
		member.setEmail(email);
		
		Member memberRow = null;
		Integer memCnt = 0;
		
		memCnt = memberService.selectMemberCount(member);	
		memberRow = memberService.selectMemberSuccessYn(member);		
		
		if(memCnt == 1){			
			String from=messageSource.getMessage("send.email.ID", null, localeResolver.resolveLocale(request));
			String subject=messageSource.getMessage("member.control.001", null, localeResolver.resolveLocale(request));

			try {
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
				messageHelper.setTo(memberRow.getEmail());
				//message : ���� ��û�Ͻ� ���̵��
				//message : �Դϴ�. �����մϴ�.
				messageHelper.setText(memberRow.getLastName()+memberRow.getFirstName()+messageSource.getMessage("member.control.002", null, localeResolver.resolveLocale(request))+memberRow.getUserId()+messageSource.getMessage("member.control.003", null, localeResolver.resolveLocale(request)) );
				messageHelper.setFrom(from);
				messageHelper.setSubject(subject); 
				mailSender.send(message);
			} catch(Exception e){
				System.out.println(e);
			}
			
			return "successTrue";
		} else {
			// ���� ������
			return "successFalse";
		}		
	}

	@PostMapping("/findpwd.html")
	public @ResponseBody String findPwd(Member member, HttpServletRequest request ) {
		String ranStr   = null;
		String userId  = request.getParameter("fm_user_id");
		String email    = request.getParameter("fm_email2");
		
		ranStr =  new RandomStringBuilder()
					.putLimitedChar(RandomStringBuilder.ALPHABET)
					.putLimitedChar(RandomStringBuilder.NUMBER)
					.putExcludedChar(RandomStringBuilder.SPECIAL)
					.setLength(12).build();
		
		member.setUserId(userId);
		member.setEmail(email);
		member.setUserPw(ranStr);
		
		Member memberRow = null;
		Integer memCnt = 0;
		
		memCnt = memberService.selectMemberCount_(member);	
		memberRow = memberService.selectMemberSuccessYn_(member);
		
		if(memCnt == 1){
			String from=messageSource.getMessage("send.email.ID", null, localeResolver.resolveLocale(request));
			//message : ��û�Ͻ� ��й�ȣ �Դϴ�.
			String subject=messageSource.getMessage("member.control.004", null, localeResolver.resolveLocale(request));
			
			try {
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
				messageHelper.setTo(memberRow.getEmail());
				messageHelper.setText(memberRow.getLastName()+memberRow.getFirstName()+messageSource.getMessage("member.control.005", null, localeResolver.resolveLocale(request))+ranStr+messageSource.getMessage("member.control.006", null, localeResolver.resolveLocale(request)) );
				messageHelper.setFrom(from);
				messageHelper.setSubject(subject); 
				mailSender.send(message);
				memberService.updateMemberPw(member);
			} catch(Exception e){
				System.out.println(e);
			}
			return "True!";
		} else {
			return "False!";
		}		
	}

	@RequestMapping(value="/my/license.html", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView mypageLicenseManagement(LicenseList listLicense, LicenseSubList licenseUseDevice, HttpServletRequest request, HttpSession session ){
		ModelAndView modelAndView = new ModelAndView();
		
		MyUserDetails activeUser = (MyUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		
		for(GrantedAuthority auth : authentication.getAuthorities()) {
			if(auth.getAuthority().equals("ROLE_COMPANY_MEMBER") || auth.getAuthority().equals("ROLE_INDIVIDUAL_MEMBER") || auth.getAuthority().equals("ROLE_USER")) {
				var menuList = groupViewMenuService.selectViewMenu(activeUser.getMember().getGroupName());
				
				modelAndView.addObject("menuLarge", menuList.get("menuLarge"));
			}
		}
		
		int useLicense = licenseService.checkUseLicense(activeUser.getMember().getUserSeq());
		
		if(useLicense == 0) {
			modelAndView.setViewName("06_mypage/my_license_regist");
		}else if(useLicense == 1) {
			listLicense = licenseService.selectMyList(listLicense, activeUser.getMember().getUserSeq());
			modelAndView.addObject("licenseList", listLicense);
			
			if(request.getParameter("currentPage") == null || licenseUseDevice.getCurrentPage() == 0) {
				licenseUseDevice.setCurrentPage(1);
			}
			licenseUseDevice.setLicenseSeq(listLicense.getList().get(0).getLicenseSeq());
			licenseUseDevice = licenseService.selectLicenseUseDevice(licenseUseDevice);
			
			modelAndView.addObject("searchValue", licenseUseDevice.getSearchValue());
			modelAndView.addObject("currentPage", licenseUseDevice.getCurrentPage());
			modelAndView.addObject("licenseUseDevice", licenseUseDevice);
			
			modelAndView.setViewName("06_mypage/my_license_list");
		}
		
		return modelAndView;
	}
	
	@PostMapping("/my/deleteLicenseUseDevice.html")
	public @ResponseBody int mypageDeleteLicenseUseDevicePost(HttpServletRequest request, HttpSession session ){
		return licenseService.deleteLicenseUseDevice(Integer.parseInt(request.getParameter("licensesubSeq")));
	}

	@PostMapping("/my/licenseRegistCheck.html")
	public @ResponseBody int mypageLicenseRegistCheck(HttpServletRequest request, HttpSession session ){
		return licenseService.licenseRegistCheck(request.getParameter("licenseNum"));
	}

	@PostMapping("/my/licenseRegist.html")
	public ModelAndView mypageLicenseRegist(HttpServletRequest request, HttpSession session ){
		ModelAndView modelAndView = new ModelAndView();
		
		try {
			MyUserDetails activeUser = (MyUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			SecurityContext context = SecurityContextHolder.getContext();
			Authentication authentication = context.getAuthentication();
			
			if(request.getParameter("licenseSeq") != null && !request.getParameter("licenseSeq").equals("")) {
				licenseService.licenseExpire(Integer.parseInt(request.getParameter("licenseSeq")));
			}

			licenseService.licenseRegist(activeUser.getMember().getUserSeq(), request.getParameter("licenseNum"));
			
			for(GrantedAuthority auth : authentication.getAuthorities()) {
				if(auth.getAuthority().equals("ROLE_COMPANY_MEMBER") || auth.getAuthority().equals("ROLE_INDIVIDUAL_MEMBER") || auth.getAuthority().equals("ROLE_USER")) {
					var menuList = groupViewMenuService.selectViewMenu(activeUser.getMember().getGroupName());
					
					modelAndView.addObject("menuLarge", menuList.get("menuLarge"));
				}
			}
			
			modelAndView.setViewName("redirect:/my/license.html");	
		}catch(Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}

	@GetMapping("/my/licenseRenew.html")
	public ModelAndView mypageLicenseRenewGet(HttpServletRequest request, HttpSession session ){
		ModelAndView modelAndView = new ModelAndView();
		
		try {
			MyUserDetails activeUser = (MyUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			SecurityContext context = SecurityContextHolder.getContext();
			Authentication authentication = context.getAuthentication();
			
			for(GrantedAuthority auth : authentication.getAuthorities()) {
				if(auth.getAuthority().equals("ROLE_COMPANY_MEMBER") || auth.getAuthority().equals("ROLE_INDIVIDUAL_MEMBER") || auth.getAuthority().equals("ROLE_USER")) {
					var menuList = groupViewMenuService.selectViewMenu(activeUser.getMember().getGroupName());
					
					modelAndView.addObject("menuLarge", menuList.get("menuLarge"));
				}
			}
			
			List<License> license = null;
			license = licenseService.selectLicenseForRenew(activeUser.getMember().getUserSeq());

			modelAndView.addObject("licenseSeq", license.get(0).getLicenseSeq());
			modelAndView.setViewName("06_mypage/my_license_regist");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	public class RandomStringBuilder {
	     
	    public static final String NUMBER = "0123456789";
	    public static final String ALPHABET_LOWER_CASE = "abcdefghijkmnlopqrstuvwxyz";
	    public static final String ALPHABET_UPPER_CASE = "ABCDEFGHIJKMNLOPQRSTUVWXYZ";
	    public static final String ALPHABET = ALPHABET_LOWER_CASE + ALPHABET_UPPER_CASE;
	    public static final String SPECIAL = "~!@#$%^&*()_+{}|\\\"`;:'<>?,./=-[]";
	     
	     
	    private HashSet mExcludedCharSet = new HashSet(); 
	    private ArrayList mLimitCharList = new ArrayList();
	             
	    int mLength = 32;
	     
	    public String build() {
	        return generateRandomString(mLength);
	    }
	     
	    public RandomStringBuilder setLength(int length) {
	        mLength = length;
	        return this;
	    }
	     
	    public int getLength() {
	        return mLength;
	    }
	     
	    public RandomStringBuilder putExcludedChar(char excluded) {
	        mExcludedCharSet.add(excluded);
	        return this;
	    }
	     
	    public RandomStringBuilder putExcludedChar(char[] excludedList) {
	        for(char excluded : excludedList) 
	                putExcludedChar(excluded);
	        return this;
	    }
	     
	    public RandomStringBuilder putExcludedChar(String excluded) { 
	                putExcludedChar(excluded.toCharArray());
	        return this;
	    }
	     
	    public RandomStringBuilder putLimitedChar(char limited) {
	        mLimitCharList.add(limited);
	        return this;
	    }
	     
	    public RandomStringBuilder putLimitedChar(char[] limitedList) {
	        for(char limited : limitedList) 
	                putLimitedChar(limited);
	        return this;
	    }
	     
	    public RandomStringBuilder putLimitedChar(String limited) {
	        putLimitedChar(limited.toCharArray());
	        return this;
	    }
	     
	    public boolean removeExcluded(char excluded) {
	        return mExcludedCharSet.remove(excluded);
	    }
	     
	    public boolean removeLimitedChar(char limited) {
	        return mLimitCharList.remove((Character)limited);
	    }
	     
	    public void clearExcluded() {
	        mExcludedCharSet.clear();
	    }
	    public void clearLimited() {
	        mLimitCharList.clear();
	    }
	     

	    public String generateRandomString(int length) {
	        boolean runExcludeChar = !isExcludedCharInLimitedChar();            
	        StringBuffer strBuffer = new StringBuffer(length);
	        Random rand = new Random(System.nanoTime());
	        for(int i = 0; i < length; ++i ) {
	            char randomChar = makeChar(rand);
	            if(runExcludeChar)
	                randomChar = excludeChar(rand, randomChar);
	            strBuffer.append(randomChar);
	        }
	        return strBuffer.toString();
	    }
	     
	    private boolean isExcludedCharInLimitedChar() {
	        return mExcludedCharSet.containsAll(mLimitCharList);
	    }
	     
	    private char excludeChar(Random rand, char arg) {
	        while(mExcludedCharSet.contains(arg)) {
	            arg = makeChar(rand);
	        }
	        return arg;
	    }
	    private char makeChar(Random rand) {
	        if(mLimitCharList.isEmpty())
	            return (char)(rand.nextInt(94) + 33);
	        else
	            return (Character) mLimitCharList.get(rand.nextInt(mLimitCharList.size()));
	    }
	}
}