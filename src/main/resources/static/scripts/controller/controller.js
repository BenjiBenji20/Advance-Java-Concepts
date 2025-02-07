import { registrationView, authView } from '../view/view.js';
import { ServiceAPI } from '../service/service.js';

/**
 * registration form submit button event.
 * Calls the service to get json
 */
export function registrationFormController() {
  registrationView(); // render registration view

  document.getElementById("registration-form-js").addEventListener('submit', async (e) => {
    e.preventDefault(); // prevent from submitting if not input all fields
  
    const userData = {
      completeName: document.getElementById("complete-name").value,
      username: document.getElementById("username").value,
      password: document.getElementById("password").value
    };
  
    try {
      const result = await ServiceAPI.registerUserService(userData);
  
      if(result.message) {
        // successfull
        loginFormController();
        alert(result.message);
      } 
      else {
        // if there's no data
        registrationView();
        alert(result.error);
      }
    } catch (error) {
      console.error('Error found: ', error);
    }
  });
}

/**
 * Login form submit button event
 */
export function loginFormController() {
  authView(); // render login view

  document.getElementById("login-form-js").addEventListener('submit', async (e) => {
    e.preventDefault();
  
    // collects all user inputs
    const userData = {
      username: document.getElementById("username").value,
      password: document.getElementById("password").value
    };
    
    try {
      const result = await ServiceAPI.authUserService(userData);

      if(result.error) {
        // if there's no data
        alert(result.error);
      } 
      else {
        console.log('Login successful!');
      }
    } 
    catch (error) {
      console.error("Error found: ", error);
    }
  });
}