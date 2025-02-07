import { registrationFormController, loginFormController } from "../controller/controller.js";

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
      <a id="login-link-js">Already have an account? Login</a>
    </form>
  `;

  document.getElementById("login-link-js").addEventListener('click', () => {
    loginFormController();
  });
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

export function renderHeader(userProfile) {
  return `
    <nav class="navbar fixed-top bg-body-tertiary">
      <nav class="navbar bg-body-tertiary">
        <div class="container-fluid">
          <div class="user-profile-js">
            <div class="nav col-1">${userProfile.completeName}</div>
            <div class="nav col-1">${userProfile.username}</div>
           <div class="update-btn">
              <i class="bi bi-arrow-clockwise" title="update profile"></i>
            </div>
          </div>
          <form class="d-flex" role="search id="search-form-js"">
            <input class="form-control id="search-input-js"  me-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success" type="submit">Search</button>
          </form>
        </div>
      </nav>
    </nav>
  `;
}

export function defaultHomeview(userList, userProfile) {
  let tableContent = '';

  tableContent += renderHeader(userProfile);

  tableContent += `
    <h2>User Table</h2>
        <ul class="responsive-table">
          <li class="table-header">
            <div class="header-col-1">User Id</div>
            <div class="header-col-2">Complete Name</div>
            <div class="header-col-3">Username</div>
          </li>
        
  `;

  userList.forEach(user => {
    tableContent += `
          <li class="table-row">
            <div class="col col-1">${user.id}</div>
            <div class="col col-2">${user.completeName}</div>
            <div class="col col-3">${user.username}</div>
            <div class="trash-icon">
              <i class="bi bi-trash" title="delete this user"></i>
            </div>
          </li>
    `;
  });

  tableContent += `</ul>`;
  containerElement.innerHTML = tableContent;
}

