import { registrationView, containerElement } from '../view/view.js';
import { ServiceAPI } from '../service/service.js';

document.getElementById("registration-form-js").addEventListener('submit', async (e) => {
  e.preventDefault(); // prevent from submitting if not input all fields

  const userData = {
    completeName: document.getElementById("complete-name").value,
    username: document.getElementById("username").value,
    password: document.getElementById("password").value
  };

  try {
    const result = await ServiceAPI.registerUser(userData);

    if(result) {
      containerElement.innerHTML = registrationView();
      alert(result.message);
    } 
    else {
      // if there's no data
      containerElement.innerHTML = registrationView();
      alert(result.message);
    }
  } catch (error) {
    console.error('Error found: ', error);
  }
});