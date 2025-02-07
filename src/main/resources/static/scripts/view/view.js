import { registrationFormController } from "../controller/controller.js";

export const containerElement = document.querySelector('.container');

// render default view
defaultView();

export function defaultView() {
  registrationFormController();
}

export function registrationView() {
  containerElement.innerHTML =  `
    <form id="registration-form-js">
      <h3>Register Now</h3>
      <input type="text" id="complete-name" placeholder="Complete Name" required>
      <input type="text" id="username" placeholder="Username" required>
      <div class="password-input">
          <input type="password" id="password" placeholder="password" required>
          <i class="bi bi-eye"></i>
      </div>

      <button type="submit">Submit!</button>
    </form>
  `;
}

export function authView() {
  containerElement.innerHTML =  `
        <form id="login-form-js">
          <h3>Login Now</h3>
          <input type="text" id="username" placeholder="username" required>
          <input type="password" id="password" placeholder="Password" required>
          <input type="submit">
      </form>
    `;
}

