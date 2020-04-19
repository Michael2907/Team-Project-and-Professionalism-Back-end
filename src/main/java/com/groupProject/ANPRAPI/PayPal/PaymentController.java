package com.groupProject.ANPRAPI.PayPal;

import com.groupProject.ANPRAPI.Config.JwtResponse;
import com.groupProject.ANPRAPI.Config.JwtTokenUtil;
import com.groupProject.ANPRAPI.Domain.UserID;
import com.groupProject.ANPRAPI.Service.UserService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller to handle all paypal functionality
 */
@Controller
@RequestMapping("/")
public class PaymentController {

	//Define routes for the success and cancel pages
	public static final String PAYPAL_SUCCESS_URL = "pay/success";
	public static final String PAYPAL_CANCEL_URL = "pay/cancel";
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PaypalService paypalService;

	@Autowired
	private UserService userService;
	
	/**
	 * Get request that will redirect the user to the index page
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String index(){
		return "index";
	}

	/**
	 * POST request that will redirect the user to the paypal login page
	 * @param request
	 * @return redirect to success of fail page
	 */
	@RequestMapping(method = RequestMethod.POST, value = "pay")
	public String pay(HttpServletRequest request){
		//Define URL of success and fail URLS to pass to paypal
		String cancelUrl = URLUtils.getBaseURl(request) + "/" + PAYPAL_CANCEL_URL;
		String successUrl = URLUtils.getBaseURl(request) + "/" + PAYPAL_SUCCESS_URL;

		try {
			//Create payment object to pass to paypal
			Payment payment = paypalService.createPayment(
					10.00,
					"GBP",
					PaypalPaymentMethod.paypal, 
					PaypalPaymentIntent.sale,
					"Purchase 10 credits",
					cancelUrl, 
					successUrl);
			//Handle the response back from paypal
			for(Links links : payment.getLinks()){
				if(links.getRel().equals("approval_url")){
					return "redirect:" + links.getHref();
				}
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		return "redirect:/";
	}

	/**
	 * GET request to direct the user to the cancel page
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = PAYPAL_CANCEL_URL)
	public String cancelPay(){
		return "cancel";
	}

	/**
	 * GET request to handle a successful paypal payment
	 * @param paymentId
	 * @param payerId
	 * @return redirect URL
	 */
	@RequestMapping(method = RequestMethod.GET, value = PAYPAL_SUCCESS_URL)
	public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId){
		try {
			Payment payment = paypalService.executePayment(paymentId, payerId);
			if(payment.getState().equals("approved")){
				//Successfully made payment
				userService.addCredits(10, payment.getPayer().getPayerInfo().getEmail());
				return "success";
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		return "redirect:/";
	}
}
