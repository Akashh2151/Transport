package transportapk.Controller;

 

 
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import transportapk.Helper.Message;
import transportapk.dao.UserRepository;
import transportapk.entity.user;
import transportapk.servises.Emailservises;

@Controller
public class forgetpassword {
	Random random = new Random(6754);
	
	@Autowired
	private Emailservises emailservice;
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	
	
	@PostMapping("/forget")
	public ResponseEntity<Message> sendOTP(@RequestParam("email")String email,HttpServletRequest request)
	{
		System.out.println("EMAIL"+email);
		
//		generate otp 4 digit
		
		
		int otp=random.nextInt(12456);
	 
		System.out.println("OTP"+otp);
		
		String subject="OTP From Akash Desai";
		
		String message=""
		+"<div style='border:1px solid #e2e2e2; padding:20px'>"
		+"<h1>"
		+"OTP "
		+"<b>"+otp
		+"</n>"
		+"</h1>"
		+"</div>";
		String to=email;
		
		boolean flag = this.emailservice.sendEmail(subject, message, to);
		
		
	 
		request.getSession().setAttribute("otpSent",String.valueOf(otp) );
		System.out.println(request.getSession().getAttribute("otpSent"));
//		 session.setAttribute("email",email);
		
		user user = this.userRepository.getemail(email);
		
		if(user != null){
            
			return new ResponseEntity<Message>(new Message("Successfully otp sent"),HttpStatus.OK);
			
		}else {
			
			return new ResponseEntity<Message>(new Message("does not exist in this email id"),HttpStatus.OK);
		}
    
		
//		return new ResponseEntity<Message>(new Message("Successfully otp sent"), HttpStatus.OK);
//		
				
	}
		
	
	
	
	
	
	
	
	
	
	
	
//		verify otp
		@PostMapping("/verifyotp")
		public ResponseEntity<String> verifyOtp(@RequestParam("otp") String otp ,String username,HttpServletRequest request,HttpSession session) {

			String Otp = (String) session.getAttribute("otpSent");
			
			String Username= username;
			
//			System.out.println("....................................");
//			
//			String  email=(String)session.getAttribute("email");
			
//			System.out.println("EMAIL"+username);
			
			
			request.getSession().setAttribute("usernameSent",String.valueOf(username) );
			System.out.println(request.getSession().getAttribute("usernameSent"));

		    if(otp.equals(Otp)){
		       
		    	user user = this.userRepository.getUsername(Username);
		    
				if(user==null){
                    
					return new ResponseEntity<String>("user does not exist in this Id",HttpStatus.OK);
					
				}else {
					
					return new ResponseEntity<String>("OTP verify successfull",HttpStatus.OK);
				}
		    
		 
//		            return new ResponseEntity<String>("you have enterd Right otp",HttpStatus.OK);
//				System.out.println("Otp verify Successfully");
		       }else{
//		    	 session.setAttribute("message","you have enterd wrong otp !!");
		    
		    	    return new ResponseEntity<String>("you have enterd wrong otp",HttpStatus.OK);
		    }
		    
//		    return new ResponseEntity<String>("changed password successfully",HttpStatus.OK);
		}
		
	
		
		
		
		
		
		//change password
		
		
		@PostMapping("/change")
		public ResponseEntity<String> changePassword(@RequestParam("password") String password,String username,HttpSession session) {
			
//			String Otp = (String) session.getAttribute("otpSent");
		
			String Password= password;
			
			System.out.println("password"+Password);
			 
		    String Newusername=username;
		    
	    	user user = this.userRepository.getUsername(Newusername);
			
			user.setPassword(Password);
			
			this.userRepository.save(user);
			
		return new ResponseEntity<String>("changed password successfully",HttpStatus.OK);
		
	}
	
	
		
		
		
		


}
