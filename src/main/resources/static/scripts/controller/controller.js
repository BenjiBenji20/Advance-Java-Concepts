import { registrationView, authView, 
  defaultHomeView, homeHeaderView,
  clearSuggestion, displaySearchSuggestion
} from '../view/view.js';
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
  
      if(result) {
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
      const result = await ServiceAPI.authUserService(userData); // get the data of user who logged in
      const allUserData = await ServiceAPI.getAllUsersService(); // get all users data

      if(result.error) {
        // if there's no data
        alert(result.error);
      } 
      else {
        alert('Login successfull!');
        homeHeaderView(result);
        defaultHomeView(allUserData);
      }
    } 
    catch (error) {
      console.error("Error found: ", error);
    }
  });
}

/**
 * Get all user's data
 */
export async function userTableController() {
  try {
    const allUserData = await ServiceAPI.getAllUsersService(); // get all users data

    if(allUserData) {
      defaultHomeView(allUserData);
    } else {
      console.log('BACK TO THE REGISTER PAGE')
      loginFormController();
    }
  } 
  catch (error) {
    console.error("Error found: ", error);
  }
}

/**
 * SEARCH controller: TYPE
 * It fetch data while typing and display fetched data to the suggestion
 */
export function searchController() {
  const searchInput = document.getElementById("search-input-js");
  const searchForm =document.getElementById("search-form-js");

  // handle search inputs
  searchInput.addEventListener('input', async(e) => {
    const keyword = searchInput.value.trim();

    try {
      // if user deleted all input type characters, then it will render the default view
      if(keyword.length === 0) {
        clearSuggestion(); // clear suggestion container
        const allUserData = await ServiceAPI.getAllUsersService(); // get all users data
        defaultHomeView(allUserData);

        return;
      }

      const searchResult = await ServiceAPI.searchUserService(keyword);
      displaySearchSuggestion(searchResult);
    } 
    catch (error) {
      console.error("Error found: ", error);
    }
  });

  // Handle search form submission
  searchForm.addEventListener('submit', async (e) => {
    e.preventDefault(); // Prevent form submission and page reload

    const keyword = searchInput.value.trim();

    try {
      if (keyword.length === 0) {
        // If the keyword is empty, fetch all users and render the default view
        const allUserData = await ServiceAPI.getAllUsersService();
        defaultHomeView(allUserData);
      } else {
        // Perform a search and display the results in the table
        const searchResult = await ServiceAPI.searchUserService(keyword);

        if (searchResult && searchResult.length > 0) {
          defaultHomeView(searchResult); // Render the search results in the table
        } else {
          alert("No users found matching your search.");
          const allUserData = await ServiceAPI.getAllUsersService();
          defaultHomeView(allUserData); // render default home
        }
      }
    } catch (error) {
      console.error("Error found: ", error);
    }
  });
}