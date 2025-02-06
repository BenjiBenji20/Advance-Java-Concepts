export function registrationView() {
  return `
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

// rennder html page
export const containerElement = document.querySelector('.container');

containerElement.innerHTML = registrationView();