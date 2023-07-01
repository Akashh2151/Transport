package transportapk.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import transportapk.dao.UserRepository;
import transportapk.entity.user;
import transportapk.servises.Emailservises;

@Controller
public class ChangePasswotrd {
	
	@Autowired
	private Emailservises emailservice;
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	
	@PostMapping("/changepass")
	public ResponseEntity<String> changePassword(@RequestParam("password") String password,String username,String oldpassword,HttpSession session) {
		
//		String Otp = (String) session.getAttribute("otpSent");
	    
		//newpassword
		String Password= password;
		System.out.println(" new password: " +Password);
		//old password
		String OldPassword=oldpassword;
		System.out.println("old password: " +OldPassword);
		//username
	    String Newusername=username;
		System.out.println("username: " +Newusername);
	    
    	user user = this.userRepository.getUsername(Newusername);
    	
    	user oldpass = this.userRepository.getpassword(OldPassword);
		
    	if(oldpassword.equals(OldPassword) ||username.equals(Newusername)) {
    		
    		user.setPassword(Password);
    		
    		this.userRepository.save(user);
    		
    	    return new ResponseEntity<String>("changed password successfully",HttpStatus.OK);
    		
    	}else {
    		
    		return new ResponseEntity<String>("cheked your username and lod password!",HttpStatus.OK);
    	}
    	

	
}


	

}
